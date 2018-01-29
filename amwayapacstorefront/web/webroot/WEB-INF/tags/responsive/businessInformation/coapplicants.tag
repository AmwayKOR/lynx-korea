<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="panel panel-default">
	<div class="panel-heading" role="tab" id="headingTwo">
		<h4 class="panel-title">
			<div class="collapsed" data-toggle="collapse" data-parent="#business-page-accordion" href="#BusinessCollapseTwo"
				aria-expanded="false" aria-controls="BusinessCollapseTwo">
				<span class="icon-users icon-businnes"></span>
				<span class="text-uppercase">
					<spring:theme code="business.information.applicant" />
				</span>
				<span class="indicator pull-right"></span>
			</div>
		</h4>
	</div>
	<div id="BusinessCollapseTwo" class="panel-collapse applicants-panel collapse" role="tabpanel"
		aria-labelledby="headingTwo" style="height: 0px;">
		<div v-if="coApplicants.length" class="row">
			<div id="co-applicants-container">
				<div class="applicant-row col-md-12" v-for="i in Math.ceil(coApplicants.length / 2)">
				    <div class="applicant-item col-md-6" v-for="coApplicant in coApplicants.slice((i - 1) * 2, i * 2)">
				    	<div class="row">
				    		<div class="col-xs-12 col-md-5 row-title"><spring:theme code="business.information.applicant.name"/></div>
				    		<div class="col-xs-12 col-md-7 row-description">{{ coApplicant.name }}</div>
				    	</div>
				    	<div class="row">
				    		<div class="col-xs-12 col-md-5 row-title"><spring:theme code="business.information.applicant.phone"/></div>
				    		<div class="col-xs-12 col-md-7 row-description">{{ coApplicant.phone }}</div>
				    	</div>
				    	<div class="row">
				    		<div class="col-xs-12 col-md-5 row-title"><spring:theme code="business.information.applicant.phone.secondary"/></div>
				    		<div class="col-xs-12 col-md-7 row-description">{{ coApplicant.secondaryPhone }}</div>
				    	</div>
				    	<div class="row">
				    		<div class="col-xs-12 col-md-5 row-title"><spring:theme code="business.information.applicant.email"/></div>
				    		<div class="col-xs-12 col-md-7 row-description">{{ coApplicant.email }}</div>
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
		<div class="account-orderhistory-pagination">
			<p class="text-center">
				<a type="button" id="show-more" class="btn btn-link show-more-orders js-show-more-personal btn-show-more-business-information">
					<span>
						<spring:theme code="business.information.applicant.view" />
					</span>
					<span class="icon icon-arrow-dropdown"></span>
				</a>
			</p>
		</div>
	</div>
</div>
