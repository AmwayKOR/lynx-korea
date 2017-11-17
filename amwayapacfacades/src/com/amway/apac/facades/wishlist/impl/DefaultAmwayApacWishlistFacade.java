package com.amway.apac.facades.wishlist.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.HUNDRED_INT;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.SHOPPING_LIST_ENTRY_DEFAULT_QUANTITY;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static org.springframework.util.Assert.hasLength;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.apac.core.wishlist.services.AmwayApacWishllistService;
import com.amway.apac.facades.wishlist.AmwayApacWishlistFacade;
import com.amway.facades.product.data.WishlistData;
import com.amway.facades.wishlist.impl.DefaultAmwayWishlistFacade;
import com.amway.facades.wishlist.modification.status.AmwayApacWishlistModificationStatus;


/**
 * Default implementation of {@link AmwayApacWishlistFacade}
 *
 * @author Parvesh Goyal
 *
 */
public class DefaultAmwayApacWishlistFacade extends DefaultAmwayWishlistFacade implements AmwayApacWishlistFacade
{

	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacWishlistFacade.class);

	private AmwayApacWishllistService amwayApacWishlistService;
	private Converter<Wishlist2Model, WishlistData> amwayApacWishlistBasicConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WishlistData> getAllWishlistsWithBasicData(final String sortField, final String sortOrder)
	{
		return Converters.convertAll(getAmwayApacWishlistService().getWishlists(sortField, sortOrder),
				getAmwayApacWishlistBasicConverter());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData getWishlistByUidForCurrentUser(final String uid)
	{
		Assert.hasLength(uid, "Parameter uid can not be null or empty.");

		return getWishlistConverter().convert(getAmwayApacWishlistService().getWishlistByUidForCurrentUser(uid));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayApacWishlistModificationStatus addProductToWishlist(final String productCode, final String wishlistUid)
	{
		Assert.hasLength(productCode, "Parameter productcode can not be null or empty.");
		Assert.hasLength(wishlistUid, "Parameter wishlistUid can not be null or empty.");

		AmwayApacWishlistModificationStatus status = AmwayApacWishlistModificationStatus.SUCCESS;

		final Wishlist2Model wishlistToAddProduct = fetchWishlistByUidInternal(wishlistUid);
		final ProductModel productModel = fetchProductByCodeInternal(productCode);

		if (wishlistToAddProduct == null)
		{
			status = AmwayApacWishlistModificationStatus.WISHLIST_NOT_FOUND;
		}
		else if (productModel == null)
		{
			status = AmwayApacWishlistModificationStatus.PRODUCT_NOT_FONUD;
		}
		else if (getWishlistService().hasWishlistEntryForProduct(productModel, wishlistToAddProduct))
		{
			status = AmwayApacWishlistModificationStatus.PRODUCT_ALREADY_EXISTS;

			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Product with code [").append(productCode)
						.append("] is already present in the wishlist with uid [").append(wishlistUid).append("].").toString());
			}
		}
		else
		{
			getWishlistService().addWishlistEntry(wishlistToAddProduct, productModel, SHOPPING_LIST_ENTRY_DEFAULT_QUANTITY,
					Wishlist2EntryPriority.HIGH, StringUtils.EMPTY);

			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Added product with code [").append(productCode)
						.append("] to wishlist with uid [").append(wishlistUid).append("].").toString());
			}
		}

		return status;
	}

	/**
	 * Fetches the product whose code is given
	 *
	 * @param productCode
	 *           product code to find the product
	 * @return product found, null if no product is found.
	 */
	protected ProductModel fetchProductByCodeInternal(final String productCode)
	{
		ProductModel product = null;
		try
		{
			product = getProductService().getProductForCode(productCode);
		}
		catch (final AmbiguousIdentifierException aIE)
		{
			LOGGER.error(new StringBuilder(HUNDRED_INT).append("More than one products found with code [").append(productCode)
					.append("].").toString(), aIE);

		}
		catch (final UnknownIdentifierException uIE)
		{
			LOGGER.error(
					new StringBuilder(HUNDRED_INT).append("No product found with code [").append(productCode).append("].").toString(),
					uIE);
		}
		return product;
	}

	/**
	 * Fetches the wishlist whose uid is given for current user
	 *
	 * @param wishlistUid
	 *           uid of the wishlist
	 * @return wishlist found, null if no wishlist is found.
	 */
	protected Wishlist2Model fetchWishlistByUidInternal(final String wishlistUid)
	{
		Wishlist2Model wishlist = null;
		try
		{
			wishlist = getAmwayApacWishlistService().getWishlistByUidForCurrentUser(wishlistUid);
		}
		catch (final AmbiguousIdentifierException aIE)
		{
			LOGGER.error(new StringBuilder(HUNDRED_INT).append("More than one shopping list found with uid [").append(wishlistUid)
					.append("].").toString(), aIE);

		}
		catch (final UnknownIdentifierException uIE)
		{
			LOGGER.error(new StringBuilder(HUNDRED_INT).append("No shopping list found with uid [").append(wishlistUid).append("].")
					.toString(), uIE);
		}
		return wishlist;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayApacWishlistModificationStatus updateWishlistNameAndReturnStatus(final String uid, final String newName)
	{
		hasLength(uid, "Parameter uid can not be null or empty.");
		hasLength(newName, "Parameter newName can not be null or empty.");

		AmwayApacWishlistModificationStatus modificationstatus = AmwayApacWishlistModificationStatus.SUCCESS;
		if (null == getWishlistService().getWishlistByName(newName))
		{
			final Wishlist2Model wishlistModel = getWishlistService().getWishlistByUid(uid);

			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(HUNDRED_INT).append("Updating name for wishlist with uid [").append(uid)
						.append("], original name is [").append(wishlistModel.getName()).append("].").toString());
			}

			getWishlistService().updateWishlistName(wishlistModel, newName);

			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(HUNDRED_INT).append("Name is updated for Wishlist with uid [").append(uid)
						.append("], new name is [").append(newName).append("].").toString());
			}
		}
		else
		{
			modificationstatus = AmwayApacWishlistModificationStatus.WISHLIST_NAME_ALREADY_EXISTS;
			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(HUNDRED_INT).append("Name could not be updated for wishlist with uid [").append(uid)
						.append("] as a wishlist already exists with the name [").append(newName).append("].").toString());
			}

		}
		return modificationstatus;
	}

	/**
	 * @return the amwayApacWishlistBasicConverter
	 */
	public Converter<Wishlist2Model, WishlistData> getAmwayApacWishlistBasicConverter()
	{
		return amwayApacWishlistBasicConverter;
	}

	/**
	 * @param amwayApacWishlistBasicConverter
	 *           the amwayApacWishlistBasicConverter to set
	 */
	@Required
	public void setAmwayApacWishlistBasicConverter(final Converter<Wishlist2Model, WishlistData> amwayApacWishlistBasicConverter)
	{
		this.amwayApacWishlistBasicConverter = amwayApacWishlistBasicConverter;
	}

	/**
	 * @return the amwayApacWishlistService
	 */
	public AmwayApacWishllistService getAmwayApacWishlistService()
	{
		return amwayApacWishlistService;
	}

	/**
	 * @param amwayApacWishlistService
	 *           the amwayApacWishlistService to set
	 */
	@Required
	public void setAmwayApacWishlistService(final AmwayApacWishllistService amwayApacWishlistService)
	{
		this.amwayApacWishlistService = amwayApacWishlistService;
	}

}
