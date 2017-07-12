/**
 *
 */
package com.amway.core.stock.strategy.impl;

import de.hybris.platform.warehousing.atp.strategy.impl.WarehousingWarehouseSelectionStrategy;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.amway.core.stock.strategy.AmwayWarehouseSelectionStrategy;


/**
 * Default Implementation
 */
public class DefaultAmwayWarehouseSelectionStrategy extends WarehousingWarehouseSelectionStrategy
		implements AmwayWarehouseSelectionStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayWarehouseSelectionStrategy.class);




	@Deprecated
	public StockLevelModel getBestStockLevel(final ProductModel productModel, final BaseStoreModel baseStoreModel,
			final AbstractOrderModel abstractOrderModel, final long qty, final boolean relaxMode)
			throws InsufficientStockLevelException
	{
		return null;
	}

	@Deprecated
	public StockLevelModel getBestStockLevel(final ProductModel productModel, final PointOfServiceModel pointOfServiceModel,
			final AbstractOrderModel abstractOrderModel, final long qty, final boolean relaxMode)
			throws InsufficientStockLevelException

	{
		return null;
	}
}
