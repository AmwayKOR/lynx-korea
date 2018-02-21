package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.integration.dms.services.GetPartyPrefrenceListRequest;


/**
 * Populator implementation for {@link CommonRequestFieldsData} as source and {@link GetPartyPrefrenceListRequest} as
 * target type.
 */
public class DmsGetPartyPreferenceInputPopulator extends AbstractDmsPopulator
		implements Populator<CommonRequestFieldsData, GetPartyPrefrenceListRequest>
{
	@Override
	public void populate(final CommonRequestFieldsData source, final GetPartyPrefrenceListRequest target)
			throws ConversionException
	{
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setPartyId(source.getPartyId());
		target.setAboNum(source.getAboNum());
	}

}
