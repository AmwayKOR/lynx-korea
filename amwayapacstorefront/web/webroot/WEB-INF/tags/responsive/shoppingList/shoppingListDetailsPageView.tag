<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="shoppingListData" required="true" type="com.amway.facades.product.data.WishlistData" %>
<%@ attribute name="sortField" required="false" type="java.lang.String" %>

<div class="container-fluid main-container">
    <div class="print-hide breadcrumb-section">
        <ol class="breadcrumb">
            <li>
                <a href="homepage.html">Home</a></li>
            <li>
                <a href="shopping-list-landing.html">Shopping List</a></li>
            <li class="active">Shopping List Detail</li></ol>
    </div>
</div>
<div class="container-fluid main-container">
    <div class="row cartTitile new-shopping-list-detail no-border">
        <div>
            <h1 class="product-list-page-title mb25">${shoppingListData.name}
                <a href="">
                    <span class="edit-name"><spring:theme code="shopping.list.page.actions.section.edit.name" /></span></a>
            </h1>
            <span class="list-product">
                <a href="">
                    <span class="share-list"><spring:theme code="shopping.list.page.actions.section.share.list" /></span></a>
                <span class="divider">|</span>
                <a href="">
                    <span class="Delete-list"><spring:theme code="shopping.list.page.actions.section.delete.list" /></span></a>
            </span>
        </div>
        <div class="row cart-content-wrapper">
            <div class="account-section-content shoppingListDetail-content">
            	<shoppingList:shoppingListBasicDetailsSection shoppingListData="${shoppingListData}" />
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
                                                <div class="item-name-container">
                                                    <label class="js-sku-input-field-label sku-input-field-label">Item name or number</label>
                                                    <input class="js-quick-shop-name quick-shop-name js-name-input-field name-input-field ui-autocomplete-input" data-options="{&quot;autocompleteUrl&quot; : &quot;/lynxstorefront/lynx/en/quickOrder/autocomplete&quot;,&quot;minCharactersBeforeRequest&quot; : &quot;3&quot;,&quot;waitTimeBeforeRequest&quot; : &quot;500&quot;,&quot;displayProductImages&quot; : true}" autocomplete="off" type="text">
                                                    <input class="js-hidden-sku-field" type="hidden"></div>
                                                <div class="qty-container">
                                                    <label>Qty</label>
                                                    <input class="js-quick-shop-qty quick-shop-qty" type="text"></div>
                                                <div class="add-item-container">
                                                    <button class="btn btn-primary btn-block js-quick-shop-submit">Add item</button></div>
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
                <div class="cart-top-totals">your shopping list(28 items)</div>
                <div class="row">
                    <div class="col-md-9 cart-items-wrapper">
                        <div class="order-entry-pagination">
                            <div class="pagination-bar top new-pagination-bar">
                                <div class="print-hide pagination-toolbar">
                                    <div class="helper clearfix hidden-md hidden-lg"></div>
                                    <div class="sort-refine-bar">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-7 col-md-7 pagination-controls-wrapper">
                                                <div class="form-group">
                                                    <label class="control-label cart-detail__label" for="sortForm1">Sort by</label>
                                                    <select class="cart-detail__size" id="sortBy">
                                                        <option>Last Item Added</option>
                                                        <option selected="">Last Item Added</option>
                                                        <option>Last Item Added</option></select>
                                                    <a class="payment-forms__apply btn-blue-white cart-detail__mob-hide" href="#">apply</a></div>
                                            </div>
                                            <div class="col-xs-12 col-sm-5 col-md-5 pagination-wrap">
                                                <div class="add-to-component-container">
                                                    <div class="pull-right action-panel-wrapper add-to-dropdown">
                                                        <div class="cart-detail__dropdown dropdown dropdown-accordion" data-accordion="#addtoAccordion">
                                                            <div class="cart-detail__addto dropdown-toggle">add to</div>
                                                            <ul class="cart-detail__dropdown-menu dropdown-menu" role="menu" aria-labelledby="dLabel">
                                                                <div class="cart-detail__div-short"></div>
                                                                <div id="addtoAccordion" class=" clearfix">
                                                                    <div class="cart-detail__addto-item panel">
                                                                        <h5 class="product-category__item-header collapsed" href="#addtoCollapse1" data-toggle="collapse" data-parent="#addtoAccordion" aria-expanded="false">ADD TO DITTO</h5>
                                                                        <div class="panel-collapse collapse" id="addtoCollapse1" aria-expanded="false" style="height: 0px;">
                                                                            <div class="cart-detail__panel-body">
                                                                                <input class="" id="toDitto" name="toDitto" type="checkbox">
                                                                                <label class="cart-detail__addto-options" for="toDitto">Add to Ditto</label></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="cart-detail__addto-item panel">
                                                                        <h5 class="product-category__item-header collapsed" href="#addtoCollapse2" data-toggle="collapse" data-parent="#addtoAccordion" aria-expanded="false">ADD TO SHOPPING LIST</h5>
                                                                        <div class="panel-collapse collapse" id="addtoCollapse2" aria-expanded="false" style="height: 0px;">
                                                                            <div class="cart-detail__panel-body">
                                                                                <input class="" id="shoplistName" name="shoplistName" type="checkbox">
                                                                                <label class="cart-detail__addto-options" for="shoplistName">Shopping List Name</label>
                                                                                <br>
                                                                                <input class="" id="longShoplistName" name="longShoplistName" type="checkbox">
                                                                                <label class="cart-detail__addto-options" for="longShoplistName">Long Shopping List Name</label>
                                                                                <br>
                                                                                <input class="" id="shoplistName2" name="shoplistName2" type="checkbox">
                                                                                <label class="cart-detail__addto-options" for="shoplistName2">Shopping List Name</label>
                                                                                <br>
                                                                                <input class="" id="listName" name="listName" type="checkbox">
                                                                                <label class="cart-detail__addto-options" for="listName">List Name</label></div>
                                                                            <div class="cart-detail__panel-body-link">
                                                                                <a href="#" class="btn-blue-white">add to list</a>
                                                                                <a class="cartlist__cancelorder">Create New List</a></div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="cart-detail__addto-item panel">
                                                                        <h5 class="product-category__item-header collapsed" href="#addtoCollapse3" data-toggle="collapse" data-parent="#addtoAccordion" aria-expanded="false">ADD TO RECEIPT</h5>
                                                                        <div class="panel-collapse collapse" id="addtoCollapse3" aria-expanded="false" style="height: 0px;">
                                                                            <div class="cart-detail__panel-body">
                                                                                <input class="" id="toReceipt" name="toReceipt" type="checkbox">
                                                                                <label class="cart-detail__addto-options" for="toReceipt">Add to Receipt</label></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="pull-right action-panel-wrapper cartlist__cancelorder cart-detail__update-cart">
                                                    <div class="text-action-btn">
                                                        <span>Update Cart</span></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <ul class="shopping-cart-item-list js-shopping-cart-item-list">
                            <li class="visible-md visible-lg">
                                <div class="col-xs-12 list-header">
                                    <div class="col-xs-4 list-item-info">Item (style number)</div>
                                    <div class="col-xs-1 list-item-quantity">Qty</div>
                                    <div class="col-xs-3 list-item-set-price">set price</div>
                                    <div class="col-xs-2 list-item-price">Total Price</div>
                                    <div class="col-xs-2 list-item-total">Total PV/BV</div></div>
                            </li>
                            <li class="product-list-item js-shopping-cart-list-item">
                                <div class="col-xs-12 col-md-4 product-details print-col-6">
                                    <div class="product-item-element list-item-toggle">
                                        <label class="checkbox-element-wrapper">
                                            <input class="shopping-cart-entry-checkbox _checkbox-element-global-class" value="" data-product-code="" type="checkbox">
                                            <span class="_checkbox-element-global-span"></span>
                                        </label>
                                    </div>
                                    <div class="product-item-element list-item-image">
                                        <div>
                                            <a href="#">
                                                <img src="${themeResourcePath}/images/nu105480.jpg" alt="Soft Cleanser" title="Soft Cleanser"></a>
                                        </div>
                                        <div class="list-item-remove">
                                            <div class="remove-item-btn js-remove-entry-button" id="removeEntry_1">
                                                <span class="">Remove</span></div>
                                        </div>
                                    </div>
                                    <div class="product-item-element list-item-info">
                                        <span class="product-name">Nutrilite Men's Pack</span>
                                        <div class="product-code">Item #
                                            <span class="product-item-number">105480</span></div>
                                        <div class="product-category">Vitamins &amp; Supplements</div>
                                        <div class="product-stock">
                                            <div>
                                                <span class="stock">
                                                    <span class="product-availability">
                                                        <span class="green">
                                                            <span class="icon icon-check-bold"></span>
                                                            <span class="text text-uppercase">In Stock</span></span>
                                                    </span>
                                                </span>
                                                <br>
                                                <span class="pick-up product-availability"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-9 col-md-1 product-item-element list-item-quantity print-col-6">
                                    <form id="updateCartForm0" class="js-qty-form0" data-cart="" action="#" method="post">
                                        <input id="quantity_0" class="form-control js-update-entry-quantity-input quantity-value " name="quantity" value="1" size="1" type="text"></form>
                                    <span class="usage-cal">Usage Calculator</span>
                                </div>
                                <div class="col-xs-9 product-item-element list-item-ibo-price col-md-3 list-item-price print-col-6">
                                    <form id="sortForm1" name="sortForm1" method="get" action="#">
                                        <select id="sortOptions1" name="sort" class="form-control">
                                            <option disabled="">Sort by</option>
                                            <option value="name-asc-c" selected="selected">IBO Price-$47.76</option>
                                            <option value="name-desc-c">IBO Price-$34.26</option>
                                            <option value="retail-price-asc-c">IBO Price-$21.74</option>
                                            <option value="retail-price-desc-c">IBO Price-$32.56</option>
                                            <option value="newest-asc-c">IBO Price-$25.43</option>
                                            <option value="newest-desc-c">IBO Price-$78.23</option>
                                            <option value="category-asc-c">IBO Price-$89.46</option>
                                            <option value="category-desc-c">IBO Price-$49.16</option></select>
                                        <input name="q" value=":name-asc-c" type="hidden">
                                        <input name="pageType" value="CATEGORY" type="hidden">
                                        <input name="text" value="" type="hidden">
                                        <br>
                                       </form>
                                </div>
                                <div class="col-xs-9 product-item-element list-item-ibo-price col-md-2 list-item-price print-col-6">
                                    <span class="price-label">Price</span>
                                    <span class="value-wrapper">$238.80</span></div>
                                <div class="col-xs-9 col-md-2 product-item-element list-item-total js-item-total  print-col-6">
                                    <span class="total-price-label">Total Price</span>
                                    <span class="value-wrapper">91.10/286.55</span></div>
                            </li>
                            <li class="product-list-item js-shopping-cart-list-item">
                                <div class="col-xs-12 col-md-4 product-details print-col-6">
                                    <div class="product-item-element list-item-toggle">
                                        <label class="checkbox-element-wrapper">
                                            <input class="shopping-cart-entry-checkbox _checkbox-element-global-class" value="" data-product-code="" type="checkbox">
                                            <span class="_checkbox-element-global-span"></span>
                                        </label>
                                    </div>
                                    <div class="product-item-element list-item-image">
                                        <div>
                                            <a href="#">
                                                <img src="${themeResourcePath}/images/nu105480.jpg" alt="Soft Cleanser" title="Soft Cleanser"></a>
                                        </div>
                                        <div class="list-item-remove">
                                            <div class="remove-item-btn js-remove-entry-button" id="removeEntry_1">
                                                <span class="">Remove</span></div>
                                        </div>
                                    </div>
                                    <div class="product-item-element list-item-info">
                                        <span class="product-name">Nutrilite Men's Pack</span>
                                        <div class="product-code">Item #
                                            <span class="product-item-number">105480</span></div>
                                        <div class="product-category">Vitamins &amp; Supplements</div>
                                        <div class="product-stock">
                                            <div>
                                                <span class="stock">
                                                    <span class="product-availability">
                                                        <span class="green">
                                                            <span class="icon icon-check-bold"></span>
                                                            <span class="text text-uppercase">In Stock</span></span>
                                                    </span>
                                                </span>
                                                <br>
                                                <span class="pick-up product-availability"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-9 col-md-1 product-item-element list-item-quantity print-col-6">
                                    <form id="updateCartForm0" class="js-qty-form0" data-cart="" action="#" method="post">
                                        <input id="quantity_0" class="form-control js-update-entry-quantity-input quantity-value " name="quantity" value="1" size="1" type="text"></form>
                                </div>
                                <div class="col-xs-9 product-item-element list-item-ibo-price col-md-3 list-item-price print-col-6">
                                    <form id="sortForm1" name="sortForm1" method="get" action="#">
                                        <select id="sortOptions1" name="sort" class="form-control">
                                            <option disabled="">Sort by</option>
                                            <option value="name-asc-c" selected="selected">Retail Price-$1,831.46</option>
                                            <option value="name-desc-c">Retail Price-$1,566.45</option>
                                            <option value="retail-price-asc-c">Retail Price-$1,452</option>
                                            <option value="retail-price-desc-c">Retail Price-$2,331.36</option>
                                            <option value="newest-asc-c">Retail Price-$3,231.32</option>
                                            <option value="newest-desc-c">Retail Price-$1,233.34</option>
                                            <option value="category-asc-c">Retail Price-$1,556.23</option>
                                            <option value="category-desc-c">Retail Price-$1,832.36</option></select>
                                        <input name="q" value=":name-asc-c" type="hidden">
                                        <input name="pageType" value="CATEGORY" type="hidden">
                                        <input name="text" value="" type="hidden">
                                        <br>
                                        </form>
                                </div>
                                <div class="col-xs-9 product-item-element list-item-ibo-price col-md-2 list-item-price print-col-6">
                                    <span class="price-label">Price</span>
                                    <span class="value-wrapper">$5494.38</span></div>
                                <div class="col-xs-9 col-md-2 product-item-element list-item-total js-item-total  print-col-6">
                                    <span class="total-price-label">Total Price</span>
                                    <span class="value-wrapper">966.45/3,038.46</span></div>
                            </li>
                            <li class="product-list-item js-shopping-cart-list-item">
                                <div class="col-xs-12 col-md-4 product-details print-col-6">
                                    <div class="product-item-element list-item-toggle">
                                        <label class="checkbox-element-wrapper">
                                            <input class="shopping-cart-entry-checkbox _checkbox-element-global-class" value="" data-product-code="" type="checkbox">
                                            <span class="_checkbox-element-global-span"></span>
                                        </label>
                                    </div>
                                    <div class="product-item-element list-item-image">
                                        <div>
                                            <a href="#">
                                                <img src="${themeResourcePath}/images/nu105480.jpg" alt="Soft Cleanser" title="Soft Cleanser"></a>
                                        </div>
                                        <div class="list-item-remove">
                                            <div class="remove-item-btn js-remove-entry-button" id="removeEntry_1">
                                                <span class="">Remove</span></div>
                                        </div>
                                    </div>
                                    <div class="product-item-element list-item-info">
                                        <span class="product-name">Nutrilite Men's Pack</span>
                                        <div class="product-code">Item #
                                            <span class="product-item-number">105480</span></div>
                                        <div class="product-category">Vitamins &amp; Supplements</div>
                                        <div class="product-stock">
                                            <div>
                                                <span class="stock">
                                                    <span class="product-availability">
                                                        <span class="green">
                                                            <span class="icon icon-check-bold"></span>
                                                            <span class="text text-uppercase">In Stock</span></span>
                                                    </span>
                                                </span>
                                                <br>
                                                <span class="pick-up product-availability"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-9 col-md-1 product-item-element list-item-quantity print-col-6">
                                    <form id="updateCartForm0" class="js-qty-form0" data-cart="" action="#" method="post">
                                        <input id="quantity_0" class="form-control js-update-entry-quantity-input quantity-value " name="quantity" value="1" size="1" type="text"></form>
                                </div>
                                <div class="col-xs-9 product-item-element list-item-ibo-price col-md-3 list-item-price print-col-6">
                                    <form id="sortForm1" name="sortForm1" method="get" action="#">
                                        <select id="sortOptions1" name="sort" class="form-control">
                                            <option disabled="">Sort by</option>
                                            <option value="name-asc-c" selected="selected">IBO Price-$47.76</option>
                                            <option value="name-desc-c">IBO Price-$21.46</option>
                                            <option value="retail-price-asc-c">IBO Price-$34.79</option>
                                            <option value="retail-price-desc-c">IBO Price-$32.16</option>
                                            <option value="newest-asc-c">IBO Price-$78.65</option>
                                            <option value="newest-desc-c">IBO Price-$14.51</option>
                                            <option value="category-asc-c">IBO Price-$32.86</option>
                                            <option value="category-desc-c">IBO Price-$93.58</option></select>
                                        <input name="q" value=":name-asc-c" type="hidden">
                                        <input name="pageType" value="CATEGORY" type="hidden">
                                        <input name="text" value="" type="hidden">
                                        <br>
                                       </form>
                                </div>
                                <div class="col-xs-9 product-item-element list-item-ibo-price col-md-2 list-item-price print-col-6">
                                    <span class="price-label">Price</span>
                                    <span class="value-wrapper">$383.60</span></div>
                                <div class="col-xs-9 col-md-2 product-item-element list-item-total js-item-total  print-col-6">
                                    <span class="total-price-label">Total Price</span>
                                    <span class="value-wrapper">103.80/326.00</span></div>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <div class="row shopping-cart-total-wrapper shoppingListDetail-wrapper">
                            <div class="shopping-cart-total">
                                <div class="order-block">
                                    <div class="order-block-header">
                                        <span>Order Summary</span></div>
                                    <div class="cart-totals">
                                        <div class="js-cart-totals js-cart-totals-order-summary">
                                            <div class="wrapper row auto-subtotal">
                                                <div class="col-xs-6 cart-totals-left">Item(s) Subtotal</div>
                                                <div class="col-xs-6 cart-totals-right text-right">$58.20</div></div>
                                            <!--<div class="wrapper row auto-promotions">-->
                                                <!--<div class="col-xs-8 cart-totals-left">-->
                                                    <!--<span>Promotions</span>-->
                                                    <!--<span class="cart-order-symmary-button-wrapper">(-->
                                                        <!--<span class="details-button js-checkout-promotions-details-btn" data-content="orderSummaryPromotionsModal">Details</span>)</span></div>-->
                                                <!--<div class="col-xs-4 cart-totals-right text-right red">- $12.00</div></div>-->
                                            <!--<div class="wrapper row auto-delivery">-->
                                                <!--<div class="col-xs-6 cart-totals-left">Shipping</div>-->
                                                <!--<div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>-->
                                            <!--<div class="wrapper row auto-delivery">-->
                                                <!--<div class="col-xs-6 cart-totals-left">Tax One</div>-->
                                                <!--<div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>-->
                                            <!--<div class="wrapper row auto-delivery">-->
                                                <!--<div class="col-xs-6 cart-totals-left">Tax Two</div>-->
                                                <!--<div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>-->
                                            <!--<div class="wrapper row auto-delivery">-->
                                                <!--<div class="col-xs-6 cart-totals-left">Service Fee(s)</div>-->
                                                <!--<div class="col-xs-6 cart-totals-right text-right">$0.00</div></div>-->
                                            <div class="wrapper wrapper-borders row auto-total">
                                                <div class="col-xs-6 cart-totals-left">Total</div>
                                                <div class="col-xs-6 cart-totals-right text-right">$46.20</div></div>
                                            <div class="wrapper row auto-total noTop">
                                                <div class="col-xs-6 cart-totals-left">TOTAL PV/ BV</div>
                                                <div class="col-xs-6 cart-totals-right text-right">222.45 / 699.63</div></div>
                                        </div>
                                        <!--<div class="cart-voucher">-->
                                            <!--<div class="proceed-to-checkout-container">-->
                                                <!--<button class="btn btn-primary btn-block btn&#45;&#45;continue-checkout js-continue-checkout-button" data-checkout-url="#">Proceed to Checkout</button></div>-->
                                        <!--</div>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--<div class="row cart-actions">-->
                    <!--<div class="col-xs-12 col-md-3 checkout-button-container">-->
                        <!--<button class="btn btn-primary btn-block checkoutButton continueCheckout" data-checkout-url="#">proceed to checkout</button></div>-->
                    <!--<div class="col-xs-12 col-md-8 continue-shopping-button-container">-->
                        <!--<button class="btn btn-link btn-block continueShoppingButton" data-continue-shopping-url="#">Continue Shopping</button></div>-->
                <!--</div>-->
            </div>
        </div>
    </div>
</div>