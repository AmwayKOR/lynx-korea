/**
 *
 */
package com.amway.integration.cis.dms.blockprevservices.mock.impl;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.BlockPrivDetSrvcOutput;
import com.amway.integration.dms.services.BlockPrivilegeServiceOutput;


public class MockGetBlockPrevService
		extends AbstractDmsService<BlockPrevResponseDataList, BlockPrevRequestData, BlockPrivilegeServiceOutput>
		implements DmsService<BlockPrevRequestData, BlockPrevResponseDataList>
{
	private static final String ABO_NO = "3109215040";
	private static final String AFFLI_NO = "170";

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
		dataList.setReturnMessage("Faild to get Block Prev List");
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
		final BlockPrivDetSrvcOutput privDetSrvcOutput = new BlockPrivDetSrvcOutput();
		privDetSrvcOutput.setAboNum(ABO_NO);
		privDetSrvcOutput.setBlockPrivTypeId("BlockAmwayCredit");
		privDetSrvcOutput.setEffectiveDate("2016-01-23T06:38:30-02:00");
		privDetSrvcOutput.setExpirationDate("2020-01-23T06:38:30-02:00");
		privDetSrvcOutput.setIsBlockFlag("Y");
		privDetSrvcOutput.setSalesPlanAff(AFFLI_NO);

		output.getObject().add(privDetSrvcOutput);
		output.setReturnCd(1);
		output.setReturnMessage("Success");
		return output;
	}

}
