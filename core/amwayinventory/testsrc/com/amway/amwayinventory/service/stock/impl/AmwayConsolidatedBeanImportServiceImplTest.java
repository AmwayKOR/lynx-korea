package com.amway.amwayinventory.service.stock.impl;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.amway.amwayinventory.AmwayInventoryTestConstants;
import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.service.stock.AmwayInventoryStockService;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class AmwayConsolidatedBeanImportServiceImplTest
{
	@InjectMocks
	private AmwayConsolidatedBeanImportServiceImpl amwayConsolidatedBeanImportService = new AmwayConsolidatedBeanImportServiceImpl();

	@Spy
	private ThreadPoolTaskExecutor inventoryExecutor = new ThreadPoolTaskExecutor();
	@Mock
	private AmwayInventoryStockService amwayInventoryStockService;
	@Mock
	private ModelService modelService;


	@Before
	public void setUp()
	{
		inventoryExecutor.setMaxPoolSize(1);
		inventoryExecutor.initialize();
		when(amwayInventoryStockService
				.getStockLevel(AmwayInventoryTestConstants.PRODUCT_1, AmwayInventoryTestConstants.WAREHOUSE_1)).thenReturn(
				createStockLevel(AmwayInventoryTestConstants.PRODUCT_1));
		when(amwayInventoryStockService
				.getStockLevel(AmwayInventoryTestConstants.PRODUCT_2, AmwayInventoryTestConstants.WAREHOUSE_1)).thenReturn(
				createStockLevel(AmwayInventoryTestConstants.PRODUCT_2));
		when(amwayInventoryStockService
				.getStockLevel(AmwayInventoryTestConstants.PRODUCT_3, AmwayInventoryTestConstants.WAREHOUSE_1)).thenReturn(
				createStockLevel(AmwayInventoryTestConstants.PRODUCT_3));
	}

	@Test
	public void whenConsolidatedBeansAreImportedThenModelServiceSaveAllIsCalled() throws Exception
	{
		List<AmwayConsolidatedInventoryBean> consolidatedInventoryBeans = Arrays.asList(
				consolidatedBean(AmwayInventoryTestConstants.PRODUCT_1, AmwayInventoryTestConstants.WAREHOUSE_1),
				consolidatedBean(AmwayInventoryTestConstants.PRODUCT_2, AmwayInventoryTestConstants.WAREHOUSE_1),
				consolidatedBean(AmwayInventoryTestConstants.PRODUCT_3, AmwayInventoryTestConstants.WAREHOUSE_1));
		amwayConsolidatedBeanImportService.importStocks(consolidatedInventoryBeans);
		verify(modelService).saveAll(argThat(new EqualityArgumentStockLevelMatcher(
				Arrays.asList(createStockLevel(AmwayInventoryTestConstants.PRODUCT_1),
						createStockLevel(AmwayInventoryTestConstants.PRODUCT_2),
						createStockLevel(AmwayInventoryTestConstants.PRODUCT_3)))));
	}

	private AmwayConsolidatedInventoryBean consolidatedBean(String productCode, String warehouseCode)
	{
		AmwayConsolidatedInventoryBean amwayConsolidatedInventoryBean = new AmwayConsolidatedInventoryBean();
		amwayConsolidatedInventoryBean.setProductCode(productCode);
		amwayConsolidatedInventoryBean.setWarehouseCode(warehouseCode);
		amwayConsolidatedInventoryBean.setAmount(0);
		return amwayConsolidatedInventoryBean;
	}

	private StockLevelModel createStockLevel(String productCode)
	{
		StockLevelModel stockLevel = new StockLevelModel();
		stockLevel.setProductCode(productCode);
		return stockLevel;
	}

	private class EqualityArgumentStockLevelMatcher extends ArgumentMatcher<Collection<StockLevelModel>>
	{
		private Collection<StockLevelModel> stockLevels;

		public EqualityArgumentStockLevelMatcher(Collection<StockLevelModel> stockLevels)
		{
			this.stockLevels = stockLevels;
		}

		@Override
		public boolean matches(Object argument)
		{
			if (argument instanceof Collection)
			{
				//@formatter:off
				return ((Collection<StockLevelModel>)argument).stream()
						.allMatch(this::findStockLevelInSource);
				//@formatter:on
			}
			return false;
		}

		private boolean findStockLevelInSource(StockLevelModel stockLevel)
		{
			//@formatter:off
			return stockLevels.stream()
					.filter(sl -> Objects.equals(sl.getProductCode(), stockLevel.getProductCode()))
					.findAny()
					.isPresent();
			//@formatter:on
		}
	}

}
