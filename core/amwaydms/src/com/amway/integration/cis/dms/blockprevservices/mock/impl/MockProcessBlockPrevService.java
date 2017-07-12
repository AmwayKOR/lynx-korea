/**
 *
 */
package com.amway.integration.cis.dms.blockprevservices.mock.impl;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.BlockPrivilegeServiceOutput;



public class MockProcessBlockPrevService
		extends AbstractDmsService<BlockPrevResponseDataList, BlockPrevRequestData, BlockPrivilegeServiceOutput>
		implements DmsService<BlockPrevRequestData, BlockPrevResponseDataList>
{

	@Override
	public BlockPrevResponseDataList process(final BlockPrevRequestData requestData)
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
	protected BlockPrevResponseDataList createResultObject()
	{
		return new BlockPrevResponseDataList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#createDefaultResult()
	 */
	@Override
	protected BlockPrevResponseDataList createDefaultResult()
	{
		final BlockPrevResponseDataList dataList = createResultObject();
		dataList.setReturnCd(-1);
		dataList.setReturnMessage("Fail to process block prev service");
		return dataList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.integration.cis.dms.service.impl.AbstractDmsService#executeEvent(java.lang.Object)
	 */
	@Override
	protected BlockPrivilegeServiceOutput executeEvent(final Object input)
	{
		final BlockPrivilegeServiceOutput output = new BlockPrivilegeServiceOutput();
		output.setReturnCd(1);
		output.setReturnMessage("success");
		return output;
	}

}
