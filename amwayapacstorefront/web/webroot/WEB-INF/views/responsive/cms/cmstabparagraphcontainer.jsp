<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="amway-theme">
    <div class="accordion-element new-accordion-element" id="productPageAccordion" role="tablist" aria-multiselectable="true">
    	<div class="panel panel-default accordion-panel">
          <c:forEach var="component" items="${components}">
              <cms:component component="${component}" />
          </c:forEach>
        </div>
    </div>
</div>