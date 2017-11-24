<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/responsive/category"%>

<template:page pageTitle="${pageTitle}">
	<div class="container-fluid main-container">
		<product:productDetailsPanel />
		<product:productPageTabs />
		<div class="recommendation swatches-description__margin-minus">
			<img class="recommendation__image" src="${themeResourcePath}/images/b_spot_swatch.png">
			<div class="recommendation__message">
				<blockquote class="recommendation__message-main">
					Selling Artistry's top-of-the-
					<br>
					line bundles helped drive my
					<br>
					business - and helped make
					<br>
					me reach Platinum.
				</blockquote>
				<p class="recommendation__message-signature">Platinum ABO: Karen Martin</p>
				<a class="recommendation__message-link" href="#">
					learn more
					<span class="recommendation__message-icon glyphicon glyphicon-menu-right"></span>
				</a>
			</div>
		</div>
		<div class="row simpleimagecomponent pcp-banner new-advice block">
			<div class="amway-theme col-xs-12">
				<div class="three-tile-component">
					<div class="carousel-title-container">
						<cms:pageSlot position="ToolsAndAdviceProductDetailSlot" var="component">
							<cms:component component="${component}" />
						</cms:pageSlot>
					</div>
					<div class="row">
						<div class="item col-xs-12 col-md-4">
							<div class="full-width-item second-option">
								<cms:pageSlot position="ToolsAndAdviceBannerProductDetailSlot1" var="component1">
									<cms:component component="${component1}" />
								</cms:pageSlot>
							</div>
						</div>
						<div class="item col-xs-12 col-md-4">
							<div class="full-width-item second-option">
								<cms:pageSlot position="ToolsAndAdviceBannerProductDetailSlot2" var="component2">
									<cms:component component="${component2}" />
								</cms:pageSlot>
							</div>
						</div>
						<div class="item col-xs-12 col-md-4">
							<div class="full-width-item second-option">
								<cms:pageSlot position="ToolsAndAdviceBannerProductDetailSlot3" var="component3">
									<cms:component component="${component3}" />
								</cms:pageSlot>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<cms:pageSlot position="UpSelling" var="comp">
			<cms:component component="${comp}" />
		</cms:pageSlot>

		<div class="row simpleimagecomponent pcp-banner">
			<div class="amway-theme col-xs-12">
				<div class="three-tile-component">
					<cms:pageSlot position="LearningOpportunitiesProductDetailSlot" var="component4">
						<cms:component component="${component4}" />
					</cms:pageSlot>


					<cms:pageSlot position="LearningOpportunitiesBannerProductDetailSlot1" var="component5">
						<cms:component component="${component5}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
		<category:recentlyViewedProducts />
	</div>

	<div class="cart-popup modal fade" id="product-zoom-image-panel" tabindex="-1" role="dialog"></div>
	<div class="cart-popup modal fade" id="product-zoom-image-panel" tabindex="-1" role="dialog"
		aria-labelledby="cartModalLabel" aria-hidden="true"></div>
	<product:productShoppingListPopups />
	<product:productDittoPopups />
</template:page>