<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="spellingSuggestion" required="true" type="de.hybris.platform.commerceservices.search.facetdata.SpellingSuggestionData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:if test="${not empty spellingSuggestion}">
	<div class="searchSpellingSuggestionPrompt">
		<c:url value="/search/?text=${fn:escapeXml(spellingSuggestion.suggestion)}" var="spellingSuggestionQueryUrl"/>
		<spring:theme code="search.spelling.suggestion.prompt"/>&nbsp;<a href="${spellingSuggestionQueryUrl}">${fn:escapeXml(spellingSuggestion.suggestion)}</a>?
	</div>
</c:if>
