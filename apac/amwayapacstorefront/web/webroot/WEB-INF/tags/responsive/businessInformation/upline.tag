<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="panel panel-default">
	<div class="panel-heading" role="tab" id="headingThree">
		<h4 class="panel-title">
			<div class="collapsed" data-toggle="collapse" data-parent="#business-page-accordion" href="#BusinessCollapseThree"
				aria-expanded="false" aria-controls="BusinessCollapseThree">
				<span class="icon-arrow-with-circle-up icon-businnes"></span>
				<span class="text-uppercase">
					<spring:theme code="business.information.upline" />
				</span>
				<span class="indicator pull-right"></span>
			</div>
		</h4>
	</div>
	<div id="BusinessCollapseThree" class="panel-collapse upline-panel collapse" role="tabpanel"
		aria-labelledby="headingThree" style="height: 0px;">
		<div v-if="uplines.length" v-for="(upline, index) in uplines" id="upline-container">
		    <div class="upline-component">
		        <div class="upline-header">
		            <span class="title">{{upline.level}}</span>
		            <span class="id">&nbsp;&nbsp;|&nbsp;&nbsp;{{upline.aboId}}</span>
		        </div>
		        <div class="row upline-row">
		            <div class="upline-item col-md-6">
		                <div class="row">
		                    <div class="col-xs-12 col-md-4 row-title"><spring:theme code="business.information.upline.name"/></div>
		                    <div class="col-xs-12 col-md-8 row-description">{{upline.name}}</div>
		                </div>
		                <div class="row">
		                    <div class="col-xs-12 col-md-4 row-title"><spring:theme code="business.information.upline.phone"/></div>
		                    <div class="col-xs-12 col-md-8 row-description">{{upline.phone}}</div>
		                </div>
		                <div class="row">
		                    <div class="col-xs-12 col-md-4 row-title"><spring:theme code="business.information.upline.email"/></div>
		                    <div class="col-xs-12 col-md-8 row-description">{{upline.email}}</div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<div v-else class="account-orderhistory-pagination">
			<p class="text-center">
				<spring:theme code="business.information.applicant.view" />
			</p>
		</div>
	</div>
</div>