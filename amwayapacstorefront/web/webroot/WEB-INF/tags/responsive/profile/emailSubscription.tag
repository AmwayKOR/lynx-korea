<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="panel panel-default account-profile__panel">
	<div class="panel-heading" role="tab" id="headingEmail">
	    <h4 class="panel-title">
	        <div class="collapsed" role="button" data-toggle="collapse" data-parent="#profileEditAccordion" href="#collapseEmail" aria-expanded="false" aria-controls="collapseEmail">
	            <span class="icon-personal-info icon-mail"></span>
	            <span class="text-uppercase"><spring:theme code="profile.email.subscription.label"/></span>
	            <span class="indicator pull-right"></span>
	        </div>
	    </h4>
	</div>
	<div id="email-subscription-container"></div>
</div>

<script id="email-subscription-template" type="text/x-jquery-tmpl">
<div id="collapseEmail" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEmail">
    <div class="panel-body">
	    <div class="row">
	        <div class="col-md-12 email-subscriptions-container">
	            <div class="account-profile__subtitle"><spring:theme code="profile.email.subscription.description"/></div>
		            <div class="js-email-subscriptions-container">
		                <div class="amway-theme">
		                    <div class="row">
	                            <div class="col-xs-12 col-md-6">
	                                <div class="account-profile__checkbox-wrapper">
	                                    <div class="amw-checkbox-wrap">
	                                        <input id="name1" name="name1" class="amw-global-checkbox" {{if amwayWeeklyUpdate}} checked {{/if}} type="checkbox">
	                                        <span class="amw-checkbox-overlay"></span>
	                                        <div class="amw-checkbox-lable-box">
	                                            <label for="name1" class="amw-after-checkbox-label"><spring:theme code="profile.email.subscription.amway.weekly.update"/></label>
	                                            <p><spring:theme code="profile.email.subscription.amway.weekly.update.description"/></p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-xs-12 col-md-6">
	                                <div class="account-profile__checkbox-wrapper">
	                                    <div class="amw-checkbox-wrap">
	                                        <input id="name3" name="name3" class="amw-global-checkbox" type="checkbox" {{if amwayUpdates}} checked {{/if}}>
	                                        <span class="amw-checkbox-overlay"></span>
	                                        <div class="amw-checkbox-lable-box">
	                                            <label for="name1" class="amw-after-checkbox-label"><spring:theme code="profile.email.subscription.amway.update"/></label>
	                                            <p><spring:theme code="profile.email.subscription.amway.update.description"/></p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-xs-12 col-md-6">
	                                <div class="account-profile__checkbox-wrapper">
	                                    <div class="amw-checkbox-wrap">
	                                        <input id="name2" name="name2" class="amw-global-checkbox" type="checkbox" {{if myIBOBusinessInfo}} checked {{/if}}>
	                                        <span class="amw-checkbox-overlay"></span>
	                                        <div class="amw-checkbox-lable-box">
	                                            <label for="name1" class="amw-after-checkbox-label"><spring:theme code="profile.email.subscription.myibo.businessinfo"/></label>
	                                            <p><spring:theme code="profile.email.subscription.myibo.businessinfo.description"/></p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-xs-12 col-md-6">
	                                <div class="account-profile__checkbox-wrapper">
	                                    <div class="amw-checkbox-wrap">
	                                        <input id="name1" name="name1" class="amw-global-checkbox" type="checkbox" {{if campaignUpdates}} checked {{/if}}>
	                                        <span class="amw-checkbox-overlay"></span>
	                                        <div class="amw-checkbox-lable-box">
	                                            <label for="name1" class="amw-after-checkbox-label"><spring:theme code="profile.email.subscription.campaign.update"/></label>
	                                            <p><spring:theme code="profile.email.subscription.campaign.update.description"/></p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            
	                            <div class="col-xs-12 col-md-6">
	                                <div class="account-profile__checkbox-wrapper">
	                                    <div class="amw-checkbox-wrap">
	                                        <input id="name3" name="name3" class="amw-global-checkbox" type="checkbox" {{if iboAINewsletter}} checked {{/if}}>
	                                        <span class="amw-checkbox-overlay"></span>
	                                        <div class="amw-checkbox-lable-box">
	                                            <label for="name1" class="amw-after-checkbox-label"><spring:theme code="profile.email.subscription.ibo.ainewsletter"/></label>
	                                            <p><spring:theme code="profile.email.subscription.ibo.ainewsletter.description"/></p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-xs-12 col-md-6">
	                                <div class="account-profile__checkbox-wrapper">
	                                    <div class="amw-checkbox-wrap">
	                                        <input id="name2" name="name2" class="amw-global-checkbox" type="checkbox" {{if amwayMarketResearch}} checked {{/if}}>
	                                        <span class="amw-checkbox-overlay"></span>
	                                        <div class="amw-checkbox-lable-box">
	                                            <label for="name1" class="amw-after-checkbox-label"><spring:theme code="profile.email.subscription.amway.marketresearch"/></label>
	                                            <p><spring:theme code="profile.email.subscription.amway.marketresearch.description"/></p>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
		                    </div>
		                </div>
		            </div>
		            <div class="js-email-subscriptions-buttons">
		                <button class="btn btn-primary js-email-subscriptions-save-btn" type="button" name="button"><spring:theme code="profile.email.subscription.button.save"/></button>
		            </div>
	        </div>
	    </div>
	</div>
</div>
</script>