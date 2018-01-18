<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<template:page pageTitle="${pageTitle}">
	<div class="container-fluid main-container">
		<div class="row">
			<div class="searchEmptyPageMiddle">
				<div class=" searchEmptyPageMiddle-component">

					<div class="content">
						<cms:pageSlot position="SideContent" var="comp">
							<div class="new-search-icon">
								<img src="${themeResourcePath}/images/new-search-icon.png" />
							</div>
							<cms:component component="${comp}" />
						</cms:pageSlot>
					</div>
				</div>

				<div class="searchEmptyPageMiddle-component">
					<cms:pageSlot position="MiddleContent" var="comp1">
						<cms:component component="${comp1}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
		<cms:pageSlot position="ProductCarouselSlot" var="component">
			<cms:component component="${component}" />
		</cms:pageSlot>
	</div>
</template:page>