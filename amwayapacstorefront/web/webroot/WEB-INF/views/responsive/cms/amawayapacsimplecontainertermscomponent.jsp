<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<li  class="${maxHeight ? 'simple' : ''}">
	<form:hidden path="term[${radioCount}].optional" value="${optional}" />
	<c:if test="${not empty title}">
	    <h3 class="uppercase"><cms:component component="${title}"/>
	    	<a class="show-terms"><i class="icon icon-exports"></i></a>
	    </h3>
    </c:if>
    <form:errors path="term[${radioCount}].termAccepted" />
    <form:errors path="term[${radioCount}].optional" />
    <div class="conditions">
    	<div class="text">
			<cms:component component="${content}"/>
   		</div>
           <button class="text-btn view-all">
               View All
               <i class="icon icon-arrow-downs"></i>
               <i class="icon icon-arrow-ups"></i>
           </button>
    </div>
    <div class="action amway-theme">
        <div class="radio-wrapper">
            <label class="amw-radio-wrap radio-label">
	            <form:radiobutton value="true" path="term[${radioCount}].termAccepted" checked="checked" name="radio-${radioCount}" cssClass="amw-global-radio"/>
                <span class="amw-radio-overlay"></span>
                <span class="amw-label-radio-text">${radio1Description}</span></label>
        </div>
        <div class="radio-wrapper">
            <label class="amw-radio-wrap radio-label">
	            <form:radiobutton value="false" path="term[${radioCount}].termAccepted" name="radio-${radioCount}" cssClass="amw-global-radio"/>
                <span class="amw-radio-overlay"></span>
                <span class="amw-label-radio-text">${radio2Description}</span></label>
        </div>
    </div>
    <div class="pop-box-container">
	    <div class="pop-box">
	        <div class="pop-header uppercase">
	        	<cms:component component="${title}"/><i class="pop-close icon icon-x-close"></i>
	        </div>
	        <div class="pop-content">
	            <div id="terms">
	                <div class="div-table">
						<div class="col-xs-12">
							<div class="col-text">
						    	<cms:component component="${content}"/>
							</div>
						    <div class="line"></div>
						</div>
	                </div>
	            </div>
	            <div class="action amway-theme">
	                    <div class="radio-wrapper">
	                        <label class="amw-radio-wrap radio-label">
	                            <form:radiobutton value="true" path="term[${radioCount}].termAccepted" checked="checked" name="radio-${radioCount}" cssClass="amw-global-radio"/>
	                            <span class="amw-radio-overlay"></span>
	                            <span class="amw-label-radio-text">${radio1Description}</span></label>
	                    </div>
	                    <div class="radio-wrapper">
	                        <label class="amw-radio-wrap radio-label">
	                            <form:radiobutton value="false" path="term[${radioCount}].termAccepted" name="radio-${radioCount}" cssClass="amw-global-radio"/>
	                            <span class="amw-radio-overlay"></span>
	                            <span class="amw-label-radio-text">${radio2Description}</span></label>
	                    </div>
	                </div>
	        </div>
	        <div class="pop-footer">
	            <a href="register-simple-terms.html" class="btn-blue-white" id="submit-btn">SUBMIT</a>
	            <button class="btn-cancel" id="cancel-btn">Cancel</button>
	        </div>
	   </div>
   </div>
</li>
