<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="orderData" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="shipping-delivery-summary-header">
	<span>
		<spring:theme code="checkout.multi.order.summary" />
	</span>
</div>
<div class="shipping-delivery-summary-prices">
	<ul>
		<li class="shipping-delivery-list-item">
			<span class="shipping-delivery-item-label">
				<spring:theme code="checkout.multi.order.summary.subtotal" />
			</span>
			<span class="performance-box-item-abovalue">${orderData.subTotal.formattedValue}</span>
		</li>
		<li class="shipping-delivery-list-item">
			<span class="shipping-delivery-item-label">
				<spring:theme code="checkout.multi.order.summary.shipping" />
			</span>
			<span class="performance-box-item-abovalue">${orderData.deliveryCost.formattedValue}</span>
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
				<spring:theme code="checkout.multi.order.summary.total" />
			</span>
			<span class="shipping-delivery-item-totalvalue">${orderData.totalPrice.formattedValue}</span>
		</li>
		<li class="shipping-delivery-list-item-pvbv">
			<span class="shipping-delivery-item-total">
				<spring:theme code="checkout.multi.order.summary.totalpvbv" />
			</span>
			<span class="shipping-delivery-item-totalvalue">${orderData.totalPrice.amwayValue.pointValue} /
				${orderData.totalPrice.amwayValue.businessVolume}</span>
		</li>
	</ul>
</div>

