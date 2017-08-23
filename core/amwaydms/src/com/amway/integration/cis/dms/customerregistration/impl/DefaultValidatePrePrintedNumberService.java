package com.amway.integration.cis.dms.customerregistration.impl;

import com.amway.core.dms.data.PrePrintedNumberRequestData;
import com.amway.core.dms.data.PrePrintedNumberResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ValidatePrePrntdNmbrResponse;
import com.hybris.commons.client.RestResponse;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to validate the pre printed number.
 */
@Deprecated
public class DefaultValidatePrePrintedNumberService
		extends AbstractDmsService<PrePrintedNumberResultData, PrePrintedNumberRequestData, ValidatePrePrntdNmbrResponse>
		implements DmsService<PrePrintedNumberRequestData, PrePrintedNumberResultData>
{

	@Override
	protected ValidatePrePrntdNmbrResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}

	@Override
	protected PrePrintedNumberResultData createDefaultResult()
	{
		final PrePrintedNumberResultData prePrintedNumberResultData = createResultObject();
		prePrintedNumberResultData.setReturnMessage("validation.kitnumber.invalid");
		prePrintedNumberResultData.setReturnCd(-1);
		return prePrintedNumberResultData;
	}

	@Override
	protected PrePrintedNumberResultData createResultObject()
	{
		return new PrePrintedNumberResultData();
	}
}
