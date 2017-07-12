package com.amway.core.wishlist.daos.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.wishlist2.impl.daos.impl.DefaultWishlist2Dao;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.amway.core.wishlist.daos.AmwayWishlistDao;


/**
 * Default implementation for the wish list DAO extending DefaultWishlist2Dao to include the site specific nature of the
 * wish lists and changing the sorting mechanism of the wish lists.
 *
 * @author Parvesh Goyal
 */
public class AmwayWishlistDaoImpl extends DefaultWishlist2Dao implements AmwayWishlistDao
{
	private static final Logger LOG = LoggerFactory.getLogger(AmwayWishlistDaoImpl.class);

	private static final String FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY = new StringBuilder(200).append("SELECT {")
			.append(Wishlist2Model.PK).append("} FROM {").append(Wishlist2Model._TYPECODE).append("} WHERE {")
			.append(Wishlist2Model.USER).append("} = ?").append(Wishlist2Model.USER).append(" AND {").append(Wishlist2Model.SITE)
			.append("} =?").append(Wishlist2Model.SITE).append(" ORDER BY {").append(Wishlist2Model.FAVORITE).append("} DESC, {")
			.append(Wishlist2Model.MODIFIEDTIME).append("} DESC").toString();

	private static final String FIND_DEFAULT_WISHLISTS_FOR_CUSTOMER_AND_SITE_QUERY = new StringBuilder(200).append("SELECT {")
			.append(Wishlist2Model.PK).append("} FROM {").append(Wishlist2Model._TYPECODE).append("} WHERE {")
			.append(Wishlist2Model.USER).append("} = ?").append(Wishlist2Model.USER).append(" AND {").append(Wishlist2Model.SITE)
			.append("} =?").append(Wishlist2Model.SITE).append(" AND {").append(Wishlist2Model.DEFAULT).append("} =?")
			.append(Wishlist2Model.DEFAULT).toString();

	private static final String FIND_FAVORITE_WISHLISTS_FOR_CUSTOMER_AND_SITE_QUERY = new StringBuilder(200).append("SELECT {")
			.append(Wishlist2Model.PK).append("} FROM {").append(Wishlist2Model._TYPECODE).append("} WHERE {")
			.append(Wishlist2Model.USER).append("} = ?").append(Wishlist2Model.USER).append(" AND {").append(Wishlist2Model.SITE)
			.append("} =?").append(Wishlist2Model.SITE).append(" AND {").append(Wishlist2Model.FAVORITE).append("} =?")
			.append(Wishlist2Model.FAVORITE).toString();


	private static final String FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY_AND_NAME = new StringBuilder(200).append("SELECT {")
			.append(Wishlist2Model.PK).append("} FROM {").append(Wishlist2Model._TYPECODE).append("} WHERE {")
			.append(Wishlist2Model.USER).append("} = ?").append(Wishlist2Model.USER).append(" AND {").append(Wishlist2Model.SITE)
			.append("} =?").append(Wishlist2Model.SITE).append(" AND LOWER({").append(Wishlist2Model.NAME).append("}) = LOWER(?")
			.append(Wishlist2Model.NAME).append(") ORDER BY {").append(Wishlist2Model.FAVORITE).append("} DESC, {")
			.append(Wishlist2Model.MODIFIEDTIME).append("} DESC").toString();

	private static final String FIND_WISHLISTS_FOR_UID_QUERY = new StringBuilder(100).append("SELECT {").append(Wishlist2Model.PK)
			.append("} FROM {").append(Wishlist2Model._TYPECODE).append("} WHERE {").append(Wishlist2Model.UID).append("} =?")
			.append(Wishlist2Model.UID).toString();



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> findWishlistsForUserAndSite(final UserModel user, final BaseSiteModel baseSite)
	{
		validateParameterNotNull(user, "User must not be null!");
		validateParameterNotNull(baseSite, "Base Site must not be null!");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY);
		fQuery.addQueryParameter(Wishlist2Model.USER, user);
		fQuery.addQueryParameter(Wishlist2Model.SITE, baseSite);

		final SearchResult<Wishlist2Model> result = search(fQuery);
		return result.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> findDefaultWishlistForUserAndSite(final UserModel user, final BaseSiteModel baseSite)
	{
		validateParameterNotNull(user, "User must not be null!");
		validateParameterNotNull(baseSite, "Base Site must not be null!");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_DEFAULT_WISHLISTS_FOR_CUSTOMER_AND_SITE_QUERY);
		fQuery.addQueryParameter(Wishlist2Model.USER, user);
		fQuery.addQueryParameter(Wishlist2Model.SITE, baseSite);
		fQuery.addQueryParameter(Wishlist2Model.DEFAULT, Boolean.TRUE);

		final SearchResult<Wishlist2Model> result = search(fQuery);
		return result.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> findFavoriteWishlistsForUserAndSite(final UserModel user, final BaseSiteModel baseSite)
	{
		validateParameterNotNull(user, "User must not be null!");
		validateParameterNotNull(baseSite, "BaseSite must not be null!");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_FAVORITE_WISHLISTS_FOR_CUSTOMER_AND_SITE_QUERY);
		fQuery.addQueryParameter(Wishlist2Model.USER, user);
		fQuery.addQueryParameter(Wishlist2Model.SITE, baseSite);
		fQuery.addQueryParameter(Wishlist2Model.FAVORITE, Boolean.TRUE);

		final SearchResult<Wishlist2Model> result = search(fQuery);
		return result.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> findWishlistsForUID(final String uid)
	{
		Assert.hasLength(uid, "UID must not be null!");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_WISHLISTS_FOR_UID_QUERY);
		fQuery.addQueryParameter(Wishlist2Model.UID, uid);

		final SearchResult<Wishlist2Model> result = search(fQuery);
		return result.getResult();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> findWishlistByNameForUserAndSiteAndName(final UserModel user, final BaseSiteModel baseSite,
			final String name)
	{
		validateParameterNotNull(user, "User must not be null!");
		validateParameterNotNull(baseSite, "Base Site must not be null!");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY_AND_NAME);
		fQuery.addQueryParameter(Wishlist2Model.USER, user);
		fQuery.addQueryParameter(Wishlist2Model.SITE, baseSite);
		fQuery.addQueryParameter(Wishlist2Model.NAME, name);

		final SearchResult<Wishlist2Model> result = search(fQuery);
		return result.getResult();
	}


}
