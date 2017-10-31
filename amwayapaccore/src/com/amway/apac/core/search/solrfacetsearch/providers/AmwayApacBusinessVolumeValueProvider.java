/**
 *
 */
package com.amway.apac.core.search.solrfacetsearch.providers;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.internal.i18n.I18NConstants;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.product.strategies.AmwayApacPrimaryVariantSelectionStrategy;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.search.solrfacetsearch.provider.impl.BusinessVolumeValueProvider;


/**
 *
 */
public class AmwayApacBusinessVolumeValueProvider extends BusinessVolumeValueProvider
{
	private static final Logger LOG = LoggerFactory.getLogger(AmwayApacBusinessVolumeValueProvider.class);

	private AmwayApacPrimaryVariantSelectionStrategy amwayApacPrimaryVariantSelectionStrategy;

	@Override
	protected List<FieldValue> createFieldValue(final ProductModel productModel, final CurrencyModel currency,
			final IndexedProperty indexedProperty) throws FieldValueProviderException
	{
		final Collection<CatalogVersionModel> filteredCatalogVersions = filterCatalogVersions(
				getCatalogVersionService().getSessionCatalogVersions());
		final List<PriceInformation> prices = new ArrayList<PriceInformation>();

		getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public void executeWithoutResult()
			{
				getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
						UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP));

				getSessionService().setAttribute(I18NConstants.CURRENCY_SESSION_ATTR_KEY, currency);
				getCatalogVersionService().setSessionCatalogVersions(filteredCatalogVersions);

				ProductModel productToIndex = productModel;
				if (CollectionUtils.isNotEmpty(productToIndex.getVariants()))
				{
					if (LOG.isInfoEnabled())
					{
						LOG.info(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT).append("The product with code [")
								.append(productModel.getCode())
								.append("] is a base product, fetching primary variant to display the Business Volume.").toString());
					}
					productToIndex = amwayApacPrimaryVariantSelectionStrategy.getPrimaryVariant(productToIndex);
				}

				prices.addAll(getPriceService().getPriceInformationsForProduct(productToIndex));
			}
		}, getUserService().getAnonymousUser());

		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		for (final PriceInformation priceInformation : prices)
		{
			if (priceInformation != null)
			{
				if (priceInformation.getPriceValue().getCurrencyIso().equalsIgnoreCase(currency.getIsocode()))
				{
					final Double value = (Double) priceInformation.getQualifierValue("businessVolume");
					addFieldValues(fieldValues, indexedProperty, currency, value);
					LOG.info("Extracted BV: " + value);
				}
			}
		}

		return fieldValues;
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
