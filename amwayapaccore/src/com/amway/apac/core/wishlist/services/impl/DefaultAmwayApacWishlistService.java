package com.amway.apac.core.wishlist.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.apac.core.wishlist.daos.AmwayApacWishlistDao;
import com.amway.apac.core.wishlist.services.AmwayApacWishllistService;
import com.amway.core.wishlist.services.impl.AmwayWishlistServiceImpl;


/**
 * Default implementation of {@link AmwayApacWishllistService}
 *
 * @author Parvesh Goyal
 *
 */
public class DefaultAmwayApacWishlistService extends AmwayWishlistServiceImpl implements AmwayApacWishllistService
{
	private AmwayApacWishlistDao amwayApacWishlistDao;

	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacWishlistService.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> getWishlists(final String sortField, final String sortOrder)
	{
		final UserModel currentUser = getCurrentUser();
		final BaseSiteModel currentSite = getBaseSiteService().getCurrentBaseSite();

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Fetching wishlists for user [").append(currentUser.getUid())
					.append("] for site [").append(currentSite.getUid()).append("].").toString());
		}

		final List<Wishlist2Model> wishlistsFound = getAmwayApacWishlistDao().findWishlistsForUserAndSite(currentUser, currentSite,
				sortField, sortOrder);

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Found ").append(CollectionUtils.size(wishlistsFound))
					.append(" wishlists for user [").append(currentUser.getUid()).append("] for site [").append(currentSite.getUid())
					.append("].").toString());
		}

		return wishlistsFound;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wishlist2Model getWishlistByUidForCurrentUser(final String uid)
	{
		Assert.hasLength(uid, "Uid can not be null or empty.");

		final UserModel currentUser = getCurrentUser();
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Fetching wishlists for user [").append(currentUser.getUid())
					.append("] and uid [").append(uid).append("].").toString());
		}

		final List<Wishlist2Model> results = getAmwayApacWishlistDao().findWishlistsForUIDAndUser(uid, currentUser);

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Found ").append(CollectionUtils.size(results))
					.append(" wishlists for user [").append(currentUser.getUid()).append("] and uid [").append(uid).append("].")
					.toString());
		}

		// validating that there is only one result.
		validateIfSingleResult(results,
				new StringBuilder(100).append("No wishlists found with uid [").append(uid).append("] and user [")
						.append(currentUser.getUid()).append("].").toString(),
				new StringBuilder(100).append("More than one wishlists found with uid [").append(uid).append("] and user [")
						.append(currentUser.getUid()).append("].").toString());

		return results.iterator().next();
	}

	/**
	 * Overriding {@link AmwayWishlistServiceImpl} implementation to bypass the default shopping list check.
	 */
	@Override
	public boolean deleteWishlist(final String uid)
	{
		boolean isDeleted = false;
		final Wishlist2Model wishlist = getWishlistByUid(uid);
		if (null != wishlist)
		{
			getModelService().remove(wishlist);
			isDeleted = true;
		}
		return isDeleted;
	}

	/**
	 * @return the amwayApacWishlistDao
	 */
	public AmwayApacWishlistDao getAmwayApacWishlistDao()
	{
		return amwayApacWishlistDao;
	}

	/**
	 * @param amwayApacWishlistDao
	 *           the amwayApacWishlistDao to set
	 */
	@Required
	public void setAmwayApacWishlistDao(final AmwayApacWishlistDao amwayApacWishlistDao)
	{
		this.amwayApacWishlistDao = amwayApacWishlistDao;
	}

}
