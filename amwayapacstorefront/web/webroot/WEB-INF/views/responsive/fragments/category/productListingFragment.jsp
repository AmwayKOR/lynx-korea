<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<div class="product-listing-section">
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
	<div class="see-more-section">
		<c:if test="${searchPageData.pagination.currentPage < (searchPageData.pagination.numberOfPages - 1)}">
			<form action="${ requestScope['javax.servlet.forward.servlet_path']}" method="get">
	       		<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
	       		<input type="hidden" name="sort" value=""/>
	       		<input type="hidden" name="page" value="${searchPageData.pagination.currentPage + 1}"/>
	            <button type="button" id="show-more" class="btn btn-link">
	                <span class="text-wrap">Show more</span>
	                <span class="icon icon-arrow-dropdown"></span>
	            </button>
	       	</form>
   		</c:if>
	</div>
</div>