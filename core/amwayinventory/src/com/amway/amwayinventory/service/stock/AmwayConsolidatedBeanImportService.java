package com.amway.amwayinventory.service.stock;

import java.util.Collection;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;


/**
 * Service to update stocks by info from consolidated inventory beans
 */
public interface AmwayConsolidatedBeanImportService
{
	/**
	 * Update stock levels by info from consolidated inventory beans
	 *
	 * @param consolidatedInventoryBeans
	 * 		- consolidated inventory beans
	 */
	void importStocks(Collection<AmwayConsolidatedInventoryBean> consolidatedInventoryBeans);
}
