/**
 *
 */
package com.amway.apac.core.backorder.dao;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Date;
import java.util.List;

import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;



/**
 * Data access to {@link com.amway.core.model.AmwayBackOrderModel}
 *
 * @author ankushbhatia
 */
public interface AmwayApacBackOrderDao
{

	/**
	 * Find all the AmwayBackOrder for a particular status, warehouse and product in created order(FIFO)
	 *
	 * @param status
	 * @param warehouse
	 * @param product
	 * @param isAscending
	 * @return List<AmwayBackOrderModel>
	 */
	List<AmwayBackOrderModel> getBackOrders(AmwayBackOrderStatus status, WarehouseModel warehouse, ProductModel product,
			boolean isAscending, final BaseSiteModel baseSite);

	/**
	 * Find all the AmwayBackOrder for a particular status, older than provided date
	 *
	 * @param status
	 * @param Date
	 * @return List<AmwayBackOrderModel>
	 */
	List<AmwayBackOrderModel> getBackOrdersForExpiring(final String status, final Date date);
}
