package com.amway.amwayinventory.service.stock.impl;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.WAREHOUSE_1;
import static java.util.Collections.singleton;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.concurrent.Executor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.service.AmwayInventoryExecutorService;
import com.amway.amwayinventory.strategy.stock.AmwayInventoryStockChangeStrategy;


@UnitTest
public class AmwayConsolidatedBeanImportServiceImplTest
{
	@InjectMocks
	private AmwayConsolidatedBeanImportServiceImpl amwayConsolidatedBeanImportService;

	@Mock
	private AmwayInventoryStockChangeStrategy amwayInventoryStockChangeStrategy;
	@Mock
	private AmwayInventoryExecutorService amwayInventoryExecutorService;
	@Mock
	private ModelService modelService;

	@Mock
	private AmwayConsolidatedInventoryBean consolidatedInventoryBean;
	@Mock
	private StockLevelModel stockLevelModel;
	@Mock
	private Executor executor;

	private Collection<AmwayConsolidatedInventoryBean> consolidatedInventoryBeans;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		doAnswer(invocation ->
		{
			((Runnable) invocation.getArguments()[0]).run();
			return null;
		}).when(executor).execute(any(Runnable.class));

		when(amwayInventoryStockChangeStrategy.changeStockLevel(consolidatedInventoryBean)).thenReturn(stockLevelModel);
		when(amwayInventoryExecutorService.getExecutorByWarehouse(WAREHOUSE_1)).thenReturn(executor);
		when(consolidatedInventoryBean.getWarehouseCode()).thenReturn(WAREHOUSE_1);
		consolidatedInventoryBeans = singleton(consolidatedInventoryBean);
	}

	@Test
	public void shouldPassTaskToExecutorWhenImportStocks()
	{
		amwayConsolidatedBeanImportService.importStocks(consolidatedInventoryBeans);

		verify(executor, times(1)).execute(any());
	}

	@Test
	public void shouldCallStockChangeStrategyWhenUpdatingStockLevels()
	{
		int count = consolidatedInventoryBeans.size();

		amwayConsolidatedBeanImportService.importStocks(consolidatedInventoryBeans);

		verify(amwayInventoryStockChangeStrategy, times(count)).changeStockLevel(any());
	}

	@Test
	public void shouldSaveAllStockLevelsWhenConsolidatedBeansAreImported()
	{
		amwayConsolidatedBeanImportService.importStocks(consolidatedInventoryBeans);

		verify(modelService).saveAll(anyCollectionOf(StockLevelModel.class));
	}
}
