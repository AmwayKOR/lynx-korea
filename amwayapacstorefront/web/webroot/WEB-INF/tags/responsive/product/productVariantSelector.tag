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

<c:if test="${product.baseOptions[0].variantType eq 'AmwayApacSizeVariantProduct'}">
    <c:set var="variantSizes" value="${product.baseOptions[0].options}"/>
    <div class="row">
        <div class="size-selector-container">
            <label class="control-label" for="pdpAddtoCartInput">Size</label>
            <select id="Size" class="size-select text-center js-qty-selector-input form-control">
                <c:forEach items="${variantSizes}" var="variantSize">
                    <c:forEach items="${variantSize.variantOptionQualifiers}" var="variantOptionQualifier">
                         <c:url value="${variantSize.url}" var="variantOptionUrl"/>

                         <option value="${variantOptionUrl}" ${(variantSize.url eq product.url) ? 'selected="selected"' : ''}>
                                 ${variantOptionQualifier.value}
                         </option>
                    </c:forEach>
                </c:forEach>
            </select>
        </div>
    </div>
</c:if>