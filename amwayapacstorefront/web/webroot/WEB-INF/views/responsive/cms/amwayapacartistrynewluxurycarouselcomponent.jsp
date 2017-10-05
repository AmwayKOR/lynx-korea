<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div>
                        <div class="sub_banner">
                            <div class="banner__wrapper">
                            		
                                <span class="banner__message--main">${title}</span>
                                <br />
                                <div class="banner__message--sub">
                                		<cms:component component="${description}"  />
                                </div>
                                <div class="banner__shop-now">
                                		<cms:component component="${shopnow}"  />
                                </div>
                                </div>
                            <img class="banner__image-sub" src="${media.url}" alt="${media.altText}" />
                          </div>
</div>