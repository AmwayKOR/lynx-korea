<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<div class="product-description__imageslist" id="productListTab">
    <c:forEach items="${galleryImages}" var="container" varStatus="varStatus">
        <div>
            <img class="product-description__image" src="${container.thumbnail.url}" alt="protein-powder"></div>
        <div>
    </c:forEach>
</div>