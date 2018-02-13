package com.amway.integration.cis.v3.util;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;

import com.amway.core.model.AmwayAccountModel;
import com.amway.integration.cis.v3.swagger.OrderRequest;


/**
 * Utility class to fulfill order related calls (POST orders).
 */
public class OrderCallUtil
{
	public static RequestContext<OrderRequest> createOrderRequest(OrderModel order, CustomerModel currentUser,
			AmwayAccountModel currentAccount, OrderRequest.ActionTypeEnum orderAction, String invoiceNumber,
			OrderRequest.OrderTypeEnum type)
	{
		RequestContext<OrderRequest> request = new RequestContext<OrderRequest>(
				currentAccount, currentUser, order.getStore());
		OrderRequest requestData = new OrderRequest();
		requestData.setActionType(orderAction);
		requestData.setOrderType(type);
		requestData.setInvoiceNumber(invoiceNumber);
		requestData.setProcessDate(DmsCallDecorator.formatDateForDMS(new Date()));
		request.setCountryIso(order.getSite().getDefaultCountry().getIsocode());
		request.setRequestData(requestData);
		return request;
	}
}
