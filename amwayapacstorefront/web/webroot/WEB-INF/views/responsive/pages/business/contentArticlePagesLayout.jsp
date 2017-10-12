<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/responsive/business"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
	<div class="container-fluid main-container ">
		<div class="print-hide breadcrumb-section">
			<ol class="breadcrumb">
				<li><a href="homepage.html">Home</a></li>
				<li><a href="dashboard.html">My Business Center</a></li>
				<li class="active">New IBO Info</li>
			</ol>
		</div>
	</div>

	<div class="product-category-page amway-theme">
		<div class="product-categories">
			<div class="amway-theme">
				<cms:pageSlot position="PathwayToSuccessContentSlot" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>

	<div class="experience-brands clearfix clear-border">
		<div class="new-hot-brand">
			<div class="experience-brands__content clear-bg bg-gray">
				<cms:pageSlot position="BuildYourBusinessContentSlot"
					var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>

	<div class="experience-brands clearfix clear-border">
		<div class="new-hot-brand">
			<div class="experience-brands__content bg-white clear-bg">
				<cms:pageSlot position="ExperienceTheStarterKitContentSlot"
					var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>

	<div class="container-fluid main-container">
		<div class="category-success-story row">
			<div class="success-story__item col-sm-12 col-md-6">
				<cms:pageSlot position="ArticleSuccessStoryContentSlot1"
					var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
			<div class="success-story__item col-sm-12 col-md-6">

				<cms:pageSlot position="ArticleSuccessStoryContentSlot2"
					var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>
</template:page>