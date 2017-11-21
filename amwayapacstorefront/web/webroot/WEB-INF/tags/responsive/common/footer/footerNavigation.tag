<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<ul id="accordion" class="col-sm-8">
	<c:forEach items="${navigationNodes}" var="node">
		<c:if test="${node.visible}">
			<c:forEach items="${node.children}" var="childnode" varStatus="loop">
				<li class="quick-links-item col-sm-3 panel">
					<h5 class="quick-links-header">
						<cms:component component="${childnode.nodeLink}" />
					</h5>
					<ul id="quick-links-collapse1" class="panel-collapse collapse">
						<c:forEach items="${childnode.links}" varStatus="i" var="childlink">
							<cms:component component="${childlink}" evaluateRestriction="true" element="li" />
							<c:if test="${loop.last == true && i.last == true}">
								<li>
									<p class="quick-links-title">NEED HELP?</p>
									<img src="${themeResourcePath}/images/footer_chat.png" />
									<a href="#">Chat Live Now</a>
								</li>
							</c:if>

						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</c:if>
	</c:forEach>
</ul>
