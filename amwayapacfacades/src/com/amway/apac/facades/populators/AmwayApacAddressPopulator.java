package com.amway.apac.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressPopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;


/**
 * Overriding OOTB {@link AddressPopulator} to populate APAC specific fields.
 *
 * @author Aaron Yong
 */
public class AmwayApacAddressPopulator extends AddressPopulator
{

	/**
	 * Overriding OOTB implementation to populate APAC specific fields.
	 */
	@Override
	protected void populateAddressFields(final AddressModel source, final AddressData target)
	{
		super.populateAddressFields(source, target);
		target.setLine3(source.getLine3());
	}
}
