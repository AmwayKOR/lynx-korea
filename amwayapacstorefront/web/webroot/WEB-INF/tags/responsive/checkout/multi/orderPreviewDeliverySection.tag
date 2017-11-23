<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="shippingdiv" class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header"><spring:theme code="order.preview.shipping.delivery.title"/></p>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.ship.to"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-name">${cartData.deliveryAddress.firstName} ${cartData.deliveryAddress.lastName}</p>
			<p class="shipping-delivery-address-detail">
				${cartData.deliveryAddress.postalCode}<br>${cartData.deliveryAddress.town}<br>${cartData.deliveryAddress.line1}<br>${cartData.deliveryAddress.line2}<br>${cartData.deliveryAddress.phone}
				<br>${cartData.deliveryAddress.email}<br>
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.delivery.options"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">
				${cartData.deliveryMode.name} <br>${cartData.deliveryMode.description}
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.prices.on.packing"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">Price Included</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.preview.exempt.sale.tax"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">No</p>
		</div>
	</div>
	<a name="savebutton" href="#" class="shipping-delivery-save btn-blue-white"><spring:theme code="order.preview.address.edit"/></a>
</div>
