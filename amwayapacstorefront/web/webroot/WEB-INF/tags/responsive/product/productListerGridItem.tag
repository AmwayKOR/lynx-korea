<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:url value="${product.url}" var="productPDPurl" />
<c:url value="/cart/add" var="addToCartUrl" />
<div class="list-item-wrapper">
	<div class="amway-suggest__item-container">
		<div class="amway-suggest__item">
			<div class="product-list__item">
				<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
					<c:if test="${not product.multidimensional}">
						<product:productTileBadge stockLevel="${product.stock.stockLevelStatus.code}" />
					</c:if>
				</sec:authorize>
				<div class="product-list__item-content amwahover">
					
					<span class="product-list__thum" >
						<product:productPrimaryImage product="${product}" format="productGrid" cssClass="product-list__thumbnail" />
						<button class="quick-view-btn" data-product-code="${product.code}">
						<spring:theme code="plp.producttile.quick.view" /></button>
					</span>
					<div class="product-list__item-detail">
						<p class="product-list__item-title">
							<a class="product-list__item-title" target="_self" href="${productPDPurl}">${product.name}</a>
						</p>
						<p class="product-list__item-number">
							<spring:theme code="plp.producttile.item.no" />
							${product.code}
						</p>
						<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
							<div class="product-list__item-title product-list__item-aboprice">
								<span class="product-list__item-abolabel">
									<spring:theme code="plp.producttile.price" />
								</span>
								<span class="product-list__item-abovalue">${product.price.formattedValue}</span>
							</div>
						</sec:authorize>
						<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
							<div class="product-list__item-title product-list__item-aboprice">
								<span class="product-list__item-abolabel">
									<spring:theme code="plp.producttile.abo.price" />
								</span>
								<span class="product-list__item-abovalue">${product.price.formattedValue}</span>
							</div>
							<div class="product-list__item-retailprice">
								<span class="product-list__item-abolabel">
									<spring:theme code="plp.producttile.retail.price" />
								</span>
								<span class="product-list__item-abovalue">${product.retailPrice.formattedValue}</span>
							</div>
							<div class="product-list__item-retailprice">
								<span class="product-list__item-abolabel">
									<spring:theme code="plp.producttile.pvbv" />
								</span>
								<span class="product-list__item-abovalue">${product.price.amwayValue.pointValue} /
									${product.price.amwayValue.businessVolume}</span>
							</div>
						</sec:authorize>
					</div>
				</div>
				<div class="product-list__item-link col-md-12">
					<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
						<button class="btn-blue-white sign-in-register" data-target="#login-drop-content" data-toggle="collapse">
							<spring:theme code="plp.producttile.signin.register" />
						</button>
					</sec:authorize>
					<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
						<c:choose>
							<c:when test="${product.multidimensional}">
								<button class="btn-blue-white add-to-cart-quick-view" type="submit" data-product-code="${product.code}">
									<spring:theme code="plp.producttile.addtocart" />
								</button>
								<a
									class="product-list__item-link-text product-list__item-link-common col-xs-12 col-md-12 add-to-cart-quick-view"
									data-product-code="${product.code}" href="#">
									<spring:theme code="plp.producttile.shoppinglist" />
								</a>
							</c:when>
							<c:otherwise>

								<form action="${addToCartUrl}" method="post" class="add_to_cart_form">
									<input type="hidden" name="productCodePost" value="${product.code}" />
									
										<button class="btn-blue-white btn-primary" type="submit"  
											<c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock'}">disabled="disabled"</c:if>>
											
											<spring:theme code="plp.producttile.addtocart" />
										</button>
									
								</form>
								<c:url var="addToShoppingListUrl" value="/shopping-lists/data/all" />
								<a class="addToList product-list__item-link-text product-list__item-link-common col-xs-12 col-md-12"
									data-add-to-shopping-list-url="${addToShoppingListUrl}" data-product-code="${product.code}">
									<spring:theme code="plp.producttile.shoppinglist" />
								</a>
								<div class="cart-detail__dropdown-menu dropdown-menu shopping-list-popup-wrapper plp-add-to-shopping-list-popup shoppinglist-popup" role="menu">
								</div>
							</c:otherwise>
						</c:choose>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</div>
