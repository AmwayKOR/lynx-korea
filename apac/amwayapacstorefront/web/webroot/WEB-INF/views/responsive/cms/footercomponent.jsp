<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"%>

<div id="quick">
	<div class="quick-links">
		<div class="container">
			<div class="row">
				<footer:footerNavigation />
				<footer:footerQuickLinks />
			</div>
		</div>
	</div>
</div>

<footer>
	<div class="footer">
		<footer:footerCopyrightTermsSection />
	</div>
</footer>