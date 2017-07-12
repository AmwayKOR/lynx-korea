/**
 *
 */
package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.SponsorResponseData;
import com.amway.core.dms.data.SponsorSearchResponseData;
import com.amway.integration.dms.services.GetSponsorListResponse;
import com.amway.integration.dms.services.Sponsor;


/**
 * Populator implementation for {@link GetSponsorListResponse} as source and {@link SponsorSearchResponseData} as target
 * type
 */
public class DmsSponsorSearchOutputPopulator extends AbstractDmsPopulator
		implements Populator<GetSponsorListResponse, SponsorSearchResponseData>
{

	@Override
	public void populate(final GetSponsorListResponse source, final SponsorSearchResponseData target) throws ConversionException
	{
		target.setReturnCd(source.getReturnCd());
		target.setReturnMessage(source.getReturnMessage());
		final List<Sponsor> sponsorList = source.getSponsorList();
		final List<SponsorResponseData> sponsorListData = new ArrayList<SponsorResponseData>();
		for (final Sponsor sponsor : sponsorList)
		{
			final SponsorResponseData sponsorData = new SponsorResponseData();
			sponsorData.setNumber(sponsor.getSponsorAboNum());
			sponsorData.setCityName(sponsor.getCityName());
			sponsorData.setStateCode(sponsor.getStateCd());
			sponsorData.setSponsorName(sponsor.getSponsorName());
			sponsorListData.add(sponsorData);
		}
		target.setSponsorList(sponsorListData);
	}
}
