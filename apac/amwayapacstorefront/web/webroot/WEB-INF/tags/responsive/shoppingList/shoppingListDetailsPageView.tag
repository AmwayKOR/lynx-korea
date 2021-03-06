<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>

<div class="container-fluid main-container">
    <div class="row cartTitile new-shopping-list-detail no-border">
        <shoppingList:shoppingListDetailsPageNameSection shoppingListData="${shoppingListData}" />
        <div class="row cart-content-wrapper">
            <div class="account-section-content shoppingListDetail-content">
            	<shoppingList:shoppingListBasicDetailsSection shoppingListData="${shoppingListData}" />
            	<shoppingList:shoppingListDetailsQuickShopSection shoppingListData="${shoppingListData}" />
            	<shoppingList:shoppingListItemsDisplaySection shoppingListData="${shoppingListData}" />
            </div>
        </div>
    </div>
</div>