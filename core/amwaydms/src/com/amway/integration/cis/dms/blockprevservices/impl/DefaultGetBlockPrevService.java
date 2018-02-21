package com.amway.integration.cis.dms.blockprevservices.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.BlockPrivilegeServiceOutput;
import com.amway.integration.dms.services.GetBlockPrivDetInput;
import com.hybris.commons.client.RestResponse;


/**
 * Service for to get the block prev details.
 *
 * port to amwaydms2
 */
public class DefaultGetBlockPrevService
		extends AbstractDmsService<BlockPrevResponseDataList, BlockPrevRequestData, BlockPrivilegeServiceOutput>
		implements DmsService<BlockPrevRequestData, BlockPrevResponseDataList>
{
	private static final Logger LOG = Logger.getLogger(DefaultGetBlockPrevService.class);

	@Override
	protected BlockPrevResponseDataList createResultObject()
	{
		return new BlockPrevResponseDataList();
	}

	@Override
	protected BlockPrevResponseDataList createDefaultResult()
	{
		final BlockPrevResponseDataList getBalanceResult = createResultObject();
		getBalanceResult.setReturnCd(-1);
		getBalanceResult.setReturnMessage("Failed to get block prev details");
		return getBalanceResult;
	}

	@Override
	protected BlockPrivilegeServiceOutput executeEvent(final Object input)
	{
		LOG.info("Calling webservice /BlocksPrivilegeService/getABOBlockPriv....");
		final RestResponse<BlockPrivilegeServiceOutput> dmsResultRestResponse = getDmsClient()
				.executeDmsRequest(getXclientRefId(), getUrlPath(), input,
						BlockPrivilegeServiceOutput.class);
		Assert.notNull(dmsResultRestResponse, "Process Block Prev Service failure");
		return dmsResultRestResponse.getResult();
	}

}
