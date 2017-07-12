/**
 *
 */
package com.amway.core.account.service;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.BlockPrevResponseDataList;
import com.amway.core.model.AmwayAccountModel;


/**
 * interface for Block Privilege Service
 */
public interface AmwayBlocksPrivilegeService
{
	/**
	 * method to block and unblock Privilege
	 *
	 * @param blockPrivTypeId
	 * @param block
	 * @param account
	 * @param user
	 * @param salesPlanAff
	 * @return CommonResponseFieldsData
	 */
	CommonResponseFieldsData processBlockPrivilege(final String blockPrivTypeId, final boolean block,
			final AmwayAccountModel account, final CustomerModel user, final String salesPlanAff);

	/**
	 * method to get block Privilege
	 *
	 * @param blockPrivTypeId
	 * @param block
	 * @param account
	 * @param user
	 * @param salesPlanAff
	 * @param order
	 * @return BlockPrevResponseDataList
	 */
	BlockPrevResponseDataList getBlockPrivilege(final String blockPrivTypeId, final String block, final AmwayAccountModel account,
			final CustomerModel user, final String salesPlanAff, final OrderModel order);

	/**
	 * This method checks for privilege blocked.
	 *
	 * @param blockPrivTypeId
	 * @param block
	 * @param account
	 * @param user
	 * @param salesPlanAff
	 * @param order
	 * @return
	 */
	boolean isPrivilegeBlocked(final String blockPrivTypeId, final String block, final AmwayAccountModel account,
			final CustomerModel user, final String salesPlanAff, final OrderModel order);
}
