<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="mobile-header-cell navigation-menu-toggle-wrapper">
	<a role="button" data-toggle="collapse" href="#mobile-menu-accordion" aria-expanded="false"
		aria-controls="mobile-menu-accordion" class="overlay-menu-toggle-mobile js-overlay-menu-toggle-mobile collapsed">
		<span class="hamburger-icon icon-Icon-Hamburger-01"></span>
	</a>
	<div class="panel-group collapse overlay-mobile-menu js-overlay-mobile-menu js-remove-overlay-desktop-menu"
		id="mobile-menu-accordion" role="tablist" aria-multiselectable="true">

		<cms:pageSlot position="MobileNavigationBar" var="component">
			<cms:component component="${component}" />
		</cms:pageSlot>

		<li role="presentation" class=" nav-list-element">
			<div class="overlay-menu-component-mobile-wrapper country">
				<div class="panel-group" id="mobile-menu-category-accordion" role="tablist" aria-multiselectable="true">
					<div class="panel panel-default overlay-menu-mobile__panel">
						<cms:pageSlot position="MobileCountrySelector" var="component1">
							<cms:component component="${component1}" />
						</cms:pageSlot>
					</div>
				</div>
			</div>
		</li>
	</div>
	<div class="overlay-mobile-menu-background js-overlay-mobile-menu-background"></div>
</div>
