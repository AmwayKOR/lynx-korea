package com.amway.apac.core.backorder.dao;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Date;
import java.util.List;

import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;


/**
 * Data access to {@link AmwayBackOrderModel}
 *
 * @author ankushbhatia
 */
public interface AmwayApacBackOrderDao
{

	/**
	 * Find all the AmwayBackOrder for a particular Status, Warehouse, Product or BaseStore.
	 *
	 * @param status
	 *           AmwayBackOrderStatus EG. ACTIVE, CANCELLED etc. <Mandatory>
	 * @param warehouse
	 *           the warehouse the AmwayBackOrder belong to. <NonMandatory>
	 * @param product
	 *           the product the AmwayBackOrder belong to. <NonMandatory>
	 * @param baseStore
	 *           the baseStore the AmwayBackOrder belong to. <NonMandatory>
	 * @return List<AmwayBackOrderModel>
	 */
	List<AmwayBackOrderModel> getBackOrders(final AmwayBackOrderStatus status, final WarehouseModel warehouse,
			final ProductModel product, BaseStoreModel baseStore);

	/**
	 * Find all the AmwayBackOrder for a particular Status, older than provided date.
	 *
	 * @param status
	 *           AmwayBackOrderStatus EG. ACTIVE, CANCELLED etc. <Mandatory>
	 * @param date
	 *           for getting AmwayBackOrder before the date. <Mandatory>
	 * @return All the AmwayBackOrders for status and before date.
	 */
	List<AmwayBackOrderModel> getBackOrdersByStatusAndDate(final AmwayBackOrderStatus status, final Date date);

	/**
	 * Fetches the AmwayBackOrder for a given Consignment.
	 *
	 * @param consignment
	 *           the Consignment the AmwayBackOrder belong to. <Mandatory>
	 * @return AmwayBackOrder for particular Consignment
	 */
	AmwayBackOrderModel getBackOrdersByConsignment(final ConsignmentModel consignment);
}
