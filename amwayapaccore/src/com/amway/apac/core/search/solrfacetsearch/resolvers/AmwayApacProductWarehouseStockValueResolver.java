package com.amway.apac.core.search.solrfacetsearch.resolvers;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.stock.services.AmwayApacStockService;
import com.amway.apac.core.stock.strategies.AmwayApacCommerceStockLevelStatusStrategy;
import com.amway.core.model.AmwayKitProductModel;


/**
 * Resolver to index the stockStatus for all the warehouses.
 *
 * @author Shubham Goyal
 */
public class AmwayApacProductWarehouseStockValueResolver extends AbstractValueResolver<ProductModel, Object, Object>
{
	private FieldNameProvider fieldNameProvider;
	private AmwayApacStockService amwayApacStockService;
	private AmwayApacCommerceStockLevelStatusStrategy amwayApacCommerceStockLevelStatusStrategy;


	@Override
	protected void addFieldValues(final InputDocument document, final IndexerBatchContext paramIndexerBatchContext,
			final IndexedProperty indexedProperty, final ProductModel product,
			final de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver.ValueResolverContext<Object, Object> paramValueResolverContext)
			throws FieldValueProviderException
	{
		final Collection<StockLevelModel> stockLevelsForProduct = this.getAmwayApacStockService().getAllStockLevels(product);
		for (final StockLevelModel stockLevel : stockLevelsForProduct)
		{
			final WarehouseModel warehouseModel = stockLevel.getWarehouse();
			if (null != warehouseModel)
			{
				StockLevelStatus stockLevelStatus = StockLevelStatus.OUTOFSTOCK;
				if (CollectionUtils.isNotEmpty(warehouseModel.getBaseStores()))
				{
					if ((product instanceof AmwayKitProductModel) || (null != product.getVariantType()))
					{
						stockLevelStatus = getAmwayApacStockService().getProductStatus(product, warehouseModel);
					}
					else
					{
						stockLevelStatus = getAmwayApacCommerceStockLevelStatusStrategy().checkStatus(stockLevel);
					}
				}
				final String warehouseCode = stockLevel.getWarehouse().getPk().toString();
				final Collection<String> fieldNames = getFieldNames(indexedProperty, warehouseCode);
				for (final String fieldName : fieldNames)
				{
					document.addField(fieldName, stockLevelStatus.getCode());
				}
			}
		}
	}

	protected Collection<String> getFieldNames(final IndexedProperty indexedProperty, final String warehouseCode)
			throws FieldValueProviderException
	{
		Collection<String> fieldNames = new ArrayList<>();
		if (StringUtils.isNotEmpty(warehouseCode))
		{
			fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, warehouseCode);
		}
		return fieldNames;
	}


	/**
	 * @return the fieldNameProvider
	 */
	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	/**
	 * @param fieldNameProvider
	 *           the fieldNameProvider to set
	 */
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	/**
	 * @return the amwayApacStockService
	 */
	public AmwayApacStockService getAmwayApacStockService()
	{
		return amwayApacStockService;
	}

	/**
	 * @param amwayApacStockService
	 *           the amwayApacStockService to set
	 */
	@Required
	public void setAmwayApacStockService(final AmwayApacStockService amwayApacStockService)
	{
		this.amwayApacStockService = amwayApacStockService;
	}

	/**
	 * @return the amwayApacCommerceStockLevelStatusStrategy
	 */
	public AmwayApacCommerceStockLevelStatusStrategy getAmwayApacCommerceStockLevelStatusStrategy()
	{
		return amwayApacCommerceStockLevelStatusStrategy;
	}

	/**
	 * @param amwayApacCommerceStockLevelStatusStrategy
	 *           the amwayApacCommerceStockLevelStatusStrategy to set
	 */
	@Required
	public void setAmwayApacCommerceStockLevelStatusStrategy(
			final AmwayApacCommerceStockLevelStatusStrategy amwayApacCommerceStockLevelStatusStrategy)
	{
		this.amwayApacCommerceStockLevelStatusStrategy = amwayApacCommerceStockLevelStatusStrategy;
	}

}

