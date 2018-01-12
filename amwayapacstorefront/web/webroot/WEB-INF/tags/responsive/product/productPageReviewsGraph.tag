<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="${product.url}/reviewCount" var="productReviewGraphActionUrl"/>

<spring:htmlEscape defaultHtmlEscape="true" />
<div class="product-review">
	<div id="reviewgraph" class="comuserinfo" data-url="${productReviewGraphActionUrl}">
		<dl>
		    <dt>5 star</dt>
		    <dd class="star5"><div style="width:0%;"></div><span>0</span></dd>
		</dl>
		<dl>
		    <dt>4 star</dt>
		    <dd class="star4"><div style="width:0%;"></div><span>0</span></dd>
		</dl>
		<dl>
		    <dt>3 star</dt>
		    <dd class="star3"><div style="width:0%;"></div><span>0</span></dd>
		</dl>
		<dl>
		    <dt>2 star</dt>
		    <dd class="star2"><div style="width:0%;"></div><span>0</span></dd>
		</dl>
		<dl>
			<dt>1 star</dt>
		    <dd class="star1"><div style="width:0%;"></div><span>0</span></dd>
		</dl>
	</div>
	<c:set var="isUserAbo" value="false" />
	<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true" />
	</sec:authorize>
	<c:choose>
		<c:when test="${isUserAbo}">
			<p class="product-collapse__review">
				<a href="#" class="product-description__readmore">
					<img class="product-collapse__review-icon" src="${themeResourcePath}/images/review.png">
				<spring:theme code="review.reviews.add" />
				</a>
			</p>
		</c:when>
		<c:otherwise>
			<spring:theme code="review.reviews.login" />
		</c:otherwise>
	</c:choose>
</div>