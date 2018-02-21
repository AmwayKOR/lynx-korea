<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="amway-suggest col-md-12">
	<h1 class="amway-suggest__title">${component.name}</h1>
	<div id="productSuggestListTabs" class="amway-suggest__list">
		<c:forEach items="${productData}" var="product">
			<product:productCarouselListerGridItem product="${product}"/>
		</c:forEach>
	</div>
</div>
