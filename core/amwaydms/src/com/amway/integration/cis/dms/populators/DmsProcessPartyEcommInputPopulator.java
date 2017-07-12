package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
import com.amway.core.dms.data.PartyEcommRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.dms.services.EcommData;
import com.amway.integration.dms.services.ProcessPartyEcommRequest;


/**
 * Populator implementation for {@link AddUpdatePartyEcommRequestData} as source and {@link ProcessPartyEcommRequest} as
 * target type.
 */
public class DmsProcessPartyEcommInputPopulator implements Populator<AddUpdatePartyEcommRequestData, ProcessPartyEcommRequest>
{
	@Override
	public void populate(final AddUpdatePartyEcommRequestData source, final ProcessPartyEcommRequest target)
			throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setPartyId(source.getPartyId());
		target.setSalesPlanAff(source.getSalesPlanAff());

		for (final PartyEcommRequestData ecommRequestData : source.getPartyEcommData())
		{
			final EcommData ecommData = new EcommData();
			ecommData.setContactPointName(ecommRequestData.getContactPointName());
			ecommData.setContactPointTypeCd(ecommRequestData.getContactPointTypeCd());
			ecommData.setEcommAddr(ecommRequestData.getEcommAddr());

			for (final UsageRequestData usageData : ecommRequestData.getUsageData())
			{
				ecommData.setPrimaryFlag(usageData.getPrimaryFlag());
			}
			target.getEcommDataList().add(ecommData);
		}
	}
}
