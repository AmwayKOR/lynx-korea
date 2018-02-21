/**
 *
 */
package com.amway.core.account.service.impl;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.service.DmsService;


public class MockProcessBlockPrevService implements DmsService<BlockPrevRequestData, CommonResponseFieldsData>
{
	@Override
	public CommonResponseFieldsData process(final BlockPrevRequestData requestData)
	{
		final CommonResponseFieldsData getBalanceResult = new CommonResponseFieldsData();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to process block prev details");
		return getBalanceResult;
	}
}
