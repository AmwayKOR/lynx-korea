/**
 *
 */
package com.amway.integration.cis.dms.customerregistration.mock.impl;

import org.apache.commons.lang.math.RandomUtils;

import com.amway.core.dms.data.RegisterRequestData;
import com.amway.core.dms.data.RegistrationResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.register.services.RegistrationProcessDecision;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.CustomerRegistrationOutput;
import com.amway.integration.dms.services.NewABOPartyInfo;


/**
 * Mock Service for to process the cutomer registration.
 */
public class MockProcessRegistrationServiceImpl
		extends AbstractDmsService<RegistrationResultData, RegisterRequestData, CustomerRegistrationOutput>
		implements DmsService<RegisterRequestData, RegistrationResultData>
{

	@Override
	public RegistrationResultData process(final RegisterRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}


	private String generatePartyId()
	{
		return Long.toString(RandomUtils.nextLong());
	}


	private String generateABONumber()
	{
		return Long.toString(RandomUtils.nextLong());
	}

	@Override
	protected RegistrationResultData createDefaultResult()
	{
		final RegistrationResultData registrationResult = createResultObject();
		registrationResult.setDecision(RegistrationProcessDecision.UNKNOWN);
		registrationResult.setReturnMessage("registration.could.not.processed");
		return registrationResult;
	}

	@Override
	protected RegistrationResultData createResultObject()
	{
		return new RegistrationResultData();
	}


	@Override
	protected CustomerRegistrationOutput executeEvent(final Object input)
	{
		final CustomerRegistrationOutput registrationOutput = new CustomerRegistrationOutput();
		final NewABOPartyInfo newAboPartyInfo = new NewABOPartyInfo();

		registrationOutput.setModuleId(generateABONumber());
		newAboPartyInfo.setPartyId(generatePartyId());
		registrationOutput.getNewABOPartyList().add(newAboPartyInfo);
		registrationOutput.setReturnCd(1);
		registrationOutput.setReturnMessage("Success");

		return registrationOutput;
	}

}
