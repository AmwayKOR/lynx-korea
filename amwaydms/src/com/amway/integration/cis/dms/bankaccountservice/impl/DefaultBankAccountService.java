/**
 *
 */
package com.amway.integration.cis.dms.bankaccountservice.impl;

import com.amway.core.dms.data.BankAccountDetails;
import com.amway.core.dms.data.BankAccountRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.BankAccountDetailResponse;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/** @deprecated  To reduce dependency on any particular version of DMS, it was decided to limit direct
 * hybris-to-DMS communication to the minimum that is absolutely required to support order capture flow.
 * More information:  https://jira.amway.com:8444/display/HC/amwaydms
 *
 * Service to get bank account information
 */
@Deprecated
public class DefaultBankAccountService
		extends AbstractDmsService<BankAccountDetails, BankAccountRequestData, BankAccountDetailResponse>
		implements DmsService<BankAccountRequestData, BankAccountDetails>
{
	private static final Logger LOG = Logger.getLogger(DefaultBankAccountService.class);

	@Override
	protected BankAccountDetails createResultObject()
	{
		return new BankAccountDetails();
	}

	@Override
	protected BankAccountDetails createDefaultResult()
	{

		final BankAccountDetails bankAccountDetailsData = createResultObject();
		bankAccountDetailsData.setReturnCd(-1);
		bankAccountDetailsData.setReturnMessage("Failed to get Bank Account information");
		return bankAccountDetailsData;

	}


	@Override
	protected BankAccountDetailResponse executeEvent(final Object input)
	{
		LOG.info("Calling webservice BankAccountService/getBankAccount.....");
		final RestResponse<BankAccountDetailResponse> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(),
						 input,
						BankAccountDetailResponse.class);
		Assert.notNull(dmsResultRestResponse, "Failed to get Bank Account information");
		return dmsResultRestResponse.getResult();
	}

}
