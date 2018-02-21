<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="/search/" var="searchUrl" />
<spring:url value="/search/autocomplete/{/componentuid}" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid" value="${component.uid}" />
</spring:url>
<c:url var="addProductToShoppingListUrl" value="/shopping-lists/add-product" />
<div class="ui-front">
	<form name="search_form_${fn:escapeXml(component.uid)}" method="POST" action="${addProductToShoppingListUrl}"
		class="add-product-to-shopping-list-form" id="addProductToShoppingListForm">
		<div class="item-name-container">
			<label class="js-sku-input-field-label sku-input-field-label">
				<spring:theme code="shopping.list.page.quick.shop.item.label" />
			</label>
			<input type="text" id="js-shopping-list-search-input" class="js-quick-shop-name quick-shop-name js-name-input js-shopping-list-search-input" name="productCode" value=""
					maxlength="100" placeholder="${placeholder}"
					data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'>
			<input type="hidden" name="shoppingListUid" value="${shoppingListData.uid}">
		</div>
		<div class="qty-container">
			<label>
				<spring:theme code="shopping.list.page.quick.quantity.label" />
			</label>
			<input class="js-quick-shop-qty quick-shop-qty" type="text" value="1">
		</div>
		<div class="add-item-container">
			<button class="btn btn-primary btn-block js-quick-shop-submit" type="submit">
				<spring:theme code="shopping.list.page.quick.submit.label" />
			</button>
		</div>
	</form>
	<shoppingList:shoppingListSearchResultSection/>
</div>
