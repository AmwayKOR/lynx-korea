package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.DeletePartyAddressRequestData;
import com.amway.integration.dms.services.DeleteAddress;
import com.amway.integration.dms.services.DeletePartyAddressRequest;


/**
 * Populator implementation for {@link DeletePartyAddressRequestData} as source and {@link DeletePartyAddressRequest} as
 * target type.
 */
public class DmsDeletePartyAddressInputPopulator extends AbstractDmsPopulator
		implements Populator<DeletePartyAddressRequestData, DeletePartyAddressRequest>
{
	@Override
	public void populate(final DeletePartyAddressRequestData source, final DeletePartyAddressRequest target)
			throws ConversionException
	{
		final DeleteAddress deleteAddress = new DeleteAddress();

		deleteAddress.setAboNum(source.getAboNum());
		deleteAddress.setContactPointName(convertToJAXBString("contactPointName", source.getContactPointName()));
		deleteAddress.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", source.getContactPointTypeCd()));
		deleteAddress.setPartyId(convertToJAXBString("partyId", source.getPartyId()));
		deleteAddress.setSalesPlanAff(source.getSalesPlanAff());

		target.setDeleteAddress(deleteAddress);
	}

}
