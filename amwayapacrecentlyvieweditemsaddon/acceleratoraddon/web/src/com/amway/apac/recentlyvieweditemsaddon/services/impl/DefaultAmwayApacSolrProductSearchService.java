package com.amway.apac.recentlyvieweditemsaddon.services.impl;

import com.amway.apac.recentlyvieweditemsaddon.services.AmwayApacProductSearchService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryTermData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.impl.DefaultSolrProductSearchService;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;

/**
 * Created by Govind on 12/13/2017.
 */
public class DefaultAmwayApacSolrProductSearchService<ITEM extends ProductData> extends DefaultSolrProductSearchService implements AmwayApacProductSearchService{

    private Converter<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData>> productCategorySearchPageConverter;
    private ThreadContextService threadContextService;

    protected Converter<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData>> getProductCategorySearchPageConverter()
    {
        return productCategorySearchPageConverter;
    }

    @Required
    public void setProductCategorySearchPageConverter(
            final Converter<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData>> productCategorySearchPageConverter)
    {
        this.productCategorySearchPageConverter = productCategorySearchPageConverter;
    }

    protected ThreadContextService getThreadContextService()
    {
        return threadContextService;
    }

    @Required
    public void setThreadContextService(final ThreadContextService threadContextService)
    {
        this.threadContextService = threadContextService;
    }


    public ProductSearchPageData<SearchStateData, ITEM> productCodesSearch(String productCodes, PageableData pageableData) {
        final SolrSearchQueryData searchQueryData = createSearchQueryData();

        searchQueryData.setFilterTerms(Collections.<SolrSearchQueryTermData> emptyList());
        searchQueryData.setRawQuery(productCodes);

        return getThreadContextService().executeInContext(
                new ThreadContextService.Executor<ProductSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
                {
                    @Override
                    public ProductSearchPageData<SearchStateData, ITEM> execute()
                    {
                        return getProductCategorySearchPageConverter()
                                .convert(doSearch(searchQueryData, pageableData));
                    }
                });
    }
}
