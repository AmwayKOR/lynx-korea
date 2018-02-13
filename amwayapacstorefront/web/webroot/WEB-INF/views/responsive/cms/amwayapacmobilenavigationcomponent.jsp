<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<li role="presentation" class=" nav-list-element">
	<c:choose>
		<c:when test="${fn:length(component.navigationNode.children) > 1}">
					<div class="overlay-menu-component-mobile-wrapper">
                                        <div class="panel-group" id="mobile-menu-category-accordion" role="tablist"
                                             aria-multiselectable="true">
                                            
                                            <c:forEach items="${component.navigationNode.children}" var="childrenNode" varStatus="loop">
                                            		<div class="panel panel-default overlay-menu-mobile__panel">
                                                <div class="panel-heading overlay-menu-mobile__panel__heading"
                                                     role="tab" id="navMenuMobHeading${loop.index}">
                                                    <h4 class="panel-title">
                                                        <a class="panel-toggle collapsed" role="button"
                                                           data-toggle="collapse"
                                                           data-parent="#mobile-menu-category-accordion"
                                                           href="#navMenuMobCollapse${loop.index}" aria-expanded="true"
                                                           aria-controls="navMenuMobCollapse${loop.index}">
                                                           
                                                            <span class="title-element tab-image">
                                                                <img src="${childrenNode.nodeImg.url}"/></span>
                                                            <span class="title-element active-parent-icon">
                                                                <span class="icon-chevron-left"></span>
                                                            </span>
                                                            <span class="title-element title-text">${childrenNode.nodeLink.linkName}</span>
                                                            <span class="title-element accordion-icon-wrapper">
                                                                <span class="pull-right icon-minus"></span>
                                                            </span>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <c:set var="nodelinkreplace" value='${fn:replace(childrenNode.nodeLink.linkName,"&","")}'/>
                                                <c:set var="nodelinkname" value='${fn:replace(nodelinkreplace," ","")}'/>
                                                <div id="navMenuMobCollapse${loop.index}"
                                                     class="panel-collapse collapse overlay-menu-mobile__panel__content"
                                                     role="tabpanel" aria-labelledby="navMenuMobHeading${loop.index}">
                                                    <div class="panel-body">
                                                    		<c:forEach items="${childrenNode.children}" var="grandChildrenNode" varStatus="loop">
                                                    				<div class="overlay-menu-subcategory js-overlay-menu-subcategory main-subcategory">
		                                                            <h5>
		                                                            		
		                                                                <a class="subcategory-toggle" data-toggle="collapse"
		                                                                   href="#navMenuMobCategoryCollapse-${nodelinkname}${loop.index}"
		                                                                   aria-expanded="false"
		                                                                   aria-controls="navMenuMobCategoryCollapse-${nodelinkname}${loop.index}">
		                                                                   
		                                                                   <!--<cms:component component="${grandChildrenNode.nodeLink}" element="span" class="subcategory-title"/>-->
		                                                                    <span class="subcategory-title">${grandChildrenNode.nodeLink.linkName}</span>
		                                                                    <span class="subcategory-icon icon-chevron-right"></span>
		                                                                </a>
		                                                            </h5>
		                                                            <ul class="subcategory-list collapse"
		                                                                id="navMenuMobCategoryCollapse-${nodelinkname}${loop.index}">
		                                                                <div class="list-wrapper">
		                                                                		<c:forEach items="${grandChildrenNode.links}" var="grandChildrenLinks">
		                                                                                	
	                                                                              <cms:component component="${grandChildrenLinks}" evaluateRestriction="true" element="li" />
						                            
	                                                                          </c:forEach>
		                                                                </div>
		                                                            </ul>
		                                                        </div>
                                                    		</c:forEach>
                                                        
                                                        <div class="overlay-menu-subcategory tools-brands js-overlay-menu-subcategory">
                                                        		
                                                      	  <h5>
                                                      	  
                                                                <a class="subcategory-toggle tools-link"
                                                                   data-toggle="collapse"
                                                                   href="#navMenuMobBrandsCollapse${nodelinkname}">
                                                                    <span class="subcategory-title">Shop by Brands</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse"
                                                                id="navMenuMobBrandsCollapse${nodelinkname}">
                                                                <div class="list-wrapper">
                                                                		<c:forEach items="${childrenNode.brandBanners}" var="banner" varStatus="loop">
                                                                		<c:url value="${fn:escapeXml(banner.urlLink)}" var="encodedUrlLink" />
                                                                		<c:url value="${fn:escapeXml(banner.media.altText)}" var="encodedUrlText" />
                                                                		
                                                                			<li>
                                                                				<a href ="${encodedUrlLink}" >${encodedUrlText}</a>
                                                                			</li>
                                                                			
                                                                		</c:forEach>
                                                                </div>
                                                            </ul>
                                                           
                                                        </div>
                                                        <div class="overlay-menu-subcategory js-overlay-menu-subcategory tools-brands">
                                                            <h5>
                                                                <a class="subcategory-toggle tools-link"
                                                                   data-toggle="collapse"
                                                                   href="#navMenuMobToolsCollapse${nodelinkname}"
                                                                   aria-expanded="false"
                                                                   aria-controls="navMenuMobToolsCollapse${nodelinkname}">
                                                                    <span class="subcategory-title">Tools &amp; Advice</span>
                                                                    <span class="subcategory-icon icon-chevron-right"></span>
                                                                </a>
                                                            </h5>
                                                            <ul class="subcategory-list collapse"
                                                                id="navMenuMobToolsCollapse${nodelinkname}">
                                                                <div class="list-wrapper">
                                                                    <li>
                                                                    		<c:forEach items="${childrenNode.toolAdviceLinks}" var="tooladviceLink" varStatus="linkLoop">
	                                                                            		<cms:component component="${tooladviceLink}" element="div"/>
	                                                                      </c:forEach>
                                                                    </li>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                            </c:forEach>
                                            
                                        </div>
                                    </div>
		</c:when>    
		<c:otherwise>
			<cms:component component="${component.navigationNode.nodeLink}"  />
		</c:otherwise>
	</c:choose>
</li>
                                