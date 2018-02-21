/**
 *
 */
package com.amway.integration.cis.dms.customerregistration.impl;

import de.hybris.platform.commercefacades.user.data.RegisterData;

import org.springframework.util.Assert;

import com.amway.core.dms.data.RegistrationResultData;
import com.amway.core.dms.service.DmsService;
import com.amway.core.register.services.RegistrationProcessDecision;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.CustomerRegistrationOutput;
import com.hybris.commons.client.RestResponse;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service for to process the customer registration.
 */
@Deprecated
public class DefaultProcessCustomerRegistrationService
		extends AbstractDmsService<RegistrationResultData, RegisterData, CustomerRegistrationOutput>
		implements DmsService<RegisterData, RegistrationResultData>
{

	@Override
	protected CustomerRegistrationOutput executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;

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
}
