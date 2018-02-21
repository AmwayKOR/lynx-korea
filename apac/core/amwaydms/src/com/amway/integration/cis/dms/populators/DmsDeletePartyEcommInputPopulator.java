/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
import com.amway.core.dms.data.PartyEcommRequestData;
import com.amway.integration.dms.services.DeletePartyEcommRequest;
import com.amway.integration.dms.services.PartyEcommData;


/**
 * Populator implementation for {@link AddUpdatePartyEcommRequestData} as source and {@link DeletePartyEcommRequest} as
 * target type.
 */
public class DmsDeletePartyEcommInputPopulator extends AbstractDmsPopulator
		implements Populator<AddUpdatePartyEcommRequestData, DeletePartyEcommRequest>
{
	@Override
	public void populate(final AddUpdatePartyEcommRequestData source, final DeletePartyEcommRequest target)
			throws ConversionException
	{
		final PartyEcommData partyEcommData = new PartyEcommData();
		final PartyEcommRequestData ecommData = source.getPartyEcommData().get(0);
		partyEcommData.setPartyId(convertToJAXBString("partyId", ecommData.getPartyId()));
		partyEcommData.setContactPointName(convertToJAXBString("contactPointName", ecommData.getContactPointName()));
		partyEcommData.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", ecommData.getContactPointTypeCd()));
		partyEcommData.setEcommAddr(convertToJAXBString("ecommAddr", ecommData.getEcommAddr()));
		partyEcommData.setEcommTypeCd(convertToJAXBString("ecommTypeCd", ecommData.getEcommTypeCd()));
		partyEcommData.setEcommName(convertToJAXBString("ecommName", ecommData.getEcommName()));
		partyEcommData.setEcommTypeName(convertToJAXBString("ecommTypeName", ecommData.getEcommTypeName()));
		partyEcommData.setStatusCd(convertToJAXBString("statusCd", ecommData.getStatusCd()));
		partyEcommData.setAllowForLogIn(convertToJAXBString("allowForLogin", ecommData.getAllowForLogIn()));
		partyEcommData.setContactId(convertToJAXBString("contactId", ecommData.getContactId()));


		target.setPartyEcommData(partyEcommData);
	}

}
