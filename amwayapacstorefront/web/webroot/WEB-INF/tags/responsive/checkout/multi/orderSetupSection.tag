<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="shipping-delivery-ship">
    <p class="shipping-delivery-shipping-header"><spring:theme code="checkout.step.one.order.setup"/></p>
    <p class="shipping-delivery-setup-title"><spring:theme code="checkout.step.one.exempt.sales.tax"/></p>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio3" id="radio31" value="radio31" checked="" />
        <label for="radio31">Yes</label>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio3" id="radio32" value="radio32" />
        <label for="radio32">No</label>
    </div>
    <p class="shipping-delivery-setup-title"><spring:theme code="checkout.step.one.freight.forwarding"/></p>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio4" id="radio311" value="radio311" />
        <label for="radio311">Yes</label>
    </div>
    <div class="panel">
        <input class="amwa-radio" type="radio" name="optradio4" id="radio322" value="radio322" checked="" />
        <label for="radio322">No</label>
    </div>
</div>