<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<spring:url value="/cart/voucher/apply" var="applyVoucherAction" htmlEscape="false"/>
<spring:url value="/cart/voucher/remove" var="removeVoucherAction" htmlEscape="false"/>

<c:set var="containerClass">
    <c:choose>
        <c:when test="${not empty errorMsg}">has-error</c:when>
        <c:when test="${not empty successMsg}">has-success</c:when>
        <c:otherwise></c:otherwise>
    </c:choose>
</c:set>
<label class="control-label cart-voucher__label" for="voucher-code">Apply promotion</label>
<form id="applyVoucherForm" action="" method="post">
    <input id="js-voucher-code-text" name="voucherCode" class="js-voucher-code cart-voucher__input form-control input-sm" placeholder="enter coupon code" value="" maxlength="100" type="text">
    <button type="button" id="js-voucher-apply-btn" class="btn-blue-white">Apply</button>
    <div>
        <input name="CSRFToken" value="d634d67c-ffc6-4970-9127-7202eae70870" type="hidden"></div>
</form>
<div class="voucher-description">Exact discounts, promotions, delivery charges, taxes, and other fees will be calculated after submitting this sales report.</div>