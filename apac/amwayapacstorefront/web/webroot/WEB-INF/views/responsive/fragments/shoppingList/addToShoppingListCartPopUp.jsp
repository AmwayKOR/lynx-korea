<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="addMultiProductListUrl" value="/shopping-lists/add-multiproduct" />
<form:form action="${addMultiProductListUrl}" method="post" id="multiShoppingListCartUpdateForm">
	<div class="cart-detail__panel-body">
		<c:choose>
			<c:when test="${not empty shoppingLists}">
				<c:forEach items="${shoppingLists}" var="availableProduct" varStatus="status">

					<input class="" id="${availableProduct.name}" name="shoppingLists[${status.index}].selected" type="checkbox">
					<label class="cart-detail__addto-options" for="${availableProduct.name}">
						<span>${availableProduct.name}</span>
					</label>
					<input class="" name="shoppingLists[${status.index}].shoppingListUid" type="hidden" value="${availableProduct.uid}">
					<input class="" name="shoppingLists[${status.index}].shoppingListName" type="hidden"
						value="${availableProduct.name}">
	
					<br />
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span>
					<spring:theme code="text.shoppinglist.emptylist" />
				</span>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="cart-detail__panel-body-link">
		<a class="btn-blue-white updateToList1">
			<spring:theme code="text.shoppinglist.addtolist" />
		</a>

		<a id="newlist" class="cartlist__cancelorder" href="/amwayapacstorefront/shopping-lists/all">
			<spring:theme code="text.shoppinglist.createnewlist" />
		</a>

	</div>

</form:form>
