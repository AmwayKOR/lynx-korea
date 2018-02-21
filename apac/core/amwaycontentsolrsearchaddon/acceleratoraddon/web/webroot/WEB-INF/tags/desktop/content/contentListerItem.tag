<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="content" required="true" type="com.amway.facade.content.data.ContentData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="content" tagdir="/WEB-INF/tags/desktop/content" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="${content.url}" var="contentUrl"/>

<div class="contentListItem">
	<c:url value="${content.url}" var="contentUrl"/>
	<ycommerce:testId code="test_searchPage_wholecontent">
			<br/>
			<h3>
				<a href="${contentUrl}" title="${content.title}" class="contentMainLink">
					${not empty content.title ? content.title : content.name}				
				</a>
			</h3>
	</ycommerce:testId>
	<span id="contentListItem">${content.shortPageContent}</span>
</div>
