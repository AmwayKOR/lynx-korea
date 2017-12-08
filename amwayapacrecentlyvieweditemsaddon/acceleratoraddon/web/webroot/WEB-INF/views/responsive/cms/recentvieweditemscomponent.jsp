<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<div class="row product-recentlyviewed">
	<div class="col-sm-12 column product-description__section">
		<div class="product-title-container">
			<h2 class="amway-learning__title">
				<span class="mH">Your</span>Recently Viewed
				<span class="mH">Items</span></h2>
		</div>
		<div class="product-recentlyviewed__imagelist" id="recentlyViewedListTab">
		    <c:forEach items="${recentlyViewedProducts}" var="product">
		        <div>
               	    <img class="product-recentlyviewed__img" src="images/vitamin-c.png" alt="${product}" />
                </div>
		    </c:forEach>
		</div>
	</div>
</div>