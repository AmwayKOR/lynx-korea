package com.amway.apac.core.search.solrfacetsearch.resolvers;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.HUNDRED_INT;

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

import com.amway.apac.core.product.strategies.AmwayApacPrimaryVariantSelectionStrategy;


/**
 * Overriding the AmwayCore Implementation to get primary variant values for base variant product indexing.
 *
 * @author Shubham Goyal
 */
public class AmwayApacProductPricesValueResolver extends ProductPricesValueResolver
{
	/**
	 * Logger instance to record events at class level
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(AmwayApacProductPricesValueResolver.class);

	private AmwayApacPrimaryVariantSelectionStrategy amwayApacPrimaryVariantSelectionStrategy;

	@Override
	protected List<PriceInformation> loadPriceInformations(final Collection<IndexedProperty> indexedProperties,
			final ProductModel product)
	{
		return getPriceService().getPriceInformationsForProduct(getResolvedProduct(product));
	}


	private ProductModel getResolvedProduct(final ProductModel product)
	{
		ProductModel resolvedProduct = product;
		if (CollectionUtils.isNotEmpty(product.getVariants()))
		{
			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(HUNDRED_INT).append("The product with code [").append(product.getCode())
						.append("] is a base product, fetching primary variant to display the price.").toString());
			}
			resolvedProduct = getAmwayApacPrimaryVariantSelectionStrategy().getPrimaryVariant(product);
		}
		return resolvedProduct;
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

