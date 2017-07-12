package com.amway.core.facades.order;

import com.amway.core.enums.AmwayCartType;

import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.ordercancel.OrderCancelException;

import java.util.Date;


/**
 * Interface for Amway order facade.
 */
public interface AmwayOrderFacade extends OrderFacade
{
	/**
	 * @param pageableData
	 * @param amwayCartType
	 * @return SearchPageData
	 */
	public SearchPageData<OrderHistoryData> getPagedOrderHistory(PageableData pageableData, AmwayCartType amwayCartType);

	/**
	 * @param code
	 * @param type
	 * @return OrderData
	 */
	public OrderData getOrderDetailsForCode(String code, AmwayCartType type);

	/**
	 * @param code
	 * @param type
	 * @return
	 * @throws OrderCancelException
	 */
	public boolean cancelOrder(String code, AmwayCartType type) throws OrderCancelException;

	/**
	 * @param orderCode
	 * @param pos
	 * @return
	 */
	public boolean sendOrderReturnNotification(String orderCode, AmwayCartType pos);

	SearchPageData<OrderHistoryData> getPagedOrderHistoryForBatch(PageableData pageableData, String batch_id);

	SearchPageData<OrderHistoryData> getPagedOrderHistoryForPOS(PageableData pageableData, String pickupStore, Date startDate, Date endDate);
}
