<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>

<%-- JS configuration --%>
	<script type="text/javascript">
		/*<![CDATA[*/
		<%-- Define a javascript variable to hold the content path --%>
		var ACC = { config: {} , messages:{}, globalMessageTypes:{}};
			ACC.config.contextPath = "${contextPath}";
			ACC.config.encodedContextPath = "${encodedContextPath}";
			ACC.config.commonResourcePath = "${commonResourcePath}";
			ACC.config.themeResourcePath = "${themeResourcePath}";
			ACC.config.siteResourcePath = "${siteResourcePath}";
			ACC.config.rootPath = "${siteRootUrl}";	
			ACC.config.CSRFToken = "${CSRFToken.token}";
			ACC.config.currentCurrecySymbol = "${currentCurrency.symbol}";	
			ACC.pwdStrengthVeryWeak = '<spring:theme code="password.strength.veryweak" />';
			ACC.pwdStrengthWeak = '<spring:theme code="password.strength.weak" />';
			ACC.pwdStrengthMedium = '<spring:theme code="password.strength.medium" />';
			ACC.pwdStrengthStrong = '<spring:theme code="password.strength.strong" />';
			ACC.pwdStrengthVeryStrong = '<spring:theme code="password.strength.verystrong" />';
			ACC.pwdStrengthUnsafePwd = '<spring:theme code="password.strength.unsafepwd" />';
			ACC.pwdStrengthTooShortPwd = '<spring:theme code="password.strength.tooshortpwd" />';
			ACC.pwdStrengthMinCharText = '<spring:theme code="password.strength.minchartext"/>';
			ACC.accessibilityLoading = '<spring:theme code="aria.pickupinstore.loading"/>';
			ACC.accessibilityStoresLoaded = '<spring:theme code="aria.pickupinstore.storesloaded"/>';
			ACC.config.googleApiKey="${googleApiKey}";
			ACC.config.googleApiVersion="${googleApiVersion}";

			<c:if test="${request.secure}"><c:url value="/search/autocompleteSecure"  var="autocompleteUrl"/></c:if>
			<c:if test="${not request.secure}"><c:url value="/search/autocomplete"  var="autocompleteUrl"/></c:if>
			ACC.autocompleteUrl = '${autocompleteUrl}';

			<c:url value="/login" var="loginUrl"/>
			ACC.config.loginUrl = '${loginUrl}';

			<c:url value="/authentication/status" var="authenticationStatusUrl"/>
			ACC.config.authenticationStatusUrl = '${authenticationStatusUrl}';

			<c:forEach var="jsVar" items="${jsVariables}">
				<c:if test="${not empty jsVar.qualifier}" >
					ACC.${jsVar.qualifier} = '${jsVar.value}';
				</c:if>
			</c:forEach>
			
			ACC.globalMessageTypes.CONF_MESSAGES_HOLDER = "CONF_MESSAGES_HOLDER";
			ACC.globalMessageTypes.WARNING_MESSAGES_HOLDER = "WARNING_MESSAGES_HOLDER";
			ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER = "ERROR_MESSAGES_HOLDER";

			ACC.messages.shoppingListNameEmptyErrorMessage= '<spring:theme code="shopping.list.create.error.empty.name"/>';
			ACC.messages.shoppingListCreationError='<spring:theme code="shopping.list.create.error"/>';
			ACC.messages.shoppingListAddProductError='<spring:theme code="shopping.list.add.product.error"/>';
			ACC.messages.shoppingListRemoveProductError='<spring:theme code="shopping.list.remove.product.error"/>';
			ACC.messages.quickViewFetchError='<spring:theme code="quick.view.fetch.error"/>';
			ACC.messages.shoppingListNameUpdateError='<spring:theme code="shoppinglist.update.name.error"/>';
			ACC.messages.showMoreProductsError='<spring:theme code="show.more.fetch.products.error"/>';
			ACC.messages.shoppingListUidEmptyErrorMessage= '<spring:theme code="shopping.list.add.product.error.empty.listuid"/>';
			ACC.messages.productCodeEmptyErrorMessage= '<spring:theme code="shopping.list.add.product.error.empty.name"/>';
			ACC.messages.productAddToCartError= '<spring:theme code="product.addtocart.error"/>';
			ACC.messages.shoppingListProductAddToCartError= '<spring:theme code="shopping.list.grid.addtocart.none.selected"/>';
			ACC.messages.quickShopProductCodeError= '<spring:theme code="quick.shop.product.code.error"/>';
			ACC.messages.quickShopQuantityError= '<spring:theme code="quick.shop.quantity.error"/>';
		/*]]>*/
	</script>
	<template:javaScriptAddOnsVariables/>
	
	<%-- generated variables from commonVariables.properties --%>
	<script type="text/javascript" src="${sharedResourcePath}/js/generatedVariables.js"></script>