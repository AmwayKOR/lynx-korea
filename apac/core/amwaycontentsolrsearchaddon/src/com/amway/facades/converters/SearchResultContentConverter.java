/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2012 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.amway.facades.converters;

import de.hybris.platform.commerceservices.converter.impl.AbstractPopulatingConverter;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.facade.content.data.ContentData;


/**
 * Converter implementation for {@link SearchResultValueData} as source and {@link ContentData} as target type.
 */
public class SearchResultContentConverter extends AbstractPopulatingConverter<SearchResultValueData, ContentData>
{
	//private UrlResolver<ContentData> productDataUrlResolver;
	private CommonI18NService commonI18NService;

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Override
	protected ContentData createTarget()
	{
		return new ContentData();
	}

	@Override
	public void populate(final SearchResultValueData source, final ContentData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		// Pull the values directly from the SearchResult object
		target.setTitle(this.<String> getValue(source, "title"));
		target.setName(this.<String> getValue(source, "name"));
		target.setPageContent(getConcatenatedContent(source, "pagecontent"));
		target.setUrl(this.<String> getValue(source, "url"));
		target.setContentId(this.<String> getValue(source, "id"));

		//final String tShort = target.getPageContent();

		//target.setShortPageContent();

		//		final Pattern pattern = Pattern.compile("<[a-zA-Z\\/][^>]*>");
		//		final Matcher matcher = pattern.matcher(target.getPageContent());
		//		while (matcher.find())
		//		{
		//
		//		}

		super.populate(source, target);
	}

	private String getConcatenatedContent(final SearchResultValueData source, final String propertyName)
	{
		final StringBuilder pageContent = new StringBuilder();
		final List<String> contentData = getValue(source, propertyName);
		if (CollectionUtils.isNotEmpty(contentData))
		{
			for (final String content : contentData)
			{
				pageContent.append(content);
			}

		}


		return pageContent.toString();
	}

	protected void populateUrl(final SearchResultValueData source, final ContentData target)
	{
		final String url = this.<String> getValue(source, "url");
		if (StringUtils.isEmpty(url))
		{
			// Resolve the URL and set it on the product data
			//			target.setUrl(getProductDataUrlResolver().resolve(target));
		}
		else
		{
			target.setUrl(url);
		}
	}

	protected <T> T getValue(final SearchResultValueData source, final String propertyName)
	{
		if (source.getValues() == null)
		{
			return null;
		}

		// DO NOT REMOVE the cast (T) below, while it should be unnecessary it is required by the javac compiler
		return (T) source.getValues().get(propertyName);
	}
}
