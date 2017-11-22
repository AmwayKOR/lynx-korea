<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderHistoryData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/account"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tr class="responsive-table-item order-history-table-item js-order-toggle js-expand-order" data-order-id="${order.placed}">
	<ycommerce:testId code="orderHistoryItem_orderDetails_link">
		<td class="responsive-table-cell date-cell">
			<span class="icon icon-chevron-right"></span>
			<span class="icon icon-chevron-down display-none"></span>
			<span class="date">
				<fmt:formatDate value="${order.placed}" pattern="MM/dd/yyyy"/>
			</span>
		</td>
		<td class="responsive-table-cell responsive-table-cell-bold">
			
		</td>
		<td class="status">
			<spring:theme code="text.account.order.status.display.${order.statusDisplay}"/>	
		</td>
		<td class="responsive-table-cell hidden-xs">
			<span>Not sure</span>
		</td>
		<td class="responsive-table-cell hidden-xs">
			${order.code}
		</td>
	</ycommerce:testId>
</tr>
