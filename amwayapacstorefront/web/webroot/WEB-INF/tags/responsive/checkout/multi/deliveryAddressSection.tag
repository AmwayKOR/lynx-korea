<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>

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
    <p class="shipping-delivery-shipping-header"><spring:theme code="checkout.step.one.delivery.address"/></p>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio1" value="radio1" />
        <label for="radio1" data-toggle="collapse" href="#collapseOne" data-parent="#shippingdiv" id="fileAddressDiv"><spring:theme code="checkout.step.one.delivery.address.on.file"/></label>
        <div id="collapseOne" class="shipping-delivery-radio-body collapse in">
			<c:url value="/checkout/multi/delivery-address/edit" var="deliveryAddressEditUrl"/>
			<div class="shipping-delivery-main-container">
				<c:choose>
				    <c:when test="${cartAddress ne null}">
						<checkout:deliveryAddressForm address="${cartAddress}" url="${deliveryAddressEditUrl}" deliveryAddress="${cartAddress}"/>
				    </c:when>    
				    <c:otherwise>
						<div class="shipping-delivery-display-detail">
							<p class="shipping-delivery-address-detail">
								<spring:theme code="checkout.step.one.delivery.address.empty"/>
							</p>
						</div>
				    </c:otherwise>
				</c:choose>
			</div>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio2" />
        <label for="radio2" class="collapsed" data-toggle="collapse" href="#collapseTwo" data-parent="#shippingdiv" id="addressFormDiv"><spring:theme code="checkout.step.one.delivery.address.enter.new"/></label>
        <div id="collapseTwo" class="shipping-delivery-radio-body panel-collapse collapse">
        	<c:url value="/checkout/multi/delivery-address/add" var="deliveryAddressCreationUrl"/>
       		<div class="shipping-delivery-add-container">
       			<checkout:deliveryAddressForm address="${null}" url="${deliveryAddressCreationUrl}" deliveryAddress="${cartAddress}"/>
       		</div>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio3" />
        <label for="radio3" class="collapsed" data-toggle="collapse" href="#collapseThree" data-parent="#shippingdiv" id="alternateAddressDiv"><spring:theme code="checkout.step.one.delivery.address.use.alternate"/></label>
        <div id="collapseThree" class="shipping-delivery-radio-body panel-collapse collapse">
            <div class="panel-body">
            	<div class="shipping-delivery-list-container">
            		<checkout:deliveryAddressList addressList="${deliveryAddresses}" deliveryAddress="${cartAddress}" />
            	</div>
            </div>
        </div>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio" id="radio4" />
        <label for="radio4" class="collapsed" data-toggle="collapse" href="#collapseFour" data-parent="#shippingdiv"><spring:theme code="checkout.step.one.delivery.address.select.customer"/></label>
        <div id="collapseFour" class="shipping-delivery-radio-body panel-collapse collapse">
            <div class="panel-body">
                IBO / Customer
            </div>
        </div>
    </div>
</div>