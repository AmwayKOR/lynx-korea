<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="tabIndex" required="false" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


	
	<div class="form-group">
        <formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="form-control" mandatory="true" />
	</div>
	<div class="form-group">
        <formElement:formInputBox idKey="address.lastName" labelKey="address.lastName" path="lastName" inputCSS="form-control" mandatory="true" />
	</div>
    <div class="form-group">
        <formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="form-control" mandatory="true" />
    </div>
    <div class="form-group">
        <formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="form-control" mandatory="true"/>
    </div>
    <div class="form-group">
        <formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="form-control" mandatory="true" />
    </div>
    <div class="form-group">
        <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="form-control" mandatory="true" />
    </div>
    <div class="form-group">
        <formElement:formInputBox idKey="address.phone" labelKey="address.phone" path="phone" inputCSS="form-control" mandatory="true" />
    </div>
    <div class="form-group">
        <formElement:formInputBox idKey="address.email" labelKey="address.email" path="email" inputCSS="form-control" mandatory="false" />
    </div>
	
