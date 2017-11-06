package com.amway.apac.core.account.service.impl;

import com.amway.core.account.service.impl.MockAmwayProfileService;
import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;


/**
 * Overriding {@link MockAmwayProfileService} to set business nature.
 *
 * @author Shubham Goyal
 */
public class AmwayApacMockProfileService extends MockAmwayProfileService
{

	@Override
	public AmwayProfileResponseData process(final AmwayProfileRequestData requestData)
	{
		final AmwayProfileResponseData amwayProfileResponseData = super.process(requestData);
		amwayProfileResponseData.getAccountMasterDetails()
				.setBusinessNature(com.amway.core.enums.AmwayBusinessNature.AMWAYBUSINESSNATURE_1.getCode());
		return amwayProfileResponseData;
	}


}
