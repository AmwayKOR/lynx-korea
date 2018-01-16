/**
 *
 */
package com.amway.apac.core.order.consignment.service.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.order.consignment.service.AmwayApacConsignmentService;
import com.amway.core.order.consignment.service.impl.DefaultAmwayConsignmentService;


/**
 * Service Implementation of features related to Consignment Creation
 */
public class DefaultAmwayApacConsignmentService extends DefaultAmwayConsignmentService implements AmwayApacConsignmentService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacConsignmentService.class);

	private ModelService modelService;

	private static final char PREFIX_CODE = 'a';

	/**
	 * {@inheritDoc}
	 *
	 * This method creates multiple consignment on the basis of order entry statuses
	 *
	 * @param order
	 * @return List of consignments
	 */
	@Override
	public List<ConsignmentModel> createConsignments(final AbstractOrderModel order) throws ConsignmentCreationException
	{
		final List<ConsignmentModel> allConsignments = new ArrayList<>();
		if (Objects.nonNull(order))
		{
			final AtomicLong index = new AtomicLong();
			final List<AbstractOrderEntryModel> remainingEntries = new ArrayList<>();
			for (final AbstractOrderEntryModel entry : order.getEntries())
			{
				if (null != entry.getDispositionCode() && entry.getDispositionCode().equals(InStockStatus.BACKORDER))
				{
					final ConsignmentModel consignment = createConsignment(order,
							PREFIX_CODE + order.getCode() + '_' + index.getAndIncrement(), Arrays.asList(entry));
					if (consignment != null)
					{
						LOG.info(String.format("Created consignment with code : [%s] for order with code : [%s] ",
								consignment.getCode(), consignment.getOrder().getCode()));
						allConsignments.add(consignment);
					}
				}
				else
				{
					remainingEntries.add(entry);
				}
			}
			createConsignmentForRemainingEntries(order, remainingEntries, allConsignments);
		}
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
			allConsignments.add(createConsignment(order, PREFIX_CODE + order.getCode(), remainingEntries));
		}
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	@Override
	public void setModelService(final ModelService modelService)
	{
		super.setModelService(modelService);
		this.modelService = modelService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

}
