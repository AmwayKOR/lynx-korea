<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="account-landing-page">
	<div class="user-account-header">
		<div class="container">
			<div class="row">
				<div class="user-block col-sm-4">
					<div class="user-image-container">
						<img class="user-image" src="${themeResourcePath}/images/yui.png">
						<div class="icon icon-pencil"></div>
					</div>
					<div class="user-info">
						<span class="user-name">Yui Mori</span>
						<span class="user-id">IBO # 1234567</span>
					</div>
				</div>
				<cms:pageSlot position="ProfileBarJumpTo" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>
</div>