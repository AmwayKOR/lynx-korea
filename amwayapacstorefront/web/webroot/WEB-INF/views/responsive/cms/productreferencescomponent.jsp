<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<c:url value="${url}" var="addToCartUrl"/>
<c:choose>
	<c:when test="${not empty productReferences and component.maximumNumberProducts > 0}">
	    <div class="row">
            <div class="amway-suggest col-md-12">
                <h1 class="amway-suggest__title">${fn:escapeXml(component.title)}</h1>
                <div id="productSuggestListTabs" class="amway-suggest__list">
                    <c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
                        <product:productCarouselListerGridItem product="${productReference}"/>
                    </c:forEach>
                </div>
            </div>
        </div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent />
	</c:otherwise>
</c:choose>
