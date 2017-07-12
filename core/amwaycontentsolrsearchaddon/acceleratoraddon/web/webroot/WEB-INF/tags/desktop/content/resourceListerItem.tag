
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="content" required="true" type="com.amway.facade.content.data.ResourceData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="content" tagdir="/WEB-INF/tags/desktop/content" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<script>
	var popupWindow = null;
	function centeredPopup(url,winName,w,h,scroll){
	LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
	TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
	settings =
	'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
	popupWindow = window.open(url,winName,settings)
}
</script>

<div id="tab6" class="tab_view">
	<div class="tab_conatin_hd">
<c:choose>
	<c:when  test="${fn:endsWith(content.title, '.mp3')}">
	<div class="vdo_links_pdp">
	<div class="vdo_pdp gallery">
					<a href="${content.id}" onclick="centeredPopup(this.href,'myWindow','700','300','yes');return false"><img class="vdo_img"
						src="/_ui/desktop/common/images/vdo.png" /><span> ${content.title}<spring:theme
								code="product.videoResource" /></span></a>
				</div>
				</div>
	</c:when>
	<c:when  test="${fn:endsWith(content.title,'.pdf')}">
	<div class="pdf_links_pdp">
	<div class="pdf_pdp">
				<a href="${content.id}" target="_blank"><img class="pdf_img"
					src="/_ui/desktop/common/images/pdf.png" /><span> ${content.title}<spring:theme
							code="product.download" />  </span></a>
			</div></div>
	</c:when>
	<c:otherwise>
	<span>${content.title}</span>
	</c:otherwise>
</c:choose>
</div>
</div>