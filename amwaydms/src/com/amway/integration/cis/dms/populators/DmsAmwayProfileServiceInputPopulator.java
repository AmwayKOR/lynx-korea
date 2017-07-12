/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.AmwayProfileRequestData;
import com.amway.integration.dms.services.AmwayProfileInput;


/**
 * Populator implementation for {@link AmwayProfileRequestData} as source and {@link AmwayProfileInput} as target type.
 */
public class DmsAmwayProfileServiceInputPopulator extends AbstractDmsPopulator implements
		Populator<AmwayProfileRequestData, AmwayProfileInput>
{


	@Override
	public void populate(final AmwayProfileRequestData source, final AmwayProfileInput target) throws ConversionException
	{
		if (source != null)
		{
			target.setAboNum(source.getAboNum());
			target.setSalesPlanAff(source.getSalesPlanAff());
			target.setDetailLevelCd(source.getDeltailLevelCd());
		}
	}
}
