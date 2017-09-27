<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>

<cms:pageSlot position="TopHeaderSlot" var="component" element="div" class="container">
	<cms:component component="${component}" />
</cms:pageSlot>
<header class="js-mainHeader main-header general-header">
    <div class="amway-theme">
        <nav class="header-container">
            <div class="header-wrapper pos-relative">
                <div class="collapse popover fade bottom" role="tooltip" id="login-drop-content">
                    <i class="mobile-popover-close"></i>
                    <div class="arrow" style=""></div>
                    <h3 class="popover-title" style="display: none;"></h3>
                    <div class="popover-content">
                        <ul class="nav__links account-popover js-my-account-popover">
                            <li class="account-popover__congratulation">
                                <div class="account">
                                    <div class="sys-message">
                                        <div class="ico-wraper">
                                            <i class="icon-caution"></i>
                                        </div>
                                        <span>A new ABO has signed a contract!</span></div>
                                    <div class="sys-message">
                                        <div class="ico-wraper">
                                            <span class="message-num">99</span></div>
                                        <span>You have new messages!</span></div>
                                </div>
                            </li>
                            <li class="account-popover__scores">
                                <ul>
                                    <li>PV: 300.0</li>
                                    <li>CVR: No</li></ul>
                                <p>Last Update: 8/17/2016 - 4:07pm</p>
                            </li>
                            <li class="account-popover__pv-display" onclick="javascript:location.href='dashboard-MVP.html'">
                                <img src="${themeResourcePath}/images/performance-chart-arch-mobile.png" alt="" /></li>
                            <li class="account-popover__element">
                                <a href="MyAccount.html" title="My Account">My Account</a></li>
                            <li class="account-popover__element">
                                <a href="AddNewPaymentMethod.html" title="Billing Shipping">Billing Shipping</a></li>
                            <li class="account-popover__element">
                                <a href="#" title="Ditto">Ditto</a></li>
                            <li class="account-popover__element">
                                <a href="#" title="Personal Details">Personal Details</a></li>
                            <li class="account-popover__element">
                                <a href="Order-History-Personal-Orders-expanded.html" title="Order History">Order History</a></li>
                            <li class="account-popover__element sign-out">
                                <a href="homepage-not-login.html" title="Sign Out">
                                    <i class="glyphicon glyphicon-log-out"></i>Sign Out</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="collapse mini-cart-items-container js-mini-cart-items-container mini-cart-wrapper" id="shoppingcar-drop-content">
                    <div class="arrow" style=""></div>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a data-toggle="tab" href="#miniShoppingCartPopupContentPC">Shopping Cart (
                                <span id="minicartTabTotalItems">18</span>)</a></li>
                        <li>
                            <a data-toggle="tab" href="#dittoPopupContentPC">My Ditto</a></li>
                    </ul>
                    <div class="tab-content js-tab-content">
                        <div id="miniShoppingCartPopupContentPC" class="tab-pane fade in active">
                            <div class="mini-cart js-mini-cart">
                                <div class="mini-cart-body">
                                    <ol class="mini-cart-list">
                                        <li class="mini-cart-item">
                                            <div class="row product-wrapper">
                                                <div class="product-item-element list-item-image col-xs-3">
                                                    <div class="thumb">
                                                        <a href="">
                                                            <img src="${themeResourcePath}/images/heart-health-65.png" alt="Lip Gloss Creme" title="Lip Gloss Creme" /></a>
                                                    </div>
                                                </div>
                                                <div class="product-details col-xs-9">
                                                    <div class="name-wrapper">
                                                        <a class="name" href="">Lip Gloss Creme</a></div>
                                                    <div class="product-item-element list-item-ibo-price">
                                                        <div>
                                                            <div class="first-price">
                                                                <span class="label-wrapper">IBO Cost:</span>
                                                                <span class="value-wrapper">$24.80</span>
                                                                <div class="retail-price">
                                                                    <s>
                                                                        <span class="retail-price-strike-out">$24.99</span></s>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <span class="label-wrapper">Retail price:</span>
                                                                <span class="value-wrapper">$25.99</span></div>
                                                        </div>
                                                    </div>
                                                    <div class="product-item-element list-item-pv-bv">
                                                        <span class="total-pv-bv-label">PV/BV:</span>
                                                        <span class="value-wrapper">4.0/3.0</span></div>
                                                    <div class="qty">
                                                        <span class="qty-label">Qty:</span>
                                                        <span class="qty-value">1</span></div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="mini-cart-item">
                                            <div class="row product-wrapper">
                                                <div class="product-item-element list-item-image col-xs-3">
                                                    <div class="thumb">
                                                        <a href="">
                                                            <img src="${themeResourcePath}/images/heart-health-65.png" alt="Spray Bottle" title="Spray Bottle" /></a>
                                                    </div>
                                                </div>
                                                <div class="product-details col-xs-9">
                                                    <div class="name-wrapper">
                                                        <a class="name" href="">Spray Bottle</a></div>
                                                    <div class="product-item-element list-item-ibo-price">
                                                        <div>
                                                            <div class="first-price">
                                                                <span class="label-wrapper">IBO Cost:</span>
                                                                <span class="value-wrapper">$1.99</span>
                                                                <div class="retail-price">
                                                                    <s>
                                                                        <span class="retail-price-strike-out">$2.00</span></s>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <span class="label-wrapper">Retail price:</span>
                                                                <span class="value-wrapper">$3.00</span></div>
                                                        </div>
                                                    </div>
                                                    <div class="product-item-element list-item-pv-bv">
                                                        <span class="total-pv-bv-label">PV/BV:</span>
                                                        <span class="value-wrapper">4.0/3.0</span></div>
                                                    <div class="qty">
                                                        <span class="qty-label">Qty:</span>
                                                        <span class="qty-value">1</span></div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="mini-cart-item">
                                            <div class="row product-wrapper">
                                                <div class="product-item-element list-item-image col-xs-3">
                                                    <div class="thumb">
                                                        <a href="">
                                                            <img src="${themeResourcePath}/images/heart-health-65.png" alt="G&amp;H Refresh+&amp;trade; Body Wash - Gel" title="G&amp;H Refresh+&amp;trade; Body Wash - Gel" /></a>
                                                    </div>
                                                </div>
                                                <div class="product-details col-xs-9">
                                                    <div class="name-wrapper">
                                                        <a class="name" href="">G&amp;H Refresh+™ Body Wash - Gel</a></div>
                                                    <div class="product-item-element list-item-ibo-price">
                                                        <div>
                                                            <div class="first-price">
                                                                <span class="label-wrapper">IBO Cost:</span>
                                                                <span class="value-wrapper">$8.73</span>
                                                                <div class="retail-price">
                                                                    <s>
                                                                        <span class="retail-price-strike-out">$8.80</span></s>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <span class="label-wrapper">Retail price:</span>
                                                                <span class="value-wrapper">$10.14</span></div>
                                                        </div>
                                                    </div>
                                                    <div class="product-item-element list-item-pv-bv">
                                                        <span class="total-pv-bv-label">PV/BV:</span>
                                                        <span class="value-wrapper">4.0/3.0</span></div>
                                                    <div class="qty">
                                                        <span class="qty-label">Qty:</span>
                                                        <span class="qty-value">1</span></div>
                                                </div>
                                            </div>
                                        </li>
                                    </ol>
                                    <div class="banner__component banner"></div>
                                    <div class="summary-block">
                                                    <span>There are
                                                        <span class="bold">18 items</span> in your cart</span></div>
                                    <div class="mini-cart-totals">
                                        <div class="key">ESTIMATED TOTAL</div>
                                        <div class="value">$1,616.79</div>
                                        <div class="key">TOTAL PV/BV</div>
                                        <div class="value">82.0 / 64.0</div></div>
                                    <div class="links">
                                        <div>
                                            <div class="cartPopupButtons"></div>
                                        </div>
                                    </div>
                                    <a href="shopping-cart.html" class="btn btn-primary btn-block mini-cart-checkout-button view-cart-button">VIEW CART</a></div>
                            </div>
                        </div>
                        <div id="dittoPopupContentPC" class="tab-pane fade"></div>
                    </div>
                </div>
                <div class="country-language-container header-content">
                    <div class="header-element-content">
                        <div class="site-logo">
                          <cms:pageSlot position="SiteLogo" var="component" element="div">
                              <cms:component component="${component}"/>
                          </cms:pageSlot>
                        </div>
                        <span class="dropdown">
                            <button id="countrySelectorDropdown" class="btn small dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <div class="wrapper">
                                    <span class="flag-icon flag-icon-US">
                                        <img class="topbar__flag" src="${themeResourcePath}/images/jp.jpg" alt="flag" /></span>
                                    <span class="current-country-code">Japan</span>
                                    <span class="icon icon-arrow-dropdown">
                                        <i class="location-arrow-down glyphicon glyphicon-menu-down"></i>
                                    </span>
                                </div>
                            </button>
                            <ul class="dropdown-menu countryBox" aria-labelledby="countrySelectorDropdown">
                                <li class="content-item">
                                    <a class="country-item-link" href="">
                                        <span class="flag-icon">
                                            <img class="topbar__flag" src="${themeResourcePath}/images/cn.jpg" alt="flag" /></span>
                                        <span class="country-item-text">China</span></a>
                                </li>
                                <li class="content-item">
                                    <a class="country-item-link" href="">
                                        <span class="flag-icon">
                                            <img class="topbar__flag" src="${themeResourcePath}/images/my.jpg" alt="flag" /></span>
                                        <span class="country-item-text">Malaysia</span></a>
                                </li>
                                <li class="content-item">
                                    <a class="country-item-link" href="">
                                        <span class="flag-icon">
                                            <img class="topbar__flag" src="${themeResourcePath}/images/ph.jpg" alt="flag" /></span>
                                        <span class="country-item-text">Philippines</span></a>
                                </li>
                                <li class="content-item">
                                    <a class="country-item-link" href="">
                                        <span class="flag-icon">
                                            <img class="topbar__flag" src="${themeResourcePath}/images/kr.jpg" alt="flag" /></span>
                                        <span class="country-item-text">Korea</span></a>
                                </li>
                                <li class="content-item">
                                    <a class="country-item-link" href="">
                                        <span class="flag-icon">
                                            <img class="topbar__flag" src="${themeResourcePath}/images/th.jpg" alt="flag" /></span>
                                        <span class="country-item-text">Thailand</span></a>
                                </li>
                                <li class="content-item">
                                    <a class="country-item-link" href="">
                                        <span class="flag-icon">
                                            <img class="topbar__flag" src="${themeResourcePath}/images/sg.jpg" alt="flag" /></span>
                                        <span class="country-item-text">Singapore</span></a>
                                </li>
                                <li class="content-item current-country">
                                    <a class="country-item-link" href="">
                                        <span class="flag-icon">
                                            <img class="topbar__flag" src="${themeResourcePath}/images/jp.jpg" alt="flag" /></span>
                                        <span class="country-item-text">Japan</span></a>
                                </li>
                            </ul>
                        </span>
                        <form id="lang-form" class="hidden" action="#" method="post">
                            <div class="form-group">
                                <select name="code" id="lang-selector" class="form-control select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                                    <option value="en" lang="en">English</option>
                                    <option value="es" lang="es">Espa&ntilde;ol</option></select>
                                <span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100px;">
                                    <span class="selection">
                                        <span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-labelledby="select2-lang-selector-container">
                                            <span class="select2-selection__rendered" id="select2-lang-selector-container" title="English">English</span>
                                            <span class="select2-selection__arrow" role="presentation">
                                                <b role="presentation"></b>
                                            </span>
                                        </span>
                                    </span>
                                    <span class="dropdown-wrapper" aria-hidden="true"></span>
                                </span>
                            </div>
                            <div>
                                <input type="hidden" name="CSRFToken" value="a2a5ec40-53df-43c9-87ae-c3e661dbd64d" /></div>
                        </form>
                        <div class="nav-links-container pos-relative">
                            <ul class="nav-list clearfix">
                                <li class=" nav-item">
                                    <a href="#" data-toggle="collapse" data-target="#login-drop-content" class="pos-relative img-nav-link js-my-account-menu opened collapsed">
                                        <img src="${themeResourcePath}/images/yui.png" alt="" width="34px" />
                                        <span class="message-num-1">24</span>
                                        <span class="user-name">Yui Mori</span>
                                        <span class="icon icon-arrow-dropdown"></span>
                                    </a>
                                </li>
                                <li class="componentContainer liOffcanvas nav-item nav-mini-cart">
                                    <span class="">
                                        <div class="nav-cart nav-cart-wrapper js-nav-cart-wrapper print-hide">
                                            <a href="#" class="pos-relative mini-cart-link js-mini-cart-link clearfix " data-toggle="collapse" data-target="#shoppingcar-drop-content">
                                                <div class="mini-cart-icon">
                                                    <span class="icon-shopping-cart"></span>
                                                </div>
                                                <div class="mini-cart-count js-mini-cart-count">
                                                    <span class="nav-items-total">16</span></div>
                                                <div class="cart-icon-wrapper">
                                                    <div class="mini-cart-arrow"></div>
                                                </div>
                                            </a>
                                            <div class="mini-cart-items-container js-mini-cart-items-container">

                                                <ul class="nav nav-tabs">
                                                    <li class="active">
                                                        <a data-toggle="tab" href="#miniShoppingCartPopupContent">Shopping Cart (
                                                            <span id="minicartTabTotalItems">0</span>)</a></li>
                                                    <li>
                                                        <a data-toggle="tab" href="#dittoPopupContent">My Ditto</a></li>
                                                </ul>
                                                <div class="tab-content js-tab-content">
                                                    <div id="miniShoppingCartPopupContent" class="tab-pane fade in active"></div>
                                                    <div id="dittoPopupContent" class="tab-pane fade"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mini-cart-container js-mini-cart-container"></div>
                                    </span>
                                </li>
                            </ul>

                        </div>
                    </div>
                </div>
                <div class="">
                    <div class="nav-links-container">
                        <ul class="nav-list clearfix"></ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div class="amway-theme">
        <nav class="print-hide amw-navigation-menu js-navigation--middle">
            <div class="navigation-menu-container">
                <div class="mobile-container">
                    <div class="mobile-wrapper">
                        <div class="mobile-header-cell navigation-menu-toggle-wrapper">
                            <a role="button" data-toggle="collapse" href="#mobile-menu-accordion" aria-expanded="false" aria-controls="mobile-menu-accordion" class="overlay-menu-toggle-mobile js-overlay-menu-toggle-mobile collapsed">
                                <span class="hamburger-icon icon-Icon-Hamburger-01"></span>
                            </a>
                            <div class="panel-group collapse overlay-mobile-menu js-overlay-mobile-menu js-remove-overlay-desktop-menu" id="mobile-menu-accordion" role="tablist" aria-multiselectable="true">
                                <li role="presentation" class=" nav-list-element">
                                    <div class="overlay-menu-component-mobile-wrapper">
                                        <div class="panel-group" id="mobile-menu-category-accordion" role="tablist" aria-multiselectable="true">
                                            <div class="panel panel-default overlay-menu-mobile__panel">
                                                <div class="panel-heading overlay-menu-mobile__panel__heading" role="tab" id="navMenuMobHeading0">
                                                    <h4 class="panel-title">
                                                        <a class="panel-toggle collapsed" role="button" data-toggle="collapse" data-parent="#mobile-menu-category-accordion" href="#navMenuMobCollapse0" aria-expanded="true" aria-controls="navMenuMobCollapse0">
                                                            <span class="title-element tab-image">
                                                                <img src="${themeResourcePath}/images/nutrition.png" /></span>
                                                            <span class="title-element active-parent-icon">
                                                                <span class="icon-chevron-left"></span>
                                                            </span>
                                                            <span class="title-element title-text">Nutrition</span>
                                                            <span class="title-element accordion-icon-wrapper">
                                                                <span class="pull-right icon-minus"></span>
                                                            </span>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="navMenuMobCollapse0" class="panel-collapse collapse overlay-menu-mobile__panel__content" role="tabpanel" aria-labelledby="navMenuMobHeading0">
                                                    <div class="panel-body">
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="Vitamins_and_Supplements_Product_listing_page.html" onclick="javascript:location.href='Vitamins_and_Supplements_Product_listing_page.html'" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-VitaminsSupplementsNavNode">
                                                                    <span class="subcategory-title">Vitamins &amp; Supplements</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-VitaminsSupplementsNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Supplements">Supplements</a></li>
                                                                    <li>
                                                                        <a href="#" title="Bone Health">Bone Health</a></li>
                                                                    <li>
                                                                        <a href="#" title="Energy">Energy</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-WeightManagementNavNode">
                                                                    <span class="subcategory-title">Weight Management</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-WeightManagementNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Multivitamins">Multivitamins</a></li>
                                                                    <li>
                                                                        <a href="#" title="Bar">Bar</a></li>
                                                                    <li>
                                                                        <a href="#" title="Shakes">Shakes</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-SportsNutrionNavNode">
                                                                    <span class="subcategory-title">Sports Nutrion</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-SportsNutrionNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Energize">Energize</a></li>
                                                                    <li>
                                                                        <a href="#" title="Hydrate">Hydrate</a></li>
                                                                    <li>
                                                                        <a href="#" title="Nutrilite Sports Nutrition">Nutrilite Sports Nutrition</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-EnergyDrinksNavNode">
                                                                    <span class="subcategory-title">Energy Drinks</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-EnergyDrinksNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Sugar Free">Sugar Free</a></li>
                                                                    <li>
                                                                        <a href="#" title="Low Carb">Low Carb</a></li>
                                                                    <li>
                                                                        <a href="#" title="Kosher">Kosher</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        
                                                        <div class="overlay-menu-subcategory tools-brands js-overlay-menu-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle tools-link" data-toggle="collapse" href="#navMenuMobBrandsCollapse-NutritionBrandsNavNode">
                                                                    <span class="subcategory-title">Shop by Brands</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobBrandsCollapse-NutritionBrandsNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#">truvinity.png</a></li>
                                                                    <li>
                                                                        <a href="#">Nutrilite</a></li>
                                                                    <li>
                                                                        <a href="#">BodyKey</a></li>
                                                                    <li>
                                                                        <a href="#">XS Energy Drink</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory view-all-link">
                                                            <h5>
                                                                <a href="categoryPage.html" class="subcategory-toggle tools-link">
                                                                    <span class="subcategory-title">All Nutrition</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory tools-brands">
                                                            <h5>
                                                                <a class="subcategory-toggle tools-link" data-toggle="collapse" href="#navMenuMobToolsCollapse-NutritionToolsNavNode" aria-expanded="false" aria-controls="navMenuMobToolsCollapseNutritionToolsNavNode">
                                                                    <span class="subcategory-title">Tools &amp; Advice</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobToolsCollapse-NutritionToolsNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Supplement Recommender">Skincare Recommender Ditto Scheduled Ordering</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default overlay-menu-mobile__panel">
                                                <div class="panel-heading overlay-menu-mobile__panel__heading" role="tab" id="navMenuMobHeading1">
                                                    <h4 class="panel-title">
                                                        <a class="panel-toggle collapsed" role="button" data-toggle="collapse" data-parent="#mobile-menu-category-accordion" href="#navMenuMobCollapse1" aria-expanded="true" aria-controls="navMenuMobCollapse1">
                                                            <span class="title-element tab-image">
                                                                <img src="${themeResourcePath}/images/beauty.png" /></span>
                                                            <span class="title-element active-parent-icon">
                                                                <span class="icon-chevron-left"></span>
                                                            </span>
                                                            <span class="title-element title-text">Beauty</span>
                                                            <span class="title-element accordion-icon-wrapper">
                                                                <span class="pull-right icon-minus"></span>
                                                            </span>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="navMenuMobCollapse1" class="panel-collapse collapse overlay-menu-mobile__panel__content" role="tabpanel" aria-labelledby="navMenuMobHeading1">
                                                    <div class="panel-body">
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-SkincareNavNode">
                                                                    <span class="subcategory-title">Skin Care</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-SkincareNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Cleansers">Cleansers</a></li>
                                                                    <li>
                                                                        <a href="#" title="Toners">Toners</a></li>
                                                                    <li>
                                                                        <a href="#" title="Moisturizers">Moisturizers</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-MakeupNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-MakeupNavNode">
                                                                    <span class="subcategory-title">Makeup</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-MakeupNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Accessories">Accessories</a></li>
                                                                    <li>
                                                                        <a href="#" title="Cheeks">Cheeks</a></li>
                                                                    <li>
                                                                        <a href="#" title="Eyes">Eyes</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory view-all-link">
                                                            <h5>
                                                                <a href="#" class="subcategory-toggle tools-link">
                                                                    <span class="subcategory-title">View All&nbsp;Beauty</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default overlay-menu-mobile__panel">
                                                <div class="panel-heading overlay-menu-mobile__panel__heading" role="tab" id="navMenuMobHeading2">
                                                    <h4 class="panel-title">
                                                        <a class="panel-toggle collapsed" role="button" data-toggle="collapse" data-parent="#mobile-menu-category-accordion" href="#navMenuMobCollapse2" aria-expanded="true" aria-controls="navMenuMobCollapse2">
                                                            <span class="title-element tab-image">
                                                                <img src="${themeResourcePath}/images/bath.png" /></span>
                                                            <span class="title-element active-parent-icon">
                                                                <span class="icon-chevron-left"></span>
                                                            </span>
                                                            <span class="title-element title-text">Bath &amp; Body</span>
                                                            <span class="title-element accordion-icon-wrapper">
                                                                <span class="pull-right icon-minus"></span>
                                                            </span>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="navMenuMobCollapse2" class="panel-collapse collapse overlay-menu-mobile__panel__content" role="tabpanel" aria-labelledby="navMenuMobHeading2">
                                                    <div class="panel-body">
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-HairCareNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-HairCareNavNode">
                                                                    <span class="subcategory-title">Hair Care</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-HairCareNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Conditioners">Conditioners</a></li>
                                                                    <li>
                                                                        <a href="#" title="Shampoos">Shampoos</a></li>
                                                                    <li>
                                                                        <a href="#" title="Styling &amp; Treatments">Styling &amp; Treatments</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-BodyCareNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-BodyCareNavNode">
                                                                    <span class="subcategory-title">Body Care</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-BodyCareNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Body Wash">Body Wash</a></li>
                                                                    <li>
                                                                        <a href="#" title="Spa">Spa</a></li>
                                                                    <li>
                                                                        <a href="#" title="Lotions">Lotions</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-OralCareNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-OralCareNavNode">
                                                                    <span class="subcategory-title">Oral Care</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-OralCareNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Toothpastes">Toothpastes</a></li>
                                                                    <li>
                                                                        <a href="#" title="Breath Sprays">Breath Sprays</a></li>
                                                                    <li>
                                                                        <a href="#" title="Brushes &amp; Floss">Brushes &amp; Floss</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory tools-brands">
                                                            <h5>
                                                                <a class="subcategory-toggle tools-link" data-toggle="collapse" href="#navMenuMobToolsCollapse-BathBodyToolsNavNode" aria-expanded="false" aria-controls="navMenuMobToolsCollapseBathBodyToolsNavNode">
                                                                    <span class="subcategory-title">Tools &amp; Advice</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobToolsCollapse-BathBodyToolsNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Ditto Scheduled Ordering">Ditto Scheduled Ordering</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory view-all-link">
                                                            <h5>
                                                                <a href="" class="subcategory-toggle tools-link">
                                                                    <span class="subcategory-title">View All&nbsp;Bath &amp; Body</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default overlay-menu-mobile__panel">
                                                <div class="panel-heading overlay-menu-mobile__panel__heading" role="tab" id="navMenuMobHeading3">
                                                    <h4 class="panel-title">
                                                        <a class="panel-toggle collapsed" role="button" data-toggle="collapse" data-parent="#mobile-menu-category-accordion" href="#navMenuMobCollapse3" aria-expanded="true" aria-controls="navMenuMobCollapse3">
                                                            <span class="title-element tab-image">
                                                                <img src="${themeResourcePath}/images/home.png" /></span>
                                                            <span class="title-element active-parent-icon">
                                                                <span class="icon-chevron-left"></span>
                                                            </span>
                                                            <span class="title-element title-text">Home</span>
                                                            <span class="title-element accordion-icon-wrapper">
                                                                <span class="pull-right icon-minus"></span>
                                                            </span>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="navMenuMobCollapse3" class="panel-collapse collapse overlay-menu-mobile__panel__content" role="tabpanel" aria-labelledby="navMenuMobHeading3">
                                                    <div class="panel-body">
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-LaundryNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-LaundryNavNode">
                                                                    <span class="subcategory-title">Laundry</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-LaundryNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Detergents">Detergents</a></li>
                                                                    <li>
                                                                        <a href="#" title="Treatments &amp; Additives">Treatments &amp; Additives</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-HomeCleanersNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-HomeCleanersNavNode">
                                                                    <span class="subcategory-title">Home Cleaners</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-HomeCleanersNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Surface Cleaners">Surface Cleaners</a></li>
                                                                    <li>
                                                                        <a href="#" title="Disinfectant Cleaners">Disinfectant Cleaners</a></li>
                                                                    <li>
                                                                        <a href="#" title="Dish Detergent">Dish Detergent</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-WaterTreatmentNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-WaterTreatmentNavNode">
                                                                    <span class="subcategory-title">Water Treatment</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-WaterTreatmentNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Purifiers">Purifiers</a></li>
                                                                    <li>
                                                                        <a href="#" title="Replacement Filters">Replacement Filters</a></li>
                                                                    <li>
                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-AirTreatmentNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-AirTreatmentNavNode">
                                                                    <span class="subcategory-title">Air Treatment</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-AirTreatmentNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Filtration">Filtration</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="#navMenuMobCategoryCollapse-CookwareNavNode" aria-expanded="false" aria-controls="navMenuMobCategoryCollapse-CookwareNavNode">
                                                                    <span class="subcategory-title">Cookware</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobCategoryCollapse-CookwareNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Cutlery">Cutlery</a></li>
                                                                    <li>
                                                                        <a href="#" title="Pans">Pans</a></li>
                                                                    <li>
                                                                        <a href="#" title="Replacement Parts">Replacement Parts</a></li>
                                                                    <li>
                                                                        <a href="#" class="overlay-tab-title">View All</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory tools-brands">
                                                            <h5>
                                                                <a class="subcategory-toggle tools-link" data-toggle="collapse" href="#navMenuMobToolsCollapse-HomeToolsNavNode" aria-expanded="false" aria-controls="navMenuMobToolsCollapseHomeToolsNavNode">
                                                                    <span class="subcategory-title">Tools &amp; Advice</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse" id="navMenuMobToolsCollapse-HomeToolsNavNode">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                        <a href="#" title="Ditto Scheduled Ordering">Ditto Scheduled Ordering</a></li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory view-all-link">
                                                            <h5>
                                                                <a href="" class="subcategory-toggle tools-link">
                                                                    <span class="subcategory-title">View All&nbsp;Home</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li role="presentation" class=" nav-list-element">
                                    <a href="dashboard.html" title="Start a Business">Business Center</a></li>
                                <li role="presentation" class=" nav-list-element">
                                    <a href="#" title="About Amway">About Amway</a></li>
                                    <li role="presentation" class=" nav-list-element">
                                    <a href="#" title="About Amway">Education</a>
                                </li>
                                <li role="presentation" class=" nav-list-element">
                                    <div class="overlay-menu-component-mobile-wrapper country">
                                        <div class="panel-group" id="mobile-menu-category-accordion" role="tablist" aria-multiselectable="true">
                                            <div class="panel panel-default overlay-menu-mobile__panel">
                                                <div class="panel-heading overlay-menu-mobile__panel__heading" role="tab" id="navMenuMobHeading4">
                                                    <h4 class="panel-title">
                                                        <a class="panel-toggle collapsed" role="button" data-toggle="collapse" data-parent="#mobile-menu-category-accordion" href="#navMenuMobCollapse4" aria-expanded="true" aria-controls="navMenuMobCollapse4">
                                                            <div id="closeHead">  
                                                                <span class="title-element tab-image">
                                                                    <img src="${themeResourcePath}/images/jp.jpg" /></span>
                                                                <span class="title-element active-parent-icon">
                                                                    <span class="icon-chevron-left"></span>
                                                                </span>
                                                                <span class="title-element title-text">Japan</span>
                                                                <span class="title-element accordion-icon-wrapper">
                                                                    <span class="pull-right icon-minus"></span>
                                                                </span>
                                                           </div>
                                                           <div id="openHead">
                                                               <span class="title-element title-text">COUNTRY</span>
                                                           </div>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="navMenuMobCollapse4" class="panel-collapse collapse overlay-menu-mobile__panel__content" role="tabpanel" aria-labelledby="navMenuMobHeading4">
                                                    <div class="panel-body">
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
                                                            <!--<h5>
                                                                <a class="subcategory-toggle" data-toggle="collapse" href="Vitamins_and_Supplements_Product_listing_page.html" onclick="javascript:location.href='Vitamins_and_Supplements_Product_listing_page.html'" aria-expanded="false" aria-controls="navMenuMobCountry">
                                                                    <span class="subcategory-title">COUNTRY</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>-->
                                                            <ul class="subcategory-list countryBox" id="navMenuMobCountry">

                                                                <li class="content-item">
                                                                    <a class="country-item-link" href="">
                                                                        <span class="flag-icon">
                                                                            <img class="topbar__flag" src="${themeResourcePath}/images/cn.jpg" alt="flag"></span>
                                                                        <span class="country-item-text">China</span></a>
                                                                </li>
                                                                <li class="content-item">
                                                                    <a class="country-item-link" href="">
                                                                        <span class="flag-icon">
                                                                            <img class="topbar__flag" src="${themeResourcePath}/images/my.jpg" alt="flag"></span>
                                                                        <span class="country-item-text">Malaysia</span></a>
                                                                </li>
                                                                <li class="content-item">
                                                                    <a class="country-item-link" href="">
                                                                        <span class="flag-icon">
                                                                            <img class="topbar__flag" src="${themeResourcePath}/images/ph.jpg" alt="flag"></span>
                                                                        <span class="country-item-text">Philippines</span></a>
                                                                </li>
                                                                <li class="content-item">
                                                                    <a class="country-item-link" href="">
                                                                        <span class="flag-icon">
                                                                            <img class="topbar__flag" src="${themeResourcePath}/images/kr.jpg" alt="flag"></span>
                                                                        <span class="country-item-text">Korea</span></a>
                                                                </li>
                                                                <li class="content-item">
                                                                    <a class="country-item-link" href="">
                                                                        <span class="flag-icon">
                                                                            <img class="topbar__flag" src="${themeResourcePath}/images/th.jpg" alt="flag"></span>
                                                                        <span class="country-item-text">Thailand</span></a>
                                                                </li>
                                                                <li class="content-item">
                                                                    <a class="country-item-link" href="">
                                                                        <span class="flag-icon">
                                                                            <img class="topbar__flag" src="${themeResourcePath}/images/sg.jpg" alt="flag"></span>
                                                                        <span class="country-item-text">Singapore</span></a>
                                                                </li>
                                                                <li class="content-item current-country">
                                                                    <a class="country-item-link" href="">
                                                                        <span class="flag-icon">
                                                                            <img class="topbar__flag" src="${themeResourcePath}/images/jp.jpg" alt="flag"></span>
                                                                        <span class="country-item-text">Japan</span></a>
                                                                </li>

                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </div>
                            <div class="overlay-mobile-menu-background js-overlay-mobile-menu-background"></div>
                        </div>
                        
                        <div class="mobile-header-cell mobile-nav-row-btn-logo">
                            <div class=" yComponentWrapper">
                                <div class="banner__component banner">
                                    <a href="homepage.html">
                                        <img title="" alt="" src="${themeResourcePath}/images/logo2.png" /></a>
                                </div>
                            </div>
                        </div>
                        <div class="mobile-header-cell search-btn-container">
                            <a class="mobile-search-btn js-toggle-xs-search print-hide">
                                <span class="icon icon-search"></span>
                            </a>
                        </div>
                        <div class="mobile-header-cell user-info-container">
                            <button class="mobile-user-btn js-toggle-xs-search print-hide" type="button">
                                <img src="${themeResourcePath}/images/yui.png" alt="" width="34px">
                                <span class="message-num-1">24</span>
                                <!--<span class="icon icon-user"></span>-->
                            </button>
                        </div>
                        <div class=" miniCartSlot componentContainer mobile-header-cell mobile-cart-container">
                            <div class="">
                                <div class="nav-cart nav-cart-wrapper js-nav-cart-wrapper print-hide">
                                    <a href="#" class="mini-cart-link js-mini-cart-link clearfix " data-mini-cart-url="" data-mini-cart-refresh-url="" data-mini-cart-name="Shopping Cart" data-mini-cart-empty-name="Empty Cart">
                                        <div class="mini-cart-icon ">
                                            <span class="nav-items-total">16</span>
                                            <span class="icon-shopping-cart"></span>

                                        </div>

                                        <div class="cart-icon-wrapper">
                                            <div class="mini-cart-arrow"></div>
                                        </div>
                                    </a>
                                    <!--remove min-cart-->
                                </div>
                                <div class="mini-cart-container js-mini-cart-container"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="desktop-nav-menu clearfix">
                    <div class="col-sm-12 col-sm-8 top-menu">
                        <div class="top-menu-links pull-left">
                        		<nav:topNavigation />
                        		<!-- 
                            <ul class="menu-nav-list print-hide">
                                <li role="presentation" class=" nav-list-item js-remove-overlay-mobile-menu">
                                    <div class="overlay-menu-component-desktop-wrapper">
                                        <a role="button" data-toggle="collapse" href="#overlay-menu-wrapper" aria-expanded="false" aria-controls="overlay-menu-wrapper" class="overlay-menu-toggle-desktop js-overlay-menu-toggle-desktop collapsed">
                                            <span class="hamburger-icon icon-Icon-Hamburger-01"></span>
                                            <span class="button-text">Shop
                                                <i class="main-menu__arrow-down glyphicon glyphicon-menu-down"></i></span>
                                        </a>
                                        <div id="overlay-menu-wrapper" class="collapse hidden-sm overlay-menu-container">
                                            <div class="overlay-menu row">
                                                <div class="col-sm-3 overlay-menu-headers">
                                                    <div class="menu-headers-container">
                                                        <div>
                                                            <ul class="tab-header-list" role="tablist">
                                                                <li role="presentation" class="tab-header-item active">
                                                                    <div class="arrow"></div>
                                                                    <a href="#navMenuTab0" aria-controls="navMenuTab0" role="tab" data-toggle="tab" class="tab-toggle">
                                                                        <div class="wrapper">
                                                                            <span class="tab-image">
                                                                                <img src="${themeResourcePath}/images/nutrition.png" /></span>
                                                                            <span class="tab-title">Nutrition</span></div>
                                                                    </a>
                                                                </li>
                                                                <li role="presentation" class="tab-header-item ">
                                                                    <div class="arrow"></div>
                                                                    <a href="#navMenuTab1" aria-controls="navMenuTab1" role="tab" data-toggle="tab" class="tab-toggle">
                                                                        <div class="wrapper">
                                                                            <span class="tab-image">
                                                                                <img src="${themeResourcePath}/images/beauty.png" /></span>
                                                                            <span class="tab-title">Beauty</span></div>
                                                                    </a>
                                                                </li>
                                                                <li role="presentation" class="tab-header-item ">
                                                                    <div class="arrow"></div>
                                                                    <a href="#navMenuTab2" aria-controls="navMenuTab2" role="tab" data-toggle="tab" class="tab-toggle">
                                                                        <div class="wrapper">
                                                                            <span class="tab-image">
                                                                                <img src="${themeResourcePath}/images/bath.png" /></span>
                                                                            <span class="tab-title">Bath &amp; Body</span></div>
                                                                    </a>
                                                                </li>
                                                                <li role="presentation" class="tab-header-item ">
                                                                    <div class="arrow"></div>
                                                                    <a href="#navMenuTab3" aria-controls="navMenuTab3" role="tab" data-toggle="tab" class="tab-toggle">
                                                                        <div class="wrapper">
                                                                            <span class="tab-image">
                                                                                <img src="${themeResourcePath}/images/home.png" /></span>
                                                                            <span class="tab-title">Home</span></div>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-9 tab-content">
                                                    <div role="tabpanel" class="tab-pane active" id="navMenuTab0">
                                                        <div class="overlay-menu-tab">
                                                            <div class="overlay-menu-tab-first-col">
                                                                <div class="overlay-menu__tab-wrapper">
                                                                    <div class="overlay-menu-tab-row">
                                                                        <div class="overlay-menu-tab-categories clearfix row">
                                                                            <div class="col-sm-3 tab__category">
                                                                                <div class="category-top-link" onclick="javascript:location.href='Vitamins_and_Supplements_Product_listing_page.html'">
                                                                                    <h6>Vitamins &amp; Supplements</h6></div>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Supplements">bone health</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Supplements">Children's Vitamins</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Supplements">Heart Health</a></li>
                                                                                    <li>
                                                                                        <a href="Vitamins_and_Supplements_Product_listing_page.html" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                                <a href="categoryPage.html" class="overlay-tab-title-bottom">more categories</a></div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>Weight Management</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Multivitamins">suplements</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Bar">Bar</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Shakes">Shakes</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>Sports Nutrion</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Energize">Energize</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Hydrate">Hydrate</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Nutrilite Sports Nutrition">Nutrilite Sports Nutrition</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>Energy Drinks</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Sugar Free">Kosher</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Low Carb">Sugar-Free</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Kosher">Low-Carb</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-brands">
                                                                        <div class="">
                                                                            <h6>Shop by Brands</h6>
                                                                            <div class="row images-container">
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="truvinity.png" alt="truvinity.png" src="${themeResourcePath}/images/nutrilite@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="Nutrilite" alt="Nutrilite" src="${themeResourcePath}/images/bodu-key@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="BodyKey" alt="BodyKey" src="${themeResourcePath}/images/truvivity@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="XS Energy Drink" alt="XS Energy Drink" src="${themeResourcePath}/images/layer-712@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-tools">
                                                                        <h6>Tools &amp; Advice</h6>
                                                                        <ul class="tools-list">
                                                                            <li class=" tools-item">
                                                                                <a href="#" title="Supplement Recommender">Skincare Recommender Ditto Scheduled Ordering</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class=" col-sm-3 overlay-menu__tab__banner">
                                                                <div class="banner__component banner">
                                                                    <a href="#">
                                                                        <img title="tab_banner.jpg" alt="tab_banner.jpg" src="./Checkout _ Lynx Site_files/tab-banner.jpg" /></a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane " id="navMenuTab1">
                                                        <div class="overlay-menu-tab">
                                                            <div class="overlay-menu-tab-first-col">
                                                                <div class="overlay-menu__tab-wrapper">
                                                                    <div class="overlay-menu-tab-row">
                                                                        <div class="overlay-menu-tab-categories clearfix row">
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>skin care</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="" title="Supplements">Anti Ageing Solutions</a></li>
                                                                                    <li>
                                                                                        <a href="" title="Energy">ARTISTRY™ Special Care</a></li>
                                                                                    <li>
                                                                                        <a href="" title="Energy">Daily Skincare</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                                <a href="categoryPage.html" class="overlay-tab-title-bottom">more categories</a></div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>colour</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="" title="Multivitamins">Applicators &amp; Accessories</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Bar">Eye &amp; Brows</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Shakes">Eyes &amp; Cheeks</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>makeup</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Energize">cheeks</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Hydrate">concealers</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Nutrilite Sports Nutrition">Foundations &amp; Powders</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>BEAUTY ACCESSORIES</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Sugar Free">Facial Cotton</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Low Carb">Brush Set</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Kosher">Powder Compact</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-brands">
                                                                        <div class="">
                                                                            <h6>Shop by Brands</h6>
                                                                            <div class="row images-container">
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="truvinity.png" alt="truvinity.png" src="${themeResourcePath}/images/artistry@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-tools">
                                                                        <h6>Tools &amp; Advice</h6>
                                                                        <ul class="tools-list">
                                                                            <li class=" tools-item">
                                                                                <a href="#" title="Supplement Recommender">Skincare Recommender Ditto Scheduled Ordering</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class=" col-sm-3 overlay-menu__tab__banner">
                                                                <div class="banner__component banner">
                                                                    <a href="#">
                                                                        <img title="tab_banner.jpg" alt="tab_banner.jpg" src="./Checkout _ Lynx Site_files/tab-banner.jpg" /></a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane " id="navMenuTab2">
                                                        <div class="overlay-menu-tab">
                                                            <div class="overlay-menu-tab-first-col">
                                                                <div class="overlay-menu__tab-wrapper">
                                                                    <div class="overlay-menu-tab-row">
                                                                        <div class="overlay-menu-tab-categories clearfix row">
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>hair care</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Supplements">Conditioners</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Energy">Shampoos</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Energy">Styling &amp; Treatments</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                                <a href="categoryPage.html" class="overlay-tab-title-bottom">more categories</a></div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>BODY CARE</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Multivitamins">Body Wash</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Bar">Deodorants</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Shakes">Lotions</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>ORAL CARE</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Energize">Breath Sprays</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Hydrate">Toothpastes</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Nutrilite Sports Nutrition">Brushes &amp; Floss</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>SUN AND TANNING</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Sugar Free">Sunscreen Lotion</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Low Carb">Self Tan Mousse</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-brands">
                                                                        <div class="">
                                                                            <h6>Shop by Brands</h6>
                                                                            <div class="row images-container">
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="truvinity.png" alt="truvinity.png" src="${themeResourcePath}/images/satinique@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="Nutrilite" alt="Nutrilite" src="${themeResourcePath}/images/suntime@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="BodyKey" alt="BodyKey" src="${themeResourcePath}/images/g-h@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="XS Energy Drink" alt="XS Energy Drink" src="${themeResourcePath}/images/glister@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-tools">
                                                                        <h6>Tools &amp; Advice</h6>
                                                                        <ul class="tools-list">
                                                                            <li class=" tools-item">
                                                                                <a href="#" title="Supplement Recommender">Quck Product Checklists Ditto Scheduled Ordering</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class=" col-sm-3 overlay-menu__tab__banner">
                                                                <div class="banner__component banner">
                                                                    <a href="#"></a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane " id="navMenuTab3">
                                                        <div class="overlay-menu-tab">
                                                            <div class="overlay-menu-tab-first-col">
                                                                <div class="overlay-menu__tab-wrapper">
                                                                    <div class="overlay-menu-tab-row">
                                                                        <div class="overlay-menu-tab-categories clearfix row">
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>HOUSEHOLD HYGEIENE</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Supplements">Kitchen Cleaner</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Energy">General Cleaning</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Energy">Bathroom Cleaner</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                                <a href="categoryPage.html" class="overlay-tab-title-bottom">more categories</a></div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>LAUNDRY</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Multivitamins">Detergents</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Bar">Treatment &amp; Additives</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Shakes">Lotions</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>WATER TREATMENT</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Energize">Purifiers</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Hydrate">Replacement Filters</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                            <div class="col-sm-3 tab__category">
                                                                                <h6>COOKWARE</h6>
                                                                                <ul class="category-links">
                                                                                    <li>
                                                                                        <a href="#" title="Sugar Free">Cutlery</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Low Carb">Pans</a></li>
                                                                                    <li>
                                                                                        <a href="#" title="Kosher">Replacement Parts</a></li>
                                                                                    <li>
                                                                                        <a href="" class="overlay-tab-title">View All</a></li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-brands">
                                                                        <div class="">
                                                                            <h6>Shop by Brands</h6>
                                                                            <div class="row images-container">
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="truvinity.png" alt="truvinity.png" src="${themeResourcePath}/images/amway-home@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="Nutrilite" alt="Nutrilite" src="${themeResourcePath}/images/legacy-of-clean@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="BodyKey" alt="BodyKey" src="${themeResourcePath}/images/e-spring@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                                <div class=" col-md-3">
                                                                                    <div class="banner__component banner">
                                                                                        <a href="#">
                                                                                            <img title="XS Energy Drink" alt="XS Energy Drink" src="${themeResourcePath}/images/i-cook@2x.png" /></a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-tools">
                                                                        <h6>Tools &amp; Advice</h6>
                                                                        <ul class="tools-list">
                                                                            <li class=" tools-item">
                                                                                <a href="#" title="Supplement Recommender">Quck Product Checklists Ditto Scheduled Ordering</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class=" col-sm-3 overlay-menu__tab__banner">
                                                                <div class="banner__component banner">
                                                                    <a href="#">
                                                                        <img title="tab_banner.jpg" alt="tab_banner.jpg" src="./Checkout _ Lynx Site_files/tab-banner.jpg" /></a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li role="presentation" class=" nav-list-item js-remove-overlay-mobile-menu">
                                    <a href="dashboard.html" title="">Business Center</a></li>
                                <li role="presentation" class=" nav-list-item js-remove-overlay-mobile-menu">
                                    <a href="#" title="About Amway">About Amway</a></li>
                                <li role="presentation" class=" nav-list-item js-remove-overlay-mobile-menu">
                                    <a href="#" title="About Amway">Education</a></li>
                            </ul>
                            -->
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-4 top-search print-hide">
                        <div class="site-search">
                            <div class="ui-front">
                                <form name="search_form_SearchBox" method="get" action="">
                                    <div class="input-group">
                                        <input type="text" class="form-control js-site-search-input ui-autocomplete-input" name="text" value="" maxlength="100" placeholder="Search" data-options="{&quot;autocompleteUrl&quot; : &quot;/lynxstorefront/lynx/en/search/autocomplete/SearchBox&quot;,&quot;minCharactersBeforeRequest&quot; : &quot;2&quot;,&quot;waitTimeBeforeRequest&quot; : &quot;500&quot;,&quot;displayProductImages&quot; : true}" autocomplete="off" />
                                        <span class="input-group-btn">
                                            <a class="btn btn-link" type="submit">
                                                <span class="icon-search"></span>
                                            </a>
                                        </span>
                                    </div>
                                </form>
                                <ul class="ui-autocomplete ui-front ui-menu ui-widget ui-widget-content" id="ui-id-1" tabindex="0" style="display: none;"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <!--search suggest-->
    <div class="popover auto-suggestion-popover fade bottom in" role="tooltip" id="">
        <div class="popover-arrow arrow" style="left: 71.9353%;"></div>
        <h3 class="popover-title" style="display: none;"></h3>
        <div class="popover-content">
            <button class="search-results-close">x</button>
            <ul>
                <div class="category-wrapper js-category-wrapper suggested-words-wrapper js-suggested-words-wrapper js-search-words-wrapper">
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="name">
                                <span class="js-name"></span>
                                <span class="js-start-of-name"></span>
                                <span class="bold js-highlited-part-of-name">nutri</span>
                                <span class="js-rest-of-name">cion</span></div>
                        </a>
                    </li>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="name">
                                <span class="js-name"></span>
                                <span class="js-start-of-name"></span>
                                <span class="bold js-highlited-part-of-name">nutri</span>
                                <span class="js-rest-of-name">lite</span></div>
                        </a>
                    </li>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="name">
                                <span class="js-name"></span>
                                <span class="js-start-of-name"></span>
                                <span class="bold js-highlited-part-of-name">nutri</span>
                                <span class="js-rest-of-name">plant</span></div>
                        </a>
                    </li>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="name">
                                <span class="js-name"></span>
                                <span class="js-start-of-name"></span>
                                <span class="bold js-highlited-part-of-name">nutri</span>
                                <span class="js-rest-of-name">tion</span></div>
                        </a>
                    </li>
                </div>
                <div class="category-wrapper js-category-wrapper products-wrapper hidden-xs hidden-sm">
                    <div class="header row">
                        <div class="title col-md-6">
                            <span>Products</span></div>
                        <div class="view-all-container col-md-6">
                            <a class="js-products-view-all-results" href="">View all results</a></div>
                    </div>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="thumb">
                                <img src="${themeResourcePath}/images/1.jpeg" /></div>
                            <div class="name">Nutrilite Vitamin C</div></a>
                    </li>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="thumb">
                                <img src="${themeResourcePath}/images/2.jpeg" /></div>
                            <div class="name">Nutrilite Women’s Pack</div></a>
                    </li>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="thumb">
                                <img src="${themeResourcePath}/images/3.jpeg" /></div>
                            <div class="name">Nutrilite Cal Mag D</div></a>
                    </li>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="thumb">
                                <img src="${themeResourcePath}/images/4.jpeg" /></div>
                            <div class="name">Nutrilite Double X 31</div></a>
                    </li>
                    <li class="ui-menu-item">
                        <a href="">
                            <div class="thumb">
                                <img src="${themeResourcePath}/images/5.jpeg" /></div>
                            <div class="name">Nutrilite dietary</div></a>
                    </li>
                </div>
            </ul>
        </div>
    </div>
    <!--search end-->

</header>


<cms:pageSlot position="BottomHeaderSlot" var="component" element="div"	class="container-fluid">
	<cms:component component="${component}" />
</cms:pageSlot>
