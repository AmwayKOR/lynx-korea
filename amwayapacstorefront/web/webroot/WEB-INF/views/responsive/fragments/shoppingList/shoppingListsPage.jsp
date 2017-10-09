<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${not empty errorMessage}">
	<div class="error-message"><spring:theme code="${errorMessage}" /></div>
</c:if>
<c:if test="${not empty successMessage}">
	<div class="success-message"><spring:theme code="${successMessage}" /></div>
	<div class="page-content" ><shoppingList:shoppingListsPageView /></div>
</c:if>	

