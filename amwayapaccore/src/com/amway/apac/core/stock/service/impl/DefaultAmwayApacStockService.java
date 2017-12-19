/**
 *
 */
package com.amway.apac.core.stock.service.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import com.amway.apac.core.stock.dao.AmwayApacStockLevelDao;
import com.amway.apac.core.stock.service.AmwayApacStockService;
import com.amway.core.stock.service.impl.DefaultAmwayStockService;


/**
 * Class to provide default implementations to AmwayApacStock service methods
 */
public class DefaultAmwayApacStockService extends DefaultAmwayStockService implements AmwayApacStockService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacStockService.class.getName());

	private AmwayApacStockLevelDao amwayApacStockLevelDao;

	/**
	 * @param product
	 *           -The product for which the stock is to be updated
	 * @param warehouse
	 *           -The WH for which the stock is to updated
	 * @param amount
	 *           -The actual amount that shall be updated into the stock
	 *
	 */
	@Override
	public void updateAvailableAmount(final ProductModel product, final WarehouseModel warehouse, final int amount)
	{
		Validate.notNull(product, "Parameter productCode cannot be null!");
		Validate.notNull(warehouse, "Parameter warehouse cannot be null!");
		final StockLevelModel currentStockLevel = checkAndGetStockLevel(product, warehouse, product.getCode());

		LOG.info("Updating stock amount : " + amount + " for product " + product.getCode() + " and skuId " + product.getCode());
		getAmwayApacStockLevelDao().updateAvailableAmount(currentStockLevel, amount);
		clearCacheForItem(currentStockLevel);
	}

	/**
	 * @return the amwayApacStockLevelDao
	 */
	public AmwayApacStockLevelDao getAmwayApacStockLevelDao()
	{
		return amwayApacStockLevelDao;
	}

	/**
	 * @param amwayApacStockLevelDao
	 *           the amwayApacStockLevelDao to set
	 */
	public void setAmwayApacStockLevelDao(final AmwayApacStockLevelDao amwayApacStockLevelDao)
	{
		this.amwayApacStockLevelDao = amwayApacStockLevelDao;
	}


}
