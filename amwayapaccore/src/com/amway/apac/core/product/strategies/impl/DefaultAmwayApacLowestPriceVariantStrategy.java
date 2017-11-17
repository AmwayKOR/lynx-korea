package com.amway.apac.core.product.strategies.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.BASE_PRODUCT_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

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


/**
 * Strategy Implementation for {@link AmwayApacPrimaryVariantSelectionStrategy}. This implementation resolves the lowest
 * price variant as the primary variant.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacLowestPriceVariantStrategy implements AmwayApacPrimaryVariantSelectionStrategy
{

	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacLowestPriceVariantStrategy.class);

	private PriceService priceService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayApacVariantProductModel getPrimaryVariant(final ProductModel baseProduct)
	{
		validateParameterNotNullStandardMessage(BASE_PRODUCT_STRING, baseProduct);

		AmwayApacVariantProductModel lowestPriceVariant = null;
		if ((CollectionUtils.isNotEmpty(baseProduct.getVariants()))
				&& (baseProduct.getVariants().iterator().next() instanceof AmwayApacVariantProductModel))
		{
			lowestPriceVariant = (AmwayApacVariantProductModel) baseProduct.getVariants().iterator().next();
			PriceInformation lowestPriceVariantPrice = getPriceService().getPriceInformationsForProduct(lowestPriceVariant)
					.iterator().next();

			for (final VariantProductModel variant : baseProduct.getVariants())
			{
				if (variant instanceof AmwayApacVariantProductModel)
				{
					final PriceInformation variantPrice = getPriceService().getPriceInformationsForProduct(variant).iterator().next();
					if (lowestPriceVariantPrice.getPriceValue().getValue() > variantPrice.getPriceValue().getValue())
					{
						lowestPriceVariantPrice = variantPrice;
						lowestPriceVariant = (AmwayApacVariantProductModel) variant;
					}
				}
			}
		}
		if ((LOGGER.isInfoEnabled()) && (null != lowestPriceVariant))
		{
			LOGGER.info(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT)
					.append("Lowest Price primary variant for base product with code [").append(baseProduct.getCode())
					.append("] is variant product with code [").append(lowestPriceVariant.getCode()).append("]").toString());
		}
		return lowestPriceVariant;
	}

	/**
	 * @return the priceService
	 */
	public PriceService getPriceService()
	{
		return priceService;
	}

	/**
	 * @param priceService
	 *           the priceService to set
	 */
	@Required
	public void setPriceService(final PriceService priceService)
	{
		this.priceService = priceService;
	}

}
