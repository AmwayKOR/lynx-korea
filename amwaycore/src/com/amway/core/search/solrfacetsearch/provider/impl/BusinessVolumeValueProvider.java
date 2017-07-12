package com.amway.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.internal.i18n.I18NConstants;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.util.Config;
import com.amway.core.constants.AmwaycoreConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import org.apache.log4j.Logger;


/**
 * This class will provide the value of BusinessVolume to Solr
 */
public class BusinessVolumeValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private static final Logger LOG = Logger.getLogger(BusinessVolumeValueProvider.class);

	private FieldNameProvider fieldNameProvider;
	private CatalogVersionService catalogVersionService;
	private PriceService priceService;

	private ModelService modelService;
	private SessionService sessionService;
	private UserService userService;

	@Override
	public Collection getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
			throws FieldValueProviderException
	{
		final Collection fieldValues = new ArrayList();
		ProductModel productToIndex = null;

		if (model instanceof ProductModel)
		{
			productToIndex = (ProductModel) model;

			if (productToIndex != null)
			{
				if (indexedProperty.isCurrency())
				{
					final Collection<CurrencyModel> currencies = indexConfig.getCurrencies();
					for (final CurrencyModel currency : currencies)
					{
						fieldValues.addAll(createFieldValue(productToIndex, currency, indexedProperty));
					}
				}
				else
				{
					// TODO
					fieldValues.addAll(createFieldValue(productToIndex, null, indexedProperty));
				}
			}
		}
		return fieldValues;
	}

	protected List<FieldValue> createFieldValue(final ProductModel productModel, final CurrencyModel currency,
			final IndexedProperty indexedProperty) throws FieldValueProviderException
	{
		final Collection<CatalogVersionModel> filteredCatalogVersions = filterCatalogVersions(
				getCatalogVersionService().getSessionCatalogVersions());
		final List<PriceInformation> prices = new ArrayList<PriceInformation>();

		sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public void executeWithoutResult()
			{
				getSessionService().setAttribute(Europe1Constants.PARAMS.UPG,
						UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP));

				getSessionService().setAttribute(I18NConstants.CURRENCY_SESSION_ATTR_KEY, currency);
				getCatalogVersionService().setSessionCatalogVersions(filteredCatalogVersions);
				prices.addAll(priceService.getPriceInformationsForProduct(productModel));
			}
		}, userService.getAnonymousUser());

		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		for (final PriceInformation priceInformation : prices)
		{
			if (priceInformation != null)
			{
				if (priceInformation.getPriceValue().getCurrencyIso().equalsIgnoreCase(currency.getIsocode()))
				{
					Double value = (Double) priceInformation.getQualifierValue("businessVolume");
					addFieldValues(fieldValues, indexedProperty, currency, value);
					LOG.info("Extracted BV: " + value);
				}
			}
		}

		return fieldValues;
	}

	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final CurrencyModel currency, final Object value)
	{
		final Collection<String> fieldNames = getFieldNameProvider()
				.getFieldNames(indexedProperty, currency == null ? null : currency.getIsocode());
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}

	protected Collection<CatalogVersionModel> filterCatalogVersions(final Collection<CatalogVersionModel> sessionCatalogVersions)
	{
		final List<CatalogVersionModel> result = new ArrayList<CatalogVersionModel>(sessionCatalogVersions.size());

		for (final CatalogVersionModel catalogVersion : sessionCatalogVersions)
		{
			LOG.info("Checking catalog version " + catalogVersion.getVersion());

			if (!(catalogVersion instanceof ClassificationSystemVersionModel) && !(catalogVersion
					.getCatalog() instanceof ContentCatalogModel))
			{
				LOG.info("Including version " + catalogVersion.getVersion());
				result.add(catalogVersion);
			}
		}

		return result;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	public PriceService getPriceService()
	{
		return priceService;
	}

	public void setPriceService(final PriceService priceService)
	{
		this.priceService = priceService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
