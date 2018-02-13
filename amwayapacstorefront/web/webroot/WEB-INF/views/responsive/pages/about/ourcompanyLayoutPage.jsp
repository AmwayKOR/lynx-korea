<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<template:page pageTitle="${pageTitle}" >

	<div id="our-company" class="extra2">
		<div class="container-fluid main-container ">
			<div class="row cartTitile">
				<h1 class="product-list-page-title mb25">Our Company</h1>
			</div>
	    </div> 
	    
	    <cms:pageSlot position="Contentslot1" var="feature" element="div">
            <cms:component component="${feature}" />
        </cms:pageSlot>
	    
	    
	    <cms:pageSlot position="Contentslot2" var="feature" element="div">
            <cms:component component="${feature}" />
        </cms:pageSlot>
	    
	    
	    <div class="container-fluid my-count">
	        <div class="row">
	            <div id="business-page-accordion" role="tablist" aria-multiselectable="true">
	                <div class="panel panel-default">
	                    <div class="panel-heading" role="tab" id="headingTwo">
	                        <h4 class="panel-title">
	                            <div class="collapsed" data-toggle="collapse" data-parent="#business-page-accordion" href="#ourCompanyOne" aria-expanded="false" aria-controls="BusinessCollapseTwo">
	                                <span class="glyphicon glyphicon-folder-close"></span>
	                                <span class="text-uppercase">accordion content tbd</span>
	                                <span class="indicator pull-right"></span>
	                            </div>
	                        </h4>
	                    </div>
	                    <div id="ourCompanyOne" class="panel-collapse applicants-panel collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
	                        <div class="row">
	                            
	                        </div>
	                       
	                    </div>
	                </div>
	                <div class="panel panel-default">
	                    <div class="panel-heading" role="tab" id="headingTwo">
	                        <h4 class="panel-title">
	                            <div class="collapsed" data-toggle="collapse" data-parent="#business-page-accordion" href="#ourCompanyTwo" aria-expanded="false" aria-controls="BusinessCollapseTwo">
	                                <span class="glyphicon glyphicon-folder-close"></span>
	                                <span class="text-uppercase">accordion content tbd</span>
	                                <span class="indicator pull-right"></span>
	                            </div>
	                        </h4>
	                    </div>
	                    <div id="ourCompanyTwo" class="panel-collapse applicants-panel collapse" role="tabpanel" aria-labelledby="headingTwo" style="height: 0px;">
	                        <div class="row">
	                            
	                        </div>
	                       
	                    </div>
	                </div>
	                <div class="panel panel-default">
	                    <div class="panel-heading" role="tab" id="headingTwo">
	                        <h4 class="panel-title">
	                            <div class="collapsed" data-toggle="collapse" data-parent="#business-page-accordion" href="#ourCompanyThree" aria-expanded="false" aria-controls="BusinessCollapseTwo">
	                                <span class="glyphicon glyphicon-folder-close"></span>
	                                <span class="text-uppercase">accordion content tbd</span>
	                                <span class="indicator pull-right"></span>
	                            </div>
	                        </h4>
	                    </div>
	                    <div id="ourCompanyThree" class="panel-collapse applicants-panel collapse" role="tabpanel" aria-labelledby="headingThree" style="height: 0px;">
	                        <div class="row">
	                            
	                        </div>
	                       
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    
	</div>
</template:page>

