<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="col-sm-12 col-md-4 featured-product__item">
	<div class="featured-product__thumnail">
		 <cms:component component="${banner}"  />
	</div>
	<p class="featured-product__title">${title}</p>
	<div class="featured-product__subtitle">
		<cms:component component="${description}"  />
	</div>
	<div class="featured-product__link ">
     	<cms:component component="${link}"  />
  	</div>
                              
</div>