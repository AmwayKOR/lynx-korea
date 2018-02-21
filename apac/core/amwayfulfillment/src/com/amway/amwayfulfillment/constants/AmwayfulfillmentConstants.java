/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 *
 */
package com.amway.amwayfulfillment.constants;


/**
 * Global class for all Amwayfulfillment constants. You can add global constants for your extension into this class.
 */
public final class AmwayfulfillmentConstants extends GeneratedAmwayfulfillmentConstants
{
	public static final String EXTENSIONNAME = "amwayfulfillment";  //NOSONAR

	public static final String BASE_STORE_MISSING_ERROR = "BaseStoreMissingError";
	public static final String BASE_STORE_MISSING_MSG = "Base store with given code %s cannot be found";

	public static final String ORDER_MISSING_ERROR = "OrderMissingError";
	public static final String ORDER_MISSING_MSG = "Order with given code %s cannot be found";

	public static final String NULL_PARAMETER_ERROR = "NullParameterError";
	public static final String NULL_PARAMETER_MSG = "Check that all required parameters passed: %s";

	public static final String WAREHOUSE_MISSING_ERROR = "WarehouseMissingError";
	public static final String WAREHOUSE_MISSING_MSG = "Warehouse with given code %s cannot be found";

	public static final String PRODUCT_MISSING_ERROR = "ProductMissingError";
	public static final String PRODUCT_MISSING_MSG = "Order entry with product code: %s for order: %s not found";

	public static final String DIFFERENT_SHIPPED_ERROR = "ShippedQuantityDiffersError";
	public static final String DIFFERENT_SHIPPED_MSG = "Shipped quantity differs from %s order entries quantity";

	public static final String IGNORED_CONSIGNMENT_ERROR = "IgnoredConsignmentCreatedError";
	public static final String IGNORED_CONSIGNMENT_MSG = "Consignment created with ignore status. Possible reasons: wrong order status, shipment was already confirmed";

	public static final String SUCCESS = "Success";

	private AmwayfulfillmentConstants()
	{
		//empty to avoid instantiating this constant class
	}

}
