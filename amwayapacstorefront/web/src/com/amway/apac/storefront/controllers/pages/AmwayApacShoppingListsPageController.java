package com.amway.apac.storefront.controllers.pages;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.SORT_FIELD_STRING;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.SORT_ORDER_STRING;
import static com.amway.apac.storefront.controllers.ControllerConstants.GeneralConstants.WISHLIST_SORT_BY_LAST_UPDATED;
import static com.amway.apac.storefront.controllers.ControllerConstants.ModelParameters.ERROR_MESSAGE;
import static com.amway.apac.storefront.controllers.ControllerConstants.ModelParameters.SUCCESS_MESSAGE;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.facades.wishlist.AmwayApacWishlistFacade;
import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.facades.product.data.WishlistData;


/**
 * Controller for shopping lists pages.
 */
@Controller
@RequestMapping("/shopping-lists")
public class AmwayApacShoppingListsPageController extends AbstractPageController
{
	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacShoppingListsPageController.class);

	/**
	 * Path parameter for shopping list uid
	 */
	private static final String SHOPPING_LIST_UID_PATH_VARIABLE_PATTERN = "{shoppingListUid:.*}";

	@Resource(name = "wishlistFacade")
	private AmwayApacWishlistFacade amwayApacWishlistFacade;

	@Resource(name = "shoppingListDetailsBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder shoppingListDetailsBreadcrumbBuilder;

	/**
	 * Controller method for the page displaying all the shopping lists of the user.
	 *
	 * @param model
	 *           model
	 * @return view
	 * @throws CMSItemNotFoundException
	 *            if shopping lists CMS page is not found
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String allShoppingLists(final Model model,
			@RequestParam(name = SORT_FIELD_STRING, required = false, defaultValue = WISHLIST_SORT_BY_LAST_UPDATED) final String sorfField,
			@RequestParam(name = SORT_ORDER_STRING, required = false, defaultValue = AmwayapacCoreConstants.DESC_STRING) final String sortOrder)
			throws CMSItemNotFoundException
	{
		populateShoppingListsPageView(model, sorfField, sortOrder);
		return getViewForPage(model);
	}

	/**
	 * Populates the shopping lists data in the model.
	 *
	 * @param model
	 *           model
	 * @param sortOrder
	 *           field according to which sorting of the shopping lists will be done
	 * @param sorfField
	 *           defines whether sorting will be ascending or descending
	 * @throws CMSItemNotFoundException
	 *            if the shopping lists cms page is not found.
	 */
	private void populateShoppingListsPageView(final Model model, final String sorfField, final String sortOrder)
			throws CMSItemNotFoundException
	{
		model.addAttribute(SORT_FIELD_STRING, sorfField);
		model.addAttribute(SORT_ORDER_STRING, sortOrder);

		model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LISTS_STRING,
				amwayApacWishlistFacade.getAllWishlistsWithBasicData(resolveSortField(sorfField), sortOrder));

		storeCmsPageInModel(model, getContentPageForLabelOrId(ControllerConstants.GeneralConstants.SHOPPING_LISTS_CMS_PAGE));
		setUpMetaDataForContentPage(model,
				getContentPageForLabelOrId((ControllerConstants.GeneralConstants.SHOPPING_LISTS_CMS_PAGE)));
		model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR,
				shoppingListDetailsBreadcrumbBuilder.getBreadcrumbs(null));
	}

	/**
	 * Converts the UI parameters for sorting to actual model attributes based on which sorting is to be done.
	 *
	 * @param sorfField
	 *           sort parameter
	 * @return resolved value, if parameter is null or invalid, sorting is done by last updated.
	 */
	private String resolveSortField(final String sorfField)
	{
		String resolvedSortField = Wishlist2Model.MODIFIEDTIME;
		if (StringUtils.isNotBlank(sorfField))
		{
			switch (sorfField)
			{
				case ControllerConstants.GeneralConstants.WISHLIST_SORT_BY_NAME:
					resolvedSortField = Wishlist2Model.NAME;
					break;
				case ControllerConstants.GeneralConstants.WISHLIST_SORT_BY_ADDED_FOR: // down line functionality is not done yet, so sorting is done by user
					resolvedSortField = Wishlist2Model.USER;
					break;
				case ControllerConstants.GeneralConstants.WISHLIST_SORT_BY_LAST_UPDATED:
					resolvedSortField = Wishlist2Model.MODIFIEDTIME;
					break;
				case ControllerConstants.GeneralConstants.WISHLIST_SORT_BY_USER:
					resolvedSortField = Wishlist2Model.USER;
					break;
				default:
					resolvedSortField = Wishlist2Model.MODIFIEDTIME; // if the value is invalid then, using the sort by modified time as default
					break;
			}
		}
		return resolvedSortField;
	}

	/**
	 * Controller method to create a new shopping list.
	 *
	 * @param shoppingListName
	 *           name of the new shopping list
	 * @param model
	 *           model
	 *
	 * @return the view
	 * @throws CMSItemNotFoundException
	 *            if the shopping list page is not found.
	 */
	@RequestMapping(value = "/create-shopping-list", method = RequestMethod.POST)
	public String createShoppingList(@RequestParam(value = "shoppingListName") final String shoppingListName, final Model model,
			@RequestParam(name = SORT_FIELD_STRING, required = false, defaultValue = Wishlist2Model.MODIFIEDTIME) final String sorfField,
			@RequestParam(name = SORT_ORDER_STRING, required = false, defaultValue = AmwayapacCoreConstants.DESC_STRING) final String sortOrder)
			throws CMSItemNotFoundException
	{
		if (validateShoppingListName(shoppingListName, model))
		{
			final WishlistData wishlist = amwayApacWishlistFacade.createWishlist(shoppingListName);
			if (wishlist == null) // happens when there is already existing wishlist with same name
			{
				model.addAttribute(ERROR_MESSAGE,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_NAME_ALREADY_EXISTS);
			}
			else // success
			{
				model.addAttribute(SUCCESS_MESSAGE,
						ControllerConstants.SuccessMessageKeys.ShoppingList.SHOPPING_LIST_CREATED_SUCESS_MESSAGE);
				populateShoppingListsPageView(model, sorfField, sortOrder);
			}
		}
		return ControllerConstants.Views.Fragments.ShoppingList.ShoppingListsPageFragment;
	}

	/**
	 * Validates shopping list name
	 *
	 * @param shoppingListName
	 *           shopping list name
	 * @param model
	 *           MVC model to add error messages
	 *
	 * @return true if the name is valid, else false
	 */
	private boolean validateShoppingListName(final String shoppingListName, final Model model)
	{
		boolean isShoppingListNameValid = true;

		if (StringUtils.isBlank(shoppingListName)) // check for empty shopping list name
		{
			isShoppingListNameValid = false;
			model.addAttribute(ERROR_MESSAGE, ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_CREATE_NAME_EMPTY);
		}
		else if (shoppingListName.length() > AmwayapacCoreConstants.SHOPPING_LIST_DEFAULT_MAX_LENGTH) //check if shopping list name exceeds max length
		{
			isShoppingListNameValid = false;
			model.addAttribute(ERROR_MESSAGE,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_CREATE_NAME_MAX_LENGTH);
		}
		return isShoppingListNameValid;
	}

	/**
	 * Controller method for the page displaying shopping list details
	 *
	 * @param model
	 *           model
	 * @return view
	 * @throws CMSItemNotFoundException
	 *            if shopping lists CMS page is not found
	 */
	@RequestMapping(value = "/detail/" + SHOPPING_LIST_UID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String shoppingListDetails(final Model model, @PathVariable("shoppingListUid") final String shoppingListUid)
			throws CMSItemNotFoundException
	{
		populateShoppingListDetailsPageView(model, shoppingListUid);
		return getViewForPage(model);
	}

	/**
	 * Populates the shopping list details data in the model.
	 *
	 * @param model
	 *           model
	 * @param shoppingListUid
	 *           uid of the shopping list to be displayed
	 * @throws CMSItemNotFoundException
	 *            if the shopping lists cms page is not found.
	 */
	private void populateShoppingListDetailsPageView(final Model model, final String shoppingListUid)
			throws CMSItemNotFoundException
	{
		if (StringUtils.isBlank(shoppingListUid))
		{
			GlobalMessages.addErrorMessage(model,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_PAGE_NOT_FOUND);
		}
		else
		{
			try
			{
				model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LIST_DATA,
						amwayApacWishlistFacade.getWishlistByUid(shoppingListUid));
			}
			catch (AmbiguousIdentifierException | UnknownIdentifierException exception)
			{
				GlobalMessages.addErrorMessage(model,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_PAGE_NOT_FOUND);
				LOGGER.error(new StringBuilder(100).append("No shopping list found with uid [").append(shoppingListUid).append("].")
						.toString(), exception);
			}
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(ControllerConstants.GeneralConstants.SHOPPING_LIST_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model,
				getContentPageForLabelOrId((ControllerConstants.GeneralConstants.SHOPPING_LIST_DETAILS_CMS_PAGE)));
		model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR, shoppingListDetailsBreadcrumbBuilder
				.getBreadcrumbs((ControllerConstants.GeneralConstants.SHOPPING_LIST_DETAILS_PAGE_BREADCRUMB_KEY)));
	}

}