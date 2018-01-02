<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="row product-recentlyviewed">
	<div class="col-sm-12 column product-description__section">
		<div class="product-title-container">
			<h2 class="amway-learning__title">
				<span class="mH"><spring:theme code="recentvieweditems.heading"/></span></h2>
		</div>
		<div class="product-recentlyviewed__imagelist" id="recentlyViewedListTab">
		    <c:forEach items="${recentlyViewedProducts.results}" var="product">
		        <div>
                     <product:productPrimaryImage product="${product}" format="recentlyViewed" cssClass="product-recentlyviewed__img" />
                   
                </div>
		    </c:forEach>
		</div>
	</div>
</div>
