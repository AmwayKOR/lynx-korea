<div class="shipping-delivery-ship">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ tag language="java" pageEncoding="ISO-8859-1"%>
	<p class="shipping-delivery-shipping-header"><spring:theme code="checkout.payment.assign.volume"/></p>
	<p class="shipping-delivery-setup-title"><spring:theme code="checkout.payment.assign.volume.month"/></p>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio3" id="radio31" value="radio31" checked>
		<label for="radio31">October</label>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio3" id="radio32" value="radio32">
		<label for="radio32">November</label>
	</div>
	<p class="shipping-delivery-setup-title"><spring:theme code="checkout.payment.frieght.forwarding"/></p>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio4" id="radio311" value="radio311">
		<label for="radio311">Me</label>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio4" id="radio322" value="radio322" checked>
		<label for="radio322">Another IBO</label>
	</div>
</div>