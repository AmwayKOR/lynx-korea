<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="${url}" var="addToCartUrl"/>
<c:url value="/cart" var="cartUrl"/>

<spring:url value="${product.url}/configuratorPage/{/configuratorType}" var="configureProductUrl" htmlEscape="false">
    <spring:param name="configuratorType"  value="${configuratorType}"/>
</spring:url>


<form:form method="post" id="addToCartForm" class="add_to_cart_form" action="${addToCartUrl}">
	<input type="hidden" maxlength="3" size="1" id="qty" name="qty" class="qty js-qty-selector-input" value="1">
    <input type="hidden" name="productCodePost" value="${fn:escapeXml(product.code)}"/>
    <input type="hidden" id="cartUrl" value="${cartUrl}"/>

	<button type="submit" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" disabled="disabled"><spring:theme code="basket.add.to.basket"/></button>
	<button type="button" id="BuyNow" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6"><spring:theme code="checkout.checkout"/></button>

</form:form>

