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
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Default commerce web services point of sale validator. Checks if point of sale with given name exist.
 */
public class PointOfSaleValidator implements Validator
{
	private PointOfServiceService pointOfServiceService;

	@Override
	public boolean supports(final Class<?> clazz)
	{
		return CartData.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors)
	{
		final String storeName = (String) target;
		final PointOfServiceModel pointOfServiceModel = StringUtils.isEmpty(storeName) ? null : getPointOfServiceService()
				.getPointOfServiceForName(storeName);
		if (pointOfServiceModel == null)
		{
			errors.reject("pointOfService.notExists");
		}

		//TODO: to return only if it is a valid POS
		//		if (pointOfServiceModel != null && pointOfServiceModel.get)
		//		{
		//			errors.reject("pointOfService.invalid");
		//		}
	}

	protected PointOfServiceService getPointOfServiceService()
	{
		return pointOfServiceService;
	}

	@Required
	public void setPointOfServiceService(final PointOfServiceService pointOfServiceService)
	{
		this.pointOfServiceService = pointOfServiceService;
	}

}

