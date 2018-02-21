<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<div>
	<div class="product-listing-section">
		<c:forEach items="${searchPageData.results}" var="product">
			<div class="product__list--item col-sm-12 col-md-4 js-query-result-item">
		        <div class="list-item-wrapper">
		            <product:productListerGridItem product="${product}"/>
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
	            <button type="button" id="show-more" class="btn btn-link btn-show-more-product-listing">
	                <span class="text-wrap">Show more</span>
	                <span class="icon icon-arrow-dropdown"></span>
	            </button>
	       	</form>
   		</c:if>
	</div>
</div>