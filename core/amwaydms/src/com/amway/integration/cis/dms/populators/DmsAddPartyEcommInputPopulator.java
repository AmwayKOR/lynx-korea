/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
import com.amway.core.dms.data.PartyEcommRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.AddPartyEcommRequest;
import com.amway.integration.dms.services.PartyEcommData;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link AddUpdatePartyEcommRequestData} as source and {@link AddPartyEcommRequest} as
 * target type.
 */
public class DmsAddPartyEcommInputPopulator extends AbstractDmsPopulator
		implements Populator<AddUpdatePartyEcommRequestData, AddPartyEcommRequest>
{

	@Override
	public void populate(final AddUpdatePartyEcommRequestData source, final AddPartyEcommRequest target) throws ConversionException
	{
		final PartyEcommData partyEcommData = new PartyEcommData();
		final List<PartyEcommRequestData> partyEcommRequestDataList = source.getPartyEcommData();
		for (final PartyEcommRequestData ecommData : partyEcommRequestDataList)
		{
			if (ecommData != null)
			{
				partyEcommData.setPartyId(convertToJAXBString("partyId", ecommData.getPartyId()));
				partyEcommData.setEcommAddr(convertToJAXBString("ecommAddr", ecommData.getEcommAddr()));
				partyEcommData.setEcommTypeCd(convertToJAXBString("ecommTypeCd", ecommData.getEcommTypeCd()));
				partyEcommData.setEcommName(convertToJAXBString("ecommName", ecommData.getEcommName()));
				partyEcommData.setEcommTypeName(convertToJAXBString("ecommTypeName", ecommData.getEcommTypeName()));
				partyEcommData.setStatusCd(convertToJAXBString("statusCd", ecommData.getStatusCd()));
				partyEcommData.setAllowForLogIn(convertToJAXBString("allowForLogin", ecommData.getAllowForLogIn()));
				partyEcommData.setContactId(convertToJAXBString("contactId", ecommData.getContactId()));
				partyEcommData.setContactPointName(convertToJAXBString("contactPointName", ecommData.getContactPointName()));
				partyEcommData.setContactPointTypeCd(convertToJAXBString("contactPointTypeCd", ecommData.getContactPointTypeCd()));

				final List<UsageData> usageDataList = new ArrayList<UsageData>();
				for (final UsageRequestData UsageRequestData : ecommData.getUsageData())
				{
					final UsageData usageData = new UsageData();
					usageData.setContactPointPurposeCd(UsageRequestData.getContactPointPurposeCd());
					usageData.setPrimaryFlag(UsageRequestData.getPrimaryFlag());
					usageDataList.add(usageData);
				}
				partyEcommData.getUsageDataList().addAll(usageDataList);
			}
		}
		target.setPartyEcommData(partyEcommData);
	}
}
