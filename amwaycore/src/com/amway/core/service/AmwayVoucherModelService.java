package com.amway.core.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.voucher.VoucherModelService;
import de.hybris.platform.voucher.model.VoucherModel;


/**
 * Interface for amway voucher
 */
public interface AmwayVoucherModelService extends VoucherModelService
{

	/**
	 * @param paramVoucherModel
	 * @param paramString
	 * @param paramAbstractOrderModel
	 * @return boolean
	 */
	@Override
	public boolean isReservable(VoucherModel paramVoucherModel, String paramString, AbstractOrderModel paramAbstractOrderModel);


	/**
	 * @param paramVoucherModel
	 * @param paramString
	 * @param paramUserModel
	 * @return boolean
	 */
	@Override
	public boolean isReservable(VoucherModel paramVoucherModel, String paramString, UserModel paramUserModel);
}
