package com.amway.amwayinventory.service.stock.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.impl.StockLevelDao;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.amwayinventory.service.stock.AmwayInventoryStockService;


/**
 * Default implementation service to manage stock levels by product code and warehouse code
 */
public class AmwayInventoryStockServiceImpl implements AmwayInventoryStockService
{
	@Autowired
	private StockLevelDao stockLevelDao;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private ModelService modelService;

	@Override
	public StockLevelModel getStockLevel(String productCode, String warehouseCode)
	{
		WarehouseModel warehouse = warehouseService.getWarehouseForCode(warehouseCode);
		return stockLevelDao.findStockLevel(productCode, warehouse);
	}

	@Override
	public StockLevelModel createStockLevel(String productCode, String warehouseCode, Integer available)
	{
		if (available < 0)
		{
			throw new IllegalArgumentException("available amount cannot be negative.");
		}
		WarehouseModel warehouse = warehouseService.getWarehouseForCode(warehouseCode);
		StockLevelModel stockLevel = stockLevelDao.findStockLevel(productCode, warehouse);
		if (stockLevel != null)
		{
			throw new JaloSystemException("product [" + productCode + "] in warehouse [" + warehouseCode
					+ "] already exists. The same product cannot be created in the same warehouse again.");
		}
		stockLevel = modelService.create(StockLevelModel.class);
		stockLevel.setProductCode(productCode);
		stockLevel.setWarehouse(warehouse);
		stockLevel.setAvailable(available);
		stockLevel.setInStockStatus(InStockStatus.NOTSPECIFIED);
		stockLevel.setTreatNegativeAsZero(true);
		modelService.save(stockLevel);
		return stockLevel;
	}

}
