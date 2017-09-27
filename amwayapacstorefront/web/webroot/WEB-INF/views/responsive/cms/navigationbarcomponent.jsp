<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<c:set value="${component.styleClass} ${dropDownLayout}" var="bannerClasses"/>

<li role="presentation" class=" nav-list-item js-remove-overlay-mobile-menu">
	<c:choose>
	
		<c:when test="${fn:length(component.navigationNode.children) > 1}">
			
			<div class="overlay-menu-component-desktop-wrapper">
					<div data-toggle="collapse" href="#overlay-menu-wrapper" aria-expanded="false" aria-controls="overlay-menu-wrapper" class="overlay-menu-toggle-desktop js-overlay-menu-toggle-desktop collapsed">
						<cms:component component="${component.navigationNode.nodeLink}"  />
						<i class="main-menu__arrow-down glyphicon glyphicon-menu-down"></i>
					</div>
					
					<div id="overlay-menu-wrapper" class="collapse hidden-sm overlay-menu-container">
                                            <div class="overlay-menu row">
                                                <div class="col-sm-3 overlay-menu-headers">
                                                    <div class="menu-headers-container">
                                                        <div>
                                                            <ul class="tab-header-list" role="tablist">
                                                           	<c:forEach items="${component.navigationNode.children}" var="childrenNode" varStatus="loop">
                                                            		
                                                            			
																		
																	        <li role="presentation" class="tab-header-item <c:if test="${loop.index==0}">active</c:if> ">
																		        <div class="arrow"></div>
	                                                            					
				                                                                    <a href="#navMenuTab${loop.index}" aria-controls="navMenuTab0" role="tab" data-toggle="tab" class="tab-toggle">
				                                                                        <div class="wrapper">
				                                                                            <span class="tab-image">
				                                                                            
				                                                                                <img src="${childrenNode.nodeImg.url}" />
				                                                                            </span>
				                                                                            <span class="tab-title">${childrenNode.nodeLink.linkName}</span></div>
				                                                                    </a>
                                                            				
                                                            					</li>
																    		
                                                            				
                                                            					
                                                            					
                                                            			
                                                            		</c:forEach>
                                                            		
                                                            		
                                                                
                                                               
                                                                
                                                                
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-9 tab-content">
                                                
                                                		<c:forEach items="${component.navigationNode.children}" var="childrenNode" varStatus="loop">
	                                                		<div role="tabpanel" class="tab-pane active" id="navMenuTab${loop.index}">
	                                                        <div class="overlay-menu-tab">
	                                                            <div class="overlay-menu-tab-first-col">
	                                                                <div class="overlay-menu__tab-wrapper">
	                                                                    <div class="overlay-menu-tab-row">
	                                                                        <div class="overlay-menu-tab-categories clearfix row">
	                                                                        
	                                                                        	<c:forEach items="${childrenNode.children}" var="grandChildrenNode" varStatus="loop">
	                                                                        	
	                                                                        		<div class="col-sm-3 tab__category">
	                                                                        			
	                                                                        		  	<cms:component component="${grandChildrenNode.nodeLink}" element="span" class="category-top-link"/>
	                                                                                
		                                                                                <ul class="category-links">
		                                                                                	<c:forEach items="${grandChildrenNode.links}" var="grandChildrenLinks">
		                                                                                	
		                                                                                		<cms:component component="${grandChildrenLinks}" evaluateRestriction="true" element="li" />
							                            
		                                                                                	</c:forEach>
		                                                                                    
		                                                                                </ul>
		                                                                                
		                                                                                <c:if test="${loop.index==0}">
		                                                                                	<a href="categoryPage.html" class="overlay-tab-title-bottom">more categories</a>
		                                                                                </c:if>
		                                                                                
	                                                                              </div>
	                                                                        	
	                                                                        	</c:forEach>
	                                                                        	
	                                                                           
	                                                        
	                                                                        </div>
	                                                                    </div>
	                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-brands">
	                                                                        <div class="">
	                                                                            <h6>Shop by Brands</h6>
	                                                                            <div class="row images-container">
	                                                                            		
	                                                                            		<c:forEach items="${childrenNode.simpleBannerComponents}" var="banner" varStatus="loop">
	                                                                            			<div class=" col-md-3">
	                                                                            				<cms:component component="${banner}" element="div" class="banner__component banner"/>
	                                                                            			</div>
	                                                                            		</c:forEach>
	                                                                            		
	                                                                            </div>
	                                                                        </div>
	                                                                    </div>
	                                                                    <div class="overlay-menu-tab-row overlay-menu-tab-tools">
	                                                                        <h6>Tools &amp; Advice</h6>
	                                                                        <ul class="tools-list">
	                                                                            <li class=" tools-item">
	                                                                            	<cms:component component="${childrenNode.nodeToolAdviceLink}" element="div"/>
	                                                                            </li>
	                                                                        </ul>
	                                                                    </div>
	                                                                </div>
	                                                            </div>
	                                                            <div class=" col-sm-3 overlay-menu__tab__banner">
	                                                                <div class="banner__component banner">
	                                                                    <a href="#">
	                                                                        <img title="tab_banner.jpg" alt="tab_banner.jpg" src="./Checkout _ Lynx Site_files/tab-banner.jpg" /></a>
	                                                                </div>
	                                                            </div>
	                                                        </div>
	                                                    </div>
                                                   </c:forEach>                     		
                                                	    
                                                    
                                                    
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
			
			
	 	</c:when>    
		<c:otherwise>
			<cms:component component="${component.navigationNode.nodeLink}"  />
		</c:otherwise>
	</c:choose>
</li>

