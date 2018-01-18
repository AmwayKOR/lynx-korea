<%@ attribute name="orderData" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>

<div class="cartlist">
	<span class="cartlist-header cartlist-detail-mob " data-toggle="collapse" href="#cartlistContent" data-parent="#accordion"><spring:theme code="checkout.product.list.product.details"/></span>
	<div id="cartlistContent" class="cartlist-content panel-collapse collapse in">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="cartlist-th cartlist-detail-desktop">
						<div>
							<div class="col-xs-6"><spring:theme code="checkout.product.list.product.details"/></div>
							<div class="cartlist-detail-desktop col-xs-1"><spring:theme code="checkout.product.list.quantity"/></div>
							<div class="cartlist-detail-desktop col-xs-1"><spring:theme code="checkout.product.list.service.fee"/></div>
							<div class="cartlist-detail-desktop col-xs-2"><spring:theme code="checkout.product.list.total.price"/></div>
							<div class="cartlist-detail-desktop col-xs-2"><spring:theme code="checkout.product.list.total.pvbv"/></div>
						</div>
					</div>
					<div class="cartlist-tbody container-fluid">
						<c:forEach items="${orderData.entries}" var="entry">
							<multi-checkout:checkoutIndividualEntrySection entry="${entry}" />
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>