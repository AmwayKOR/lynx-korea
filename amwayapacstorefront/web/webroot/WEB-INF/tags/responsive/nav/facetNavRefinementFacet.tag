<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="facet" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="isMobile" type="java.lang.Boolean" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="mobileOrPc" value="pc"/>
<c:if test="${isMobile}">
	<c:set var="mobileOrPc" value="mob"/>
</c:if>

                             	
<div class="panel">
    <div class="panel-heading" role="tab" id="${facet.code}Facet">
        <h4 class="panel-title">
            <div role="button" class="collapse-button collapsed" data-toggle="collapse" data-parent="#facetAccordion" href="#${facet.code}FacetBody${mobileOrPc}" aria-controls="${facet.code}FacetBody">
                <span class="text-uppercase">${facet.name}<span class=""></span>
                </span>
                <span class="pull-right icon-minus"></span>
            </div>
        </h4>
    </div>
    <div id="${facet.code}FacetBody${mobileOrPc}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" style="height: 0px;">
        <div class="panel-body">
            <div id="tabContent_${facet.code}Facet" class="facet js-facet" data-current-query="${searchPageData.currentQuery.query.value}">
                <div class="facet__name js-facet-name">
                    <span class="glyphicon facet__arrow"></span>
                    Shop by ${facet.name}</div>
                <div class="facet__values js-facet-values js-facet-form">
                <ul class="facet__list js-facet-list js-facet-top-values">
               		<c:forEach items="${facet.values}" var="facetValue">
                		<li class="js-facet-value js-facet-init-value js-facet-top-value">
                           <input class="js-facet-item-value" type="hidden" name="facetValue" value="retailPriceFacet:$0-$49.99">
                           <input class="js-facet-item-query" type="hidden" name="facetQuery" value=":name-asc-c">
                           <form action="" method="get" class="non-js-desctop">
                           <input type="hidden" name="q" value="${facetValue.query.query.value}"></input>
                               <label class="hidden-xs hidden-sm">
                                   <input class="facet__list__checkbox js-facet-checkbox  js-facet-item-checkbox sr-only" type="checkbox" <c:if test="${facetValue.selected}">checked="checked"</c:if>>
                                   <span class="facet__list__label">
                                   <span class="facet__list__mark"></span>
                                   <span class="facet__list__text">
                                       ${facetValue.name}<span class="facet__value__count">
                                               (${facetValue.count})</span>
                                       </span>
                               </span>
                               </label>
                               <label class="hidden-md hidden-lg">
                                   <input class="facet__list__checkbox js-facet-mobile-checkbox js-facet-item-checkbox sr-only" type="checkbox" data-facet-code="${facet.code}" data-facet-value-code="${facetValue.code}" <c:if test="${facetValue.selected}">checked="checked"</c:if>>
                                   <span class="facet__list__label">
                                   <span class="facet__list__mark"></span>
                                   <span class="facet__list__text">
                                       ${facetValue.name}<span class="facet__value__count">
                                               (${facetValue.count})</span>
                                       </span>
                               </span>
                               </label>
                           </form>
                       </li>
               		</c:forEach>
                 </ul>
                 <div class="facet-button-wrapper hidden-md hidden-lg">
                     <button type="button" class="js-facet-group-button-apply facet-button-apply">Apply</button>
                 </div>
             </div>
         </div>
     </div>
  </div>
</div>
