<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="option-item-container col-xs-6 col-md-4">
    <a href="${link.url}">
        <div class="option-item">
            <div class="icon ${title}">
            </div>
            <span class="option-title">${title2}</span>
            <span class="option-description">${description.content}</span>
        </div>
    </a>
</div>