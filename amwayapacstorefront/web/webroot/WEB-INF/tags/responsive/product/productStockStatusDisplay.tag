<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
	<c:set var="disableAddToCart"
			value="${(product.stock.stockLevelStatus.code eq 'outOfStock') || (product.stock.stockLevelStatus.code eq 'noLongerAvailable') || (product.stock.stockLevelStatus.code eq 'temporaryNotAvailable') || (product.stock.stockLevelStatus.code eq 'notYetAvailable') || (product.stock.stockLevelStatus.code eq 'backOrder') || (product.stock.stockLevelStatus.code eq 'notSpecified')}" />
	<c:if test="${not empty product.stock.stockLevelStatus.code}">
		<div class="product-stock">
			<div>
				<c:choose>
					<c:when test="${not disableAddToCart}">
						<span class="stock">
							<span class="product-availability">
								<span class="green">
									<span class="icon icon-check-bold"></span>
									<span class="text text-uppercase">
										<spring:theme code="pdp.stock.level.${product.stock.stockLevelStatus.code}" />
									</span>
								</span>
							</span>
						</span>
						<br>
						<span class="pick-up product-availability"></span>
					</c:when>
					<c:otherwise>
						<span class="stock out-of-stock">
							<span class="product-availability">
								<span class="red">
									<span class="text text-uppercase">
										<spring:theme code="pdp.stock.level.${product.stock.stockLevelStatus.code}" />
									</span>
								</span>
							</span>
						</span>
						<br>
						<span class="pick-up product-availability"></span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:if>
</sec:authorize>
