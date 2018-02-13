/**
 *
 */
package com.amway.core.facades.salesreceipt.impl;

import com.amway.core.facades.salesreceipt.AmwayPointOfSaleReceiptFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.PrincipalData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import org.springframework.beans.factory.annotation.Required;
import com.amway.core.facades.customer.AmwayCustomerFacade;
import com.amway.core.order.services.AmwayOrderService;
import com.amway.facades.data.AmwayPointOfSaleReceiptData;
import com.amway.facades.data.AmwayAccountData;
import com.amway.facades.cart.data.PaymentDetailsData;




/**
 * @author jatinarora
 *
 */
public class DefaultAmwayPointOfSaleReceiptFacade implements AmwayPointOfSaleReceiptFacade
{

	private AmwayOrderService amwayOrderService;
	private AmwayCustomerFacade amwayCustomerFacade;
	private Converter<PointOfServiceModel, PointOfServiceData> pointOfServiceConverter;
	private Converter<OrderModel, OrderData> orderConverter;
	private Converter<PrincipalModel, PrincipalData> principalConverter;
	private Converter<CustomerModel, CustomerData> customerConverter;
	private Converter<AddressModel, AddressData> addressConverter;
	private Converter<AbstractOrderModel, PaymentDetailsData> amwayPaymentDetailsConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPointOfSaleReceiptData getSalesReceipt(final String orderCode)
	{
		final AmwayPointOfSaleReceiptData salesReceiptData = new AmwayPointOfSaleReceiptData();
		// fetch the order
		final OrderModel order = getAmwayOrderService().getOrderDetailsForCode(orderCode);
		final PrincipalModel employee = order.getEmployee();
		if (employee != null)
		{
			salesReceiptData.setEmployeeData(getPrincipalConverter().convert(employee));
		}
		if (order.getBatch() != null && order.getBatch().getTerminal() != null
				&& order.getBatch().getTerminal().getPointOfService() != null)
		{
			salesReceiptData
					.setPointOfServiceData(getPointOfServiceConverter().convert(order.getBatch().getTerminal().getPointOfService()));
		}

		salesReceiptData.setOrderData(getOrderConverter().convert(order));
		salesReceiptData.setPaymentData(getAmwayPaymentDetailsConverter().convert(order));
		CustomerData customerData = null;
		if (order.getUser() != null)
		{
			final CustomerModel customer = (CustomerModel) order.getUser();
			customerData = customerConverter.convert(customer);
			if (customer.getDefaultPaymentAddress() != null)
			{
				customerData.setDefaultBillingAddress(addressConverter.convert(customer.getDefaultPaymentAddress()));
			}
			if (customer.getDefaultShipmentAddress() != null)
			{
				customerData.setDefaultShippingAddress(addressConverter.convert(customer.getDefaultShipmentAddress()));
			}
		}
		final AmwayAccountData accountData = getAmwayCustomerFacade().getCurrentAccount();
		if (customerData != null)
		{
			accountData.setPrimaryParty(customerData);
		}
		salesReceiptData.setAmwayAccountData(accountData);
		return salesReceiptData;
	}


	@Required
	/**
	 * @param customerConverter
	 *           the customerConverter to set
	 */
	public void setCustomerConverter(final Converter<CustomerModel, CustomerData> customerConverter)
	{
		this.customerConverter = customerConverter;
	}

	@Required

	/**
	 * @param addressConverter
	 *           the addressConverter to set
	 */
	public void setAddressConverter(final Converter<AddressModel, AddressData> addressConverter)
	{
		this.addressConverter = addressConverter;
	}


	public AmwayOrderService getAmwayOrderService()
	{
		return amwayOrderService;
	}

	@Required
	public void setAmwayOrderService(final AmwayOrderService amwayOrderService)
	{
		this.amwayOrderService = amwayOrderService;
	}


	public AmwayCustomerFacade getAmwayCustomerFacade()
	{
		return amwayCustomerFacade;
	}

	@Required
	public void setAmwayCustomerFacade(final AmwayCustomerFacade amwayCustomerFacade)
	{
		this.amwayCustomerFacade = amwayCustomerFacade;
	}

	public Converter<PointOfServiceModel, PointOfServiceData> getPointOfServiceConverter()
	{
		return pointOfServiceConverter;
	}

	@Required
	public void setPointOfServiceConverter(final Converter<PointOfServiceModel, PointOfServiceData> pointOfServiceConverter)
	{
		this.pointOfServiceConverter = pointOfServiceConverter;
	}

	protected Converter<OrderModel, OrderData> getOrderConverter()
	{
		return orderConverter;
	}

	@Required
	public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
	{
		this.orderConverter = orderConverter;
	}

	public Converter<PrincipalModel, PrincipalData> getPrincipalConverter()
	{
		return principalConverter;
	}

	@Required
	public void setPrincipalConverter(final Converter<PrincipalModel, PrincipalData> principalConverter)
	{
		this.principalConverter = principalConverter;
	}

	public Converter<AbstractOrderModel, PaymentDetailsData> getAmwayPaymentDetailsConverter() {
		return amwayPaymentDetailsConverter;
	}

	public void setAmwayPaymentDetailsConverter(Converter<AbstractOrderModel, PaymentDetailsData> amwayPaymentDetailsConverter) {
		this.amwayPaymentDetailsConverter = amwayPaymentDetailsConverter;
	}
}
