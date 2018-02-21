<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<div class="timeline">
	<div class="timelineMain">
		<c:forEach items="${component.carouselComponents}" var="banner" varStatus="status">
			<cms:component component="${banner}" />
		</c:forEach>
	</div>
</div>