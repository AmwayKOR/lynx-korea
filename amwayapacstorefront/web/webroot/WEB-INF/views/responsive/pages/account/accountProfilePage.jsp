<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/responsive/profile"%>

<div class="container-fluid my-count">
	<div class="row">
		<div class=" container">
			<div class="panel-group account-profile" id="profileEditAccordion" role="tablist" aria-multiselectable="true">
				<profile:personalInformation/>
				<profile:passwordAndSecurity/>
				<profile:manageOptIns/>
				<profile:emailSubscription/>
			</div>
		</div>
	</div>
</div>