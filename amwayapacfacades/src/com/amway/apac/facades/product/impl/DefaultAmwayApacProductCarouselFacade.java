package com.amway.apac.facades.product.impl;

import de.hybris.platform.acceleratorfacades.productcarousel.impl.DefaultProductCarouselFacade;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Overriding OOTB {@link DefaultProductCarouselFacade} to populate as per APAC requirements.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacProductCarouselFacade extends DefaultProductCarouselFacade
{
	/**
	 * Product Options required for UI display for Product carousel as per APAC requirements.
	 */
	protected static final List<ProductOption> AMWAY_APAC_PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.IMAGES, ProductOption.STOCK);

	/**
	 * Fetches list of products for a given product carousel component when not in preview (i.e., no cmsTicketId in
	 * present in the session).
	 *
	 * @param component
	 *           The product carousel component model
	 * @return List<ProductData> list of available products
	 */
	@Override
	protected List<ProductData> fetchProductsForNonPreviewMode(final ProductCarouselComponentModel component)
	{

		final List<ProductData> products = new ArrayList<>();

		for (final ProductModel productModel : component.getProducts())
		{
			products.add(getProductFacade().getProductForCodeAndOptions(productModel.getCode(), AMWAY_APAC_PRODUCT_OPTIONS));
		}

		for (final CategoryModel categoryModel : component.getCategories())
		{
			for (final ProductModel productModel : categoryModel.getProducts())
			{
				products.add(getProductFacade().getProductForCodeAndOptions(productModel.getCode(), AMWAY_APAC_PRODUCT_OPTIONS));
			}
		}

		return products;

	}

	/**
	 * Fetches list of products for a given product carousel component when in preview (i.e., cmsTicketId in present in
	 * the session).
	 *
	 * @param component
	 *           The product carousel component model
	 * @return List<ProductData> list of available products
	 */
	@Override
	protected List<ProductData> fetchProductsForPreviewMode(final ProductCarouselComponentModel component)
	{
		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				try
				{
					getSearchRestrictionService().disableSearchRestrictions();

					final List<ProductData> products = new ArrayList<>();

					for (final ProductModel productModel : getProductCarouselRendererService().getDisplayableProducts(component))
					{
						products.add(getProductForOptions(productModel, AMWAY_APAC_PRODUCT_OPTIONS));
					}

					for (final CategoryModel categoryModel : getProductCarouselRendererService().getListOfCategories(component))
					{
						for (final ProductModel productModel : getProductCarouselRendererService()
								.getDisplayableProducts(categoryModel))
						{
							products.add(getProductForOptions(productModel, AMWAY_APAC_PRODUCT_OPTIONS));
						}
					}
					return products;
				}
				finally
				{
					getSearchRestrictionService().enableSearchRestrictions();
				}
			}
		});

	}
}
