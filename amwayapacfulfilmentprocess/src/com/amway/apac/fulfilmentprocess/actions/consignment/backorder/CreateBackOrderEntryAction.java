package com.amway.apac.fulfilmentprocess.actions.consignment.backorder;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;



/**
 * This action is used to create a back order model as a part of the consignment process
 */
public class CreateBackOrderEntryAction extends AbstractSimpleDecisionAction<ConsignmentProcessModel>
{
	private static final Logger LOG = Logger.getLogger(CreateBackOrderEntryAction.class);

	/**
	 * Creates a BackOrder model entry
	 */
	@Override
	public Transition executeAction(final ConsignmentProcessModel process)
	{
		final ConsignmentModel consignment = process.getConsignment();
		if (consignment != null)
		{
			final List<AmwayBackOrderModel> backOrders = new ArrayList<>();
			final AbstractOrderModel originalOrder = consignment.getOrder();
			consignment.getConsignmentEntries().stream().forEach(consignmentEntry -> {
				final AmwayBackOrderModel amwayBackOrderModel = getModelService().create(AmwayBackOrderModel.class);
				amwayBackOrderModel.setConsignment(consignment);
				amwayBackOrderModel.setOrderingAbo(originalOrder.getUser());
				amwayBackOrderModel.setStatus(AmwayBackOrderStatus.ACTIVE);
				amwayBackOrderModel.setProduct(consignmentEntry.getOrderEntry().getProduct());
				amwayBackOrderModel.setReleaseByDate(getReleaseDate(originalOrder.getStore()));
				amwayBackOrderModel.setWarehouse(consignment.getWarehouse());
				amwayBackOrderModel.setBaseStore(originalOrder.getStore());
				if (originalOrder instanceof OrderModel)
				{
					amwayBackOrderModel.setOriginalOrder((OrderModel) originalOrder);
				}
				backOrders.add(amwayBackOrderModel);
			});
			if (CollectionUtils.isNotEmpty(backOrders))
			{
				getModelService().saveAll(backOrders);
				LOG.info(String
						.format(
								"Successfully created [%s] BackOrder entry for original order : [%s], consignment no. : [%s] , warehouse : [%s]",
								String.valueOf(backOrders.size()), process.getConsignment().getOrder().getCode(), process
										.getConsignment().getCode(), process.getConsignment().getWarehouse().getCode()));
			}
			return Transition.OK;
		}
		LOG.error("Process has no consignment" + process.getCode());
		return Transition.NOK;
	}

	/**
	 * @param baseStoreModel
	 * @return Date- The release date by which a backorder should be fulfilled
	 */
	private Date getReleaseDate(final BaseStoreModel baseStoreModel)
	{
		final int backOrderFulfillmentPeriod = baseStoreModel.getBackOrderFulfillmentPeriod() != null ? baseStoreModel
				.getBackOrderFulfillmentPeriod().intValue() : 0;
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, backOrderFulfillmentPeriod);
		return calendar.getTime();
	}

	@Override
	public Set<String> getTransitions()
	{
		return Transition.getStringValues();
	}
}
