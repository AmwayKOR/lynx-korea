<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<c:url var="cmsLinkUrl" value="${urlLink}" />
<div id="productLearningListTabs" class="owl-carousel">
	<c:forEach items="${textImageComponent}" var="component">
		<div class="item">
			<cms:component component="${component}" />
		</div>
	</c:forEach>
</div>




		
<%-- 					<div class="full-width-item second-option">
				<div class="item-image-wrap">
					<a href="${cmsLinkUrl}" class="primary-link "> <img
						class="desktop-image" src="${component.backgroundImage.media.url}"
						alt="three tile"></a>
				</div>
				<div class="item-content-wrap">
					<div class="carousel-link-wrap">
						<a href="${cmsLinkUrl}" class="primary-link "> <span>${component.headerText}</span></a>

					</div>
				</div>
			</div>
 --%>