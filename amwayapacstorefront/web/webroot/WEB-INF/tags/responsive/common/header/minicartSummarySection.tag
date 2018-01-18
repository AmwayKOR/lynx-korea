<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="summary-block">
	<span>
		<spring:theme code="mini.cart.item.summary.prefix" />
		<span class="bold">
			${numberItemsInCart}
			<spring:theme code="mini.cart.item.summary.item" />
		</span>
		<spring:theme code="mini.cart.item.summary.suffix" />
	</span>
	<div class="legend">
		<spring:theme code="mini.cart.item.show" arguments="${numberShowing},${numberItemsInCart}" />
			${numberShowing}
		<spring:theme code="mini.cart.item.summary.item" />
		<c:if test="${numberItemsInCart > numberShowing}">
			<a href="${cartUrl}">
				<spring:theme code="mini.cart.item.show.all" />
			</a>
		</c:if>
	</div>
</div>
<div class="mini-cart-totals">
	<div class="key">
		<spring:theme code="mini.cart.price.total" />
	</div>
	<div class="value">${cartData.totalPrice.formattedValue}</div>
	<div class="key">
		<spring:theme code="mini.cart.pvbv.total" />
	</div>
	<div class="value">${cartData.totalPrice.amwayValue.pointValue}/${cartData.totalPrice.amwayValue.businessVolume}</div>
</div>