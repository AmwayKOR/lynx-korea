/**
 *
 */
package com.amway.core.attributeHandlers;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import com.amway.core.model.AmwayEmailContactInfoModel;


/**
 * Dynamic attribute handler for primary email of User.
 *
 * @author vikrant.ghai
 */
public class AmwayEmailContactInfoAttributeHandler extends AbstractDynamicAttributeHandler<String, AmwayEmailContactInfoModel>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler#get(de.hybris.platform.
	 * servicelayer .model.AbstractItemModel)
	 */
	@Override
	public String get(final AmwayEmailContactInfoModel amwayEmailContactInfo)
	{
		if (null == amwayEmailContactInfo)
		{
			throw new IllegalArgumentException("user model is required");
		}
		return amwayEmailContactInfo.getUser().getCustomerEmail();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler#set(de.hybris.platform.
	 * servicelayer .model.AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(final AmwayEmailContactInfoModel amwayEmailContactInfo, final String value)
	{
		if (null == amwayEmailContactInfo)
		{
			return;
		}
		amwayEmailContactInfo.getUser().setCustomerEmail(value.toLowerCase());
	}
}
