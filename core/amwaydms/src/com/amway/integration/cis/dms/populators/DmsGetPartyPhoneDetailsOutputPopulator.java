package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.PartyPhoneDetailsResponseData;
import com.amway.core.dms.data.PhoneMasterRequestData;
import com.amway.integration.dms.services.GetPartyPhoneResponse;
import com.amway.integration.dms.services.PartyPhoneData;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link GetPartyPhoneResponse} as source and {@link PartyPhoneDetailsResponseData} as
 * target type.
 */
public class DmsGetPartyPhoneDetailsOutputPopulator extends AbstractDmsPopulator
		implements Populator<GetPartyPhoneResponse, PartyPhoneDetailsResponseData>
{
	@Override
	public void populate(final GetPartyPhoneResponse source, final PartyPhoneDetailsResponseData target) throws ConversionException
	{
		final List<PhoneMasterRequestData> phoneMasterDataList = new ArrayList<PhoneMasterRequestData>();

		for (final PartyPhoneData partyPhoneData : source.getPartyPhoneList())
		{
			final PhoneMasterRequestData phoneMasterData = new PhoneMasterRequestData();
			phoneMasterData.setPartyId((partyPhoneData.getPartyId()));
			phoneMasterData.setContactPointName((partyPhoneData.getContactPointName()));
			phoneMasterData.setPhoneCntryCd((partyPhoneData.getPhoneCntryCd()));
			phoneMasterData.setPhoneAreaCd((partyPhoneData.getPhoneAreaCd()));
			phoneMasterData.setSmsCapableFlag((partyPhoneData.getSmsCapableFlag()));
			phoneMasterData.setCntryCd((partyPhoneData.getCntryCd()));
			phoneMasterData.setStatusCd((partyPhoneData.getStatusCd()));
			phoneMasterData.setPhoneLocalNum((partyPhoneData.getPhoneLocalNum()));
			phoneMasterData.setContactPointTypeCd((partyPhoneData.getContactPointTypeCd()));
			phoneMasterData.setDayFlag((partyPhoneData.getDayFlag()));
			phoneMasterData.setEveningFlag((partyPhoneData.getEveningFlag()));
			for (final UsageData usageData : partyPhoneData.getUsageDataList())
			{
				if (usageData.getContactPointPurposeCd().equalsIgnoreCase("Registration") && usageData.getPrimaryFlag()
						.equalsIgnoreCase("Y"))
				{
					phoneMasterData.setRegistration(true);
				}
				else if (usageData.getContactPointPurposeCd().equalsIgnoreCase("GeneralPurpose") && usageData.getPrimaryFlag()
						.equalsIgnoreCase("Y"))
				{
					phoneMasterData.setGeneralPurpose(true);
				}
			}
			phoneMasterDataList.add(phoneMasterData);
		}
		target.setPhoneMasterListData(phoneMasterDataList);
	}

}
