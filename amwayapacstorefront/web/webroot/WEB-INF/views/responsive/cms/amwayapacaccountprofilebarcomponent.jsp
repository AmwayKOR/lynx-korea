<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="go-block col-sm-8">
	<div class="amway-theme">
		<div class="jump-to">
			<span>
				<spring:theme code="account.jumpTo.label" />
			</span>
		</div>
		<div class="jump-to-wrapper">
			<select class="js-jump-to-selection hide select2-hidden-accessible" tabindex="-1" aria-hidden="true">
				<c:forEach items="${links}" var="pageUrl" varStatus="status">
					<cms:component component="${pageUrl}" />
				</c:forEach>
			</select>
			<input type="button" class="js-jump-to-selection-btn" value="<spring:theme code="account.jumpTo.go.label" />">
		</div>
	</div>
</div>