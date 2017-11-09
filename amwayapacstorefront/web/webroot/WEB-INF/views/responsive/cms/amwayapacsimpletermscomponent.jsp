<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<li>
	<form:hidden path="term[${radioCount}].optional" value="${optional}" />
    <div class="agreement-box">
    	<c:if test="${not empty title}">
	    <h3 class="uppercase"><cms:component component="${title}"/>
	    	<a class="show-terms"><i class="icon icon-exports"></i></a>
	    </h3>
    	</c:if>
		<cms:component component="${content}"/>
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
                <form:radiobutton value="true" path="term[${radioCount}].termAccepted" name="radio-${radioCount}" cssClass="amw-global-radio"/>
                <span class="amw-radio-overlay"></span>
                <span class="amw-label-radio-text">${radio2Description}</span></label>
        </div>
    </div>
</li>