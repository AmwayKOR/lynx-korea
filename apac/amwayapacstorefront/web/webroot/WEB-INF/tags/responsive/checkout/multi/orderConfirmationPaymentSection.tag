<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header"><spring:theme code="order.confirmation.payment.title"/></p>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.payment.method"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">${orderData.paymentInfo.cardTypeData.name} ${orderData.paymentInfo.cardNumber}</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.volume.month"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">November</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.pvbv.recipient"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">1234567 - Yui Mori</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.applied.promotion"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">
				PromoCode123 <br>Winter2016
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.charitable.donations"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">$5.00 Donated to The American Red Cross</p>
		</div>
	</div>
</div>
