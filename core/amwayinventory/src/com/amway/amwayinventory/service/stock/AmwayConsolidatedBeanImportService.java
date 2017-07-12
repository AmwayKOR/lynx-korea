package com.amway.amwayinventory.service.stock;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;


/**
 * Service to update stocks by info from consolidated inventory beans
 */
public interface AmwayConsolidatedBeanImportService
{
	/**
	 * update stock levels by info from consolidated inventory beans
	 * @param amwayConsolidatedInventoryBeans - consolidated inventory beans
	 */
	void importStocks(Collection<AmwayConsolidatedInventoryBean> amwayConsolidatedInventoryBeans)
			throws ExecutionException, InterruptedException;
}
