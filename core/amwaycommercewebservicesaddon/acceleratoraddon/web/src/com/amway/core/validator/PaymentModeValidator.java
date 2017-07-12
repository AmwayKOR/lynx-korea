/**
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.core.validator;

import de.hybris.platform.commercefacades.order.data.CartData;

import java.util.ArrayList;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



import com.amway.core.cart.data.PlaceOrderCartInfoData;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.facades.checkout.AmwayCheckoutFacade;


/**
 * Default commerce web services cart validator. Checks if cart is calculated and if needed values are filled.
 */
public class PaymentModeValidator implements Validator
{


	public class PaymentMode
	{

		public static final String ARCREDIT = "arCredit";
		public static final String CREDITCARD = "creditCard";
		public static final String CASH = "cash";

		public class AccountBalanceType
		{
			public static final String ACCOUNTBALANCETYPECREDIT = "Credit";
			public static final String ACCOUNTBALANCETYPEMONETARY = "Monetary";
		}

	}



	@Resource(name = "amwayCheckoutFacade")
	private AmwayCheckoutFacade amwayCheckoutFacade;

	@Override
	public boolean supports(final Class<?> clazz)
	{
		return CartData.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors)
	{
		final PlaceOrderCartInfoData placeOrderCartInfoData = (PlaceOrderCartInfoData) target;

		final Set<AmwayPaymentModeData> paymentModeDatas = amwayCheckoutFacade.getSupportedPaymentModes();

		final ArrayList<String> payList = new ArrayList<String>();

		for (final AmwayPaymentModeData data : paymentModeDatas)
		{
			payList.add(data.getCode());
		}

		if (placeOrderCartInfoData.getArCreditPaymentInfo() != null)
		{
			checkPaymentMode(payList, PaymentMode.ARCREDIT, errors);
		}


		if (placeOrderCartInfoData.getCashpaymententries() != null)
		{
			checkPaymentMode(payList, PaymentMode.CASH, errors);
		}
		if (placeOrderCartInfoData.getCcpaymententries() != null)
		{
			checkPaymentMode(payList, PaymentMode.CREDITCARD, errors);
		}



		//		final paymentModeData;


	}

	private boolean checkPaymentMode(final ArrayList<String> payList, final String selected, final Errors errors)
	{
		if (!payList.contains(selected))
		{
			errors.reject("cart.paymentMode.invalid");
		}
		return false;
	}

}
