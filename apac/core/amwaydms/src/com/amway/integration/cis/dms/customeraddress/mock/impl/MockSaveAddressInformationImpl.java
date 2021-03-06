/**
 *
 */
package com.amway.integration.cis.dms.customeraddress.mock.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AddUpdatePartyAddressRequestData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;


/**
 * Mock Service for to Save Address Information
 */
public class MockSaveAddressInformationImpl
		extends AbstractDmsService<CommonResponseFieldsData, AddUpdatePartyAddressRequestData, ReturnInfoService>
		implements DmsService<AddUpdatePartyAddressRequestData, CommonResponseFieldsData>
{

	@Override
	public CommonResponseFieldsData process(final AddUpdatePartyAddressRequestData requestData)
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
	protected CommonResponseFieldsData createResultObject()
	{
		return new CommonResponseFieldsData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected CommonResponseFieldsData createDefaultResult()
	{
		final CommonResponseFieldsData fieldsData = new CommonResponseFieldsData();
		fieldsData.setReturnCd(-1);
		fieldsData.setReturnMessage("Failed To Save Address Information");
		return fieldsData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected ReturnInfoService executeEvent(final Object input)
	{
		final ReturnInfoService response = new ReturnInfoService();
		response.setReturnCd(1);
		response.setReturnMessage("Address updated successfully");
		return response;
	}
}
