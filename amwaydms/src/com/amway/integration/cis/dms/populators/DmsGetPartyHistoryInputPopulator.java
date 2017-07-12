package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.integration.dms.services.PartyHistoryRequest;


/**
 * Populator implementation for {@link CommonRequestFieldsData} as source and {@link PartyHistoryRequest} as target
 * type.
 */
public class DmsGetPartyHistoryInputPopulator extends AbstractDmsPopulator
		implements Populator<CommonRequestFieldsData, PartyHistoryRequest>
{

	@Override
	public void populate(final CommonRequestFieldsData source, final PartyHistoryRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setPartyId(source.getPartyId());
	}

}
