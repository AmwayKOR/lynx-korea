<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="quote" tagdir="/WEB-INF/tags/responsive/quote" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="js-cart-totals js-cart-totals-order-summary">
    <div class="wrapper row auto-subtotal">
        <div class="col-xs-6 cart-totals-left">Item(s) Subtotal</div>
        <div class="col-xs-6 cart-totals-right text-right">$58.20</div></div>
    <div class="wrapper row auto-promotions">
        <div class="col-xs-8 cart-totals-left">
            <span>Promotions</span>
            <span class="cart-order-symmary-button-wrapper">(
                <span class="details-button js-checkout-promotions-details-btn" data-content="orderSummaryPromotionsModal">Details</span>)</span></div>
        <div class="col-xs-4 cart-totals-right text-right red">- $12.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left">Shipping</div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left">Tax One</div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left">Tax Two</div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper row auto-delivery">
        <div class="col-xs-6 cart-totals-left">Service Fee(s)</div>
        <div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>
    <div class="wrapper wrapper-borders row auto-total">
        <div class="col-xs-6 cart-totals-left">Total</div>
        <div class="col-xs-6 cart-totals-right text-right">$46.20</div></div>
    <div class="wrapper row auto-total noTop">
        <div class="col-xs-6 cart-totals-left">TOTAL PV/ BV</div>
        <div class="col-xs-6 cart-totals-right text-right">222.45 / 699.63</div></div>
</div>