<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="quote" tagdir="/WEB-INF/tags/responsive/quote" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="js-cart-totals js-cart-totals-order-summary">
    <div class="wrapper row auto-subtotal">
        <div class="col-xs-6 cart-totals-left"><spring:theme code="basket.page.totals.subtotal"/>  </div>
        <div class="col-xs-6 cart-totals-right text-right"><format:price priceData="${cartData.subTotal}"/></div></div>
    <div class="wrapper row auto-promotions">
        <div class="col-xs-8 cart-totals-left">
            <span><spring:theme code="basket.page.totals.promotions"/> </span>
            <span class="cart-order-symmary-button-wrapper">(
                <span class="details-button js-checkout-promotions-details-btn" data-content="orderSummaryPromotionsModal"><spring:theme code="basket.page.totals.promotions.details"/> </span>)</span></div>
        <div class="col-xs-4 cart-totals-right text-right red">- $0.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left"><spring:theme code="basket.page.totals.shipping"/></div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left"><spring:theme code="basket.page.totals.taxOne"/></div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left"><spring:theme code="basket.page.totals.taxTwo"/></div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left"><spring:theme code="basket.page.totals.serviceFee"/></div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper wrapper-borders row auto-total">
        <div class="col-xs-6 cart-totals-left"><spring:theme code="basket.page.totals.total"/></div>
        <div class="col-xs-6 cart-totals-right text-right"><format:price priceData="${cartData.totalPrice}"/></div></div>
        <div class="wrapper row auto-total noTop">
                <div class="col-xs-6 cart-totals-left"><spring:theme code="basket.page.totals.totalPVBV"/></div>
                <div class="col-xs-6 cart-totals-right text-right">${cartData.totalPrice.amwayValue.pointValue}</div></div>
</div>