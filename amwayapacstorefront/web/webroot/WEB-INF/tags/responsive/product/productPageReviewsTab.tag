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

<div class="accordion-panel-heading" role="tab" id="reviews">
    <div class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#productPageAccordion" href="#reviewsbody" aria-controls="bundleBody">
        <h5 class="accordion-panel-title">
            <span class="accordion-title-wrapper">
                <span class="title-element accordion-header-icon"></span>
                <span class="title-element title-text">reviews</span>
                <span class="title-element accordion-icon-wrapper"></span>
            </span>
        </h5>
    </div>
</div>
<div id="reviewsbody" class="accordion-panel-collapse collapse">
    <div class="product-collapse__rating">
        <p class="product-collapse__review">
            <a href="#" class="product-description__readmore">
                <img class="product-collapse__review-icon" src="${themeResourcePath}/images/review.png">Review This Item
            </a>
        </p>
    </div>
    <ul id="reviews" class="review-list" data-reviews="${getPageOfReviewsUrl}"  data-allreviews="${getAllReviewsUrl}"></ul>
</div>
