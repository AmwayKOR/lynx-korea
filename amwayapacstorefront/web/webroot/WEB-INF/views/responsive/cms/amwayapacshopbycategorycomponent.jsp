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

<a class="primary-link category-item col-sm-6 col-md-3" href="${linkUrl}">
	<img class="js-responsive-image" data-a="${imagerData}" data-media='{${imagerData}}' alt='${altText}' title='${altText}' style="">
	<%-- <cms:component component="${component.banner}" /> --%>
	<label data-a="${component.uid}">
		<span class="category-name">${link.linkName}</span>
	</label>
</a>