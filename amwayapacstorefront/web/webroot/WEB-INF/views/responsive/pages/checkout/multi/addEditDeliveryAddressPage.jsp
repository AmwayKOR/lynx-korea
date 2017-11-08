<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<template:checkoutPage>

<div class="container-fluid main-container">
    <div class="shipping-delivery">
        <div class="container-fluid">
          <div class="row cartTitile">
              <div class="product-list-page-title mb25">
                  CHECKOUT
              </div>
          </div>
        </div>
       	<multi-checkout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}"/>
        <div class="container-fluid">
        	<div class="row">
          		<div class="col-xs-12 col-md-9 cart-items-wrapper">
		        	<multi-checkout:deliveryAddressSection/>
		        	<multi-checkout:deliveryMethodSelector/>
		        	<multi-checkout:orderSetupSection/>
    			</div>
    			<multi-checkout:checkoutOrderDetails/>
    		</div>
   		</div>
    </div>
    <multi-checkout:deliveryCartItems/>
    <div class="amwa-btnbar">
        <a href="Checkout-2-payment.html" class="btn-blue-white" onclick="">continue to payment</a>
        <a class="cartlist-cancelorder">Cancel Order</a>
    </div>
</div>
</template:checkoutPage>
