<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ attribute name="addressList" required="true" type="java.lang.Object"%>
<%@ attribute name="deliveryAddress" required="false" type="java.lang.Object"%>

<c:url value="/checkout/multi/delivery-address/edit" var="deliveryAddressEditUrl"/>
<c:forEach var="address" items="${addressList}">
	<div class="${deliveryAddress.id ne address.id ? 'shipping-delivery-alternate' : 'shipping-delivery-primary hidden'}">
		<div class="shipping-delivery-edit-container">
			<checkout:deliveryAddressForm address="${address}" url="${deliveryAddressEditUrl}" deliveryAddress="${deliveryAddress}" />
		</div>
	</div>
</c:forEach>
