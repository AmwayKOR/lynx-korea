package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amway.apac.facades.wishlist.AmwayApacWishlistFacade;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for shopping lists pages.
 */
@Controller
@RequestMapping("/shopping-lists")
public class AmwayApacShoppingListsPageController extends AbstractPageController
{

	@Resource(name = "wishlistFacade")
	private AmwayApacWishlistFacade amwayApacWishlistFacade;

	@Resource(name = "simpleBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder simpleBreadcrumbBuilder;

	/**
	 * Controller method for the page displaying all the shopping lists of the user.
	 *
	 * @param model
	 *           model
	 * @return view
	 * @throws CMSItemNotFoundException
	 *            if shopping lists cms page is not found
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String wishlist(final Model model) throws CMSItemNotFoundException
	{
		populateShoppingListsPageView(model);
		return getViewForPage(model);
	}

	/**
	 * Populates the shopping lists data in the model.
	 *
	 * @param model
	 *           model
	 * @throws CMSItemNotFoundException
	 *            if the shopping lists cms page is not found.
	 */
	private void populateShoppingListsPageView(final Model model) throws CMSItemNotFoundException
	{
		// call to create favorite wishlist if user does not have one yet.
		amwayApacWishlistFacade.createFavoriteWishlistIfNeeded();

		model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LISTS_STRING,
				amwayApacWishlistFacade.getAllWishlistsWithBasicData());
		storeCmsPageInModel(model, getContentPageForLabelOrId(ControllerConstants.GeneralConstants.SHOPPING_LISTS_CMS_PAGE));
		setUpMetaDataForContentPage(model,
				getContentPageForLabelOrId((ControllerConstants.GeneralConstants.SHOPPING_LISTS_CMS_PAGE)));
		model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR,
				simpleBreadcrumbBuilder.getBreadcrumbs((ControllerConstants.GeneralConstants.SHOPPING_LISTS_PAGE_BREADCRUMB_KEY)));
	}
}