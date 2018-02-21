/**
 *
 */
package com.amway.search.solrfacetsearch.provider.impl;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForPageModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForTemplateModel;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


public class ContentPageValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
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
			final AbstractPageModel contentPage = (AbstractPageModel) model;

			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

			if (indexedProperty.isLocalized())
			{
				final Collection<LanguageModel> languages = indexConfig.getLanguages();
				for (final LanguageModel language : languages)
				{
					fieldValues.addAll(createFieldValue(contentPage, language, indexedProperty));
				}
			}
			else
			{
				fieldValues.addAll(createFieldValue(contentPage, null, indexedProperty));
			}
			return fieldValues;
		}
		else
		{
			throw new FieldValueProviderException("Cannot evaluate rating of non-contentPage item");
		}
	}

	protected List<FieldValue> createFieldValue(final AbstractPageModel contentPage, final LanguageModel language,
			final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

		final Collection<String> contentData = getPageContent(contentPage, language);
		if (contentData != null)
		{
			addFieldValues(fieldValues, indexedProperty, language, contentData);
		}

		return fieldValues;
	}

	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final LanguageModel language, final Object value)
	{
		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
				language == null ? null : language.getIsocode());
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}

	protected Collection<String> getPageContent(final AbstractPageModel contentPage, final LanguageModel language)
	{
		i18nService.setCurrentLocale(commonI18NService.getLocaleForLanguage(language));
		Collection<String> contentData = new ArrayList<>();
		final StringBuilder content = new StringBuilder();
		for (final ContentSlotForTemplateModel slotForPage : contentPage.getMasterTemplate().getContentSlots())
		{
			final ContentSlotModel slot = slotForPage.getContentSlot();
			final Collection<AbstractCMSComponentModel> components = slot.getCmsComponents();

			for (final AbstractCMSComponentModel component : components)
			{
				if (component instanceof CMSParagraphComponentModel)
				{
					final CMSParagraphComponentModel paragraph = (CMSParagraphComponentModel) component;

					if (paragraph.getContent() != null && !paragraph.getContent().isEmpty())
					{
						content.append(paragraph.getContent().replaceAll("<[a-zA-Z\\/][^>]*>", ""));
					}
				}
			}
		}

		for (final ContentSlotForPageModel slotForPage : contentPage.getContentSlots())
		{
			final ContentSlotModel slot = slotForPage.getContentSlot();
			final Collection<AbstractCMSComponentModel> components = slot.getCmsComponents();

			for (final AbstractCMSComponentModel component : components)
			{
				if (component instanceof CMSParagraphComponentModel)
				{
					final CMSParagraphComponentModel paragraph = (CMSParagraphComponentModel) component;
					if (paragraph.getContent() != null && !paragraph.getContent().isEmpty())
					{
						content.append(paragraph.getContent().replaceAll("<[a-zA-Z\\/][^>]*>", ""));
					}
				}
			}
		}

		if (StringUtils.isNotEmpty(content.toString()))
		{
			contentData = splitString(content, 2000);
		}

		return contentData;
	}

	private static List<String> splitString(final StringBuilder content, final int lineSize)
	{
		final List<String> result = new ArrayList<>();

		final Pattern pattern = Pattern.compile("\\b.{1," + (lineSize - 1) + "}\\b\\W?");
		final Matcher matcher = pattern.matcher(content.toString());

		while (matcher.find())
		{
			result.add(matcher.group());
		}
		return result;
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
