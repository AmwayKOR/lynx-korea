<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>

<div class="page-content">
	<shoppingList:shoppingListDetailsPageView shoppingListData="${shoppingListData}" />
</div>


