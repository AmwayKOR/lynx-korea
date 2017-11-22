<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="shipping-delivery-summary-header">
	<span>
		<spring:theme code="checkout.step.one.order.summary" />
	</span>
</div>
<div class="shipping-delivery-summary-prices">
	<ul>
		<li class="shipping-delivery-list-item">
			<span class="shipping-delivery-item-label">
				<spring:theme code="checkout.step.one.order.summary.subtotal" />
			</span>
			<span class="performance-box-item-abovalue">${cartData.subTotal.formattedValue}</span>
		</li>
		<li class="shipping-delivery-list-item">
			<span class="shipping-delivery-item-label">
				<spring:theme code="checkout.step.one.order.summary.shipping" />
			</span>
			<span class="performance-box-item-abovalue">${cartData.deliveryCost.formattedValue}</span>
		</li>
		<li class="shipping-delivery-list-item">
			<span class="shipping-delivery-item-label">Tax One</span>
			<span class="performance-box-item-abovalue">$0.00</span>
		</li>
		<li class="shipping-delivery-list-item">
			<span class="shipping-delivery-item-label">Tax Two</span>
			<span class="performance-box-item-abovalue">$0.00</span>
		</li>
		<li class="shipping-delivery-list-item">
			<span class="shipping-delivery-item-label">Service Fee(s)</span>
			<span class="performance-box-item-abovalue">$0.00</span>
		</li>
		<li class="shipping-delivery-list-item-total">
			<span class="shipping-delivery-item-total">
				<spring:theme code="checkout.step.one.order.summary.total" />
			</span>
			<span class="shipping-delivery-item-totalvalue">${cartData.totalPrice.formattedValue}</span>
		</li>
		<li class="shipping-delivery-list-item-pvbv">
			<span class="shipping-delivery-item-total">
				<spring:theme code="checkout.step.one.order.summary.totalpvbv" />
			</span>
			<span class="shipping-delivery-item-totalvalue">${cartData.totalPrice.amwayValue.pointValue} /
				${cartData.totalPrice.amwayValue.businessVolume}</span>
		</li>
	</ul>
</div>

