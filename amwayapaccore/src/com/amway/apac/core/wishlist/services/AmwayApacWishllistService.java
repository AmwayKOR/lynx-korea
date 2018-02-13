package com.amway.apac.core.wishlist.services;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
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

	/**
	 * Gets the wish list according to uid
	 *
	 * @param uid
	 *           to identify the wish list
	 *
	 * @return wish list model
	 *
	 * @throws IllegalArgumentException
	 *            if the uid is null or empty
	 * @throws UnknownIdentifierException
	 *            if no wishist is found
	 * @throws AmbiguousIdentifierException
	 *            if more than one wishlists found with the uid
	 */
	Wishlist2Model getWishlistByUidForCurrentUser(final String uid);


	Wishlist2EntryModel addAndReturnWishlistEntry(final Wishlist2Model wishlist, final ProductModel product, final Integer desired,
			final Wishlist2EntryPriority priority, final String comment);


}
