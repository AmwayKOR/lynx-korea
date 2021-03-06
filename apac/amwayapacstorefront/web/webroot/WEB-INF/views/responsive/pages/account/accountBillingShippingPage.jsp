<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>

<div class="container">
	<div id="dittoRelatedAddressPopupTitle" data-popup-title="Change delivery address in the related Ditto schedules">
		<div class="billing-shipping">
			<div class="panel-group accordion-billing-shipping" id="billingShippingAccordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel">
					<div class="panel-heading" role="tab" id="paymentInformation">
						<h4 class="panel-title">
							<div role="button" class="" data-toggle="collapse" data-parent="#billingShippingAccordion"
								href="#paymentInformationBody" aria-controls="paymentInformationBody">
								<span class="icon-billing-shipping icon-credit-card"></span>
								<span class="text-uppercase panel-title-text">Payment information</span>
								<span class="indicator pull-right"></span>
							</div>
						</h4>
					</div>
					<div id="paymentInformationBody" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<c:choose>
								<c:when test="${not empty paymentInfoData}">
									<c:forEach items="${paymentInfoData}" var="paymentInfo">

										<div class="account-paymentdetails account-list ">
											<div class="account-cards card-select">
												<div>
													<div class="payment-info-container container-fluid">
														<div id="" class="row">
															<div class="col-xs-6 card-info-block">
																<div class="card-image">

																	<c:choose>
																		<c:when test="${fn:escapeXml(paymentInfo.cardTypeData.code) eq 'visa'}">
																			<img src="${themeResourcePath}/images/visa.png" />
																		</c:when>
																		<c:when test="${fn:escapeXml(paymentInfo.cardTypeData.code) eq 'master'}">
																			<img src="${themeResourcePath}/images/mastercard.png" />
																		</c:when>
																		<c:otherwise>
																			<img src="${themeResourcePath}/images/mastercard.png" />
																		</c:otherwise>
																	</c:choose>

																</div>
																<div class="card-info">
																	<div class="card-data">
																		<span class="card-code">${fn:escapeXml(paymentInfo.cardTypeData.name)}</span>
																		<span class="panel-title-text">ending in</span>
																		<ycommerce:testId code="paymentDetails_item_cardNumber_text">${fn:substring(fn:escapeXml(paymentInfo.cardNumber), 12, 16)}</ycommerce:testId>
																	</div>
																</div>
																<div class="card-info">
																	<div class="card-data expires-message-warpper">
																		<span class="expires-label"> Expires</span>
																		<span>
																			<c:if test="${paymentInfo.expiryMonth lt 10}">0</c:if>${fn:escapeXml(paymentInfo.expiryMonth)}/${fn:escapeXml(paymentInfo.expiryYear)}</span>
																	</div>
																</div>
															</div>
															<div class="col-xs-6 block-right">
																<div class="address-block">
																	<div class="address-title">
																		<span class="panel-title-text">Billing address</span>
																	</div>
																	<div>
																		${fn:escapeXml(paymentInfo.billingAddress.line1)}&nbsp;${fn:escapeXml(paymentInfo.billingAddress.line2)}
																	</div>
																	<div>
																		${fn:escapeXml(paymentInfo.billingAddress.town)}&nbsp;${fn:escapeXml(paymentInfo.billingAddress.country.name)}&nbsp;${fn:escapeXml(paymentInfo.billingAddress.postalCode)}
																	</div>

																</div>

																<div class="button-block">
																	<button class="btn btn-primary edit">Edit</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="editPay" class="container-fluid payment-new-wrapper js-create-accertify-session ">
											<div class="creat-new-wrapper main row">
												<div class="create-payment-container form-container col-lg-12">
													<div id="new-payment-info-form" class="js-new-payment-info-wrapper">
														<form id="newCreditCardPostForm" name="paymentInfoPostForm" class="new-payment-info" action=""
															method="POST">

															<div class="container-fluid">
																<div class="row">
																	<div class="card-block col-md-5">
																		<div class="card-image card-image-block col-md-1">
																			<c:choose>
																				<c:when test="${fn:escapeXml(paymentInfo.cardTypeData.code) eq 'visa'}">
																					<img src="${themeResourcePath}/images/visa.png" />
																				</c:when>
																				<c:when test="${fn:escapeXml(paymentInfo.cardTypeData.code) eq 'master'}">
																					<img src="${themeResourcePath}/images/mastercard.png" />
																				</c:when>
																				<c:otherwise>
																					<img src="${themeResourcePath}/images/mastercard.png" />
																				</c:otherwise>
																			</c:choose>
																		</div>
																		<div class="card-form col-md-11 card-form-wrapper">
																			<input type="hidden" name="paymentToken" id="paymentToken" />
																			<input type="hidden" name="paymentProvider" id="paymentProvider" />
																			<input type="hidden" name="cardType" id="cardType" />
																			<div>
																				<label class="control-label" for="card-number"> Card number</label>
																				<div class="help-block card-number-block">
																					<input type="text" id="card-number" class="text form-control gw-proxied"
																						value="************${fn:substring(fn:escapeXml(paymentInfo.cardNumber), 12, 16)}" style="" />

																					<span class="has-error js-card-number-error hide"> Please enter a valid credit card number</span>
																				</div>
																			</div>
																			<div>
																				<label class="control-label" for="expiry-month"> Expires</label>
																				<div class="help-block expiry-block">
																					<input type="text" id="expiry-month" class="text form-control"
																						value="<c:if test="${paymentInfo.expiryMonth lt 10}">0</c:if>${fn:escapeXml(paymentInfo.expiryMonth)}" />
																					<span class="has-error js-expiry-month-error hide"> Please enter a valid expiry month</span>
																				</div>
																				<span class="separator">/</span>
																				<div class="help-block expiry-block">
																					<input type="text" id="expiry-year" class="text form-control"
																						value="${fn:escapeXml(paymentInfo.expiryYear)}" />
																					<span class="has-error js-expiry-year-error hide"> Please enter a valid expiry year</span>
																				</div>
																			</div>
																			<div>
																				<label class="control-label" for="security-code"> CVV</label>
																				<div class="help-block security-code-block">
																					<input type="password" id="security-code" class="text form-control gw-proxied" value="456" />

																					<span class="has-error js-security-code-error hide"> Please enter a valid security code</span>
																				</div>
																			</div>
																			<div class="checkbox-container">
																				<div class="form-group">
																					<div class="checkbox">
																						<label class="control-label width-auto">
																							<input id="paymentInfo" name="primaryCard"
																								class="js-mandatory-payment-meyhod-changed  _checkbox-element-global-class" type="checkbox"
																								value="true" />
																							<input type="hidden" name="_primaryCard" value="on" />
																							<span class="_checkbox-element-global-span"></span>
																							<span class="checkbox-text _checkbox-element-global-text"> Use as Primary Card</span>
																							<span class="mandatory"> </span>
																							<span class="skip"></span>
																						</label>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="address-block col-md-7">
																		<div class="row">
																			<div class="address-form col-md-10">
																				<div class="headline">Billing Address</div>
																				<div>
																					<div id="defaultMailingAddressSection">
																						<label class="control-label _checkbox-element-global-label">
																							<input id="useDefaultMailingAddressCheckbox" type="checkbox"
																								class="_checkbox-element-global-class" />
																							<span class="_checkbox-element-global-span"></span>
																							<span class="checkbox-text _checkbox-element-global-text"> Use Existing Primary Mailing
																								Address</span>
																						</label>
																						<br />
																						<input type="hidden" id="defaultMailingAddressName" value="" />
																						<input type="hidden" id="defaultMailingAddressLine1" value="489 Friendship Lane" />
																						<input type="hidden" id="defaultMailingAddressLine2" value="" />
																						<input type="hidden" id="defaultMailingAddressCity" value="Oakland" />
																						<input type="hidden" id="defaultMailingAddressRegionIso" value="CA" />
																						<input type="hidden" id="defaultMailingAddressPostalCode" value="94612" />
																					</div>
																					<!-- TODO: will be edited with county selection implementation -->
																					<div class="form-group">
																						<label class="control-label " for="mailingAddressLine1"> Address Line 1</label>
																						<input id="mailingAddressLine1" name="addressLine1" class="text form-control"
																							value="${fn:escapeXml(paymentInfo.billingAddress.line1)}" />
																					</div>
																					<div class="form-group">
																						<label class="control-label " for="mailingAddressLine2"> Address Line 2</label>
																						<input id="mailingAddressLine2" name="addressLine2" class="text form-control"
																							value="${fn:escapeXml(paymentInfo.billingAddress.line2)}" />
																					</div>
																					<div class="form-group">
																						<label class="control-label" for="address.townCity">City/State</label>
																						<input id="mailingAddressCity" name="addressCity" class="form-control" type="text" value="CA" />
																						<select id="mailingAddressRegionIso" class="form-control" name="addressRegionIso">
																							<option title="name" value="AL">AL</option>
																							<option title="name" value="AK">AK</option>
																							<option title="name" value="AS">AS</option>
																							<option title="name" value="AZ">AZ</option>
																							<option title="name" value="AR">AR</option>
																							<option title="name" value="CA" selected>CA</option>
																							<option title="name" value="CO">CO</option>
																							<option title="name" value="CT">CT</option>
																							<option title="name" value="DE">DE</option>
																							<option title="name" value="DC">DC</option>
																							<option title="name" value="FL">FL</option>
																							<option title="name" value="GA">GA</option>
																							<option title="name" value="GU">GU</option>
																							<option title="name" value="HI">HI</option>
																							<option title="name" value="ID">ID</option>
																							<option title="name" value="IL">IL</option>
																							<option title="name" value="IN">IN</option>
																							<option title="name" value="IA">IA</option>
																							<option title="name" value="KS">KS</option>
																							<option title="name" value="KY">KY</option>
																							<option title="name" value="LA">LA</option>
																							<option title="name" value="ME">ME</option>
																							<option title="name" value="MD">MD</option>
																							<option title="name" value="MA">MA</option>
																							<option title="name" value="MI">MI</option>
																							<option title="name" value="MN">MN</option>
																							<option title="name" value="MS">MS</option>
																							<option title="name" value="MO">MO</option>
																							<option title="name" value="MT">MT</option>
																							<option title="name" value="NE">NE</option>
																							<option title="name" value="NV">NV</option>
																							<option title="name" value="NH">NH</option>
																							<option title="name" value="NJ">NJ</option>
																							<option title="name" value="NM">NM</option>
																							<option title="name" value="NY">NY</option>
																							<option title="name" value="NC">NC</option>
																							<option title="name" value="ND">ND</option>
																							<option title="name" value="MP">MP</option>
																							<option title="name" value="OH">OH</option>
																							<option title="name" value="OK">OK</option>
																							<option title="name" value="OR">OR</option>
																							<option title="name" value="PA">PA</option>
																							<option title="name" value="PR">PR</option>
																							<option title="name" value="RI">RI</option>
																							<option title="name" value="SC">SC</option>
																							<option title="name" value="SD">SD</option>
																							<option title="name" value="TN">TN</option>
																							<option title="name" value="TX">TX</option>
																							<option title="name" value="UM">UM</option>
																							<option title="name" value="VI">VI</option>
																							<option title="name" value="UT">UT</option>
																							<option title="name" value="VT">VT</option>
																							<option title="name" value="VA">VA</option>
																							<option title="name" value="WA">WA</option>
																							<option title="name" value="WV">WV</option>
																							<option title="name" value="WI">WI</option>
																							<option title="name" value="WY">WY</option>
																						</select>
																					</div>
																					<div class="form-group">
																						<label class="control-label " for="mailingAddressPostalCode"> zip</label>
																						<input id="mailingAddressPostalCode" name="addressPostalCode" class="text form-control"
																							value="${fn:escapeXml(paymentInfo.billingAddress.postalCode)}" />
																					</div>
																				</div>
																			</div>
																			<div class="address-buttons col-md-2">
																				<div>
																					<button type="button" id="editFormSubmit" class="btn btn-primary">Save</button>
																				</div>
																				<div>
																					<button id="editCancel" type="button" class="cancel-button js-hide-new-card-form">Cancel</button>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div>
																<input type="hidden" name="CSRFToken" value="f6eebe09-194d-436f-a044-29dc566efe76" />
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:when>
							</c:choose>
							<div id="newPayment" class="container-fluid payment-new-wrapper js-create-accertify-session ">
								<div class="creat-new-wrapper main row">
									<div class="create-payment-container form-container col-lg-12">
										<div id="new-payment-info-form" class="js-new-payment-info-wrapper">
											<form id="newCreditCardPostForm" name="paymentInfoPostForm" class="new-payment-info" action="" method="POST">

												<div class="container-fluid">
													<div class="row">
														<div class="card-block col-md-5">
															<div class="card-image card-image-block col-md-1">
																<div class="no-image-block no-image-block-wrapper"></div>
															</div>
															<div class="card-form col-md-11 card-form-wrapper">
																<input type="hidden" name="paymentToken" id="paymentToken" />
																<input type="hidden" name="paymentProvider" id="paymentProvider" />
																<input type="hidden" name="cardType" id="cardType" />
																<div>
																	<label class="control-label" for="card-number"> Card number</label>
																	<div class="help-block card-number-block">
																		<input type="text" id="card-number" class="text form-control gw-proxied" value="" style="" />

																		<span class="has-error js-card-number-error hide"> Please enter a valid credit card number</span>
																	</div>
																</div>
																<div>
																	<label class="control-label" for="expiry-month"> Expires</label>
																	<div class="help-block expiry-block">
																		<input type="text" id="expiry-month" class="text form-control" value="" />
																		<span class="has-error js-expiry-month-error hide"> Please enter a valid expiry month</span>
																	</div>
																	<span class="separator">/</span>
																	<div class="help-block expiry-block">
																		<input type="text" id="expiry-year" class="text form-control" value="" />
																		<span class="has-error js-expiry-year-error hide"> Please enter a valid expiry year</span>
																	</div>
																</div>
																<div>
																	<label class="control-label" for="security-code"> CVV</label>
																	<div class="help-block security-code-block">
																		<input type="password" id="security-code" class="text form-control gw-proxied" value="" />

																		<span class="has-error js-security-code-error hide"> Please enter a valid security code</span>
																	</div>
																</div>
																<div class="checkbox-container">
																	<div class="form-group">
																		<div class="checkbox">
																			<label class="control-label width-auto">
																				<input id="paymentInfo" name="primaryCard"
																					class="js-mandatory-payment-meyhod-changed  _checkbox-element-global-class" type="checkbox"
																					value="true" />
																				<input type="hidden" name="_primaryCard" value="on" />
																				<span class="_checkbox-element-global-span"></span>
																				<span class="checkbox-text _checkbox-element-global-text"> Use as Primary Card</span>
																				<span class="mandatory"> </span>
																				<span class="skip"></span>
																			</label>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="address-block col-md-7">

															<div class="row">
																<div class="address-form col-md-10">
																	<div class="headline">Billing Address</div>
																	<div>
																		<div id="defaultMailingAddressSection">
																			<label class="control-label _checkbox-element-global-label">
																				<input id="useDefaultMailingAddressCheckbox" type="checkbox" class="_checkbox-element-global-class" />
																				<span class="_checkbox-element-global-span"></span>
																				<span class="checkbox-text _checkbox-element-global-text"> Use Existing Primary Mailing
																					Address</span>
																			</label>
																			<br />
																			<input type="hidden" id="defaultMailingAddressName" value="" />
																			<input type="hidden" id="defaultMailingAddressLine1" value="489 Friendship Lane" />
																			<input type="hidden" id="defaultMailingAddressLine2" value="" />
																			<input type="hidden" id="defaultMailingAddressCity" value="Oakland" />
																			<input type="hidden" id="defaultMailingAddressRegionIso" value="CA" />
																			<input type="hidden" id="defaultMailingAddressPostalCode" value="94612" />
																		</div>
																		<!-- TODO: will be edited with county selection implementation -->
																		<div class="form-group">
																			<label class="control-label " for="mailingAddressLine1"> Address Line 1</label>
																			<input id="mailingAddressLine1" name="addressLine1" class="text form-control" value="" />
																		</div>
																		<div class="form-group">
																			<label class="control-label " for="mailingAddressLine2"> Address Line 2</label>
																			<input id="mailingAddressLine2" name="addressLine2" class="text form-control" value="" />
																		</div>
																		<div class="form-group">
																			<label class="control-label" for="address.townCity">City/State</label>
																			<input id="mailingAddressCity" name="addressCity" class="form-control" type="text" value="" />
																			<select id="mailingAddressRegionIso" class="form-control" name="addressRegionIso">
																				<option title="name" value="AL">AL</option>
																				<option title="name" value="AK">AK</option>
																				<option title="name" value="AS">AS</option>
																				<option title="name" value="AZ">AZ</option>
																				<option title="name" value="AR">AR</option>
																				<option title="name" value="CA">CA</option>
																				<option title="name" value="CO">CO</option>
																				<option title="name" value="CT">CT</option>
																				<option title="name" value="DE">DE</option>
																				<option title="name" value="DC">DC</option>
																				<option title="name" value="FL">FL</option>
																				<option title="name" value="GA">GA</option>
																				<option title="name" value="GU">GU</option>
																				<option title="name" value="HI">HI</option>
																				<option title="name" value="ID">ID</option>
																				<option title="name" value="IL">IL</option>
																				<option title="name" value="IN">IN</option>
																				<option title="name" value="IA">IA</option>
																				<option title="name" value="KS">KS</option>
																				<option title="name" value="KY">KY</option>
																				<option title="name" value="LA">LA</option>
																				<option title="name" value="ME">ME</option>
																				<option title="name" value="MD">MD</option>
																				<option title="name" value="MA">MA</option>
																				<option title="name" value="MI">MI</option>
																				<option title="name" value="MN">MN</option>
																				<option title="name" value="MS">MS</option>
																				<option title="name" value="MO">MO</option>
																				<option title="name" value="MT">MT</option>
																				<option title="name" value="NE">NE</option>
																				<option title="name" value="NV">NV</option>
																				<option title="name" value="NH">NH</option>
																				<option title="name" value="NJ">NJ</option>
																				<option title="name" value="NM">NM</option>
																				<option title="name" value="NY">NY</option>
																				<option title="name" value="NC">NC</option>
																				<option title="name" value="ND">ND</option>
																				<option title="name" value="MP">MP</option>
																				<option title="name" value="OH">OH</option>
																				<option title="name" value="OK">OK</option>
																				<option title="name" value="OR">OR</option>
																				<option title="name" value="PA">PA</option>
																				<option title="name" value="PR">PR</option>
																				<option title="name" value="RI">RI</option>
																				<option title="name" value="SC">SC</option>
																				<option title="name" value="SD">SD</option>
																				<option title="name" value="TN">TN</option>
																				<option title="name" value="TX">TX</option>
																				<option title="name" value="UM">UM</option>
																				<option title="name" value="VI">VI</option>
																				<option title="name" value="UT">UT</option>
																				<option title="name" value="VT">VT</option>
																				<option title="name" value="VA">VA</option>
																				<option title="name" value="WA">WA</option>
																				<option title="name" value="WV">WV</option>
																				<option title="name" value="WI">WI</option>
																				<option title="name" value="WY">WY</option>
																			</select>
																		</div>
																		<div class="form-group">
																			<label class="control-label " for="mailingAddressPostalCode"> zip</label>
																			<input id="mailingAddressPostalCode" name="addressPostalCode" class="text form-control" value="" />
																		</div>
																	</div>
																</div>
																<div class="address-buttons col-md-2">
																	<div>
																		<button type="button" id="newCreditCardFormSubmit" class="btn btn-primary">Save</button>
																	</div>
																	<div>
																		<button type="button" id="newCancel" class="cancel-button js-hide-new-card-form">Cancel</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div>
													<input type="hidden" name="CSRFToken" value="f6eebe09-194d-436f-a044-29dc566efe76" />
												</div>
											</form>
										</div>
									</div>
								</div>

							</div>
							<a class="add-new-payment-info">
								<span class="icon icon-plus"></span>
								<span title="Add new payment method" href="">Add new payment method</span>
							</a>
						</div>
					</div>
				</div>
				<div class="panel">
					<div class="panel-heading" role="tab" id="billingShipping">
						<h4 class="panel-title">
							<div role="button" class="collapsed" data-toggle="collapse" data-parent="#billingShippingAccordion"
								href="#billingShippingBody" aria-controls="billingShippingBody">
								<span class="icon-billing-shipping icon-home2"></span>
								<span class="text-uppercase panel-title-text">Shipping addresses</span>
								<span class="indicator pull-right"></span>
							</div>
						</h4>
					</div>
					<c:if test="${empty addressData}">
					</c:if>
					<div id="billingShippingBody" class="panel-collapse collapse billing-and-shipping-section" role="tabpanel"
						aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="primary-alternate-body">
								<c:if test="${not empty addressData}">
									<div class="primary-address-section container-fluid">
										<div class="header">
											<span class="primary-text"> Primary addresses</span>
										</div>
										<div class="main row">
											<c:forEach items="${addressData}" var="address">
												<c:if test="${address.defaultAddress}">
													<div class="address-container col-xs-12 col-md-6">
														<div class="form-container">
															<div class="mailing-address">
																<address:shippingAddressDetail addressData="${address}" />
															</div>
															<c:set var="addressID" scope="request" value="${fn:escapeXml(address.id)}" />
															<c:set var="countryISO" scope="request" value="${fn:escapeXml(address.country.isocode)}" />
															<div class="error-mailing-address"></div>
															<div class="mailing-address-edit">
																<h4>SHIPPING ADDRESS</h4>
																
																<div class="addressEdit"></div>

															</div>
														</div>

													</div>
												</c:if>
											</c:forEach>
										</div>
									</div>
									<div class="alternative-address-section container-fluid">
										<div class="header">
											<span class="primary-text"> Alternate addresses</span>
										</div>
										<div class="main row">
											<c:forEach items="${addressData}" var="address">
												<c:if test="${not address.defaultAddress}">
													<div class="address-container col-xs-12 col-md-6">
														<div class="form-container">
															<div class="mailing-address">
																<address:shippingAddressDetail addressData="${address}" />
															</div>

															<c:set var="addressID" scope="request" value="${fn:escapeXml(address.id)}" />
															<c:set var="countryISO" scope="request" value="${fn:escapeXml(address.country.isocode)}" />
															<div class="error-mailing-address"></div>
															<div class="mailing-address-edit">
																<h4>SHIPPING ADDRESS</h4>
																
																<div class="addressEdit"></div>
															</div>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</div>
							<div class="add-alternate-address alternative-address-section container-fluid">
								<div class="main row">
									<div class="address-container col-xs-12 col-md-6">
										<div class="add-new-alternate-address js-add-new-address-btn">
											<span class="icon icon-plus"></span>
											<a title="Add New Alternative Address" class="add-alternate">Add New Alternative Address</a>

										</div>
										<div class="form-container">
											<div class="new-alternate-address mailing-address-edit">
												<div class="error-mailing-address"></div>
												<c:url value="/my-account/add-address" var="addAddressUrl" />
												<form:form id="shipping-address-form-new" action="${addAddressUrl}" commandName="addressForm" method="post">
													<fieldset>
														<form:hidden path="regionIso" value="${country}"/>
														<form:hidden path="countryIso" value="${country}"/>
														
														
														<address:addressFormElements user="${customerData}" regions="${regions}" country="${country}" />


														<button class="btn-save btn btn-primary" type="button"
															onclick="ACC.billingshipping.submitFormForAddAddress(this);" form="shipping-address-form-new">SAVE</button>

														<div class="button-right-check billingShipping">
															<form:checkbox id="address.defaultAddress-new" path="defaultAddress" />
															<label for="address.defaultAddress-new">
																<spring:theme code="address.defaultAddress" />
															</label>
														</div>
													</fieldset>
												</form:form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>