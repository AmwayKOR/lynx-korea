package com.amway.integration.cis.dms.taxid.services.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.TaxIdResponse;
import com.amway.integration.dms.services.UpdateTaxIdRequest;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service to update the party tax id details.
 */
@Deprecated
public class DefaultUpdatePartyTaxIdService
		extends AbstractDmsService<CommonResponseFieldsData, UpdateTaxIdRequest, TaxIdResponse>
		implements DmsService<UpdateTaxIdRequest, CommonResponseFieldsData>
{

	private static final Logger LOG = Logger.getLogger(DefaultUpdatePartyTaxIdService.class);

	@Override
	protected TaxIdResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("Failed to update party tax id details");
		commonResponseFieldsData.setReturnCd(-1);

		return commonResponseFieldsData;
	}


}
