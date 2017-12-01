<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="panel panel-default">
	<div class="panel-heading" role="tab" id="headingOne">
		<h4 class="panel-title">
			<div data-toggle="collapse" data-parent="#business-page-accordion" href="#BusinessCollapseOne" aria-expanded="true"
				aria-controls="BusinessCollapseOne" class="collapsed">
				<span class="icon-shop icon-businnes"></span>
				<span class="text-uppercase">
					<spring:theme code="business.information" />
				</span>
				<span class="indicator pull-right"></span>
			</div>
		</h4>
	</div>
	<div id="BusinessCollapseOne" class="panel-collapse business-info collapse" role="tabpanel"
		aria-labelledby="headingOne" style="height: 40px;">
		<div id="business-information-container"></div>
	</div>
</div>

<script id="business-information-template" type="text/x-jquery-tmpl">
    <div class="row">
        <div class="col-xs-12 col-md-3 row-title"><spring:theme code="business.information.name"/></div>
        <div class="col-xs-12 col-md-5 row-description">{{= name}}</div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-3 row-title"><spring:theme code="business.information.tax.id"/></div>
        <div class="col-xs-12 col-md-5 row-description">{{= tax_id}}</div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-3 row-title"><spring:theme code="business.information.mission.statement"/></div>
        <div id="mission-statement-text" class="col-xs-12 col-md-5 row-description mission-statement">{{= mission_statement}}</div>
        <textarea id="mission-statement-textarea" class="col-xs-12 col-md-5 row-description mission-statement">{{= mission_statement}}</textarea>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-3 row-title">
            <span><spring:theme code="business.information.photo"/></span>
            <div class="photo-description"><spring:theme code="business.information.photo.description"/></div>
        </div>
        <div class="col-xs-12 col-md-5 row-description business-photo">
            <div><img src="{{= photo}}"><button class="photo-edit"><span>Edit</span></button></div>
        </div>
    </div>
    <div class="row">
        <div  class="edit edit-item-link col-xs-12 col-md-3 row-title">
            <button class="btn btn-default btn-block businees-action-btn btn-primary"><spring:theme code="business.information.button.edit"/></button>
        </div>
    </div>
</script>