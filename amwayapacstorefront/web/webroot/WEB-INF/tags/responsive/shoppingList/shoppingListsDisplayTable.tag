<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="shoppingListsData" required="true" type="java.util.List" %>
<%@ attribute name="sortField" required="false" type="java.lang.String" %>

 <table class="ditto-schedule-table js-ditto-schedule-table">
     <thead>
     <tr>
         <th class="ditto-field-name js-ditto-sorting-option">
             <form class="ditto-sorting-form js-ditto-sorting-form" method="get">
             	<input type="hidden" name="sortField" value="byName" class="sortFieldInput" id="nameSortFieldInput">
             	<input type="hidden" name="sortOrder" value="desc" class="sortOrderInput" id="nameSortOrderInput">
                 <button class="ditto-header-button" type="submit"><spring:theme code="shopping.lists.page.lists.table.name.label" />
                     <span class="header-icon icon-arrow-dropdown <c:if test="${sortField eq 'byName'}">underline</c:if>"></span></button>
             </form>
         </th>
         <th class="">
             <form class="ditto-sorting-form" method="get">
             	<input type="hidden" name="sortField" value="byLastUpdated" class="sortFieldInput" id="lastupdatedSortFieldInput">
             	<input type="hidden" name="sortOrder" value="desc" class="sortOrderInput" id="lastupdatedSortOrderInput">
                 <button class="ditto-header-button" type="submit"><spring:theme code="shopping.lists.page.lists.table.lastupdated.label" />
                     <span class="header-icon icon-arrow-dropdown <c:if test="${sortField eq 'byLastUpdated'}">underline</c:if>"></span></button></form>
         </th>
     </tr>
     </thead>
     <tbody>
      <c:forEach items="${shoppingListsData}" var="shoppingList" varStatus="loop">
       <tr id="row-${loop.index}">
       	   <c:url value="/shopping-lists/detail/${shoppingList.uid}" var="shoppingListDetailsUrl" />
           <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="${shoppingListDetailsUrl}">${shoppingList.name}</a> </span> </td>
           <td class=""><fmt:formatDate value="${shoppingList.lastUpdated}" type="both"/></td>
       </tr>
      </c:forEach>
     </tbody>
 </table>
