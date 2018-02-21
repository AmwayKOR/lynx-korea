package com.amway.core.error.service.impl;

import com.amway.core.error.dto.EnterpriseErrorMessage;
import com.amway.core.error.dto.EnterpriseErrorMessageContext;
import com.amway.core.error.service.AmwayEnterpriseErrorMessageService;


public class DefaultAmwayEnterpriseErrorMessageService implements AmwayEnterpriseErrorMessageService
{

	@Override
	public EnterpriseErrorMessage getErrorMessageDetails(final EnterpriseErrorMessageContext errorContext)
	{
		throw new UnsupportedOperationException(
				"Default implementation which will be updated once Enterprise Error Message will be introduced.");
	}

}
