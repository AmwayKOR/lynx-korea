<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
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

<div class="experience-brands__content"
	style="background-image: url(${component.sideImage.media.url});background-color: #ffffff;background-repeat-x: no-repeat;background-repeat-y: no-repeat;">
	<div class="wrapper">
		<div class="banner-content">
			<div class="banner-title-wrapper">
				<cms:component component="${component.text}" />
			</div>
			<div class="banner-button-wrap">
				<a class="banner-button-link ">
					<cms:component component="${component.link}" />
				</a>
			</div>
		</div>
		<img class="experience-brands__image js-responsive-image" data-media='{${imagerData}}' title='${altText}' alt='${altText}' />
	</div>
</div>
