/**
 *
 */
package com.amway.apac.core.backorder.strategies;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
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

	/**
	 * Select AmwayBackOrders for expiring based upon current date
	 *
	 * @param status
	 * @param date
	 * @return List<AmwayBackOrderModel>
	 */
	public List<AmwayBackOrderModel> getBackOrdersForExpiring(final String status, final Date date);

	/**
	 * Select All AmwayBackOrders for release
	 *
	 * @return Map<StockLevelModel,List<AmwayBackOrderModel>
	 */
	public Map<StockLevelModel, List<AmwayBackOrderModel>> getBackOrdersForRelease(final BaseSiteModel baseSite);


}
