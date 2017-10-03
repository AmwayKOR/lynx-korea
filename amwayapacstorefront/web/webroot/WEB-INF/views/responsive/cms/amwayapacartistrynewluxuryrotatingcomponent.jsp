<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<div id="sub_banner_list" class="sub_ban" style="background-image: url(${image.url});background-color: #ffffff;background-repeat-x: no-repeat;background-repeat-y: no-repeat;">

	
	
	<c:forEach items="${banners}" var="banner" varStatus="status">
                			<cms:component component="${banner}"/>
	</c:forEach>
                    
</div>