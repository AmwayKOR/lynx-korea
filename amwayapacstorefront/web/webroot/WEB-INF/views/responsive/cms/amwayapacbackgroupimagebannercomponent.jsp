<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="experience-brands__content" style="background-image: url(${backgroudImage.url});background-color: #ffffff;background-repeat-x: no-repeat;background-repeat-y: no-repeat;">
    <div class="wrapper">
        <div class="banner-content">
            <div class="banner-title-wrapper">
                <h2 class="banner-title">
                	<span>${title}</span>
				</h2>
                <div class="sub-title-wrap">
                	<cms:component component="${description}" />
                </div>
            </div>
			<div class="banner-button-wrap banner-button-link btn-blue-white">
            	<cms:component component="${shopnow}"/>
            </div>
        </div>
        <img class="experience-brands__image" src="${media.url}" alt="${media.altText}" /></div>
</div>