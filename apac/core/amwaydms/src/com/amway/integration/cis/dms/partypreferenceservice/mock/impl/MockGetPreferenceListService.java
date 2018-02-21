/**
 *
 */
package com.amway.integration.cis.dms.partypreferenceservice.mock.impl;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PreferenceListResponse;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.GetPreferenceResponse;
import com.amway.integration.dms.services.PrefMasterData;


/**
 * @author admin
 */
public class MockGetPreferenceListService
		extends AbstractDmsService<PreferenceListResponse, CommonRequestFieldsData, GetPreferenceResponse>
		implements DmsService<CommonRequestFieldsData, PreferenceListResponse>
{

	@Override
	public PreferenceListResponse process(final CommonRequestFieldsData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	@Override
	protected PreferenceListResponse createResultObject()
	{
		return new PreferenceListResponse();
	}

	@Override
	protected PreferenceListResponse createDefaultResult()
	{
		final PreferenceListResponse getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get preference list");
		return getBalanceResult;
	}

	@Override
	protected GetPreferenceResponse executeEvent(final Object input)
	{
		final GetPreferenceResponse response = new GetPreferenceResponse();

		final PrefMasterData prefMasterData = new PrefMasterData();
		prefMasterData.setPreferenceId("Confidentiality");
		prefMasterData.setChoiceListFlg("1");
		prefMasterData.setIsTrueFlg("0");
		prefMasterData.setPreferenceDesc("Confidentiality");
		prefMasterData.setPreferenceCatagoryTypeId("Food");
		prefMasterData.setPreferenceNote("1");

		response.getPrefMasterData().add(prefMasterData);

		response.setReturnCd(1);
		response.setReturnMessage("Success");

		return response;
	}

}
