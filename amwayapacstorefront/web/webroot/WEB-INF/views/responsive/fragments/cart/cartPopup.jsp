<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>

<div class="mini-cart js-mini-cart">
	<div class="mini-cart-body">
		<c:choose>
			<c:when test="${numberShowing > 0 && numberItemsInCart > 0}">
				<header:minicartWithProducts />
			</c:when>
			<c:otherwise>
				<header:minicartWithoutProducts />
			</c:otherwise>
		</c:choose>
		<c:if test="${not empty lightboxBannerComponent && lightboxBannerComponent.visible}">
			<cms:component component="${lightboxBannerComponent}" evaluateRestriction="true" />
		</c:if>
		<a href="${cartUrl}" class="btn btn-primary btn-block mini-cart-checkout-button view-cart-button">
			<spring:theme code="mini.cart.button.view.cart" />
		</a>
	</div>
</div>