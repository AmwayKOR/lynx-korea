/**
 *
 */
package com.amway.integration.cis.dms.customerregistration.mock.impl;

import com.amway.core.dms.data.RegisterRequestData;
import com.amway.core.dms.data.RegistrationResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.register.services.RegistrationProcessDecision;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AddPartyResponse;


/**
 * Mock Service for to add additional applicant.
 */
public class MockAddAdditionalApplicantServiceImpl
		extends AbstractDmsService<RegistrationResultData, RegisterRequestData, AddPartyResponse>
		implements DmsService<RegisterRequestData, RegistrationResultData>
{

	@Override
	public RegistrationResultData process(final RegisterRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	@Override
	protected RegistrationResultData createDefaultResult()
	{
		final RegistrationResultData registrationResult = createResultObject();
		registrationResult.setDecision(RegistrationProcessDecision.UNKNOWN);
		return registrationResult;
	}

	@Override
	protected RegistrationResultData createResultObject()
	{
		return new RegistrationResultData();
	}

	@Override
	protected AddPartyResponse executeEvent(final Object input)
	{
		final AddPartyResponse response = new AddPartyResponse();
		response.setPartyId("151058196");
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
	}

}
