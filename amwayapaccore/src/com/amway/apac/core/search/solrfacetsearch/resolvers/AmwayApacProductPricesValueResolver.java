/**
 *
 */
package com.amway.apac.core.search.solrfacetsearch.resolvers;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductPricesValueResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.product.strategies.AmwayApacPrimaryVariantSelectionStrategy;


/**
 *
 */
public class AmwayApacProductPricesValueResolver extends ProductPricesValueResolver
{

	private final Logger LOG = LoggerFactory.getLogger(AmwayApacProductPricesValueResolver.class);

	private AmwayApacPrimaryVariantSelectionStrategy amwayApacPrimaryVariantSelectionStrategy;

	@Override
	protected List<PriceInformation> loadPriceInformations(final Collection<IndexedProperty> indexedProperties,
			final ProductModel product)
	{
		ProductModel productToIndex = product;
		if (CollectionUtils.isNotEmpty(productToIndex.getVariants()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT).append("The product with code [")
						.append(product.getCode()).append("] is a base product, fetching primary variant to display the price.")
						.toString());
			}
			productToIndex = getAmwayApacPrimaryVariantSelectionStrategy().getPrimaryVariant(productToIndex);
		}

		return getPriceService().getPriceInformationsForProduct(productToIndex);
	}

	/**
	 * @return the amwayApacPrimaryVariantSelectionStrategy
	 */
	public AmwayApacPrimaryVariantSelectionStrategy getAmwayApacPrimaryVariantSelectionStrategy()
	{
		return amwayApacPrimaryVariantSelectionStrategy;
	}

	/**
	 * @param amwayApacPrimaryVariantSelectionStrategy
	 *           the amwayApacPrimaryVariantSelectionStrategy to set
	 */
	@Required
	public void setAmwayApacPrimaryVariantSelectionStrategy(
			final AmwayApacPrimaryVariantSelectionStrategy amwayApacPrimaryVariantSelectionStrategy)
	{
		this.amwayApacPrimaryVariantSelectionStrategy = amwayApacPrimaryVariantSelectionStrategy;
	}
}

