<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/responsive/category" %>

<template:page pageTitle="${pageTitle}">
            <div class="container-fluid main-container">
                <div class="print-hide breadcrumb-section">
                    <ol class="breadcrumb">
                        <li>
                            <a href="homepage.html">Home</a></li>
                        <!--<li>-->
                            <!--<a href="#">Amway Catalog</a></li>-->
                        <li>
                            <a href="categoryPage.html">Nutrition</a></li>
                        <li class="active">Vitamins & Supplements</li></ol>
                </div>
            </div>
            <div class="container-fluid main-container new-plp">
                <div class="row">
                    <h1 class="col-sm-12 product-list-page-title">${categoryName}</h1>
                    <div class="col-xs-3 print-hide">
                    	<category:productListDesktopFacetSection searchPageData="${searchPageData}"/>
                    </div>
                    <div class="col-sm-12 col-md-9">
                        <!-- Product List -->
                        <div class="new-product-list-right-slot product-list-right-slot product-list row">
                            <div class="product__list--wrapper yComponentWrapper product-list-right-component">
                                <category:productListingTopRightSection searchPageData="${searchPageData}"/>
                                <category:productListSection searchPageData="${searchPageData}"/>
                                <category:productListerPaginationSection/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		<div class="overlay"></div>
		<!--quick view section -->
        <div class="view-box"></div>
        
        <!-- add to cart popup section -->
        <div id="add-to-cart-box"class="cbox">
            <div class="cart-popup__dialog">
                <div class="cart-popup__header">
                    <span class="cart-popup__header-text">added to your shopping cart
                        <img class="cart-popup__close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
                <div class="cart-popup__content">
                    <div class="cart-popup__item-info amwahover">
                        <img src="${themeResourcePath}/images/lo-c-bundle.png" class="cart-popup__thumbnail" alt="product">
                        <div class="cart-popup__item-detail">
                            <p class="cart-popup__item-title">Nutrilite® Heart Health Pack Has a Long Name – Wraps to Two Lines</p>
                            <p class="cart-popup__item-count">60 Count</p>
                            <span class="cart-popup__item-number">Item #: 116991</span>
                            <div class="cart-popup__item-title cart-popup__item-aboprice">
                                <span>ABO Price:</span>
                                <span class="product-list__item-abovalue">$16.62</span></div>
                            <div class="cart-popup__item-retailprice">
                                <span>Retail Price:</span>
                                <span class="product-list__item-abovalue">$25.55</span></div>
                            <div class="cart-popup__item-retailprice">
                                <span>PV / BV:</span>
                                <span class="product-list__item-abovalue">4.50 / 14.21</span></div>
                            <div class="cart-popup__quantity cart-popup__item-retailprice">
                                <span class="cart-popup__qty">Qty: 1</span>
                                <a>Edit</a>
                            </div>
                        </div>
                    </div>
                    <div class="cart-popup__item-link">
                        <a href="Checkout-1-shippingAndDelivery.html" class="btn-blue-white">check out</a>
                        <a class="cart-popup__item-link-text" href="Vitamins_and_Supplements_Product_listing_page.html">Continue Shopping</a></div>
                </div>
            </div>
        </div>
</template:page>