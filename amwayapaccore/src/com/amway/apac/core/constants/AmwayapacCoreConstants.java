/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.apac.core.constants;

/**
 * Global class for all AmwayapacCore constants. You can add global constants for your extension into this class.
 */
public final class AmwayapacCoreConstants extends GeneratedAmwayapacCoreConstants
{
	public static final String EXTENSIONNAME = "amwayapaccore";


	private AmwayapacCoreConstants()
	{
		//empty
	}

	// implement here constants used by this extension
	public static final String QUOTE_BUYER_PROCESS = "quote-buyer-process";
	public static final String QUOTE_SALES_REP_PROCESS = "quote-salesrep-process";
	public static final String QUOTE_USER_TYPE = "QUOTE_USER_TYPE";
	public static final String QUOTE_SELLER_APPROVER_PROCESS = "quote-seller-approval-process";
	public static final String QUOTE_TO_EXPIRE_SOON_EMAIL_PROCESS = "quote-to-expire-soon-email-process";
	public static final String QUOTE_EXPIRED_EMAIL_PROCESS = "quote-expired-email-process";
	public static final String QUOTE_POST_CANCELLATION_PROCESS = "quote-post-cancellation-process";

	/**
	 * String constant source.
	 */
	public static final String SOURCE_STRING = "source";

	/**
	 * String constant target.
	 */
	public static final String TARGET_STRING = "target";


	/**
	 * int constant - 50.
	 */
	public static final int FIFTY_INT = 50;

	/**
	 * int constant - 100.
	 */
	public static final int HUNDRED_INT = 100;

	/**
	 * int constant - 200.
	 */
	public static final int TWO_HUNDRED_INT = 200;

	/**
	 * Maximum length of the shopping lists names.
	 */
	public static final int SHOPPING_LIST_DEFAULT_MAX_LENGTH = 100;

	/**
	 * Default quantity to add in the wishlist for a product.
	 */
	public static final Integer SHOPPING_LIST_ENTRY_DEFAULT_QUANTITY = Integer.valueOf(1);

	/**
	 * String constant for ascending to be used in queries.
	 */
	public static final String ASC_STRING = "ASC";

	/**
	 * String constant for descending to be used in queries.
	 */
	public static final String DESC_STRING = "DESC";

	/**
	 * Sort field string constant
	 */
	public static final String SORT_FIELD_STRING = "sortField";

	/**
	 * Sort order string constant
	 */
	public static final String SORT_ORDER_STRING = "sortOrder";

	/**
	 * user string constant
	 */
	public static final String USER_STRING = "user";

	/**
	 * Product Listing Page grid image format
	 */
	public static final String PRODUCT_LIST_GRID_FORMAT = "productGrid";

}
