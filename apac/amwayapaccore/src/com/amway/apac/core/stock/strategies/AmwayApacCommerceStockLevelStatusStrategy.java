package com.amway.apac.core.stock.strategies;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.stock.strategy.StockLevelStatusStrategy;

import java.util.Set;


/**
 * @author Shubham Goyal
 */
public interface AmwayApacCommerceStockLevelStatusStrategy extends StockLevelStatusStrategy
{

	/**
	 * Evaluates and returns StockLevelStatus based on the sets of Stock Statuses of major and minor kit entries of Kit
	 * Product.
	 *
	 * @param majorItemsStatusSet
	 *           Set of StockLevelStatus of all major products.
	 * @param minorItemsStatusSet
	 *           Set of StockLevelStatus of all minor products.
	 * @return {@link StockLevelStatus} after the evaluation.
	 */
	StockLevelStatus evaluateKitProductStockStatus(Set<StockLevelStatus> majorItemsStatusSet,
			Set<StockLevelStatus> minorItemsStatusSet);

}
