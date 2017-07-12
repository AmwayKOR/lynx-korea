/**
 *
 */
package com.amway.search.solrfacetsearch.provider.impl;

import de.hybris.platform.acceleratorcms.model.restrictions.CMSUiExperienceRestrictionModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.restrictions.AbstractRestrictionModel;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
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
import org.springframework.beans.factory.annotation.Required;


public class ContentUIExperienceValueProvider extends AbstractPropertyFieldValueProvider
		implements FieldValueProvider, Serializable
{
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

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model instanceof AbstractPageModel)
		{
			final AbstractPageModel page = (AbstractPageModel) model;
			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

			if (indexedProperty.isLocalized())
			{
				final Collection<LanguageModel> languages = indexConfig.getLanguages();
				for (final LanguageModel language : languages)
				{
					fieldValues.addAll(createFieldValue(page, language, indexedProperty));
				}
			}
			else
			{
				fieldValues.addAll(createFieldValue(page, null, indexedProperty));
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

		final String uiExperience = getPageUIExperience(page, language);
		if (uiExperience != null)
		{
			addFieldValues(fieldValues, indexedProperty, language, uiExperience);
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

	protected String getPageUIExperience(final AbstractPageModel page, final LanguageModel language)
	{
		String uiExperience = null;

		final List<AbstractRestrictionModel> restrictions = page.getRestrictions();
		for (final AbstractRestrictionModel abstractRestrictionModel : restrictions)
		{
			if (abstractRestrictionModel instanceof CMSUiExperienceRestrictionModel)
			{
				final CMSUiExperienceRestrictionModel uiRestriction = (CMSUiExperienceRestrictionModel) abstractRestrictionModel;
				if (UiExperienceLevel.MOBILE.getCode().equals(uiRestriction.getUiExperience().getCode()))
				{
					uiExperience = UiExperienceLevel.MOBILE.getCode();
					break;
				}
				else
				{
					uiExperience = UiExperienceLevel.DESKTOP.getCode();
					break;
				}
			}
		}

		if (StringUtils.isEmpty(uiExperience))
		{
			uiExperience = UiExperienceLevel.DESKTOP.getCode();
		}

		return uiExperience;
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
