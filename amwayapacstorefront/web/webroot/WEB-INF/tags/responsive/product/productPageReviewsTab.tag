<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:url value="${product.url}/reviewhtml/3" var="getPageOfReviewsUrl"/>
<c:url value="${product.url}/reviewhtml/all" var="getAllReviewsUrl"/>
<c:url value="${product.url}/review" var="productReviewActionUrl"/>

<div class="product-collapse__rating">
	<div>
		<ul id="reviews" class="review-list" data-reviews="${getPageOfReviewsUrl}" data-allreviews="${getAllReviewsUrl}"></ul>
		<div class="product-collapse__rating">
		<c:if test="${not empty reviewsCount and reviewsCount > 3}">
			<p class="more">
				<a href="#" class="all-reviews-btn">
					<spring:theme code="review.show.all" />
				</a>
			</p>
			</c:if>
		</div>
	</div>
</div>
<div id="product-review" class="cbox review">
    <div class="cart-popup__dialog">
        <div class="cart-popup__header">
           	<span class="cart-popup__header-text"><spring:theme code="review.header" />
            <img class="cart-popup__close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
	        <div class="cart-popup__content review-popup">
	            <div class="cart-popup__item-info  amwahover">
	                <div class="row review-popup_item">
	                    <div class="col-xs-4 col-md-2"><img src="${galleryImages[0].product.url}" class="cart-popup__thumbnail" alt="product"></div>
	                    <div class="col-xs-8 col-md-10">
	                        <p class="cart-popup__item-title"><spring:theme code="review.title" /></p>
	                        <p class="cart-popup__item-count">${product.name}</p>
	                    </div>
	                </div>
	                <form v-on:submit.prevent="submit" action="#" method="get">
	                <div class="dialog-form-module">
	                	<input type="hidden" ref="alias" value="${user.name}" class="form-control" name="alias" >
                        <div class="row form-group" v-bind:class="{'has-error': ratingError}">
                            <div class="col-md-12 form-name"><spring:theme code="review.rating" /></div>
                            <div class="col-md-12 form-opera star" >
                               <div id="star"></div>
                               <div v-bind:class="[ratingError ? 'visible' : 'invisible', {'help-block with-errors' : ratingError}]">{{ ratingMessage }}</div>
                            </div>
                        </div>
                        <div class="row form-group" v-bind:class="{'has-error': headlineError}">
                            <div class="col-md-12 form-name"><spring:theme code="review.headline" /></div>
                            <div class="col-md-12 form-opera">
                            	<input v-model="headline" name="headline" type="text" class="form-control" required="required">
                            	<div v-bind:class="[headlineError ? 'visible' : 'invisible', {'help-block with-errors' : headlineError}]">{{ headlineMessage }}</div>
                            </div>
                        </div>
                        <div class="row form-group" v-bind:class="{'has-error': commentError}">
                            <div class="col-md-12 form-name"><spring:theme code="review.comment" /></div>
                            <div class="col-md-12 form-opera">
                            	<textarea v-model="comment" name="comment" class="form-control"></textarea>
                            </div>
                            <div class="col-md-12 form-opera">
                            	<div v-bind:class="[commentError ? 'visible' : 'invisible', {'help-block with-errors' : commentError}]">{{ commentMessage }}</div>
                            </div>
                        </div>
	                </div>
	                <div class="cart-popup__item-link"><a class="review-btn btn-blue-white" v-on:click.stop.prevent="submit" href="${productReviewActionUrl}"><spring:theme code="review.submit" /></a><a class="review-btn btn-blue-white cart-popup__item-link-text closeCbox" href="javascript:void(0);"><spring:theme code="review.cancel" /></a></div>
	                </form>
	            </div>
	        </div>
    </div>
</div>
<div class="cbox prompt">
    <div class="cart-popup__dialog">
        <div class="cart-popup__header">
            <span class="cart-popup__header-text"><spring:theme code="review.confirmation.thank.you.title" />
                <img class="cart-popup__close" src="${themeResourcePath}/images/close.png" alt="close" data-dismiss="modal" aria-label="Close" aria-hidden="true"></span></div>
        <div class="cart-popup__content review-popup">
            <div class="cart-popup__item-info  amwahover">
                <div class="row prompt_item">
                    <div class="col-xs-12">
                        <p class="cart-popup__item-title"><spring:theme code="review.confirmation.thank.you.header" /></p>
                        <p class="cart-popup__item-count"><spring:theme code="review.confirmation.thank.you.headline" /></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>