package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.PartyPhoneData;
import com.amway.integration.dms.services.UpdatePartyPhoneRequest;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link PartyPhoneDetailsRequestData} as source and {@link UpdatePartyPhoneRequest} as
 * target type.
 */
public class DmsUpdatePartyPhoneDetailsInputPopulator extends AbstractDmsPopulator
		implements Populator<PartyPhoneDetailsRequestData, UpdatePartyPhoneRequest>
{
	@Override
	public void populate(final PartyPhoneDetailsRequestData source, final UpdatePartyPhoneRequest target)
			throws ConversionException
	{
		final PartyPhoneData partyPhoneData = new PartyPhoneData();
		final PhoneMasterRequestData phoneMasterData = source.getPhoneMasterListData().get(0);

		partyPhoneData.setDayFlag(convertToJAXBString("dayFlag", phoneMasterData.getDayFlag()));
		partyPhoneData.setCntryCd(convertToJAXBString("cntryCd", phoneMasterData.getCntryCd()));
		partyPhoneData.setEveningFlag(convertToJAXBString("eveningFlag", phoneMasterData.getEveningFlag()));
		partyPhoneData.setPhoneAreaCd(convertToJAXBString("phoneAreaCd", phoneMasterData.getPhoneAreaCd()));
		partyPhoneData.setPhoneCntryCd(convertToJAXBString("phoneCntryCd", phoneMasterData.getPhoneCntryCd()));
		partyPhoneData.setPhoneExtNum(convertToJAXBString("phoneExtNum", phoneMasterData.getPhoneExtNum()));
		partyPhoneData.setPhoneLocalNum(convertToJAXBString("phoneLocalNum", phoneMasterData.getPhoneLocalNum()));
		partyPhoneData.setSmsCapableFlag(convertToJAXBString("smsCapableFlag", phoneMasterData.getSmsCapableFlag()));
		partyPhoneData.setStatusCd(convertToJAXBString("statusCd", phoneMasterData.getStatusCd()));
		partyPhoneData.setPartyId(convertToJAXBString("partyId", phoneMasterData.getPartyId()));
		partyPhoneData.setContactPointName(convertToJAXBString("contactPointName", phoneMasterData.getContactPointName()));
		partyPhoneData.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", phoneMasterData.getContactPointTypeCd()));
		partyPhoneData.setContactId(convertToJAXBString("contactId", phoneMasterData.getContactId()));


		if (CollectionUtils.isNotEmpty(phoneMasterData.getUsageData()))
		{
			final List<UsageData> usageDataList = new ArrayList<UsageData>();
			for (final UsageRequestData UsageRequestData : phoneMasterData.getUsageData())
			{
				final UsageData usageData = new UsageData();
				usageData.setContactPointPurposeCd(UsageRequestData.getContactPointPurposeCd());
				usageData.setPrimaryFlag(UsageRequestData.getPrimaryFlag());

				usageDataList.add(usageData);
			}
			partyPhoneData.getUsageDataList().addAll(usageDataList);
		}
		target.setPartyPhoneData(partyPhoneData);
	}
}
