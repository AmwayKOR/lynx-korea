<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData" %>
<%@ attribute name="quantity" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="cart-popup__item-info amwahover">
    <product:productPrimaryImage product="${entry.product}" format="quickView" cssClass="cart-popup__thumbnail"/>
    <div class="cart-popup__item-detail">
        <p class="cart-popup__item-title">${fn:escapeXml(entry.product.name)}</p>
        <span class="cart-popup__item-number">
        <spring:theme code="pdp.add.to.cart.popup.label.item" arguments="${fn:escapeXml(entry.product.code)}" /></span>
        <div class="cart-popup__item-title cart-popup__item-aboprice">
            <span><spring:theme code="pdp.add.to.cart.popup.label.aboprice" /></span>
            <span class="product-list__item-abovalue"><format:fromPrice priceData="${entry.product.price}"/></span></div>
        <div class="cart-popup__item-retailprice">
            <span><spring:theme code="pdp.add.to.cart.popup.label.retail" /></span>
            <span class="product-list__item-abovalue"><format:fromPrice priceData="${entry.product.retailPrice}"/></span></div>
        <div class="cart-popup__item-retailprice">
            <span><spring:theme code="pdp.add.to.cart.popup.label.pvbv" /></span>
            <span class="product-list__item-abovalue">${entry.product.price.amwayValue.pointValue} / ${entry.product.price.amwayValue.businessVolume}</span></div>
        <div class="cart-popup__quantity cart-popup__item-retailprice">
            <span class="cart-popup__qty"><spring:theme code="pdp.add.to.cart.popup.label.quantity" arguments="${quantity}" /></span>
            <a><spring:theme code="pdp.add.to.cart.popup.label.edit" /></a>
        </div>
    </div>
</div>