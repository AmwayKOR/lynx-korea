<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="company-banner">
    <img src="${bannerDekstopImg.url}" class="pc"/>
    <img src="${bannerMobileImg.url}" class="mob"/>
    
    <div class="banner_wrapper">
        <h1>${bannertitle}</h1>
        <span class="text"><cms:component component="${text}" /></span>
        <a href="${link.url}" class="banner-more">${link.linkName}</a>
    </div>
</div>