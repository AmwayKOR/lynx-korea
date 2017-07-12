package com.amway.amwayinventory.service.stock.impl;

import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amway.amwayinventory.strategy.stock.AmwayConsolidationStockStrategy;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class AmwayStockConsolidationServiceImplTest
{
	@InjectMocks
	private AmwayStockConsolidationServiceImpl amwayStockConsolidationService = new AmwayStockConsolidationServiceImpl();

	@Mock
	private AmwayConsolidationStockStrategy amwayConsolidationStockStrategy;

	@Test
	public void whenInventoryIsConsolidatedThenStrategyIsCalled() throws Exception
	{
		amwayConsolidationStockStrategy.consolidateStocks(Collections.emptyList());
		verify(amwayConsolidationStockStrategy).consolidateStocks(anyCollection());
	}

}
