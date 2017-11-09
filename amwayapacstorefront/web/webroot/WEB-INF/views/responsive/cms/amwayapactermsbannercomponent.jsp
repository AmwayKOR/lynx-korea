<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div id="masthead" class="main-container">
    <img src="${backgroundImage.media.url}">
    <div class="text small-wrap"> 
        <h2 class="uppercase">${headerText}</h2>
        <cms:component component="${details}" />
    </div>
</div>