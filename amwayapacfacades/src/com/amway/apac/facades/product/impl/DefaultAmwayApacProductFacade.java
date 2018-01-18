package com.amway.apac.facades.product.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.CLOSE_PARENTHESIS;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.SPACE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.facades.product.AmwayApacProductFacade;


/**
 * Default implementation of {@link AmwayApacProductFacade}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacProductFacade extends DefaultProductFacade<ProductModel> implements AmwayApacProductFacade
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacProductFacade.class);

	/** Solr query label for searching by code. */
	private static final String SOLR_CODE_STRING_QUERY_LABEL = "code_string:(";

	/** Solr query to search by relevance. */
	private static final String SOLR_RELEVANCE_SEARCH_QUERY = ":relevance";

	/** The Constant PRODUCT_CODES. */
	private static final String PRODUCT_CODES = "Product code list";

	/** The product search facade. */
	private ProductSearchFacade<ProductData> productSearchFacade;


	/**
	 * Gets recently viewed items by solr serch for product codes
	 *
	 * @param productCodes
	 *
	 * @return Product data list to render in component
	 *
	 * @throws IllegalArgumentException
	 */
	@Override
	public List<ProductData> getRecentlyViewedProductData(final List<String> productCodes)
	{
		validateParameterNotNullStandardMessage(PRODUCT_CODES, productCodes);

		final List<ProductData> recentlyViewedProductsFound = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(productCodes))
		{
			try
			{
				final ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData = productSearchFacade
						.categorySearch(null, createSearchQueryData(productCodes), null);

				if (CollectionUtils.isNotEmpty(searchPageData.getResults()))
				{
					recentlyViewedProductsFound.addAll(searchPageData.getResults());
				}
			}
			catch (final ConversionException cE)
			{
				LOGGER.error(new StringBuilder(100).append("Error while fetching products").toString(), cE);
			}
		}
		return recentlyViewedProductsFound;
	}

	/**
	 * Creates the search query by product codes list.
	 *
	 * @param productCodes
	 *           the product codes
	 * @return the search state data
	 */
	protected SearchStateData createSearchQueryData(final List<String> productCodes)
	{
		final SearchStateData searchState = new SearchStateData();
		final StringBuilder filterQueryBuilder = new StringBuilder(100).append(SOLR_CODE_STRING_QUERY_LABEL);
		for (final String productCode : productCodes)
		{
			filterQueryBuilder.append(SPACE).append(productCode);
		}
		filterQueryBuilder.append(CLOSE_PARENTHESIS);

		searchState.setFilterRawQueries(filterQueryBuilder.toString());

		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(SOLR_RELEVANCE_SEARCH_QUERY);
		searchState.setQuery(searchQueryData);
		return searchState;
	}

	/**
	 * Gets the product search facade.
	 *
	 * @return the productSearchFacade
	 */
	public ProductSearchFacade<ProductData> getProductSearchFacade()
	{
		return productSearchFacade;
	}

	/**
	 * Sets the product search facade.
	 *
	 * @param productSearchFacade
	 *           the productSearchFacade to set
	 */
	public void setProductSearchFacade(final ProductSearchFacade<ProductData> productSearchFacade)
	{
		this.productSearchFacade = productSearchFacade;
	}
}
