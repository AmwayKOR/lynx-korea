<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="isOrderForm" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<div class="product-details">
    <div class="bundle"></div>
    <p class="price retail-price-discount"><format:fromPrice priceData="${product.price}"/>
        <span class="description-cost"><spring:theme code="product.volumePrices.column.aboCost"/></span></p>
    <span class="additional-price">
        <ul>
            <li>Retail Price:
                <span class="retail-price-strike-out"><format:fromPrice priceData="${product.retailPrice}"/> | <spring:theme code="product.volumePrices.column.pvBV"/> ${product.price.amwayValue.pointValue} / ${product.price.amwayValue.businessVolume}</span></li>
        </ul>
    </span>
</div>