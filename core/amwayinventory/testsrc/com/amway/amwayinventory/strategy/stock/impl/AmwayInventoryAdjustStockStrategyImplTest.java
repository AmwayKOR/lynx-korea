package com.amway.amwayinventory.strategy.stock.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.service.stock.AmwayInventoryStockService;


@UnitTest
public class AmwayInventoryAdjustStockStrategyImplTest
{
	@InjectMocks
	private AmwayInventoryAdjustStockStrategyImpl amwayInventoryAdjustStockStrategy;

	@Mock
	private AmwayInventoryStockService amwayInventoryStockService;

	@Mock
	private AmwayConsolidatedInventoryBean consolidatedInventoryBean;
	@Mock
	private StockLevelModel stockLevel;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(amwayInventoryStockService.getStockLevel(any(), any())).thenReturn(stockLevel);
		when(stockLevel.getAvailable()).thenReturn(10);
	}

	@Test
	public void shouldAddDeltaWhenStockExistAndDeltaIsPositive()
	{
		when(consolidatedInventoryBean.getAmount()).thenReturn(5);

		amwayInventoryAdjustStockStrategy.changeStockLevel(consolidatedInventoryBean);

		verify(stockLevel).setAvailable(15);
	}

	@Test
	public void shouldDecreaseStockByDeltaWhenStockExistAndDeltaIsNegativeButModulusLessThanCurrentStock()
	{
		when(consolidatedInventoryBean.getAmount()).thenReturn(-5);

		amwayInventoryAdjustStockStrategy.changeStockLevel(consolidatedInventoryBean);

		verify(stockLevel).setAvailable(5);
	}

	@Test
	public void shouldSetStockZeroWhenStockExistAndDeltaIsNegativeButModulusMoreThanCurrentStock()
	{
		when(consolidatedInventoryBean.getAmount()).thenReturn(-15);

		amwayInventoryAdjustStockStrategy.changeStockLevel(consolidatedInventoryBean);

		verify(stockLevel).setAvailable(0);
	}

	@Test
	public void shouldCreateStockWithDeltaAvailableWhenStockNotExistAndDeltaIsPositive()
	{
		when(amwayInventoryStockService.getStockLevel(any(), any())).thenReturn(null);
		when(consolidatedInventoryBean.getAmount()).thenReturn(5);

		amwayInventoryAdjustStockStrategy.changeStockLevel(consolidatedInventoryBean);

		verify(amwayInventoryStockService).createStockLevel(any(), any(), eq(5));
	}

	@Test
	public void shouldCreateZeroAvailableStockWhenStockNotExistAndDeltaIsNegative()
	{
		when(amwayInventoryStockService.getStockLevel(any(), any())).thenReturn(null);
		when(consolidatedInventoryBean.getAmount()).thenReturn(-15);

		amwayInventoryAdjustStockStrategy.changeStockLevel(consolidatedInventoryBean);

		verify(amwayInventoryStockService).createStockLevel(any(), any(), eq(0));
	}
}