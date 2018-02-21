package com.amway.amwayinventory.service.stock.impl;

import static java.util.Collections.emptyList;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.strategy.stock.AmwayConsolidationStockStrategy;


@UnitTest
public class AmwayStockConsolidationServiceImplTest
{
	@InjectMocks
	private AmwayStockConsolidationServiceImpl amwayStockConsolidationService;

	@Mock
	private AmwayConsolidationStockStrategy amwayConsolidationStockStrategy;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCallStrategyWhenConsolidateInventoryBeans()
	{
		amwayStockConsolidationService.consolidateInventory(emptyList());

		verify(amwayConsolidationStockStrategy).consolidateStocks(anyCollectionOf(AmwayInventoryBean.class));
	}
}
