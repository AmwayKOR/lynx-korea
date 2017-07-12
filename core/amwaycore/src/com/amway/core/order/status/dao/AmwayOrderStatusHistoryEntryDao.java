/**
 *
 */
package com.amway.core.order.status.dao;

import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;

import com.amway.core.model.AmwayOrderStatusHistoryEntryModel;


/**
 * Data access to {@link com.amway.core.model.AmwayOrderStatusHistoryEntryModel}
 */
public interface AmwayOrderStatusHistoryEntryDao extends GenericDao<AmwayOrderStatusHistoryEntryModel>
{
	/**
	 * Method to find the order status transitions.
	 *
	 * @param ordeCode
	 * @return List<AmwayOrderStatusHistoryEntryModel>
	 */
	List<AmwayOrderStatusHistoryEntryModel> findOrderStatusTransitions(final String ordeCode);
}
