package com.amway.core.renewal.service.impl;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.renewal.dao.AmwayRenewalDao;
import com.amway.core.renewal.service.AmwayRenewalService;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.store.services.BaseStoreService;
import org.springframework.beans.factory.annotation.Required;

import static java.util.Objects.nonNull;

@AmwayBean(ext="amwaycore",docs={"https://jira.amway.com:8444/display/HC/Auto-Renewal+Order",
		                           "https://jira.amway.com:8444/display/HC/Auto-Renewal+OCC+Endpoint"})
public class DefaultAmwayRenewalService implements AmwayRenewalService
{
	private static final String ACCOUNT_IS_INACTIVE_ATTR = "isInactiveAccount";
	private static final String NO_DELIVERY_MODE_CODE = "no-delivery";

	private AmwayRenewalDao amwayRenewalDao;
	private CommerceCheckoutService commerceCheckoutService;
	private SessionService sessionService;
	private DeliveryService deliveryService;

	@Override
	public String placeRenewal(final CartModel cart) throws InvalidCartException
	{
		return placeRenewal(cart, SalesApplication.AMWAY_WEB);
	}

	@Override
	public String placeRenewal(final CartModel cart, final SalesApplication channel) throws InvalidCartException
	{
		final CommerceCheckoutParameter parameter = createCommerceCheckoutParameter(cart, channel);
		commerceCheckoutService.authorizePayment(parameter);
		return placeAndCapture(cart, channel);
	}

	@Override
	public String placeAndCapture(final CartModel cart, final SalesApplication channel) throws InvalidCartException
	{
		String orderCode = null;
		cart.setDeliveryMode(deliveryService.getDeliveryModeForCode(NO_DELIVERY_MODE_CODE));
		cart.setType(AmwayCartType.RENEWAL);
		final CommerceCheckoutParameter parameter = createCommerceCheckoutParameter(cart, channel);
		final CommerceOrderResult placeResult = commerceCheckoutService.placeOrder(parameter);
		final OrderModel order = placeResult.getOrder();
		if (nonNull(order))
		{
			sessionService.setAttribute(ACCOUNT_IS_INACTIVE_ATTR, false);
			orderCode = order.getCode();
		}
		return orderCode;
	}

	private CommerceCheckoutParameter createCommerceCheckoutParameter(final CartModel cart, final SalesApplication channel)
	{
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cart);
		parameter.setSalesApplication(channel);
		final PaymentInfoModel paymentInfo = cart.getPaymentInfo();
		if (paymentInfo instanceof CreditCardPaymentInfoModel)
		{
			parameter.setPaymentProvider(((CreditCardPaymentInfoModel) paymentInfo).getPaymentProvider());
		}
		return parameter;
	}

	@Override
	public AbstractOrderModel getLastRenewalOrder(UserModel user)
	{
		return amwayRenewalDao.getLastRenewalOrder(user).orElse(null);
	}

	@Required
	public void setAmwayRenewalDao(AmwayRenewalDao amwayRenewalDao)
	{
		this.amwayRenewalDao = amwayRenewalDao;
	}

	@Required
	public void setCommerceCheckoutService(CommerceCheckoutService commerceCheckoutService)
	{
		this.commerceCheckoutService = commerceCheckoutService;
	}

	@Required
	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	@Required
	public void setDeliveryService(DeliveryService deliveryService)
	{
		this.deliveryService = deliveryService;
	}
}
