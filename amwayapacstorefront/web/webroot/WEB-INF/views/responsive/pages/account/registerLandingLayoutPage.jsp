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
	<div id="register-landing-page">
	
		<div id="masthead" class="main-container">
			<cms:pageSlot position="JoinAmwayContentSlot" var="component">
				<cms:component component="${component}" />
			</cms:pageSlot>
		</div>
		
		<div class="container-fluid main-container small-wrap">
			<cms:pageSlot position="WeAreAmwayContentSlot" var="component">
				<cms:component component="${component}" />
			</cms:pageSlot>
			<div class="row" id="involved-ways">
				<div class="col-md-6 col-xs-12">
					<cms:pageSlot position="BecomeAnABOContentSlot" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</div>
				<div class="line"></div>
				<div class="col-md-6 col-xs-12">
					<cms:pageSlot position="BecomeACustomerContentSlot" var="component">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</div>
			</div>
			<div class="footer-notice italic">
				<div class="tips">
                    <div class="toolTips">
                        <div id="triangle-down"></div>
                        <cms:pageSlot position="FooterNoticeToolTipParagraphContentSlot"
							var="component">
							<cms:component component="${component}" />
						</cms:pageSlot>
                    </div>
                    <a href="javascript:void(0)" class="upTips">
                        <i class="icon icon-infos"></i>
                    </a>
                </div>
				<cms:pageSlot position="FooterNoticeParagraphContentSlot"
					var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>

	</div>
</template:page>