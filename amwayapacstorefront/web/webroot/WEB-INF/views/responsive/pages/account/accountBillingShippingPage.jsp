<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
            <div class="container">
                <div id="dittoRelatedAddressPopupTitle"
                    data-popup-title="Change delivery address in the related Ditto schedules">
                    <div class="billing-shipping">
                        <div class="panel-group accordion-billing-shipping" id="billingShippingAccordion" role="tablist"
                            aria-multiselectable="true">
                            <div class="panel">
                                <div class="panel-heading" role="tab" id="paymentInformation">
                                    <h4 class="panel-title">
                                        <div role="button" class="" data-toggle="collapse"
                                            data-parent="#billingShippingAccordion" href="#paymentInformationBody"
                                            aria-controls="paymentInformationBody">
                                            <span class="icon-billing-shipping icon-credit-card"></span>
                                            <span class="text-uppercase panel-title-text">Payment information</span>
                                            <span class="indicator pull-right"></span>
                                        </div>
                                    </h4>
                                </div>
                                <div id="paymentInformationBody" class="panel-collapse collapse in" role="tabpanel"
                                    aria-labelledby="headingOne">
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
			                                                                    				<img src="${themeResourcePath}/images/visa.png"/>
			                                                                    			</c:when>
			                                                                    			<c:when test="${fn:escapeXml(paymentInfo.cardTypeData.code) eq 'master'}">
			                                                                    				<img src="${themeResourcePath}/images/mastercard.png"/>
			                                                                    			</c:when>
			                                                                    			<c:otherwise>
			                                                                    				<img src="${themeResourcePath}/images/mastercard.png"/>
			                                                                    			</c:otherwise>
			                                                                    		</c:choose>
			                                                                        
			                                                                    </div>
			                                                                    <div class="card-info">
			                                                                        <div class="card-data">
			                                                                            <span class="card-code">${fn:escapeXml(paymentInfo.cardTypeData.name)}</span>
			                                                                            <span class="panel-title-text">ending in</span>
			                                                                            <!-- <span class="expiration-date">0008</span> -->
			                                                                            <!--<c:set var="creditcardNumber" value="${fn:split(fn:escapeXml(paymentInfo.cardNumber),fn:length(fn:escapeXml(paymentInfo.cardNumber))-4)}"></c:set> -->
			                                                                            <ycommerce:testId code="paymentDetails_item_cardNumber_text" >${fn:substring(fn:escapeXml(paymentInfo.cardNumber), 12, 16)}</ycommerce:testId>
			                                                                        </div>
			                                                                    </div>
			                                                                    <div class="card-info">
			                                                                        <div class="card-data expires-message-warpper">
			                                                                            <span class="expires-label"> Expires</span>
			                                                                            
			                                                                            <span> <c:if test="${paymentInfo.expiryMonth lt 10}">0</c:if>${fn:escapeXml(paymentInfo.expiryMonth)}/${fn:escapeXml(paymentInfo.expiryYear)}</span>
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
			                                                                        <button class="btn btn-primary edit">
			                                                                            Edit
			                                                                        </button>
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
			                                                            <form id="newCreditCardPostForm" name="paymentInfoPostForm"
			                                                                class="new-payment-info" action="" method="POST">
			
			                                                                <div class="container-fluid">
			                                                                <div class="row">
			                                                                <div class="card-block col-md-5">
			                                                                    <div class="card-image card-image-block col-md-1">
			                                                                        <c:choose>
			                                                                    			<c:when test="${fn:escapeXml(paymentInfo.cardTypeData.code) eq 'visa'}">
			                                                                    				<img src="${themeResourcePath}/images/visa.png"/>
			                                                                    			</c:when>
			                                                                    			<c:when test="${fn:escapeXml(paymentInfo.cardTypeData.code) eq 'master'}">
			                                                                    				<img src="${themeResourcePath}/images/mastercard.png"/>
			                                                                    			</c:when>
			                                                                    			<c:otherwise>
			                                                                    				<img src="${themeResourcePath}/images/mastercard.png"/>
			                                                                    			</c:otherwise>
			                                                                    		</c:choose>
			                                                                    </div>
			                                                                    <div class="card-form col-md-11 card-form-wrapper">
			                                                                        <input type="hidden" name="paymentToken" id="paymentToken"/>
			                                                                        <input type="hidden" name="paymentProvider"
			                                                                            id="paymentProvider"/>
			                                                                        <input type="hidden" name="cardType" id="cardType"/>
			                                                                        <div>
			                                                                            <label class="control-label" for="card-number"> Card
			                                                                                number</label>
			                                                                            <div class="help-block card-number-block">
			                                                                                <input type="text" id="card-number"
			                                                                                    class="text form-control gw-proxied"
			                                                                                    value="************${fn:substring(fn:escapeXml(paymentInfo.cardNumber), 12, 16)}"
			                                                                                    style=""/>
			
			                                                                                <span class="has-error js-card-number-error hide"> Please enter a valid credit card number</span>
			                                                                            </div>
			                                                                        </div>
			                                                                        <div>
			                                                                            <label class="control-label" for="expiry-month">
			                                                                                Expires</label>
			                                                                            <div class="help-block expiry-block">
			                                                                                <input type="text" id="expiry-month"
			                                                                                    class="text form-control" value="<c:if test="${paymentInfo.expiryMonth lt 10}">0</c:if>${fn:escapeXml(paymentInfo.expiryMonth)}"/>
			                                                                                <span class="has-error js-expiry-month-error hide"> Please enter a valid expiry month</span>
			                                                                            </div>
			                                                                            <span class="separator">/</span>
			                                                                            <div class="help-block expiry-block">
			                                                                                <input type="text" id="expiry-year"
			                                                                                    class="text form-control" value="${fn:escapeXml(paymentInfo.expiryYear)}"/>
			                                                                                <span class="has-error js-expiry-year-error hide"> Please enter a valid expiry year</span>
			                                                                            </div>
			                                                                        </div>
			                                                                        <div>
			                                                                            <label class="control-label" for="security-code">
			                                                                                CVV</label>
			                                                                            <div class="help-block security-code-block">
			                                                                                <input type="password" id="security-code"
			                                                                                    class="text form-control gw-proxied"
			                                                                                    value="456"/>
			
			                                                                                <span class="has-error js-security-code-error hide"> Please enter a valid security code</span>
			                                                                            </div>
			                                                                        </div>
			                                                                        <div class="checkbox-container">
			                                                                            <div class="form-group">
			                                                                                <div class="checkbox">
			                                                                                    <label class="control-label width-auto"> <input
			                                                                                            id="paymentInfo" name="primaryCard"
			                                                                                            class="js-mandatory-payment-meyhod-changed  _checkbox-element-global-class"
			                                                                                            type="checkbox" value="true"/><input
			                                                                                            type="hidden" name="_primaryCard"
			                                                                                            value="on"/><span
			                                                                                            class="_checkbox-element-global-span"></span>
			                                                                                        <span class="checkbox-text _checkbox-element-global-text"> Use as Primary Card</span>
			                                                                                        <span class="mandatory"> </span> <span
			                                                                                                class="skip"></span> </label>
			                                                                                </div>
			                                                                            </div>
			                                                                        </div>
			                                                                    </div>
			                                                                </div>
			                                                                <div class="address-block col-md-7">
			
			                                                                <div class="row">
			                                                                    <div class="address-form col-md-10">
			                                                                        <div class="headline">
			                                                                            Billing Address
			                                                                        </div>
			                                                                        <div>
			                                                                            <div id="defaultMailingAddressSection">
			                                                                                <label class="control-label _checkbox-element-global-label">
			                                                                                    <input id="useDefaultMailingAddressCheckbox"
			                                                                                        type="checkbox"
			                                                                                        class="_checkbox-element-global-class"/>
			                                                                                    <span class="_checkbox-element-global-span"></span>
			                                                                                    <span class="checkbox-text _checkbox-element-global-text"> Use Existing Primary Mailing Address</span>
			                                                                                </label>
			                                                                                <br/>
			                                                                                <input type="hidden"
			                                                                                    id="defaultMailingAddressName" value=""/>
			                                                                                <input type="hidden"
			                                                                                    id="defaultMailingAddressLine1"
			                                                                                    value="489 Friendship Lane"/>
			                                                                                <input type="hidden"
			                                                                                    id="defaultMailingAddressLine2"
			                                                                                    value=""/>
			                                                                                <input type="hidden"
			                                                                                    id="defaultMailingAddressCity"
			                                                                                    value="Oakland"/>
			                                                                                <input type="hidden"
			                                                                                    id="defaultMailingAddressRegionIso"
			                                                                                    value="CA"/>
			                                                                                <input type="hidden"
			                                                                                    id="defaultMailingAddressPostalCode"
			                                                                                    value="94612"/>
			                                                                            </div>
			                                                                            <!-- TODO: will be edited with county selection implementation -->
			                                                                            <div class="form-group">
			                                                                                <label class="control-label "
			                                                                                    for="mailingAddressLine1"> Address Line
			                                                                                    1</label>
			                                                                                <input id="mailingAddressLine1" name="addressLine1" class="text form-control" value="${fn:escapeXml(paymentInfo.billingAddress.line1)}"/>
			                                                                            </div>
			                                                                            <div class="form-group">
			                                                                                <label class="control-label "
			                                                                                    for="mailingAddressLine2"> Address Line
			                                                                                    2</label>
			                                                                                <input id="mailingAddressLine2"
			                                                                                    name="addressLine2"
			                                                                                    class="text form-control" value="${fn:escapeXml(paymentInfo.billingAddress.line2)}"/>
			                                                                            </div>
			                                                                            <div class="form-group">
			                                                                                <label class="control-label"
			                                                                                    for="address.townCity">City/State</label>
			                                                                                <input id="mailingAddressCity"
			                                                                                    name="addressCity" class="form-control"
			                                                                                    type="text" value="CA"/>
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
			                                                                                <label class="control-label "
			                                                                                    for="mailingAddressPostalCode">
			                                                                                    zip</label>
			                                                                                <input id="mailingAddressPostalCode"
			                                                                                    name="addressPostalCode"
			                                                                                    class="text form-control" value="${fn:escapeXml(paymentInfo.billingAddress.postalCode)}"/>
			                                                                            </div>
			                                                                        </div>
			                                                                    </div>
			                                                                    <div class="address-buttons col-md-2">
			                                                                        <div>
			                                                                            <button type="button" id="editFormSubmit"
			                                                                                    class="btn btn-primary"> Save
			                                                                            </button>
			                                                                        </div>
			                                                                        <div>
			                                                                            <button id="editCancel" type="button"
			                                                                                    class="cancel-button js-hide-new-card-form">
			                                                                                Cancel
			                                                                            </button>
			                                                                        </div>
			                                                                    </div>
			                                                                </div>
			
			                                                                </div>
			                                                                </div>
			                                                                </div>
			
			                                                                <div>
			                                                                    <input type="hidden" name="CSRFToken"
			                                                                        value="f6eebe09-194d-436f-a044-29dc566efe76"/>
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
                                                        <form id="newCreditCardPostForm" name="paymentInfoPostForm"
                                                            class="new-payment-info" action="" method="POST">

                                                            <div class="container-fluid">
                                                            <div class="row">
                                                            <div class="card-block col-md-5">
                                                                <div class="card-image card-image-block col-md-1">
                                                                    <div class="no-image-block no-image-block-wrapper"></div>
                                                                </div>
                                                                <div class="card-form col-md-11 card-form-wrapper">
                                                                    <input type="hidden" name="paymentToken"
                                                                        id="paymentToken"/>
                                                                    <input type="hidden" name="paymentProvider"
                                                                        id="paymentProvider"/>
                                                                    <input type="hidden" name="cardType" id="cardType"/>
                                                                    <div>
                                                                        <label class="control-label" for="card-number"> Card
                                                                            number</label>
                                                                        <div class="help-block card-number-block">
                                                                            <input type="text" id="card-number"
                                                                                class="text form-control gw-proxied"
                                                                                value=""
                                                                                style=""/>

                                                                            <span class="has-error js-card-number-error hide"> Please enter a valid credit card number</span>
                                                                        </div>
                                                                    </div>
                                                                    <div>
                                                                        <label class="control-label" for="expiry-month">
                                                                            Expires</label>
                                                                        <div class="help-block expiry-block">
                                                                            <input type="text" id="expiry-month"
                                                                                class="text form-control" value=""/>
                                                                            <span class="has-error js-expiry-month-error hide"> Please enter a valid expiry month</span>
                                                                        </div>
                                                                        <span class="separator">/</span>
                                                                        <div class="help-block expiry-block">
                                                                            <input type="text" id="expiry-year"
                                                                                class="text form-control" value=""/>
                                                                            <span class="has-error js-expiry-year-error hide"> Please enter a valid expiry year</span>
                                                                        </div>
                                                                    </div>
                                                                    <div>
                                                                        <label class="control-label" for="security-code">
                                                                            CVV</label>
                                                                        <div class="help-block security-code-block">
                                                                            <input type="password" id="security-code"
                                                                                class="text form-control gw-proxied"
                                                                                value=""/>

                                                                            <span class="has-error js-security-code-error hide"> Please enter a valid security code</span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="checkbox-container">
                                                                        <div class="form-group">
                                                                            <div class="checkbox">
                                                                                <label class="control-label width-auto"> <input
                                                                                        id="paymentInfo" name="primaryCard"
                                                                                        class="js-mandatory-payment-meyhod-changed  _checkbox-element-global-class"
                                                                                        type="checkbox" value="true"/><input
                                                                                        type="hidden" name="_primaryCard"
                                                                                        value="on"/><span
                                                                                        class="_checkbox-element-global-span"></span>
                                                                                    <span class="checkbox-text _checkbox-element-global-text"> Use as Primary Card</span>
                                                                                    <span class="mandatory"> </span> <span
                                                                                            class="skip"></span> </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="address-block col-md-7">

                                                            <div class="row">
                                                                <div class="address-form col-md-10">
                                                                    <div class="headline">
                                                                        Billing Address
                                                                    </div>
                                                                    <div>
                                                                        <div id="defaultMailingAddressSection">
                                                                            <label class="control-label _checkbox-element-global-label">
                                                                                <input id="useDefaultMailingAddressCheckbox"
                                                                                    type="checkbox"
                                                                                    class="_checkbox-element-global-class"/>
                                                                                <span class="_checkbox-element-global-span"></span>
                                                                                <span class="checkbox-text _checkbox-element-global-text"> Use Existing Primary Mailing Address</span>
                                                                            </label>
                                                                            <br/>
                                                                            <input type="hidden"
                                                                                id="defaultMailingAddressName" value=""/>
                                                                            <input type="hidden"
                                                                                id="defaultMailingAddressLine1"
                                                                                value="489 Friendship Lane"/>
                                                                            <input type="hidden"
                                                                                id="defaultMailingAddressLine2"
                                                                                value=""/>
                                                                            <input type="hidden"
                                                                                id="defaultMailingAddressCity"
                                                                                value="Oakland"/>
                                                                            <input type="hidden"
                                                                                id="defaultMailingAddressRegionIso"
                                                                                value="CA"/>
                                                                            <input type="hidden"
                                                                                id="defaultMailingAddressPostalCode"
                                                                                value="94612"/>
                                                                        </div>
                                                                        <!-- TODO: will be edited with county selection implementation -->
                                                                        <div class="form-group">
                                                                            <label class="control-label "
                                                                                for="mailingAddressLine1"> Address Line
                                                                                1</label>
                                                                            <input id="mailingAddressLine1"
                                                                                name="addressLine1"
                                                                                class="text form-control" value=""/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="control-label "
                                                                                for="mailingAddressLine2"> Address Line
                                                                                2</label>
                                                                            <input id="mailingAddressLine2"
                                                                                name="addressLine2"
                                                                                class="text form-control" value=""/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="control-label"
                                                                                for="address.townCity">City/State</label>
                                                                            <input id="mailingAddressCity"
                                                                                name="addressCity" class="form-control"
                                                                                type="text" value=""/>
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
                                                                            <label class="control-label "
                                                                                for="mailingAddressPostalCode">
                                                                                zip</label>
                                                                            <input id="mailingAddressPostalCode"
                                                                                name="addressPostalCode"
                                                                                class="text form-control" value=""/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="address-buttons col-md-2">
                                                                    <div>
                                                                        <button type="button" id="newCreditCardFormSubmit"
                                                                                class="btn btn-primary"> Save
                                                                        </button>
                                                                    </div>
                                                                    <div>
                                                                        <button type="button" id="newCancel"
                                                                                class="cancel-button js-hide-new-card-form">
                                                                            Cancel
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            </div>
                                                            </div>
                                                            </div>

                                                            <div>
                                                                <input type="hidden" name="CSRFToken"
                                                                    value="f6eebe09-194d-436f-a044-29dc566efe76"/>
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
                                        <div role="button" class="collapsed" data-toggle="collapse"
                                            data-parent="#billingShippingAccordion" href="#billingShippingBody"
                                            aria-controls="billingShippingBody">
                                            <span class="icon-billing-shipping icon-home2"></span>
                                            <span class="text-uppercase panel-title-text">Shipping addresses</span>
                                            <span class="indicator pull-right"></span>
                                        </div>
                                    </h4>
                                </div>
                                <c:if test="${empty addressData}">
									<div class="account-section-content content-empty">
										<spring:theme code="text.account.addressBook.noSavedAddresses" />
									</div>
							    </c:if>
							    
							    <c:if test="${not empty addressData}">
	                                <div id="billingShippingBody" class="panel-collapse collapse billing-and-shipping-section"
	                                    role="tabpanel" aria-labelledby="headingOne">
	                                    <div class="panel-body">
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
				                                                            	   <span class="primary-text mailing-address__title">Shipping address</span>
					                                                                <span class="secondary-text mailing-address__line-one">${fn:escapeXml(address.line1)}&nbsp;<c:if test="${not empty fn:escapeXml(address.line2)}">${fn:escapeXml(address.line2)}</c:if></span>
					                                                                <span class="secondary-text mailing-address__city">${fn:escapeXml(address.town)}</span>
					                                                                <div>
					                                                                    ${fn:escapeXml(address.region.name)}
					                                                                </div>
					                                                                <span class="secondary-text mailing-address__zip">${fn:escapeXml(address.postalCode)}</span>
					                                                                <span class="secondary-text mailing-address__phone">${fn:escapeXml(address.phone)}</span>
		                                                                			   <span class="secondary-text mailing-address__email">${fn:escapeXml(address.email)}</span>
	                                                                			   
				                                                                <button type="button"
				                                                                        class="btn-edit btn btn-primary"
				                                                                        data-address-id="${fn:escapeXml(address.id)}"
				                                                                        data-address-countryISOCode="${fn:escapeXml(address.country.isocode)}"> Edit
				                                                                </button>
				                                                            </div>
				                                                            <c:set var="addressID" scope="request" value="${fn:escapeXml(address.id)}"/>
				                                                            <c:set var="countryISO" scope="request" value="${fn:escapeXml(address.country.isocode)}"/>
				                                                            <div class="mailing-address-edit">
				                                                                <h4>SHIPPING ADDRESS</h4>
				                                                                <div class="addressEdit">
				                                                                		
				                                                                </div>
				                                                                
				                                                            </div>
				                                                     </div>
				                                                    <div id="address-form-8796093218839_unique"
				                                                        class="form-container display-none">
				                                                        <div id="validationErrorMsgCreateAddressForm" class="display-none">
				                                                            The Address can't be validated. Please try again.
				                                                        </div>
				                                                        <form id="addressForm-8796093218839_shipping" class="new-address"
				                                                            action="" method="post">
				                                                            <div id="externalValidationErrorMessage"></div>
				                                                            <span class="primary-text">Shipping address</span>
				                                                            <input id="addressName" name="addressName" type="hidden"
				                                                                value=""/>
				                                                            <input id="addressId" name="addressId" value="8796093218839"
				                                                                type="hidden"/>
				                                                            <input id="fullName" name="fullName" value="Tester QaLOS"
				                                                                type="hidden"/>
				                                                            <input id="phoneCountryCode" name="phoneCountryCode"
				                                                                type="hidden" value=""/>
				                                                            <input id="phoneAreaCode" name="phoneAreaCode" type="hidden"
				                                                                value=""/>
				                                                            <input id="phoneNumber" name="phoneNumber" type="hidden"
				                                                                value=""/>
				                                                            <input id="email" name="email" value="TeaserQaLOS@gmail.com"
				                                                                type="hidden"/>
				                                                            <input id="skipAdditionalFields" name="skipAdditionalFields"
				                                                                value="true" type="hidden"/>
				                                                            <input id="taxJurisdictionCode-8796093218839_shipping"
				                                                                name="taxJurisdictionCode" type="hidden" value=""/>
				                                                            <div class="form-group">
				                                                                <label class="control-label " for="address.line1"> Address
				                                                                    Line 1</label>
				                                                                <input id="address.line1" name="line1"
				                                                                    class="text form-control" minlength="1"
				                                                                    value="489 Friendship Lane" maxlength="70"/>
				                                                            </div>
				                                                            <div class="form-group">
				                                                                <label class="control-label " for="address.line2"> Address
				                                                                    Line 2</label>
				                                                                <input id="address.line2" name="line2"
				                                                                    class="text form-control" value="" maxlength="70"/>
				                                                            </div>
				                                                            <div class="amway-theme">
				                                                                <div class="form-group">
				                                                                    <label class="control-label" for="address.townCity">City/State</label>
				                                                                    <input id="address.townCity" name="townCity"
				                                                                        class="form-control" min="1" max="70"
				                                                                        mandatory="true" type="text" value="Oakland"/>
				                                                                    <select id="address.region" name="regionIso"
				                                                                            tabindex="-1" class="select2-hidden-accessible"
				                                                                            aria-hidden="true">
				                                                                        <option title="name" value="US-AL">AL</option>
				                                                                        <option title="name" value="US-AK">AK</option>
				                                                                        <option title="name" value="US-AS">AS</option>
				                                                                        <option title="name" value="US-AZ">AZ</option>
				                                                                        <option title="name" value="US-AR">AR</option>
				                                                                        <option title="name" selected="true" value="US-CA">
                                                                            CA
                                                                        </option>
				                                                                        <option title="name" value="US-CO">CO</option>
				                                                                        <option title="name" value="US-CT">CT</option>
				                                                                        <option title="name" value="US-DE">DE</option>
				                                                                        <option title="name" value="US-DC">DC</option>
				                                                                        <option title="name" value="US-FL">FL</option>
				                                                                        <option title="name" value="US-GA">GA</option>
				                                                                        <option title="name" value="US-GU">GU</option>
				                                                                        <option title="name" value="US-HI">HI</option>
				                                                                        <option title="name" value="US-ID">ID</option>
				                                                                        <option title="name" value="US-IL">IL</option>
				                                                                        <option title="name" value="US-IN">IN</option>
				                                                                        <option title="name" value="US-IA">IA</option>
				                                                                        <option title="name" value="US-KS">KS</option>
				                                                                        <option title="name" value="US-KY">KY</option>
				                                                                        <option title="name" value="US-LA">LA</option>
				                                                                        <option title="name" value="US-ME">ME</option>
				                                                                        <option title="name" value="US-MD">MD</option>
				                                                                        <option title="name" value="US-MA">MA</option>
				                                                                        <option title="name" value="US-MI">MI</option>
				                                                                        <option title="name" value="US-MN">MN</option>
				                                                                        <option title="name" value="US-MS">MS</option>
				                                                                        <option title="name" value="US-MO">MO</option>
				                                                                        <option title="name" value="US-MT">MT</option>
				                                                                        <option title="name" value="US-NE">NE</option>
				                                                                        <option title="name" value="US-NV">NV</option>
				                                                                        <option title="name" value="US-NH">NH</option>
				                                                                        <option title="name" value="US-NJ">NJ</option>
				                                                                        <option title="name" value="US-NM">NM</option>
				                                                                        <option title="name" value="US-NY">NY</option>
				                                                                        <option title="name" value="US-NC">NC</option>
				                                                                        <option title="name" value="US-ND">ND</option>
				                                                                        <option title="name" value="US-MP">MP</option>
				                                                                        <option title="name" value="US-OH">OH</option>
				                                                                        <option title="name" value="US-OK">OK</option>
				                                                                        <option title="name" value="US-OR">OR</option>
				                                                                        <option title="name" value="US-PA">PA</option>
				                                                                        <option title="name" value="US-PR">PR</option>
				                                                                        <option title="name" value="US-RI">RI</option>
				                                                                        <option title="name" value="US-SC">SC</option>
				                                                                        <option title="name" value="US-SD">SD</option>
				                                                                        <option title="name" value="US-TN">TN</option>
				                                                                        <option title="name" value="US-TX">TX</option>
				                                                                        <option title="name" value="US-UM">UM</option>
				                                                                        <option title="name" value="US-VI">VI</option>
				                                                                        <option title="name" value="US-UT">UT</option>
				                                                                        <option title="name" value="US-VT">VT</option>
				                                                                        <option title="name" value="US-VA">VA</option>
				                                                                        <option title="name" value="US-WA">WA</option>
				                                                                        <option title="name" value="US-WV">WV</option>
				                                                                        <option title="name" value="US-WI">WI</option>
				                                                                        <option title="name" value="US-WY">WY</option>
				                                                                    </select>
				                                                                    <span class="select2 select2-container select2-container--default"
				                                                                        dir="ltr" style="width: 14px;"><span
				                                                                            class="selection"><span
				                                                                            class="select2-selection select2-selection--single"
				                                                                            role="combobox" aria-haspopup="true"
				                                                                            aria-expanded="false" tabindex="0"
				                                                                            aria-labelledby="select2-addressregion-container"><span
				                                                                            class="select2-selection__rendered"
				                                                                            id="select2-addressregion-container"
				                                                                            title="name">CA</span><span
				                                                                            class="select2-selection__arrow"
				                                                                            role="presentation"><b role="presentation"></b></span></span></span><span
				                                                                            class="dropdown-wrapper"
				                                                                            aria-hidden="true"></span></span>
				                                                                </div>
				                                                            </div>
				                                                            <div class="form-group">
				                                                                <label class="control-label " for="address.postcode">
				                                                                    zip</label>
				                                                                <input id="address.postcode" name="postcode"
				                                                                    class="text form-control" minlength="1" value="94612"
				                                                                    maxlength="20"/>
				                                                            </div>
				                                                            <div class="form-group">
				                                                                <div class="checkbox">
				                                                                    <label class="control-label "> <input
				                                                                            id="address.addressStandardizationBypassFlag"
				                                                                            name="addressStandardizationBypassFlag"
				                                                                            class="  _checkbox-element-global-class"
				                                                                            type="checkbox" value="true"/><input
				                                                                            type="hidden"
				                                                                            name="_addressStandardizationBypassFlag"
				                                                                            value="on"/><span
				                                                                            class="_checkbox-element-global-span"></span>
				                                                                        <span class="checkbox-text _checkbox-element-global-text"> Override address</span>
				                                                                        <span class="skip"></span> </label>
				                                                                </div>
				                                                            </div>
				                                                            <div>
				                                                                <a href=""
				                                                                class="js-manage-address-btn js-change-ditto btn btn-primary">
				                                                                    Save</a>
				                                                                <div class="display-none">
				                                                                    <div id="popup_confirm_address_change"
				                                                                        class="account-address-change-popup">
				                                                                        <div class="addressItem">
				                                                                            Do you want change this address for all related
				                                                                            Ditto schedules?
				                                                                            <div class="modal-actions">
				                                                                                <div class="row">
				                                                                                    <div class="col-xs-12 col-sm-6 col-sm-push-6">
				                                                                                        <button class="btn btn-primary btn-block js-change_address_button_accept show_processing_message"
				                                                                                                type="submit"> Accept
				                                                                                        </button>
				                                                                                    </div>
				                                                                                    <div class="col-xs-12 col-sm-6 col-sm-pull-6">
				                                                                                        <button class="btn btn-default btn-block js-change_address_button_reject show_processing_message"
				                                                                                                type="submit"> Reject
				                                                                                        </button>
				                                                                                    </div>
				                                                                                </div>
				                                                                            </div>
				                                                                        </div>
				                                                                    </div>
				                                                                </div>
				                                                            </div>
				                                                            <input type="hidden" name="isDittoRelatedAddress"
				                                                                id="isDittoRelatedAddress" value="true"/>
				                                                            <div class="checkbox-container edit-address-checkbox-container">
				               													 <span class="display-none"> 
				               													 <input id="shippingAddress_8796093218839" name="defaultShippingAddress" value="true" type="hidden"/>
																		                <div class="form-group">
																		                    <div class="checkbox">
																		                    <label class="control-label add-address-left-label"> <input id="mailingAddress_8796093218839"
																		                                                                                name="defaultMailingAddress"
																		                                                                                class="add-address-left-input js-mailing-address-changed  _checkbox-element-global-class"
																		                                                                                type="checkbox" value="true"
																		                                                                                checked="checked"/><input type="hidden"
																		                                                                                                        name="_defaultMailingAddress"
																		                                                                                                        value="on"/><span
																		                            class="_checkbox-element-global-span"></span> <span
																		                            class="checkbox-text _checkbox-element-global-text"> Make Primary Mailing Address</span> <span
																		                            class="mandatory"> </span> <span class="skip"></span> </label>
																		                    </div>
																		                </div> 
																		          </span>
				                                                            </div>
				                                                            <div>
				                                                                <input type="hidden" name="CSRFToken"
				                                                                    value="f6eebe09-194d-436f-a044-29dc566efe76"/>
				                                                            </div>
				                                                        </form>
				                                                        <div class="hidden">
				                                                            <div id="selectTaxJurisdictionBlockId-8796093218839_shipping"
				                                                                data-popup-title="Choose the selection">
				                                                                <div class="amway-theme js-tax-jurisdiction-block-content tax-juristiction-select-modal-container">
				                                                                    <div class="tax-juristiction-container">
				                                                                        <div class="tax-juristiction-message">
				                                                                            <p> The address you have entered has returned a
				                                                                                tax rate for both inside and outside the
				                                                                                city limits.Please choose the appropriate
				                                                                                selection below. Once selected, the
				                                                                                combination below charges the correct tax
				                                                                                rate for the address you provided. If you
				                                                                                are unsure which selection is correct for
				                                                                                you (inside or outside the city limits),
				                                                                                please contact your local tax authority. If
				                                                                                you think you got this message in error,
				                                                                                click the Back button to fix the address and
				                                                                                try again.</p>
				                                                                        </div>
				                                                                        <div class="table-header-container">
				                                                                            <label>Tax jurisdiction for:</label>
				                                                                            <span id="streetLine1-8796093218839_shipping"> </span>
				                                                                        </div>
				                                                                        <table class="tax-juristiction-table">
				                                                                            <thead>
				                                                                            <tr>
				                                                                                <th class="col-md-2">Select one</th>
				                                                                                <th class="col-md-2">State</th>
				                                                                                <th class="col-md-2">County</th>
				                                                                                <th class="col-md-2">City limit</th>
				                                                                                <th class="col-md-2">City</th>
				                                                                                <th class="col-md-2">Zip code</th>
				                                                                            </tr>
				                                                                            </thead>
				                                                                            <tbody class="js-tax-jurisdictions-list">
				                                                                            </tbody>
				                                                                        </table>
				                                                                        <input type="button"
				                                                                            class="btn primary button-submit js-save-address-with-tax-jurisdiction"
				                                                                            value="Save"/>
				                                                                    </div>
				                                                                </div>
				                                                            </div>
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
	                                                        				  	  <span class="primary-text mailing-address__title">Shipping address</span>
				                                                                <span class="secondary-text mailing-address__line-one">${fn:escapeXml(address.line1)}&nbsp;<c:if test="${not empty fn:escapeXml(address.line2)}">${fn:escapeXml(address.line2)}</c:if></span>
				                                                                <span class="secondary-text mailing-address__city">${fn:escapeXml(address.town)}</span>
				                                                                <div>
				                                                                    ${fn:escapeXml(address.region.name)}
				                                                                </div>
				                                                                <span class="secondary-text mailing-address__zip">${fn:escapeXml(address.postalCode)}</span>
				                                                                <span class="secondary-text mailing-address__phone">${fn:escapeXml(address.phone)}</span>
	                                                                			   <span class="secondary-text mailing-address__email">${fn:escapeXml(address.email)}</span>
				                                                              	 <button type="button"
					                                                                        class="btn-edit btn btn-primary"
					                                                                        data-address-id="${fn:escapeXml(address.id)}"
					                                                                        data-address-countryISOCode="${fn:escapeXml(address.country.isocode)}"> Edit
					                                                                </button>
				                                                            </div>
				                                                            
				                                                            <c:set var="addressID" scope="request" value="${fn:escapeXml(address.id)}"/>
				                                                            <c:set var="countryISO" scope="request" value="${fn:escapeXml(address.country.isocode)}"/>
				                                                            <div class="mailing-address-edit">
				                                                                <h4>SHIPPING ADDRESS</h4>
				                                                                <div class="addressEdit">
				                                                                		
				                                                                </div>
				                                                            </div>
	                                                        			</div>
	                                                        	</div>
	                                            			</c:if>
	                                            		</c:forEach>
	                                            		
	                                            		
	                                                
	                                                
	                                                
	                                                <div class="form-container col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                                                    <div class="js-new-address-wrapper display-none amway-theme">
	                                                        <form id="addressForm-new" class="new-address" action=""
	                                                            method="post">
	                                                            <div id="externalValidationErrorMessage"></div>
	                                                            <div id="validationErrorMsgCreateAddressForm"
	                                                                class="display-none">
	                                                                The Address can't be validated. Please try again.
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <label class="control-label " for="address.addressName">
	                                                                    Address name</label>
	                                                                <input id="address.addressName" name="addressName"
	                                                                    class="text form-control" value="" maxlength="256"/>
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <label class="control-label " for="address.fullName"> Full
	                                                                    Name</label>
	                                                                <input id="address.fullName" name="fullName"
	                                                                    class="text form-control" value="" maxlength="256"/>
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <label class="control-label " for="address.line1"> Address
	                                                                    Line 1</label>
	                                                                <input id="address.line1" name="line1"
	                                                                    class="text form-control" minlength="1" value=""
	                                                                    maxlength="70"/>
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <label class="control-label " for="address.line2"> Address
	                                                                    Line 2</label>
	                                                                <input id="address.line2" name="line2"
	                                                                    class="text form-control" value="" maxlength="70"/>
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <label class="control-label" for="address.townCity">City/State</label>
	                                                                <input id="address.townCity" name="townCity"
	                                                                    class="form-control" minlength="1" type="text"
	                                                                    value="" maxlength="70"/>
	                                                                <select id="address.region" name="regionIso" tabindex="-1"
	                                                                        class="select2-hidden-accessible"
	                                                                        aria-hidden="true">
	                                                                    <option value="US-AL">AL</option>
	                                                                    <option value="US-AK">AK</option>
	                                                                    <option value="US-AS">AS</option>
	                                                                    <option value="US-AZ">AZ</option>
	                                                                    <option value="US-AR">AR</option>
	                                                                    <option value="US-CA">CA</option>
	                                                                    <option value="US-CO">CO</option>
	                                                                    <option value="US-CT">CT</option>
	                                                                    <option value="US-DE">DE</option>
	                                                                    <option value="US-DC">DC</option>
	                                                                    <option value="US-FL">FL</option>
	                                                                    <option value="US-GA">GA</option>
	                                                                    <option value="US-GU">GU</option>
	                                                                    <option value="US-HI">HI</option>
	                                                                    <option value="US-ID">ID</option>
	                                                                    <option value="US-IL">IL</option>
	                                                                    <option value="US-IN">IN</option>
	                                                                    <option value="US-IA">IA</option>
	                                                                    <option value="US-KS">KS</option>
	                                                                    <option value="US-KY">KY</option>
	                                                                    <option value="US-LA">LA</option>
	                                                                    <option value="US-ME">ME</option>
	                                                                    <option value="US-MD">MD</option>
	                                                                    <option value="US-MA">MA</option>
	                                                                    <option value="US-MI">MI</option>
	                                                                    <option value="US-MN">MN</option>
	                                                                    <option value="US-MS">MS</option>
	                                                                    <option value="US-MO">MO</option>
	                                                                    <option value="US-MT">MT</option>
	                                                                    <option value="US-NE">NE</option>
	                                                                    <option value="US-NV">NV</option>
	                                                                    <option value="US-NH">NH</option>
	                                                                    <option value="US-NJ">NJ</option>
	                                                                    <option value="US-NM">NM</option>
	                                                                    <option value="US-NY">NY</option>
	                                                                    <option value="US-NC">NC</option>
	                                                                    <option value="US-ND">ND</option>
	                                                                    <option value="US-MP">MP</option>
	                                                                    <option value="US-OH">OH</option>
	                                                                    <option value="US-OK">OK</option>
	                                                                    <option value="US-OR">OR</option>
	                                                                    <option value="US-PA">PA</option>
	                                                                    <option value="US-PR">PR</option>
	                                                                    <option value="US-RI">RI</option>
	                                                                    <option value="US-SC">SC</option>
	                                                                    <option value="US-SD">SD</option>
	                                                                    <option value="US-TN">TN</option>
	                                                                    <option value="US-TX">TX</option>
	                                                                    <option value="US-UM">UM</option>
	                                                                    <option value="US-VI">VI</option>
	                                                                    <option value="US-UT">UT</option>
	                                                                    <option value="US-VT">VT</option>
	                                                                    <option value="US-VA">VA</option>
	                                                                    <option value="US-WA">WA</option>
	                                                                    <option value="US-WV">WV</option>
	                                                                    <option value="US-WI">WI</option>
	                                                                    <option value="US-WY">WY</option>
	                                                                </select>
	                                                                <span class="select2 select2-container select2-container--default"
	                                                                    dir="ltr" style="width: 14px;"><span
	                                                                        class="selection"><span
	                                                                        class="select2-selection select2-selection--single"
	                                                                        role="combobox" aria-haspopup="true"
	                                                                        aria-expanded="false" tabindex="0"
	                                                                        aria-labelledby="select2-addressregion-container"><span
	                                                                        class="select2-selection__rendered"
	                                                                        id="select2-addressregion-container"
	                                                                        title="AL">AL</span><span
	                                                                        class="select2-selection__arrow"
	                                                                        role="presentation"><b
	                                                                        role="presentation"></b></span></span></span><span
	                                                                        class="dropdown-wrapper" aria-hidden="true"></span></span>
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <label class="control-label " for="address.postcode">
	                                                                    zip</label>
	                                                                <input id="address.postcode" name="postcode"
	                                                                    class="text form-control" minlength="1" value=""
	                                                                    maxlength="20"/>
	                                                            </div>
	                                                            <div class="form-group row phone-form-field-container">
	                                                                <label class="control-label col-md-4"> Phone</label>
	                                                                <div class="amway-theme phone-number-value-container col-md-8 col-sm-12">
	                                                                    <div class="value-part-1 col-md-3 col-sm-4">
	                                                                        <div class="control-group">
	                                                                            <select id="address.phoneCountryCode"
	                                                                                    name="phoneCountryCode"
	                                                                                    class="form-control select2-hidden-accessible"
	                                                                                    tabindex="-1" aria-hidden="true">
	                                                                                <option value="1">CA (1)</option>
	                                                                                <option value="509">HT (509)</option>
	                                                                                <option value="1">US (1)</option>
	                                                                                <option value="1809">DO (1809)</option>
	                                                                            </select>
	                                                                            <span class="select2 select2-container select2-container--default"
	                                                                                dir="ltr" style="width: 14px;"><span
	                                                                                    class="selection"><span
	                                                                                    class="select2-selection select2-selection--single"
	                                                                                    role="combobox" aria-haspopup="true"
	                                                                                    aria-expanded="false" tabindex="0"
	                                                                                    aria-labelledby="select2-addressphoneCountryCode-container"><span
	                                                                                    class="select2-selection__rendered"
	                                                                                    id="select2-addressphoneCountryCode-container"
	                                                                                    title="CA (1)">CA (1)</span><span
	                                                                                    class="select2-selection__arrow"
	                                                                                    role="presentation"><b
	                                                                                    role="presentation"></b></span></span></span><span
	                                                                                    class="dropdown-wrapper"
	                                                                                    aria-hidden="true"></span></span>
	                                                                        </div>
	                                                                    </div>
	                                                                    <div class="value-part-2 col-md-3 col-sm-4">
	                                                                        <div class="form-group">
	                                                                            <label class="control-label "
	                                                                                for="address.phoneAreaCode"> </label>
	                                                                            <input id="address.phoneAreaCode"
	                                                                                name="phoneAreaCode"
	                                                                                class="text form-control" minlength="1"
	                                                                                type="number" value="" maxlength="3"/>
	                                                                        </div>
	                                                                    </div>
	                                                                    <div class="value-part-3 col-md-6 col-sm-4">
	                                                                        <div class="form-group">
	                                                                            <label class="control-label "
	                                                                                for="address.phoneNumber"> </label>
	                                                                            <input id="address.phoneNumber"
	                                                                                name="phoneNumber"
	                                                                                class="text form-control" minlength="1"
	                                                                                type="number" value="" maxlength="8"/>
	                                                                        </div>
	                                                                    </div>
	                                                                </div>
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <label class="control-label " for="address.email">
	                                                                    Email</label>
	                                                                <input id="address.email" name="email"
	                                                                    class="text form-control" minlength="1" value=""
	                                                                    maxlength="254"/>
	                                                            </div>
	                                                            <div class="form-group">
	                                                                <div class="checkbox">
	                                                                    <label class="control-label "> <input
	                                                                            id="address.addressStandardizationBypassFlag"
	                                                                            name="addressStandardizationBypassFlag"
	                                                                            class="  _checkbox-element-global-class"
	                                                                            type="checkbox" value="true"/><input
	                                                                            type="hidden"
	                                                                            name="_addressStandardizationBypassFlag"
	                                                                            value="on"/><span
	                                                                            class="_checkbox-element-global-span"></span>
	                                                                        <span class="checkbox-text _checkbox-element-global-text"> Override address</span>
	                                                                        <span class="skip"></span> </label>
	                                                                </div>
	                                                            </div>
	                                                            <input id="taxJurisdictionCode-new" name="taxJurisdictionCode"
	                                                                type="hidden" value=""/>
	                                                            <div>
	                                                                <div class="checkbox-container">
	                                                                    <div class="form-group">
	                                                                        <div class="checkbox">
	                                                                            <label class="control-label add-address-left-label">
	                                                                                <input id="shippingAddress"
	                                                                                    name="defaultShippingAddress"
	                                                                                    class="add-address-left-input  _checkbox-element-global-class"
	                                                                                    type="checkbox" value="true"/><input
	                                                                                    type="hidden"
	                                                                                    name="_defaultShippingAddress"
	                                                                                    value="on"/><span
	                                                                                    class="_checkbox-element-global-span"></span>
	                                                                                <span class="checkbox-text _checkbox-element-global-text"> Make Primary Shipping Address</span>
	                                                                                <span class="mandatory"> </span> <span
	                                                                                    class="skip"></span> </label>
	                                                                        </div>
	                                                                    </div>
	                                                                </div>
	                                                                <input type="submit" class="js-manage-address-btn"
	                                                                    value="Save"/>
	                                                                <div class="js-cancel-new-address-btn new-address-cancel-btn display-none">
	                                                                    Cancel
	                                                                </div>
	                                                            </div>
	                                                            <div>
	                                                                <input type="hidden" name="CSRFToken"
	                                                                    value="f6eebe09-194d-436f-a044-29dc566efe76"/>
	                                                            </div>
	                                                        </form>
	                                                        <div class="hidden">
	                                                            <div id="selectTaxJurisdictionBlockId-new"
	                                                                data-popup-title="Choose the selection">
	                                                                <div class="amway-theme js-tax-jurisdiction-block-content tax-juristiction-select-modal-container">
	                                                                    <div class="tax-juristiction-container">
	                                                                        <div class="tax-juristiction-message">
	                                                                            <p> The address you have entered has returned a
	                                                                                tax rate for both inside and outside the
	                                                                                city limits.Please choose the appropriate
	                                                                                selection below. Once selected, the
	                                                                                combination below charges the correct tax
	                                                                                rate for the address you provided. If you
	                                                                                are unsure which selection is correct for
	                                                                                you (inside or outside the city limits),
	                                                                                please contact your local tax authority. If
	                                                                                you think you got this message in error,
	                                                                                click the Back button to fix the address and
	                                                                                try again.</p>
	                                                                        </div>
	                                                                        <div class="table-header-container">
	                                                                            <label>Tax jurisdiction for:</label>
	                                                                            <span id="streetLine1-new"> </span>
	                                                                        </div>
	                                                                        <table class="tax-juristiction-table">
	                                                                            <thead>
	                                                                            <tr>
	                                                                                <th class="col-md-2">Select one</th>
	                                                                                <th class="col-md-2">State</th>
	                                                                                <th class="col-md-2">County</th>
	                                                                                <th class="col-md-2">City limit</th>
	                                                                                <th class="col-md-2">City</th>
	                                                                                <th class="col-md-2">Zip code</th>
	                                                                            </tr>
	                                                                            </thead>
	                                                                            <tbody class="js-tax-jurisdictions-list">
	                                                                            </tbody>
	                                                                        </table>
	                                                                        <input type="button"
	                                                                            class="btn primary button-submit js-save-address-with-tax-jurisdiction"
	                                                                            value="Save"/>
	                                                                    </div>
	                                                                </div>
	                                                            </div>
	                                                        </div>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                            <div class="add-new-alternate-address js-add-new-address-btn">
	                                                <span class="icon icon-plus"></span>
	                                                <a title="Add New Alternative Address" href="">Add New Alternative Address</a>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
                                </c:if>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>