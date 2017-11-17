<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
	<div class="col-xs-6 extraclass-mobile-width">
		<div class="cartlist-image">
			<img src="${themeResourcePath}/images/heart-health_checkout.png" width="67px" height="67px" />
		</div>
		<div class="cartlist-text">
			<span calss="cartlist-big">${entry.product.name}
				<span>
					<br />
					<c:forEach items="${entry.product.baseOptions[0].selected.variantOptionQualifiers}" var="variantOptionQualifier">
						<span class="cartlist-small1">${variantOptionQualifier.name} : ${variantOptionQualifier.value} </span>
						<br />
					</c:forEach>
					<span class="cartlist-small2">Item # ${entry.product.code} </span>
					<br />
					<span class="cartlist-small3">${entry.basePrice.amwayValue.pointValue} PV / ${entry.basePrice.amwayValue.businessVolume} BV </span>
					<br />
					<span cladss="cartlist-instock">
						<product:productStockStatusDisplay product="${entry.product}"/>
					</span>
					<div class="cartlist-detail-mob">
						<div class="cartlist-mbweight">
							<span class="cartlist-mbleft"><spring:theme code="checkout.product.list.quantity"/></span>
							<span class="cartlist-mbright">${entry.quantity}</span>
						</div>
						<div class="cartlist-mbweight">
							<span class="cartlist-mbleft"><spring:theme code="checkout.product.list.service.fee"/></span>
							<span class="cartlist-mbright">$0.00</span>
						</div>
						<div class="cartlist-mbweight">
							<span class="cartlist-mbleft"><spring:theme code="checkout.product.list.total.price"/></span>
							<span class="cartlist-mbright">${entry.totalPrice.formattedValue}</span>
						</div>
						<div class="cartlist-mbweight">
							<span class="cartlist-mbleft"><spring:theme code="checkout.product.list.total.pvbv"/></span>
							<span class="cartlist-mbright">${entry.value.pointValue} / ${entry.value.businessVolume}</span>
						</div>
					</div>
				</span>
			</span>
		</div>
	</div>
	<div class="cartlist-detail-desktop col-xs-1">
		<p class="cartlist-td">${entry.quantity}</p>
	</div>
	<div class="cartlist-detail-desktop col-xs-1">
		<p class="cartlist-td">$0.00</p>
	</div>
	<div class="cartlist-detail-desktop col-xs-2">
		<p class="cartlist-td">${entry.totalPrice.formattedValue}</p>
	</div>
	<div class="cartlist-detail-desktop col-xs-2">
		<p class="cartlist-td">${entry.value.pointValue}/${entry.value.businessVolume}</p>
	</div>
</div>