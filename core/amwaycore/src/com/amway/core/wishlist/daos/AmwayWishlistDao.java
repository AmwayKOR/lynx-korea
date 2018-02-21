package com.amway.core.wishlist.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.wishlist2.impl.daos.Wishlist2Dao;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;


/**
 * DAO interface for wish lists related functionalities.
 *
 * @author Parvesh Goyal
 */
public interface AmwayWishlistDao extends Wishlist2Dao
{
	/**
	 * Finds all the wish lists for the given user and the given site. The lists are sorted so that the favorite list is
	 * the first one and the remaining ones are sorted so that last modified ones come first.
	 *
	 * @param user
	 *           user for whom wish lists are to be found.
	 * @param baseSite
	 *           site for which wish lists are to be found.
	 * @return wish lists found.
	 *
	 * @throws IllegalArgumentException
	 *            if either user or baseSite provided is null.
	 *
	 */
	List<Wishlist2Model> findWishlistsForUserAndSite(final UserModel user, final BaseSiteModel baseSite);

	/**
	 * Finds the default wish lists for the given user and the given site.
	 *
	 * @param user
	 *           user for whom default wish lists are to be found.
	 * @param baseSite
	 *           site for which default wish list are to be found.
	 * @return wish lists found.
	 *
	 * @throws IllegalArgumentException
	 *            if either user or baseSite provided is null.
	 *
	 */
	List<Wishlist2Model> findDefaultWishlistForUserAndSite(final UserModel user, final BaseSiteModel baseSite);

	/**
	 * Finds the favorite wish lists for the given user and the given site.
	 *
	 * @param user
	 *           user for whom favorite wish lists is to be found.
	 * @param baseSite
	 *           site for which favorite wish lists is to be found.
	 * @return wish lists found.
	 *
	 * @throws IllegalArgumentException
	 *            if either user or baseSite provided is null.
	 *
	 */
	List<Wishlist2Model> findFavoriteWishlistsForUserAndSite(final UserModel user, final BaseSiteModel baseSite);


	/**
	 * Finds Wish lists for given UID.
	 *
	 * @param uid
	 *           unique identifier to find wish lists
	 * @return wish lists found
	 */
	List<Wishlist2Model> findWishlistsForUID(final String uid);

	/**
	 * fetches wishlist for the given user with the given name
	 *
	 * @param user
	 * @param baseSite
	 * @param name
	 * @return wish for the current user with the given name
	 */
	List<Wishlist2Model> findWishlistByNameForUserAndSiteAndName(UserModel user, BaseSiteModel baseSite, String name);

}
