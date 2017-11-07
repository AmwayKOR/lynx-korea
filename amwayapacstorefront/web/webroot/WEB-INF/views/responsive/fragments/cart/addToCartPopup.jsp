<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>

<c:url var="cartPageUrl" value="/cart" />
<div id="add-to-cart-box"class="cbox">
    <div class="cart-popup__dialog">
        <div class="cart-popup__header">
            <span class="cart-popup__header-text"><spring:theme code="basket.added.to.basket" />
                <img class="cart-popup__close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
        <div class="cart-popup__content">
            <cart:popupCartItems entry="${entry}" product="${product}" quantity="${quantity}"/>
            <div class="cart-popup__item-link">
                <a href="${cartPageUrl}" class="btn-blue-white"><spring:theme code="checkout.checkout" /></a>
                <a class="cart-popup__item-link-text closeCbox" href="#"><spring:theme code="cart.page.continue" /></a></div>
        </div>
    </div>
</div>
