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

	private BusinessProcessService businessProcessService;

	private ModelService modelService;

	private static final char PREFIX_CODE = 'a';

	private static final String PROCESS_CODE_SEPARATOR = "-";

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
						final String consignmentBackOrderProcessDefName = "consignment-backorder-process";
						final String consignmentBackOrderProcessCode = new StringBuilder(consignmentBackOrderProcessDefName)
								.append(PROCESS_CODE_SEPARATOR).append(consignment.getCode()).append(PROCESS_CODE_SEPARATOR)
								.append(System.currentTimeMillis()).toString();
						final ConsignmentProcessModel consignmentBackOrderProcess = businessProcessService.createProcess(
								consignmentBackOrderProcessCode, consignmentBackOrderProcessDefName);
						LOG.info(String.format("Created consignment with code : [%s] for order with code : [%s] ",
								consignment.getCode(), consignment.getOrder().getCode()));
						consignmentBackOrderProcess.setConsignment(consignment);
						getModelService().save(consignmentBackOrderProcess);
						businessProcessService.startProcess(consignmentBackOrderProcess);
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
	 *            Creates a single consignment for all the remaining entries in order
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
		super.setModelService(modelService);
		this.modelService = modelService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

}
