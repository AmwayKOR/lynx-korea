<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="address" required="false" type="java.lang.Object"%>
<%@ attribute name="deliveryAddress" required="false" type="java.lang.Object"%>
<%@ attribute name="url" required="false" type="java.lang.String"%>

<c:set var="addressID" scope="request" value="${fn:escapeXml(address.id)}"/>
<c:set var="countryISO" scope="request" value="${fn:escapeXml(address.country.isocode)}"/>
<div class="shipping-delivery-display-detail ${address ne null ? '' : 'hidden'}">
	<p class="shipping-delivery-address-detail">
		<c:if test="${not empty address.line1}"><br/>${fn:escapeXml(address.line1)}</c:if>
		<c:if test="${not empty address.line2}"><br/>${fn:escapeXml(address.line2)}</c:if>
		<c:if test="${not empty address.line3}"><br/>${fn:escapeXml(address.line3)}</c:if>
		<c:if test="${not empty address.town}"><br/>${fn:escapeXml(address.town)}</c:if>
		<c:if test="${not empty address.postalCode}"><br/>${fn:escapeXml(address.postalCode)}</c:if>
	</p>
	<label class="shipping-delivery-address-edit address-form-edit-label ${address ne null ? '' : 'hidden'}"><spring:theme code="checkout.step.one.delivery.address.edit"/></label>
	<br/>
	<div class="row">
	<c:if test="${address ne null and address.id ne deliveryAddress.id}">
		<div class="col-md-3">
			<c:url value="/checkout/multi/delivery-address/select-address" var="deliveryAddressSelectionUrl"/>
			<form id="delivery-address-form-use-${address.id}" action="${deliveryAddressSelectionUrl}" method="post" class="pull-left  form-inline">
				<input type="hidden" name="selectedAddressCode" value="${address.id}" />
				<input type="hidden" name="CSRFToken" value="${CSRFToken.token}" />
				<button class="shipping-delivery-use btn-blue-white" type="submit" form="delivery-address-form-use-${address.id}"><spring:theme code="checkout.step.one.delivery.address.use.this.address"/></button>
			</form>
		</div>
	</c:if>
	<c:if test="${removeAddressEnabled and address.id ne deliveryAddress.id}">
		<div class="col">
			<c:url value="/checkout/multi/delivery-address/remove" var="deliveryAddressDeleteUrl"/>
			<form id="delivery-address-form-delete-${address.id}" action="${deliveryAddressDeleteUrl}" method="post">
				<input type="hidden" name="addressCode" value="${address.id}" />
				<input type="hidden" name="CSRFToken" value="${CSRFToken.token}" />
				<button class="shipping-delivery-remove btn-blue-white ${not empty address.id ? 'delete' : ''}" type="submit" form="delivery-address-form-delete-${address.id}"><spring:theme code="checkout.step.one.delivery.address.delete"/></button>
			</form>
		</div>
	</c:if>
	</div>
</div>
<div class="shipping-delivery-edit-detail ${address ne null ? 'hidden' : ''}">
<form:form id="delivery-address-form-${address.id}" action="${url}" commandName="addressForm" method="post">
	<fieldset>
		<form:hidden path="addressId" value="${address.id}"/>
		<form:hidden path="countryIso" value="${address.country.isocode}"/>
		<br/>
		<div class="form-group">
			<form:label path="line1"><spring:theme code="checkout.step.one.delivery.address.line1"/></form:label>
			<form:input name="line1" path="line1" value="${address.line1}"/>
		</div>
		<div class="form-group">
			<form:label path="line2"><spring:theme code="checkout.step.one.delivery.address.line2"/></form:label>
			<form:input name="line2" path="line2" value="${address.line2}"/>
		</div>
		<div class="form-group">
			<form:label path="line3"><spring:theme code="checkout.step.one.delivery.address.line3"/></form:label>
			<form:input name="line3" path="line3" value="${address.line3}"/>
		</div>
		<div class="form-group">
			<form:label path="townCity"><spring:theme code="checkout.step.one.delivery.address.townCity"/></form:label>
			<form:input name="townCity" path="townCity" value="${address.town}"/>
		</div>
		<div class="form-group">
			<form:label path="postcode"><spring:theme code="checkout.step.one.delivery.address.postcode"/></form:label>
			<form:input name="postcode" path="postcode" value="${address.postalCode}"/>
		</div>
		
		<input hidden="true" type="checkbox" checked="checked" name="saveInAddressBook" path="saveInAddressBook"/>
		
		<div class="shipping-delivery-options-container">
			<c:if test="${address.id ne deliveryAddress.id}">
				<input class="login-form-remember" id="defaultAddress-${address.id}" ${address eq null ? 'checked' : ''} name="defaultAddress" type="checkbox" path="defaultAddress" />
				<label for="defaultAddress-${address.id}"><spring:theme code="checkout.step.one.delivery.address.default"/></label>
			</c:if>
			<button class="shipping-delivery-save btn-blue-white ${not empty address.id ? 'update' : 'save'}" type="submit" form="delivery-address-form-${address.id}"><spring:theme code="checkout.step.one.delivery.address.update"/></button>
		</div>
		
	</fieldset>
</form:form>
</div>


