<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="addMultiProductListUrl" value="/shopping-lists/add-multiproduct" />
<form:form action="${addMultiProductListUrl}" method="post" id="multiShoppingListUpdateForm">
	<ul>
		<c:choose>
			<c:when test="${not empty shoppingLists}">
				<input class="js-populated-product-code" name="productCodes[0]" type="hidden">
				<c:forEach items="${shoppingLists}" var="availableProduct" varStatus="status">
					<li>
						<input class="" id="toSL${status.index}" name="shoppingLists[${status.index}].selected" type="checkbox">
						<label class="cart-detail__addto-options" for="toSL${status.index}"></label>
						<input class="" name="shoppingLists[${status.index}].shoppingListUid" type="hidden" value="${availableProduct.uid}">
							<input class="" name="shoppingLists[${status.index}].shoppingListName" type="hidden" value="${availableProduct.name}">
					
						<span>${availableProduct.name}</span>
					</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<li>
					<span>
						<spring:theme code="text.shoppinglist.emptylist" />
					</span>
				</li>

			</c:otherwise>
		</c:choose>
	</ul>

	<div class="dropdown-menu-bottom">
		<button class="btn btn-primary" <c:if test="${empty shoppingLists}">disabled="disabled"</c:if> type="submit">
			<spring:theme code="text.shoppinglist.addtolist" />
		</button>
		
		<a id="newlist" href="/amwayapacstorefront/shopping-lists/all">
			<spring:theme code="text.shoppinglist.createnewlist" />
		</a>
	</div>
</form:form>
