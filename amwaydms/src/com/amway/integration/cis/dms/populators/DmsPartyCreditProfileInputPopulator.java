/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.integration.dms.services.CreditProfileRequest;


/**
 * Populator implementation for {@link CommonRequestFieldsData} as source and {@link CreditProfileRequest} as target
 * type.
 */
public class DmsPartyCreditProfileInputPopulator extends AbstractDmsPopulator
		implements Populator<CommonRequestFieldsData, CreditProfileRequest>
{
	@Override
	public void populate(final CommonRequestFieldsData source, final CreditProfileRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
		target.setPartyId(source.getPartyId());
	}

}
