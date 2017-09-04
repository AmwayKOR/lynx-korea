package com.amway.integration.cis.dms.taxid.services.impl;

import com.amway.core.data.TaxDetailsData;
import com.amway.core.dms.data.TaxIdDetailsResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPartyTaxIdDetailsResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service implementation for to get the party tax id details from magic.
 */
@Deprecated
public class DefaultGetPartyTaxIdService
		extends AbstractDmsService<TaxIdDetailsResponseData, TaxDetailsData, GetPartyTaxIdDetailsResponse>
		implements DmsService<TaxDetailsData, TaxIdDetailsResponseData>
{
	private static final Logger LOG = Logger.getLogger(DefaultGetPartyTaxIdService.class);

	@Override
	protected GetPartyTaxIdDetailsResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}


	@Override
	protected TaxIdDetailsResponseData createResultObject()
	{
		return new TaxIdDetailsResponseData();
	}

	@Override
	protected TaxIdDetailsResponseData createDefaultResult()
	{
		final TaxIdDetailsResponseData getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get tax details details");
		return getBalanceResult;
	}


}
