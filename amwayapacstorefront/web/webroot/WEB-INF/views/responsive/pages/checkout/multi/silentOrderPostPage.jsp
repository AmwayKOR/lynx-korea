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

<c:url var="checkoutOrderPreviewStepUrl" value="/checkout/multi/summary/view" />
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
				</div>
			</div>
			<multi-checkout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}" />
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-md-9 cart-items-wrapper">
						<multi-checkout:paymentOptionsSection />
						<multi-checkout:paymentAssignVolumeSection />
						<multi-checkout:paymentPromotionSection />
					</div>
					<div class="col-xs-12 col-md-3 shipping-delivery-summary">
						<div class="shipping-delivery-side">
							<multi-checkout:checkoutOrderDetails orderData="${cartData}"/>
							<div class="shipping-delivery-summary-link">
								<a href="${checkoutOrderPreviewStepUrl}"
									class="shipping-delivery-summary-continue btn-blue-white checkout-payment-step">
									<spring:theme code="checkout.payment.continue.to.preview" />
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<multi-checkout:deliveryCartItems orderData="${cartData}"/>
		<div class="amwa-btnbar">
			<a href="${checkoutOrderPreviewStepUrl}" class="btn-blue-white checkout-payment-step">
				<spring:theme code="checkout.payment.continue.to.preview" />
			</a>
			<a class="cartlist-cancelorder" href="${cartUrl}">
				<spring:theme code="checkout.step.one.cancel.order" />
			</a>
		</div>
	</div>
</template:checkoutPage>
