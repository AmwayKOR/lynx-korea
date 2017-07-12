/**
 *
 */
package com.amway.integration.cis.dms.partyhistoryservice.mock.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PartyHistoryDataResponse;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.PartyHistoryData;
import com.amway.integration.dms.services.PartyHistoryResponse;
import com.hybris.cis.common.utils.StringUtils;


public class MockGetPartyHistoryService
		extends AbstractDmsService<PartyHistoryDataResponse, CommonRequestFieldsData, PartyHistoryResponse>
		implements DmsService<CommonRequestFieldsData, PartyHistoryDataResponse>
{

	@Override
	public PartyHistoryDataResponse process(final CommonRequestFieldsData requestData)
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
	protected PartyHistoryDataResponse createResultObject()
	{
		// YTODO Auto-generated method stub
		return new PartyHistoryDataResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected PartyHistoryDataResponse createDefaultResult()
	{
		final PartyHistoryDataResponse response = createResultObject();
		response.setReturnCd(-1);
		response.setReturnMessage("Fail to get party history");
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected PartyHistoryResponse executeEvent(final Object input)
	{
		final PartyHistoryResponse response = new PartyHistoryResponse();
		final PartyHistoryData historyData = new PartyHistoryData();
		historyData.setActionCd("Registration history");
		historyData.setCountryCd("BR");
		historyData.setDetail("Conversion");
		historyData.setModuleName("EBUF10B");
		historyData.setPartyId("171956");
		historyData.setProcessCd("Regular Registration");
		historyData.setProcessDate("2016-03-07T20:52:57-03:00");
		historyData.setProcessTypeCd("Membership");
		historyData.setReasonCd(StringUtils.EMPTY);
		historyData.setTransactionDate("2016-03-07T20:52:57-03:00");
		historyData.setTransactionSourceCd("HybrisCockpit");
		historyData.setUserId("bzlblv");
		response.getPartyHistoryData().add(historyData);
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
	}

}
