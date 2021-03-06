package com.amway.amwayinventory.validator.stock.impl;

import static com.amway.amwayinventory.constants.AmwayInventoryConstants.ERROR_AVAILABLE_NEGATIVE;

import org.springframework.validation.Errors;

import com.amway.amwayinventory.data.AmwayInventoryBean;


/**
 * Validator to check inventory beans when full update is occurred
 */
public class AmwayFullInventoryValidator extends AmwayInventoryValidator
{
	@Override
	public void validate(Object inventoryBean, Errors errors)
	{
		super.validate(inventoryBean, errors);
		AmwayInventoryBean amwayInventoryBean = (AmwayInventoryBean) inventoryBean;
		if (amwayInventoryBean.getAvailable() != null && amwayInventoryBean.getAvailable() < 0)
		{
			errors.reject(ERROR_AVAILABLE_NEGATIVE);
		}
	}
}
