<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">
	<cms:pageSlot position="Section1" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>

	<div class="container-fluid main-container">
		<div class="amway-theme subTitle">
			<div class="full-width-title-component">
				<cms:pageSlot position="Section2" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>

		<div class="category-success-story row">
			<div class="success-story__item col-sm-12 col-md-6">
				<cms:pageSlot position="Section3A" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
			<div class="success-story__item col-sm-12 col-md-6">
				<cms:pageSlot position="Section3B" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>
	<div class="new-hot-brand">
		<div class="amway-theme">
			<div class="full-width-title-component">
				<div class="full-width-title-component">
					<cms:pageSlot position="Section4" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
		<cms:pageSlot position="Section5" var="component">
			<cms:component component="${component}" />
		</cms:pageSlot>

	</div>
	<div class="container-fluid main-container">
		<div class="featured-product no-border col-sm-12">
			<div>
				<cms:pageSlot position="Section6A" var="component" element="div">
					<cms:component component="${component}" />
				</cms:pageSlot>
				<cms:pageSlot position="Section6B" var="component" element="div">
					<cms:component component="${component}" />
				</cms:pageSlot>
				<cms:pageSlot position="Section6C" var="component" element="div">
					<cms:component component="${component}" />
				</cms:pageSlot>

			</div>
		</div>
		<div class="row">
			<cms:pageSlot position="Section7" var="component" element="div">
				<cms:component component="${component}" />
			</cms:pageSlot>
		</div>
		<div class="row">
			<!-- featured Product -->
			<div class="featured-product no-border col-sm-12">
				<h4 class="featured-product__slogan">
					<cms:pageSlot position="Section8" var="component" element="div">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</h4>
				<div>
					<cms:pageSlot position="Section9A" var="component" element="div">
						<cms:component component="${component}" />
					</cms:pageSlot>
					<cms:pageSlot position="Section9B" var="component" element="div">
						<cms:component component="${component}" />
					</cms:pageSlot>
					<cms:pageSlot position="Section9C" var="component" element="div">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- Video Preview-->
			<cms:pageSlot position="Section10" var="component" element="div">
				<cms:component component="${component}" />
			</cms:pageSlot>
		</div>
		<div class="row featured_brands">
			<div class=" col-sm-12">
				<div class="amway-theme">
					<div class="full-width-title-component">

						<cms:pageSlot position="Section11" var="component">
							<h2 class="full-width-title-component__title">${component.content}</h2>
						</cms:pageSlot>
					</div>
				</div>
				<cms:pageSlot position="Section12" var="component" element="div">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>
</template:page>
