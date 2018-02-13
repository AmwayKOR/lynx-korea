<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="deliveyPaymentStep" value="/checkout/multi/payment-method/add" />
<div class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header"><spring:theme code="order.preview.payment.title"/></p>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.payment.method"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">${cartData.paymentInfo.cardTypeData.name} ${cartData.paymentInfo.cardNumber}</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.volume.month"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">November</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.pvbv.recipient"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">1234567 - Yui Mori</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.applied.promotion"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">
				PromoCode123 <br>Winter2016
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.charitable.donations"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">$5.00 Donated to The American Red Cross</p>
		</div>
	</div>
	<a name="savebutton" href="${deliveyPaymentStep}" class="shipping-delivery-save btn-blue-white"><spring:theme code="order.preview.payment.edit"/></a>
</div>