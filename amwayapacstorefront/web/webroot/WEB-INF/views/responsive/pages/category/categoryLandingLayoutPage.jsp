<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/responsive/category"%>

<template:page pageTitle="${pageTitle}">

	<div class="product-category-page amway-theme">
		<div class="product-categories">
			<div class="amway-theme">
				<div class="full-width-banner-component banner-left-align">
					<cms:pageSlot position="TopBannerContentSlot" var="feature">
						<cms:component component="${feature}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid main-container">
		<div class="row ">
			<div class="col-md-12 ">
				<div class="row simpleimagecomponent">
					<div class="amway-theme">
						<div class="full-width-title-component">
							<cms:pageSlot position="ShopByCategoryTitleSlot" var="component">
								<cms:component component="${component}" />
							</cms:pageSlot>
						</div>
					</div>
				</div>
				<div class="simpleimagecomponent pcp-prod row">
					<cms:pageSlot position="ShopByCategoryContentSlot" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
	</div>

	<div class="experience-brands clearfix">
		<div class="new-hot-brand">
			<div class="amway-theme">
				<div class="full-width-title-component">
					<cms:pageSlot position="ExperienceBrandsContentSlot" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</div>
			</div>
			<cms:pageSlot position="BrandBackgroundImageBannerContentSlot" var="component">
				<cms:component component="${component}" />
			</cms:pageSlot>
		</div>
	</div>

	<div class="container-fluid main-container">
		<div class="category-success-story row">
			<div class="success-story__item col-sm-12 col-md-6">
				<cms:pageSlot position="SuccessStoryContentSlot1" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>

			<div class="success-story__item col-sm-12 col-md-6">
				<cms:pageSlot position="SuccessStoryContentSlot2" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>

		<div class="row simpleimagecomponent pcp-banner new-advice block">
			<div class="amway-theme col-xs-12">
				<div class="three-tile-component">
					<div class="carousel-title-container">
						<cms:pageSlot position="ToolsAndAdviceContentSlot" var="component">
							<cms:component component="${component}" />
						</cms:pageSlot>
					</div>
					<div class="row">
						<div class="item col-xs-12 col-md-4">
							<div class="full-width-item second-option">
								<cms:pageSlot position="ToolsAndAdviceBannerContentSlot1" var="component">
									<cms:component component="${component}" />
								</cms:pageSlot>
							</div>
						</div>
						<div class="item col-xs-12 col-md-4">
							<div class="full-width-item second-option">
								<cms:pageSlot position="ToolsAndAdviceBannerContentSlot2" var="component">
									<cms:component component="${component}" />
								</cms:pageSlot>
							</div>
						</div>
						<div class="item col-xs-12 col-md-4">
							<div class="full-width-item second-option">
								<cms:pageSlot position="ToolsAndAdviceBannerContentSlot3" var="component">
									<cms:component component="${component}" />
								</cms:pageSlot>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<cms:pageSlot position="RecentlyViewedItemCarousel" var="comp">
            <cms:component component="${comp}" />
        </cms:pageSlot>
	</div>
</template:page>