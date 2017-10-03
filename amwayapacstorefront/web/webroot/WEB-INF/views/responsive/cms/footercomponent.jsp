<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"  %>

<div class="quick-links">
	    <div class="container">
	        <div class="row">
	            <ul id="accordion" class="col-sm-8">
	            
	            <c:forEach items="${navigationNodes}" var="node">
                        <c:if test="${node.visible}">
                        		<c:forEach items="${node.children}" var="childnode" varStatus="loop">
	                        			<li class="quick-links-item col-sm-3 panel">
			                        		<h5 class="quick-links-header">${childnode.nodeTitle}</h5>
			                        		<ul id="quick-links-collapse1" class="panel-collapse collapse">
			                        				<c:forEach items="${childnode.links}" varStatus="i" var="childlink">
													<cms:component component="${childlink}" evaluateRestriction="true" element="li"/>
					                            
					                            <c:if test="${loop.last == true && i.last == true}">
					                            		<li>
							                            <p class="quick-links-title">NEED HELP?</p>
							                            <img src="${themeResourcePath}/images/footer_chat.png" />
							                            <a href="#">Chat Live Now</a>
							                        </li>
					                            </c:if>
					                            
					                            </c:forEach>
					                     </ul>
				                     </li>
	                         </c:forEach>
                        </c:if>
                 </c:forEach>
	            
	                
	                
	            </ul>
	            <div class="quick-links-misc col-sm-4">
	                <ul>
	                    <li>
	                        <p class="quick-links-title">RECEIVE NEWS &amp; OFFERS</p>
	                        <form class="form-inline">
	                            <input type="email" class="quick-links-email" placeholder="Email Address" />
	                            <a href="#" class="btn-blue-white">subscribe</a></form>
	                    </li>
	                    <li>
	                        <p class="quick-links-title">${title1}</p>
	                        <div class="foot-social-icon">
	                        		<c:forEach items="${simpleBannerComponents}" var="banner" varStatus="status">
			                			<cms:component component="${banner}"/>
								</c:forEach>
	                            
	                        </div>
	                    </li>
	                    <li>
	                        <!--<p class="quick-links-title">CONNECT WITH AMWAY</p>-->
	                        <cms:component component="${amwayLogoBanner}"  />
	                        <cms:component component="${connectionsBanner}"  />
	                    </li>
	                    <li>
	                        <p class="quick-links-title">${title2}</p>
	                        <cms:component component="${downloadAppStoreBanner}"  />
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
</div>


<footer>
    <div class="footer">
        <ul class="footer-nav">
            <li>
                <span>${copyRightTitle}</span></li>
               
            <c:forEach items="${links}" var="link" varStatus="status">
            		<li>
	            		<cms:component component="${link}"/>
	            </li>
			                			
			</c:forEach>
        </ul>
        <div class="footer-logos">
            <cms:component component="${footerBanner}"/>
		</div>
    </div>
</footer>




