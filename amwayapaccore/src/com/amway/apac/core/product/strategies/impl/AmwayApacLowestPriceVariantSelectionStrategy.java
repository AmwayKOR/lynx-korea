package com.amway.apac.core.product.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.variants.model.VariantProductModel;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.model.AmwayApacVariantProductModel;
import com.amway.apac.core.product.strategies.AmwayApacPrimaryVariantSelectionStrategy;
import com.amway.apac.core.search.solrfacetsearch.providers.AmwayApacBusinessVolumeValueProvider;


/**
 * Strategy Implementation for {@link AmwayApacPrimaryVariantSelectionStrategy} to evaluate Lowest Price variant.
 *
 * @author Shubham Goyal
 */
public class AmwayApacLowestPriceVariantSelectionStrategy implements AmwayApacPrimaryVariantSelectionStrategy
{
	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacBusinessVolumeValueProvider.class);

	private PriceService netPriceService;

	@Override
	public AmwayApacVariantProductModel getPrimaryVariant(final ProductModel baseProduct)
	{
		AmwayApacVariantProductModel lowestPriceVariant = null;
		if (CollectionUtils.isNotEmpty(baseProduct.getVariants()))
		{
			lowestPriceVariant = (AmwayApacVariantProductModel) baseProduct.getVariants().iterator().next();
			PriceInformation lowestPriceVariantPrice = getNetPriceService().getPriceInformationsForProduct(lowestPriceVariant)
					.get(0);
			for (final VariantProductModel variant : baseProduct.getVariants())
			{
				final PriceInformation variantPrice = getNetPriceService().getPriceInformationsForProduct(variant).get(0);
				if (lowestPriceVariantPrice.getPriceValue().getValue() > variantPrice.getPriceValue().getValue())
				{
					lowestPriceVariantPrice = variantPrice;
					lowestPriceVariant = (AmwayApacVariantProductModel) variant;
				}
			}
		}
		if (LOGGER.isInfoEnabled() && null != lowestPriceVariant)
		{
			LOGGER.info(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT)
					.append("Lowest Price primary variant for base product with code [").append(baseProduct.getCode())
					.append("] is variant product with code [").append(lowestPriceVariant.getCode()).append("]").toString());
		}
		return lowestPriceVariant;
	}

	/**
	 * @return the netPriceService
	 */
	public PriceService getNetPriceService()
	{
		return netPriceService;
	}

	/**
	 * @param netPriceService
	 *           the netPriceService to set
	 */
	@Required
	public void setNetPriceService(final PriceService netPriceService)
	{
		this.netPriceService = netPriceService;
	}

}
