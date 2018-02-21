<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="content" tagdir="/WEB-INF/tags/addons/amwaycontentsolrsearchaddon/desktop/content" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/addons/amwaycontentsolrsearchaddon/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%-- <%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %> --%>

<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	
	<c:if test="${productHasResult}">
		<c:url var="productSearchUrl" value="/search?text=${searchPageData.freeTextSearch}"/>
		<script>var productQuerySearchUrl = '${productSearchUrl}';</script>				
	</c:if>	
			
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<div class="span-18 content last">
		<cms:pageSlot position="Section2" var="feature" element="div" >
			<cms:component component="${feature}" />
		</cms:pageSlot>

		<div class="results">
			<h1><spring:theme code="search.page.searchText" arguments="${searchPageData.freeTextSearch}"/></h1>
		</div>

		<nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
	
		<nav:pagination top="true"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
	
		<div class="contentList">
			<c:forEach items="${searchPageData.results}" var="content">
				<content:contentListerItem content="${content}"/>
			</c:forEach>
		</div>
		
		<br/>
	
		<nav:pagination top="false"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>

	</div>
	
<%-- 	<storepickup:pickupStorePopup /> --%>
</template:page>