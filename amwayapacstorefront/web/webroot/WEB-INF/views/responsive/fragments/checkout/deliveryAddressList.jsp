<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<input type="hidden" id="response" value="${response}" />
<div class="messages-wrapper">
	<common:globalMessages/>
</div>
<div class="content-wrapper">
	<checkout:deliveryAddressList addressList="${deliveryAddresses}" deliveryAddress="${cartData.deliveryAddress}" />
</div>