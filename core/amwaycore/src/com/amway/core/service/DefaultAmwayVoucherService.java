package com.amway.core.service;

import de.hybris.platform.commerceservices.order.CommerceCartCalculationStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.voucher.constants.GeneratedVoucherConstants;
import de.hybris.platform.voucher.impl.DefaultVoucherService;
import de.hybris.platform.voucher.jalo.PromotionVoucher;
import de.hybris.platform.voucher.jalo.Voucher;
import de.hybris.platform.voucher.jalo.VoucherManager;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.annotation.Resource;


/**
 * Default Implementation for amway voucher service
 */
public class DefaultAmwayVoucherService extends DefaultVoucherService
{
	@Resource
	private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

	@Resource
	private AmwayVoucherModelService voucherModelService;

	/**
	 * @param paramString
	 * @param paramCartModel
	 * @return boolean
	 * @throws JaloPriceFactoryException
	 */
	@Override
	public boolean redeemVoucher(final String paramString, final CartModel paramCartModel) throws JaloPriceFactoryException
	{

		final Voucher voucher = getVoucher(getVoucher(paramString));
		boolean voucherRedeemed = false;
		if (voucher instanceof PromotionVoucher && redeemVoucher(paramString, paramCartModel, voucher))
		{
			voucherRedeemed = true;
		}
		else
		{
			if (super.redeemVoucher(paramString, paramCartModel))
			{
				voucherRedeemed = true;
			}
		}
		if (voucherRedeemed)
		{
			final CommerceCartParameter parameter = new CommerceCartParameter();
			parameter.setEnableHooks(true);
			parameter.setCart(paramCartModel);
			commerceCartCalculationStrategy.recalculateCart(parameter);
			return true;
		}
		return false;
	}

	/**
	 * @param paramString
	 * @param paramCartModel
	 * @throws JaloPriceFactoryException
	 */
	@Override
	public void releaseVoucher(final String paramString, final CartModel paramCartModel) throws JaloPriceFactoryException
	{
		super.releaseVoucher(paramString, paramCartModel);
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(paramCartModel);
		commerceCartCalculationStrategy.recalculateCart(parameter);
	}


	private boolean redeemVoucher(final String aVoucherCode, final CartModel order, final Voucher voucher)
			throws JaloPriceFactoryException
	{
		final AbstractOrder aCart = (Cart) getModelService().getSource(order);

		if (getVoucher(getVoucher(aVoucherCode)).checkVoucherCode(aVoucherCode) && (voucherModelService
				.isReservable(getVoucher(aVoucherCode), aVoucherCode, order)))
		{
			aCart.addDiscount(voucher);
			aCart.recalculate();
			final Collection appliedVoucherCodes = new LinkedHashSet(
					VoucherManager.getInstance().getAppliedVoucherCodes((Cart) aCart));
			appliedVoucherCodes.add(aVoucherCode);
			aCart.setProperty(voucher.getSession().getSessionContext(),
					GeneratedVoucherConstants.Attributes.AbstractOrder.APPLIEDVOUCHERCODES,
					(!(appliedVoucherCodes.isEmpty())) ? appliedVoucherCodes : null);

			return true;
		}
		return false;
	}


	/**
	 * @return commerceCartCalculationStrategy
	 */
	public CommerceCartCalculationStrategy getCommerceCartCalculationStrategy()
	{
		return commerceCartCalculationStrategy;
	}

	/**
	 * @param commerceCartCalculationStrategy the commerceCartCalculationStrategy to set
	 */
	public void setCommerceCartCalculationStrategy(final CommerceCartCalculationStrategy commerceCartCalculationStrategy)
	{
		this.commerceCartCalculationStrategy = commerceCartCalculationStrategy;
	}

}
