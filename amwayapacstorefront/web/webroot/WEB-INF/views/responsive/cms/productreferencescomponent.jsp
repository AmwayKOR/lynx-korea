<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<c:url value="${url}" var="addToCartUrl"/>
<c:choose>
	<c:when test="${not empty productReferences and component.maximumNumberProducts > 0}">
	    <div class="row">
            <div class="amway-suggest col-md-12">
                <h1 class="amway-suggest__title">${fn:escapeXml(component.title)}</h1>
                <div id="productSuggestListTabs" class="amway-suggest__list">
                    <c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
                        <div class="amway-suggest__item-container">
                            <div class="amway-suggest__item">
                                <div class="plp-item-label limit-stock">Limited Stock<span class="right-white"></span></div>
                                <div class="amway-suggest__item-content amwahover">
                                    <product:productPrimaryReferenceImage product="${productReference.target}" format="product" />
                                    <div class="amway-suggest__item-detail">
                                        <p class="amway-suggest__item-title">${productReference.target.name}</p>
                                        <p class="amway-suggest__item-count">30 Count</p>
                                        <p class="amway-suggest__item-number"><spring:theme code="product.item.number.label" />: ${fn:escapeXml(productReference.target.code)}</p>
                                        <div class="amway-suggest__item-title amway-suggest__item-aboprice">
                                            <span class="amway-suggest__item-abolabel"><spring:theme code="product.volumePrices.column.aboCost" />:</span>
                                            <span class="amway-suggest__item-abovalue"><format:fromPrice priceData="${productReference.target.price}" /></span></div>
                                        <div class="amway-suggest__item-retailprice">
                                            <span class="amway-suggest__item-abolabel"><spring:theme code="product.volumePrices.column.retailPrice" /></span>
                                            <span class="amway-suggest__item-abovalue"><format:fromPrice priceData="${productReference.target.price}" /></span></div>
                                        <div class="amway-suggest__item-retailprice">
                                            <span class="amway-suggest__item-abolabel"><spring:theme code="product.volumePrices.column.pvBV" /></span>
                                            <span class="amway-suggest__item-abovalue">${product.price.amwayValue.pointValue} / ${product.price.amwayValue.businessVolume}</span></div>
                                    </div>
                                </div>
                                <div class="amway-suggest__item-link">
                                    <button type="submit" class="btn btn-primary btn-block js-add-to-cart js-enable-btn col-md-6" disabled="disabled"><spring:theme code="basket.add.to.basket"/></button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent />
	</c:otherwise>
</c:choose>