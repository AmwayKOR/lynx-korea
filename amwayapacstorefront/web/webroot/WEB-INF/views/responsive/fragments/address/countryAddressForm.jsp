<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty country}">
	<c:url value="/my-account/edit-address/edit/" var="shippingAddressEditUrl"/>
	
	<form:form id="shipping-address-form-${fn:escapeXml(addressData.id)}" action="${shippingAddressEditUrl}" commandName="addressForm" method="post">
		<fieldset>
			<address:addressFormElements regions="${regions}"
		                             country="${country}"/>
			
			<form:hidden path="countryIso"/>
			<form:hidden path="titleCode"/>
			<form:hidden path="addressId"/>
			
			<!-- Set this to pass the validation temporarily 
			<input hidden="true" type="text" name="titleCode" value="asd"/>
			-->
			      
		    <button class="btn-save btn btn-primary" type="button" 
		    		onclick="ACC.billingshipping.submitFormForEditAddress(this);" 
		    		form="shipping-address-form-${fn:escapeXml(addressData.id)}"
		    		data-address-id="${fn:escapeXml(addressData.id)}"
		    		data-address-default="${addressData.defaultAddress}">SAVE</button>
		    	
		    		<c:if test="${not addressData.defaultAddress}">
		    			<div class="button-right-check billingShipping">
				        <form:checkbox id="address.defaultAddress-${fn:escapeXml(addressData.id)}" path="defaultAddress" />
	                    <label for="address.defaultAddress-${fn:escapeXml(addressData.id)}"> 
	                        <spring:theme code="address.defaultAddress"/>
	                    </label>
			    		</div>
		    		</c:if>
		    	
		    
		</fieldset>
   	</form:form>
</c:if>