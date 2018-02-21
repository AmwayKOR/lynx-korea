package com.amway.integration.cis.dms.accounthistoryservice.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.AccountHistoryDataResponse;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AccountHistoryResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/* Service to get account history details.
 */

/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 */
@Deprecated
public class DefaultGetAccountHistoryService
		extends AbstractDmsService<AccountHistoryDataResponse, CommonRequestFieldsData, AccountHistoryResponse>
		implements DmsService<CommonRequestFieldsData, AccountHistoryDataResponse>
{
	private static final Logger LOG = Logger.getLogger(DefaultGetAccountHistoryService.class);

	@Override
	protected AccountHistoryDataResponse createResultObject()
	{
		return new AccountHistoryDataResponse();
	}

	@Override
	protected AccountHistoryDataResponse createDefaultResult()
	{
		final AccountHistoryDataResponse getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get account history details");
		return getBalanceResult;
	}

	@Override
	protected AccountHistoryResponse executeEvent(final Object input)
	{
		//see Deprecated comment above
		return null;
	}

}
