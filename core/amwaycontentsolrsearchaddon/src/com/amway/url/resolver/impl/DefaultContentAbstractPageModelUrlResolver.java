/**
 *
 */
package com.amway.url.resolver.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.commerceservices.url.impl.AbstractUrlResolver;

import org.springframework.beans.factory.annotation.Required;


public class DefaultContentAbstractPageModelUrlResolver extends AbstractUrlResolver<AbstractPageModel>
{

	private UrlResolver<CategoryModel> categoryUrlResolver;

	private CommerceCategoryService commerceCategoryService;
	private String pattern;

	protected CommerceCategoryService getCommerceCategoryService()
	{
		return commerceCategoryService;
	}

	@Required
	public void setCommerceCategoryService(final CommerceCategoryService commerceCategoryService)
	{
		this.commerceCategoryService = commerceCategoryService;
	}

	protected String getPattern()
	{
		return pattern;
	}

	@Required
	public void setPattern(final String pattern)
	{
		this.pattern = pattern;
	}

	/**
	 * @return the categoryUrlResolver
	 */
	public UrlResolver<CategoryModel> getCategoryUrlResolver()
	{
		return categoryUrlResolver;
	}

	/**
	 * @param categoryUrlResolver the categoryUrlResolver to set
	 */
	public void setCategoryUrlResolver(final UrlResolver<CategoryModel> categoryUrlResolver)
	{
		this.categoryUrlResolver = categoryUrlResolver;
	}

	@Override
	protected String resolveInternal(final AbstractPageModel source)
	{
		return "";
	}
}
