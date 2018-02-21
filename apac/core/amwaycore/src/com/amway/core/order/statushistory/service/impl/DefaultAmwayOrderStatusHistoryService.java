/**
 *
 */
package com.amway.core.order.statushistory.service.impl;

import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

import org.springframework.util.Assert;

import com.amway.core.model.AmwayOrderStatusHistoryEntryModel;
import com.amway.core.order.status.dao.AmwayOrderStatusHistoryEntryDao;
import com.amway.core.order.statushistory.service.AmwayOrderStatusHistoryService;


/**
 * Service to retrieve the details of AmwayOrderStatusHistoryEntryModel
 */
public class DefaultAmwayOrderStatusHistoryService extends AbstractBusinessService implements AmwayOrderStatusHistoryService
{


	private AmwayOrderStatusHistoryEntryDao amwayOrderStatusHistoryEntryDao;

	/**
	 * @return amwayOrderStatusHistoryEntryDao
	 */
	public AmwayOrderStatusHistoryEntryDao getAmwayOrderStatusHistoryEntryDao()
	{
		return amwayOrderStatusHistoryEntryDao;
	}

	/**
	 * @param amwayOrderStatusHistoryEntryDao the amwayOrderStatusHistoryEntryDao to set
	 */
	public void setAmwayOrderStatusHistoryEntryDao(final AmwayOrderStatusHistoryEntryDao amwayOrderStatusHistoryEntryDao)
	{
		this.amwayOrderStatusHistoryEntryDao = amwayOrderStatusHistoryEntryDao;
	}

	/**
	 * Method to retrieve the OrderStatus history of an order
	 *
	 * @param orderCode
	 */
	@Override
	public List<AmwayOrderStatusHistoryEntryModel> getStatusHistory(final String orderCode)
	{
		Assert.notNull(orderCode, "Order Code cannot be null");
		return getAmwayOrderStatusHistoryEntryDao().findOrderStatusTransitions(orderCode);
	}
}
