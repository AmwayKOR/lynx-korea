<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="searchPageData" type="de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>


<div class="product__listing product__list row js-query-result new-product__listing">
	<c:forEach items="${searchPageData.results}" var="product">
		<div class="product__list--item col-sm-12 col-md-4 js-query-result-item">
         <div class="list-item-wrapper">
         	<product:productListerItem product="${product}"/>
         </div>
     </div>
	</c:forEach>
</div>
