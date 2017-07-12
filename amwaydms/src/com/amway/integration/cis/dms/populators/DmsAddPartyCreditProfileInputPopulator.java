package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.CreditProfileData;
import com.amway.integration.dms.services.AddPartyCreditProfileRequest;
import com.amway.integration.dms.services.PartyCreditPofileObject;


/**
 * Populator implementation for {@link CreditProfileData} as source and {@link AddPartyCreditProfileRequest} as target
 * type.
 */
public class DmsAddPartyCreditProfileInputPopulator extends AbstractDmsPopulator
		implements Populator<CreditProfileData, AddPartyCreditProfileRequest>
{
	@Override
	public void populate(final CreditProfileData source, final AddPartyCreditProfileRequest target) throws ConversionException
	{
		final List<PartyCreditPofileObject> partyCreditProfileDataList = new ArrayList<>();
		final PartyCreditPofileObject partyCreditProfile = new PartyCreditPofileObject();
		partyCreditProfile.setPartyId(source.getPartyId());
		partyCreditProfile.setCntryCd(source.getCntryCd());
		partyCreditProfile.setCreditScore(source.getCreditScore());
		partyCreditProfile.setCreditStatusCd(source.getCreditStatusCd());
		partyCreditProfile.setFicoScore(source.getFicoScore());
		partyCreditProfileDataList.add(partyCreditProfile);

		target.setPartyCreditProfileList(partyCreditProfileDataList);
	}
}
