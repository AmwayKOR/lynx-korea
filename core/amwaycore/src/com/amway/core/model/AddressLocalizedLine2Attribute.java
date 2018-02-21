package com.amway.core.model;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;


/**
 * @author Dzmitry Misiuk
 */
public class AddressLocalizedLine2Attribute extends AbstractDynamicAttributeHandler<String, PickupAddressModel>
{
	public AddressLocalizedLine2Attribute()
	{
	}

	public String get(PickupAddressModel addressModel)
	{
		if (addressModel == null)
		{
			throw new IllegalArgumentException("address model is required");
		}
		else
		{
			return addressModel.getLocalizedStreetnumber();
		}
	}

	public void set(PickupAddressModel addressModel, String value)
	{
		if (addressModel != null)
		{
			addressModel.setLocalizedStreetnumber(value);
		}

	}
}
