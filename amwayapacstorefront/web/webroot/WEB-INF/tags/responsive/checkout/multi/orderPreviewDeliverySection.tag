<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<div id="shippingdiv" class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header">SHIPPING & DELIVERY</p>
	<div class="order-preview-box">
		<label class="order-preview-label">order Will ship to</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-name">${cartData.deliveryAddress.firstName} ${cartData.deliveryAddress.lastName}</p>
			<p class="shipping-delivery-address-detail">
				${cartData.deliveryAddress.postalCode}<br>${cartData.deliveryAddress.town}<br>${cartData.deliveryAddress.line1}<br>${cartData.deliveryAddress.line2}<br>${cartData.deliveryAddress.phone}
				<br>${cartData.deliveryAddress.email}<br>
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label">delivery options</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">
				${cartData.deliveryMode.name} <br>${cartData.deliveryMode.description}
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label">prices on packing list</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">Price Included</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label">Exempt from sales tax</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">No</p>
		</div>
	</div>
	<a name="savebutton" href="#" class="shipping-delivery-save btn-blue-white">edit</a>
</div>

