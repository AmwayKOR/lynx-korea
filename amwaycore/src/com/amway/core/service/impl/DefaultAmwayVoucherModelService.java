package com.amway.core.service.impl;

import de.hybris.platform.core.GenericCondition;
import de.hybris.platform.core.GenericConditionList;
import de.hybris.platform.core.GenericQuery;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.voucher.constants.GeneratedVoucherConstants;
import de.hybris.platform.voucher.impl.DefaultVoucherModelService;
import de.hybris.platform.voucher.jalo.GeneratedPromotionVoucher;
import de.hybris.platform.voucher.jalo.Voucher;
import de.hybris.platform.voucher.jalo.VoucherInvalidation;
import de.hybris.platform.voucher.jalo.VoucherManager;
import de.hybris.platform.voucher.model.PromotionVoucherModel;
import de.hybris.platform.voucher.model.VoucherModel;

import java.util.Collection;
import java.util.List;

import com.amway.core.service.AmwayVoucherModelService;


/**
 * Default implementation
 */
public class DefaultAmwayVoucherModelService extends DefaultVoucherModelService implements AmwayVoucherModelService
{

	@Override
	public boolean isReservable(final VoucherModel voucher, final String voucherCode, final UserModel user)
	{
		if (voucher instanceof PromotionVoucherModel)
		{
			final Voucher promotionVoucher = getVoucher(voucher);
			final User voucherUser = (User) getModelService().getSource(user);

			final GenericCondition condition = GenericCondition.createConditionList(
					new GenericCondition[] { GenericCondition.equals("user", voucherUser),
							GenericCondition.equals("code", voucherCode) });

			final Collection<VoucherInvalidation> voucherInvalidations = promotionVoucher.getSession()
					.search(new GenericQuery("VoucherInvalidation".intern(), condition),
							promotionVoucher.getSession().createSearchContext()).getResult();

			return voucherInvalidations.size() < ((GeneratedPromotionVoucher) promotionVoucher).getRedemptionQuantityLimitPerUser()
					.intValue();
		}
		else
		{
			super.isReservable(voucher, voucherCode, user);
		}
		return false;
	}

	@Override
	public boolean isReservable(final VoucherModel voucher, final String voucherCode, final AbstractOrderModel order)
	{
		if (voucher instanceof PromotionVoucherModel)
		{
			final AbstractOrder abstractOrder = (AbstractOrder) getModelService().getSource(order);
			return isReservable(voucher, voucherCode, order.getUser()) && !isApplied(voucherCode, abstractOrder, voucher);
		}
		else
		{
			return super.isReservable(voucher, voucherCode, order);
		}
	}

	private boolean isApplied(final String aVoucherCode, final AbstractOrder abstractOrder, final VoucherModel voucher)
	{
		boolean ret = false;
		final VoucherManager vm = VoucherManager.getInstance();
		final Voucher v = vm.getVoucher(aVoucherCode);
		if ((v != null) &&

				(abstractOrder.getDiscounts().contains(v)))
		{
			if (abstractOrder instanceof Cart)
			{
				ret = vm.getAppliedVoucherCodes((Cart) abstractOrder).contains(aVoucherCode);
			}
			else
			{
				final VoucherInvalidation inv = getInvalidation(aVoucherCode, (Order) abstractOrder, voucher);
				ret = (inv != null) && ("confirmed".equals(inv.getStatus()));
			}

		}

		return ret;
	}


	/**
	 * @param aVoucherCode
	 * @param abstractOrder
	 * @return
	 */
	private VoucherInvalidation getInvalidation(final String aVoucherCode, final Order abstractOrder, final VoucherModel voucher)
	{
		final Voucher promotionVoucher = getVoucher(voucher);
		final GenericQuery query = new GenericQuery(GeneratedVoucherConstants.TC.VOUCHERINVALIDATION, GenericConditionList
				.createConditionList(new GenericCondition[] { GenericCondition.equals("code", aVoucherCode),
						GenericCondition.equals("order", abstractOrder) }));
		final List invalidations = promotionVoucher.getSession().search(query, promotionVoucher.getSession().createSearchContext())
				.getResult();
		return invalidations.isEmpty() ? null : (VoucherInvalidation) invalidations.get(0);
	}


}
