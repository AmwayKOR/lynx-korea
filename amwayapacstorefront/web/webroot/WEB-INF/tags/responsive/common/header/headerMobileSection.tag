<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="isUserAbo" value="false" />
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true" />
</sec:authorize>

<div class="mobile-container">
	<div class="mobile-wrapper">
		<nav:MobileTopNav />
		<div class="mobile-header-cell mobile-nav-row-btn-logo">
			<div class=" yComponentWrapper">
				<div class="banner__component banner">
					<cms:pageSlot position="MobileSiteLogo" var="component" element="div">
						<cms:component component="${component}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
		<div class="mobile-header-cell search-btn-container">
			<a class="mobile-search-btn js-toggle-xs-search print-hide">
				<span class="icon icon-search"></span>
			</a>
		</div>

		<c:choose>
			<c:when test="${isUserAbo}">
				<div class="mobile-header-cell user-info-container">
					<button class="mobile-user-btn js-toggle-xs-search print-hide" type="button">

						<img src="${themeResourcePath}/images/yui.png" alt="" width="34px">
						<span class="message-num-1">
							<spring:theme code="text.header.mobile.message-num-1" />
						</span>
					</button>
				</div>
			</c:when>
			<c:otherwise>
				<div class="mobile-header-cell user-info-container">
					<button class="mobile-user-btn js-toggle-xs-search print-hide" type="button">
						<span class="icon icon-user"></span>
					</button>
				</div>
			</c:otherwise>
		</c:choose>

		<cms:pageSlot position="MobileMiniCart" var="component">
			<cms:component component="${component}" />
		</cms:pageSlot>
	</div>
</div>
