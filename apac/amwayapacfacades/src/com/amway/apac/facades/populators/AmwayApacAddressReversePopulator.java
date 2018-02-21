package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * Overriding OOTB {@link AddressReversePopulator} to populate APAC specific fields.
 *
 * @author Aaron Yong
 */
public class AmwayApacAddressReversePopulator extends AddressReversePopulator
{

	/**
	 * Overriding OOTB implementation to populate APAC specific fields.
	 */
	@Override
	public void populate(final AddressData addressData, final AddressModel addressModel) throws ConversionException
	{
		super.populate(addressData, addressModel);
		addressModel.setLine3(addressData.getLine3());
	}
}
