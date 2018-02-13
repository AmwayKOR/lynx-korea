<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="shippingdiv" class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header">
		<spring:theme code="checkout.payment.options" />
	</p>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio1" value="radio1">
		<label for="radio1" class="collapsed" data-toggle="collapse" href="#collapseOne" data-parent="#shippingdiv">
			<spring:theme code="checkout.payment.saved.credit.card" />
		</label>
		<div id="collapseOne" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">
				<spring:theme code="checkout.payment.saved.credit.card" />
			</div>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio2">
		<label for="radio2" data-toggle="collapse" href="#collapseTwo" data-parent="#shippingdiv">
			<spring:theme code="checkout.payment.new.credit.card" />
		</label>
		<div id="collapseTwo" class="shipping-delivery-radio-body panel-collapse collapse in">
			<form:form id="silentOrderPostForm" name="silentOrderPostForm" commandName="sopPaymentDetailsForm"
				action="${paymentFormUrl}" method="POST">
				<input type="hidden" name="orderPage_receiptResponseURL"
					value="${fn:escapeXml(silentOrderPageData.parameters['orderPage_receiptResponseURL'])}" />
				<input type="hidden" name="orderPage_declineResponseURL"
					value="${fn:escapeXml(silentOrderPageData.parameters['orderPage_declineResponseURL'])}" />
				<input type="hidden" name="orderPage_cancelResponseURL"
					value="${fn:escapeXml(silentOrderPageData.parameters['orderPage_cancelResponseURL'])}" />
				<c:forEach items="${sopPaymentDetailsForm.signatureParams}" var="entry" varStatus="status">
					<input type="hidden" id="${entry.key}" name="${entry.key}" value="${fn:escapeXml(entry.value)}" />
				</c:forEach>
				<c:forEach items="${sopPaymentDetailsForm.subscriptionSignatureParams}" var="entry" varStatus="status">
					<input type="hidden" id="${entry.key}" name="${entry.key}" value="${fn:escapeXml(entry.value)}" />
				</c:forEach>
				<input type="hidden" value="testemail@email.com" name="billTo_email"
					id="billTo_email">


				<div class="form-group">
					<label for="cardNumber">
						<spring:theme code="checkout.payment.new.card.number" />
					</label>
					<input name="card_accountNumber" type="text" />
					<img src="${themeResourcePath}/images/payment-method.png" alt="Visa Master JCB" />
				</div>
				<div class="form-group">
					<label for="addName">
						<spring:theme code="checkout.payment.new.card.name" />
					</label>
					<input name="card_nameOnCard" type="text" />
				</div>
				<div class="form-group">
					<label for="expDate">
						<spring:theme code="checkout.payment.new.card.exp.date" />
					</label>
					<input name="card_expirationMonth" class="expDate" type="text" placeholder="MM" />
					<div class="payment-forms-back-slash">/</div>
					<input name="card_expirationYear" class="expDate" type="text" placeholder="YYYY" />
				</div>
				<div class="form-group">
					<label for="expDate3">
						<spring:theme code="checkout.payment.new.card.security.code" />
					</label>
					<input name="card_cvNumber" type="text" />
				</div>
				<input type="hidden" name="billTo_country" value="US" />
				<input type="hidden" name="billTo_firstName" value="TestFirst" />
				<input type="hidden" name="billTo_lastName" value="TestLast" />
				<input type="hidden" name="billTo_street1" value="TestStreet" />
				<input type="hidden" name="billTo_city" value="TestCity" />
				<input type="hidden" name="billTo_postalCode" value="Test123" />
				<input type="hidden" name="card_cardType" value="002" />
			</form:form>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio3">
		<label for="radio3" class="collapsed" data-toggle="collapse" href="#collapseThree" data-parent="#shippingdiv">
			<spring:theme code="checkout.payment.paypal" />
		</label>
		<div id="collapseThree" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">
				<spring:theme code="checkout.payment.paypal" />
			</div>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio4">
		<label for="radio4" class="collapsed" data-toggle="collapse" href="#collapseFour" data-parent="#shippingdiv">
			<spring:theme code="checkout.payment.bank.draft" />
		</label>
		<div id="collapseFour" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">
				<spring:theme code="checkout.payment.bank.draft" />
			</div>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio5">
		<label for="radio5" class="collapsed" data-toggle="collapse" href="#collapseFive" data-parent="#shippingdiv"><spring:theme code="checkout.payment.interest.free.payment"/></label>
		<div id="collapseFive" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">Bank Draft</div>
		</div>
	</div>
</div>
