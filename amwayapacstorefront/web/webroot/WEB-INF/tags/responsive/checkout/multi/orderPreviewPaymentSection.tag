<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<div class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header">payment</p>
	<div class="order-preview-box">
		<label class="order-preview-label">payment method</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">${cartData.paymentInfo.cardTypeData.name} ${cartData.paymentInfo.cardNumber}</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label">volume month</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">November</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label">pv/bv recipient</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">1234567 - Yui Mori</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label">Applied promotions</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">
				PromoCode123 <br>Winter2016
			</p>
		</div>
	</div>
	<div class="order-preview-box">
		<label class="order-preview-label">charitable donations</label>
		<div class="order-preview-value">
			<p class="shipping-delivery-address-detail">$5.00 Donated to The American Red Cross</p>
		</div>
	</div>
	<a name="savebutton" href="#" class="shipping-delivery-save btn-blue-white">edit</a>
</div>