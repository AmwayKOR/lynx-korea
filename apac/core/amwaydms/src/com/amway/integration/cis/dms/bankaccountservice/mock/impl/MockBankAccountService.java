package com.amway.integration.cis.dms.bankaccountservice.mock.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.BankAccountDetails;
import com.amway.core.dms.data.BankAccountRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AccountDetailOutput;
import com.amway.integration.dms.services.AccountUseDetailXrefInput;
import com.amway.integration.dms.services.BankAccountDetailResponse;


/**
 * Mock service for bank account details.
 */
public class MockBankAccountService
		extends AbstractDmsService<BankAccountDetails, BankAccountRequestData, BankAccountDetailResponse>
		implements DmsService<BankAccountRequestData, BankAccountDetails>
{

	@Override
	public BankAccountDetails process(final BankAccountRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createResultObject()
	 */
	@Override
	protected BankAccountDetails createResultObject()
	{
		return new BankAccountDetails();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected BankAccountDetails createDefaultResult()
	{
		final BankAccountDetails bankAccountDetailsData = createResultObject();
		bankAccountDetailsData.setReturnCd(-1);
		bankAccountDetailsData.setReturnMessage("Failed to get Bank Account information");
		return bankAccountDetailsData;
	}

	/*
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object) >>>>>>> AT-4436
	 */
	@Override
	protected BankAccountDetailResponse executeEvent(final Object input)
	{
		final BankAccountDetailResponse response = new BankAccountDetailResponse();
		final AccountDetailOutput detailOutput = new AccountDetailOutput();


		detailOutput.setIssuingBankId(convertToJAXBString("issuingBankName", "Caixa Economica Federal "));
		detailOutput.setEffectiveEndDate(convertToJAXBString("effectiveEndDate", "2016-01-12T00:00:00-02:00"));
		detailOutput.setBankAcctHolderName(convertToJAXBString("bankAcctHolderName", "SANTANA GEAZI RODRIQUES "));
		detailOutput.setBankBranchCode(convertToJAXBString("bankBranchCode", "4151"));
		detailOutput.setPrimaryRoutingNum(convertToJAXBString("primaryRoutingNum", "0"));
		detailOutput.setBankAcctNum(convertToJAXBString("bankAcctNum", "0000209249"));
		detailOutput.setEffectiveStartDate(convertToJAXBString("effectiveStartDate", "2016-01-12T00:00:00-02:00"));
		detailOutput.setBankAcctTypeCd(convertToJAXBString("bankAcctTypeCd", "Checking"));
		final AccountUseDetailXrefInput accountUseDetail = new AccountUseDetailXrefInput();
		accountUseDetail.setAcctUseCode(convertToJAXBString("acctUseCode", "1"));
		accountUseDetail.setUseFlag(convertToJAXBString("useFlag", "useFlag"));
		detailOutput.getAcctUsageList().add(accountUseDetail);
		response.getBankAccountList().add(detailOutput);
		response.setReturnCd(1);

		return response;
	}


	protected String convertToJAXBString(final String elementName, final String elementValue)
	{
		return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;
	}

}
