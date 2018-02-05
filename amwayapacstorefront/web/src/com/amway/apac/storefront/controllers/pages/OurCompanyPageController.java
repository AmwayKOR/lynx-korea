package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * @author Teresa Teo
 *
 */
@Controller
@RequestMapping("/about-amway")
public class OurCompanyPageController extends AbstractSearchPageController
{
	private static final String BREADCRUMBS_ATTR = "breadcrumbs";
	// CMS Pages
	private static final String OUR_COMPANY_CMS_PAGE = "our-company";

	@Resource(name = "ourCompanyBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder ourCompanyBreadcrumbBuilder;

	@RequestMapping(value = "/our-company", method = RequestMethod.GET)
	public String ourCompany(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(OUR_COMPANY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(OUR_COMPANY_CMS_PAGE));
		model.addAttribute(BREADCRUMBS_ATTR,
				ourCompanyBreadcrumbBuilder.getBreadcrumbs(ControllerConstants.GeneralConstants.OUR_COMPANY_PAGE_BREADCRUMB_KEY));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

		return getViewForPage(model);
	}
}
