<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach items="${medias}" var="media">
	<c:choose>
		<c:when test="${empty imagerData}">
			<c:set var="imagerData">"${media.width}":"${media.url}"</c:set>
		</c:when>
		<c:otherwise>
			<c:set var="imagerData">${imagerData},"${media.width}":"${media.url}"</c:set>
		</c:otherwise>
	</c:choose>
	<c:if test="${empty altText}">
		<c:set var="altText" value="${fn:escapeXml(media.altText)}" />
	</c:if>
</c:forEach>

<div class="product-category-page amway-theme">
	<div class="product-categories">
		<div class="amway-theme">
			<div class="full-width-banner-component banner-left-align ">
				<div class="banner-image-wrap full-width-banner">
					<img class="desktop-image" src="${backgroundImage.media.url}"
						alt="" /> <img class="desktop-image-mob"
						src="images/article_baner.png" alt="banner" />
				</div>
				<div class="banner-content-wrap-ink-out">
					<div class="banner-content-wrap-ink">
						<div class="banner-content">
							<div class="banner-title-wrapper">
								<cms:component
												component="${text}" />
							</div>
							<div class="banner-button-wrap">
								<cms:component component="${link}" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>