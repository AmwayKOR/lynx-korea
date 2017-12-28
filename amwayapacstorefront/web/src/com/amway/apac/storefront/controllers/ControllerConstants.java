/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.apac.storefront.controllers;

import de.hybris.platform.acceleratorcms.model.components.CMSTabParagraphContainerModel;
import de.hybris.platform.acceleratorcms.model.components.CartSuggestionComponentModel;
import de.hybris.platform.acceleratorcms.model.components.CategoryFeatureComponentModel;
import de.hybris.platform.acceleratorcms.model.components.DynamicBannerComponentModel;
import de.hybris.platform.acceleratorcms.model.components.NavigationBarComponentModel;
import de.hybris.platform.acceleratorcms.model.components.ProductFeatureComponentModel;
import de.hybris.platform.acceleratorcms.model.components.ProductReferencesComponentModel;
import de.hybris.platform.acceleratorcms.model.components.PurchasedCategorySuggestionComponentModel;
import de.hybris.platform.acceleratorcms.model.components.SimpleResponsiveBannerComponentModel;
import de.hybris.platform.acceleratorcms.model.components.SubCategoryListComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;

import com.amway.apac.storefront.components.model.AmwayApacAccountProfileBarLinkComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacArtistryDealCarouselComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacArtistryNewLuxuryCarouselComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacBuildYourBusinessComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacButtonLinkComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacCategoryTopBannerComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacExperienceTheStarterKitComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacIconLinkComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacImageLinkComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacJoinAmwayComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacNewProductComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacPathToSuccessPitchPerfectComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacPathwayToSuccessComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacPlatinumABOComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacShopByCategoryComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacSupplementsComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacTermsBannerComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacToolsAndAdviceComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacYourOpportunityComponentModel;



/**
 */
public interface ControllerConstants
{
	// Constant names cannot be changed due to their usage in dependant extensions, thus nosonar

	/**
	 * Class with action name constants
	 */
	interface Actions
	{
		interface Cms // NOSONAR
		{
			String _Prefix = "/view/"; // NOSONAR
			String _Suffix = "Controller"; // NOSONAR

			/**
			 * Default CMS component controller
			 */
			String DefaultCMSComponent = _Prefix + "DefaultCMSComponentController"; // NOSONAR

			/**
			 * CMS components that have specific handlers
			 */
			String PurchasedCategorySuggestionComponent = _Prefix + PurchasedCategorySuggestionComponentModel._TYPECODE + _Suffix; // NOSONAR
			String CartSuggestionComponent = _Prefix + CartSuggestionComponentModel._TYPECODE + _Suffix; // NOSONAR
			String ProductReferencesComponent = _Prefix + ProductReferencesComponentModel._TYPECODE + _Suffix; // NOSONAR
			String ProductCarouselComponent = _Prefix + ProductCarouselComponentModel._TYPECODE + _Suffix; // NOSONAR
			String ProductFeatureComponent = _Prefix + ProductFeatureComponentModel._TYPECODE + _Suffix; // NOSONAR
			String CategoryFeatureComponent = _Prefix + CategoryFeatureComponentModel._TYPECODE + _Suffix; // NOSONAR
			String NavigationBarComponent = _Prefix + NavigationBarComponentModel._TYPECODE + _Suffix; // NOSONAR
			String CMSLinkComponent = _Prefix + CMSLinkComponentModel._TYPECODE + _Suffix; // NOSONAR
			String DynamicBannerComponent = _Prefix + DynamicBannerComponentModel._TYPECODE + _Suffix; // NOSONAR
			String SubCategoryListComponent = _Prefix + SubCategoryListComponentModel._TYPECODE + _Suffix; // NOSONAR
			String SimpleResponsiveBannerComponent = _Prefix + SimpleResponsiveBannerComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacShopByCategoryComponent = _Prefix + AmwayApacShopByCategoryComponentModel._TYPECODE + _Suffix; // NOSONAR
			String CMSTabParagraphContainer = _Prefix + CMSTabParagraphContainerModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacButtonLinkComponent = _Prefix + AmwayApacButtonLinkComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacIconLinkComponent = _Prefix + AmwayApacIconLinkComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacImageLinkComponent = _Prefix + AmwayApacImageLinkComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacAccountProfileBarLinkComponent = _Prefix + AmwayApacAccountProfileBarLinkComponentModel._TYPECODE
					+ _Suffix; // NOSONAR
			String AmwayApacCategoryTopBannerComponent = _Prefix + AmwayApacCategoryTopBannerComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacToolsAndAdviceComponent = _Prefix + AmwayApacToolsAndAdviceComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacPathToSuccessPitchPerfectComponent = _Prefix + AmwayApacPathToSuccessPitchPerfectComponentModel._TYPECODE
					+ _Suffix; // NOSONAR
			String AmwayApacPathwayToSuccessComponent = _Prefix + AmwayApacPathwayToSuccessComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacBuildYourBusinessComponent = _Prefix + AmwayApacBuildYourBusinessComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacPlatinumABOComponent = _Prefix + AmwayApacPlatinumABOComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacExperienceTheStarterKitComponent = _Prefix + AmwayApacExperienceTheStarterKitComponentModel._TYPECODE
					+ _Suffix; // NOSONAR
			String AmwayApacJoinAmwayComponent = _Prefix + AmwayApacJoinAmwayComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacArtistryDealCarouselComponent = _Prefix + AmwayApacArtistryDealCarouselComponentModel._TYPECODE
					+ _Suffix; // NOSONAR
			String AmwayApacArtistryNewLuxuryCarouselComponent = _Prefix + AmwayApacArtistryNewLuxuryCarouselComponentModel._TYPECODE
					+ _Suffix; // NOSONAR
			String AmwayApacNewProductComponent = _Prefix + AmwayApacNewProductComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacSupplementsComponent = _Prefix + AmwayApacSupplementsComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacYourOpportunityComponent = _Prefix + AmwayApacYourOpportunityComponentModel._TYPECODE + _Suffix; // NOSONAR
			String AmwayApacTermsBannerComponent = _Prefix + AmwayApacTermsBannerComponentModel._TYPECODE + _Suffix; // NOSONAR
		}
	}

	/**
	 * Class with view name constants
	 */
	interface Views
	{
		interface Cms // NOSONAR
		{
			String ComponentPrefix = "cms/"; // NOSONAR
		}

		interface Pages
		{
			interface Account // NOSONAR
			{
				String AccountLoginPage = "pages/account/accountLoginPage"; // NOSONAR
				String AccountHomePage = "pages/account/accountHomePage"; // NOSONAR
				String AccountOrderHistoryPage = "pages/account/accountOrderHistoryPage"; // NOSONAR
				String AccountOrderPage = "pages/account/accountOrderPage"; // NOSONAR
				String AccountProfilePage = "pages/account/accountProfilePage"; // NOSONAR
				String AccountProfileEditPage = "pages/account/accountProfileEditPage"; // NOSONAR
				String AccountProfileEmailEditPage = "pages/account/accountProfileEmailEditPage"; // NOSONAR
				String AccountChangePasswordPage = "pages/account/accountChangePasswordPage"; // NOSONAR
				String AccountAddressBookPage = "pages/account/accountAddressBookPage"; // NOSONAR
				String AccountEditAddressPage = "pages/account/accountEditAddressPage"; // NOSONAR
				String AccountPaymentInfoPage = "pages/account/accountPaymentInfoPage"; // NOSONAR
				String AccountRegisterPage = "pages/account/accountRegisterPage"; // NOSONAR
			}

			interface Checkout // NOSONAR
			{
				String CheckoutRegisterPage = "pages/checkout/checkoutRegisterPage"; // NOSONAR
				String CheckoutConfirmationPage = "pages/checkout/checkoutConfirmationPage"; // NOSONAR
				String CheckoutLoginPage = "pages/checkout/checkoutLoginPage"; // NOSONAR
			}

			interface MultiStepCheckout // NOSONAR
			{
				String AddEditDeliveryAddressPage = "pages/checkout/multi/addEditDeliveryAddressPage"; // NOSONAR
				String ChooseDeliveryMethodPage = "pages/checkout/multi/chooseDeliveryMethodPage"; // NOSONAR
				String ChoosePickupLocationPage = "pages/checkout/multi/choosePickupLocationPage"; // NOSONAR
				String AddPaymentMethodPage = "pages/checkout/multi/addPaymentMethodPage"; // NOSONAR
				String CheckoutSummaryPage = "pages/checkout/multi/checkoutSummaryPage"; // NOSONAR
				String HostedOrderPageErrorPage = "pages/checkout/multi/hostedOrderPageErrorPage"; // NOSONAR
				String HostedOrderPostPage = "pages/checkout/multi/hostedOrderPostPage"; // NOSONAR
				String SilentOrderPostPage = "pages/checkout/multi/silentOrderPostPage"; // NOSONAR
				String GiftWrapPage = "pages/checkout/multi/giftWrapPage"; // NOSONAR
			}

			interface Password // NOSONAR
			{
				String PasswordResetChangePage = "pages/password/passwordResetChangePage"; // NOSONAR
				String PasswordResetRequest = "pages/password/passwordResetRequestPage"; // NOSONAR
				String PasswordResetRequestConfirmation = "pages/password/passwordResetRequestConfirmationPage"; // NOSONAR
			}

			interface Error // NOSONAR
			{
				String ErrorNotFoundPage = "pages/error/errorNotFoundPage"; // NOSONAR
			}

			interface Cart // NOSONAR
			{
				String CartPage = "pages/cart/cartPage"; // NOSONAR
				String CartContentPage = "pages/cart/cartContent";
			}

			interface StoreFinder // NOSONAR
			{
				String StoreFinderSearchPage = "pages/storeFinder/storeFinderSearchPage"; // NOSONAR
				String StoreFinderDetailsPage = "pages/storeFinder/storeFinderDetailsPage"; // NOSONAR
				String StoreFinderViewMapPage = "pages/storeFinder/storeFinderViewMapPage"; // NOSONAR
			}

			interface Misc // NOSONAR
			{
				String MiscRobotsPage = "pages/misc/miscRobotsPage"; // NOSONAR
				String MiscSiteMapPage = "pages/misc/miscSiteMapPage"; // NOSONAR
			}

			interface Guest // NOSONAR
			{ // NOSONAR
				String GuestOrderPage = "pages/guest/guestOrderPage"; // NOSONAR
				String GuestOrderErrorPage = "pages/guest/guestOrderErrorPage"; // NOSONAR
			}

			interface Product // NOSONAR
			{
				String WriteReview = "pages/product/writeReview"; // NOSONAR
				String OrderForm = "pages/product/productOrderFormPage"; // NOSONAR
			}

			interface QuickOrder // NOSONAR
			{
				String QuickOrderPage = "pages/quickOrder/quickOrderPage"; // NOSONAR
			}

			interface CSV // NOSONAR
			{
				String ImportCSVSavedCartPage = "pages/csv/importCSVSavedCartPage"; // NOSONAR
			}

			interface ShoppingList //NOSONAR
			{
				String ShoppingListDetailsPage = "pages/shoppingList/shoppingListDetailsPage"; //NOSONAR
			}

			interface Registration //NOSONAR
			{
				String RegistrationSimpleTerms = "pages/registration/registrationSimpleTerms"; //NOSONAR
			}
		}

		interface Fragments
		{
			interface Cart // NOSONAR
			{
				String AddToCartPopup = "fragments/cart/addToCartPopup"; // NOSONAR
				String MiniCartPanel = "fragments/cart/miniCartPanel"; // NOSONAR
				String MiniCartErrorPanel = "fragments/cart/miniCartErrorPanel"; // NOSONAR
				String CartPopup = "fragments/cart/cartPopup"; // NOSONAR
				String ExpandGridInCart = "fragments/cart/expandGridInCart"; // NOSONAR
			}

			interface Account // NOSONAR
			{
				String CountryAddressForm = "fragments/address/countryAddressForm"; // NOSONAR
				String ShippingAddressDetail = "fragments/address/shippingAddressDetail"; // NOSONAR
				String ShippingAddressBody = "fragments/address/shippingAddressBody"; // NOSONAR
				String SavedCartRestorePopup = "fragments/account/savedCartRestorePopup"; // NOSONAR
				String orderDetailsDetails = "fragments/account/orderDetails"; // NOSONAR
			}

			interface Checkout // NOSONAR
			{
				String TermsAndConditionsPopup = "fragments/checkout/termsAndConditionsPopup"; // NOSONAR
				String BillingAddressForm = "fragments/checkout/billingAddressForm"; // NOSONAR
				String ReadOnlyExpandedOrderForm = "fragments/checkout/readOnlyExpandedOrderForm"; // NOSONAR
				String DeliveryAddressListPage = "fragments/checkout/deliveryAddressList"; // NOSONAR
			}

			interface Password // NOSONAR
			{
				String PasswordResetRequestPopup = "fragments/password/passwordResetRequestPopup"; // NOSONAR
				String ForgotPasswordValidationMessage = "fragments/password/forgotPasswordValidationMessage"; // NOSONAR
			}

			interface Product // NOSONAR
			{
				String FutureStockPopup = "fragments/product/futureStockPopup"; // NOSONAR
				String QuickViewPopup = "fragments/product/quickViewPopup"; // NOSONAR
				String ZoomImagesPopup = "fragments/product/zoomImagesPopup"; // NOSONAR
				String ReviewsTab = "fragments/product/reviewsTab"; // NOSONAR
				String StorePickupSearchResults = "fragments/product/storePickupSearchResults"; // NOSONAR
			}

			interface Category
			{
				String ProductListingFragment = "fragments/category/productListingFragment"; //NOSONAR
			}

			/**
			 * Interface for Shopping List related fragments
			 *
			 * @author Parvesh Goyal
			 *
			 */
			interface ShoppingList
			{
				/**
				 * Complete shopping lists page view
				 */
				String ShoppingListsPageFragment = "fragments/shoppingList/shoppingListsPage"; //NOSONAR

				/**
				 * Response UI when product is updated in the shopping list.
				 */
				String UpdateProductInShoppingListResponse = "fragments/shoppingList/updateProductInShoppingListResponse"; //NOSONAR

				/**
				 * Response UI when shopping list name is updated.
				 */
				String UpdateShoppingListNameResponse = "fragments/shoppingList/updateShoppingListNameResponse"; //NOSONAR

				/**
				 * Response UI when product is updated in the shopping list.
				 */
				String AddmultipleProductToShoppingListResponse = "fragments/shoppingList/addMultipleProductToShoppingListResponse"; //NOSONAR

				/**
				 * Response UI when shopping list is updated.
				 */
				String AddToShoppingListPopUp = "fragments/shoppingList/addToShoppingListPopUp"; //NOSONAR
			}
		}
	}

	/**
	 * Interface to group together the controller model parameters
	 *
	 * @author Parvesh goyal
	 *
	 */
	interface ModelParameters
	{
		/**
		 * String constant numberOfOrders
		 */
		String NUMBER_OF_ORDERS = "numberOfOrders";

		/**
		 * String constant linkUrl
		 */
		String LINK_URL = "linkUrl";

		/**
		 * Shopping lists model attribute
		 */
		String SHOPPING_LISTS_STRING = "shoppingLists";

		/**
		 * Shopping list data model attribute
		 */
		String SHOPPING_LIST_DATA = "shoppingListData";

		/**
		 * Model parameter for shopping list name.
		 */
		String SHOPPING_LIST_NAME = "shoppingListName";

		/**
		 * Model parameter for login error.
		 */
		String LOGIN_ERROR = "loginError";

		/**
		 * Model parameter for quantity.
		 */
		String QUANTITY_ATTR = "quantity";

		/**
		 * Model parameter for customer data.
		 */
		String CUSTOMERDATA_ATTR = "customerData";

		/**
		 * Model parameter for payment info data.
		 */
		String PAYMENTINFO_ATTR = "paymentInfoData";

		/**
		 * Model parameter for supported countries.
		 */
		String SUPPORTEDCOUNTRIES_ATTR = "supportedCountries";

		/**
		 * String constant linkUrl
		 */
		String LINK_URL_STRING = "linkUrl";

		/**
		 * String constant linkUrl
		 */
		String SECONDARY_LINK_URL_STRING = "secondaryLinkUrl";

		/**
		 * String constant linkUrl
		 */
		String MEDIAS_STRING = "medias";

		/**
		 * String constant linkUrl
		 */
		String SECONDARY_MEDIAS_STRING = "secondaryMedias";

		/**
		 * String termForm
		 */
		String TERM_FORM_STRING = "termForm";

		/**
		 * Model parameter for modification.
		 */
		String MODIFICATION = "modification";

		/**
		 * String category name
		 */
		String CATEGORY_NAME = "categoryName";


		/**
		 * String Category Code
		 */
		String CATEGORY_CODE = "categoryCode";

	}

	/**
	 * Interface to group together generic constants for the controllers.
	 *
	 * @author Parvesh goyal
	 *
	 */
	interface GeneralConstants
	{
		/**
		 * Shopping lists cms page id
		 */
		String SHOPPING_LISTS_CMS_PAGE = "shopping-lists";

		/**
		 * Shopping list details page id
		 */
		String SHOPPING_LIST_DETAILS_CMS_PAGE = "shopping-list-details";

		/**
		 * Breadcrumbs Model attribute
		 */
		String BREADCRUMBS_ATTR = "breadcrumbs";

		/**
		 * Shopping list details breadcrumb key
		 */
		String REGISTER_SIMPLE_TERMS_PAGE_BREADCRUMB_KEY = "register.simple.terms.page.breadcrumb";
		/**
		 * Shopping list details breadcrumb key
		 */
		String REGISTER_MULTIPLE_TERMS_PAGE_BREADCRUMB_KEY = "register.multiple.terms.page.breadcrumb";
		/**
		 * Shopping list details breadcrumb key
		 */
		String SHOPPING_LIST_DETAILS_PAGE_BREADCRUMB_KEY = "shopping.list.details.page.breadcrumb";

		/**
		 * Shopping list sorting parameter to sort by name
		 */
		String SHOPPING_LIST_SORT_BY_NAME = "byName";

		/**
		 * Shopping list sorting parameter to sort by lastUpdated.
		 */
		String SHOPPING_LIST_SORT_BY_LAST_UPDATED = "byLastUpdated";

		/**
		 * Prefix appended to status for add product to shopping list messages.
		 */
		String SHOPPING_LIST_ADD_PRODUCT_MESSAGES_PREFIX = "shopping.list.add.product.status.";

		/**
		 * Prefix appended to status for add multiple product to shopping list messages.
		 */
		String SHOPPING_LIST_ADD_MULTIPLE_PRODUCT_MESSAGES_PREFIX = "shopping.list.add.product.status.multiple.";

		/**
		 * Prefix appended to status for add product to shopping list messages.
		 */
		String SHOPPING_LIST_UPDATE_NAME_MESSGAES_PREFIX = "shoppinglist.update.name.status.";

		/**
		 * Billing & Shipping breadcrumb key
		 */
		String BILLING_SHIPPING_PAGE_BREADCRUMB_KEY = "account.billingshipping.page.breadcrumb";

		/**
		 * Business & Information breadcrumb key
		 */
		String BUSINESS_INFORMATION_PAGE_BREADCRUMB_KEY = "account.businessinformation.page.breadcrumb";
	}

	/**
	 * Interface to group together the error message keys.
	 *
	 * @author Parvesh goyal
	 *
	 */
	interface ErrorMessageKeys
	{
		/**
		 * Interface to group Add to cart related error messages
		 *
		 * @author Parvesh Goyal
		 */
		interface AddToCart
		{
			/**
			 * Error message when a negative quantity is given for add to cart.
			 */
			String INVALID_QUANTITY = "basket.error.quantity.invalid";

			/**
			 * Default error message for add to cart.
			 */
			String BASKET_ERROR = "basket.error.occurred";

			/**
			 * Error message for unknown identifier
			 */
			String PRODUCT_NOT_FOUND = "product.not.found.error";
		}

		/**
		 * Interface to group Shopping list related error messages together
		 *
		 * @author Parvesh Goyal
		 */
		interface ShoppingList
		{
			/**
			 * Error message to be displayed when the name is empty.
			 */
			String SHOPPING_LIST_CREATE_NAME_EMPTY = "shopping.list.create.error.empty.name";

			/**
			 * Error message to be displayed when the name exceeds the max length for shopping list name.
			 */
			String SHOPPING_LIST_CREATE_NAME_MAX_LENGTH = "shopping.list.create.error.maxlength.exceeeded";

			/**
			 * Error message to be displayed when the shopping list name already exists.
			 */
			String SHOPPING_LIST_NAME_ALREADY_EXISTS = "shopping.list.create.error.already.exists";

			/**
			 * Error message on shopping list details page when no shopping list with uid is found.
			 */
			String SHOPPING_LIST_DETAILS_NOT_FOUND = "shopping.list.details.error.not.found";

			/**
			 * Error message on shopping list details page when more than one shopping lists exist for the same uid.
			 */
			String SHOPPING_LIST_DETAILS_AMBIGUOUS_UID = "shopping.list.details.error.ambiguous.uid";

			/**
			 * Error message to be displayed on add to Shopping list when product code is empty.
			 */
			String SHOPPING_LIST_ADD_PRODUCT_CODE_EMPTY = "shopping.list.add.product.error.empty.name";

			/**
			 * Error message to be displayed on add to Shopping list when product code is empty.
			 */
			String SHOPPING_LIST_ADD_PRODUCT_LIST_UID_EMPTY = "shopping.list.add.product.error.empty.listuid";

		}

		interface Registration
		{
			/**
			 * Error Message for the case when some of the terms not accepted.
			 */
			String TERMS_ACCEPT_ERROR = "terms.accept.error";

			/**
			 * Error message for the case when no terms are accepted/un-accepted.
			 */
			String NO_TERMS_ACCEPTED = "no.term.accepted";
		}
	}

	/**
	 * Interface to group together success message keys.
	 *
	 * @author Parvesh Goyal
	 */
	interface SuccessMessageKeys
	{
		/**
		 * Interface to group Shopping list related success messages together
		 *
		 * @author Parvesh Goyal
		 *
		 */
		interface ShoppingList
		{
			/**
			 * Success message to be displayed when shopping list is sucessfully created.
			 */
			String SHOPPING_LIST_CREATED_SUCESS_MESSAGE = "shopping.list.create.success";

			/**
			 * Success message to be displayed when a product is removed from the shopping list.
			 */
			String SHOPPING_LIST_REMOVE_PRODUCT_SUCCESS_MESSAGE = "shopping.list.remove.product.success";

			/**
			 * Success message to be displayed when a shopping list is deleted.
			 */
			String DELETE_SHOPPING_LIST_SUCCESS_MESSAGE = "shopping.list.remove.success";
		}
	}


}
