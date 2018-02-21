package com.amway.core.error.service;

import com.amway.core.error.dto.EnterpriseErrorMessage;
import com.amway.core.error.dto.EnterpriseErrorMessageContext;


public interface AmwayEnterpriseErrorMessageService
{

	EnterpriseErrorMessage getErrorMessageDetails(EnterpriseErrorMessageContext errorContext);

}
