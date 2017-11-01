<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set value="${fn:length(reviews) eq reviewsTotal}" var="showingAllReviews"/>
<div id="showingAllReviews" data-showingAllReviews="${showingAllReviews}" ></div>

<c:if test="${not empty reviews}">
	<c:forEach items="${reviews}" var="review" varStatus="status">
		<p class="product-collapse__review">
		    <c:forEach  begin="1" end="5">
                <img src="${themeResourcePath}/images/star-empty.png" alt="star">
            </c:forEach>
            ${fn:escapeXml(review.headline)}</p>
            <p class="product-collapse__info">
                <c:choose>
                    <c:when test="${not empty review.alias}">
                        ${fn:escapeXml(review.alias)}
                    </c:when>
                    <c:otherwise>
                        <spring:theme code="review.submitted.anonymous" />
                    </c:otherwise>
                </c:choose>
                <c:set var="reviewDate" value="${review.date}" />
                <fmt:formatDate value="${reviewDate}" pattern="MMMM dd, yyyy" />
            </p>
            <p class="product-collapse_desc">${fn:escapeXml(review.comment)}</p>
        </div>
	</c:forEach>
</c:if>