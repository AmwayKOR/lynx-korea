<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/responsive/category" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<template:page pageTitle="${pageTitle}">
            <div class="container-fluid main-container new-plp">
                <div class="row">
                    <h1 class="col-sm-12 product-list-page-title">${categoryName}</h1>
                    <div class="col-xs-3 print-hide">
                    	<category:productListDesktopFacetSection searchPageData="${searchPageData}"/>
                    </div>
                    <div class="col-sm-12 col-md-9">
                        <!-- Product List -->
                        <div class="new-product-list-right-slot product-list-right-slot product-list row">
                            <div class="product__list--wrapper yComponentWrapper product-list-right-component">
                                <category:productListingTopRightSection searchPageData="${searchPageData}"/>
                                <c:choose>
                                	<c:when test="${searchPageData.pagination.totalNumberOfResults gt 0}">
                                		<category:productListSection searchPageData="${searchPageData}"/>
                                		<category:productListerPaginationSection/>
                                	</c:when>
                                	<c:otherwise>
                                		<div class="searchEmptyPageMiddle-component">
				                            <div class="content">
				                            	<cms:pageSlot position="NoResultsContentSlot" var="component">
				                            		<cms:component component="${component}"/>
				                            	</cms:pageSlot>
				                            </div>
					                    </div>
                                	</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>

                <cms:pageSlot position="UpSelling" var="comp">
                    <cms:component component="${comp}" />
                </cms:pageSlot>

            </div>
</template:page>