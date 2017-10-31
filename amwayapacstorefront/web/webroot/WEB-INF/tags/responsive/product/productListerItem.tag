<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="${product.url}" var="productPDPurl"/>
<div class="amway-suggest__item-container">
     <div class="amway-suggest__item">
		<div class="product-list__item">
		    <!--<img src="${themeResourcePath}/images/limited_stock.png" class="product-list__flag" alt="limited stock" />-->
		    <product:productTileBadge stockLevel="${product.stock.stockLevelStatus.code}"/>
		    <div class="product-list__item-content amwahover">
		        <button class="quick-view-btn" data-product-code="${product.code}"><spring:theme code="plp.producttile.quick.view"/></button>
		        <a class="product-list__thum" target="_self" href="${productPDPurl}">
		        	<product:productPrimaryImage product="${product}" format="productGrid" cssClass="product-list__thumbnail"/>
		        </a>
		        <div class="product-list__item-detail">
		            <p class="product-list__item-title">
		                <a class="product-list__item-title" target="_self" href="${productPDPurl}">${product.name}</a></p>
		            <p class="product-list__item-number"><spring:theme code="plp.producttile.item.no"/> ${product.code}</p>
		            <div class="product-list__item-title product-list__item-aboprice">
		                <span class="product-list__item-abolabel"><spring:theme code="plp.producttile.abo.price"/></span>
		                <span class="product-list__item-abovalue">${product.price.formattedValue}</span></div>
		            <div class="product-list__item-retailprice">
		                <span class="product-list__item-abolabel"><spring:theme code="plp.producttile.retail.price"/></span>
		                <span class="product-list__item-abovalue">${product.retailPrice.formattedValue}</span></div>
		            <div class="product-list__item-retailprice">
		                <span class="product-list__item-abolabel"><spring:theme code=""/></span>
		                <span class="product-list__item-abovalue">${product.price.amwayValue.pointValue} / ${product.price.amwayValue.businessVolume}</span></div>
		        </div>
		    </div>
		    <div class="product-list__item-link col-md-12">
		        <a href="#" class="btn-blue-white" data-toggle="modal" data-target="#cart-modal"><spring:theme code="plp.producttile.addtocart"/></a>
		        <a class="product-list__item-link-text product-list__item-link-common col-xs-6 col-md-10" href="#"><spring:theme code="plp.producttile.shoppinglist"/></a>
		        <a class="product-list__item-link-ditto product-list__item-link-common col-xs-6 col-md-2" href="#">
		            <span class="like-shape"></span>
		            <span class="wish-list"><spring:theme code="plp.producttile.wishlist"/></span></a>
		    </div>
		</div>
	</div>
</div>