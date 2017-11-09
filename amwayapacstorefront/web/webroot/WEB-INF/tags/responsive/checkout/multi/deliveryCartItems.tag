<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>

<div class="cartlist">
	<span class="cartlist-header cartlist-detail-mob " data-toggle="collapse" href="#cartlistContent" data-parent="#accordion"> ORDER DETAILS </span>
	<div id="cartlistContent" class="cartlist-content panel-collapse collapse in">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="cartlist-th cartlist-detail-desktop">
						<div>
							<div class="col-xs-6">PRODUCT DETAILS</div>
							<div class="cartlist-detail-desktop col-xs-1">QTY</div>
							<div class="cartlist-detail-desktop col-xs-1">SERVICE FEE</div>
							<div class="cartlist-detail-desktop col-xs-2">TOTAL PRICE</div>
							<div class="cartlist-detail-desktop col-xs-2">TOTAL PB/BV</div>
						</div>
					</div>
					<div class="cartlist-tbody container-fluid">
						<c:forEach items="${cartData.entries}" var="entry">
							<multi-checkout:checkoutIndividualEntrySection entry="${entry}" />
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>