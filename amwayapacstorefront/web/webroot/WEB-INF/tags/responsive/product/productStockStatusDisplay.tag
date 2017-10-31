<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<c:set var="productinstock" value="${true}" />
<c:if test="${product.stock.stockLevelStatus.code ne 'inStock'}">
	<c:set var="productinstock" value="${false}" />
</c:if>

<c:if test="${not empty product.stock.stockLevelStatus.code}">
	<div class="product-stock">
		<div>
			<c:choose>
				<c:when test="${productinstock}">
					<span class="stock"> <span class="product-availability">
							<span class="green"> <span class="icon icon-check-bold"></span>
								<span class="text text-uppercase">${product.stock.stockLevelStatusName}</span>
						</span>
					</span>
					</span>
					<br>
					<span class="pick-up product-availability"></span>
				</c:when>
				<c:otherwise>
					<span class="stock out-of-stock"> <span class="product-availability">
							<span class="red"><span class="text text-uppercase">${product.stock.stockLevelStatusName}</span>
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
