<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<c:url var="businessInformationUrl" value="/my-account/business-information" />
<c:url var="orderHistoryUrl" value="/my-account/orders" />
<c:url var="billingAndShippingUrl" value="/my-account/billing-shipping" />
<c:url var="autoRenewalUrl" value="/my-account/auto-renewal" />
<c:url var="contractsUrl" value="/my-account/contracts" />

<div class="row">
	<div class="account-landing-page">
		<div class="container-fluid">
			<div class="row user-account-options">
				<div class="container">
					<div class="row">
						<cms:pageSlot position="Section1A" var="component">
							<div class="option-item-container col-xs-6 col-md-4">
								<a href="${businessInformationUrl}">
									<div class="option-item">
										<cms:component component="${component}" />
									</div>
								</a>
							</div>
						</cms:pageSlot>
						<cms:pageSlot position="Section1B" var="component">
							<div class="option-item-container col-xs-6 col-md-4">
								<a href="${orderHistoryUrl}">
									<div class="option-item">
										<cms:component component="${component}" />
										<div class="option-item-footer">
											<span>
												<spring:theme code="account.order.history.count.prefix" />
												<span class="option-item-footer-digit">${numberOfOrders}</span>
												<spring:theme code="account.order.history.count.suffix" />
											</span>
										</div>
									</div>
								</a>
							</div>
						</cms:pageSlot>
						<cms:pageSlot position="Section1C" var="component">
							<div class="option-item-container col-xs-6 col-md-4">
								<a href="${billingAndShippingUrl}">
									<div class="option-item">
										<cms:component component="${component}" />
									</div>
								</a>
							</div>
						</cms:pageSlot>
						<cms:pageSlot position="Section2B" var="component">
							<div class="option-item-container col-xs-6 col-md-4">
								<a href="${autoRenewalUrl}">
									<div class="option-item">
										<cms:component component="${component}" />
										<div class="option-item-footer">
											<span>
												<spring:theme code="account.abo.status.label" />
												<span class="icon-check-bold"></span>
												<span class="active-green">active</span>
											</span>
										</div>
									</div>
								</a>
							</div>
						</cms:pageSlot>
						<cms:pageSlot position="Section2C" var="component">
							<div class="option-item-container col-xs-6 col-md-4">
								<a href="${contractsUrl}">
									<div class="option-item">
										<cms:component component="${component}" />
										<div class="option-item-footer">
											<span>
												<spring:theme code="account.abo.contracts.count.prefix" />
												<span class="option-item-footer-digit">0</span>
												<spring:theme code="account.abo.contracts.count.suffix" />
											</span>
										</div>
									</div>
								</a>
							</div>
						</cms:pageSlot>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>