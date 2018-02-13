<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData"%>

<c:url value="${entry.product.url}" var="entryProductUrl" />
<li class="mini-cart-item">
	<div class="row product-wrapper">
		<div class="product-item-element list-item-image col-xs-3">
			<div class="thumb">
				<a href="${entryProductUrl}">
					<product:productPrimaryImage product="${entry.product}" format="cartIcon" />
				</a>
			</div>
		</div>
		<div class="product-details col-xs-9">
			<div class="name-wrapper">
				<a class="name" href="${entryProductUrl}">${fn:escapeXml(entry.product.name)}</a>
			</div>
			<div class="product-item-element list-item-ibo-price">
				<div>
					<div class="first-price">
						<span class="label-wrapper">
							<spring:theme code="mini.cart.price.base" />
						</span>
						<span class="value-wrapper">${entry.product.price.formattedValue}</span>
						<div class="retail-price">
							<s>
								<span class="retail-price-strike-out">${entry.product.retailPrice.formattedValue}</span>
							</s>
						</div>
					</div>
					<div>
						<span class="label-wrapper">
							<spring:theme code="mini.cart.price.retail" />
						</span>
						<span class="value-wrapper">${entry.product.retailPrice.formattedValue}</span>
					</div>
				</div>
			</div>
			<div class="product-item-element list-item-pv-bv">
				<span class="total-pv-bv-label">
					<spring:theme code="mini.cart.item.pvbv" />
				</span>
				<span class="value-wrapper">${entry.product.price.amwayValue.pointValue} /
					${entry.product.price.amwayValue.businessVolume}</span>
			</div>
			<div class="qty">
				<span class="qty-label">
					<spring:theme code="mini.cart.item.quantity" />
				</span>
				<span class="qty-value">${entry.quantity}</span>
			</div>
		</div>
	</div>
</li>