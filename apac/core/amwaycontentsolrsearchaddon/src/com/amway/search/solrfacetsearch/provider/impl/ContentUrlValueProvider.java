/**
 *
 */
package com.amway.search.solrfacetsearch.provider.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.restrictions.AbstractRestrictionModel;
import de.hybris.platform.cms2.model.restrictions.CMSCategoryRestrictionModel;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


public class ContentUrlValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private static final Logger LOG = Logger.getLogger(ContentUrlValueProvider.class);

	private UrlResolver<AbstractPageModel> urlResolver;
	private FieldNameProvider fieldNameProvider;
	private CommonI18NService commonI18NService;

	protected FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	protected UrlResolver<AbstractPageModel> getUrlResolver()
	{
		return urlResolver;
	}

	@Required
	public void setUrlResolver(final UrlResolver<AbstractPageModel> urlResolver)
	{
		this.urlResolver = urlResolver;
	}

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model instanceof AbstractPageModel)
		{
			final AbstractPageModel product = (AbstractPageModel) model;
			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

			if (indexedProperty.isLocalized())
			{
				final Collection<LanguageModel> languages = indexConfig.getLanguages();
				for (final LanguageModel language : languages)
				{
					fieldValues.addAll(createFieldValue(product, language, indexedProperty));
				}
			}
			else
			{
				fieldValues.addAll(createFieldValue(product, null, indexedProperty));
			}
			return fieldValues;
		}
		else
		{
			throw new FieldValueProviderException("Cannot evaluate rating of non-product item");
		}
	}

	protected List<FieldValue> createFieldValue(final AbstractPageModel page, final LanguageModel language,
			final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

		final String productUrl = getPageUrl(page, language);
		if (productUrl != null)
		{
			addFieldValues(fieldValues, indexedProperty, language, productUrl);
		}

		return fieldValues;
	}

	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final LanguageModel language, final Object value)
	{
		final Collection<String> fieldNames = getFieldNameProvider()
				.getFieldNames(indexedProperty, language == null ? null : language.getIsocode());
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}

	protected String getPageUrl(final AbstractPageModel page, final LanguageModel language)
	{
		i18nService.setCurrentLocale(commonI18NService.getLocaleForLanguage(language));
		String url = null;
		try
		{
			url = getUrlResolver().resolve(page);
		}
		catch (final Exception e)
		{
			LOG.error(e.getMessage(), e);
			//
		}

		if (StringUtils.isEmpty(url))
		{
			if (page instanceof ContentPageModel)
			{
				url = ((ContentPageModel) page).getLabel();
			}
			else if (page instanceof CategoryPageModel)
			{
				final List<AbstractRestrictionModel> restrictions = ((CategoryPageModel) page).getRestrictions();
				beakHere:
				for (final AbstractRestrictionModel abstractRestrictionModel : restrictions)
				{
					if (abstractRestrictionModel instanceof CMSCategoryRestrictionModel)
					{
						final CMSCategoryRestrictionModel category = (CMSCategoryRestrictionModel) abstractRestrictionModel;
						final Collection<CategoryModel> categories = category.getCategories();
						if (categories != null && !categories.isEmpty())
						{
							for (final CategoryModel categoryModel : categories)
							{
								url = "/c/" + categoryModel.getCode();
								break beakHere;
							}
						}
						else
						{
							final List<String> categoryCodes = category.getCategoryCodes();
							if (categoryCodes != null && !categoryCodes.isEmpty())
							{
								for (final String code : categoryCodes)
								{
									url = "/c/" + code;
									break beakHere;
								}
							}
						}

					}
				}
			}
		}

		return url;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}
}
