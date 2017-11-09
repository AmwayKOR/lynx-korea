<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<div class="col-sm-12 col-md-6 product-image-gallery js-product-image-gallery" style="opacity: 1;">
	 <c:choose>
        <c:when test="${galleryImages == null || galleryImages.size() == 0}">
            <div class="image-gallery js-gallery">
	            <spring:theme code="img.missingProductImage.responsive.product" text="/" var="imagePath"/>
	            <c:choose>
	                <c:when test="${originalContextPath ne null}">
	                    <c:url value="${imagePath}" var="imageUrl" context="${originalContextPath}"/>
	                </c:when>
	                <c:otherwise>
	                    <c:url value="${imagePath}" var="imageUrl" />
	                </c:otherwise>
	            </c:choose>
	            <img src="${imageUrl}"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="image-gallery js-gallery">
	             <img src="${galleryImages[0].product.url}">
		    </div>
		    <div class="buttons zoom-center js-zoom-center">
		        <button class="enlarge2 btn btn-link">
		            <span class="glyphicon glyphicon-zoom-in"></span>
		            <span><spring:theme code="pdp.image.panel.enlarge" /></span></button>
		    </div>
		    <product:productGalleryThumbnail galleryImages="${galleryImages}" />
		    <div class="enlarge-content-wrapper display-none">
	    		<div class="product-description__super-zoom">
					<img id="top-image" class="product-description__super-image"
						alt="full screen image"
						src="${themeResourcePath}/images/heart-health-full.jpg"
						data-dismiss="modal" aria-label="Close" aria-hidden="true">
				</div>
		    </div>
        </c:otherwise>
    </c:choose>
</div>