/**
 *
 */
package com.amway.core.stock.strategy;

import de.hybris.platform.commerceservices.stock.strategies.WarehouseSelectionStrategy;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;


/**
 * Interface for stock level of Amwaywarehouse.
 */
public interface AmwayWarehouseSelectionStrategy extends WarehouseSelectionStrategy
{


}
