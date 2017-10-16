<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>
<%@ attribute name="sortField" required="false" type="java.lang.String" %>

<c:url var="addProductToShoppingListUrl" value="/shopping-lists/add-product" />
<div class="account-section-content">
     <div id="quickOrder" class="account-section shopping-cart-quick-shop-wrapper" data-grid-confirm-message="">
         <div class="quick-shop-container new-quick-shop-container">
             <div id="quickShopAccordion" class="panel-group accordion-custom" role="tablist" aria-multiselectable="true">
                 <div class="panel">
                     <div class="panel-heading" role="tab" id="quickShop">
                         <h4 class="panel-title">
                             <div role="button" data-toggle="collapse" data-parent="#quickShopAccordion" href="#quickShopBody" aria-controls="quickShopBody" class="collapsed">
                                 <img src="${themeResourcePath}/images/shopping-cart-icon.png" alt="cart" class="cart-header__cart-icon" />
                                 <span class="text-uppercase accordion-header-text">Quick Shop</span>
                                 <span class="pull-right icon-minus"></span>
                             </div>
                         </h4>
                     </div>
                     <div id="quickShopBody" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
                         <div class="panel-body">
                             <div id="quickShopForm">
                             	 <form method="POST" action="${addProductToShoppingListUrl}" class="add-product-to-shopping-list-form" id="addProductToShoppingListForm">
	                                 <div class="item-name-container">
	                                     <label class="js-sku-input-field-label sku-input-field-label"><spring:theme code="shopping.list.page.quick.shop.item.label" /></label>
	                                     <input class="js-quick-shop-name quick-shop-name js-name-input" type="text" name="productCode">
	                                 	 <input type="hidden" name="shoppingListUid" value="${shoppingListData.uid}">    	
	                                 </div>
	                                 <div class="qty-container">
	                                     <label><spring:theme code="shopping.list.page.quick.quantity.label" /></label>
	                                     <input class="js-quick-shop-qty quick-shop-qty" type="text" value="1" disabled="disabled"></div>
	                                 <div class="add-item-container">
	                                     <button class="btn btn-primary btn-block js-quick-shop-submit"><spring:theme code="shopping.list.page.quick.submit.label" /></button></div>
                                 </form>
                             </div>
                             <div class="row">
                                 <div class="col-sm-12">
                                     <div class="js-quick-shop-message quick-shop-message">
                                         <span class="icon-check-bold"></span>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
 </div>