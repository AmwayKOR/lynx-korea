/**
 *
 */
package com.amway.facades.converters;

import de.hybris.platform.commerceservices.converter.impl.AbstractPopulatingConverter;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.facade.content.data.ContentData;
import com.amway.facade.content.data.ResourceData;


public class SearchResultResourceConverter extends AbstractPopulatingConverter<SearchResultValueData, ResourceData>
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
	protected ResourceData createTarget()
	{
		return new ResourceData();
	}

	@Override
	public void populate(final SearchResultValueData source, final ResourceData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		// Pull the values directly from the SearchResult object
		target.setTitle(this.<String> getValue(source, "title"));
		target.setDisplayImage(this.<String> getValue(source, "displayImage"));
		target.setUrl(this.<String> getValue(source, "url"));
		target.setDescription(this.<String> getValue(source, "description"));
		target.setSubtitle(this.<String> getValue(source, "subtitle"));
		target.setId(this.<String> getValue(source, "id"));

		target.setType(this.<String> getValue(source, "type"));
		target.setSize(this.<Integer> getValue(source, "size"));
		target.setCreationtime((Date) source.getValues().get("creationtime"));
		super.populate(source, target);
	}

	protected void populateUrl(final SearchResultValueData source, final ContentData target)
	{
		final String url = this.<String>getValue(source, "url");
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
