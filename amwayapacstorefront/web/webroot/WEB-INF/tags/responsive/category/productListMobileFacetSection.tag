<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="searchPageData" type="de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="aboUser" value="false"/>
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="aboUser" value="true"/>
</sec:authorize>

<div id="product-facet" class="mob-product-facet product__facet js-product-facet">
	<form action="" method="get" id="mobile-facet-apply-form">
		<input hidden="hidden" name="q">
	</form>
    <div class="filters-header">
        <span><spring:theme code="plp.facet.section.label"/></span>
        <button form="clearAllForm" type="submit"><spring:theme code="plp.facet.clear.all"/></button>
    </div>
    <div class="panel-group accordion-custom" id="facetAccordion" role="tablist" aria-multiselectable="true">
	    <c:forEach items="${searchPageData.facets}" var="facet">
	    	<c:choose>
	    		<c:when test="${facet.code eq 'aboPriceRange'}">
	    			<c:if test="${aboUser}">
	    				<nav:facetNavRefinementFacet facet="${facet}" isMobile="true"/>
	    			</c:if>
	    		</c:when>
	    		<c:when test="${facet.code eq 'retailPriceRange'}">
	    			<c:if test="${not aboUser}">
	    				<nav:facetNavRefinementFacet facet="${facet}" isMobile="true"/>
	    			</c:if>
	    		</c:when>
				<c:otherwise>
            		<nav:facetNavRefinementFacet facet="${facet}" isMobile="true"/>
            	</c:otherwise>
			</c:choose>
	     </c:forEach>
    </div>
</div>
       	