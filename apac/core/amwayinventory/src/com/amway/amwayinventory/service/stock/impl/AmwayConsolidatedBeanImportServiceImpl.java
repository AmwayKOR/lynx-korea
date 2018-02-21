package com.amway.amwayinventory.service.stock.impl;

import static java.util.stream.Collectors.*;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.service.AmwayInventoryExecutorService;
import com.amway.amwayinventory.service.stock.AmwayConsolidatedBeanImportService;
import com.amway.amwayinventory.strategy.stock.AmwayInventoryStockChangeStrategy;


/**
 * Service to import consolidated beans into system.
 * Import is executed via different thread pool executors specified for each warehouse
 */
public class AmwayConsolidatedBeanImportServiceImpl implements AmwayConsolidatedBeanImportService
{
	@Autowired
	private AmwayInventoryExecutorService amwayInventoryExecutorService;
	@Autowired
	private ModelService modelService;

	private AmwayInventoryStockChangeStrategy amwayInventoryStockChangeStrategy;

	@Override
	public void importStocks(Collection<AmwayConsolidatedInventoryBean> consolidatedBeans)
	{
		//@formatter:off
		Map<String, Collection<AmwayConsolidatedInventoryBean>> beansGroupedByWarehouse = consolidatedBeans.stream()
				.collect(groupingBy(AmwayConsolidatedInventoryBean::getWarehouseCode, toCollection(HashSet::new)));
		//@formatter:on
		beansGroupedByWarehouse.entrySet().forEach(this::importStocksByWarehouse);
	}

	private void importStocksByWarehouse(Map.Entry<String, Collection<AmwayConsolidatedInventoryBean>> entry)
	{
		Executor executor = amwayInventoryExecutorService.getExecutorByWarehouse(entry.getKey());
		executor.execute(() -> updateStockLevel(entry.getValue(), amwayInventoryStockChangeStrategy::changeStockLevel));
	}

	private void updateStockLevel(Collection<AmwayConsolidatedInventoryBean> consolidatedBeans,
			Function<AmwayConsolidatedInventoryBean, StockLevelModel> changeStockFunction)
	{
		//@formatter:off
		List<StockLevelModel> stocksToSave = consolidatedBeans.stream()
				.map(changeStockFunction)
				.collect(toList());
		//@formatter:on
		modelService.saveAll(stocksToSave);
	}

	@Required
	public void setAmwayInventoryStockChangeStrategy(AmwayInventoryStockChangeStrategy amwayInventoryStockChangeStrategy)
	{
		this.amwayInventoryStockChangeStrategy = amwayInventoryStockChangeStrategy;
	}
}
