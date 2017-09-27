<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<div class="video-preview col-sm-12">
	<div class="video-preview__img">
		<cms:component component="${bannerGif1}"  />
	</div>
	<div class="video-preview__img-mob">
		<cms:component component="${bannerGif2}"  />
	</div>
	<div class="video-preview__content">
        <img class="video-preview__play" src="${playbutton.url}" alt="icon" />
        <p class="video-preview__title">${title}</p>
        <div class="video-preview__subtitle">
			<cms:component component="${description}"  />
		</div>
    </div>
       
</div>