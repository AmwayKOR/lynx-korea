/**
 *
 */
package com.amway.integration.cis.los.account.detail.service.mock.impl;

import com.amway.core.los.data.LosAccountDetailResponseData;
import com.amway.core.los.data.LosAccountRequestData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.pojo.AboLosAward;
import com.amway.integration.cis.los.pojo.AboLosExtended;
import com.amway.integration.cis.los.pojo.DetailResponse;
import com.amway.integration.cis.los.pojo.LineOfSponsorshipBusinessDetail;
import com.amway.integration.cis.los.pojo.LosAccountRequest;
import com.amway.integration.cis.los.service.impl.AbstractLosService;


/**
 * Mock service for Los Account Detail
 */
public class MockLosAccountDetailService
		extends AbstractLosService<LosAccountDetailResponseData, LosAccountRequestData, DetailResponse>
		implements LosService<LosAccountRequestData, LosAccountDetailResponseData>
{
	private static final String RETRIEVE_DATE = "20160201";
	private static final String LOS_AWARD_QUALIFCATION_PERIOD = "201602";
	private static final String LOS_AWARD_CODE = "1";
	private static final String LOS_AWARD_RANK = "1";
	private static final String LOS_AWARD_NAME = "abc";
	private static final String ABO_NO = "7001002605";

	@Override
	public LosAccountDetailResponseData process(final LosAccountRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createResultObject()
	 */

	@Override
	protected LosAccountDetailResponseData createResultObject()
	{
		final LosAccountDetailResponseData accountDetailResponseData = new LosAccountDetailResponseData();
		accountDetailResponseData.setReturnCode(1);
		return accountDetailResponseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#createDefaultResult()
	 */
	@Override
	protected LosAccountDetailResponseData createDefaultResult()
	{
		final LosAccountDetailResponseData accountDetailResponseData = createResultObject();
		accountDetailResponseData.setReturnCode(-1);
		accountDetailResponseData.setReturnMessage("Failed to get los account details");

		return accountDetailResponseData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.los.service.impl.AbstractLosService#executeEvent(java.lang.Object)
	 */
	@Override
	protected DetailResponse executeEvent(final Object input)
	{
		final LosAccountRequest losAccountRequest = (LosAccountRequest) input;
		final DetailResponse response = new DetailResponse();
		final LineOfSponsorshipBusinessDetail lineOfSponsorshipBusinessDetail = new LineOfSponsorshipBusinessDetail();
		lineOfSponsorshipBusinessDetail.setABONo(new Long(ABO_NO));
		lineOfSponsorshipBusinessDetail.setAff(losAccountRequest.getAff());

		final AboLosAward aboLosAward = new AboLosAward();
		aboLosAward.setCode(LOS_AWARD_CODE);
		aboLosAward.setName(LOS_AWARD_NAME);
		aboLosAward.setQualifcationPeriod(LOS_AWARD_QUALIFCATION_PERIOD);
		aboLosAward.setRank(LOS_AWARD_RANK);
		lineOfSponsorshipBusinessDetail.setCurrentAward(aboLosAward);

		final AboLosExtended aboLosExtended = new AboLosExtended();
		lineOfSponsorshipBusinessDetail.setExtended(aboLosExtended);

		final AboLosAward losAward = new AboLosAward();
		lineOfSponsorshipBusinessDetail.setHighestAward(losAward);

		lineOfSponsorshipBusinessDetail.setRetrieveDate(RETRIEVE_DATE);

		response.getDetail().add(lineOfSponsorshipBusinessDetail);

		return response;
	}

}
