package com.amway.apac.core.wishlist.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.exceptions.FlexibleSearchException;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import com.amway.core.wishlist.daos.AmwayWishlistDao;


/**
 * Extending {@link AmwayWishlistDao} to add more functionalities.
 *
 * @author Parvesh Goyal
 *
 */
public interface AmwayApacWishlistDao extends AmwayWishlistDao
{
	/**
	 * Finds all the wish lists for the given user and the given site. The lists are sorted so that the favorite list is
	 * the first one and the remaining ones are sorted so that last modified ones come first.
	 *
	 * @param user
	 *           user for whom wish lists are to be found.
	 * @param baseSite
	 *           site for which wish lists are to be found.
	 * @param sortField
	 *           field to be used for sorting, if empty, modified time will be used.
	 * @param sortOrder
	 *           decides whether search results will be ascending or descending, if empty order will be descending
	 * @return wish lists found.
	 *
	 * @throws IllegalArgumentException
	 *            if either user or baseSite provided is null.
	 * @throws FlexibleSearchException
	 *            if the sorfField is not a valid field in {@link Wishlist2Model}
	 *
	 */
	List<Wishlist2Model> findWishlistsForUserAndSite(final UserModel user, final BaseSiteModel baseSite, final String sortField,
			final String sortOrder);

}
