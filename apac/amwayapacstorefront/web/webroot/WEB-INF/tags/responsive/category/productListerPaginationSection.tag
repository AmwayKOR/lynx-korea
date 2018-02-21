<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="searchPageData" type="de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData" %>

<c:if test="${searchPageData.pagination.currentPage < (searchPageData.pagination.numberOfPages - 1)}">
 <div class="text-center show-more-products-wrapper">
 	<form action="${requestScope['javax.servlet.forward.servlet_path']}" method="get">
 		<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
 		<input type="hidden" name="sort" value=""/>
 		<input type="hidden" name="page" value="${searchPageData.pagination.currentPage + 1}"/>
      <button type="button" id="show-more" class="btn btn-link btn-show-more-product-lister">
          <span class="text-wrap"><spring:theme code="plp.show.more"/></span>
          <span class="icon icon-arrow-dropdown"></span>
      </button>
 	</form>
 </div>
</c:if>