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
public class AmwayInventoryFullUpdateStockStrategyImplTest
{
	@InjectMocks
	private AmwayInventoryFullUpdateStockStrategyImpl amwayInventoryFullUpdateStockStrategy;

	@Mock
	private AmwayInventoryStockService amwayInventoryStockService;

	@Mock
	private StockLevelModel stockLevel;
	@Mock
	private AmwayConsolidatedInventoryBean consolidatedInventoryBean;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(consolidatedInventoryBean.getAmount()).thenReturn(5);
	}

	@Test
	public void shouldSetCurrentStockToAmountWhenStockAlreadyExist()
	{
		when(amwayInventoryStockService.getStockLevel(any(), any())).thenReturn(stockLevel);

		amwayInventoryFullUpdateStockStrategy.changeStockLevel(consolidatedInventoryBean);

		verify(stockLevel).setAvailable(5);
	}

	@Test
	public void shouldCreateStockWithAmountWhenStockNotExist()
	{
		when(amwayInventoryStockService.getStockLevel(any(), any())).thenReturn(null);

		amwayInventoryFullUpdateStockStrategy.changeStockLevel(consolidatedInventoryBean);

		verify(amwayInventoryStockService).createStockLevel(any(), any(), eq(5));
	}
}