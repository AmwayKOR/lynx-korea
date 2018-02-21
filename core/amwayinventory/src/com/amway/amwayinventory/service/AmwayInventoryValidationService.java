package com.amway.amwayinventory.service;

import java.util.Collection;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryValidationResult;


/**
 * Service for validation collection of {@link AmwayInventoryBean}
 */
public interface AmwayInventoryValidationService
{
	/**
	 * Validate collection of inventory beans
	 *
	 * @param inventoryBeans
	 * 		collection of beans for validation
	 * @return validation result dto
	 */
	AmwayInventoryValidationResult validate(Collection<AmwayInventoryBean> inventoryBeans);
}
