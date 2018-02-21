<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="amway-theme subTitle">
                    <div class="full-width-title-component">
                        <h2 class="full-width-title-component__title">${title}</h2>
                        <p>${news}</p>
                        <div class="featured-product__link">
                        		<cms:component component="${link}"  />
                        		<span class="featured-product__message-icon glyphicon glyphicon-menu-right"></span>
                        </div>
                    </div>
</div>
