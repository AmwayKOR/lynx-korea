<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>


<div class="cart-top-totals"><spring:theme code="shoppinglist.items.section.label.count" arguments="${fn:length(shoppingListData.entries)}" /></div>
<c:if test="${fn:length(shoppingListData.entries) > 0}">
	<div class="row">
	    <div class="col-md-9 cart-items-wrapper">
			<shoppingList:shoppingListItemsDisplayPaginationSection shoppingListData="${shoppingListData}" />
			<shoppingList:shoppingListItemsDisplayItemsListSection shoppingListData="${shoppingListData}" />
	    </div>
	    <div class="col-md-3">
	    	<shoppingList:shoppingListItemsDisplayOrderSummarySection shoppingListData="${shoppingListData}" />
	    </div>
	</div>
</c:if>