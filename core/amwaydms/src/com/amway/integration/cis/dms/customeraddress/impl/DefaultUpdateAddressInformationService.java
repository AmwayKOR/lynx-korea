package com.amway.integration.cis.dms.customeraddress.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AddUpdatePartyAddressRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;
import com.hybris.commons.client.RestResponse;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to update the address information.
 */
@Deprecated
public class DefaultUpdateAddressInformationService
		extends AbstractDmsService<CommonResponseFieldsData, AddUpdatePartyAddressRequestData, ReturnInfoService>
		implements DmsService<AddUpdatePartyAddressRequestData, CommonResponseFieldsData>
{

	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}

	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnMessage("invalid fields entry");
		commonResponseFieldsData.setReturnCd(-1);
		return commonResponseFieldsData;
	}

	@Override
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}
}
