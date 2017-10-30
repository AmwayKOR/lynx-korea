<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="header-margin hidden-sm hidden-xs"></div>
<div class="container-fluid main-container">
    <div class="row">
        <div class="title-product-replacement-container">
            <div class="col-sm-12 col-md-12 pdp-info page-title-container">
                <div class="product-details page-title">
                    <div class="name">${fn:escapeXml(product.name)}</div>
                    <div class="item-code">
                        <span><spring:theme code="product.item.number.label" /></span>
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
                            <div class="description">${product.summary}</div>
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