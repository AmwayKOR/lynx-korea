/**
 *
 */
package com.amway.integration.cis.dms.sponsorsearch.services.mock;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.SponsorResponseData;
import com.amway.core.dms.data.SponsorSearchRequestData;
import com.amway.core.dms.data.SponsorSearchResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetSponsorListResponse;
import com.amway.integration.dms.services.Sponsor;


/**
 * Mock Service implementation to search sponsors based on zipcode
 */
public class MockSponsorSearchService
		extends AbstractDmsService<SponsorSearchResponseData, SponsorSearchRequestData, GetSponsorListResponse>
		implements DmsService<SponsorSearchRequestData, SponsorSearchResponseData>
{

	@Override
	public SponsorSearchResponseData process(final SponsorSearchRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
	 */
	@Override
	protected SponsorSearchResponseData createResultObject()
	{
		return new SponsorSearchResponseData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected SponsorSearchResponseData createDefaultResult()
	{
		final SponsorSearchResponseData sponsorSearchResponseData = createResultObject();
		sponsorSearchResponseData.setReturnCd(-1);
		final List<SponsorResponseData> sponsorListData = new ArrayList<SponsorResponseData>();
		final SponsorResponseData sponsorData = new SponsorResponseData();
		sponsorData.setNumber("41333");
		sponsorData.setCityName("SAO PAULO");
		sponsorData.setStateCode("SP");
		sponsorListData.add(sponsorData);

		return sponsorSearchResponseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected GetSponsorListResponse executeEvent(final Object input)
	{
		final GetSponsorListResponse response = new GetSponsorListResponse();
		final Sponsor sponsor = new Sponsor();
		sponsor.setSponsorAboNum("3109215040");
		sponsor.setCityName("SAO PAULO");
		sponsor.setStateCd("SP");
		sponsor.setSponsorName("Tom");

		response.getSponsorList().add(sponsor);
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
	}


}
