package com.amway.amwayinventory.service.stock.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.impl.StockLevelDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amway.amwayinventory.AmwayInventoryTestConstants;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class AmwayInventoryStockServiceImplTest
{
	@Mock
	private StockLevelDao stockLevelDao;
	@Mock
	private WarehouseService warehouseService;
	@Mock
	private ModelService modelService;

	@InjectMocks
	private AmwayInventoryStockServiceImpl amwayInventoryStockService = new AmwayInventoryStockServiceImpl();

	@Test
	public void whenGetStockLevelThenStockLevelDaoIsCalled() throws Exception
	{
		WarehouseModel mockWarehouse = new WarehouseModel();
		when(warehouseService.getWarehouseForCode(AmwayInventoryTestConstants.WAREHOUSE_1)).thenReturn(mockWarehouse);
		amwayInventoryStockService.getStockLevel(AmwayInventoryTestConstants.PRODUCT_1, AmwayInventoryTestConstants.WAREHOUSE_1);
		verify(stockLevelDao).findStockLevel(AmwayInventoryTestConstants.PRODUCT_1, mockWarehouse);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenAvailableAmountIsNegativeThenThrowException() throws Exception
	{
		amwayInventoryStockService.createStockLevel(AmwayInventoryTestConstants.PRODUCT_1, AmwayInventoryTestConstants.WAREHOUSE_1,-1);
	}

	@Test(expected = JaloSystemException.class)
	public void whenStockLevelAlreadyExistsThenThrowException() throws Exception
	{
		WarehouseModel mockWarehouse = new WarehouseModel();
		when(warehouseService.getWarehouseForCode(AmwayInventoryTestConstants.WAREHOUSE_1)).thenReturn(mockWarehouse);
		when(stockLevelDao.findStockLevel(AmwayInventoryTestConstants.PRODUCT_1, mockWarehouse)).thenReturn(new StockLevelModel());
		amwayInventoryStockService.createStockLevel(AmwayInventoryTestConstants.PRODUCT_1, AmwayInventoryTestConstants.WAREHOUSE_1,0);
	}

	@Test
	public void whenStockLevelIsCreatedThenModelServiceSaveIsCalled() throws Exception
	{
		StockLevelModel mockStockLevel = new StockLevelModel();
		when(modelService.create(StockLevelModel.class)).thenReturn(mockStockLevel);
		amwayInventoryStockService.createStockLevel(AmwayInventoryTestConstants.PRODUCT_1, AmwayInventoryTestConstants.WAREHOUSE_1,0);
		verify(modelService).save(mockStockLevel);
	}

}
