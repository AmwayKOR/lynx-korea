
/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */

package com.amway.core.constants;

/**
 * Global class for all AmwayCore constants. You can add global constants for your extension into this class.
 */
public final class AmwaycoreConstants extends GeneratedAmwaycoreConstants
{
	public static final String EXTENSIONNAME = "amwaycore";

	// TODO use AmwayAccount relation
	public static final String DISTRIBUTOR_GROUP = "amwaycore.defaultABOGroup";

	public static final String DEFAULT_BASE_SITE_ID = "amwaycore.defaultBaseSiteId";

	// implement here constants used by this extension
	public static final String RENEWAL_NOT_FOUND_ERROR_MSG = "Renewal not found";

	/**
	 * Session variable account.
	 */
	public class SessionVariables
	{
		public static final String ACCOUNT = "account";
		public static final String API_AUTH_TOKEN = "apiToken";
	}


	/**
	 * Price groups for ABO,RETAIL,EMPLOYEE.
	 */
	public class PriceGroups
	{
		public static final String ABO_USER_PRICE_GROUP = "AmwayDefaultABOPriceGroup";
		public static final String RETAIL_PRICE_GROUP = "AmwayDefaultRetailPriceGroup";
		public static final String EMPLOYEE_PRICE_GROUP = "AmwayDefaultEmployeePriceGroup";
		public static final String MAX_DISCOUNT_PRICE_GROUP = "AmwayDefaultMaxDiscountPriceGroup";
	}

	/**
	 * This method defines about Amway profile detail levels.
	 */
	public class AmwayProfileDetailLevels
	{
		public static final String MINDETAIL = "MinDetail";
		public static final String FULLDETAIL = "FullDetail";
		public static final String ORDERPAYMENTS = "OrderPayments";
	}

	/**
	 * this method defines about account types of amway.
	 */
	public class AccountTypes
	{
		public static final String BUSINESS_OWNER = "BusinessOwner";
		public static final String CUSTOMER = "Customer";
		public static final String EMPLOYEE = "Employee";
	}

	/**
	 * This method defines account sub types code.
	 */
	public class AccountSubTypesCode
	{
		public static final String BUSINESS_OWNER = "1";
		public static final String CUSTOMER = "4";
		public static final String EMPLOYEE = "7";
	}

	/**
	 * this is for segmentation level types.
	 */
	public class SegmentationLevelTypes
	{
		public static final String PLATINUM = "Platinum";
		public static final String SILVER_PRODUCER = "SilverProducer";
		public static final String SILVER_SPONSOR = "SilverSponsor";
		public static final String NON_GROUP_LEADER = "NonGroupLeader";
	}

	/**
	 * this method defines types of addresses.
	 */
	public class AddressTypes
	{
		public static final String BILLING = "Billing";
		public static final String SHIPPING = "Shipping";
		public static final String REGISTRATION = "Registration";
		public static final String MAILING = "Mailing";
	}

	/**
	 * Types of Payment modes.
	 */
	public class PaymentMode
	{

		public static final String ARCREDIT = "arCredit";
		public static final String CREDITCARD = "creditCard";
		public static final String CASH = "cash";

		/**
		 * Account balance type : credit & Monetary.
		 */
		public class AccountBalanceType
		{
			public static final String ACCOUNTBALANCETYPECREDIT = "Credit";
			public static final String ACCOUNTBALANCETYPEMONETARY = "Monetary";
		}

	}

	/**
	 * this method defines payment detail map.
	 */
	public class PaymentDetailsMap
	{
		public static final String AMOUNT = "amount";
		public static final String CC_CPFNUMBER = "cpfNumber";
		public static final String CC_INSTALLMENTS = "installments";
	}

	/**
	 * Types of block privilege.
	 */
	public class BlocksPrivilegeTypes
	{
		public static final String BLOCKAMWAYCREDIT = "BlockAmwayCredit";
		public static final String BLOCKAMWAYSPONSORING = "BlockSponsoring";

		/**
		 * Flags for Block & Unblock.
		 */
		public class IsBlockFlag
		{
			public static final String BLOCK = "Y";
			public static final String UNBLOCK = "N";
		}

	}



	/**
	 * Types of sales application.
	 */
	public class SalesApplication
	{
		public static final String WEB = "Web";
		public static final String WEBMOBILE = "WebMobile";
		public static final String CALLCENTER = "CallCenter";
		public static final String POS = "pos";
	}

	/**
	 * this is for types of delivey methods.
	 */
	public class DeliveryModes
	{
		public static final String FREE_SHIPPING = "free-shipping";
		public static final String BOPIS = "pickup-bopis";
		public static final String POS = "pickup-pos";
	}

	/**
	 * Types of tax : cpf & cnpj
	 */
	public class TaxTypes
	{
		public static final String CPF = "CPF";
		public static final String CNPJ = "CNPJ";
	}


	private AmwaycoreConstants()
	{
		//empty
	}

	/**
	 *
	 * Wishlist related constants
	 *
	 */
	public class WishLists
	{
		public static final String DEFAULT_SHOPPING_LIST_NAME = "My Favorite List";
	}

	// implement here constants used by this extension
}
