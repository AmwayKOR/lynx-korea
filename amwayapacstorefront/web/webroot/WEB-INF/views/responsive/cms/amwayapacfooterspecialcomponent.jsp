<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"%>

<li>
	<c:if test="${not empty sectionTitle}">
		<p class="quick-links-title">${sectionTitle}</p>
	</c:if>
	<c:forEach items="${simpleBanners}" var="banner" varStatus="status">
		<cms:component component="${banner}" />
	</c:forEach>
</li>
