<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>

<c:url var="updateShoppingListNameUrl" value="/shopping-lists/update-shopping-list-name" />
<c:url var="removeShoppingListUrl" value="/shopping-lists/remove-shopping-list" />

<div id="shopping-list-name-section">
	<div id="name-update-form" class="display-none">
		<form method="POST" action="${updateShoppingListNameUrl}" class="update-shopping-list-name-form" id="updateShoppingListNameForm">
			<h1 class="product-list-page-title mb25">
	    		<input class="js-quick-shop-name quick-shop-name js-name-input shopping-list-name" type="text" name="shoppingListName" data-original-name="${fn:escapeXml(shoppingListData.name)}" value="${fn:escapeXml(shoppingListData.name)}">
	    		<input type="hidden" name="shoppingListUid" value="${shoppingListData.uid} ">
	    		<a href="#" class="submit-name-update-form">
	            	 <span class="edit-name name-section-tiny-span"><spring:theme code="shopping.list.page.actions.section.save.name" /></span></a>
	            <span class="divider name-section-tiny-span">|</span>
	        	<a href="#" class="cancel-name-update-form">
	            	<span class="edit-name name-section-tiny-span"><spring:theme code="shopping.list.page.actions.section.cancel.name" /></span></a>
	    	</h1>
	    </form>
    </div>
    <div id="name-display-section">
	     <h1 class="product-list-page-title mb25">
	     	<span class="shopping-list-name-display">
	     			${fn:escapeXml(shoppingListData.name)}
	     	</span>
	         <a href="#" class="name-edit-link">
	             <span class="edit-name name-section-tiny-span"><spring:theme code="shopping.list.page.actions.section.edit.name" /></span></a>
	     </h1>
    </div>
    <div>
	    <span class="list-product">
	        <a href="">
	            <span class="share-list name-section-tiny-span"><spring:theme code="shopping.list.page.actions.section.share.list" /></span></a>
	        <span class="divider">|</span>
		    <form method="POST" action="${removeShoppingListUrl}" class="remove-shopping-list-url" id="removeShoppingListForm">
                <input type="hidden" name="shoppingListUid" value="${shoppingListData.uid} ">
                <input type="hidden" name="CSRFToken" value="${CSRFToken.token}">
                <a href="#">
                    <span class="Delete-list name-section-tiny-span delete-shopping-list-link"><spring:theme code="shopping.list.page.actions.section.delete.list" /></span>
                </a>
            </form>
	    </span>
    </div>
</div>