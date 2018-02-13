/**
 *
 */
package com.amway.core.account.service.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.core.account.service.AmwayBlocksPrivilegeService;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.BlockPrevRequestData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.dms.service.DmsService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.util.AmwayDateHelper;


/**
 * interface for Block Privilege Service
 */
public class DefaultAmwayBlocksPrivilegeService implements AmwayBlocksPrivilegeService
{
	private DmsService<BlockPrevRequestData, CommonResponseFieldsData> processBlockPrevService;
	private DmsService<BlockPrevRequestData, BlockPrevResponseDataList> getBlockPrevService;

	/**
	 * {@link #processBlockPrivilege(java.lang.String, boolean, com.amway.core.model.AmwayAccountModel, de.hybris.platform.core.model.user.CustomerModel, java.lang.String)}
	 */
	@Override
	public CommonResponseFieldsData processBlockPrivilege(final String blockPrivTypeId, final boolean block,
			final AmwayAccountModel account, final CustomerModel user, final String salesPlanAff)
	{
		final BlockPrevRequestData requestData = new BlockPrevRequestData();
		requestData.setAboNum(account.getCode());
		requestData.setLoggedInAccountId(account.getCode());
		requestData.setBlockPrivTypeId(blockPrivTypeId);
		final String currentTime = AmwayDateHelper.getTimeForSiteTimeZone(AmwayDateHelper.TIME_FMT_LOS);
		requestData.setEffectiveDate(currentTime);
		requestData.setExpirationDate(block ? StringUtils.EMPTY : currentTime);
		requestData.setSalesPlanAff(salesPlanAff);
		requestData.setPartyId(user.getCustomerID());
		requestData.setLoggedInPartyId(user.getCustomerID());
		return getProcessBlockPrevService().process(requestData);
	}

	/**
	 * {@link #getBlockPrivilege(java.lang.String, java.lang.String, com.amway.core.model.AmwayAccountModel, de.hybris.platform.core.model.user.CustomerModel, java.lang.String, de.hybris.platform.core.model.order.OrderModel)}
	 */
	@Override
	public BlockPrevResponseDataList getBlockPrivilege(final String blockPrivTypeId, final String block,
			final AmwayAccountModel account, final CustomerModel user, final String salesPlanAff, final OrderModel order)
	{
		final BlockPrevRequestData request = new BlockPrevRequestData();
		request.setAboNum(account.getCode());
		request.setLoggedInAccountId(account.getCode());
		request.setEffectiveDate(AmwayDateHelper.getTimeForSiteTimeZone(order.getSite(), AmwayDateHelper.TIME_FMT_LOS));
		request.setBlockPrivTypeId(blockPrivTypeId);
		request.setSalesPlanAff(salesPlanAff);
		request.setIsBlockFlag(block);
		return getGetBlockPrevService().process(request);
	}

	/**
	 * {@link #isPrivilegeBlocked(java.lang.String, java.lang.String, com.amway.core.model.AmwayAccountModel, de.hybris.platform.core.model.user.CustomerModel, java.lang.String)}
	 */
	@Override
	public boolean isPrivilegeBlocked(final String blockPrivTypeId, final String block, final AmwayAccountModel account,
			final CustomerModel user, final String salesPlanAff, final OrderModel order)
	{
		final BlockPrevResponseDataList responseData = getBlockPrivilege(blockPrivTypeId, block, account, user, salesPlanAff,
				order);
		if (responseData.getReturnCd() == 1 && CollectionUtils.isNotEmpty(responseData.getBlockPrevResponseData()))
		{
			return true;
		}
		return false;
	}

	/**
	 * @return processBlockPrevService
	 */
	public DmsService<BlockPrevRequestData, CommonResponseFieldsData> getProcessBlockPrevService()
	{
		return processBlockPrevService;
	}

	/**
	 * @param processBlockPrevService the processBlockPrevService to set
	 */
	public void setProcessBlockPrevService(
			final DmsService<BlockPrevRequestData, CommonResponseFieldsData> processBlockPrevService)
	{
		this.processBlockPrevService = processBlockPrevService;
	}

	/**
	 * @return getBlockPrevService
	 */
	public DmsService<BlockPrevRequestData, BlockPrevResponseDataList> getGetBlockPrevService()
	{
		return getBlockPrevService;
	}

	/**
	 * @param getBlockPrevService the getBlockPrevService to set
	 */
	public void setGetBlockPrevService(final DmsService<BlockPrevRequestData, BlockPrevResponseDataList> getBlockPrevService)
	{
		this.getBlockPrevService = getBlockPrevService;
	}



}
