<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>

<c:url var="cmsLinkUrl" value="${linkUrl}" />

<a class="country-item-link" href="${cmsLinkUrl}">
	<span class="flag-icon"><img class="topbar__flag"
		src="${imageAttribute.url}" alt="flag" /></span> <span
	class="country-item-text">${linkName}</span>
</a>
