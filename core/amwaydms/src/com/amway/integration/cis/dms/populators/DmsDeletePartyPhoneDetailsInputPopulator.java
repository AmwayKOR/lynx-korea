/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.integration.dms.services.DeletePartyPhoneRequest;
import com.amway.integration.dms.services.PartyPhoneData;


/**
 * Populator implementation for {@link PartyPhoneDetailsRequestData} as source and {@link DeletePartyPhoneRequest} as
 * target type.
 */
public class DmsDeletePartyPhoneDetailsInputPopulator extends AbstractDmsPopulator
		implements Populator<PartyPhoneDetailsRequestData, DeletePartyPhoneRequest>
{
	@Override
	public void populate(final PartyPhoneDetailsRequestData source, final DeletePartyPhoneRequest target)
			throws ConversionException
	{
		final PartyPhoneData partyPhoneData = new PartyPhoneData();
		final PhoneMasterRequestData phoneMasterData = source.getPhoneMasterListData().get(0);

		partyPhoneData.setCntryCd(convertToJAXBString("cntryCd", phoneMasterData.getCntryCd()));
		partyPhoneData.setDayFlag(convertToJAXBString("dayFlag", phoneMasterData.getDayFlag()));
		partyPhoneData.setContactPointName(convertToJAXBString("contactPointName", phoneMasterData.getContactPointName()));
		partyPhoneData.setPhoneAreaCd(convertToJAXBString("phoneAreaCd", phoneMasterData.getPhoneAreaCd()));
		partyPhoneData.setPhoneCntryCd(convertToJAXBString("phoneCntryCd", phoneMasterData.getPhoneCntryCd()));
		partyPhoneData.setPhoneLocalNum(convertToJAXBString("phoneLocalNum", phoneMasterData.getPhoneLocalNum()));
		partyPhoneData.setPartyId(convertToJAXBString("partyId", phoneMasterData.getPartyId()));
		partyPhoneData.setEveningFlag(convertToJAXBString("eveningFlag", phoneMasterData.getEveningFlag()));

		target.setPartyPhoneData(partyPhoneData);
	}
}
