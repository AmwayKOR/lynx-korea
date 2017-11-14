<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:url value="/cart/add" var="addToCartUrl" />
<c:url value="/cart" var="checkoutUrl"></c:url>
<c:set var="outOfStock" value="${true}" />	
<c:if test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
	<c:set var="outOfStock" value="${false}" />
</c:if>
<div class="view-box" >
	<div class="cart-popup__dialog">
		<div class="cart-popup__header">
			<span class="cart-popup__header-text">
				QUICK VIEW
				<img class="cart-popup__close quick-view-close" src="${themeResourcePath}/images/close.png" alt="close" aria-label="Close" aria-hidden="true">
			</span>
		</div>
		<div class="cart-popup__content">
			<div class="cart-popup__item-info amwahover">
				<product:productPrimaryImage product="${product}" format="quickView" cssClass="cart-popup__thumbnail" />
				<product:quickViewPopupDetailsSection product="${product}" />
			</div>
			<div class="cart-popup__item-link">
				<div class="cart-popup__item-link-content">
					<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
						<a class="btn-blue-white sign-in-register" data-target="#login-drop-content" data-toggle="collapse"> 
							<spring:theme code="plp.producttile.signin.register" />
						</a>
					</sec:authorize>
					<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
						<form action="${addToCartUrl}" method="post" class="add_to_cart_form">
							<input type="hidden" name="productCodePost" value="${product.code}" />
							<button id="addToCartButton" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" <c:if test="${outOfStock}">disabled="disabled"</c:if>type="submit">
								<spring:theme code="plp.producttile.addtocart" />
							</button>
						</form>
						<button id="BuyNow" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" onclick="window.location.href='${checkoutUrl}'">Checkout</button>
						<a class="product-list__item-link-text product-list__item-link-common col-xs-12 col-md-12" href="#">
							<spring:theme code="plp.producttile.shoppinglist" />
						</a>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</div>