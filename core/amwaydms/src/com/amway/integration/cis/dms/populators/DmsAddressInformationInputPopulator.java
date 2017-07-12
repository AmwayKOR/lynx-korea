/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.dms.data.AddressInformationRequestData;
import com.amway.integration.dms.services.GetPartyAddressInput;


/**
 * Populator implementation for {@link AddressInformationRequestData} as source and {@link GetPartyAddressInput} as
 * target type.
 */
public class DmsAddressInformationInputPopulator implements Populator<AddressInformationRequestData, GetPartyAddressInput>
{
	@Override
	public void populate(final AddressInformationRequestData source, final GetPartyAddressInput target) throws ConversionException
	{
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setAboNum(source.getIboNo());
		target.setPartyId(source.getPartyId());
		target.setContactPointPurposeCd(source.getContactPointPurposeCd());
		target.setPrimaryFlag(source.getPrimaryFlag());
	}
}
