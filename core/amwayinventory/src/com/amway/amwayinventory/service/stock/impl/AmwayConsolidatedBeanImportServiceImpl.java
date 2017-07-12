package com.amway.amwayinventory.service.stock.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.service.stock.AmwayConsolidatedBeanImportService;
import com.amway.amwayinventory.service.stock.AmwayInventoryStockService;


/**
 * Service to import consolidated beans into system. Import is executed via thread pool executor
 */
public class AmwayConsolidatedBeanImportServiceImpl implements AmwayConsolidatedBeanImportService
{
	@Autowired
	private ThreadPoolTaskExecutor inventoryExecutor;
	@Autowired
	private AmwayInventoryStockService amwayInventoryStockService;
	@Autowired
	private ModelService modelService;

	@Override
	public void importStocks(Collection<AmwayConsolidatedInventoryBean> amwayConsolidatedInventoryBeans)
			throws ExecutionException, InterruptedException
	{
		Future<?> future = inventoryExecutor.submit(() -> updateStockLevel(amwayConsolidatedInventoryBeans));
		future.get();
	}

	private void updateStockLevel(Collection<AmwayConsolidatedInventoryBean> consolidatedInventoryBeans)
	{
		//formatter:off
		List<StockLevelModel> stocksToSave = consolidatedInventoryBeans.stream()
				.map(this::getStocksToSave)
				.collect(Collectors.toList());
		//formatter:on
		modelService.saveAll(stocksToSave);
	}

	private StockLevelModel getStocksToSave(AmwayConsolidatedInventoryBean consolidatedInventoryBean)
	{
		String productCode = consolidatedInventoryBean.getProductCode();
		String warehouseCode = consolidatedInventoryBean.getWarehouseCode();
		Integer amount = consolidatedInventoryBean.getAmount();
		StockLevelModel stockLevel = amwayInventoryStockService.getStockLevel(productCode,warehouseCode);
		if(stockLevel == null)
		{
			return amwayInventoryStockService.createStockLevel(productCode, warehouseCode, amount);
		}
		stockLevel.setAvailable(amount);
		return stockLevel;
	}


}
