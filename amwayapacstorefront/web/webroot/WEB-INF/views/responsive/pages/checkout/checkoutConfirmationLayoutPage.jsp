<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>

<c:url var="continueShoppingUrl" value="/" />
<c:url var="cartUrl" value="/cart" />
<spring:htmlEscape defaultHtmlEscape="true" />
<template:checkoutPage>
	<div class="container-fluid main-container">
		<div class="shipping-delivery">
			<div class="container-fluid">
				<div class="row cartTitile">
					<div class="product-list-page-title mb25">
						<spring:theme code="checkout.multi.checkout" />
					</div>
					<div class="confirm-box">
						<p class="confirm-box-message">
							<spring:theme code="order.confirmation.thanks" />
						</p>
						<p class="payment-forms-header-sub">
							<spring:theme code="order.confirmation.order.number.is" />
							<span class="confirm-box-message-sub-bold">${orderData.code}</span>
							.
						</p>
						<a href="${continueShoppingUrl}" class="confirm-box-continue-shopping">
							<spring:theme code="order.confirmation.continue.shopping" />
						</a>
					</div>
				</div>
			</div>
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-md-9 cart-items-wrapper">
						<multi-checkout:orderConfirmationDeliverySection />
						<multi-checkout:orderConfirmationPaymentSection />
					</div>
					<div class="col-xs-12 col-md-3 shipping-delivery-summary">
						<div class="shipping-delivery-side">
							<multi-checkout:checkoutOrderDetails orderData="${orderData}" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<multi-checkout:deliveryCartItems orderData="${orderData}" />
		<div class="amwa-btnbar">
			<a href="#" class="btn-blue-white" onclick="">
				<spring:theme code="order.confirmation.print.button" />
			</a>
			<a href="#" class="btn-blue-white" onclick="">
				<spring:theme code="order.confirmation.email.button" />
			</a>
		</div>
	</div>
</template:checkoutPage>
