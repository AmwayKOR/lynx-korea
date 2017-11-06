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
                        <span>A new ABO has signed a contract!</span></div>
                    <div class="sys-message">
                        <div class="ico-wraper">
                            <span class="message-num">99</span></div>
                        <span>You have new messages!</span></div>
                </div>
            </li>
            <li class="account-popover__scores">
                <ul>
                    <li>PV: 300.0</li>
                    <li>CVR: No</li></ul>
                <p>Last Update: 8/17/2016 - 4:07pm</p>
            </li>
            <li class="account-popover__pv-display" onclick="javascript:location.href='dashboard-MVP.html'">
                <img src="${themeResourcePath}/images/performance-chart-arch-mobile.png" alt="" /></li>
         
            <cms:pageSlot position="HeaderAccountNavigation" var="component">
           		<cms:component component="${component}" />
            </cms:pageSlot>
            <li class="account-popover__element sign-out">
               <c:url value="/logout" var="logoutUrl"/>
                <a href="${logoutUrl}" title="Sign Out">
                    <i class="glyphicon glyphicon-log-out"></i>Sign Out</a>
            </li>
        </ul>
    </div>
</div>