/**
 *
 */
package com.amway.integration.cis.dms.sponsorsearch.services.impl;

import com.amway.core.dms.data.SponsorSearchRequestData;
import com.amway.core.dms.data.SponsorSearchResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetSponsorListResponse;
import com.hybris.commons.client.RestResponse;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service to search sponsors based on zipcode
 */
@Deprecated
public class DefaultSponsorSearchService
		extends AbstractDmsService<SponsorSearchResponseData, SponsorSearchRequestData, GetSponsorListResponse>
		implements DmsService<SponsorSearchRequestData, SponsorSearchResponseData>
{

	@Override
	protected SponsorSearchResponseData createResultObject()
	{
		return new SponsorSearchResponseData();
	}

	@Override
	protected SponsorSearchResponseData createDefaultResult()
	{
		final SponsorSearchResponseData sponsorSearchResponseData = createResultObject();
		sponsorSearchResponseData.setReturnCd(-1);
		sponsorSearchResponseData.setReturnMessage("Failed to get Sponsors for Zip code");
		return sponsorSearchResponseData;
	}

	@Override
	protected GetSponsorListResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}


}
