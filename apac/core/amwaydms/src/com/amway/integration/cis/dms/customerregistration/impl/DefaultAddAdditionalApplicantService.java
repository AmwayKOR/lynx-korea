/**
 *
 */
package com.amway.integration.cis.dms.customerregistration.impl;

import com.amway.core.dms.data.RegisterRequestData;
import com.amway.core.dms.data.RegistrationResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.register.services.RegistrationProcessDecision;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AddPartyResponse;
import com.hybris.commons.client.RestResponse;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to add additional applicant.
 */
@Deprecated
public class DefaultAddAdditionalApplicantService
		extends AbstractDmsService<RegistrationResultData, RegisterRequestData, AddPartyResponse>
		implements DmsService<RegisterRequestData, RegistrationResultData>
{

	@Override
	protected AddPartyResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;

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
}
