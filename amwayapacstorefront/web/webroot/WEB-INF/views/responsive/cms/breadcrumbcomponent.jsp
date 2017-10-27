<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb"%>

<div class="container-fluid main-container ">
	<c:if test="${fn:length(breadcrumbs) > 0}">
		<div class="print-hide breadcrumb-section">
			<div class="breadcrumb-section">
				<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
			</div>
		</div>
	</c:if>
</div>