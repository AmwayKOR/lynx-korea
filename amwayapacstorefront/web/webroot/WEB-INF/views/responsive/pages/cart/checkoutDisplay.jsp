<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/cart/checkout" var="checkoutUrl" scope="session"/>
<div class="row cart-actions new-cart-actions">
    <div class="col-xs-12 col-md-3 checkout-button-container">
        <button class="btn btn-primary btn-block checkoutButton continueCheckout" data-checkout-url="#" onclick="javascript:location.href='Checkout-1-shippingAndDelivery.html'">proceed to checkout</button></div>
    <div class="col-xs-12 col-md-8 continue-shopping-button-container">
        <button class="btn btn-link btn-block continueShoppingButton" data-continue-shopping-url="#">Continue Shopping</button></div>
</div>
