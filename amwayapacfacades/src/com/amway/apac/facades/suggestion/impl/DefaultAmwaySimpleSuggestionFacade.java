package com.amway.apac.facades.suggestion.impl;

import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.facades.suggestion.AmwaySimpleSuggestionFacade;
import com.amway.facades.suggestion.impl.DefaultSimpleSuggestionFacade;


/**
 * Default implementation of {@link AmwaySimpleSuggestionFacade}
 *
 * @author Parvesh Goyal
 *
 */
public class DefaultAmwaySimpleSuggestionFacade extends DefaultSimpleSuggestionFacade implements AmwaySimpleSuggestionFacade
{

	private CartService cartService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductData> getSuggestionsForProductsInCart(final List<ProductReferenceTypeEnum> referenceTypes,
			final boolean excludePurchased, final Integer limit)
	{
		if (getCartService().hasSessionCart())
		{
			final Set<ProductModel> products = new HashSet<>();
			for (final AbstractOrderEntryModel entry : getCartService().getSessionCart().getEntries())
			{
				products.addAll(getAllBaseProducts(entry.getProduct()));
			}
			return Converters
					.convertAll(getSimpleSuggestionService().getReferencesForProducts(new LinkedList<ProductModel>(products),
							referenceTypes, getUserService().getCurrentUser(), excludePurchased, limit), getProductConverter());
		}
		return Collections.emptyList();
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}
}
