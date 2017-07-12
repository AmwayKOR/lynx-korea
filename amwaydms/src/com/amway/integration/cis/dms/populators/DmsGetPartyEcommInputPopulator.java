/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.integration.dms.services.GetPartyEcommRequest;


/**
 * Populator implementation for {@link CommonRequestFieldsData} as source and {@link GetPartyEcommRequest} as target
 * type.
 */
public class DmsGetPartyEcommInputPopulator extends AbstractDmsPopulator
		implements Populator<CommonRequestFieldsData, GetPartyEcommRequest>
{
	@Override
	public void populate(final CommonRequestFieldsData source, final GetPartyEcommRequest target) throws ConversionException
	{
		target.setAboNum(source.getAboNum());
		target.setPartyId(source.getPartyId());
		target.setSalesPlanAff(source.getSalesPlanAff());
	}
}
