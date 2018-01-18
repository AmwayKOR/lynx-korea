package com.amway.apac.facades.product;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;


/**
 * The Interface AmwayApacProductFacade.
 *
 * @author Ashish Sabal
 */
public interface AmwayApacProductFacade extends ProductFacade
{

	/**
	 * Gets the recently viewed product data.
	 *
	 * @param list
	 *           the list
	 * @return the recently viewed product data
	 */
	List<ProductData> getRecentlyViewedProductData(final List<String> list);
}
