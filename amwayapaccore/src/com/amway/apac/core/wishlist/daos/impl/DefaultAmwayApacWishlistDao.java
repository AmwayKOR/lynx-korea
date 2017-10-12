package com.amway.apac.core.wishlist.daos.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.DESC_STRING;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.amway.apac.core.wishlist.daos.AmwayApacWishlistDao;
import com.amway.core.wishlist.daos.impl.AmwayWishlistDaoImpl;


/**
 * Default implementation of {@link AmwayApacWishlistDao}
 *
 * @author Parvesh Goyal
 *
 */
public class DefaultAmwayApacWishlistDao extends AmwayWishlistDaoImpl implements AmwayApacWishlistDao
{

	/**
	 * Query to search for wishlist based on user and site without sorting.
	 */
	private static final String FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY = new StringBuilder(TWO_HUNDRED_INT).append("SELECT {")
			.append(Wishlist2Model.PK).append("} FROM {").append(Wishlist2Model._TYPECODE).append("} WHERE {")
			.append(Wishlist2Model.USER).append("} = ?").append(Wishlist2Model.USER).append(" AND {").append(Wishlist2Model.SITE)
			.append("} =?").append(Wishlist2Model.SITE).toString();

	/**
	 * Query to search for wishlist based on user and site sorted by user name.
	 */
	private static final String FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY_ORDER_BY_USERNAME = new StringBuilder(TWO_HUNDRED_INT)
			.append("SELECT {w.").append(Wishlist2Model.PK).append("} FROM {").append(Wishlist2Model._TYPECODE).append(" as w JOIN ")
			.append(UserModel._TYPECODE).append(" as u on {w.").append(Wishlist2Model.USER).append("}={u.").append(UserModel.PK)
			.append("}} WHERE {u.").append(UserModel.PK).append("} = ?").append(Wishlist2Model.USER).append(" AND {")
			.append(Wishlist2Model.SITE).append("} =?").append(Wishlist2Model.SITE).append(" ORDER BY LOWER({u.")
			.append(UserModel.NAME).append("})").toString();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> findWishlistsForUserAndSite(final UserModel user, final BaseSiteModel baseSite,
			final String sortField, final String sortOrder)
	{
		validateParameterNotNull(user, "User must not be null!");
		validateParameterNotNull(baseSite, "Base Site must not be null!");

		final String searchQuery = createSearchQuery(sortField, sortOrder);

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(searchQuery);
		fQuery.addQueryParameter(Wishlist2Model.USER, user);
		fQuery.addQueryParameter(Wishlist2Model.SITE, baseSite);

		final SearchResult<Wishlist2Model> result = search(fQuery);
		return result.getResult();
	}

	/**
	 * Creates the search query by populating the sort field and sort order.
	 *
	 * @param sortField
	 *           field to be used for sorting, if empty, modified time will be used.
	 * @param sortOrder
	 *           decides whether search results will be ascending or descending, if empty order will be descending
	 * @return query generated
	 */
	private String createSearchQuery(final String sortField, final String sortOrder)
	{
		final String query;

		if (Wishlist2Model.USER.equalsIgnoreCase(sortField))
		{
			query = new StringBuilder(200).append(FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY_ORDER_BY_USERNAME).append(" ")
					.append((StringUtils.isNotBlank(sortOrder)) ? sortOrder : DESC_STRING).toString();
		}
		else
		{
			query = new StringBuilder(200).append(FIND_WISHLISTS_FOR_SITE_AND_CUSTOMER_QUERY).append(" ORDER BY LOWER({")
					.append((StringUtils.isNotBlank(sortField)) ? sortField : Wishlist2Model.MODIFIEDTIME).append("}) ")
					.append((StringUtils.isNotBlank(sortOrder)) ? sortOrder : DESC_STRING).toString();
		}
		return query;
	}

}
