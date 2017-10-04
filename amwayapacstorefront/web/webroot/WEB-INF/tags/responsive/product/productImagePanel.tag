<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<div class="col-sm-12 col-md-6 product-image-gallery js-product-image-gallery v-m-pros new-product-image-gallery" style="opacity: 1;">
    <div class="image-gallery js-gallery">
        <c:choose>
            <c:when test="${galleryImages == null || galleryImages.size() == 0}">
                <div class="carousel image-gallery__image js-gallery-image">
                    <div class="item">
                        <div>
                            <spring:theme code="img.missingProductImage.responsive.product" text="/" var="imagePath"/>
                            <c:choose>
                                <c:when test="${originalContextPath ne null}">
                                    <c:url value="${imagePath}" var="imageUrl" context="${originalContextPath}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="${imagePath}" var="imageUrl" />
                                </c:otherwise>
                            </c:choose>
                            <img class="lazyOwl" data-src="${imageUrl}"/>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="image-gallery js-gallery">
                    <div class="image-tab-content">
                        <c:set var="count" value="1" />
                        <c:forEach items="${galleryImages}" var="container" varStatus="varStatus">
                            <c:if test="${count eq 1}">
                                <div id="item-${count}" class="tab-pane fade in active">
                            </c:if>
                            <c:if test="${count ne 1}">
                                <div id="item-${count}" class="tab-pane fade">
                            </c:if>
                                <img class="product-description__img-main" src="${container.product.url}">
                            </div>
                             <c:set var="count" value="${count + 1}" />
                        </c:forEach>
                    </div>
                </div>
                <div class="buttons zoom-center js-zoom-center">
                    <button class="enlarge2 btn btn-link" data-zoom-popup-title="" data-toggle="modal" data-target="#product-zoom-image-panel">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                    <span>Enlarge</span></button>
                </div>
                <product:productGalleryThumbnail galleryImages="${galleryImages}" />
            </c:otherwise>
        </c:choose>
    </div>
</div>
