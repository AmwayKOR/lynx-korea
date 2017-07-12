/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.DeletePersonalIdDetailsData;
import com.amway.integration.dms.services.DeletePersonalIdRequest;


/**
 * Populator implementation for {@link DeletePersonalIdDetailsData} as source and {@link DeletePersonalIdRequest} as
 * target type.
 */
public class DmsDeletePartyPersonalIdInputPopulator extends AbstractDmsPopulator
		implements Populator<DeletePersonalIdDetailsData, DeletePersonalIdRequest>
{
	@Override
	public void populate(final DeletePersonalIdDetailsData source, final DeletePersonalIdRequest target) throws ConversionException
	{
		target.setPartyId(source.getPartyId());
		target.setPersonalIdTypeCd(source.getPersonalIdTypeCd());
		target.setAboNum(source.getAboNum());
		target.setSalesPlanAff(source.getSalesPlanAff());
	}

}
