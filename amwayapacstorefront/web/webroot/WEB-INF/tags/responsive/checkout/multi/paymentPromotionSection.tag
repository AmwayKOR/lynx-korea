<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="shipping-delivery-ship">
	<p class="payment-forms-promotion-header shipping-delivery-shipping-header mb1"><spring:theme code="checkout.payment.promotion.apply"/></p>
	<p class="payment-forms-header-sub"><spring:theme code="checkout.payment.promotion.apply.below"/></p>
	<form class="form-inline">
		<input type="text" class="form-control" name="promotionInput" value="Winter2016" />
		<button class="payment-forms-apply btn-blue-white" href="#"><spring:theme code="checkout.payment.promotion.apply.button"/></button>
	</form>
	<p class="payment-forms-apply-message">This promotion has been applied to your order.</p>
</div>