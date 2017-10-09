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

<c:if test="${product.baseOptions[0].variantType eq 'AmwayApacFirstVariantProduct'}">
    <c:set var="firstVariantOptions" value="${product.baseOptions[0].options}"/>
    <c:set var="firstVariantName" value="${product.baseOptions[0].options[0].variantOptionQualifiers[0].name}"/>
</c:if>
<c:if test="${product.baseOptions[0].variantType eq 'AmwayApacSecondVariantProduct'}">
    <c:set var="firstVariantOptions" value="${product.baseOptions[0].options}"/>
    <c:set var="secondVariantOptions" value="${product.baseOptions[1].options}"/>
    <c:set var="firstVariantName" value="${product.baseOptions[0].options[0].variantOptionQualifiers[0].name}"/>
    <c:set var="secondVariantName" value="${product.baseOptions[1].options[0].variantOptionQualifiers[0].name}"/>
</c:if>

<c:if test="${not empty secondVariantOptions}">
    <c:if test="${secondVariantName eq 'Color'}">
        <div class="lip-color-choose">
            <p class="lip-color-choose-message">${secondVariantName}</p>
            <ul class="lip-color-choose__nav">
                <c:forEach items="${secondVariantOptions}" var="variantOption">
                    <c:forEach items="${variantOption.variantOptionQualifiers}" var="variantOptionQualifier">
                         <c:url value="${variantOption.url}" var="variantOptionUrl"/>
                         <c:set var="imageData" value="${variantOptionQualifier.image}"/>
                         <li>
                            <a href="${variantOptionUrl}"><img src="${imageData.url}" alt="color"></a>
                         </li>
                    </c:forEach>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</c:if>
<c:if test="${not empty firstVariantOptions}">
    <c:if test="${firstVariantName eq 'Color'}">
        <div class="lip-color-choose">
            <p class="lip-color-choose-message">${secondVariantName}</p>
            <ul class="lip-color-choose__nav">
                <c:forEach items="${firstVariantOptions}" var="variantOption">
                    <c:forEach items="${variantOption.variantOptionQualifiers}" var="variantOptionQualifier">
                         <c:url value="${variantOption.url}" var="variantOptionUrl"/>
                         <c:set var="imageData" value="${variantOptionQualifier.image}"/>
                         <li>
                            <a href="${variantOptionUrl}"><img src="${imageData.url}" alt="color"></a>
                         </li>
                    </c:forEach>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</c:if>