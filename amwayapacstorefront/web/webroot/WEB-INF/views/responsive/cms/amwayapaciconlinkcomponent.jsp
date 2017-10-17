<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<c:url var="cmsLinkUrl" value="${linkUrl}" />
<c:choose>
	<c:when test="${not empty component.linkName and empty linkUrl}">
		<span class="${component.styleAttributes}">
		 ${component.linkName}
		  <i class="${component.iconStyleAttributes}">
		  </i>
		</span>
	</c:when>
	<c:otherwise>
		<a href="${cmsLinkUrl}" title="${component.linkName}"
			class="${component.styleAttributes}"
			<c:if test="${(not empty component.target) and ('sameWindow' ne component.target.code)}">target="_blank"</c:if>>
			  ${component.linkName}<i class="${component.iconStyleAttributes}">
			</i>
		</a>
	</c:otherwise>
</c:choose>
