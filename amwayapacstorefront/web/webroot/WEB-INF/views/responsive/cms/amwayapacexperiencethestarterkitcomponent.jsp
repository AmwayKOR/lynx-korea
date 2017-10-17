<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="experience-brands clearfix clear-border">
	<div class="new-hot-brand">
		<div class="experience-brands__content bg-white clear-bg">
			<div class="wrapper-limit extra3">
				<div class="wrapper">
					<div class="banner-content">
						<div class="banner-title-wrapper">
							<h2 class="banner-title">
								<span>${headerText} </span>
							</h2>
							<div class="sub-title-wrap">
								<span class="banner-sub-title"> <span><cms:component
											component="${details}" /></span></span>
							</div>
						</div>
						<div class="banner-button-wrap">
							<cms:component component="${offerImageLink}" />
						</div>
					</div>
					<img class="experience-brands__image experience-brands__image_magazine"
						src="${backgroundImage.media.url}" alt="new-hot-brand" />
				</div>
			</div>
		</div>

	</div>
</div>