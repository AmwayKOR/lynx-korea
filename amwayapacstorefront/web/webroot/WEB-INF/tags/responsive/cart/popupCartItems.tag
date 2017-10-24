<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData" %>
<%@ attribute name="quantity" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="cart-popup__item-info amwahover">
    <product:productPrimaryImage product="${entry.product}" format="cartIcon"/>
    <div class="cart-popup__item-detail">
        <p class="cart-popup__item-title">${fn:escapeXml(product.name)}</p>
        <span class="cart-popup__item-number">Item #: ${fn:escapeXml(product.code)}</span>
        <div class="cart-popup__item-title cart-popup__item-aboprice">
            <span>ABO Price:</span>
            <span class="product-list__item-abovalue"><format:fromPrice priceData="${product.price}"/></span></div>
        <div class="cart-popup__item-retailprice">
            <span>Retail Price:</span>
            <span class="product-list__item-abovalue"><format:fromPrice priceData="${product.retailPrice}"/></span></div>
        <div class="cart-popup__item-retailprice">
            <span>PV / BV:</span>
            <span class="product-list__item-abovalue">${product.price.amwayValue.pointValue} / ${product.price.amwayValue.businessVolume}</span></div>
        <div class="cart-popup__quantity cart-popup__item-retailprice">
            <span class="cart-popup__qty">Qty: 1</span>
            <a>Edit</a>
        </div>
    </div>
</div>