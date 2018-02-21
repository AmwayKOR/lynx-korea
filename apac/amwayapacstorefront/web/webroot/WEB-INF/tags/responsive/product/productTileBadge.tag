<%@ attribute name="stockLevel" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="showStockBadge"
	value="${(stockLevel eq 'outOfStock') || (stockLevel eq 'noLongerAvailable') || (stockLevel eq 'temporaryNotAvailable') || (stockLevel eq 'notYetAvailable') || (stockLevel eq 'backOrder') || (stockLevel eq 'notSpecified') || (stockLevel eq 'lowStock')}" />
<c:if test="${showStockBadge}">
	<div class="plp-item-label limit-stock">
		<spring:theme code="plp.stock.level.${stockLevel}" />
		<span class="right-white"></span>
	</div>
</c:if>