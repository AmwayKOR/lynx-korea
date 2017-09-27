<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<div class="product-recentlyviewed__imagelist featured_brandsList" id="featured_brandsList">
	<c:forEach items="${banners}" var="banner" varStatus="status">
		<c:if test="${status.count <= loopCount }">
			<div>
			       <img class="product-recentlyviewed__img" src="${banner.media.url}" />
			</div>
		</c:if>
                			
	</c:forEach>

   
</div>