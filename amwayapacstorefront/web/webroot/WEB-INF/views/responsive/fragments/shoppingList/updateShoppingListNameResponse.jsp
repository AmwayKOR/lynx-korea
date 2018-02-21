<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>

<common:globalMessages />
<c:if test="${not empty accConfMsgs}">
	<div class="shopping-list-name-display-section">
		<h1 class="product-list-page-title mb25">
	     	${shoppingListName} 
	         <a href="#" class="name-edit-link">
	             <span class="edit-name"><spring:theme code="shopping.list.page.actions.section.edit.name" /></span></a>
	     </h1>
	</div>
</c:if>	

