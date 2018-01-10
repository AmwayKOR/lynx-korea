package com.amway.amwayinventory.service.stock.impl;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.PRODUCT_1;
import static com.amway.amwayinventory.AmwayInventoryTestConstants.WAREHOUSE_1;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.impl.StockLevelDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@UnitTest
public class AmwayInventoryStockServiceImplTest
{
	@InjectMocks
	private AmwayInventoryStockServiceImpl amwayInventoryStockService;

	@Mock
	private StockLevelDao stockLevelDao;
	@Mock
	private WarehouseService warehouseService;
	@Mock
	private ModelService modelService;

	@Mock
	private WarehouseModel warehouse;
	@Mock
	private StockLevelModel stockLevel;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldFindWarehouseByCodeWhenGettingStockLevel()
	{
		amwayInventoryStockService.getStockLevel(PRODUCT_1, WAREHOUSE_1);

		verify(warehouseService).getWarehouseForCode(WAREHOUSE_1);
	}

	@Test
	public void shouldFindStockLevelByDaoWhenGettingStockLevel()
	{
		when(warehouseService.getWarehouseForCode(WAREHOUSE_1)).thenReturn(warehouse);

		amwayInventoryStockService.getStockLevel(PRODUCT_1, WAREHOUSE_1);

		verify(stockLevelDao).findStockLevel(PRODUCT_1, warehouse);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenAvailableAmountIsNegative()
	{
		amwayInventoryStockService.createStockLevel(PRODUCT_1, WAREHOUSE_1, -1);
	}

	@Test(expected = JaloSystemException.class)
	public void shouldThrowExceptionWhenStockLevelAlreadyExists()
	{
		when(stockLevelDao.findStockLevel(eq(PRODUCT_1), any())).thenReturn(stockLevel);

		amwayInventoryStockService.createStockLevel(PRODUCT_1, WAREHOUSE_1, 5);
	}

	@Test
	public void shouldSaveStockWhenStockNotExistAndAvailableIsPositive()
	{
		when(modelService.create(StockLevelModel.class)).thenReturn(stockLevel);

		amwayInventoryStockService.createStockLevel(PRODUCT_1, WAREHOUSE_1, 5);

		verify(modelService).save(stockLevel);
	}
}
