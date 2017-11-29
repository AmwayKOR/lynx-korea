<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="popover auto-suggestion-popover fade bottom in" role="tooltip" id="">
	<div class="popover-arrow arrow" style="left: 71.9353%;"></div>
	<h3 class="popover-title" style="display: none;"></h3>
	<div class="popover-content">
		<button class="search-results-close">x</button>
		<ul>
			<div
				class="category-wrapper js-category-wrapper suggested-words-wrapper js-suggested-words-wrapper js-search-words-wrapper">
			</div>
			<div class="category-wrapper js-category-wrapper products-wrapper hidden-xs hidden-sm">
				<div class="header row">
					<div class="title col-md-6">
						<span>
							<spring:theme code="text.header.search.title" />
						</span>
					</div>
					<div class="view-all-container col-md-6">
						<a class="js-products-view-all-results" href="">
							<spring:theme code="text.header.search.viewallresults" />
						</a>
					</div>
				</div>
			</div>
		</ul>
	</div>
</div>