<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/responsive/category" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:page pageTitle="${pageTitle}">
            <div class="container-fluid main-container new-plp">
                <div class="row">
                    <h1 class="col-sm-12 product-list-page-title spl-page-title"><spring:theme code="search.page.search.results" arguments="${searchPageData.freeTextSearch}"/></h1>
                    <div class="slp-search-spelling-suggestion">
						<nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
                    </div>
                    <div class="plp-content-wrapper product-search">
	                    <div class="col-xs-3 print-hide">
	                    	<category:productListDesktopFacetSection searchPageData="${searchPageData}"/>
	                    </div>
	                    <div class="col-sm-12 col-md-9">
	                    	<div class="switch-buttons-wrapper">
	                            <div class="wrap">
	                                <div class="search-type-switch-button active product-button" data-search-type="PRODUCTSEARCH">Products (${searchPageData.pagination.totalNumberOfResults})</div>
	                                <div class="search-type-switch-button content-button" data-search-type="RESOURCESEARCH">Content (1)</div>
	                            </div>
	                        </div>
	                        <!-- Product List -->
	                        <div class="new-product-list-right-slot search-list-page-right-result-list-slot">
	                            <div class="product__list--wrapper search-list-page-right-result-list-component">
	                                <category:productListingTopRightSection searchPageData="${searchPageData}"/>
	                                <category:productListSection searchPageData="${searchPageData}"/>
	                                <category:productListerPaginationSection/>
	                            </div>
	                        </div>
	                    </div>
                    </div>
					<category:searchResultContentSection/>                 
                </div>
            </div>
</template:page>