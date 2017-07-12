package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.EcommMasterData;
import com.amway.core.dms.data.PartyEcommDetailsResponseData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.GetPartyEcommResponse;
import com.amway.integration.dms.services.PartyEcommData;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link GetPartyEcommResponse} as source and {@link PartyEcommDetailsResponseData} as
 * target type.
 */
public class DmsGetPartyEcommOutPopulator extends AbstractDmsPopulator
		implements Populator<GetPartyEcommResponse, PartyEcommDetailsResponseData>
{
	@Override
	public void populate(final GetPartyEcommResponse source, final PartyEcommDetailsResponseData target) throws ConversionException
	{
		final List<EcommMasterData> ecommMasterDataList = new ArrayList();
		for (final PartyEcommData partyEcommData : source.getPartyEcommDataList())
		{
			final EcommMasterData ecommMasterData = new EcommMasterData();
			ecommMasterData.setEcommPartyId((partyEcommData.getPartyId()));
			ecommMasterData.setEcommContactPointTypeCd((partyEcommData.getContactPointTypeCd()));
			ecommMasterData.setEcommContactPointName((partyEcommData.getContactPointName()));
			ecommMasterData.setEcommAddr((partyEcommData.getEcommAddr()));
			ecommMasterData.setEcommTypeCd((partyEcommData.getEcommTypeCd()));
			ecommMasterData.setStatusCd((partyEcommData.getStatusCd()));
			ecommMasterData.setContactPointTypeCd((partyEcommData.getContactPointTypeCd()));
			ecommMasterData.setContactPointName((partyEcommData.getContactPointName()));
			ecommMasterData.setPartyId((partyEcommData.getPartyId()));

			final List<UsageRequestData> usageDataList = new ArrayList<UsageRequestData>();
			for (final UsageData usageData : partyEcommData.getUsageDataList())
			{
				final UsageRequestData usageRequestData = new UsageRequestData();
				usageRequestData.setContactPointPurposeCd(usageData.getContactPointPurposeCd());
				usageRequestData.setPrimaryFlag(usageData.getPrimaryFlag());

				usageDataList.add(usageRequestData);
			}
			ecommMasterData.setUsageData(usageDataList);
			ecommMasterDataList.add(ecommMasterData);
		}
		target.setEcommMasterListData(ecommMasterDataList);
	}
}
