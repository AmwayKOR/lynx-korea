<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<c:forEach items="${medias}" var="media">
	<c:choose>
		<c:when test="${empty imagerData}">
			<c:set var="imagerData">"${media.width}":"${media.url}"</c:set>
		</c:when>
		<c:otherwise>
			<c:set var="imagerData">${imagerData},"${media.width}":"${media.url}"</c:set>
		</c:otherwise>
	</c:choose>
	<c:if test="${empty altText}">
		<c:set var="altText" value="${fn:escapeXml(media.altText)}" />
	</c:if>
</c:forEach>

<div class="banner-image-wrap full-width-banner">
	<img class="desktop-image js-responsive-image" data-media='{${imagerData}}' alt='${altText}' title='${altText}' />
</div>
<div class="banner-content-wrap">
	<div class="banner-content">
		<div class="banner-title-wrapper">
			<h2 class="banner-title">
				<img class="category-banner__icon" src="${component.logoImage.media.url}" alt="icon" />
				<span>${component.headerText}</span>
			</h2>
			<div class="sub-title-wrap">
				<span class="banner-sub-title">
					<cms:component component="${component.text}" />
				</span>
			</div>
		</div>
		<div class="banner-button-wrap">
			<cms:component component="${component.link}" />
		</div>
	</div>
</div>
