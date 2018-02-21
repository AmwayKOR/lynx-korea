/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Populator implementation for {@link ReturnInfoService} as source and {@link CommonResponseFieldsData} as target type.
 */
public class DmsSaveAddressOutputPopulator implements Populator<ReturnInfoService, CommonResponseFieldsData>
{


	@Override
	public void populate(final ReturnInfoService source, final CommonResponseFieldsData target) throws ConversionException
	{


		target.setReturnMessage(source.getReturnMessage());
		target.setReturnCd(source.getReturnCd());

	}

}
