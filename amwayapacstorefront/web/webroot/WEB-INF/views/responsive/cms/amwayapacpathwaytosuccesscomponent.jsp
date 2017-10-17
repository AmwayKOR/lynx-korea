<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="product-category-page amway-theme">
	<div class="product-categories">
		<div class="amway-theme">
			<div class="full-width-banner-component banner-left-align ">
				<div class="banner-image-wrap full-width-banner">
					<img class="desktop-image" src="${backgroundImage.media.url}"
						alt="" /> <img class="desktop-image-mob"
						src="images/article_baner.png" alt="banner" />
				</div>
				<div class="banner-content-wrap-ink-out">
					<div class="banner-content-wrap-ink">
						<div class="banner-content">
							<div class="banner-title-wrapper">
								<h2 class="banner-title">
									<span>${headerText}</span>
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
					</div>
				</div>
			</div>
		</div>
	</div>
</div>