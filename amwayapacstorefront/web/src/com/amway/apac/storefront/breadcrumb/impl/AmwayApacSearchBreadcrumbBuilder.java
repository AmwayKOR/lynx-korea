package com.amway.apac.storefront.breadcrumb.impl;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.SearchBreadcrumbBuilder;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.ui.context.Theme;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


/**
 * @author Shubham Goyal
 */
public class AmwayApacSearchBreadcrumbBuilder extends SearchBreadcrumbBuilder
{
	private static final String LAST_LINK_CLASS = "active";
	private String searchBreadcrumbPrefix;
	private I18NService i18nService;


	@Override
	public List<Breadcrumb> getBreadcrumbs(final String categoryCode, final String searchText, final boolean emptyBreadcrumbs)
	{
		final List<Breadcrumb> breadcrumbs = new ArrayList<>();

		if (categoryCode == null)
		{
			String beadcrumbName = searchText;
			final Object[] searchArg =
			{ searchText };

			final MessageSource messageSource = getMessageSource();
			if (null != messageSource)
			{
				beadcrumbName = messageSource.getMessage(getSearchBreadcrumbPrefix(), searchArg, getI18nService().getCurrentLocale());
			}

			final Breadcrumb breadcrumb = new Breadcrumb("/search?text=" + getEncodedUrl(searchText), beadcrumbName,
					emptyBreadcrumbs ? LAST_LINK_CLASS : "");
			breadcrumbs.add(breadcrumb);
		}
		else
		{
			createBreadcrumbCategoryHierarchyPath(categoryCode, emptyBreadcrumbs, breadcrumbs);
		}
		return breadcrumbs;
	}

	protected MessageSource getMessageSource()
	{
		final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null)
		{
			final HttpServletRequest request = requestAttributes.getRequest();
			final Theme theme = RequestContextUtils.getTheme(request);
			if (theme != null)
			{
				return theme.getMessageSource();
			}
		}
		return null;
	}

	/**
	 * @return the searchBreadcrumbPrefix
	 */
	public String getSearchBreadcrumbPrefix()
	{
		return searchBreadcrumbPrefix;
	}

	/**
	 * @param searchBreadcrumbPrefix
	 *           the searchBreadcrumbPrefix to set
	 */
	public void setSearchBreadcrumbPrefix(final String searchBreadcrumbPrefix)
	{
		this.searchBreadcrumbPrefix = searchBreadcrumbPrefix;
	}

	/**
	 * @return the i18nService
	 */
	public I18NService getI18nService()
	{
		return i18nService;
	}

	/**
	 * @param i18nService
	 *           the i18nService to set
	 */
	@Required
	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}
}
