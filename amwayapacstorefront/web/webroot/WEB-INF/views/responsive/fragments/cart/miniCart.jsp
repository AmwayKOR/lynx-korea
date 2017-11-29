<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<c:url value="/cart" var="cartUrl"/>
<div class="mini-cart js-mini-cart">
	<div class="mini-cart-body">
		<c:choose>
			<c:when test="${numberShowing > 0 && numberItemsInCart > 0}">
				<ol class="mini-cart-list">
					<c:forEach items="${entries}" var="entry" end="${numberShowing - 1}">
					<c:url value="${entry.product.url}" var="entryProductUrl"/>
					<li class="mini-cart-item">
						<div class="row product-wrapper">
							<div class="product-item-element list-item-image col-xs-3">
								<div class="thumb">
									<a href="${entryProductUrl}">
										<product:productPrimaryImage product="${entry.product}" format="cartIcon"/>
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
											<span class="label-wrapper"><spring:theme code="mini.cart.price.base"/></span>
											<span class="value-wrapper"><format:fromPrice priceData="${entry.product.price}"/></span>
											<div class="retail-price">
												<s>
													<span class="retail-price-strike-out"><format:price priceData="${entry.product.retailPrice}"/></span>
												</s>
											</div>
										</div>
										<div>
											<span class="label-wrapper"><spring:theme code="mini.cart.price.retail"/></span>
											<span class="value-wrapper"><format:price priceData="${entry.product.retailPrice}"/></span>
										</div>
									</div>
								</div>
								<div class="product-item-element list-item-pv-bv">
									<span class="total-pv-bv-label"><spring:theme code="mini.cart.item.pvbv"/></span>
									<span class="value-wrapper">${entry.product.price.amwayValue.pointValue} / ${entry.product.price.amwayValue.businessVolume}</span>
								</div>
								<div class="qty">
									<span class="qty-label"><spring:theme code="mini.cart.item.quantity"/></span>
									<span class="qty-value">${entry.quantity}</span>
								</div>
							</div>
						</div>
					</li>
					</c:forEach>
				</ol>
				<div class="banner__component banner"></div>
				<div class="summary-block">
					<span>
						<spring:theme code="mini.cart.item.summary.prefix"/><span class="bold">&nbsp;${numberItemsInCart}&nbsp;<spring:theme code="mini.cart.item.summary.item"/>&nbsp;</span><spring:theme code="mini.cart.item.summary.suffix"/>
					</span>
					<div class="legend">
						<spring:theme code="mini.cart.item.show" arguments="${numberShowing},${numberItemsInCart}"/>&nbsp;${numberShowing}&nbsp;<spring:theme code="mini.cart.item.summary.item"/>&nbsp;
						<c:if test="${numberItemsInCart > numberShowing}">
							<a href="${cartUrl}"><spring:theme code="mini.cart.item.show.all"/></a>
						</c:if>
					</div>
				</div>
				<div class="mini-cart-totals">
					<div class="key"><spring:theme code="mini.cart.price.total"/></div>
					<div class="value"><format:price priceData="${cartData.totalPrice}"/></div>
					<div class="key"><spring:theme code="mini.cart.pvbv.total"/></div>
					<div class="value">${cartData.totalPrice.amwayValue.pointValue} / ${cartData.totalPrice.amwayValue.businessVolume}</div>
				</div>
				<c:if test="${not empty lightboxBannerComponent && lightboxBannerComponent.visible}">
					<cms:component component="${lightboxBannerComponent}" evaluateRestriction="true"  />
				</c:if>
				<div class="links">
					<div>
						<div class="cartPopupButtons"></div>
					</div>
				</div>
				<a href="${cartUrl}" class="btn btn-primary btn-block mini-cart-checkout-button view-cart-button"><spring:theme code="mini.cart.button.view.cart"/></a>
				</c:when>
				<c:otherwise>
					<div class="summary-block">
						<span>
							<spring:theme code="mini.cart.empty"/>
						</span>
					</div>
					<c:if test="${not empty lightboxBannerComponent && lightboxBannerComponent.visible}">
						<cms:component component="${lightboxBannerComponent}" evaluateRestriction="true"  />
					</c:if>
					<a href="${cartUrl}" class="btn btn-primary btn-block mini-cart-checkout-button view-cart-button"><spring:theme code="mini.cart.button.view.cart"/></a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>