package com.amway.apac.core.wishlist.services;

import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import com.amway.core.wishlist.services.AmwayWishlistService;


/**
 * Extending {@link AmwayWishlistService} to add more wishlist functionalities
 *
 * @author Parvesh Goyal
 *
 */
public interface AmwayApacWishllistService extends AmwayWishlistService
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
	List<Wishlist2Model> getWishlists(final String sortField, final String sortOrder);

}
