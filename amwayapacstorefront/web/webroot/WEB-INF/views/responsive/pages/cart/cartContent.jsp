<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<common:globalMessages />

<div id="cartConotentDiv">
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