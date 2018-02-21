package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.integration.dms.services.AccountHistoryRequest;


/**
 * Populator implementation for {@link CommonRequestFieldsData} as source and {@link AccountHistoryRequest} as target
 * type.
 */
public class DmsGetAccountHistoryInputPopulator extends AbstractDmsPopulator
		implements Populator<CommonRequestFieldsData, AccountHistoryRequest>
{

	@Override
	public void populate(final CommonRequestFieldsData source, final AccountHistoryRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
	}

}
