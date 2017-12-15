<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="tabIndex" required="false" type="java.lang.Integer"%>
<%@ attribute name="user" required="true" type="de.hybris.platform.commercefacades.user.data.CustomerData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${country == 'ID'}">
			
		<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="form-control" mandatory="true" />
    		<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="form-control" mandatory="true"/>
    		<formElement:formInputBox idKey="address.line3" labelKey="address.line3" path="line3" inputCSS="form-control" mandatory="true"/>
    		<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="form-control" mandatory="true" />
    		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="form-control" mandatory="true" />
     	
	</c:when>
	<c:otherwise>
		
		<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="form-control" mandatory="true" />
    		<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="form-control" mandatory="true"/>
    		<formElement:formInputBox idKey="address.line3" labelKey="address.line3" path="line3" inputCSS="form-control" mandatory="true"/>
    		<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="form-control" mandatory="true" />
    		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="form-control" mandatory="true" />
	</c:otherwise>
</c:choose>
