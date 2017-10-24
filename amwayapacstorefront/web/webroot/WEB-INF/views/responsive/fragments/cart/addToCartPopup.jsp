<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

{"cartData": {
"total": "${cartData.totalPrice.value}",
"products": [
<c:forEach items="${cartData.entries}" var="cartEntry" varStatus="status">
	{
		"sku":		"${fn:escapeXml(cartEntry.product.code)}",
		"name": 	"<c:out value='${cartEntry.product.name}' />",
		"qty": 		"${cartEntry.quantity}",
		"price": 	"${cartEntry.basePrice.value}",
		"categories": [
		<c:forEach items="${cartEntry.product.categories}" var="category" varStatus="categoryStatus">
			"<c:out value='${category.name}' />"<c:if test="${not categoryStatus.last}">,</c:if>
		</c:forEach>
		]
	}<c:if test="${not status.last}">,</c:if>
</c:forEach>
]
},

"quickOrderErrorData": [
<c:forEach items="${quickOrderErrorData}" var="quickOrderEntry" varStatus="status">
	{
		"sku":		"${fn:escapeXml(quickOrderEntry.productData.code)}",
		"errorMsg": "<spring:theme code='${quickOrderEntry.errorMsg}' htmlEscape="true"/>"
	}<c:if test="${not status.last}">,</c:if>
</c:forEach>
],

"cartAnalyticsData":{"cartCode" : "${cartCode}","productPostPrice":"${entry.basePrice.value}","productName":"<c:out value='${product.name}' />"}
,
"addToCartLayer":"<spring:escapeBody javaScriptEscape="true" htmlEscape="false">
	<spring:htmlEscape defaultHtmlEscape="true">
	<spring:theme code="text.addToCart" var="addToCartText"/>
	<c:url value="/cart" var="cartUrl"/>
	<div class="cart-popup__content">
         <cart:popupCartItems entry="${entry}" product="${product}" quantity="${quantity}"/>
         <div class="cart-popup__item-link">
             <a href="${cartUrl}" class="btn-blue-white"><spring:theme code="checkout.checkout" /></a>
             <a class="cart-popup__item-link-text js-mini-cart-close-button" href=""><spring:theme code="cart.page.continue" /></a></div>
         </div>
    </div>
	</spring:htmlEscape>
</spring:escapeBody>"
}