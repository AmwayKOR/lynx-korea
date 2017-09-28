<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<template:page pageTitle="${pageTitle}">
        <main>
            <div id="header"></div>
            <div class="container-fluid main-container">
                <div class="print-hide breadcrumb-section">
                    <ol class="breadcrumb">
                        <li>
                            <a href="homepage.html">Home</a></li>
                        <!--<li>-->
                            <!--<a href="#">Amway Catalog</a></li>-->
                        <li>
                            <a href="categoryPage.html">Nutrition</a></li>
                        <li class="active">Vitamins & Supplements</li></ol>
                </div>
            </div>
            <div class="container-fluid main-container new-plp">
                <div class="row">
                    <h1 class="col-sm-12 product-list-page-title">Vitamins & Supplements</h1>
                    <div class="col-xs-3 print-hide">
                        <div class="product-list-left-refinements-slot">
                            <div class="product-list-left-refinements-component">
                                <div id="product-facet" class="pc-product-facet product__facet js-product-facet hidden-xs hidden-sm">
                                <div class="filters-header">
                                    <span>Filters</span>
                                    <button form="clearAllForm" type="submit">Clear All</button>
                                </div>
                                <div class="panel-group accordion-custom" id="facetAccordion" role="tablist" aria-multiselectable="true">
                                    <div class="panel">
                                        <div class="panel-heading" role="tab" id="retailPriceFacet">
                                            <h4 class="panel-title">
                                                <div role="button" class="collapse-button collapsed" data-toggle="collapse" data-parent="#facetAccordion" href="#retailPriceFacetBodypc" aria-controls="retailPriceFacetBody">
                                                    <span class="text-uppercase">Price<span class="">(1)</span>
                                                    </span>
                                                    <span class="pull-right icon-minus"></span>
                                                </div>
                                            </h4>
                                        </div>
                                        <div id="retailPriceFacetBodypc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                            <div class="panel-body">
                                                <div id="tabContent_retailPriceFacet" class="facet js-facet">
                                                    <div class="facet__name js-facet-name">
                                                        <span class="glyphicon facet__arrow"></span>
                                                        Shop by Price</div>
                                                    <div class="facet__values js-facet-values js-facet-form">
                                                        <ul class="facet__list js-facet-list js-facet-top-values">
                                                            <li class="js-facet-value js-facet-init-value js-facet-top-value">
                                                                <form action="" method="get" class="non-js-desctop">
                                                                    <label class="hidden-xs hidden-sm">
                                                                        <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox" checked="checked">
                                                                        <span class="facet__list__label">
                                                                            <span class="facet__list__mark"></span>
                                                                            <span class="facet__list__text">
                                                                                $0-$49.99<span class="facet__value__count">
                                                                                        (3)</span>
                                                                                </span>
                                                                        </span>
                                                                    </label>
                                                                    <label class="hidden-md hidden-lg">
                                                                        <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox" checked="checked">
                                                                    <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            $0-$49.99<span class="facet__value__count">
                                                                                    (3)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                </form>
                                                            </li>
                                                            <li class="js-facet-value js-facet-init-value">
                                                                <form action="" method="get" class="non-js-desctop">
                                                                    <label class="hidden-xs hidden-sm">
                                                                        <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                        <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            $50-$199.99<span class="facet__value__count">
                                                                                    (3)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                    <label class="hidden-md hidden-lg">
                                                                        <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                    <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            $50-$199.99<span class="facet__value__count">
                                                                                    (3)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                </form>
                                                            </li>
                                                            <li class="js-facet-value js-facet-init-value">
                                                                <input class="js-facet-item-value" type="hidden" name="facetValue" value="retailPriceFacet:$500-$999.99">
                                                                <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c">
                                                                <form action="" method="get" class="non-js-desctop">
                                                                    <label class="hidden-xs hidden-sm">
                                                                        <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                                        <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            $500-$999.99<span class="facet__value__count">
                                                                                    (1)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                    <label class="hidden-md hidden-lg">
                                                                        <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                        <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            $500-$999.99<span class="facet__value__count">
                                                                                    (1)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                </form>
                                                            </li>
                                                          </ul>
                                                        <div class="facet-button-wrapper hidden-md hidden-lg">
                                                            <button type="button" class="js-facet-group-button-apply facet-button-apply">
                                                                Apply</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel">
                                        <div class="panel-heading" role="tab" id="availabilityInfo">
                                            <h4 class="panel-title">
                                                <div role="button" class="collapse-button collapsed" data-toggle="collapse" data-parent="#facetAccordion" href="#availabilityInfoBodypc" aria-controls="availabilityInfoBody">
                                                    <span class="text-uppercase">Product Availability<span class=""></span>
                                                    </span>
                                                    <span class="pull-right icon-minus"></span>
                                                </div>
                                            </h4>
                                        </div>
                                        <div id="availabilityInfoBodypc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                            <div class="panel-body">
                                                <div id="tabContent_availabilityInfo" class="facet js-facet">
                                                    <div class="facet__name js-facet-name">
                                                        <span class="glyphicon facet__arrow"></span>
                                                        Shop by Product Availability</div>
                                                    <div class="facet__values js-facet-values js-facet-form">
                                                        <ul class="facet__list js-facet-list js-facet-top-values">
                                                            <li class="js-facet-value js-facet-init-value">
                                                                <form action="" method="get" class="non-js-desctop">
                                                                    <label class="hidden-xs hidden-sm">
                                                                        <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                    <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            In Stock<span class="facet__value__count">
                                                                                    (2)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                    <label class="hidden-md hidden-lg">
                                                                        <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                            <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            In Stock<span class="facet__value__count">
                                                                                    (2)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                </form>
                                                            </li>
                                                            <li class="js-facet-value js-facet-init-value">
                                                                <form action="" method="get" class="non-js-desctop">
                                                                    <label class="hidden-xs hidden-sm">
                                                                        <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                        <span class="facet__list__label">
                                                                        <span class="facet__list__mark"></span>
                                                                        <span class="facet__list__text">
                                                                            Temporarily Not Available<span class="facet__value__count">
                                                                                    (1)</span>
                                                                            </span>
                                                                    </span>
                                                                    </label>
                                                                    <label class="hidden-md hidden-lg">
                                                                        <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                        <span class="facet__list__label">
                                                                            <span class="facet__list__mark"></span>
                                                                            <span class="facet__list__text">
                                                                                Temporarily Not Available<span class="facet__value__count">
                                                                                        (1)</span>
                                                                                </span>
                                                                        </span>
                                                                    </label>
                                                                </form>
                                                            </li>
                                                     </ul>
                                                        <div class="facet-button-wrapper hidden-md hidden-lg">
                                                            <button type="button" class="js-facet-group-button-apply facet-button-apply">
                                                                Apply</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div></div></div></div>
                    <div class="col-sm-12 col-md-9">
                        <!-- Product List -->
                        <div class="new-product-list-right-slot product-list-right-slot product-list row">
                            <div class="product__list--wrapper yComponentWrapper product-list-right-component">
                                <div class="print-hide pagination-bar top">
                                    <div class="pagination-toolbar">
                                        <div class="row">
                                            <div class="col-xs-12 col-md-4">
                                                <div class="pull-left">
                                                    <div class="pagination-bar-results">
                                                        <div class="total-wrapper">
                                                            <span>7 Results</span></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6 col-md-6 pagination-wrapper print-hide">
                                                <div class="pull-right sort-wrapper">
                                                    <div class="sort-refine-bar">
                                                        <div class="hidden-md hidden-lg filter-facet-button-container print-hide">
                                                            <button class="btn filter-facet-button js-show-facets" data-select-refinements-title="Select Refinements">
                                                                <span class="icon-Humburger_list"></span>
                                                                <span>Filter</span>
                                                            </button>
                                                        </div>
                                                        <div class="form-group sort-group">
                                                            <label class="control-label " for="sortForm1">
                                                                Sort by</label>

                                                            <form id="sortForm1" name="sortForm1" method="get" action="">
                                                                <select id="sortOptions1" name="sort" class="form-control">
                                                                    <option disabled="">Sort by</option>
                                                                    <option value="name-asc-c" selected="selected">
                                                                        Name (A-Z)</option>
                                                                    <option value="name-desc-c">
                                                                        Name (Z-A)</option>
                                                                    <option value="retail-price-asc-c">
                                                                        Price (Lowest To Highest)</option>
                                                                    <option value="retail-price-desc-c">
                                                                        Price (Highest To Lowest)</option>
                                                                    <option value="newest-asc-c">
                                                                        Newest First</option>
                                                                    <option value="newest-desc-c">
                                                                        Oldest First</option>
                                                                    <option value="category-asc-c">
                                                                        Category (A-Z)</option>
                                                                    <option value="category-desc-c">
                                                                        Category (Z-A)</option>
                                                                </select>
                                                            </form>
                                                        </div>
                                                        <div id="product-facet" class="mob-product-facet product__facet js-product-facet">
                                                            <div class="filters-header">
                                                                <span>Filters</span>
                                                                <button form="clearAllForm" type="submit">Clear All</button>
                                                            </div>
                                                            <div class="panel-group accordion-custom" id="facetAccordion" role="tablist" aria-multiselectable="true">
                                                                <div class="panel">
                                                                    <div class="panel-heading" role="tab" id="retailPriceFacet">
                                                                        <h4 class="panel-title">
                                                                            <div role="button" class="collapse-button collapsed" data-toggle="collapse" data-parent="#facetAccordion" href="#retailPriceFacetBodymob" aria-controls="retailPriceFacetBody">
                                                                                <span class="text-uppercase">Price<span class="">(1)</span>
                                                                                </span>
                                                                                <span class="pull-right icon-minus"></span>
                                                                            </div>
                                                                        </h4>
                                                                    </div>
                                                                    <div id="retailPriceFacetBodymob" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
                                                                        <div class="panel-body">
                                                                            <div id="tabContent_retailPriceFacet" class="facet js-facet">
                                                                                <div class="facet__name js-facet-name">
                                                                                    <span class="glyphicon facet__arrow"></span>
                                                                                    Shop by Price</div>
                                                                                <div class="facet__values js-facet-values js-facet-form">
                                                                                    <ul class="facet__list js-facet-list js-facet-top-values">
                                                                                        <li class="js-facet-value js-facet-init-value js-facet-top-value">
                                                                                            <input class="js-facet-item-value" type="hidden" name="facetValue" value="retailPriceFacet:$0-$49.99">
                                                                                            <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c">
                                                                                            <form action="" method="get" class="non-js-desctop">
                                                                                                <label class="hidden-xs hidden-sm">
                                                                                                    <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox" checked="checked">
                                                                                                    <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        $0-$49.99<span class="facet__value__count">
                                                                                                                (3)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                                <label class="hidden-md hidden-lg">
                                                                                                    <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox" checked="checked">
                                                                                                    <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        $0-$49.99<span class="facet__value__count">
                                                                                                                (3)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                            </form>
                                                                                        </li>
                                                                                        <li class="js-facet-value js-facet-init-value">
                                                                                            <form action="" method="get" class="non-js-desctop">
                                                                                                <label class="hidden-xs hidden-sm">
                                                                                                    <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                    <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        $50-$199.99<span class="facet__value__count">
                                                                                                                (3)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                                <label class="hidden-md hidden-lg">
                                                                                                    <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                    <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        $50-$199.99<span class="facet__value__count">
                                                                                                                (3)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                            </form>
                                                                                        </li>
                                                                                        <li class="js-facet-value js-facet-init-value">
                                                                                            <input class="js-facet-item-value" type="hidden" name="facetValue" value="retailPriceFacet:$500-$999.99">
                                                                                            <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c">
                                                                                            <form action="" method="get" class="non-js-desctop">
                                                                                                <label class="hidden-xs hidden-sm">
                                                                                                    <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                    <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        $500-$999.99<span class="facet__value__count">
                                                                                                                (1)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                                <label class="hidden-md hidden-lg">
                                                                                                    <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                    <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        $500-$999.99<span class="facet__value__count">
                                                                                                                (1)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                            </form>
                                                                                        </li>
                                                                                  </ul>
                                                                                    <div class="facet-button-wrapper hidden-md hidden-lg">
                                                                                        <button type="button" class="js-facet-group-button-apply facet-button-apply">
                                                                                            Apply</button>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="panel">
                                                                    <div class="panel-heading" role="tab" id="availabilityInfo">
                                                                        <h4 class="panel-title">
                                                                            <div role="button" class="collapse-button collapsed" data-toggle="collapse" data-parent="#facetAccordion" href="#availabilityInfoBodymob" aria-controls="availabilityInfoBody">
                                                                                <span class="text-uppercase">Product Availability<span class=""></span>
                                                                                </span>
                                                                                <span class="pull-right icon-minus"></span>
                                                                            </div>
                                                                        </h4>
                                                                    </div>
                                                                    <div id="availabilityInfoBodymob" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                                                        <div class="panel-body">
                                                                            <div id="tabContent_availabilityInfo" class="facet js-facet">
                                                                                <div class="facet__name js-facet-name">
                                                                                    <span class="glyphicon facet__arrow"></span>
                                                                                    Shop by Product Availability</div>
                                                                                <div class="facet__values js-facet-values js-facet-form">
                                                                                    <ul class="facet__list js-facet-list js-facet-top-values">
                                                                                        <li class="js-facet-value js-facet-init-value">
                                                                                            <input class="js-facet-item-value" type="hidden" name="facetValue" value="availabilityInfo:warehouse_n,SHIP">
                                                                                            <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c:retailPriceFacet:$0-$49.99">
                                                                                            <form action="" method="get" class="non-js-desctop">
                                                                                                <label class="hidden-xs hidden-sm">
                                                                                                    <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                    <span class="facet__list__label">
                                                                                                        <span class="facet__list__mark"></span>
                                                                                                        <span class="facet__list__text">
                                                                                                            In Stock<span class="facet__value__count">
                                                                                                                    (2)</span>
                                                                                                            </span>
                                                                                                    </span>
                                                                                                </label>
                                                                                                <label class="hidden-md hidden-lg">
                                                                                                    <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        In Stock<span class="facet__value__count">
                                                                                                                (2)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                            </form>
                                                                                        </li>
                                                                                        <li class="js-facet-value js-facet-init-value">
                                                                                            <input class="js-facet-item-value" type="hidden" name="facetValue" value="availabilityInfo:warehouse_n,TNA">
                                                                                            <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c:retailPriceFacet:$0-$49.99">
                                                                                            <form action="" method="get" class="non-js-desctop">
                                                                                                <label class="hidden-xs hidden-sm">
                                                                                                    <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                    <span class="facet__list__label">
                                                                                                        <span class="facet__list__mark"></span>
                                                                                                        <span class="facet__list__text">
                                                                                                            Temporarily Not Available<span class="facet__value__count">
                                                                                                                    (1)</span>
                                                                                                            </span>
                                                                                                    </span>
                                                                                                </label>
                                                                                                <label class="hidden-md hidden-lg">
                                                                                                    <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                    <span class="facet__list__label">
                                                                                                    <span class="facet__list__mark"></span>
                                                                                                    <span class="facet__list__text">
                                                                                                        Temporarily Not Available<span class="facet__value__count">
                                                                                                                (1)</span>
                                                                                                        </span>
                                                                                                </span>
                                                                                                </label>
                                                                                            </form>
                                                                                        </li>
                                                                                       </ul>
                                                                                    <div class="facet-button-wrapper hidden-md hidden-lg">
                                                                                        <button type="button" class="js-facet-group-button-apply facet-button-apply">
                                                                                            Apply</button>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="product__listing product__list row js-query-result new-product__listing">
                                	<c:forEach items="${searchPageData.results}" var="product">
                                		<div class="product__list--item col-sm-12 col-md-4 js-query-result-item">
	                                        <div class="list-item-wrapper">
	                                            <div class="product-list__item">
	                                                <!--<img src="${themeResourcePath}/images/limited_stock.png" class="product-list__flag" alt="limited stock" />-->
	                                                <div class="plp-item-label limit-stock">Limited Stock<span class="right-white"></span></div>
	                                                <div class="product-list__item-content amwahover">
	                                                    <label class="checkbox-element-wrapper">
	                                                        <input class="shopping-cart-entry-checkbox _checkbox-element-global-class" value="" data-product-code="" type="checkbox">
	                                                        <span class="_checkbox-element-global-span"></span>
	                                                    </label>
	                                                    <button class="quick-view-btn" onclick="javascript:location.href='#'">QUICK VIEW</button>
	                                                    <a class="product-list__thum" target="_self" href="${product.url}">
	                                                        <img src="${themeResourcePath}/images/product_list_item1.png" class="product-list__thumbnail" alt="product"></img>
	                                                    </a>
	                                                    <div class="product-list__item-detail">
	                                                        <p class="product-list__item-title">
	                                                            <a class="product-list__item-title" target="_self" href="${product.url}">${product.description}</a></p>
	                                                        <p class="product-list__item-count">30 Count</p>
	                                                        <p class="product-list__item-number">Item #: ${product.code}</p>
	                                                        <div class="product-list__item-title product-list__item-aboprice">
	                                                            <span class="product-list__item-abolabel">ABO Cost:</span>
	                                                            <span class="product-list__item-abovalue">$16.62</span></div>
	                                                        <div class="product-list__item-retailprice">
	                                                            <span class="product-list__item-abolabel">Retail Price:</span>
	                                                            <span class="product-list__item-abovalue">$25.55</span></div>
	                                                        <div class="product-list__item-retailprice">
	                                                            <span class="product-list__item-abolabel">PV / BV:</span>
	                                                            <span class="product-list__item-abovalue">4.50 / 14.21</span></div>
	                                                    </div>
	                                                </div>
	                                                <div class="product-list__item-link col-md-12">
	                                                    <a href="#" class="btn-blue-white" data-toggle="modal" data-target="#cart-modal">add to cart</a>
	                                                    <a class="product-list__item-link-text product-list__item-link-common col-xs-6 col-md-10" href="#">Shopping list</a>
	                                                    <a class="product-list__item-link-ditto product-list__item-link-common col-xs-6 col-md-2" href="#">
	                                                        <span class="like-shape"></span>
	                                                        <span class="wish-list">WISH LIST</span></a>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
                                	</c:forEach>
                                
                                
                                
                                    
                                </div>
                                <p class="text-center show-more-products-wrapper">
                                    <button type="button" id="show-more" class="btn btn-link">
                                        <span class="text-wrap">Show more</span>
                                        <span class="icon icon-arrow-dropdown"></span>
                                    </button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="quick"></div>
            <div id="footer"></div>
        </main>
        <div class="overlay"></div>
        <div class="view-box">
            <div class="cart-popup__dialog">
                <div class="cart-popup__header">
                    <span class="cart-popup__header-text">QUICK VIEW
                        <img class="cart-popup__close quick-view-close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
                <div class="cart-popup__content">
                    <div class="cart-popup__item-info amwahover">
                        <img src="${themeResourcePath}/images/product_list_item2.png" class="cart-popup__thumbnail" alt="product">
                        <div class="cart-popup__item-detail">
                            <p class="cart-popup__item-title">Nutrilite® Heart Health Pack Has a Long Name – Wraps to Two Lines</p>
                            <span class="cart-popup__item-number">Item #: 116991</span>
                            <div class="cart-popup__item-title cart-popup__item-aboprice">
                                <span class="product-list__item-abovalue new-product-list__item-abovalue">$16.62</span>
                                <span>ABO Price:</span></div>
                            <div class="cart-popup__item-retailprice">
                                <span>Retail Price:</span>
                                <span class="product-list__item-abovalue Retail-product-list__item-abovalue">$25.55</span>
                                <span class="view-box-divider">|</span>
                                <span>PV / BV:</span>
                                <span class="product-list__item-abovalue PV-product-list__item-abovalue">4.50 / 14.21</span></div>
                            <div class="cart-popup__quantity cart-popup__item-retailprice">
                                <div class="amway-theme qty-selector js-qty-selector control-group">
                                    <div class="row">
                                        <div class="size-selector-container">
                                            <label class="control-label" for="pdpAddtoCartInput">Size</label>
                                            <select class="size-select text-center js-qty-selector-input form-control" value="60 Packets">
                                                <option>60 Packets</option>
                                                <option>30 Packets</option></select>
                                        </div>
                                        <div class="qty-selector-container">
                                            <label class="control-label" for="pdpAddtoCartInput">Qty</label>
                                            <input type="text" maxlength="3" class="text-center js-qty-selector-input" size="1" value="1" data-max="FORCE_IN_STOCK" data-min="1" name="pdpAddtoCartInput" id="pdpAddtoCartInput">
                                            <div class="product-stock">
                                                <span class="stock">
                                                    <span class="product-availability">
                                                        <span class="green">
                                                            <span class="icon icon-check-bold"></span>
                                                            <span class="text text-uppercase">In Stock</span></span>
                                                    </span>
                                                </span>
                                            </div>
                                        </div>
                                        <a href="#">
                                            <div class="view-more-details">View More Details</div></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cart-popup__item-link">
                        <div class="cart-popup__item-link-content">
                            <button id="addToCartButton" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">Add to cart</button>
                            <button id="BuyNow" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">BUY NOW</button></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="add-to-cart-box"class="cbox">
            <div class="cart-popup__dialog">
                <div class="cart-popup__header">
                    <span class="cart-popup__header-text">added to your shopping cart
                        <img class="cart-popup__close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
                <div class="cart-popup__content">
                    <div class="cart-popup__item-info amwahover">
                        <img src="${themeResourcePath}/images/lo-c-bundle.png" class="cart-popup__thumbnail" alt="product">
                        <div class="cart-popup__item-detail">
                            <p class="cart-popup__item-title">Nutrilite® Heart Health Pack Has a Long Name – Wraps to Two Lines</p>
                            <p class="cart-popup__item-count">60 Count</p>
                            <span class="cart-popup__item-number">Item #: 116991</span>
                            <div class="cart-popup__item-title cart-popup__item-aboprice">
                                <span>ABO Price:</span>
                                <span class="product-list__item-abovalue">$16.62</span></div>
                            <div class="cart-popup__item-retailprice">
                                <span>Retail Price:</span>
                                <span class="product-list__item-abovalue">$25.55</span></div>
                            <div class="cart-popup__item-retailprice">
                                <span>PV / BV:</span>
                                <span class="product-list__item-abovalue">4.50 / 14.21</span></div>
                            <div class="cart-popup__quantity cart-popup__item-retailprice">
                                <span class="cart-popup__qty">Qty: 1</span>
                                <a>Edit</a>
                            </div>
                        </div>
                    </div>
                    <div class="cart-popup__item-link">
                        <a href="Checkout-1-shippingAndDelivery.html" class="btn-blue-white">check out</a>
                        <a class="cart-popup__item-link-text" href="Vitamins_and_Supplements_Product_listing_page.html">Continue Shopping</a></div>
                </div>
            </div>
        </div>

</template:page>