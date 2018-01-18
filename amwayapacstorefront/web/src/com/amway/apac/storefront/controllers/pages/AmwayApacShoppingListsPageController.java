package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.AddToCartOrderForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;
import com.amway.apac.facades.wishlist.AmwayApacWishlistFacade;
import com.amway.apac.facades.wishlist.data.AmwayApacWishListModification;
import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.apac.storefront.forms.AmwayApacMultiShoppingListUpdateForm;
import com.amway.facades.product.data.WishlistData;
import com.amway.facades.wishlist.modification.status.AmwayApacWishlistModificationStatus;


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
	 * All shopping lists page URL.
	 */
	private static final String SHOPPING_LISTS_PAGE_URL = "/all";

	/**
	 * All shopping lists data URL.
	 */
	private static final String SHOPPING_LISTS_DATA_URL = "/data/all";

	/**
	 * All shopping lists data URL.
	 */
	private static final String SHOPPING_LISTS_CARTPAGE_DATA_URL = "/cart/data/all";

	/**
	 * All shopping lists multi-products data URL.
	 */
	private static final String SHOPPING_LIST_MULTIPRODUCT_DETAILS_URL = "/add-multiproduct";

	/**
	 * Redirect to all shopping lists page.
	 */
	private static final String SHOPPING_LISTS_PAGE_REDIRECT = REDIRECT_PREFIX + "/shopping-lists" + SHOPPING_LISTS_PAGE_URL;

	/**
	 * Path parameter for shopping list uid
	 */
	private static final String SHOPPING_LIST_UID_PATH_VARIABLE_PATTERN = "{shoppingListUid:.*}";

	/**
	 * URL for the shopping list details page
	 */
	private static final String SHOPPING_LIST_DETAILS_PAGE_URL = "/detail/" + SHOPPING_LIST_UID_PATH_VARIABLE_PATTERN;

	/**
	 * URL for the shopping list details page
	 */
	private static final String SHOPPING_LIST_DETAILS_SORT_PAGE_URL = "/detail/sort";

	/**
	 * /**
	 *
	 */
	private static final String SHOPPING_LIST_CONFIGURED_MAX_LENGTH = "shopping.list.configured.max.length";


	@Resource(name = "wishlistFacade")
	private AmwayApacWishlistFacade amwayApacWishlistFacade;

	@Resource(name = "shoppingListDetailsBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder shoppingListDetailsBreadcrumbBuilder;

	@Resource(name = "wishlistFacade")
	private AmwayApacWishlistFacade wishlistFacade;

	/**
	 * Controller method for the page displaying all the shopping lists of the user.
	 *
	 * @param model
	 *           model
	 * @return view
	 * @throws CMSItemNotFoundException
	 *            if shopping lists CMS page is not found
	 */
	@RequireHardLogIn
	@RequestMapping(value = SHOPPING_LISTS_PAGE_URL, method = RequestMethod.GET)
	public String allShoppingLists(final Model model,
			@RequestParam(name = AmwayapacCoreConstants.SORT_FIELD_STRING, required = false, defaultValue = ControllerConstants.GeneralConstants.SHOPPING_LIST_SORT_BY_LAST_UPDATED) final String sorfField,
			@RequestParam(name = AmwayapacCoreConstants.SORT_ORDER_STRING, required = false, defaultValue = AmwayapacCoreConstants.DESC_STRING) final String sortOrder)
			throws CMSItemNotFoundException
	{
		populateShoppingListsPageView(model, sorfField, sortOrder);
		return getViewForPage(model);
	}

	@RequireHardLogIn
	@RequestMapping(value = SHOPPING_LISTS_DATA_URL, method = RequestMethod.GET)
	public String allShoppingData(final Model model) throws CMSItemNotFoundException
	{
		populateAllShoppingLists(model);
		return ControllerConstants.Views.Fragments.ShoppingList.AddToShoppingListPopUp;
	}

	/**
	 * @param model
	 */
	private void populateAllShoppingLists(final Model model)
	{
		model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LISTS_STRING,
				amwayApacWishlistFacade.getAllWishlistsWithBasicData(
						resolveSortField(ControllerConstants.GeneralConstants.SHOPPING_LIST_SORT_BY_LAST_UPDATED),
						AmwayapacCoreConstants.DESC_STRING));
	}

	@RequireHardLogIn
	@RequestMapping(value = SHOPPING_LISTS_CARTPAGE_DATA_URL, method = RequestMethod.GET)
	public String updateShoppingData(final Model model) throws CMSItemNotFoundException
	{
		populateAllShoppingLists(model);
		return ControllerConstants.Views.Fragments.ShoppingList.AddToShoppingListCartPopUp;
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
		model.addAttribute(AmwayapacCoreConstants.SORT_FIELD_STRING, sorfField);
		model.addAttribute(AmwayapacCoreConstants.SORT_ORDER_STRING, sortOrder);

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
				case ControllerConstants.GeneralConstants.SHOPPING_LIST_SORT_BY_NAME:
					resolvedSortField = Wishlist2Model.NAME;
					break;
				case ControllerConstants.GeneralConstants.SHOPPING_LIST_SORT_BY_LAST_UPDATED:
					resolvedSortField = Wishlist2Model.MODIFIEDTIME;
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
	@RequireHardLogIn
	@RequestMapping(value = "/create-shopping-list", method = RequestMethod.POST)
	public String createShoppingList(@RequestParam(value = "shoppingListName") final String shoppingListName, final Model model,
			@RequestParam(name = AmwayapacCoreConstants.SORT_FIELD_STRING, required = false, defaultValue = Wishlist2Model.MODIFIEDTIME) final String sorfField,
			@RequestParam(name = AmwayapacCoreConstants.SORT_ORDER_STRING, required = false, defaultValue = AmwayapacCoreConstants.DESC_STRING) final String sortOrder)
			throws CMSItemNotFoundException
	{
		if (validateShoppingListName(shoppingListName, model))
		{
			final WishlistData shoppingList = amwayApacWishlistFacade.createWishlist(shoppingListName);
			if (shoppingList == null) // happens when there is already existing Shopping list with same name
			{
				GlobalMessages.addErrorMessage(model,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_NAME_ALREADY_EXISTS);
			}
			else // success
			{
				GlobalMessages.addConfMessage(model,
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
			GlobalMessages.addErrorMessage(model, ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_CREATE_NAME_EMPTY);
		}
		else if (shoppingListName.length() > getSiteConfigService().getInt(SHOPPING_LIST_CONFIGURED_MAX_LENGTH,
				AmwayapacCoreConstants.SHOPPING_LIST_DEFAULT_MAX_LENGTH)) //check if shopping list name exceeds max length
		{
			isShoppingListNameValid = false;
			GlobalMessages.addErrorMessage(model,
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
	@RequireHardLogIn
	@RequestMapping(value = SHOPPING_LIST_DETAILS_PAGE_URL, method = RequestMethod.GET)
	public String shoppingListDetails(final Model model, @PathVariable("shoppingListUid") final String shoppingListUid)
			throws CMSItemNotFoundException
	{
		populateShoppingListDetailsData(model, shoppingListUid, Boolean.FALSE);
		populateShoppingListDetailsPage(model);
		return ControllerConstants.Views.Pages.ShoppingList.ShoppingListDetailsPage;
	}

	/**
	 * Populates the shopping list details data in the model.
	 *
	 * @param model
	 *           model
	 * @param shoppingListUid
	 *           uid of the shopping list to be displayed
	 * @param isAjax
	 *           if the response is supposed to be ajax or not
	 * @throws CMSItemNotFoundException
	 *            if the shopping lists cms page is not found.
	 */
	private void populateShoppingListDetailsData(final Model model, final String shoppingListUid, final Boolean isAjax)
	{
		if (StringUtils.isBlank(shoppingListUid))
		{
			GlobalMessages.addErrorMessage(model, ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_NOT_FOUND);
		}
		else
		{
			try
			{
				model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LIST_DATA,
						amwayApacWishlistFacade.getWishlistByUidForCurrentUser(shoppingListUid));
			}
			catch (final AmbiguousIdentifierException aIE)
			{
				GlobalMessages.addErrorMessage(model,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_AMBIGUOUS_UID);
				LOGGER.error(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT)
						.append("More than one shopping list found with uid [").append(shoppingListUid).append("].").toString(), aIE);
			}
			catch (final UnknownIdentifierException uIE)
			{
				GlobalMessages.addErrorMessage(model,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_NOT_FOUND);
				LOGGER.error(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT).append("No shopping list found with uid [")
						.append(shoppingListUid).append("].").toString(), uIE);

			}
		}
	}

	/**
	 * Populates the shopping list details CMS page, title and bread-crumbs in the model
	 *
	 * @param model
	 *           view model
	 * @throws CMSItemNotFoundException
	 *            if the cms page is not found
	 */
	private void populateShoppingListDetailsPage(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute(new AddToCartOrderForm());
		model.addAttribute("sortOptions", AmwayApacCartSortCode.values());
		storeCmsPageInModel(model, getContentPageForLabelOrId(ControllerConstants.GeneralConstants.SHOPPING_LIST_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model,
				getContentPageForLabelOrId((ControllerConstants.GeneralConstants.SHOPPING_LIST_DETAILS_CMS_PAGE)));
		model.addAttribute(ControllerConstants.GeneralConstants.BREADCRUMBS_ATTR, shoppingListDetailsBreadcrumbBuilder
				.getBreadcrumbs((ControllerConstants.GeneralConstants.SHOPPING_LIST_DETAILS_PAGE_BREADCRUMB_KEY)));
	}

	/**
	 * Controller method to add product to shopping list
	 *
	 * @param productCode
	 *           code of the product
	 * @param shoppingListUid
	 *           uid of the shopping list
	 * @param model
	 *           view model
	 * @return the view
	 * @throws CMSItemNotFoundException
	 */
	@RequireHardLogIn
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String addProductToFavoriteList(@RequestParam(value = "productCode") final String productCode,
			@RequestParam(name = "shoppingListUid") final String shoppingListUid, final Model model) throws CMSItemNotFoundException
	{
		if (StringUtils.isBlank(productCode))
		{
			GlobalMessages.addErrorMessage(model,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_ADD_PRODUCT_CODE_EMPTY);
		}
		else if (StringUtils.isBlank(shoppingListUid))
		{
			GlobalMessages.addErrorMessage(model,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_ADD_PRODUCT_LIST_UID_EMPTY);
		}
		else
		{
			final AmwayApacWishListModification modification = amwayApacWishlistFacade.addProductToWishlist(productCode,
					shoppingListUid);
			populateMessageForModificationStatus(modification.getStatus(), model,
					ControllerConstants.GeneralConstants.SHOPPING_LIST_ADD_PRODUCT_MESSAGES_PREFIX);
			if (AmwayApacWishlistModificationStatus.SUCCESS.equals(modification.getStatus()))
			{
				populateShoppingListDetailsData(model, shoppingListUid, Boolean.TRUE);
				populateShoppingListDetailsPage(model);
			}
		}
		return ControllerConstants.Views.Fragments.ShoppingList.UpdateProductInShoppingListResponse;
	}

	/**
	 * Controller method to sort shopping list details product
	 *
	 * @param model
	 *           view model
	 * @return the view
	 * @throws CMSItemNotFoundException
	 */
	@RequireHardLogIn
	@RequestMapping(value = SHOPPING_LIST_DETAILS_SORT_PAGE_URL, method = RequestMethod.GET)
	public String sortShoppingListDetailsList(@RequestParam(name = "shoppingListUid") final String shoppingListUid,
			final Model model, @RequestParam(name = "sortBy", required = false) final String sortBy) throws CMSItemNotFoundException
	{
		if (StringUtils.isBlank(shoppingListUid))
		{
			GlobalMessages.addErrorMessage(model, ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_NOT_FOUND);
		}
		else
		{
			final AmwayApacCartSortCode selectedSort = StringUtils.isNotBlank(sortBy) ? AmwayApacCartSortCode.valueOf(sortBy)
					: AmwayApacCartSortCode.LAST_ITEM_ADDED;
			model.addAttribute("selectedSort", selectedSort);
			final WishlistData shoppingListData = amwayApacWishlistFacade.getWishlistByUidForCurrentUser(shoppingListUid);
			if (Objects.nonNull(shoppingListData))
			{

				wishlistFacade.getShoppingListDetailsSortBySortCode(selectedSort, shoppingListData);
			}
			model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LIST_DATA, shoppingListData);
			populateShoppingListDetailsPage(model);

		}
		return ControllerConstants.Views.Fragments.ShoppingList.SortProductInShoppingListDetailsResponse;


	}

	/**
	 * Controller method to add multiple product to shopping list
	 *
	 * @param productCode
	 *           code of the product
	 * @param uid
	 *           of the shopping list
	 * @param model
	 *           view model
	 * @return the view
	 * @throws CMSItemNotFoundException
	 */
	@RequireHardLogIn
	@RequestMapping(value = SHOPPING_LIST_MULTIPRODUCT_DETAILS_URL, method = RequestMethod.POST)
	public String addMultiProductToFavoriteList(final AmwayApacMultiShoppingListUpdateForm multiShoppingListUpdateForm,
			final Model model) throws CMSItemNotFoundException
	{
		if (CollectionUtils.isEmpty(multiShoppingListUpdateForm.getProductCodes()))
		{
			GlobalMessages.addErrorMessage(model,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_ADD_PRODUCT_CODE_EMPTY);
		}
		else if (CollectionUtils.isEmpty(multiShoppingListUpdateForm.getShoppingLists()))
		{
			GlobalMessages.addErrorMessage(model,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_ADD_PRODUCT_LIST_UID_EMPTY);
		}
		else
		{
			final List<AmwayApacWishListModification> modifications = new ArrayList<>();
			multiShoppingListUpdateForm.getProductCodes().forEach(pList -> multiShoppingListUpdateForm.getShoppingLists().stream()
					.filter(s -> BooleanUtils.isTrue(s.getSelected())).forEach(sList -> {

						final AmwayApacWishListModification modification = amwayApacWishlistFacade.addProductToWishlist(pList,
								sList.getShoppingListUid());

						final Object[] shoppingListName =
						{ sList.getShoppingListName() };

						if (AmwayApacWishlistModificationStatus.SUCCESS.equals(modification.getStatus()))
						{
							GlobalMessages
									.addConfMessage(model,
											getMessageSource().getMessage(
													new StringBuilder(AmwayapacCoreConstants.FIFTY_INT)
															.append(
																	ControllerConstants.GeneralConstants.SHOPPING_LIST_ADD_MULTIPLE_PRODUCT_MESSAGES_PREFIX)
															.append(modification.getStatus().toString().toLowerCase()).toString(),
													shoppingListName, getI18nService().getCurrentLocale()));
						}

						else
						{
							GlobalMessages
									.addErrorMessage(model,
											getMessageSource().getMessage(
													new StringBuilder(AmwayapacCoreConstants.FIFTY_INT)
															.append(
																	ControllerConstants.GeneralConstants.SHOPPING_LIST_ADD_MULTIPLE_PRODUCT_MESSAGES_PREFIX)
															.append(modification.getStatus().toString().toLowerCase()).toString(),
													shoppingListName, getI18nService().getCurrentLocale()));
						}

						if (modification.getEntry() != null)
						{
							modifications.add(modification);
						}

					}));

			model.addAttribute(ControllerConstants.ModelParameters.MODIFICATION, modifications);



		}
		return ControllerConstants.Views.Fragments.ShoppingList.AddmultipleProductToShoppingListResponse;
	}


	/**
	 * Populates the success or error message in the model based on the status by appending the prefix for the message
	 * key.
	 *
	 * @param modificationStatus
	 *           modification status
	 * @param model
	 *           view model
	 * @param prefix
	 *           prefix to create message key.
	 */
	private void populateMessageForModificationStatus(final AmwayApacWishlistModificationStatus modificationStatus,
			final Model model, final String prefix)
	{
		if (AmwayApacWishlistModificationStatus.SUCCESS.equals(modificationStatus))
		{
			GlobalMessages.addConfMessage(model, new StringBuilder(AmwayapacCoreConstants.FIFTY_INT).append(prefix)
					.append(modificationStatus.toString().toLowerCase()).toString());
		}
		else
		{
			GlobalMessages.addErrorMessage(model, new StringBuilder(AmwayapacCoreConstants.FIFTY_INT).append(prefix)
					.append(modificationStatus.toString().toLowerCase()).toString());
		}
	}

	/**
	 * Controller method to edit the name of the shopping list whose uid is given.
	 *
	 * @param shoppingListUid
	 *           uid of the shopping list whose name has to be updated
	 * @param shoppingListName
	 *           new name of the shopping list
	 * @param model
	 *           view model
	 * @return the view
	 */
	@RequireHardLogIn
	@RequestMapping(value = "/update-shopping-list-name", method = RequestMethod.POST)
	public String updateShoppingListName(@RequestParam(value = "shoppingListUid") final String shoppingListUid,
			@RequestParam(value = "shoppingListName") final String shoppingListName, final Model model)
	{
		if (StringUtils.isBlank(shoppingListUid))
		{
			GlobalMessages.addErrorMessage(model, ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_NOT_FOUND);
		}
		else if (validateShoppingListName(shoppingListName, model))
		{
			final AmwayApacWishlistModificationStatus modificationStatus = amwayApacWishlistFacade
					.updateWishlistNameAndReturnStatus(shoppingListUid, shoppingListName);
			model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LIST_NAME, shoppingListName);
			populateMessageForModificationStatus(modificationStatus, model,
					ControllerConstants.GeneralConstants.SHOPPING_LIST_UPDATE_NAME_MESSGAES_PREFIX);
		}
		return ControllerConstants.Views.Fragments.ShoppingList.UpdateShoppingListNameResponse;
	}

	/**
	 * Controller method to remove product from shopping list
	 *
	 * @param productCode
	 *           code of the product
	 * @param shoppingListUid
	 *           uid of the shopping list
	 * @param model
	 *           view model
	 * @return the view
	 * @throws CMSItemNotFoundException
	 */
	@RequireHardLogIn
	@RequestMapping(value = "/remove-product", method = RequestMethod.POST)
	public String removeProductFromFavoriteList(@RequestParam(value = "productCode") final String productCode,
			@RequestParam(name = "shoppingListUid") final String shoppingListUid, final Model model) throws CMSItemNotFoundException
	{
		if (StringUtils.isBlank(productCode))
		{
			GlobalMessages.addErrorMessage(model,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_ADD_PRODUCT_CODE_EMPTY);
		}
		else if (StringUtils.isBlank(shoppingListUid))
		{
			GlobalMessages.addErrorMessage(model,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_ADD_PRODUCT_LIST_UID_EMPTY);
		}
		else
		{
			try
			{
				final WishlistData shoppingListData = amwayApacWishlistFacade.removeFromWishList(shoppingListUid, productCode);
				model.addAttribute(ControllerConstants.ModelParameters.SHOPPING_LIST_DATA, shoppingListData);
				GlobalMessages.addConfMessage(model,
						ControllerConstants.SuccessMessageKeys.ShoppingList.SHOPPING_LIST_REMOVE_PRODUCT_SUCCESS_MESSAGE);
				populateShoppingListDetailsPage(model);
			}
			catch (final UnknownIdentifierException uIE)
			{
				LOGGER.error(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT).append("No shopping list found with uid [")
						.append(shoppingListUid).append("].").toString(), uIE);
				GlobalMessages.addErrorMessage(model,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_NOT_FOUND);
			}
			catch (final AmbiguousIdentifierException aIE)
			{
				LOGGER.error(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT)
						.append("More than one shopping list found with uid [").append(shoppingListUid).append("].").toString(), aIE);
				GlobalMessages.addErrorMessage(model,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_AMBIGUOUS_UID);
			}
		}
		return ControllerConstants.Views.Fragments.ShoppingList.UpdateProductInShoppingListResponse;
	}

	/**
	 * Controller method to remove product from shopping list
	 *
	 * @param productCode
	 *           code of the product
	 * @param shoppingListUid
	 *           uid of the shopping list
	 * @param model
	 *           view model
	 * @return the view
	 * @throws CMSItemNotFoundException
	 */
	@RequireHardLogIn
	@RequestMapping(value = "/remove-shopping-list", method = RequestMethod.POST)
	public String removeShoppingList(@RequestParam(name = "shoppingListUid") final String shoppingListUid, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (StringUtils.isBlank(shoppingListUid))
		{
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
					ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_ADD_PRODUCT_LIST_UID_EMPTY);
		}
		else
		{
			try
			{
				amwayApacWishlistFacade.deleteWishlist(shoppingListUid);
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
						ControllerConstants.SuccessMessageKeys.ShoppingList.DELETE_SHOPPING_LIST_SUCCESS_MESSAGE);
			}
			catch (final UnknownIdentifierException uIE)
			{
				LOGGER.error(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT).append("No shopping list found with uid [")
						.append(shoppingListUid).append("].").toString(), uIE);

				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_NOT_FOUND);
			}
			catch (final AmbiguousIdentifierException aIE)
			{
				LOGGER.error(new StringBuilder(AmwayapacCoreConstants.HUNDRED_INT)
						.append("More than one shopping list found with uid [").append(shoppingListUid).append("].").toString(), aIE);
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
						ControllerConstants.ErrorMessageKeys.ShoppingList.SHOPPING_LIST_DETAILS_AMBIGUOUS_UID);
			}
		}
		return SHOPPING_LISTS_PAGE_REDIRECT;
	}

}