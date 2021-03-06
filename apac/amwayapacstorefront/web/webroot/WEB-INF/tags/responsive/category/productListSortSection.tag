<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="searchPageData" type="de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="isUserAbo" value="false"/>
<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="isUserAbo" value="true"/>
</sec:authorize>

<div class="form-group sort-group">
 <label class="control-label " for="sortForm1"><spring:theme code="plp.sort.section.label"/></label>
 	<form id="sortForm1" name="sortForm1" method="get" action="">
     	<select id="sortOptions1" name="sort" class="form-control">
         	<option disabled="disabled"><spring:theme code="plp.sort.section.label"/></option>
         	<c:forEach items="${searchPageData.sorts}" var="sort">
         	<c:set var="showSort" value="true"/>
         	<c:choose>
				<c:when test="${(isUserAbo == false) && ((sort.code eq 'pvbv-desc-c') || (sort.code eq 'pvbv-asc-c') || (sort.code eq 'abo-price-desc-c') || (sort.code eq 'abo-price-asc-c'))}">
				<c:set var="showSort" value="false"/>
				</c:when>
				<c:when test="${(isUserAbo == true) && ((sort.code eq 'retail-price-desc-c') || (sort.code eq 'retail-price-asc-c'))}">
				<c:set var="showSort" value="false"/>
				</c:when>
			</c:choose>
			<c:if test="${showSort}">
         		<option value="${sort.code}" <c:if test="${sort.selected}">selected="selected"</c:if>>${sort.name}</option>
         	</c:if>
         </c:forEach>
     	</select>
    	<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
    </form>
</div>