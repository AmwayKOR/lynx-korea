package com.amway.apac.facades.wishlist;

import java.util.List;

import com.amway.facades.product.data.WishlistData;
import com.amway.facades.wishlist.AmwayWishlistFacade;


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
	 * @return wish lists found.
	 */
	List<WishlistData> getAllWishlistsWithBasicData();

	/**
	 * Makes sure that a favorite wishlist exists for the user in current site. If current customer does not have a
	 * favorite wish list, the default wish list is set as favorite wish list. If current customer does not have a
	 * default wish list, then an empty wish list is created, set as default and favorite and returned.
	 */
	void createFavoriteWishlistIfNeeded();

}
