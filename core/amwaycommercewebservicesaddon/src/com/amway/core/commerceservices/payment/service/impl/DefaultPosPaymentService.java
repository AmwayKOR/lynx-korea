package com.amway.core.commerceservices.payment.service.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.services.BaseStoreService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.cart.data.PlaceOrderCartInfoData;
import com.amway.core.cart.data.PosPaymentInfoData;
import com.amway.core.checkout.services.AmwayCommerceCheckoutService;
import com.amway.core.commerceservices.payment.pos.stratergies.PosPaymentTransactionStratergy;
import com.amway.core.commerceservices.payment.service.PosPaymentService;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.util.AmwayCartHelper;
import com.amway.facades.order.data.ArCreditPaymentInfoData;


/**
 * Default Implementation for Pos payment service.
 */
public class DefaultPosPaymentService implements PosPaymentService
{
	private PosPaymentTransactionStratergy posPaymentTransactionStratergy;
	private UserService userService;
	private CustomerAccountService customerAccountService;
	private BaseStoreService baseStoreService;
	private AmwayCommerceCheckoutService amwayCommerceCheckoutService;
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.core.commerceservices.payment.service.PosPaymentService#capturePayment(com.amway.core.cart.data.
	 * PlaceOrderCartInfoData)
	 */
	@Override
	public void capturePayment(final AbstractOrderModel orderModel, final PlaceOrderCartInfoData cartInfoData)
	{
		ServicesUtil.validateParameterNotNull(orderModel, "order model cannot be null");
		ServicesUtil.validateParameterNotNull(cartInfoData, "palce order cartinfo cannot be null");

		final CustomerModel currentUser = (CustomerModel) orderModel.getUser();
		if (!currentUser.equals(orderModel.getUser()))
		{
			throw new IllegalArgumentException("current user does not belong to the cart.");
		}

		if (!(orderModel.getPaymentInfo() instanceof AmwayMonetaryPaymentInfoModel)
				&& CollectionUtils.isNotEmpty(orderModel.getPaymentTransactions()))
		{
			throw new IllegalArgumentException(
					"this cart has already payment transactions associated to it. Please delete this cart before placing a new order.");
		}

		final List<PosPaymentInfoData> paymentDatas = new ArrayList<>();
		if (cartInfoData.getCcpaymententries() != null)
		{
			paymentDatas.addAll(cartInfoData.getCcpaymententries().getCcPaymentEntryInfos());
		}
		if (cartInfoData.getDcpaymententries() != null)
		{
			paymentDatas.addAll(cartInfoData.getDcpaymententries().getDcPaymentEntryInfos());
		}
		if (cartInfoData.getCashpaymententries() != null)
		{
			paymentDatas.addAll(cartInfoData.getCashpaymententries().getCashPaymentEntryInfos());
		}

		setPaymentDetails(currentUser, orderModel, paymentDatas);
	}

	/**
	 * Validate the order amount
	 * 
	 * @param cartModel
	 * @param cartInfoData
	 * @return boolean
	 */
	@Override
	public boolean validateOrderAmount(final CartModel cartModel, final PlaceOrderCartInfoData cartInfoData)
	{
		BigDecimal amount = BigDecimal.ZERO;

		final List<PosPaymentInfoData> paymentDatas = new ArrayList<>();
		if (cartInfoData.getCcpaymententries() != null)
		{
			paymentDatas.addAll(cartInfoData.getCcpaymententries().getCcPaymentEntryInfos());
		}
		if (cartInfoData.getDcpaymententries() != null)
		{
			paymentDatas.addAll(cartInfoData.getDcpaymententries().getDcPaymentEntryInfos());
		}
		if (cartInfoData.getCashpaymententries() != null)
		{
			paymentDatas.addAll(cartInfoData.getCashpaymententries().getCashPaymentEntryInfos());
		}

		if (cartInfoData.getArCreditPaymentInfo() != null)
		{
			paymentDatas.add(cartInfoData.getArCreditPaymentInfo());
		}
		for (final PosPaymentInfoData paymentData : paymentDatas)
		{
			amount = amount.add(BigDecimal.valueOf(paymentData.getAmount().doubleValue()));
		}

		final boolean amountValid = amount.compareTo(BigDecimal.valueOf(cartModel.getTotalPrice().doubleValue())) == 0;
		//creating this because hybris is the source of payment provider for AR
		if (amountValid && cartInfoData.getArCreditPaymentInfo() != null)
		{
			final ArCreditPaymentInfoData arPaymentInfo = new ArCreditPaymentInfoData();
			arPaymentInfo.setAmount(cartInfoData.getArCreditPaymentInfo().getAmount().doubleValue());
			amwayCommerceCheckoutService.createARCreditPaymentInfo((CustomerModel) userService.getCurrentUser(),
					arPaymentInfo.getAmount(), cartModel);
		}
		return amountValid;
	}

	/**
	 * 
	 * @return posPaymentTransactionStratergy
	 */
	public PosPaymentTransactionStratergy getPosPaymentTransactionStratergy()
	{
		return posPaymentTransactionStratergy;
	}

	/**
	 * 
	 * @param posPaymentTransactionStratergy
	 *         the posPaymentTransactionStratergy to set
	 */
	public void setPosPaymentTransactionStratergy(final PosPaymentTransactionStratergy posPaymentTransactionStratergy)
	{
		this.posPaymentTransactionStratergy = posPaymentTransactionStratergy;
	}

	/**
	 * 
	 * @return userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * 
	 * @param userService
	 *         the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * 
	 * @return customerAccountService
	 */
	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	/**
	 * 
	 * @param customerAccountService
	 *         the customerAccountService to set
	 */
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	/**
	 * 
	 * @return baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * 
	 * @param baseStoreService
	 *         the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * 
	 * @return amwayCommerceCheckoutService
	 */
	public AmwayCommerceCheckoutService getAmwayCommerceCheckoutService()
	{
		return amwayCommerceCheckoutService;
	}

	/**
	 * 
	 * @param amwayCommerceCheckoutService
	 *         the amwayCommerceCheckoutService to set
	 */
	public void setAmwayCommerceCheckoutService(final AmwayCommerceCheckoutService amwayCommerceCheckoutService)
	{
		this.amwayCommerceCheckoutService = amwayCommerceCheckoutService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *         the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * Sets payment details on the cart model.  The code for this private method was moved from an internal
	 * code block at the end of capturePayment method.
	 *
	 * @param currentUser  	current user for the order we are operating on.
	 * @param orderModel  	order we are operating on
	 * @param paymentDatas  list of POS payment info data for the order
	 */
	private void setPaymentDetails(final CustomerModel currentUser, final AbstractOrderModel orderModel,
											 final List<PosPaymentInfoData> paymentDatas)
	{

		final CartModel cartModel = (CartModel) orderModel;
		final Map<String, Object> paymentDetails = AmwayCartHelper.getPaymentDetails(cartModel);
		for (final PosPaymentInfoData paymentData : paymentDatas)
		{
			final PaymentTransactionModel paymentTransaction = getPosPaymentTransactionStratergy().savePaymentTransaction(
					currentUser, orderModel, paymentData);
			final Map<String, String> paymentInfoDetails = new HashMap<>();
			paymentInfoDetails.put("amount", String.valueOf(paymentData.getAmount()));
			paymentDetails.put(paymentTransaction.getInfo().getPk().toString(), paymentInfoDetails);
		}

		((CartModel) orderModel).setPaymentDetails(paymentDetails);
		getModelService().save(orderModel);
	}
}
