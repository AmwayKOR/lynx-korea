package com.amway.core.facades.order.impl;

import javax.annotation.Resource;

import com.amway.core.customer.service.AmwayCustomerAccountService;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.facades.order.AmwayOrderFacade;
import com.amway.core.data.AmwayOrderSearchParameters;
import com.amway.core.pos.service.AmwayPOSService;
import com.amway.core.returns.services.AmwayReturnService;
import com.amway.core.service.AmwayAccountCommerceService;

import de.hybris.platform.basecommerce.enums.CancelReason;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.order.impl.DefaultOrderFacade;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.ordercancel.OrderCancelException;
import de.hybris.platform.ordercancel.OrderCancelRequest;
import de.hybris.platform.ordercancel.OrderCancelService;
import de.hybris.platform.ordercancel.model.OrderCancelRecordEntryModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.store.BaseStoreModel;
import com.amway.core.order.services.AmwayOrderService;

import java.util.Date;


/**
 * Default Implementation
 */
public class DefaultAmwayOrderFacade extends DefaultOrderFacade implements AmwayOrderFacade
{
	private AmwayCustomerAccountService amwayCustomerAccountService;
	@Resource(name = "orderCancelService")
	private OrderCancelService orderCancelService;
	@Resource(name = "amwayAccountCommerceService")
	private AmwayAccountCommerceService amwayAccountCommerceService;
	@Resource(name = "eventService")
	private EventService eventService;
	private AmwayReturnService amwayReturnService;
	@Resource(name = "amwayPOSService")
	private AmwayPOSService amwayPOSService;
	
	@Resource(name = "orderService")
	private AmwayOrderService orderService;

	/**
	 * To get the paged order history
	 *
	 * @param pageableData
	 * @param amwayCartType
	 * @return SearchPageData<OrderHistoryData>
	 */
	@Override
	public SearchPageData<OrderHistoryData> getPagedOrderHistory(PageableData pageableData, AmwayCartType amwayCartType)
	{
		final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
		final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
		final SearchPageData<OrderModel> orderResults = getAmwayCustomerAccountService()
				.getOrders(currentCustomer, currentBaseStore, pageableData, amwayCartType);
		return convertPageData(orderResults, getOrderHistoryConverter());
	}

	protected <S, T> SearchPageData<T> convertPageData(final SearchPageData<S> source, final Converter<S, T> converter)
	{
		final SearchPageData<T> result = new SearchPageData<T>();
		result.setPagination(source.getPagination());
		result.setSorts(source.getSorts());
		result.setResults(Converters.convertAll(source.getResults(), converter));
		return result;
	}

	/**
	 * To retrieve the order details
	 *
	 * @param code
	 * @param type
	 * @return OrderData
	 */
	@Override
	public OrderData getOrderDetailsForCode(String code, AmwayCartType type)
	{
		final BaseStoreModel baseStoreModel = getBaseStoreService().getCurrentBaseStore();
		final OrderModel orderModel = getAmwayCustomerAccountService()
				.getOrderForCodeAndType((CustomerModel) getUserService().getCurrentUser(), code, baseStoreModel, type);
		if (orderModel == null)
		{
			throw new UnknownIdentifierException("Order with code " + code + " not found");
		}
		return getOrderConverter().convert(orderModel);
	}

	/**
	 * To cancel the order from POS
	 *
	 * @param code
	 * @param type
	 * @return boolean
	 */
	@Override
	public boolean cancelOrder(String code, AmwayCartType type) throws OrderCancelException
	{
		final BaseStoreModel baseStoreModel = getBaseStoreService().getCurrentBaseStore();
		final OrderModel orderModel = getAmwayCustomerAccountService()
				.getOrderForCodeAndType((CustomerModel) getUserService().getCurrentUser(), code, baseStoreModel, type);
		//check to make sure Export Status is not EXPORTED.  If its not set, its null, and that's ok.
		if (orderModel != null && (orderModel.getExportStatus() == null || !orderModel.getExportStatus().equals(ExportStatus.EXPORTED)))
		{
			final OrderCancelRequest orderCancelRequest = new OrderCancelRequest(orderModel);
			orderCancelRequest.setCancelReason(CancelReason.OTHER);
			orderCancelRequest
					.setNotes("cancellation from POS by user " + getAmwayAccountCommerceService().getCurrentAccountNumber());

			final OrderCancelRecordEntryModel orderRequestRecord = orderCancelService
					.requestOrderCancel(orderCancelRequest, getUserService().getCurrentUser());
			orderRequestRecord.getCode();
			return orderRequestRecord != null ? true : false;
		}
		else
		{
			throw new OrderCancelException(orderModel.getCode(), "Order cannot be cancelled only returned");
		}
	}

	/**
	 * To send order return notification
	 *
	 * @param code
	 * @param type
	 */
	@Override
	public boolean sendOrderReturnNotification(String code, AmwayCartType type)
	{
		final BaseStoreModel baseStoreModel = getBaseStoreService().getCurrentBaseStore();
		final OrderModel orderModel = getAmwayCustomerAccountService()
				.getOrderForCodeAndType((CustomerModel) getUserService().getCurrentUser(), code, baseStoreModel, type);
		if (orderModel == null)
		{
			throw new UnknownIdentifierException(
					"Order with orderGUID " + code + " not found for current user in current BaseStore");
		}
		boolean isReturnable = amwayReturnService.isReturnPossible(orderModel);
		if (isReturnable)
		{
			return true;
		}
		return false;
	}

	@Override
	public SearchPageData<OrderHistoryData> getPagedOrderHistoryForBatch(PageableData pageableData, String batch_id) {
		final SearchPageData<OrderModel> orderResults = amwayPOSService.getOrders(pageableData, batch_id);
		return convertPageData(orderResults, getOrderHistoryConverter());
	}

	@Override
	public SearchPageData<OrderHistoryData> getPagedOrderHistoryForPOS(PageableData pageableData, String pickupStore, Date startDate, Date endDate) {
		final SearchPageData<OrderModel> orderResults = amwayPOSService.getOrders(pageableData, pickupStore, startDate, endDate);
		return convertPageData(orderResults, getOrderHistoryConverter());
	}
	
	@Override
	public SearchPageData<OrderHistoryData> getOrders(AmwayOrderSearchParameters amwayOrderSearchParameters, PageableData pageableData) {
		final SearchPageData<OrderModel> orderResults = orderService.getOrders(amwayOrderSearchParameters, pageableData);
		return convertPageData(orderResults, getOrderHistoryConverter());
	}

	/**
	 * @return amwayCustomerAccountService
	 */
	public AmwayCustomerAccountService getAmwayCustomerAccountService()
	{
		return amwayCustomerAccountService;
	}

	/**
	 * @param amwayCustomerAccountService the amwayCustomerAccountService to set
	 */
	public void setAmwayCustomerAccountService(AmwayCustomerAccountService amwayCustomerAccountService)
	{
		this.amwayCustomerAccountService = amwayCustomerAccountService;
	}

	/**
	 * @return orderCancelService
	 */
	public OrderCancelService getOrderCancelService()
	{
		return orderCancelService;
	}

	/**
	 * @param orderCancelService the orderCancelService to set
	 */
	public void setOrderCancelService(OrderCancelService orderCancelService)
	{
		this.orderCancelService = orderCancelService;
	}

	/**
	 * @return amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService the amwayAccountCommerceService to set
	 */
	public void setAmwayAccountCommerceService(AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	/**
	 * @return eventService
	 */
	public EventService getEventService()
	{
		return eventService;
	}

	/**
	 * @param eventService the eventService to set
	 */
	public void setEventService(EventService eventService)
	{
		this.eventService = eventService;
	}

	/**
	 * @return the amwayReturnService
	 */
	public AmwayReturnService getAmwayReturnService()
	{
		return amwayReturnService;
	}

	/**
	 * @param amwayReturnService the amwayReturnService to set
	 */
	public void setAmwayReturnService(AmwayReturnService amwayReturnService)
	{
		this.amwayReturnService = amwayReturnService;
	}

	public AmwayPOSService getAmwayPOSService() {
		return amwayPOSService;
	}

	public void setAmwayPOSService(AmwayPOSService amwayPOSService) {
		this.amwayPOSService = amwayPOSService;
	}
}
