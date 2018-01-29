package com.amway.apac.facades.product.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.CLOSE_PARENTHESIS;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.SPACE;
import static com.amway.apac.facades.constants.AmwayapacFacadesConstants.PARAMETER_CATEGORY_CAN_NOT_BE_EMPTY;
import static com.amway.apac.facades.constants.AmwayapacFacadesConstants.PARAMETER_CODE_CAN_NOT_BE_NULL;
import static com.amway.apac.facades.constants.AmwayapacFacadesConstants.PRODUCT_LISTING_MAX_NUMBER_OF_RECORDS;
import static com.amway.apac.facades.constants.AmwayapacFacadesConstants.ZERO;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import static org.springframework.util.Assert.hasLength;

import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.product.data.ReferenceData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.product.services.AmwayApacProductReferenceService;
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

	private ProductSearchFacade<ProductData> productSearchFacade;
	private AmwayApacProductReferenceService amwayApacProductReferenceService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductData> getProductDataUsingSolrSearch(final List<String> productCodes)
	{
		validateParameterNotNullStandardMessage(PRODUCT_CODES, productCodes);

		final List<ProductData> productsFound = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(productCodes))
		{
			productsFound.addAll(fetchProductDataUsingSolrSearch(createSearchQueryDataForProductCodes(productCodes), null));
		}

		return productsFound;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductData> getCategoryProductReferencesForCode(final CategoryModel category,
			final List<ProductReferenceTypeEnum> referenceTypes, final Integer limit)
	{
		validateParameterNotNull(category, PARAMETER_CATEGORY_CAN_NOT_BE_EMPTY);

		final List<ReferenceData<ProductReferenceTypeEnum, ProductModel>> references = this.getAmwayApacProductReferenceService()
				.getProductReferencesForCategory(category, referenceTypes, limit);

		return convertProductReferences(limit, references);
	}

	/**
	 * Converts the product references
	 * 
	 * @param limit
	 * @param references
	 * @return
	 */
	protected List<ProductData> convertProductReferences(final Integer limit,
			final List<ReferenceData<ProductReferenceTypeEnum, ProductModel>> references)
	{
		final List<ProductData> productsFound = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(references))
		{
			productsFound.addAll(fetchProductDataUsingSolrSearch(createSearchQueryDataForProductReference(references), limit));
		}
		return productsFound;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductData> getProductReferencesProductDataForCode(final String productCode,
			final List<ProductReferenceTypeEnum> referenceTypes, final Integer limit)
	{
		hasLength(productCode, PARAMETER_CODE_CAN_NOT_BE_NULL);

		final List<ReferenceData<ProductReferenceTypeEnum, ProductModel>> references = getAmwayApacProductReferenceService()
				.getProductReferencesForCode(productCode, referenceTypes, limit);

		return convertProductReferences(limit, references);
	}

	/**
	 * Returns the {@link ProductData} for search query data and max results limit
	 *
	 * @param searchStateData
	 *           search query data
	 * @param limit
	 *           max limit of results
	 * @return list of products found
	 */
	protected List<ProductData> fetchProductDataUsingSolrSearch(final SearchStateData searchStateData, final Integer limit)
	{
		final List<ProductData> productsFound = new ArrayList<>();
		if (null != searchStateData)
		{
			try
			{
				final ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData = getProductSearchFacade()
						.categorySearch(null, searchStateData, createSearchPageDataForProductReferenceSearch(limit));

				if (CollectionUtils.isNotEmpty(searchPageData.getResults()))
				{
					productsFound.addAll(searchPageData.getResults());
				}
			}
			catch (final ConversionException cE)
			{
				LOGGER.error(new StringBuilder(100).append("Error while fetching products").toString(), cE);
			}
		}
		return productsFound;
	}

	/**
	 * Creates the search query by product codes list.
	 *
	 * @param productCodes
	 *           the product codes
	 * @return the search state data
	 */
	protected SearchStateData createSearchQueryDataForProductCodes(final List<String> productCodes)
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
	 * Creates the Solr query to fetch the product data for product references given.
	 *
	 * @param references
	 *           list of reference products to search for
	 * @return solr query data
	 */
	protected SearchStateData createSearchQueryDataForProductReference(
			final List<ReferenceData<ProductReferenceTypeEnum, ProductModel>> references)
	{
		final SearchStateData searchState = new SearchStateData();

		final StringBuilder filterQueryBuilder = new StringBuilder(100).append(SOLR_CODE_STRING_QUERY_LABEL);
		for (final ReferenceData<ProductReferenceTypeEnum, ProductModel> productReference : references)
		{
			filterQueryBuilder.append(SPACE).append(getBaseProductCode(productReference));
		}
		filterQueryBuilder.append(CLOSE_PARENTHESIS).toString();

		searchState.setFilterRawQueries(filterQueryBuilder.toString());

		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(SOLR_RELEVANCE_SEARCH_QUERY);
		searchState.setQuery(searchQueryData);
		return searchState;
	}

	/**
	 * Returns the product code if the product is single article, else returns the product code of the base product
	 *
	 * @param productReference
	 *           product reference
	 * @return code of the base product
	 */
	protected String getBaseProductCode(final ReferenceData<ProductReferenceTypeEnum, ProductModel> productReference)
	{
		return (productReference.getTarget() instanceof VariantProductModel)
				? ((VariantProductModel) productReference.getTarget()).getBaseProduct().getCode()
				: productReference.getTarget().getCode();
	}

	/**
	 * Creates page data for product references search. Page number is always kept zero. Max limit decides the page size.
	 * If limit parameter is null, then max value for solr listing is considered.
	 *
	 * @param limit
	 *           max number of product references to search
	 * @return page data
	 */
	protected PageableData createSearchPageDataForProductReferenceSearch(final Integer limit)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(ZERO);
		pageableData.setPageSize(Objects.nonNull(limit) ? limit.intValue() : PRODUCT_LISTING_MAX_NUMBER_OF_RECORDS);
		return pageableData;
	}

	/**
	 * @return the amwayApacProductReferenceService
	 */
	public AmwayApacProductReferenceService getAmwayApacProductReferenceService()
	{
		return amwayApacProductReferenceService;
	}

	/**
	 * @param amwayApacProductReferenceService
	 *           the amwayApacProductReferenceService to set
	 */
	@Required
	public void setAmwayApacProductReferenceService(final AmwayApacProductReferenceService amwayApacProductReferenceService)
	{
		this.amwayApacProductReferenceService = amwayApacProductReferenceService;
	}

	/**
	 * @return the productSearchFacade
	 */
	public ProductSearchFacade<ProductData> getProductSearchFacade()
	{
		return productSearchFacade;
	}

	/**
	 * @param productSearchFacade
	 *           the productSearchFacade to set
	 */
	@Required
	public void setProductSearchFacade(final ProductSearchFacade<ProductData> productSearchFacade)
	{
		this.productSearchFacade = productSearchFacade;
	}
}
