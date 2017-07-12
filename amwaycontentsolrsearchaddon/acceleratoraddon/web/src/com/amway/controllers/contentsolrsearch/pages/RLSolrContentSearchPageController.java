/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.amway.controllers.contentsolrsearch.pages;


import de.hybris.platform.acceleratorfacades.device.DeviceDetectionFacade;
import de.hybris.platform.acceleratorfacades.device.data.DeviceData;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorservices.customer.CustomerLocationService;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.SearchBreadcrumbBuilder;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amway.controllers.contentsolrsearch.AmwaycontentsolrsearchaddonControllerConstants;
import com.amway.facade.content.data.ContentData;
import com.amway.facade.search.ContentSearchFacade;
import com.amway.facade.search.facetdata.ContentSearchPageData;
import com.amway.util.MetaSanitizerUtil;


@Controller
@RequestMapping(value = "/rlcontentsearch")
public class RLSolrContentSearchPageController extends RLAbstractContentSearchPageController
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(RLSolrContentSearchPageController.class);

	private static final String SEARCH_CMS_PAGE_ID = "contentSearch";
	private static final String NO_RESULTS_CMS_PAGE_ID = "searchEmpty";

	@Resource(name = "contentSearchFacade")
	private ContentSearchFacade<ContentData> contentSearchFacade;

	@Resource(name = "productSearchFacade")
	private ProductSearchFacade<ProductData> productSearchFacade;

	@Resource(name = "searchBreadcrumbBuilder")
	private SearchBreadcrumbBuilder searchBreadcrumbBuilder;

	@Resource(name = "customerLocationService")
	private CustomerLocationService customerLocationService;

	@Resource(name = "cmsComponentService")
	private CMSComponentService cmsComponentService;

	@Resource
	private DeviceDetectionFacade deviceDetectionFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String refineSearch(@RequestParam(value = "q", required = false) final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "text", required = false) String searchText, final HttpServletRequest request, final Model model)
			throws CMSItemNotFoundException
	{
		String currentDevice = UiExperienceLevel.DESKTOP.getCode();
		final DeviceData deviceData = deviceDetectionFacade.getCurrentDetectedDevice();
		final boolean isMobile = deviceData.getMobileBrowser().booleanValue();
		if (isMobile)
		{
			currentDevice = UiExperienceLevel.MOBILE.getCode();
		}

		if (StringUtils.isNotBlank(searchText))
		{
			searchText = searchText + "::uiexperience:" + currentDevice;
		}

		final ContentSearchPageData<SearchStateData, ContentData> searchPageData = performSearch(searchText, page, showMode,
				sortCode, getSearchPageSize());

		populateModel(model, searchPageData, showMode);
		model.addAttribute("userLocation", customerLocationService.getUserLocation());

		if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
		{
			updatePageTitle(searchPageData.getFreeTextSearch(), model);
			storeCmsPageInModel(model, getContentPageForLabelOrId(NO_RESULTS_CMS_PAGE_ID));
		}
		else
		{
			storeContinueUrl(request);
			updatePageTitle(searchPageData.getFreeTextSearch(), model);
			storeCmsPageInModel(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
		}
		//model.addAttribute(WebConstants.BREADCRUMBS_KEY, searchBreadcrumbBuilder.getBreadcrumbs(null, searchPageData));
		model.addAttribute("pageType", PageType.CONTENTSEARCH.name());

		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(
				getMessageSource().getMessage("search.meta.description.results", null, getI18nService().getCurrentLocale()) + " "
						+ searchText + " " + getMessageSource()
						.getMessage("search.meta.description.on", null, getI18nService().getCurrentLocale()) + " " + getSiteName());
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(searchText);
		setUpMetaData(model, metaKeywords, metaDescription);

		return AmwaycontentsolrsearchaddonControllerConstants.ADDON_PREFIX + getViewForPage(model);
	}

	protected ContentSearchPageData<SearchStateData, ContentData> performSearch(final String searchQuery, final int page,
			final ShowMode showMode, final String sortCode, final int pageSize)
	{
		final PageableData pageableData = createPageableData(page, pageSize, sortCode, showMode);

		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchQuery);
		searchState.setQuery(searchQueryData);

		return contentSearchFacade.textSearch(searchState, pageableData);
	}

	protected void updatePageTitle(final String searchText, final Model model)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveContentPageTitle(
						getMessageSource().getMessage("search.meta.title", null, getI18nService().getCurrentLocale()) + " "
								+ searchText));
	}

	public DeviceDetectionFacade getDeviceDetectionFacade()
	{
		return deviceDetectionFacade;
	}

	public void setDeviceDetectionFacade(final DeviceDetectionFacade deviceDetectionFacade)
	{
		this.deviceDetectionFacade = deviceDetectionFacade;
	}
}
