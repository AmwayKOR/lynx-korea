package com.amway.integration.cis.dms.customerregistration.mock.impl;

import com.amway.core.dms.data.PrePrintedNumberRequestData;
import com.amway.core.dms.data.PrePrintedNumberResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ValidatePrePrntdNmbrResponse;


/**
 * Mock Service for to validate the pre printed number.
 */
public class MockValidatePrePrintedNumberService
		extends AbstractDmsService<PrePrintedNumberResultData, PrePrintedNumberRequestData, ValidatePrePrntdNmbrResponse>
		implements DmsService<PrePrintedNumberRequestData, PrePrintedNumberResultData>
{
	@Override
	public PrePrintedNumberResultData process(final PrePrintedNumberRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
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

	@Override
	protected ValidatePrePrntdNmbrResponse executeEvent(final Object input)
	{
		final ValidatePrePrntdNmbrResponse response = new ValidatePrePrntdNmbrResponse();
		response.setPrePrintedNum("1234567");
		response.setServiceItemId("SRV0101");
		response.setReturnCd(1);
		response.setReturnMessage("success");

		return response;
	}

}
