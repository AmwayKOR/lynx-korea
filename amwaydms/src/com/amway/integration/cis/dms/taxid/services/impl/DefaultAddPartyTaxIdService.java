package com.amway.integration.cis.dms.taxid.services.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.data.TaxDetailsData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.TaxIdResponse;
import com.hybris.commons.client.RestResponse;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service implementation for to add the party tax Id details.
 */
@Deprecated
public class DefaultAddPartyTaxIdService extends AbstractDmsService<CommonResponseFieldsData, TaxDetailsData, TaxIdResponse>
		implements DmsService<TaxDetailsData, CommonResponseFieldsData>
{

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData addTaxIdResult = createResultObject();
		addTaxIdResult.setReturnCd(-1);
		addTaxIdResult.setReturnMessage("Failed to add tax details");
		return addTaxIdResult;
	}

	@Override
	protected TaxIdResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}
}
