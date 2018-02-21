package com.amway.core.stock.dao.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.commerceservices.stock.dao.impl.DefaultCommerceWarehouseDao;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.core.stock.dao.AmwayCommerceWarehouseDao;


/**
 * 
 * Default implementation
 * 
 */
public class DefaultAmwayCommerceWarehouseDao extends DefaultCommerceWarehouseDao implements AmwayCommerceWarehouseDao
{
	/**
	 * This method returns best stocklevelModel on basis of productModel and baseStoreModel.
	 * 
	 * {@link #getStockLevelsForProductAndBaseStore(de.hybris.platform.core.model.product.ProductModel, de.hybris.platform.store.BaseStoreModel, boolean)}
	 */
	@Override
	public List<StockLevelModel> getStockLevelsForProductAndBaseStore(final ProductModel productModel,
			final BaseStoreModel baseStoreModel, final boolean relaxMode)
	{
		return getStockLevelsForProductAndWarehouses(productModel, relaxMode ? getDefaultWarehousesForBaseStore(baseStoreModel)
				: getWebWarehousesForBaseStore(baseStoreModel));
	}

	/**
	 * This method returns best stocklevelModel on basis of productModel and pointOfServiceModel.
	 * 
	 * {@link #getStockLevelsForProductAndPOS(de.hybris.platform.core.model.product.ProductModel, de.hybris.platform.storelocator.model.PointOfServiceModel , boolean)}
	 */
	@Override
	public List<StockLevelModel> getStockLevelsForProductAndPOS(final ProductModel productModel,
			final PointOfServiceModel pointOfServiceModel, final boolean relaxMode)
	{
		return getStockLevelsForProductAndWarehouses(productModel,
				relaxMode ? getPosWarehousesForBaseStore(pointOfServiceModel.getBaseStore()) : pointOfServiceModel.getWarehouses());
	}

	/**
	 * This method returns best stocklevelModel on basis of productModel and warehouseModel.
	 * 
	 * {@link #getStockLevelsForProductAndWarehouses(de.hybris.platform.core.model.product.ProductModel, java.util.List)}
	 */
	@Override
	public List<StockLevelModel> getStockLevelsForProductAndWarehouses(final ProductModel productModel,
			final List<WarehouseModel> warehouses)
	{
		ServicesUtil.validateParameterNotNull(productModel, "productModel cannot be null");
		ServicesUtil.validateParameterNotNull(warehouses, "baseStoreModel cannot be null");
		final Map params = new HashMap(2);

		params.put("productCode", productModel.getCode());
		params.put("inStockStatus", InStockStatus.FORCEOUTOFSTOCK);

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(
				"SELECT {s.pk},{s.skuId} FROM {StockLevel as s} WHERE {productCode} = ?productCode AND ({inStockStatus} is null OR {inStockStatus} != ?inStockStatus) AND {warehouse} IN (?WAREHOUSES_PARAM) GROUP BY {s.skuId},{s.pk}",
				params);
		fQuery.addQueryParameter("WAREHOUSES_PARAM", warehouses);
		fQuery.setResultClassList(Arrays.asList(StockLevelModel.class));
		final SearchResult result = search(fQuery);
		return result.getResult();
	}

	/**
	 * Default warehousese for base store.
	 * 
	 * @param baseStore
	 * 
	 */
	@Override
	public List<WarehouseModel> getDefaultWarehousesForBaseStore(final BaseStoreModel baseStore)
	{
		final List<PointOfServiceTypeEnum> posTypes = new ArrayList();
		posTypes.add(PointOfServiceTypeEnum.WAREHOUSE);
		posTypes.add(PointOfServiceTypeEnum.STORE);

		return getWarehousesForBaseStore(baseStore, posTypes);
	}

	/**
	 * to get WEB warehouses for base store.
	 * 
	 * @param baseStore
	 */
	@Override
	public List<WarehouseModel> getWebWarehousesForBaseStore(final BaseStoreModel baseStore)
	{
		final List<PointOfServiceTypeEnum> posTypes = new ArrayList();
		posTypes.add(PointOfServiceTypeEnum.WAREHOUSE);
		return getWarehousesForBaseStore(baseStore, posTypes);
	}

	/**
	 * To get POS warehouses for base store.
	 * 
	 * @param baseStore
	 */
	@Override
	public List<WarehouseModel> getPosWarehousesForBaseStore(final BaseStoreModel baseStore)
	{
		final List<PointOfServiceTypeEnum> posTypes = new ArrayList();
		posTypes.add(PointOfServiceTypeEnum.STORE);
		posTypes.add(PointOfServiceTypeEnum.POS);
		return getWarehousesForBaseStore(baseStore, posTypes);
	}

	protected List<WarehouseModel> getWarehousesForBaseStore(final BaseStoreModel baseStore,
			final List<PointOfServiceTypeEnum> posTypes)
	{
		ServicesUtil.validateParameterNotNull(baseStore, "baseStore cannot be null");
		ServicesUtil.validateParameterNotNull(posTypes, "posTypes cannot be null");
		final Map params = new HashMap(2);
		params.put("baseStore", baseStore);
		params.put("posTypes", posTypes);
		final StringBuilder query = new StringBuilder(
				"SELECT distinct({pw.target}) FROM {PoS2WarehouseRel as pw JOIN PointOfService as p ON {pw.source} = {p.pk}}")
				.append(" WHERE {p.type} IN (?posTypes)")
				.append(
						" AND {pw.target} IN ({{SELECT {w.pk} FROM {BaseStore2WarehouseRel as r JOIN Warehouse as w ON {r.target} = {w.pk}}")
				.append(" WHERE {r.source} = ?baseStore}})");

		return getFlexibleSearchService().search(query.toString(), params).getResult();
	}
}
