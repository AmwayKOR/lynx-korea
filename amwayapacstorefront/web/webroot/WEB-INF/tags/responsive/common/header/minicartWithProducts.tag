<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>

<ol class="mini-cart-list">
	<c:forEach items="${entries}" var="entry" end="${numberShowing - 1}">
		<header:minicartIndividualEntry entry="${entry}" />
	</c:forEach>
</ol>
<header:minicartSummarySection/>
<div class="links">
	<div>
		<div class="cartPopupButtons"></div>
	</div>
</div>
