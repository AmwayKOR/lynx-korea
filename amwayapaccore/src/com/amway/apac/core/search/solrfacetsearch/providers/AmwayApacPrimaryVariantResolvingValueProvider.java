package com.amway.apac.core.search.solrfacetsearch.providers;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.HUNDRED_INT;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.servicelayer.internal.i18n.I18NConstants;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

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
 * Overriding {@link ABOPriceValueProvider} implementation to use variant selection strategy while fetching prices for
 * base products.
 *
 * @author Shubham Goyal
 */
public class AmwayApacPrimaryVariantResolvingValueProvider implements FieldValueProvider
{

	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacPrimaryVariantResolvingValueProvider.class);

	private FieldValueProvider actualProvider;
	private CatalogVersionService catalogVersionService;
	private SessionService sessionService;
	private UserService userService;
	private AmwayApacPrimaryVariantSelectionStrategy amwayApacPrimaryVariantSelectionStrategy;

	/**
	 * Overriding {@link ABOPriceValueProvider} implementation to use variant selection strategy while fetching prices
	 * for base products.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Collection getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object productModel) throws FieldValueProviderException
	{
		final Collection fieldValues = new ArrayList();

		if ((null != productModel) && (productModel instanceof ProductModel))
		{
			ProductModel primaryVariant = null;

			// if the product has variants, then resolve the variant
			if ((CollectionUtils.isNotEmpty(((ProductModel) productModel).getVariants())) && (indexedProperty.isCurrency()))
			{
				primaryVariant = resolveVariantProduct(indexConfig.getCurrencies().iterator().next(), (ProductModel) productModel);
			}

			fieldValues.addAll(getActualProvider().getFieldValues(indexConfig, indexedProperty,
					(null != primaryVariant) ? primaryVariant : (ProductModel) productModel));
		}

		return fieldValues;
	}

	/**
	 * Calls the {@link AmwayApacPrimaryVariantSelectionStrategy} to decide which variant to use.
	 *
	 * @param currency
	 *           currency
	 * @param productModel
	 * @return resolved primary variant, null if no variant is found
	 */
	protected ProductModel resolveVariantProduct(final CurrencyModel currency, final ProductModel productModel)
	{
		final Collection<CatalogVersionModel> filteredCatalogVersions = filterCatalogVersions(
				getCatalogVersionService().getSessionCatalogVersions());

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public ProductModel execute()
			{
				setupSessionParameters(currency, filteredCatalogVersions);

				if (LOGGER.isInfoEnabled())
				{
					LOGGER.info(new StringBuilder(HUNDRED_INT).append("The product with code [").append(productModel.getCode())
							.append("] is a base product, fetching primary variant to display the ABO Price.").toString());
				}
				return getAmwayApacPrimaryVariantSelectionStrategy().getPrimaryVariant(productModel);
			}
		}, getUserService().getAnonymousUser());
	}

	/**
	 * Sets up the session for product fetching.
	 *
	 * @param currencyModel
	 *           currency
	 * @param filteredCatalogVersions
	 *           catalog versions.
	 */
	protected void setupSessionParameters(final CurrencyModel currencyModel,
			final Collection<CatalogVersionModel> filteredCatalogVersions)
	{
		getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
				UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP));

		getSessionService().setAttribute(I18NConstants.CURRENCY_SESSION_ATTR_KEY, currencyModel);

		getCatalogVersionService().setSessionCatalogVersions(filteredCatalogVersions);
	}

	/**
	 * Filters the product catalogs in the session
	 *
	 * @param sessionCatalogVersions
	 *           catalog versions in current session
	 * @return product catalogs found
	 */
	protected Collection<CatalogVersionModel> filterCatalogVersions(final Collection<CatalogVersionModel> sessionCatalogVersions)
	{
		final List<CatalogVersionModel> result = new ArrayList<>(sessionCatalogVersions.size());

		for (final CatalogVersionModel catalogVersion : sessionCatalogVersions)
		{

			if (!(catalogVersion instanceof ClassificationSystemVersionModel)
					&& !(catalogVersion.getCatalog() instanceof ContentCatalogModel))
			{
				result.add(catalogVersion);
			}
		}
		return result;
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

	/**
	 * @return the fieldValueProvider
	 */
	public FieldValueProvider getActualProvider()
	{
		return actualProvider;
	}

	/**
	 * @param actualProvider
	 *           the fieldValueProvider to set
	 */
	@Required
	public void setActualProvider(final FieldValueProvider actualProvider)
	{
		this.actualProvider = actualProvider;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
