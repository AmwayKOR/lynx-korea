package com.amway.apac.facades.wishlist.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.HUNDRED_INT;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.ONE_INT;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.wishlist.services.AmwayApacWishllistService;
import com.amway.apac.facades.cart.enums.AmwayApacCartSortCode;
import com.amway.apac.facades.wishlist.AmwayApacWishlistFacade;
import com.amway.apac.facades.wishlist.data.AmwayApacWishListModification;
import com.amway.facades.product.data.WishlistData;
import com.amway.facades.product.data.WishlistEntryData;
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
	private Converter<Wishlist2EntryModel, WishlistEntryData> amwayWishlistEntryBasicConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData getShoppingListDetailsSortBySortCode(final AmwayApacCartSortCode sortBy, final WishlistData data)
	{
		if (Objects.nonNull(data) && CollectionUtils.isNotEmpty(data.getEntries()))
		{
			final List<WishlistEntryData> recentlyAddedListEntries = new ArrayList<>(data.getEntries());
			final AmwayApacCartSortCode resolvedSortCode = sortBy == null ? AmwayApacCartSortCode.LAST_ITEM_ADDED : sortBy;
			switch (resolvedSortCode)
			{
				case LAST_ITEM_ADDED:
					Collections.reverse(recentlyAddedListEntries);
					break;
				case PRICE_ASCENDING:
					recentlyAddedListEntries.sort((o1, o2) -> Double.compare(o1.getProduct().getPrice().getValue().doubleValue(),
							o2.getProduct().getPrice().getValue().doubleValue()));
					break;
				case PRICE_DESCEDNING:
					recentlyAddedListEntries.sort((o1, o2) -> Double.compare(o2.getProduct().getPrice().getValue().doubleValue(),
							o1.getProduct().getPrice().getValue().doubleValue()));
					break;
				case NAME_ASCENDING:
					recentlyAddedListEntries.sort((o1, o2) -> o1.getProduct().getName().compareTo(o2.getProduct().getName()));
					break;
				case NAME_DESCEDNING:
					recentlyAddedListEntries.sort((o1, o2) -> o2.getProduct().getName().compareTo(o1.getProduct().getName()));
					break;
				default:
					Collections.reverse(recentlyAddedListEntries);
					break;
			}
			data.setEntries(Collections.unmodifiableList(recentlyAddedListEntries));
		}
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WishlistData> getAllWishlistsWithBasicData(final String sortField, final String sortOrder)
	{
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.SORTFIELD_STRING, sortField);
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.SORTORDER_STRING, sortOrder);

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
	public AmwayApacWishListModification addProductToWishlist(final String productCode, final String wishlistUid)
	{
		Assert.hasLength(productCode, "Parameter productcode can not be null or empty.");
		Assert.hasLength(wishlistUid, "Parameter wishlistUid can not be null or empty.");

		final AmwayApacWishListModification modification = new AmwayApacWishListModification();
		modification.setStatus(AmwayApacWishlistModificationStatus.SUCCESS);

		final Wishlist2EntryModel entryCreated = addProductToWishlistInternal(productCode, wishlistUid, modification);

		if (entryCreated != null)
		{
			final WishlistEntryData entryData = getAmwayWishlistEntryBasicConverter().convert(entryCreated);
			modification.setEntry(entryData);
			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Added product with code [").append(productCode)
						.append("] to wishlist with uid [").append(wishlistUid).append("].").toString());
			}
		}

		return modification;
	}

	/**
	 *
	 * @param productCode
	 * @param wishlistUid
	 * @param modification
	 * @return
	 */
	protected Wishlist2EntryModel addProductToWishlistInternal(final String productCode, final String wishlistUid,
			final AmwayApacWishListModification modification)
	{
		Wishlist2EntryModel wishlistEntry = null;
		final Wishlist2Model wishlistToAddProduct = fetchWishlistByUidInternal(wishlistUid);
		final ProductModel productModel = fetchProductByCodeInternal(productCode);

		if (wishlistToAddProduct == null)
		{
			modification.setStatus(AmwayApacWishlistModificationStatus.WISHLIST_NOT_FOUND);
		}
		else if ((productModel == null) || (productModel.getVariantType() != null))
		{
			modification.setStatus(AmwayApacWishlistModificationStatus.PRODUCT_NOT_FONUD);

		}
		else if (getWishlistService().hasWishlistEntryForProduct(productModel, wishlistToAddProduct))
		{
			modification.setStatus(AmwayApacWishlistModificationStatus.PRODUCT_ALREADY_EXISTS);

			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Product with code [").append(productCode)
						.append("] is already present in the wishlist with uid [").append(wishlistUid).append("].").toString());
			}
		}
		else
		{
			wishlistEntry = getAmwayApacWishlistService().addAndReturnWishlistEntry(wishlistToAddProduct, productModel, ONE_INT,
					Wishlist2EntryPriority.HIGH, StringUtils.EMPTY);
		}
		return wishlistEntry;
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
		Assert.hasLength(uid, "Parameter uid can not be null or empty.");
		Assert.hasLength(newName, "Parameter newName can not be null or empty.");

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

	/**
	 * @return the amwayWishlistEntryBasicConverter
	 */
	public Converter<Wishlist2EntryModel, WishlistEntryData> getAmwayWishlistEntryBasicConverter()
	{
		return amwayWishlistEntryBasicConverter;
	}

	/**
	 * @param amwayWishlistEntryBasicConverter
	 *           the amwayWishlistEntryBasicConverter to set
	 */
	public void setAmwayWishlistEntryBasicConverter(
			final Converter<Wishlist2EntryModel, WishlistEntryData> amwayWishlistEntryBasicConverter)
	{
		this.amwayWishlistEntryBasicConverter = amwayWishlistEntryBasicConverter;
	}



}