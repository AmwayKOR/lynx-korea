<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="${product.url}/reviewhtml/3" var="getPageOfReviewsUrl"/>
<c:url value="${product.url}/reviewhtml/all" var="getAllReviewsUrl"/>
<c:url value="${product.url}/review" var="productReviewActionUrl"/>


<div class="product-collapse__rating">
    <p class="product-collapse__review">
        <a href="#" class="product-description__readmore">
            <img class="product-collapse__review-icon" src="${themeResourcePath}/images/review.png"><spring:theme code="review.reviews.add" /></a></p>
<div>
<ul id="reviews" class="review-list" data-reviews="${getPageOfReviewsUrl}"  data-allreviews="${getAllReviewsUrl}"></ul>
<div class="product-collapse__rating">
    <p class="more">
        <a href="#" class="all-reviews-btn"><spring:theme code="review.show.all" /></a></p>
</div>