<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<c:set var="showAddToCart" value="" scope="session"/>
<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="firstVariantOptions" value="${product.baseOptions[0].options}"/>
<c:set var="firstVariantName" value="${product.baseOptions[0].options[0].variantOptionQualifiers[0].name}"/>
<c:set var="selectedColorVariant" value="${product.baseOptions[0].selected}"/>

<c:if test="${not empty firstVariantOptions}">
    <c:if test="${(firstVariantName eq 'Color') }">
        <div class="lip-color-choose">
            <p class="lip-color-choose-message">${firstVariantName}</p>
            <ul class="lip-color-choose__nav">
                <c:forEach items="${firstVariantOptions}" var="variantOption">
                   <c:url value="${variantOption.url}" var="variantOptionUrl"/>
                   <c:set var="imageData" value="${variantOption.variantOptionQualifiers[0].image}"/>
                   <li data-product-code="${variantOption.code}" <c:if test="${(selectedColorVariant.variantOptionQualifiers[0].value) eq (variantOption.variantOptionQualifiers[0].value)}">class="active"</c:if>>
                      <a href="${variantOptionUrl}"><img src="${imageData.url}" alt="${variantOption.variantOptionQualifiers[0].value}" title="${variantOption.variantOptionQualifiers[0].value}"></a>
                   </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</c:if>
