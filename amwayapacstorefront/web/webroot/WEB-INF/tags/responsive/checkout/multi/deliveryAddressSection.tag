<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:set var="cartAddress" value="${cartData.deliveryAddress}"/>
<c:choose>
	<c:when test="${(openCreateAddressForm) || (empty deliveryAddresses)}">
		<c:set var="divToOpen" value="addressFormDiv"/>
	</c:when>
	<c:when test="${(null eq cartAddress) && (not empty deliveryAddresses)}">
		<c:set var="divToOpen" value="alternateAddressDiv"/>
	</c:when>
	<c:otherwise>
		<c:set var="divToOpen" value="fileAddressDiv"/>
	</c:otherwise>
</c:choose>

<div id="shippingdiv" class="shipping-delivery-ship">
	<div class="display-none" id="divToOpen" data-open="${divToOpen}"></div>
    <p class="shipping-delivery-shipping-header">this order will ship to</p>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio1" value="radio1" />
        <label for="radio1" data-toggle="collapse" href="#collapseOne" data-parent="#shippingdiv" id="fileAddressDiv">Address on File</label>
        <div id="collapseOne" class="shipping-delivery-radio-body panel-collapse collapse">
           <p class="shipping-delivery-address-name">${cartAddress.firstName} ${cartAddress.lastName}</p>
           <p class="shipping-delivery-address-detail"> ${cartAddress.postalCode}
           <br/>${cartAddress.region.name}-${cartAddress.region.isocode}
           <c:if test="${not empty cartAddress.line1}"><br/>${cartAddress.line1}</c:if>
           <c:if test="${not empty cartAddress.line2}"><br/>${cartAddress.line2}</c:if>
           <c:if test="${not empty cartAddress.phone}"><br/>${cartAddress.phone}</c:if>
           <c:if test="${not empty cartAddress.email}"><br/>${cartAddress.email}</c:if>
           </p>
            <a href="#" class="shipping-delivery-address-edit"> Edit Address </a>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio2" />
        <label for="radio2" class="collapsed" data-toggle="collapse" href="#collapseTwo" data-parent="#shippingdiv" id="addressFormDiv">Enter New Address</label>
        <div id="collapseTwo" class="shipping-delivery-radio-body panel-collapse collapse">
        	<c:url value="/checkout/multi/delivery-address/add" var="deliveryAddressCreationUrl"/>
        	<form:form id="delivery-address-form" action="${deliveryAddressCreationUrl}" commandName="addressForm" method="post">
        		<fieldset>
	        		<formElement:formInputBox labelKey="titleCode" idKey="titleCode_id" path="titleCode" mandatory="true" placeholder="mr"/>
	        		<formElement:formInputBox labelKey="firstName" idKey="firstName_id" path="firstName"/>
	        		<formElement:formInputBox labelKey="lastName" idKey="lastName_id" path="lastName"/>
	        		<formElement:formInputBox labelKey="line1" idKey="line1_id" path="line1"/>
	        		<formElement:formInputBox labelKey="line2" idKey="line2_id" path="line2"/>
	        		<formElement:formInputBox labelKey="townCity" idKey="townCity_id" path="townCity"/>
	        		<formElement:formInputBox labelKey="phone" idKey="phone_id" path="phone"/>
	        		<formElement:formInputBox labelKey="postcode" idKey="postcode_id" path="postcode"/>
	        		<input hidden="true" type="checkbox" checked="checked" name="saveInAddressBook"/>
	        		
	        		<div class="shipping-delivery-options-container">
	        			<formElement:formCheckbox idKey="defaultAddress" labelKey="defaultAddress_id" path="defaultAddress" inputCSS="login-form-remember"/>
                        <button class="shipping-delivery-save btn-blue-white" type="submit" form="delivery-address-form">Save</button>
                    </div>
        		</fieldset>
        	</form:form>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio3" />
        <label for="radio3" class="collapsed" data-toggle="collapse" href="#collapseThree" data-parent="#shippingdiv" id="alternateAddressDiv">Use Alternate Address on File</label>
        <div id="collapseThree" class="shipping-delivery-radio-body panel-collapse collapse">
            <div class="panel-body">
                <c:forEach var="deliveryAddress" items="${deliveryAddresses}">
                	<c:if test="${cartAddress.id ne deliveryAddress.id}">
				        <div class="shipping-delivery-alternate">
				            <p class="shipping-delivery-address-name">${deliveryAddress.firstName} ${deliveryAddress.lastName}</p>
				            <p class="shipping-delivery-address-detail"> ${deliveryAddress.postalCode}
				            <br/>${deliveryAddress.region.name}-${deliveryAddress.region.isocode}
				            <c:if test="${not empty deliveryAddress.line1}"><br/>${deliveryAddress.line1}</c:if>
				            <c:if test="${not empty deliveryAddress.line2}"><br/>${deliveryAddress.line2}</c:if>
				            <c:if test="${not empty deliveryAddress.phone}"><br/>${deliveryAddress.phone}</c:if>
				            <c:if test="${not empty deliveryAddress.email}"><br/>${deliveryAddress.email}</c:if>
				            </p>
				            <a href="#" class="shipping-delivery-address-edit"> Edit Address </a>
				            <br/>
				            <c:url value="/checkout/multi/delivery-address/select" var="deliveryAddressSelectionUrl"/>
				            <form action="${deliveryAddressSelectionUrl}" method="get">
				            	<input type="hidden" name="selectedAddressCode" value="${deliveryAddress.id}" />
				            	<button class="shipping-delivery-use btn-blue-white">Use This Address</button>
				            </form>
				            <br/><br/>
				        </div>
			        </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio4" />
        <label for="radio4" class="collapsed" data-toggle="collapse" href="#collapseFour" data-parent="#shippingdiv">Select an IBO / Customer</label>
        <div id="collapseFour" class="shipping-delivery-radio-body panel-collapse collapse">
            <div class="panel-body">
                IBO / Customer
            </div>
        </div>
    </div>
</div>