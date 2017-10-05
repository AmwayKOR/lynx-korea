<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

 <div class="full-width-item second-option">
    <div class="item-image-wrap">
    	<cms:component component="${banner}" />
    </div>
    <div class="item-content-wrap">
        <div class="carousel-link-wrap">
        	<c:url var="bannerUrl" value="${banner.urlLink}" />
            <a href="${bannerUrl}" class="primary-link ">
                <cms:component component="${text}" />                
            </a>
        </div>
    </div>
</div>