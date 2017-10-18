<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>

<c:if test="${not empty errorMessage}">
	<div class="error-message"><spring:theme code="${errorMessage}" /></div>
</c:if>
<c:if test="${not empty successMessage}">
	<div class="success-message"><spring:theme code="${successMessage}" /></div>
	<div class="shopping-list-name-display-section">
		<h1 class="product-list-page-title mb25">
	     	${shoppingListName} 
	         <a href="#" class="name-edit-link">
	             <span class="edit-name"><spring:theme code="shopping.list.page.actions.section.edit.name" /></span></a>
	     </h1>
	</div>
</c:if>	

