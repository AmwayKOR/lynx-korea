/**
 *
 */
package com.amway.core.stock.dao;

import de.hybris.platform.commerceservices.stock.dao.CommerceWarehouseDao;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.List;


/**
 * 
 * Interface for amway commerce warhouse dao.
 * 
 */
public interface AmwayCommerceWarehouseDao extends CommerceWarehouseDao
{
	/**
	 * This method returns best stocklevelModel on basis of productModel and baseStoreModel.
	 * 
	 * @param productModel
	 * @param baseStoreModel
	 * @param relaxMode
	 * @return StockLevelModel
	 */
	List<StockLevelModel> getStockLevelsForProductAndBaseStore(ProductModel productModel, BaseStoreModel baseStoreModel,
			boolean relaxMode);

	/**
	 * This method returns best stocklevelModel on basis of productModel and pointOfServiceModel.
	 * 
	 * @param productModel
	 * @param pointOfServiceModel
	 * @param relaxMode
	 * @return StockLevelModel
	 */
	List<StockLevelModel> getStockLevelsForProductAndPOS(ProductModel productModel, PointOfServiceModel pointOfServiceModel,
			boolean relaxMode);

	/**
	 * This method returns best stocklevelModel on basis of productModel and warehouseModel.
	 * 
	 * @param productModel
	 * @param warehouses
	 * @return StockLevelModel
	 */
	List<StockLevelModel> getStockLevelsForProductAndWarehouses(ProductModel productModel, List<WarehouseModel> warehouses);

	/**
	 * This method returns web warehouses on basis of baseStore.
	 * 
	 * @param baseStore
	 * @return WarehouseModel
	 */
	List<WarehouseModel> getWebWarehousesForBaseStore(final BaseStoreModel baseStore);

	/**
	 * This method returns pos warehouses on basis of baseStore.
	 * 
	 * @param baseStore
	 * @return WarehouseModel
	 */
	List<WarehouseModel> getPosWarehousesForBaseStore(final BaseStoreModel baseStore);
}
