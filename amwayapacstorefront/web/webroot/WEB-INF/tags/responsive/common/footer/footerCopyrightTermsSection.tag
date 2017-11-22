<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<ul class="footer-nav">
	<c:if test="${not empty copyRightTitle}">
		<li>
			<span>${copyRightTitle}</span>
		</li>
	</c:if>
	<c:forEach items="${component.links}" var="link">
		<li>
			<cms:component component="${link}" />
		</li>
	</c:forEach>
</ul>
<div class="footer-logos">
	<cms:component component="${footerBanner}" />
</div>