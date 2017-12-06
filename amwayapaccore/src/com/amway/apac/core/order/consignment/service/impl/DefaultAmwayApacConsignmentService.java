/**
 *
 */
package com.amway.apac.core.order.consignment.service.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.collections.CollectionUtils;

import com.amway.apac.core.order.consignment.service.AmwayApacConsignmentService;
import com.amway.core.order.consignment.service.impl.DefaultAmwayConsignmentService;


/**
 * Service Implementation of features related to Consignment Creation
 */
public class DefaultAmwayApacConsignmentService extends DefaultAmwayConsignmentService implements AmwayApacConsignmentService
{

	/**
	 * @param order
	 *
	 *           This method creates multiple consignment on the basis of order entry statuses
	 */
	@Override
	public List<ConsignmentModel> createConsignments(final AbstractOrderModel order) throws ConsignmentCreationException
	{
		final List<ConsignmentModel> allConsignments = new ArrayList<>();
		final AtomicLong index = new AtomicLong();
		final List<AbstractOrderEntryModel> remainingEntries = new ArrayList<>();
		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			if (entry.getDispositionCode().equals(InStockStatus.BACKORDER))
			{
				allConsignments
						.add(createConsignment(order, 'a' + order.getCode() + '_' + index.getAndIncrement(), Arrays.asList(entry)));
			}
			else
			{
				remainingEntries.add(entry);
			}
		}
		createConsignmentForRemainingEntries(order, remainingEntries, allConsignments);
		return allConsignments;
	}

	/**
	 * @param order
	 * @param remainingEntries
	 * @param allConsignments
	 * @throws ConsignmentCreationException
	 *
	 *            Creates the consignment for all the remaining entries in order
	 */
	private void createConsignmentForRemainingEntries(final AbstractOrderModel order,
			final List<AbstractOrderEntryModel> remainingEntries, final List<ConsignmentModel> allConsignments)
			throws ConsignmentCreationException
	{
		if (CollectionUtils.isNotEmpty(remainingEntries))
		{
			allConsignments.add(createConsignment(order, 'a' + order.getCode(), remainingEntries));
		}
	}

}
