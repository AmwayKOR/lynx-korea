<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


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

 <div class="full-width-item second-option">
    <div class="item-image-wrap">
    <img class="desktop-image" data-media='{${imagerData}}' alt="three tile">
    	<%-- <cms:component component="${banner}" /> --%>
    </div>
    <div class="item-content-wrap">
        <div class="carousel-link-wrap">
        	<c:url var="bannerUrl" value="${banner.urlLink}" />
            <a href="${bannerUrl}" class="primary-link ">
                <cms:component component="${text}" />                
            </a>
        </div>
    </div>
</div>
