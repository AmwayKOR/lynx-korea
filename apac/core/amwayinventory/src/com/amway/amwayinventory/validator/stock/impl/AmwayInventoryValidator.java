package com.amway.amwayinventory.validator.stock.impl;

import static com.amway.amwayinventory.constants.AmwayInventoryConstants.*;

import de.hybris.platform.ordersplitting.WarehouseService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amway.amwayinventory.data.AmwayInventoryBean;


/**
 * Validator to check inventory beans
 */
public class AmwayInventoryValidator implements Validator
{
	@Autowired
	private WarehouseService warehouseService;

	@Override
	public boolean supports(Class<?> aClass)
	{
		return true;
	}

	@Override
	public void validate(Object inventoryBean, Errors errors)
	{
		AmwayInventoryBean amwayInventoryBean = (AmwayInventoryBean) inventoryBean;
		if (StringUtils.isBlank(amwayInventoryBean.getBaseItemNumber()))
		{
			errors.reject(ERROR_PRODUCT_CODE_BLANK);
		}
		if (StringUtils.isBlank(amwayInventoryBean.getWarehouseCode()))
		{
			errors.reject(ERROR_WAREHOUSE_CODE_BLANK);
		}
		else if (!checkWarehouseExisting(amwayInventoryBean.getWarehouseCode()))
		{
			errors.reject(ERROR_WAREHOUSE_NOT_FOUND);
		}

		if (amwayInventoryBean.getAvailable() == null)
		{
			errors.reject(ERROR_AVAILABLE_EMPTY);
		}
	}

	private boolean checkWarehouseExisting(String warehouseCode)
	{
		try
		{
			return warehouseService.getWarehouseForCode(warehouseCode) != null;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
