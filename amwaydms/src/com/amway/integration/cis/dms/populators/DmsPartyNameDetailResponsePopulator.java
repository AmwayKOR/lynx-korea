package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.amway.core.dms.party.data.PartyData;
import com.amway.integration.dms.services.PartyMasterNameDetailsSvcObj;
import com.amway.integration.dms.services.PartyNameDetailResponse;


/**
 * Populator implementation for {@link PartyNameDetailResponse} as source and {@link PartyData} as target type.
 */
public class DmsPartyNameDetailResponsePopulator implements Populator<PartyNameDetailResponse, PartyData>
{
	@Override
	public void populate(final PartyNameDetailResponse source, final PartyData target) throws ConversionException
	{
		final List<PartyMasterNameDetailsSvcObj> partyNameList = source.getPartyMasterNameDtlList();
		final PartyMasterNameDetailsSvcObj partyMasterNameDetails = partyNameList.get(0);
		//		final PartyMaster partyDetails = partyMasterNameDetails.getPartyMst();
		//
		//		final Date dob = toDate(partyDetails.getDateOfBirth());
		//		target.setDateOfBirth(dob);
		//		target.setGender(partyDetails.getGender());
		//		target.setMaritalStatus(partyDetails.getMaritalStatusCd());
	}

	/**
	 * @param calendar
	 * @return Date
	 */
	public static Date toDate(final XMLGregorianCalendar calendar)
	{
		if (calendar == null)
		{
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}


}
