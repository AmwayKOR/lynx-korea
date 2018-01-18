<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="panel panel-default account-profile__panel">
	<div id="manage-opt-ins-container"></div>
</div>

<script id="manage-opt-ins-template" type="text/x-jquery-tmpl">
<div class="panel-heading" role="tab" id="headingOptIns">
    <h4 class="panel-title">
        <div class="collapsed" role="button" data-toggle="collapse" data-parent="#profileEditAccordion" href="#collapseOptIns" aria-expanded="false" aria-controls="collapseOptIns">
            <span class="icon-personal-info icon-message-dots"></span>
            <span class="text-uppercase"><spring:theme code="profile.manage.optins.label"/></span>
            <span class="indicator pull-right"></span>
        </div>
    </h4>
</div>
<div id="collapseOptIns" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOptIns">
    <div class="panel-body">
        <div class="row">
            <div class="col-md-12">
                <p>{{= description}}</p>
                <div class="amway-theme">
                    <div class="account-profile__checkbox-wrapper">
                        <div class="amw-checkbox-wrap">
                            <input id="name1" name="name1" class="amw-global-checkbox" {{if amwayWeeklyUpdate}} checked {{/if}} type="checkbox">
                            <span class="amw-checkbox-overlay"></span>
                            <div class="amw-checkbox-lable-box">
                                <label for="name1" class="amw-after-checkbox-label normal"><spring:theme code="profile.manage.optins.weekly.update"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="account-profile__checkbox-wrapper">
                        <div class="amw-checkbox-wrap">
                            <input id="name2" name="name2" class="amw-global-checkbox" disabled=""  {{if textMessageNotification}} checked {{/if}} type="checkbox">
                            <span class="amw-checkbox-overlay"></span>
                            <div class="amw-checkbox-lable-box">
                                <label for="name1" class="amw-after-checkbox-label normal"><spring:theme code="profile.manage.optins.text.message.notification"/></label>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary account-profile__edit-button" type="button" name="button"><spring:theme code="profile.manage.optins.edit"/></button>
            </div>
        </div>
    </div>
</div>
</script>