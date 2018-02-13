<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

<c:forEach items="${secondaryMedias}" var="media">
	<c:choose>
		<c:when test="${empty secondaryImagerData}">
			<c:set var="secondaryImagerData">"${media.width}":"${media.url}"</c:set>
		</c:when>
		<c:otherwise>
			<c:set var="secondaryImagerData">${secondaryImagerData},"${media.width}":"${media.url}"</c:set>
		</c:otherwise>
	</c:choose>
	<c:if test="${empty secondaryAltText}">
		<c:set var="secondaryAltText" value="${fn:escapeXml(media.altText)}" />
	</c:if>
</c:forEach>

<div>
	<div class="wrapper">
		<div class="banner__wrapper">
			<img class="banner__image js-responsive-image" data-media='{${imagerData}}' alt='${altText}' title='${altText}' />
			<div class="banner__message">
				<cms:component component="${component.text}" />
				<c:if test="${not empty component.link}">
					<div class="banner__shop-now">
						<cms:component component="${component.link}" class="banner__shop-now" />
					</div>
				</c:if>
				<c:if test="${not empty component.secondaryLink}">
					<div class="banner__action">
						<cms:component component="${component.secondaryLink}" />
					</div>
				</c:if>
			</div>
			<c:if test="${not empty secondaryMedias}">
				<img class="banner__image-sub js-responsive-image" data-media='{${secondaryImagerData}}' alt='${secondaryAltText}'
					title='${secondaryAltText}' />
			</c:if>
		</div>
	</div>
</div>
