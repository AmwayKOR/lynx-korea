<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<a class="primary-link category-item col-sm-6 col-md-3" href="${linkUrl}">
    <cms:component component="${banner}" />
    <label>
        <span class="category-name">${link.linkName}</span>
    </label>
</a>
