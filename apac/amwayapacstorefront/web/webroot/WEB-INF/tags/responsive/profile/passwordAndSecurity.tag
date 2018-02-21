<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="panel panel-default account-profile__panel">
    <div class="panel-heading" role="tab" id="headingPassword">
        <h4 class="panel-title">
            <div class="collapsed" role="button" data-toggle="collapse" data-parent="#profileEditAccordion" href="#collapsePassword" aria-expanded="false" aria-controls="collapsePassword">
                <span class="icon-personal-info icon-shield"></span>
                <span class="text-uppercase"><spring:theme code="profile.password.security.label"/></span>
                <span class="indicator pull-right"></span>
            </div>
        </h4>
    </div>
    <div id="collapsePassword" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingPassword" style="height: 0px;">
        <div class="panel-body">
            <div class="row password-security-container">
                <div class="col-md-12">
                    <div class="amway-theme">
                        <div class="row email-form-group">
                            <label class="col-md-12 account-profile__label"><spring:theme code="profile.password.security.email"/></label>
                            <div class="col-md-12 account-profile__value">jennifer.jones@email.com</div>
                        </div>
                        <div class="row email-form-group">
                            <label class="col-md-12 account-profile__label"><spring:theme code="profile.password.security.phone"/></label>
                            <div class="col-md-12 account-profile__value">(555) 456-7890</div>
                        </div>
                        <div class="row email-form-group">
                            <label class="col-md-12 account-profile__label"><spring:theme code="profile.password.security.password"/></label>
                            <div class="col-md-12 account-profile__value">******** <a id="editPw" href=""><spring:theme code="profile.password.security.edit"/></a></div>
                            <div class="col-md-4 account-profile__editor">
                                <div class="form-group">
                                    <input id="profile.customerContactEmail" name="customerContactEmail" class="text form-control" value="password">
                                    <button id="editPw-sv" class="btn btn-primary js-email-subscriptions-save-btn" type="button" name="button"><spring:theme code="profile.password.security.save"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
                
            </div>
        </div>
    </div>
</div>