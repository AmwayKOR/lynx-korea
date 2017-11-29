<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="collapse popover fade bottom" role="tooltip" id="login-drop-content">
    <i class="mobile-popover-close"></i>
    <div class="arrow" style=""></div>
    <h3 class="popover-title" style="display: none;"></h3>
    <div class="popover-content">
        <ul class="nav__links account-popover js-my-account-popover">
            <li class="account-popover__congratulation">
                <div class="account">
                    <div class="sys-message">
                        <div class="ico-wraper">
                            <i class="icon-caution"></i>
                        </div>
                        <span><spring:theme code="text.header.logged.icon-caution" /></span></div>
                    <div class="sys-message">
                        <div class="ico-wraper">
                            <span class="message-num"><spring:theme code="text.header.logged.message-num" /></span></div>
                        <span><spring:theme code="text.header.logged.message" /></span></div>
                </div>
            </li>
            <li class="account-popover__scores">
                <ul>
                    <li><spring:theme code="text.header.logged.account-popover_scores" /></li>
                    <li><spring:theme code="text.header.logged.account-popover_scores.no" /></li></ul>
                <p><spring:theme code="text.header.logged.account-popover_scores.update" /></p>
            </li>
            <li class="account-popover__pv-display" onclick="javascript:location.href='dashboard-MVP.html'">
                <img src="${themeResourcePath}/images/performance-chart-arch-mobile.png" alt="" /></li>
         
            <cms:pageSlot position="HeaderAccountNavigation" var="component">
           		<cms:component component="${component}" />
            </cms:pageSlot>
            <li class="account-popover__element sign-out">
               <c:url value="/logout" var="logoutUrl"/>
                <a href="${logoutUrl}" title="Sign Out">
                    <i class="glyphicon glyphicon-log-out"></i><spring:theme code="text.header.logged.signout" /></a>
            </li>
        </ul>
    </div>
</div>