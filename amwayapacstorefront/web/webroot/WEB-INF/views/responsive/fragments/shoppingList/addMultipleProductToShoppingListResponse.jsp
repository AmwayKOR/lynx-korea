<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>

<c:url var="addNewListToShoppingListUrl" value="/shopping-lists/all" />
<common:globalMessages />
<c:if test="${not empty modification}">
	<div class="page-content">
		<div id="add-to-shopping-list" class="cbox">
			<div class="cart-popup__dialog">
				<div class="cart-popup__header">
					<span class="cart-popup__header-text">
						<spring:theme code="text.shoppinglist.addtolistpopup.addedtoshoppinglist" />
						<img class="cart-popup__close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal"
							aria-label="Close" aria-hidden="true">
					</span>
				</div>
				<div class="cart-popup__content">
					<shoppingList:shoppingListAddToListPopUp entry="${modification.entry}" />
					<div class="cart-popup__item-link">
						<a href="${addNewListToShoppingListUrl}" class="btn-blue-white">
							<spring:theme code="text.shoppinglist.addtolistpopup.gotoshoppinglist" />
						</a>
						<a class="cart-popup__item-link-text closeCbox">
							<spring:theme code="text.shoppinglist.addtolistpopup.continueshopping" />
						</a>
					</div>

				</div>
			</div>
		</div>
	</div>
</c:if>