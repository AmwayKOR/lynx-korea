/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.integration.dms.services.ReturnInfoService;
import com.amway.core.data.CommonResponseFieldsData;


/**
 * Populator to populate common response fields for any Magic service
 */
public class AmwayCommonResponseFeildsPopulator implements Populator<ReturnInfoService, CommonResponseFieldsData>
{

	@Override
	public void populate(final ReturnInfoService source, final CommonResponseFieldsData target) throws ConversionException
	{
		target.setReturnCd(source.getReturnCd());
		target.setReturnMessage(source.getReturnMessage());
	}

}
