package com.amway.apac.facades.wishlist;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;
import com.amway.apac.facades.wishlist.data.AmwayApacWishListModification;
import com.amway.facades.product.data.WishlistData;
import com.amway.facades.wishlist.AmwayWishlistFacade;
import com.amway.facades.wishlist.modification.status.AmwayApacWishlistModificationStatus;


/**
 * Overriding {@link AmwayWishlistFacade} to add apac specific functionalities.
 *
 * @author Parvesh Goyal
 *
 */
public interface AmwayApacWishlistFacade extends AmwayWishlistFacade
{

	/**
	 * Fetches all the wish lists for current customer in current site. The lists are sorted so that the favorite list is
	 * the first one and the remaining ones are sorted so that last modified ones come first. List are populated with
	 * wishlist data.
	 *
	 * @param sortField
	 *           field according to which sorting is to be done, if null then sorting will be done via last modified
	 * @param sortOrder
	 *           decides whether sorting will be ascending or descending, if null then descending order will be displayed
	 *
	 * @return wish lists found.
	 */
	List<WishlistData> getAllWishlistsWithBasicData(final String sortField, final String sortOrder);

	/**
	 * Gets the wish list according to uid
	 *
	 * @param uid
	 *           to identify the wish list
	 *
	 * @return wish list model
	 * @throws IllegalArgumentException
	 *            if the uid is null or empty
	 * @throws UnknownIdentifierException
	 *            if no wishist is found
	 * @throws AmbiguousIdentifierException
	 *            if more than one wishlists found with the uid
	 */
	WishlistData getWishlistByUidForCurrentUser(final String uid);

	/**
	 * Adds the product whose code is given to the shopping list whose uid is given.
	 *
	 * @param productCode
	 *           code of the product to add.
	 * @param wishlistUid
	 *           uid of the wishlist to which product is to be added.
	 *
	 * @throws IllegalArgumentException
	 *            if product code is null or empty, or if wishlistUid is null or empty
	 * @return {@link AmwayApacWishListModification} based on whether add to shopping list was successful or not.
	 */
	AmwayApacWishListModification addProductToWishlist(final String productCode, final String wishlistUid);

	/**
	 * Edits the name of the wishlist and returns the modification status.
	 *
	 * @param uid
	 *           to identify the wishlist whose name is going to be edited
	 * @param newName
	 *           the new name that will be assigned to the wishlist
	 * @return update name modification status
	 *
	 * @throws IllegalArgumentException
	 *            if uid or name is null or empty.
	 */
	AmwayApacWishlistModificationStatus updateWishlistNameAndReturnStatus(final String uid, final String newName);

	/**
	 * Returns the passed {@link WishlistData} with entries sorted in the way corresponding to the passed sortBy value.
	 *
	 * @param data
	 *           WishlistData
	 * @param sortBy
	 *           Sort Code
	 * @return CartData This method accepts CartData and sort by and returns CartData in given order.
	 */
	WishlistData getShoppingListDetailsSortBySortCode(final AmwayApacCartSortCode sortBy, final WishlistData data);

}
