package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.dms.data.CreditProfileData;
import com.amway.core.dms.data.CreditProfileResponseData;
import com.amway.integration.dms.services.CreditProfileResponse;
import com.amway.integration.dms.services.PartyCreditPofileObject;


/**
 * Populator implementation for {@link CreditProfileResponse} as source and {@link CreditProfileResponseData} as target
 * type.
 */
public class DmsPartyCreditProfileOutputPopulator extends AbstractDmsPopulator
		implements Populator<CreditProfileResponse, CreditProfileResponseData>
{

	@Override
	public void populate(final CreditProfileResponse source, final CreditProfileResponseData target) throws ConversionException
	{

		if (CollectionUtils.isNotEmpty(source.getPartyCreditProfileObjList()))
		{
			target.setCreditProfileList(populatePartyCreditProfileData(source.getPartyCreditProfileObjList()));
		}

	}

	/**
	 * To populate party credit profile data.
	 *
	 * @param partyCreditProfileList
	 * @return List<CreditProfileData>
	 */
	public List<CreditProfileData> populatePartyCreditProfileData(final List<PartyCreditPofileObject> partyCreditProfileList)
	{
		final List<CreditProfileData> partyCreditProfileDataList = new ArrayList<CreditProfileData>();

		for (final PartyCreditPofileObject partyCreditProfile : partyCreditProfileList)
		{
			final CreditProfileData creditProfileData = new CreditProfileData();

			creditProfileData.setCntryCd(partyCreditProfile.getCntryCd());
			creditProfileData.setCreditScore(partyCreditProfile.getCreditScore());
			creditProfileData.setCreditStatusCd(partyCreditProfile.getCreditStatusCd());
			creditProfileData.setFicoScore(partyCreditProfile.getFicoScore());
			creditProfileData.setPartyId(partyCreditProfile.getPartyId());
			partyCreditProfileDataList.add(creditProfileData);
		}
		return partyCreditProfileDataList;

	}
}
