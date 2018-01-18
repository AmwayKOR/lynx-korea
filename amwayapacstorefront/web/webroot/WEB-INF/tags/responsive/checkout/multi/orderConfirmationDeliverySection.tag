<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="shippingdiv" class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header"><spring:theme code="order.confirmation.shipping.delivery.title"/></p>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.ship.to"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-name">${orderData.deliveryAddress.firstName} ${orderData.deliveryAddress.lastName}</p>
			<p class="shipping-delivery-address-detail">
				${orderData.deliveryAddress.postalCode}<br>${orderData.deliveryAddress.town}<br>${orderData.deliveryAddress.line1}<br>${orderData.deliveryAddress.line2}<br>${orderData.deliveryAddress.phone}
				<br>${orderData.deliveryAddress.email}<br>
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.delivery.options"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">
				${orderData.deliveryMode.name} <br>${orderData.deliveryMode.description}
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.prices.on.packing"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">Price Included</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label"><spring:theme code="order.confirmation.exempt.sale.tax"/></label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">No</p>
		</div>
	</div>
</div>
