<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<template:page pageTitle="${pageTitle}" >
	<div id="about-amway" class="extra2">
		<div class="container-fluid main-container ">
	        <h1 class="product-list-page-title shopping-list-page-title mb25">
	       		<cms:pageSlot position="AboutAmwayPageTitleContentSlot1" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
	        </h1>
	    </div>
		<div class="row">
			<!-- Video Preview-->
			<cms:pageSlot position="VideoPreviewAboutAmwayContentSlot1" var="component" element="div">
				<cms:component component="${component}" />
			</cms:pageSlot>
		</div>
		<div class="experience-brands clearfix clear-border">
	        <div class="new-hot-brand">
	            <div class="experience-brands__content clear-bg">
					<cms:pageSlot position="OurCompanyAboutAmwayContentSlot1" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
	            </div>
	        </div>
	    </div>
	    <div class="container-fluid main-container content-article-bottom">
	        <div class="category-success-story row">
	            <div class="success-story__item col-sm-12 col-md-6">
					<cms:pageSlot position="SuccessStoryAboutAmwayContentSlot1" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
	            </div>
	            <div class="success-story__item col-sm-12 col-md-6">
					<cms:pageSlot position="SuccessStoryAboutAmwayContentSlot2" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
	            </div>
	        </div>
	    </div>
	    <div class="container-fluid main-container about-amway-content">
	        <div class="row">
	            <div class="row simpleimagecomponent pcp-banner new-advice block">
	                <div class="amway-theme col-xs-12">
	                    <div class="three-tile-component">
	                        <div class="row">
	                            <div class="item col-xs-12 col-md-4">
	                                <div class="full-width-item second-option">
										<cms:pageSlot position="ToolsAndAdviceBannerAboutAmwayContentSlot1" var="component">
											<cms:component component="${component}" />
										</cms:pageSlot>
	                                </div>
	                            </div>
	                            <div class="item col-xs-12 col-md-4">
	                                <div class="full-width-item second-option">
										<cms:pageSlot position="ToolsAndAdviceBannerAboutAmwayContentSlot2" var="component">
											<cms:component component="${component}" />
										</cms:pageSlot>
	                                </div>
	                            </div>
	                            <div class="item col-xs-12 col-md-4">
	                                <div class="full-width-item second-option">
										<cms:pageSlot position="ToolsAndAdviceBannerAboutAmwayContentSlot3" var="component">
											<cms:component component="${component}" />
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
</template:page>