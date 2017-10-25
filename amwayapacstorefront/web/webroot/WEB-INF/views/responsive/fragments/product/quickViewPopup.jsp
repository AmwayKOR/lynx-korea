<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="cart-popup__dialog">
    <div class="cart-popup__header">
        <span class="cart-popup__header-text">QUICK VIEW
        	<img class="cart-popup__close quick-view-close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true">
        </span>
    </div>
    <div class="cart-popup__content">
        <div class="cart-popup__item-info amwahover">
        	<product:productPrimaryImage product="${product}" format="quickView" cssClass="cart-popup__thumbnail"/>
          	<product:quickViewPopupDetailsSection product="${product}"/>
        </div>
        <div class="cart-popup__item-link">
            <div class="cart-popup__item-link-content">
            <button id="addToCartButton" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">Add to cart</button>
            <button id="BuyNow" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="openDialog();">BUY NOW</button></div>
        </div>
    </div>
</div>
