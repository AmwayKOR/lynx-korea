package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.amway.core.dms.data.CreditProfileData;
import com.amway.integration.dms.services.PartyCreditPofileObject;
import com.amway.integration.dms.services.UpdatePartyCreditProfileRequest;


/**
 * Populator implementation for {@link CreditProfileData} as source and {@link UpdatePartyCreditProfileRequest} as
 * target type.
 */
public class DmsUpdatePartyCreditProfileInputPopulator extends AbstractDmsPopulator
		implements Populator<CreditProfileData, UpdatePartyCreditProfileRequest>
{
	@Override
	public void populate(final CreditProfileData source, final UpdatePartyCreditProfileRequest target) throws ConversionException
	{
		final PartyCreditPofileObject partyCreditProfile = new PartyCreditPofileObject();
		partyCreditProfile.setPartyId(source.getPartyId());
		partyCreditProfile.setCntryCd(source.getCntryCd());
		partyCreditProfile.setCreditScore(source.getCreditScore());
		partyCreditProfile.setCreditStatusCd(source.getCreditStatusCd());
		partyCreditProfile.setFicoScore(source.getFicoScore());

		target.setPartyCreditProfile(partyCreditProfile);
	}
}
