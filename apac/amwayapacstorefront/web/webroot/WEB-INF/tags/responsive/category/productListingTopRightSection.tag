<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/responsive/category" %>
<%@ attribute name="searchPageData" type="de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="print-hide pagination-bar top">
    <div class="pagination-toolbar">
        <div class="row">
            <div class="col-xs-12 col-md-4">
                <div class="pull-left">
                    <div class="pagination-bar-results">
                        <div class="total-wrapper">
                            <span>${searchPageData.pagination.totalNumberOfResults} <spring:theme code="plp.total.search.results"/></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6 col-md-6 pagination-wrapper print-hide">
                <div class="pull-right sort-wrapper">
                    <div class="sort-refine-bar">
                        <category:productListFilterButton/>
                        <category:productListSortSection searchPageData="${searchPageData}"/>
                        <category:productListMobileFacetSection searchPageData="${searchPageData}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>