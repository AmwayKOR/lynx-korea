<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="desktop-nav-menu clearfix">
	<div class="col-sm-12 col-sm-8 top-menu">
		<div class="top-menu-links pull-left">
			<nav:topNavigation />
		</div>
	</div>
	<div class="col-sm-12 col-md-4 top-search print-hide">
		<div class="site-search">
			<cms:pageSlot position="SearchBox" var="comp1">
				<cms:component component="${comp1}" />
			</cms:pageSlot>
		</div>
	</div>
</div>