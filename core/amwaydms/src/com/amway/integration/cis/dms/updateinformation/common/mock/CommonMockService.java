/**
 *
 */
package com.amway.integration.cis.dms.updateinformation.common.mock;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.ReturnInfoService;



/**
 * Common Mock response for some services.
 */
public class CommonMockService extends AbstractDmsService<CommonResponseFieldsData, Object, Object>
		implements DmsService<Object, CommonResponseFieldsData>
{

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
		final CommonResponseFieldsData commonResponseFieldsData = createResultObject();
		commonResponseFieldsData.setReturnCd(1);
		return commonResponseFieldsData;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected Object executeEvent(final Object input)
	{
		final ReturnInfoService responce = new ReturnInfoService();
		responce.setReturnCd(1);
		responce.setReturnMessage("success");
		return responce;
	}
}
