package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.SearchBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Category Landing Page controller
 *
 * @author Shubham Goyal
 */

@Controller
@Scope("tenant")
@RequestMapping(value = "/category")
public class AmwayApacCategoryLandingPageController extends AbstractPageController
{

	protected static final String CATEGORY_CODE_PATH_VARIABLE_PATTERN = "/{categoryCode:.*}";
	private static final String CATEGORY_LANDING_PAGE_ID = "category-landing-page";

	@Resource(name = "commerceCategoryService")
	private CommerceCategoryService commerceCategoryService;

	@Resource(name = "searchBreadcrumbBuilder")
	private SearchBreadcrumbBuilder searchBreadcrumbBuilder;


	@ExceptionHandler(UnknownIdentifierException.class)
	public String handleUnknownIdentifierException(final UnknownIdentifierException exception, final HttpServletRequest request)
	{
		request.setAttribute("message", exception.getMessage());
		return FORWARD_PREFIX + "/404";
	}

	/**
	 * Controller returns category landing page with restrictions to show content belonging to requested category only.
	 *
	 * @param categoryCode
	 *           Requested category whose landing page to be displayed
	 * @param model
	 *           View model
	 * @param request
	 *           HTTP Request
	 * @return View JSP
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String getCategoryLandingPage(
			@PathVariable(ControllerConstants.ModelParameters.CATEGORY_CODE) final String categoryCode, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final CategoryModel category = commerceCategoryService.getCategoryForCode(categoryCode);
		final RequestContextData requestData = getRequestContextData(request);
		requestData.setCategory(category);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				searchBreadcrumbBuilder.getBreadcrumbs(categoryCode, StringUtils.EMPTY, false));
		storeCmsPageInModel(model, getCategoryLandingPage());
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveContentPageTitle(category.getName()));
		return getViewForPage(model);
	}

	/**
	 * Retrieves the category landing page.
	 *
	 * @return Page
	 * @throws CMSItemNotFoundException
	 */
	protected AbstractPageModel getCategoryLandingPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId(CATEGORY_LANDING_PAGE_ID);
	}

}
