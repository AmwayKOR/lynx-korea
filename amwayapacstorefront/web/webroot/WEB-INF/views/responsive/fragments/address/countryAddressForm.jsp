<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty country}">
	<c:url value="/my-account/edit-address/edit" var="shippingAddressEditUrl"/>
	<form:form id="shipping-address-form-${fn:escapeXml(addressData.id)}" action="${shippingAddressEditUrl}" commandName="addressForm" method="post">
		<fieldset>
			<address:addressFormElements regions="${regions}"
		                             country="${country}"/>
		
			
			<form:hidden path="countryIso"/>
			<form:hidden path="defaultAddress"/>
			<form:hidden path="titleCode"/>
			<form:hidden path="addressId"/>
			
			<!-- Set this to pass the validation temporarily 
			<input hidden="true" type="text" name="titleCode" value="asd"/>
			-->
			      
		    <button class="btn-save btn btn-primary" type="button" 
		    		onclick="ACC.billingshipping.submitFormForEditAddress(this);" 
		    		form="shipping-address-form-${fn:escapeXml(addressData.id)}"
		    		data-address-id="${fn:escapeXml(addressData.id)}">SAVE</button>
		    <div class="button-right-check">
		        <input class="" id="edit12" name="edit12" type="checkbox">
		        <label class="" for="edit12">Make Primary Shipping Address
		        </label>
		    </div>
		</fieldset>
   	</form:form>
</c:if>