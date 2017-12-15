/**
 *
 */
package com.amway.apac.core.order.consignment.service.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.order.consignment.service.AmwayApacConsignmentService;
import com.amway.core.order.consignment.service.impl.DefaultAmwayConsignmentService;


/**
 * Service Implementation of features related to Consignment Creation
 */
public class DefaultAmwayApacConsignmentService extends DefaultAmwayConsignmentService implements AmwayApacConsignmentService
{

	private BusinessProcessService businessProcessService;
	private ModelService modelService;

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
		final AtomicLong index = new AtomicLong();
		final List<AbstractOrderEntryModel> remainingEntries = new ArrayList<>();
		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			if (entry.getDispositionCode().equals(InStockStatus.BACKORDER))
			{
				final char prefixCode = 'a';
				final ConsignmentModel consignment = createConsignment(order,
						prefixCode + order.getCode() + '_' + index.getAndIncrement(), Arrays.asList(entry));
				final ConsignmentProcessModel consignmentProcess = businessProcessService.createProcess(consignment.getCode(),
						"consignment-backorder-process");
				consignmentProcess.setConsignment(consignment);
				getModelService().save(consignmentProcess);
				businessProcessService.startProcess(consignmentProcess);
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

	/**
	 * @param businessProcessService
	 *           the businessProcessService to set
	 */
	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

}
