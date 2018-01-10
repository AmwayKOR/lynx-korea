/**
 *
 */
package com.amway.core.stock.service.impl;

import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.commerceservices.stock.impl.DefaultCommerceStockService;
import de.hybris.platform.warehousing.atp.services.impl.WarehousingCommerceStockService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.apache.log4j.Logger;

import com.amway.core.enums.AmwayCartType;
import com.amway.core.stock.service.AmwayCommerceStockService;
import com.amway.core.stock.service.AmwayStockService;
import com.amway.core.stock.strategy.AmwayWarehouseSelectionStrategy;
import com.amway.core.util.AmwayCartHelper;


/**
 * Default implementation
 */
public class DefaultAmwayCommerceStockService extends WarehousingCommerceStockService implements AmwayCommerceStockService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayCommerceStockService.class);
	private AmwayWarehouseSelectionStrategy amwayWarehouseSelectionStrategy;
	private AmwayStockService amwayStockService;


	/**
	 * This method is just here to support POS testing for stock reserve. Override this!
	 * @param abstractOrderModel
	 */
	@Deprecated
	public void reserve(final AbstractOrderModel abstractOrderModel)
	{
		for (final AbstractOrderEntryModel abstractOrderEntryModel : abstractOrderModel.getEntries())
		{
			try
			{
				if(abstractOrderEntryModel.getDeliveryPointOfService() != null) {
					WarehouseModel warehouse = abstractOrderEntryModel.getDeliveryPointOfService().getWarehouses().get(0);

					getAmwayStockService().reserve(abstractOrderEntryModel.getProduct(), warehouse,
						abstractOrderEntryModel.getQuantity().intValue(), abstractOrderEntryModel.getSkuVersion());
				}

			}
			catch (final InsufficientStockLevelException e)
			{
				LOG.error("Error in reserving stock for skuId " + abstractOrderEntryModel.getSkuVersion() + " on order "
						+ abstractOrderModel.getCode(), e);
			}
		}
	}


	/**
	 * @return amwayWarehouseSelectionStrategy
	 */
	public AmwayWarehouseSelectionStrategy getAmwayWarehouseSelectionStrategy()
	{
		return amwayWarehouseSelectionStrategy;
	}

	/**
	 * @param amwayWarehouseSelectionStrategy the amwayWarehouseSelectionStrategy to set
	 */
	public void setAmwayWarehouseSelectionStrategy(final AmwayWarehouseSelectionStrategy amwayWarehouseSelectionStrategy)
	{
		this.amwayWarehouseSelectionStrategy = amwayWarehouseSelectionStrategy;
	}

	/**
	 * @return amwayStockService
	 */
	public AmwayStockService getAmwayStockService()
	{
		return amwayStockService;
	}

	/**
	 * @param amwayStockService the amwayStockService to set
	 */
	public void setAmwayStockService(final AmwayStockService amwayStockService)
	{
		this.amwayStockService = amwayStockService;
	}
}