<%@ page trimDirectiveWhitespaces="true"%>
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


<div class="item-image-wrap">
	<a href="${linkUrl}" class="primary-link ">
		<img class="desktop-image js-responsive-image" data-media='{${imagerData}}' alt='${altText}' title='${altText}' />
	</a>
</div>
<div class="item-content-wrap">
	<div class="carousel-link-wrap">
		<a href="${linkUrl}" class="primary-link ">
			<cms:component component="${component.text}" />
		</a>
	</div>
</div>
