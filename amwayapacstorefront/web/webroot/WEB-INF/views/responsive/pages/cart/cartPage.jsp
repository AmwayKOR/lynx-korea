<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<template:page pageTitle="${pageTitle}">
    <div id="header"></div>
    <div class="container-fluid main-container">
        <div class="print-hide breadcrumb-section">
            <ol class="breadcrumb">
                <li>
                    <a href="homepage.html">Home</a></li>
                <li class="active">Shopping Cart</li></ol>
        </div>
    </div>
    <div class="container-fluid main-container">
        <div class="row border cartTitile">
            <h1 class="product-list-page-title shopping-list-page-title mb25">SHOPPING CART</h1>
            <h4 class="product-list-page-sub-title">Welcome to your shopping cart! You can manage and complete your orders below.</h4></div>
        <div class="row cart-content-wrapper">
            <div class="account-section-content new-account-section-content">
                <div id="quickOrder" class="account-section shopping-cart-quick-shop-wrapper" data-grid-confirm-message="">
                    <div class="quick-shop-container">
                        <div id="quickShopAccordion" class="panel-group accordion-custom" role="tablist" aria-multiselectable="true">
                            <div class="panel">
                                <div class="panel-heading" role="tab" id="quickShop">
                                    <h4 class="panel-title">
                                        <div role="button" data-toggle="collapse" data-parent="#quickShopAccordion" href="#quickShopBody" aria-controls="quickShopBody" class="collapsed">
                                            <img src="${themeResourcePath}/images/shopping-cart-icon.png" alt="cart" class="cart-header__cart-icon" />
                                            <span class="text-uppercase accordion-header-text">Quick Shop</span>
                                            <span class="pull-right icon-minus"></span>
                                        </div>
                                    </h4>
                                </div>
                                <div id="quickShopBody" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
                                    <div class="panel-body">
                                        <div id="quickShopForm">
                                            <div class="item-name-container">
                                                <label class="js-sku-input-field-label sku-input-field-label">Item name or number</label>
                                                <input class="js-quick-shop-name quick-shop-name js-name-input-field name-input-field ui-autocomplete-input" data-options="{&quot;autocompleteUrl&quot; : &quot;/lynxstorefront/lynx/en/quickOrder/autocomplete&quot;,&quot;minCharactersBeforeRequest&quot; : &quot;3&quot;,&quot;waitTimeBeforeRequest&quot; : &quot;500&quot;,&quot;displayProductImages&quot; : true}" autocomplete="off" type="text">
                                                <input class="js-hidden-sku-field" type="hidden"></div>
                                            <div class="qty-container">
                                                <label>Qty</label>
                                                <input class="js-quick-shop-qty quick-shop-qty" type="text"></div>
                                            <div class="add-item-container">
                                                <button class="btn btn-primary btn-block js-quick-shop-submit">Add item</button></div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="js-quick-shop-message quick-shop-message">
                                                    <span class="icon-check-bold"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cart-top-totals">your cart (26 items)</div>
            <div class="row">
                <cms:pageSlot position="CartContentSlot" var="feature">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
                <cms:pageSlot position="CartTotalContentSlot" var="feature">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
            </div>
            <cms:pageSlot position="CheckoutContentSlot" var="feature">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>
    </div>
</template:page>