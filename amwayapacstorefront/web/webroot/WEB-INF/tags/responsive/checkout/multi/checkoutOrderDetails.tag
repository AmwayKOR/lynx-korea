<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<div class="col-xs-12 col-md-3 shipping-delivery-summary">
	<div class="shipping-delivery-side">
		<div class="shipping-delivery-summary-header">
			<span>order summary</span>
		</div>
		<div class="shipping-delivery-summary-prices">
			<ul>
				<li class="shipping-delivery-list-item">
					<span class="shipping-delivery-item-label">Item(s) Subtotal</span>
					<span class="performance-box-item-abovalue">${cartData.subTotal.formattedValue}</span>
				</li>
				<li class="shipping-delivery-list-item">
					<span class="shipping-delivery-item-label">Shipping</span>
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
					<span class="shipping-delivery-item-total">TOTAL</span>
					<span class="shipping-delivery-item-totalvalue">${cartData.totalPrice.formattedValue}</span>
				</li>
				<li class="shipping-delivery-list-item-pvbv">
					<span class="shipping-delivery-item-total">TOTAL PV/ BV</span>
					<span class="shipping-delivery-item-totalvalue">${cartData.totalPrice.amwayValue.pointValue} / ${cartData.totalPrice.amwayValue.businessVolume}</span>
				</li>
			</ul>
		</div>
		<div class="shipping-delivery-summary-link">
			<a href="Checkout-2-payment.html" class="shipping-delivery-summary-continue btn-blue-white" data-toggle="modal" data-target="#cart-modal"
				onclick="javascript:location.href='Checkout-2-payment.html'">continue to payment</a>
		</div>
	</div>
</div>