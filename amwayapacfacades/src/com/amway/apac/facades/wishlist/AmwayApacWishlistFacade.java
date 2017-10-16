package com.amway.apac.facades.wishlist;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

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

}
