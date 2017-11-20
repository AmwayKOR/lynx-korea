<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

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
                                 <cms:pageSlot position="ShoppingListQuickShopSearchBoxSlot" var="component">
                                 	<cms:component component="${component}"/>
                                 </cms:pageSlot>
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