/**
 *
 */
package com.amway.core.account.provider.impl;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import com.amway.core.model.AmwayAccountModel;


/**
 * To retrieve the default code for an Account as String.
 */
public class AmwayaccountUidAttributeProvider implements DynamicAttributeHandler<String, AmwayAccountModel>
{
	/**
	 * {@link #get(com.amway.core.model.AmwayAccountModel)}
	 */
	@Override
	public String get(final AmwayAccountModel abstractOrder)
	{
		return String.valueOf(abstractOrder.getCode());
	}

	/**
	 * {@link #set(com.amway.core.model.AmwayAccountModel, java.lang.String)}
	 */
	@Override
	public void set(final AmwayAccountModel arg0, final String arg1)
	{
		try
		{
			arg0.setCode(arg1);
		}
		catch (final NumberFormatException nExc)
		{
			throw new UnsupportedOperationException("Code can only be a valid long value");
		}
	}
}
