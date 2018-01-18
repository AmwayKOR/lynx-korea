<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="panel panel-default account-profile__panel">
    <div class="panel-heading active" role="tab" id="headingPI">
        <h4 class="panel-title">
            <div role="button" data-toggle="collapse" data-parent="#profileEditAccordion" href="#collapsePI" aria-expanded="true" aria-controls="collapsePI" class="">
                <span class="icon-personal-info icon-user-new"></span>
                <span class="text-uppercase"><spring:theme code="profile.personal.information.label"/></span>
                <span class="indicator pull-right"></span>
            </div>
        </h4>
    </div>
	<div id="personal-information-container"></div>
</div>

<script id="personal-information-template" type="text/x-jquery-tmpl">
    <div id="collapsePI" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingPI" style="">
        <div class="panel-body">
            <div class="row amway-theme">
                <div class="col-md-12">
                    <form id="updateProfileForm" action="#" method="post" enctype="multipart/form-data">
                        <div class="row name-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.name"/></label>
                            <div class="col-md-9 account-profile__value">{{= name}}</div>
                            <div class="col-md-4 account-profile__editor">
                                <div class="form-group">
                                    <input id="preferredName.name " type="text" name="name" class="text form-control" value="Name">
                                </div>
                            </div>
                        </div>
                        <div class="row preferred-name-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.name.preferred"/></label>
                            <div class="col-md-9 account-profile__value">{{= preferredName}}</div>
                            <div class="col-md-4 account-profile__editor">
                                <div class="form-group">
                                    <input id="profile.preferredName" type="text" name="preferredName" class="text form-control" value="My preferred name"></div>
                            </div>
                        </div>
                        <div class="row mobile-phone-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.phone"/></label>
                            <div class="col-md-9 account-profile__value">{{= phone}}</div>
                            <div class="col-md-2 account-profile__editor input-part-1">
                                <div class="control-group">
                                    <select id="profile.mobileCountryCode" name="mobileCountryCode" class="form-control">
                                        <option value="1" selected="selected">CA (1)</option>
                                        <option value="509">HT (509)</option>
                                        <option value="1" selected="selected">US (1)</option>
                                        <option value="1809">DO (1809)</option></select>
                                </div>
                            </div>
                            <div class="col-md-1 account-profile__editor input-part-2">
                                <div class="form-group">
                                    <input id="profile.mobileAreaCode" name="mobileAreaCode" type="text" class="text form-control" minlength="1" value="202" maxlength="3"></div>
                            </div>
                            <div class="col-md-1 account-profile__editor input-part-3">
                                <div class="form-group">
                                    <input id="profile.mobilePhoneNumber" name="mobilePhoneNumber" type="text" class="text form-control" minlength="1" value="25550153" maxlength="8"></div>
                            </div>
                        </div>
                        <div class="row alternate-phone-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.phone.alternate"/></label>
                            <div class="col-md-9 account-profile__value">{{= alternatePhone}}</div>
                            <div class="col-md-3 account-profile__editor input-part-1">
                                <div class="control-group">
                                    <select id="profile.alternateCountryCode" name="alternateCountryCode" class="form-control">
                                        <option value="1" selected="selected">CA (1)</option>
                                        <option value="509">HT (509)</option>
                                        <option value="1" selected="selected">US (1)</option>
                                        <option value="1809">DO (1809)</option></select>
                                </div>
                            </div>
                            <div class="col-md-1 account-profile__editor input-part-2">
                                <div class="form-group">
                                    <input id="profile.alternateAreaCode" type="text" name="alternateAreaCode" class="text form-control" minlength="1" value="252" maxlength="3"></div>
                            </div>
                            <div class="col-md-1 account-profile__editor input-part-3">
                                <div class="form-group">
                                    <input id="profile.alternatePhoneNumber" type="text" name="alternatePhoneNumber" class="text form-control" minlength="1" value="25846586" maxlength="8"></div>
                            </div>
                        </div>
                        <div class="row email-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.email"/></label>
                            <div class="col-md-9 account-profile__value">{{= email}}</div>
                            <div class="col-md-4 account-profile__editor">
                                <div class="form-group">
                                    <input id="profile.customerContactEmail" type="text" name="customerContactEmail" class="text form-control" value="kseniia_zaitseva@epam.com"></div>
                            </div>
                        </div>
                        <div class="row date-of-birth-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.dob"/></label>
                            <div class="col-md-9 account-profile__value">{{= dateOfBirth}}</div>
                            <div class="col-md-2 account-profile__editor account-month form-group">
                                <select id="profile.preferredLanguage" name="preferredLanguage" class="form-control">
                                    <option value="en" selected="selected">Aug</option>
                                    <option value="es_US">July</option>
                                </select>
                            </div>
                            <div class="col-md-1 account-profile__editor  account-data form-group">
                                <select id="profile.preferredLanguage" name="preferredLanguage" class="form-control">
                                    <option value="en" selected="selected">12</option>
                                    <option value="es_US">10</option>
                                </select>
                            </div>
                            <div class="col-md-1 account-profile__editor  account-year form-group">
                                <select id="profile.preferredLanguage" name="preferredLanguage" class="form-control">
                                    <option value="en" selected="selected">1975</option>
                                    <option value="es_US">1973</option>
                                </select>

                            </div>
                        </div>
                        <div class="row preferred-langauge-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.language.preferred"/></label>
                            <div class="col-md-9 account-profile__value">{{= preferredLanguage}}</div>
                            <div class="col-md-2 account-profile__editor form-group">
                                <template:errorspanfield path="preferredLanguage">
                                    <select id="profile.preferredLanguage" name="preferredLanguage" class="form-control">
                                        <option value="en" selected="selected">English</option>
                                        <option value="es_US">Español</option></select>
                                </template:errorspanfield>
                            </div>
                        </div>
                        <div class="row ">
                            <label class="col-md-3 account-profile__label" for="taxIdType"><spring:theme code="profile.personal.information.taxid.type"/><span class="skip"></span></label>
                            <div class="col-md-9 account-profile__value">{{= taxIdType}}</div>
                            <div class="col-md-4  account-profile__editor form-group">
                                    <select id="taxIdType" name="taxType" class="form-control">
                                    <option value="SSN">SSN</option>
                                    </select>
                            </div>
                        </div>
                        <div class="row"> 
                                <label class="col-md-3 account-profile__label" for="taxID"><spring:theme code="profile.personal.information.taxid"/></label>
                                <div class="col-md-9 account-profile__value">{{= taxId}}</div>
                                <div class="col-md-4 account-profile__editor form-group">
                                    <input id="taxID" name="taxId" class="text form-control" value="">
                                </div>
                        </div>
                        <div class="row progile-photo-form-group">
                            <label class="col-md-3 account-profile__label"><spring:theme code="profile.personal.information.photo"/>
                                <div class="subtext"><spring:theme code="profile.personal.information.photo.desc"/></div></label>
                            <div class="col-md-9 account-profile__value account-profile__value__avatar">
                                <img src="images/no_photo.jpg" class="" alt="No Photo" title="No Photo"></div>
                            <div class="delete-container col-md-4 account-profile__editor form-group">
                                <div class="col-md-4 account-profile__editor account-profile__editor__avatar form-group">
                                    <div class="account-profile__editor__avatar__img-overlay js-personal-info-edit-photo-button">
                                        <span class="icon-pencil icon-personal-info"></span>
                                        <span>Edit</span>
                                    </div>
                                    <input id="uploadPersonalPhoto" name="uploadPersonalPhoto" accept="image/jpeg,image/png,image/gif,image/xpng" type="file">
                                    
                                    <img src="images/no_photo.jpg" class="" alt="Tester" title="Tester">
                                </div>
                            </div>
                        </div>
                        <div class="row js-personal-info-agreement-block hidden">
                            <div class="col-xs-12 col-md-10 row-title agreement-title">
                                <span>Authorization &amp; agreement</span></div>
                            <div class="col-xs-12 col-md-10 row-description agreement-content">
                                <div class="wrapper">
                                    <div class="agreement-text">I acknowledge and affirm that: (1) I have read, understand and agree to be bound by the Terms of Use; (2) I own the content I am posting to this site or otherwise have the right to grant the license set forth in the Terms of Use, and (3) My posting of this content does not violate the rights of any other party, including but not limited to, copyright, contract rights, privacy or publicity rights, or any other right of any other person or entity.</div></div>
                            </div>
                            <div class="col-xs-12 col-md-10 row-description">
                                <div class="row checkbox-row">
                                    <label class="_checkbox-element-global-label">
                                        <input id="agreementPersonalInformation" name="agreementPersonalInformation" value="false" class="_checkbox-element-global-class js-personal-info-agreement-checkbox" type="checkbox">
                                        <span class="_checkbox-element-global-span"></span>
                                        <span class="checkbox-text _checkbox-element-global-text">I certify the account information I have provided to be true and accurate, and I acknowledge my acceptance of the Authorization and Agreement set forth above.</span></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-5">
                                <div class="accountActions account-profile__editor">
                                    <button type="submit" class="btn btn-primary save-button account-profile__save-button js-personal-info-save-button">Save</button>
                                    <button type="button" class="business-action-btn cancel-btn js-personal-info-cancel-button">Cancel</button>
                                </div>
                                <div class="account-profile__value">
                                    <button type="submit" class="btn btn-primary edit-button">Edit</button>
                                </div>
                            </div>
                        </div>
                        <div>
                            <input name="CSRFToken" value="b86a7fe2-cff1-444e-8054-d410f311f5c1" type="hidden"></div>
                    </form>
                </div>
            </div>
            <div class="hidden">
                <div id="popup_confirm_my_account_photo_delete" class="js-my_account_photo_delete_confirm_modal">
                    <div class="modal-actions">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-sm-push-6">
                                <button type="button" class="btn btn-primary btn-block js-my_account_photo_delete_confirm">Delete</button></div>
                            <div class="col-xs-12 col-sm-6 col-sm-pull-6">
                                <button type="button" class="js-my_account_photo_delete_cancel btn btn-default btn-block">Cancel</button></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

