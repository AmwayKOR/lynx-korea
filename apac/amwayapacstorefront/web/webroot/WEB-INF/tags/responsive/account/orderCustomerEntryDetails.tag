<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData"%>
<%@ attribute name="orderEntry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData"%>
<%@ attribute name="itemIndex" required="true" type="java.lang.Integer"%>
<%@ attribute name="entryQuantity" required="false" type="java.lang.Integer"%>
<%@ attribute name="itemView" required="false" type="java.lang.Boolean"%>
<%@ attribute name="unavailableView" required="false" type="java.lang.Boolean"%>
<%@ attribute name="inventoryRequestView" required="false" type="java.lang.Boolean"%>
<%@ attribute name="hideTotals" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/responsive/grid"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shopping" tagdir="/WEB-INF/tags/responsive/shopping" %>
<%@ taglib prefix="ditto" tagdir="/WEB-INF/tags/responsive/ditto" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value="${orderEntry.product.url}" var="productUrl" />
<c:url value="/my-account/order" var="orderUrl"/>

<li class="item-list-item row js-product-item-list js-automation-product">
	<div class="col-xs-12 col-md-5 order-item-element item-details">
		
		<!-- checkbox -->
		<div class="item-toggle product-cell order-item-element-child">
			<c:set var="buttonType">button</c:set>
			<c:set var="available">false</c:set>
			<c:if test="${orderEntry.product.purchasable}">
				<c:set var="available">true</c:set>
			</c:if>
			<c:if test="${orderEntry.product.purchasable and orderEntry.product.stock.stockLevelStatus.code ne 'outOfStock' }">
				<c:set var="buttonType">submit</c:set>
			</c:if>
			<c:choose>
				<c:when test="${fn:contains(buttonType, 'button') or available eq false}">
					<span class="amw-checkbox-wrap">
						<input type="checkbox" value="${orderEntry.product.code}"
                               class="js-product-entry-checkbox orderdetails-entry-checkbox js-order-product-checkbox display-none ${available ? 'available' : 'not-available'} amw-global-checkbox"/>
						<span class="amw-checkbox-overlay"></span>
						<input type="hidden" value="false" id="sellable_${orderEntry.product.code}"/>
					</span>
				</c:when>
				<c:otherwise>
					<ycommerce:testId code="addToCartButton">
						<span class="amw-checkbox-wrap">
							<input type="checkbox" value="${orderEntry.product.code}"
                                   class="js-product-entry-checkbox orderdetails-entry-checkbox js-order-product-checkbox display-none ${available ? 'available' : 'not-available'} amw-global-checkbox"/>
							<span class="amw-checkbox-overlay"></span>
							<input type="hidden" value="true" id="sellable_${orderEntry.product.code}"/>
						</span>
					</ycommerce:testId>
				</c:otherwise>
			</c:choose>
		</div>

		<%-- product image --%>
        <div class="image-wrapper">
            <div class="item-image order-item-element-child">
                <ycommerce:testId code="orderDetail_productThumbnail_link">
                    <a class="js-automation-product-image" href="${productUrl}"> <product:productPrimaryImage product="${orderEntry.product}" format="thumbnail" />
                    </a>
                </ycommerce:testId>
            </div>
        </div>

		<%-- product name, code --%>
		<div class="item-info order-item-element-child">
			<ycommerce:testId code="orderDetails_productName_link">
				<a href="${orderEntry.product.purchasable ? productUrl : ''}"><span class="item-name js-item-name js-automation-product-link">${fn:escapeXml(orderEntry.product.name)}</span></a>
			</ycommerce:testId>

			<div class="item-code">
				<ycommerce:testId code="orderDetails_productCode">
					<span class="js-automation-product-id-title"><spring:theme code="checkout.orderConfirmation.itemNo" /> </span>
					<span class="js-automation-product-id-value">${fn:escapeXml(orderEntry.product.code)}</span>
	            </ycommerce:testId>
			</div>

			<span class="value-wrapper">
                    <span class="js-automation-product-pv">
					    <c:out value="${fn:escapeXml(orderEntry.product.price.amwayValue.pointValue)} "/><spring:theme code="text.account.orderhistory.pointValue"/>
                    </span>
                    <span> / </span>
                    <span class="js-automation-product-bv">
                        <c:out value="${fn:escapeXml(orderEntry.product.price.amwayValue.businessVolume)} "/><spring:theme code="text.account.orderhistory.businessVolume"/>
                    </span>
				</span>

            

            

			<common:configurationInfos entry="${orderEntry}" />
		</div>
	</div>

	<%-- quantity --%>
	<div class="item-quantity col-md-1 order-item-element">
		<c:forEach items="${orderEntry.product.baseOptions}" var="option">
			<c:if test="${not empty option.selected and option.selected.url eq orderEntry.product.url}">
				<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
					<div>
						<ycommerce:testId code="orderDetail_variantOption_label">
							<span class="item-label js-automation-product-qty-title">${fn:escapeXml(selectedOption.name)}:</span>
							<span class="item-value js-automation-product-qty-value">${fn:escapeXml(selectedOption.value)}</span>
						</ycommerce:testId>
					</div>
					<c:set var="entryStock" value="${option.selected.stock.stockLevelStatus.code}" />
				</c:forEach>
			</c:if>
		</c:forEach>

		<ycommerce:testId code="orderDetails_productQuantity_label">
			<span class="visible-sm item-label"><spring:theme code="text.account.order.qty" />:</span>
			<span class="qtyValue item-value js-automation-product-qty-value">
                <input type="hidden" class="js-update-entry-quantity-input" value="${orderEntry.quantity}">
                <c:choose>
                    <c:when test="${itemView || entryQuantity eq null || entryQuantity eq orderEntry.quantity}">
                        ${fn:escapeXml(orderEntry.quantity)}
                    </c:when>
                    <c:otherwise>
                        <span class="js-automation-product-qty-value-1">${entryQuantity}</span>
                        &nbsp;<spring:theme code="text.account.order.qty.of"/>&nbsp;
                        <span class="js-automation-product-qty-value-2">${fn:escapeXml(orderEntry.quantity)}</span>
                    </c:otherwise>
                </c:choose>
			</span>
		</ycommerce:testId>
	</div>

	<%-- price --%>
	<div class="item-price col-md-2 col-xs-9 order-item-element">
		<span class="visible-sm price-label item-label"><spring:theme code="basket.page.itemPrice" />:</span>
		<span class="price-value item-value js-automation-product-price">
			<ycommerce:testId code="orderDetails_productItemPrice_label">
				<order:orderEntryPrice orderEntry="${orderEntry}" />
			</ycommerce:testId>
		</span>
	</div>

    <c:if test="${not hideTotals}">
        <%-- total --%>
        <div class="item-total col-md-2 col-xs-9 order-item-element">
            <span class="visible-sm item-label"><spring:theme code="text.account.order.total" />:</span>
            <span class="item-value js-automation-product-total-price">
                <ycommerce:testId code="orderDetails_productTotalPrice_label">
                    <c:choose>
                        <c:when test="${itemView || entryQuantity eq null || orderEntry.inventoryRequest eq null}">
                            <format:price priceData="${orderEntry.totalPrice}" displayFreeForZero="true" />
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${orderEntry.inventoryRequest.totalPrice.value > 0}">
                                    ${orderEntry.inventoryRequest.totalPrice.formattedValue}
                                </c:when>
                                <c:otherwise>
                                    <spring:theme code="text.free" text="FREE"/>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </ycommerce:testId>
            </span>
        </div>

        <%-- total tvbv --%>
            <div class="item-price col-md-2 col-xs-9 order-item-element">
                <span class="visible-sm price-label item-label"><spring:theme code="checkout.orderConfirmation.totalPVBV" />:</span>
                <span class="price-value item-value js-automation-product-total-pvbv">
                
                    <ycommerce:testId code="orderDetails_productTotalTvBv_label">
                        ${fn:escapeXml(orderEntry.value.pointValue)}/${fn:escapeXml(orderEntry.value.businessVolume)}
                            
                    </ycommerce:testId>
                </span>
            </div>
    </c:if>
	
</li>
