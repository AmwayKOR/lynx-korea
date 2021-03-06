<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty suggestions and component.maximumNumberProducts > 0}">
		<div class="scroller">
			<div class="title">${component.title}</div>
			<ul class="carousel jcarousel-skin  popup">
				<c:forEach end="${component.maximumNumberProducts}" items="${suggestions}" var="suggestion">
					<c:url value="${suggestion.url}/quickView" var="productQuickViewUrl"/>
					<li>
						<a href="${productQuickViewUrl}" class="popup scrollerProduct">
							<div class="thumb">
								<product:productPrimaryImage product="${suggestion}" format="product"/>
							</div>
							<c:if test="${component.displayProductPrices}">
								<div class="priceContainer">
									<format:fromPrice priceData="${suggestion.price}"/>
								</div>
							</c:if>
							<c:if test="${component.displayProductTitles}">
								<div class="details">
									${suggestion.name}
								</div>
							</c:if>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>
