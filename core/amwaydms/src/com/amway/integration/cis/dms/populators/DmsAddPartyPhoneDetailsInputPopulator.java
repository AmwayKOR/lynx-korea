package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.dms.data.PartyPhoneDetailsRequestData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.AddPartyPhoneRequest;
import com.amway.integration.dms.services.PartyPhoneData;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link PartyPhoneDetailsRequestData} as source and {@link AddPartyPhoneRequest} as
 * target type.
 */
public class DmsAddPartyPhoneDetailsInputPopulator extends AbstractDmsPopulator
		implements Populator<PartyPhoneDetailsRequestData, AddPartyPhoneRequest>
{
	@Override
	public void populate(final PartyPhoneDetailsRequestData source, final AddPartyPhoneRequest target) throws ConversionException
	{
		final PartyPhoneData partyPhoneData = new PartyPhoneData();
		final PhoneMasterRequestData phoneMasterData = source.getPhoneMasterListData().get(0);

		partyPhoneData.setDayFlag( phoneMasterData.getDayFlag());
		partyPhoneData.setCntryCd( phoneMasterData.getCntryCd());
		partyPhoneData.setEveningFlag( phoneMasterData.getEveningFlag());
		partyPhoneData.setPhoneAreaCd( phoneMasterData.getPhoneAreaCd());
		partyPhoneData.setPhoneCntryCd( phoneMasterData.getPhoneCntryCd());
		partyPhoneData.setPhoneExtNum( phoneMasterData.getPhoneExtNum());
		partyPhoneData.setPhoneLocalNum( phoneMasterData.getPhoneLocalNum());
		partyPhoneData.setSmsCapableFlag( phoneMasterData.getSmsCapableFlag());
		partyPhoneData.setStatusCd( phoneMasterData.getStatusCd());
		partyPhoneData.setPartyId( phoneMasterData.getPartyId());
		partyPhoneData.setContactPointTypeCd( phoneMasterData.getContactPointTypeCd());
		partyPhoneData.setContactPointName( phoneMasterData.getContactPointName());
		if (CollectionUtils.isNotEmpty(phoneMasterData.getUsageData()))
		{
			for (final UsageRequestData UsageRequestData : phoneMasterData.getUsageData())
			{
				final UsageData usageData = new UsageData();
				usageData.setContactPointPurposeCd(UsageRequestData.getContactPointPurposeCd());
				usageData.setPrimaryFlag(UsageRequestData.getPrimaryFlag());
				partyPhoneData.getUsageDataList().add(usageData);
			}
		}
		target.setPartyPhoneDetail(partyPhoneData);
	}

}
