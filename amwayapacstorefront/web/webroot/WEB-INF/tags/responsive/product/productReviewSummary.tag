<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="showLinks" required="false" type="java.lang.Boolean" %>
<%@ attribute name="starsClass" required="false" type="java.lang.String" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<%@ attribute name="product" required="true"
	type="de.hybris.platform.commercefacades.product.data.ProductData"%>

<div class="description-vote">
    <c:forEach  begin="1" end="5">
        <img src="${themeResourcePath}/images/star-empty.png" alt="star">
    </c:forEach>
    <a href="#tabreview" class="js-openTab">
        <span class="description-number">${product.averageRating}</span>
        <span class="description-reviews">
            <c:if test="${not empty product.reviews}">
                <spring:theme code="review.based.on"
                    arguments="${fn:length(product.reviews)}" />
            </c:if>
        </span>
    </a>
</div>
