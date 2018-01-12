<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/desktop/template/cms" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<c:url value="/" var="siteRootUrl"/>

<template:javaScriptVariables/>

<c:choose>
	<c:when test="${wro4jEnabled}">
	  	<script type="text/javascript" src="${contextPath}/wro/all_desktop.js"></script>
	  	<script type="text/javascript" src="${contextPath}/wro/addons_desktop.js"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.common.js"></script>
		<%-- AddOn JavaScript files --%>
		<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
		    <script type="text/javascript" src="${addOnJavaScript}"></script>
		</c:forEach>
	</c:otherwise>
</c:choose>



<%-- Fix for Webkit Browsers (Needs to be loaded last) --%>
<script type="text/javascript" src="${commonResourcePath}/js/acc.skiplinks.js"></script>

<cms:previewJS cmsPageRequestContextData="${cmsPageRequestContextData}" />
