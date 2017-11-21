<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="shippingdiv" class="shipping-delivery-ship">
	<p class="shipping-delivery-shipping-header">payment options</p>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio1" value="radio1">
		<label for="radio1" class="collapsed" data-toggle="collapse" href="#collapseOne" data-parent="#shippingdiv">Saved
			Credit Card</label>
		<div id="collapseOne" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">Saved Credit Card</div>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio2">
		<label for="radio2" data-toggle="collapse" href="#collapseTwo" data-parent="#shippingdiv">New Credit Card</label>
		<div id="collapseTwo" class="shipping-delivery-radio-body panel-collapse collapse in">
			<%-- <form:form id="silentOrderPostForm" name="silentOrderPostForm" commandName="sopPaymentDetailsForm"
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
				<input type="hidden" value="${fn:escapeXml(silentOrderPageData.parameters['billTo_email'])}" name="billTo_email"
					id="billTo_email">
				<div class="form-group">
					<formElement:formInputBox idKey="card_cardType" labelKey="payment.cardType" path="card_cardType"
						inputCSS="form-control" tabindex="2" mandatory="false" />
				</div>
				<div class="form-group">
					<formElement:formInputBox idKey="card_nameOnCard" labelKey="payment.nameOnCard" path="card_nameOnCard"
						inputCSS="form-control" tabindex="2" mandatory="false" />
				</div>

				<div class="form-group">
					<formElement:formInputBox idKey="card_accountNumber" labelKey="payment.cardNumber" path="card_accountNumber"
						inputCSS="form-control" mandatory="true" tabindex="3" autocomplete="off" />
				</div>
				<fieldset id="cardDate">
					<label for="" class="control-label">
						<spring:theme code="payment.expiryDate" />
					</label>
					<div class="row">
						<div class="col-xs-6">
							<formElement:formInputBox idKey="ExpiryMonth" inputCSS="form-control" labelKey="payment.month"
								path="card_expirationMonth" mandatory="true" 
								tabindex="6" />
						</div>
						<div class="col-xs-6">
							<formElement:formInputBox idKey="ExpiryYear" inputCSS="form-control" labelKey="payment.year"
								path="card_expirationYear" mandatory="true"
								tabindex="7" />
						</div>
						<div class="row">
							<div class="col-xs-6">
								<formElement:formInputBox idKey="card_cvNumber" labelKey="payment.cvn" path="card_cvNumber"
									inputCSS="form-control" mandatory="true" tabindex="8" />
							</div>
						</div>
					</div>
				</fieldset>
				<button type="submit" >asdasjdhas</button>
			</form:form> --%>

			<form action="" method="post" name="editAddress">
				<fieldset>
					<div class="form-group">
						<label for="cardNumber">Card number</label>
						<input name="cardNumber" type="text" />
						<img src="${themeResourcePath}/images/payment-method.png" alt="Visa Master JCB" />
					</div>
					<div class="form-group">
						<label for="addName">name on card</label>
						<input name="addName" type="text" />
					</div>
					<div class="form-group">
						<label for="expDate">expiration date</label>
						<input name="expDate" type="text" placeholder="MM" />
						<div class="payment-forms-back-slash">/</div>
						<input name="expDate2" type="text" placeholder="YY" />
					</div>
					<div class="form-group">
						<label for="expDate3">security code</label>
						<input name="expDate3" type="text" />
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio3">
		<label for="radio3" class="collapsed" data-toggle="collapse" href="#collapseThree" data-parent="#shippingdiv">PayPal</label>
		<div id="collapseThree" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">PayPal</div>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio4">
		<label for="radio4" class="collapsed" data-toggle="collapse" href="#collapseFour" data-parent="#shippingdiv">Bank
			Draft</label>
		<div id="collapseFour" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">Bank Draft</div>
		</div>
	</div>
	<div class="panel">
		<input class="amwa-radio" type="radio" name="optradio" id="radio5">
		<label for="radio5" class="collapsed" data-toggle="collapse" href="#collapseFive" data-parent="#shippingdiv">2
			Interest-Free Payments of $XX.XX</label>
		<div id="collapseFive" class="shipping-delivery-radio-body panel-collapse collapse">
			<div class="panel-body">Bank Draft</div>
		</div>
	</div>
</div>
