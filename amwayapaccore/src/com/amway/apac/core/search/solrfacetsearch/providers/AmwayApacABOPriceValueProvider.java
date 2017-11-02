package com.amway.apac.core.search.solrfacetsearch.providers;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.HUNDRED_INT;

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

import com.amway.apac.core.product.strategies.AmwayApacPrimaryVariantSelectionStrategy;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.search.solrfacetsearch.provider.impl.ABOPriceValueProvider;


/**
 * Overriding the AmwayCore Implementation to get primary variant values for base variant product indexing.
 *
 * @author Shubham Goyal
 */
public class AmwayApacABOPriceValueProvider extends ABOPriceValueProvider
{
	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacABOPriceValueProvider.class);

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
					if (LOGGER.isInfoEnabled())
					{
						LOGGER.info(new StringBuilder(HUNDRED_INT).append("The product with code [").append(productModel.getCode())
								.append("] is a base product, fetching primary variant to display the ABO Price.").toString());
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
				final Double value = Double.valueOf(priceInformation.getPriceValue().getValue());
				final List rangeNames = getRangeNameList(indexedProperty, value, currency.getIsocode());

				if (rangeNames.isEmpty())
				{
					addFieldValues(fieldValues, indexedProperty, currency, value);
				}
				else
				{
					final String range = (String) rangeNames.get(0);
					addFieldValues(fieldValues, indexedProperty, currency, (range == null ? value : range));
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
