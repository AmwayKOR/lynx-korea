package com.amway.amwayinventory.constants;

/**
 * Global class for all Amwayinventory constants. You can add global constants for your extension into this class.
 */
public final class AmwayInventoryConstants extends GeneratedAmwayInventoryConstants
{
	private AmwayInventoryConstants()
	{
		//empty to avoid instantiating this constant class
	}

	public static final String MSG_DELIMITER = ";";
	public static final String MSG_PREFIX = "[";
	public static final String MSG_SUFFIX = "]";

	public static final String ERROR_PRODUCT_NOT_FOUND= "amway.inventory.bean.baseitemnumber.nonexists";
	public static final String ERROR_PRODUCT_CODE_BLANK = "amway.inventory.bean.baseitemnumber.empty";
	public static final String ERROR_WAREHOUSE_CODE_BLANK = "amway.inventory.bean.warehousecode.empty";
	public static final String ERROR_WAREHOUSE_NOT_FOUND = "amway.inventory.bean.warehousecode.doesnotexist";
	public static final String ERROR_AVAILABLE_EMPTY = "amway.inventory.bean.available.empty";
	public static final String ERROR_AVAILABLE_NEGATIVE = "amway.inventory.bean.available.negative";
}