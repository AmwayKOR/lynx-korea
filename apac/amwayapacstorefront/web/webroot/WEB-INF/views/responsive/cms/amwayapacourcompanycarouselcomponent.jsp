<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div>
	<div class="slideBox">
		<div class="slide-text">
			<h1>${year}</h1>
			<p class="mob">${mobiledesc.content} </p>
			<div class="pc"> <cms:component component="${component.desktopdesc}" /> </div>
		</div>
		<div class="slide-img"><cms:component component="${bannerImg}" /></div>
	</div>
</div>
	