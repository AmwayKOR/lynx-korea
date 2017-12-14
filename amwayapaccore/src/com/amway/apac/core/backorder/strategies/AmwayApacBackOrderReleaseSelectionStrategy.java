/**
 *
 */
package com.amway.apac.core.backorder.strategies;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.List;

import com.amway.core.model.AmwayBackOrderModel;


/**
 * AmwayBackOrder release selection strategy
 *
 * @author ankushbhatia
 *
 */
public interface AmwayApacBackOrderReleaseSelectionStrategy
{

	/**
	 * Select AmwayBackOrders for release based upon stockLevel else return all.
	 *
	 * @param stockLevels
	 * @return List<AmwayBackOrderModel>
	 */
	public List<AmwayBackOrderModel> getBackOrders(final List<StockLevelModel> stockLevels);

}
