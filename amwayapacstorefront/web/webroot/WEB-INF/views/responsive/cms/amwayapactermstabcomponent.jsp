<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-xs-12 ${mdWidth ? 'col-md-'.concat(colwidth).concat(' col') : ''}">
	<c:if test="${not empty title}">
    	<h4 class="uppercase"><cms:component component="${title}"/></h4>
    </c:if>
	<div class="col-text">
    	<cms:component component="${description}"/>
	</div>
    <div class="line"></div>
</div>