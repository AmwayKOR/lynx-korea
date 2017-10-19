<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid main-container">
    <div class="print-hide breadcrumb-section">
        <ol class="breadcrumb">
            <li>
                <a href="homepage.html">Home</a></li>
            <!--<li>-->
                <!--<a href="categoryPage.html">Amway Catalog</a></li>-->
            <li>
                <a href="Vitamins_and_Supplements_Product_listing_page.html">Beauty</a></li>
            <li>
                <a href="Vitamins_and_Supplements_Product_listing_page.html">Makeup</a></li>
            <li>Lips</li></ol>
    </div>
</div>
<div class="header-margin hidden-sm hidden-xs"></div>
<div class="container-fluid main-container">
    <div class="row">
        <div class="title-product-replacement-container">
            <div class="col-sm-12 col-md-12 pdp-info page-title-container">
                <div class="product-details page-title">
                    <div class="name">${fn:escapeXml(product.name)}</div>
                    <div class="item-code">
                        <span>Item #</span>
                        <span class="code">${fn:escapeXml(product.code)}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="mg-product">
            <product:productImagePanel galleryImages="${galleryImages}" />
            <div class="clearfix hidden-sm hidden-md hidden-lg print-hide"></div>
            <div class="col-sm-12 col-md-6 pdp-info">
                <div class="product-main-info">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="description">${product.summary}
                                <a href="#">Read More</a></div>

                                <cms:pageSlot position="ColorVariantSelector" var="component">
                                    <cms:component component="${component}" />
                                </cms:pageSlot>
                            <hr class="description__line">
                            <div class="description-vote">
                                <img src="images/five_star.png" alt="star">
                                <a href="#reviewsbody">
                                <span class="description-number">4.0</span>
                                <span class="description-reviews">(32 Reviews)</span>
                                </a>
                            </div>
                            <product:productPricePanel product="${product}" />
                        </div>
						<div class="col-sm-12 col-md-12">
						    <div class="page-details-variants-select">
						        <div class="page-details-add-to-cart-component">
						            <div class="addtocart-component">
				                        <cms:pageSlot position="VariantSelector" var="component">
				                            <cms:component component="${component}" />
				                        </cms:pageSlot>
				                        <cms:pageSlot position="AddToCart" var="component" element="div" class="page-details-variants-select">
				                            <cms:component component="${component}" element="div" class="yComponentWrapper page-details-add-to-cart-component"/>
				                        </cms:pageSlot>
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
<div class="overlay"></div>
<div class="cbox addToYourShoppingCart">
            <div class="cart-popup__dialog">
                <div class="cart-popup__header">
                    <span class="cart-popup__header-text">added to your shopping cart
                        <img class="cart-popup__close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
                <div class="cart-popup__content">
                    <div class="cart-popup__item-info amwahover">
                        <img src="${themeResourcePath}/images/heart-health180.png" class="cart-popup__thumbnail" alt="product">
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
                        <a class="cart-popup__item-link-text " href="javascript:void(0);">Continue Shopping</a></div>
                </div>
            </div>
        </div>