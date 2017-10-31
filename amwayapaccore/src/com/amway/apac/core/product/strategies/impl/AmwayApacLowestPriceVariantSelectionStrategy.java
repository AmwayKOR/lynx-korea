package com.amway.apac.core.product.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.model.AmwayApacVariantProductModel;
import com.amway.apac.core.product.strategies.AmwayApacPrimaryVariantSelectionStrategy;
import com.amway.apac.core.search.solrfacetsearch.providers.AmwayApacBusinessVolumeValueProvider;


/**
 *
 */
public class AmwayApacLowestPriceVariantSelectionStrategy implements AmwayApacPrimaryVariantSelectionStrategy
{
	private static final Logger LOG = LoggerFactory.getLogger(AmwayApacBusinessVolumeValueProvider.class);

	private PriceService netPriceService;

	@Override
	public AmwayApacVariantProductModel getPrimaryVariant(final ProductModel baseProduct)
	{
		AmwayApacVariantProductModel lowestPriceVariant = null;
		if (CollectionUtils.isNotEmpty(baseProduct.getVariants()))
		{
			for (final VariantProductModel variant : baseProduct.getVariants())
			{
				if (null == lowestPriceVariant)
				{
					lowestPriceVariant = (AmwayApacVariantProductModel) variant;
				}
				else if (hasLowerPrice(lowestPriceVariant, variant))
				{
					lowestPriceVariant = (AmwayApacVariantProductModel) variant;
				}
			}
		}
		if (LOG.isInfoEnabled() && null != lowestPriceVariant)
		{
			LOG.info(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT)
					.append("Lowest Price primary variant for base product with code [").append(baseProduct.getCode())
					.append("] is variant product with code [").append(lowestPriceVariant.getCode()).append("]").toString());
		}
		return lowestPriceVariant;
	}

	private boolean hasLowerPrice(final AmwayApacVariantProductModel lowestPriceVariant, final VariantProductModel currentVariant)
	{
		final Comparator<VariantProductModel> priceComparator = new Comparator<VariantProductModel>()
		{
			@Override
			public int compare(final VariantProductModel lowestPriceVariant, final VariantProductModel currentVariant)
			{
				int result = 0;
				final List<PriceInformation> lowestPriceVariantPrice = getNetPriceService()
						.getPriceInformationsForProduct(lowestPriceVariant);
				final List<PriceInformation> currentVariantPrice = getNetPriceService()
						.getPriceInformationsForProduct(currentVariant);
				if (lowestPriceVariantPrice.get(0).getPriceValue().getValue() > currentVariantPrice.get(0).getPriceValue().getValue())
				{
					result = 1;
				}
				return result;
			}
		};

		return priceComparator.compare(lowestPriceVariant, currentVariant) > 0;
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
