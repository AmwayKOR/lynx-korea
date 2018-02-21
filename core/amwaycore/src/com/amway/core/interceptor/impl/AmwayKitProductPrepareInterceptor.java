package com.amway.core.interceptor.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashSet;
import java.util.Set;

import com.amway.core.enums.AmwayKitProductType;
import com.amway.core.model.AmwayKitProductModel;


/**
 *
 * Prepare Interceptor for Amway Kit Product
 *
 */
public class AmwayKitProductPrepareInterceptor implements PrepareInterceptor<AmwayKitProductModel>
{
	private ModelService modelService;
	private WarehouseService warehouseService;

	/**
	 *
	 * @param arg0
	 * @param arg1
	 * @throws InterceptorException
	 */
	@Override
	public void onPrepare(final AmwayKitProductModel arg0, final InterceptorContext arg1) throws InterceptorException
	{
		if (AmwayKitProductType.BUNDLED.equals(arg0.getType()))
		{
			final Set<StockLevelModel> stockLevel = new HashSet<StockLevelModel>();
			for (final WarehouseModel warehouseModel : warehouseService.getDefWarehouse())
			{
				final StockLevelModel stockLevelModel = modelService.create(StockLevelModel.class);

				stockLevelModel.setProductCode(arg0.getCode());
				stockLevelModel.setWarehouse(warehouseModel);
				stockLevelModel.setInStockStatus(InStockStatus.NOTSPECIFIED);
				stockLevelModel.setAvailable(100);
				stockLevelModel.setOverSelling(0);
				stockLevelModel.setMaxPreOrder(1);
				stockLevelModel.setReserved(0);
				stockLevelModel.setPreOrder(0);
				stockLevelModel.setMaxStockLevelHistoryCount(-1);
				stockLevelModel.setSkuId(arg0.getCode());

				modelService.save(stockLevelModel);
				stockLevel.add(stockLevelModel);

			}
			arg0.setStockLevels(stockLevel);
		}
	}

	/**
	 *
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 *
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 *
	 * @return warehouseService
	 */
	public WarehouseService getWarehouseService()
	{
		return warehouseService;
	}

	/**
	 *
	 * @param warehouseService
	 *           the warehouseService to set
	 */
	public void setWarehouseService(final WarehouseService warehouseService)
	{
		this.warehouseService = warehouseService;
	}

}