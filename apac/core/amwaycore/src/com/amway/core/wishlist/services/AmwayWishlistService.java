package com.amway.core.wishlist.services;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2Model;


/**
 * Interface for wish lists related functionalities.
 *
 * @author Parvesh Goyal
 */
public interface AmwayWishlistService extends Wishlist2Service
{
	/**
	 * Updates name of the wish list given
	 *
	 * @param wishlist
	 *           wish list whose name is to be updated.
	 * @param newName
	 *           new name of the wish list
	 *
	 * @return updated wish list
	 */
	Wishlist2Model updateWishlistName(final Wishlist2Model wishlist, final String newName);

	/**
	 * Finds the favorite wish list for current customer in current site.
	 *
	 * @return wish list found
	 */
	Wishlist2Model getFavoriteWishlist();

	/**
	 * Saves the given wish list as the favorite wish list for current customer in current site.
	 *
	 * @param wishlist
	 *           wish list to be saved as favorite
	 *
	 * @return the wish list just saved as favorite wish list
	 */
	Wishlist2Model saveAsFavoriteWishlist(final Wishlist2Model wishlist);

	/**
	 * Deletes the shopping list
	 *
	 * @param uid
	 *           to identify the wish list
	 */
	boolean deleteWishlist(final String uid);

	/**
	 * Checks whether the product is already present in the wish list
	 *
	 * @param productModel
	 *           product
	 *
	 * @param wishlist2Model
	 *           wish list
	 * @return t
	 */
	boolean hasWishlistEntryForProduct(final ProductModel productModel, final Wishlist2Model wishlist2Model);

	/**
	 * Gets the wish list according to uid
	 *
	 * @param uid
	 *           to identify the wish list
	 *
	 * @return wish list model
	 */
	Wishlist2Model getWishlistByUid(final String uid);

	/**
	 * Fetches the favorite wish list for the current customer. If current customer does not have a favorite wish list,
	 * the default wish list is set as favorite wish list. If current customer does not have a default wish list, then an
	 * empty wish list is created, set as default and favorite and returned.
	 *
	 * @return favorite wish list of current customer.
	 */
	Wishlist2Model getOrCreateFavoriteWishlist();

	/**
	 * Fetches the wish list for the current customer with name.
	 *
	 * @return wish list with name.
	 */

	Wishlist2Model getWishlistByName(final String name);
}
