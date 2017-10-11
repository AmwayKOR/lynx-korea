<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<template:page pageTitle="${pageTitle}">
        <main>
            <div id="header"></div>
            <c:set var="aboUser" value="false"/>
            <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
       			<c:set var="aboUser" value="true"/>
       		</sec:authorize>
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
                    <h1 class="col-sm-12 product-list-page-title">${categoryName}</h1>
                    <div class="col-xs-3 print-hide">
                        <div class="product-list-left-refinements-slot">
                            <div class="product-list-left-refinements-component">
                                <div id="product-facet" class="pc-product-facet product__facet js-product-facet hidden-xs hidden-sm">
                                <div class="filters-header">
                                    <span>Filters</span>
                                    <form hidden="hidden" action="" method="get" id="clearAllForm">
                                    	<input type="hidden" name="q"/>
                                    </form>
                                    <button form="clearAllForm" type="submit">Clear All Desktop</button>
                                </div>
                                <div class="panel-group accordion-custom" id="facetAccordion" role="tablist" aria-multiselectable="true">
                                <c:forEach items="${searchPageData.facets}" var="facet">
                                	<c:set var="showFacet" value="true"/>
									<c:choose>
										<c:when test="${(aboUser == false) && (facet.code eq 'aboPriceRange')}">
											<c:set var="showFacet" value="false"/>
										</c:when>
										<c:when test="${(aboUser == true) && (facet.code eq 'retailPriceRange')}">
											<c:set var="showFacet" value="false"/>
										</c:when>
									</c:choose>                                	
                                	<c:if test="${showFacet}">
	                                	<div class="panel">
	                                        <div class="panel-heading" role="tab" id="${facet.code}Facet">
	                                            <h4 class="panel-title">
	                                                <div role="button" class="collapse-button collapsed" data-toggle="collapse" data-parent="#facetAccordion" href="#${facet.code}FacetBodypc" aria-controls="${facet.code}FacetBody">
	                                                    <span class="text-uppercase">${facet.name}<span class=""></span>
	                                                    </span>
	                                                    <span class="pull-right icon-minus"></span>
	                                                </div>
	                                            </h4>
	                                        </div>
	                                        <div id="${facet.code}FacetBodypc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
	                                            <div class="panel-body">
	                                                <div id="tabContent_${facet.code}Facet" class="facet js-facet">
	                                                    <div class="facet__name js-facet-name">
	                                                        <span class="glyphicon facet__arrow"></span>
	                                                        Shop by ${facet.name}</div>
	                                                    <div class="facet__values js-facet-values js-facet-form">
	                                                        <ul class="facet__list js-facet-list js-facet-top-values">
	                                                       		<c:forEach items="${facet.values}" var="facetValue">
	                                                        		<li class="js-facet-value js-facet-init-value js-facet-top-value">
	                                                                   <input class="js-facet-item-value" type="hidden" name="facetValue" value="retailPriceFacet:$0-$49.99">
	                                                                   <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c">
	                                                                   <form action="" method="get" class="non-js-desctop">
	                                                                   <input type="hidden" name="q" value="${facetValue.query.query.value}"></input>
	                                                                       <label class="hidden-xs hidden-sm">
	                                                                           <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox" <c:if test="${facetValue.selected}">checked="checked"</c:if>>
	                                                                           <span class="facet__list__label">
	                                                                           <span class="facet__list__mark"></span>
	                                                                           <span class="facet__list__text">
	                                                                               ${facetValue.name}<span class="facet__value__count">
	                                                                                       (${facetValue.count})</span>
	                                                                               </span>
	                                                                       </span>
	                                                                       </label>
	                                                                       <label class="hidden-md hidden-lg">
	                                                                           <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox">
	                                                                           <span class="facet__list__label">
	                                                                           <span class="facet__list__mark"></span>
	                                                                           <span class="facet__list__text">
	                                                                               ${facetValue.name}<span class="facet__value__count">
	                                                                                       (${facetValue.count})</span>
	                                                                               </span>
	                                                                       </span>
	                                                                       </label>
	                                                                   </form>
	                                                               </li>
	                                                       		</c:forEach>
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
                                    </c:if>
                                </c:forEach>
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
                                                        
                                                            <span>${searchPageData.pagination.totalNumberOfResults} Results</span></div>
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
                                                                    <c:forEach items="${searchPageData.sorts}" var="sort">
                                                                    	<c:set var="showSort" value="true"/>
                                                                    	<c:choose>
																			<c:when test="${(aboUser == false) && ((sort.code eq 'pvbv-desc-c') || (sort.code eq 'pvbv-asc-c') || (sort.code eq 'abo-price-desc-c') || (sort.code eq 'abo-price-asc-c'))}">
																				<c:set var="showSort" value="false"/>
																			</c:when>
																			<c:when test="${(aboUser == true) && ((sort.code eq 'retail-price-desc-c') || (sort.code eq 'retail-price-asc-c'))}">
																				<c:set var="showSort" value="false"/>
																			</c:when>
																		</c:choose>
																		<c:if test="${showSort}">
                                                                    		<option value="${sort.code}" <c:if test="${sort.selected}">selected="selected"</c:if>>${sort.name}</option>
                                                                    	</c:if>
                                                                    </c:forEach>
                                                                </select>
                                                                <input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
                                                            </form>
                                                        </div>
                                                        <div id="product-facet" class="mob-product-facet product__facet js-product-facet">
                                                            <div class="filters-header">
                                                                <span>Filters</span>
                                                                <button form="clearAllForm" type="submit">Clear All Mobile</button>
                                                            </div>
                                                            <div class="panel-group accordion-custom" id="facetAccordion" role="tablist" aria-multiselectable="true">
                                                            <c:forEach items="${searchPageData.facets}" var="facet">
                                                                <div class="panel">
                                                                    <div class="panel-heading" role="tab" id="${facet.code}Facet">
                                                                        <h4 class="panel-title">
                                                                            <div role="button" class="collapse-button collapsed" data-toggle="collapse" data-parent="#facetAccordion" href="#${facet.code}FacetBodymob" aria-controls="${facet.code}FacetBody">
                                                                                <span class="text-uppercase">${facet.name}<span class=""></span>
                                                                                </span>
                                                                                <span class="pull-right icon-minus"></span>
                                                                            </div>
                                                                        </h4>
                                                                    </div>
                                                                    <div id="${facet.code}FacetBodymob" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
                                                                        <div class="panel-body">
                                                                            <div id="tabContent_${facet.code}Facet" class="facet js-facet">
                                                                                <div class="facet__name js-facet-name">
                                                                                    <span class="glyphicon facet__arrow"></span>
                                                                                    Shop by ${facet.name}</div>
                                                                                <div class="facet__values js-facet-values js-facet-form">
                                                                                <ul class="facet__list js-facet-list js-facet-top-values">
                                                                               		<c:forEach items="${facet.values}" var="facetValue">
                                                                                		<li class="js-facet-value js-facet-init-value js-facet-top-value">
                                                                                           <input class="js-facet-item-value" type="hidden" name="facetValue" value="retailPriceFacet:$0-$49.99">
                                                                                           <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c">
                                                                                           <form action="" method="get" class="non-js-desctop">
                                                                                           <input type="hidden" name="q" value="${facetValue.query.query.value}"></input>
                                                                                               <label class="hidden-xs hidden-sm">
                                                                                                   <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox">
                                                                                                   <span class="facet__list__label">
                                                                                                   <span class="facet__list__mark"></span>
                                                                                                   <span class="facet__list__text">
                                                                                                       ${facetValue.name}<span class="facet__value__count">
                                                                                                               (${facetValue.count})</span>
                                                                                                       </span>
                                                                                               </span>
                                                                                               </label>
                                                                                               <label class="hidden-md hidden-lg">
                                                                                                   <input class="facet__list__checkbox js-facet-checkbox js-facet-item-checkbox sr-only" type="checkbox" <c:if test="${facetValue.selected}">checked="checked"</c:if>>
                                                                                                   <span class="facet__list__label">
                                                                                                   <span class="facet__list__mark"></span>
                                                                                                   <span class="facet__list__text">
                                                                                                       ${facetValue.name}<span class="facet__value__count">
                                                                                                               (${facetValue.count})</span>
                                                                                                       </span>
                                                                                               </span>
                                                                                               </label>
                                                                                           </form>
                                                                                       </li>
                                                                               		</c:forEach>
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
                                                             </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
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
	                                                <product:productStockLevel stockLevel="${product.stock.stockLevelStatus.code}"/>
	                                                <div class="product-list__item-content amwahover">
	                                                    <button class="quick-view-btn" data-product-code="${product.code}">QUICK VIEW</button>
	                                                    <a class="product-list__thum" target="_self" href="${product.url}">
	                                                        <img src="${themeResourcePath}/images/product_list_item1.png" class="product-list__thumbnail" alt="product"></img>
	                                                    </a>
	                                                    <div class="product-list__item-detail">
	                                                        <p class="product-list__item-title">
	                                                            <a class="product-list__item-title" target="_self" href="${product.url}">${product.name}</a></p>
	                                                        <p class="product-list__item-count">30 Count</p>
	                                                        <p class="product-list__item-number">Item #: ${product.code}</p>
	                                                        <div class="product-list__item-title product-list__item-aboprice">
	                                                            <span class="product-list__item-abolabel">ABO Cost:</span>
	                                                            <span class="product-list__item-abovalue">${product.price.formattedValue}</span></div>
	                                                        <div class="product-list__item-retailprice">
	                                                            <span class="product-list__item-abolabel">Retail Price:</span>
	                                                            <span class="product-list__item-abovalue">${product.retailPrice.formattedValue}</span></div>
	                                                        <div class="product-list__item-retailprice">
	                                                            <span class="product-list__item-abolabel">PV / BV:</span>
	                                                            <span class="product-list__item-abovalue">${product.price.amwayValue.pointValue} / ${product.price.amwayValue.businessVolume}</span></div>
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
                               	<c:if test="${searchPageData.pagination.currentPage < (searchPageData.pagination.numberOfPages - 1)}">
	                                <div class="text-center show-more-products-wrapper">
	                                	<form action="${ requestScope['javax.servlet.forward.servlet_path']}" method="get">
	                                		<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
	                                		<input type="hidden" name="sort" value=""/>
	                                		<input type="hidden" name="page" value="${searchPageData.pagination.currentPage + 1}"/>
		                                    <button type="button" id="show-more" class="btn btn-link">
		                                        <span class="text-wrap">Show more</span>
		                                        <span class="icon icon-arrow-dropdown"></span>
		                                    </button>
	                                	</form>
	                                </div>
                               	</c:if>
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