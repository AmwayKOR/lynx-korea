/**
 *
 */
package com.amway.core.account.service.impl;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.dms.service.DmsService;


public class MockGetBlockPrevService implements DmsService<BlockPrevRequestData, BlockPrevResponseDataList>
{
	@Override
	public BlockPrevResponseDataList process(final BlockPrevRequestData requestData)
	{
		final BlockPrevResponseDataList getBalanceResult = new BlockPrevResponseDataList();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get block prev details");
		return getBalanceResult;
	}
}
