<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="banner homepage_banner">
	<div id="banner_list">
						<div>
								<div class="wrapper">
			                            <div class="banner__wrapper">
			                                <img class="banner__image" src="${media.url}" alt="banner" />
			                                 <div class="banner__message">
			                                    <span class="banner__message--main">${headline}</span>
			                                    <br />
			                                    <span class="banner__message--sub">${content}</span>
			                                    <div class="banner__shop-now"><cms:component component="${shopnow}" class="banner__shop-now" /></div>
			                                    <div class="banner__action"><cms:component component="${secondary}"  /><i class="glyphicon glyphicon-menu-right"></i></div>
			                                   
			                                </div>
			                                   
			                                <img class="banner__image-sub" src="${image2.url}" alt="artistry" />
			                                
			                        </div>
			                    </div>
						</div>
</div>
</div>