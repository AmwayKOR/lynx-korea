/**
 *
 */
package com.amway.apac.core.backorder.strategies;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	 * @return Map<StockLevelModel,List<AmwayBackOrderModel>
	 */
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final List<StockLevelModel> stockLevels);

	public List<AmwayBackOrderModel> getBackOrdersForExpiring(final String status, final Date date);


}
