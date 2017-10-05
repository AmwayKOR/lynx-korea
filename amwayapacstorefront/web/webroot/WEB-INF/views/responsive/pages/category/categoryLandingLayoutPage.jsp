<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<template:page pageTitle="${pageTitle}">

            <div class="product-category-page amway-theme">
                <div class="product-categories">
                    <div class="amway-theme">
                        <div class="full-width-banner-component banner-left-align">
                            <div class="banner-image-wrap full-width-banner">
                                <img class="desktop-image" src="${themeResourcePath}/images/category_banner_image.png" alt="Nutrilite Brand Image" />
                                <img class="desktop-image-mob" src="${themeResourcePath}/images/category_banner_image_mobile.png" alt="banner" /></div>
                            <div class="banner-content-wrap">
                                <div class="banner-content">
                                    <div class="banner-title-wrapper">
                                        <h2 class="banner-title">
                                            <img class="category-banner__icon" src="${themeResourcePath}/images/nutrition-icon.png" alt="icon" />
                                            <span>Nutrilite</span></h2>
                                        <div class="sub-title-wrap">
                                            <span class="banner-sub-title">
                                                <span>Duis autem vel eum iriure dolor in hendrerit in diam vulputate lorem velit esse molestie consequat</span></span>
                                        </div>
                                    </div>
                                    <div class="banner-button-wrap"> <a href="#" class="banner-button-link">
                                        <button class="btn-blue-white">
                                            <span class="btn-text">view products</span></button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid main-container">
                <div class="row ">
                    <div class="col-md-12 ">
                    	<cms:pageSlot position="ShopByCategoryContentSlot" var="component">
                    		<cms:component component="${component}" />
                    	</cms:pageSlot>
                    </div>
                </div>
            </div>
            <div class="experience-brands clearfix">
                <div class="new-hot-brand">
                    <div class="amway-theme">
                        <div class="full-width-title-component">
                            <h2 class="full-width-title-component__title">EXperience our brands</h2>
                            <h4 class="full-width-title-component__sub-title">Lorem ipsum dolor sit amet adipiscing elit sed diam nonummy</h4></div>
                    </div>
                    <div class="experience-brands__content">
                        <div class="wrapper">
                            <div class="banner-content">
                                <div class="banner-title-wrapper">
                                    <h2 class="banner-title">
                                        <span>Nutrilite headline
                                            <br />goes here</span></h2>
                                    <div class="sub-title-wrap">
                                        <span class="banner-sub-title">
                                            <span>Duis autem vel eum iriure dolor in hendrerit
                                                <br />in vulputate velit esse molestie consequat</span></span>
                                    </div>
                                </div>
                                <div class="banner-button-wrap">
                                    <a href="#" class="banner-button-link ">
                                        <button class="btn-blue-white">
                                            <span class="btn-text">shop now</span></button>
                                    </a>
                                </div>
                            </div>
                            <img class="experience-brands__image" src="${themeResourcePath}/images/experience-brands-images.png" alt="new-hot-brand" /></div>
                    </div>
                </div>
            </div>
            <div class="container-fluid main-container">
                <div class="category-success-story row">
                    <div class="success-story__item col-sm-12 col-md-6">
                        <div class="item-image-wrap">
                            <img alt="image" class="desktop-image" src="${themeResourcePath}/images/category_success_story_01.png" /></div>
                        <div class="item-content-wrap">
                            <h2 class="banner-title">
                                <span>New body key</span></h2>
                            <h4 class="banner-sub-title">Try One, Share a Few</h4>
                            <div class="sub-title-wrap">
                                <span class="banner-sub-title">Lorem ipsum dolor sit amet
                                    <br />consectetur adipiscing elit.</span></div>
                            <div class="banner-button-wrap">
                                <a href="#" class="banner-button-link ">
                                    <button class="btn-blue-white">
                                        <span class="btn-text">learn more</span></button>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="success-story__item col-sm-12 col-md-6">
                        <div class="item-image-wrap">
                            <img alt="image" class="desktop-image" src="${themeResourcePath}/images/category_success_story_02.png" /></div>
                        <div class="item-content-wrap">
                            <h2 class="banner-title">
                                <span>xs sports</span></h2>
                            <h4 class="banner-sub-title">Fuel Your Adventure</h4>
                            <div class="sub-title-wrap">
                                <span class="banner-sub-title">Lorem ipsum dolor sit amet
                                    <br />consectetur adipiscing elit.</span></div>
                            <div class="banner-button-wrap">
                                <a href="#" class="banner-button-link ">
                                    <button class="btn-blue-white">
                                        <span class="btn-text">learn more</span></button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row simpleimagecomponent pcp-banner new-advice block">
                    <div class="amway-theme col-xs-12">
                        <div class="three-tile-component">
                                <cms:pageSlot position="ToolsAndAdviceContentSlot" var="component">
                    				<cms:component component="${component}" />
                    			</cms:pageSlot>
                            <div class="row">
                                <div class="item col-xs-12 col-md-4">
                                	<cms:pageSlot position="ToolsAndAdviceBannerContentSlot1" var="component">
                    					<cms:component component="${component}" />
                    				</cms:pageSlot>
                                </div>
                                <div class="item col-xs-12 col-md-4">
                                	<cms:pageSlot position="ToolsAndAdviceBannerContentSlot2" var="component">
                    					<cms:component component="${component}" />
                    				</cms:pageSlot>
                                </div>
                                <div class="item col-xs-12 col-md-4">
                                	<cms:pageSlot position="ToolsAndAdviceBannerContentSlot3" var="component">
                    					<cms:component component="${component}" />
                    				</cms:pageSlot>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row product-recentlyviewed">
                    <div class="col-sm-12 column product-description__section">
                        <div class="product-title-container">
                            <h2 class="amway-learning__title">
                                <span class="mH">Your</span>Recently Viewed
                                <span class="mH">Items</span></h2>
                        </div>
                        <div class="product-recentlyviewed__imagelist" id="recentlyViewedListTab">
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/vitamin-c.png" alt="vitamin-c" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/lip-gloss.png" alt="lip-gloss" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/protein-powder-sm.png" alt="protein-powder-sm" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/truvivity.png" alt="truvivity" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/perfect-pack.png" alt="perfect-pack" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/cleanser.png" alt="cleanser" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/blush.png" alt="blush" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/blush.png" alt="blush" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/perfect-pack.png" alt="perfect-pack" /></div>
                            <div>
                                <img class="product-recentlyviewed__img" src="${themeResourcePath}/images/lip-gloss.png" alt="lip-gloss" /></div>
                        </div>
                    </div>
                </div>
            </div>
</template:page>