<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="/search/" var="searchUrl" />
<spring:url value="/search/autocomplete/{/componentuid}" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid" value="${component.uid}" />
</spring:url>
<c:url value="/cart/quickShop" var="quickShopFormAction" />
<div class="ui-front">
	<form name="search_form_${fn:escapeXml(component.uid)}" method="POST" action="${quickShopFormAction}"
		class="add-product-to-shopping-cart-form" id="quickShopForm">
		<div class="item-name-container">
			<label class="js-sku-input-field-label sku-input-field-label">
				<spring:theme code="basket.page.quickShop.itemNameOrNumber" />
			</label>
			<input type="text" id="js-shopping-cart-search-input"
				class="js-quick-shop-name quick-shop-name js-name-input js-shopping-cart-search-input" name="productCode" value=""
				maxlength="100" placeholder="${placeholder}"
				data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'>
			<input class="js-hidden-sku-field" type="hidden">
            <input type="hidden" name="CSRFToken" value="${CSRFToken.token}" />
		</div>
		<div class="qty-container">
			<label>
				<spring:theme code="basket.page.quickShop.qty" />
			</label>
			<input class="js-quick-shop-qty quick-shop-qty" type="text" name="quantity" />
		</div>
		<div class="add-item-container">
			<button class="btn btn-primary btn-block js-quick-shop-submit">
				<spring:theme code="basket.page.quickShop.addItem" />
			</button>
		</div>
	</form>
	<cart:shoppingCartSearchResultSection/>
</div>
