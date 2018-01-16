/**
 *
 */
package com.amway.apac.core.backorder.strategies;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;


/**
 * AmwayBackOrder selection strategy
 *
 * @author ankushbhatia
 *
 */
public interface AmwayApacBackOrderSelectionStrategy
{

	/**
	 * Select AmwayBackOrders for release based upon stockLevel
	 *
	 * @param stockLevels
	 *           List of Stock Levels the AmwayBackOrders belongs to
	 * @return List of AmwayBackOrders corresponding of each stock level
	 */
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final List<StockLevelModel> stockLevels);

	/**
	 * Select AmwayBackOrders for expiring based upon current date
	 *
	 * @param status
	 *           AmwayBackOrderStatus EG. ACTIVE, CANCELLED etc.
	 * @param date
	 *           Current date to compare AmwayBackOrder creation time
	 * @return List of AmwayBackOrders for status and before current date
	 */
	public List<AmwayBackOrderModel> getBackOrdersForExpiring(final AmwayBackOrderStatus status, final Date date);

	/**
	 * Select All AmwayBackOrders for release for particular BaseStore
	 *
	 * @param baseStore
	 * @return Map of AmwayBackOrders and StockLevel where each StockLevel is mapped to list of AmwayBackOrders
	 */
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final BaseStoreModel baseStore);


}
