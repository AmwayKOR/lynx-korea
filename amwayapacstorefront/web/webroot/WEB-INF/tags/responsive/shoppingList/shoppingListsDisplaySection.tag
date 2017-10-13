<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="shoppingListsData" required="true" type="java.util.List" %>
<%@ attribute name="sortField" required="false" type="java.lang.String" %>

 <div class="amway-theme">
     <!--tabs component-->
     <div class="ditto-nav-tabs-wrap js-ditto-nav-tabs-wrap">
         <div class="navigation-tabs-container">
             <ul class="tabs-toggles">
                 <li class="tab-toggle-wrap js-tab-toggle-wrap active">
                     <a data-toggle="tab" href="#myShoppingLists" class="tab-toggle">
                         <h6 class="toggle-text"><spring:theme code="shopping.lists.page.lists.tab.my.label" arguments="${fn:length(shoppingLists)}" /></h6></a>
                 </li>
                 <li class="tab-toggle-wrap js-tab-toggle-wrap">
                     <a data-toggle="tab" href="#downlineShoppingLists" class="tab-toggle">
                         <h6 class="toggle-text"><spring:theme code="shopping.lists.page.lists.tab.downlines.label" /></h6></a>
                 </li>
             </ul>
             <div class="tab-content tabs-content-blocks">
                 <div id="myShoppingLists" class="tab-pane content-block active">
                     <div class="ditto-schedule-table-content">
                         <div id="table-block-wrapper" class="table-block-wrapper new-table-block-wrapper">
                             <c:choose>
                             	<c:when test="${(shoppingListsData ne null) and (fn:length(shoppingListsData) > 0)}">
                             		<shoppingList:shoppingListsDisplayTable shoppingListsData="${shoppingListsData}" sortField="${sortField}"/>
                             	</c:when>
                             	<c:otherwise>
                            			<spring:theme code="shopping.lists.page.lists.tab.downlines.noresults" />                                                	
                             	</c:otherwise>
                             </c:choose>
                         </div>
                     </div>
                 </div>
                 <div id="downlineShoppingLists" class="tab-pane content-block">
                     <div class="ditto-schedule-table-content">
                         <div id="table-block-wrapper" class="table-block-wrapper new-table-block-wrapper">
                            	<spring:theme code="shopping.lists.page.lists.tab.downlines.noresults" />
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
 </div>