/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
import com.amway.core.dms.data.PartyEcommRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.PartyEcommData;
import com.amway.integration.dms.services.UpdatePartyEcommRequest;
import com.amway.integration.dms.services.UsageData;


/**
 * Populator implementation for {@link AddUpdatePartyEcommRequestData} as source and {@link UpdatePartyEcommRequest} as
 * target type.
 */
public class DmsUpdatePartyEcommInputPopulator implements Populator<AddUpdatePartyEcommRequestData, UpdatePartyEcommRequest>
{
	@Override
	public void populate(final AddUpdatePartyEcommRequestData source, final UpdatePartyEcommRequest target)
			throws ConversionException
	{
		final PartyEcommData partyEcommData = new PartyEcommData();
		final List<PartyEcommRequestData> partyEcommRequestDataList = source.getPartyEcommData();
		for (final PartyEcommRequestData ecommData : partyEcommRequestDataList)
		{
			if (ecommData != null)
			{
				partyEcommData.setPartyId(createInputJaxBElement("partyId", ecommData.getPartyId()));
				partyEcommData.setEcommAddr(createInputJaxBElement("ecommAddr", ecommData.getEcommAddr()));
				partyEcommData.setEcommTypeCd(createInputJaxBElement("ecommTypeCd", ecommData.getEcommTypeCd()));
				partyEcommData.setStatusCd(createInputJaxBElement("statusCd", ecommData.getStatusCd()));
				partyEcommData.setContactPointName(createInputJaxBElement("contactPointName", ecommData.getContactPointName()));
				partyEcommData.setContactPointTypeCd(createInputJaxBElement("contactPointTypeCd", ecommData.getContactPointTypeCd()));
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

	private String createInputJaxBElement(final String elementName, final String elementValue)
	{
		return elementValue;
	}

}
