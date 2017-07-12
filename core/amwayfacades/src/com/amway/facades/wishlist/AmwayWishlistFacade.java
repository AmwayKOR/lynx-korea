package com.amway.facades.wishlist;

import java.util.List;

import com.amway.facades.product.data.WishlistData;


/**
 * Facade interface for wish list related functionalities.
 *
 * @author Viktar_Yarmalovich.
 */
/**
 *
 */
public interface AmwayWishlistFacade
{

	/**
	 * Adds the product whose code is given to the default wish list of current customer. If current customer does not
	 * have a default wish list, then an empty wish list is created, set as default, the product given is added to it and
	 * the updated wish list is returned.
	 *
	 * @param productCode
	 *           code of the product to be added.
	 * @param desired
	 *           quantity to add
	 * @return updated wish list.
	 */
	WishlistData addToWishList(final String productCode, Integer desired);

	/**
	 * Fetches the default wish list for current customer. If current customer does not have a default wish list, then an
	 * empty wish list is created, set as default and returned.
	 *
	 * @return default wish list of current customer.
	 */
	WishlistData getWishList();


	/**
	 * Fetches the wish list for the current customer with the given Uid.
	 *
	 * @return wishlist with the given uid.
	 */
	WishlistData getWishlistByUid(final String uid);


	/**
	 * Removes the wish product whose code is given from the default wish list of current user. If current user does not
	 * have a default wish list, then an empty wish list is created, set as default and returned.
	 *
	 * @param productCode
	 *           code of the product to be removed from the wish list
	 *
	 * @return updated wish list.
	 */
	WishlistData removeFromWishList(final String productCode);

	/**
	 * Removes the wish product whose code is given from the default wish list of current user. If current user does not
	 * have a default wish list, then an empty wish list is created, set as default and returned.
	 *
	 * @param wishlistUid
	 *           to identify the shopping list from which the product is to be removed
	 * @param productCode
	 *           code of the product to be removed from the wish list
	 *
	 * @return updated wish list.
	 */
	WishlistData removeFromWishList(final String wishlistUid, String productCode);

	/**
	 * Removes the wish product whose code is given from the default wish list of current user. If current user does not
	 * have a default wish list, then an empty wish list is created, set as default and returned.
	 *
	 * @param productCode
	 *           code of the product to be removed from the wish list
	 *
	 * @return updated wish list.
	 */
	WishlistData removeProductFromFavoriteWishList(String productCode);

	/**
	 * Fetches all the wish lists for current customer in current site. The lists are sorted so that the favorite list is
	 * the first one and the remaining ones are sorted so that last modified ones come first.
	 *
	 * @return wish lists found.
	 */
	List<WishlistData> getAllWishlists();

	/**
	 * Creates a new wish list for current customer in current site.
	 *
	 * @param wishlistName
	 *           name for which wish list is to be created.
	 * @return wish list created.
	 */
	WishlistData createWishlist(final String wishlistName);

	/**
	 * Edits the name of the wishlist
	 *
	 * @param uid
	 *           to identify the wishlist whose name is going to be edited
	 * @param newName
	 *           the new name that will be assigned to the wishlist
	 * @return modified wish list
	 */
	WishlistData updateWishlistName(String uid, String newName);

	/**
	 * Deletes the shopping list
	 *
	 * @param uid
	 *           to identify the wishlist
	 */
	boolean deleteWishlist(String uid);

	/**
	 * Sets the wishlist status to favorite
	 *
	 * @param uid
	 *           to identify the wishlist
	 * @return the favorite wish list
	 */
	WishlistData setFavoriteWishlist(String uid);

	/**
	 * @param productcode
	 *
	 * @throws IllegalArgumentException
	 *            if product code is null or empty
	 * @return true if product is added to wish list
	 */
	boolean addProductToWishlist(final String productcode);

	/**
	 * Fetches the favorite wish list for the current customer. If current customer does not have a favorite wish list,
	 * the default wish list is set as favorite wish list. If current customer does not have a default wish list, then an
	 * empty wish list is created, set as default and favorite and returned.
	 *
	 * @return favorite wish list of current customer.
	 */
	WishlistData getFavoriteWishlist();
}
