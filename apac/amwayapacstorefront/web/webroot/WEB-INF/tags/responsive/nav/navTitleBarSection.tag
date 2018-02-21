<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>

<div class="menu-headers-container">
	<div>
		<ul class="tab-header-list" role="tablist">
			<c:forEach items="${component.navigationNode.children}" var="childrenNode" varStatus="loop">
				<li role="presentation" class="tab-header-item <c:if test="${loop.index==0}">active</c:if> ">
					<div class="arrow"></div>
					<a href="#navMenuTab${loop.index}" aria-controls="navMenuTab${loop.index}" role="tab" data-toggle="tab"
						class="tab-toggle">
						<div class="wrapper">
							<span class="tab-image">
								<img src="${childrenNode.nodeImg.url}" />
							</span>
							<span class="tab-title">${childrenNode.nodeLink.linkName}</span>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>