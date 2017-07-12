/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.integration.dms.services.GetPrefrenceListRequest;


/**
 * Populator implementation for {@link CommonRequestFieldsData} as source and {@link GetPrefrenceListRequest} as target
 * type.
 */
public class DmsGetPreferenceListInputPopulator extends AbstractDmsPopulator
		implements Populator<CommonRequestFieldsData, GetPrefrenceListRequest>
{
	@Override
	public void populate(final CommonRequestFieldsData source, final GetPrefrenceListRequest target) throws ConversionException
	{
		target.setSalesPlanAff(source.getSalesPlanAff());
	}
}
