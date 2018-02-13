<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:url value="/checkout/multi/delivery-address/delivery-mode-select" var="deliveryModeSelectionUrl"/>
<c:set value="${cartData.deliveryMode}" var="currentDeliveryMode"/>
<div class="shipping-delivery-ship">
    <p class="shipping-delivery-shipping-header"><spring:theme code="checkout.step.one.delivery.options"/></p>
    <form id="delivery-mode-selection-form" action="${deliveryModeSelectionUrl}" method="post">
	    <c:forEach var="deliveryMode" items="${deliveryMethods}">
			<input class="amwa-radio" type="radio" name="delivery_method" id="${deliveryMode.code}_radio21" value="${deliveryMode.code}" <c:if test="${currentDeliveryMode.code eq deliveryMode.code}">checked="checked"</c:if>/>
		    <label for="${deliveryMode.code}_radio21">${deliveryMode.name}</label>
		    <p class="shipping-delivery-radio-delivery">${deliveryMode.description} ${deliveryMode.deliveryCost.formattedValue}</p>
			<input type="hidden" name="CSRFToken" value="${CSRFToken.token}" />
		</c:forEach>
	</form>
    <input class="login-form-remember" id="includePrice" name="includePrice" type="checkbox"/>
    <label for="includePrice"><spring:theme code="checkout.step.one.include.prices"/></label>
</div>