<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<div class="row">
                    <!-- recommendation -->
                    <div class="recommendation col-sm-12">
                    		<div class="recommendation__image">
		                            <cms:component component="${banner}"  />
		                </div>
                        <div class="recommendation__message">
                            <blockquote class="recommendation__message-main">
                            		<cms:component component="${description}"  />
							</blockquote>
                            <p class="recommendation__message-signature">${title}</p>
                            <div class="recommendation__message-link ">
                                		<cms:component component="${link}"  />
                             </div>
                        </div>
</div>