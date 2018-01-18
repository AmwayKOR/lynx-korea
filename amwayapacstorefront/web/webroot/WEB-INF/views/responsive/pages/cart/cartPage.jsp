<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="${continueUrl}" var="continueShoppingUrl" scope="session"/>

<template:page pageTitle="${pageTitle}">
    <div class="container-fluid main-container add-to-cart-entry-pagination">
        <div class="row border cartTitile">
            <h1 class="product-list-page-title shopping-list-page-title mb25"><spring:theme code="basket.page.heading" /></h1>
            <h4 class="product-list-page-sub-title"><spring:theme code="basket.page.welcomeMessage" /></h4></div>
        <div class="row cart-content-wrapper">
            <cms:pageSlot position="QuickShopSlot" var="feature">
                <cms:component component="${feature}" />
            </cms:pageSlot>
            <div id="cartContent">
                <div class="cart-top-totals"><spring:theme code="basket.page.totals.total.items" arguments="${fn:length(cartData.entries)}"/></div>
                <c:if test="${fn:length(cartData.entries) ne 0}">
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
                </c:if>
            </div>
        </div>
    </div>
</template:page>