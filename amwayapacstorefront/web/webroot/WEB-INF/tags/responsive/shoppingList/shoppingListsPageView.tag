<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid main-container">
                <div class="print-hide breadcrumb-section">
                    <ol class="breadcrumb">
                        <li>
                            <a href="homepage.html">Home</a></li>
                        <li class="active">Shopping List</li></ol>
                </div>
            </div>
	<div class="container-fluid main-container">
                <div class="row cartTitile">
                    <h1 class="product-list-page-title mb25"><spring:theme code="shopping.lists.page.heading.label" /></h1></div>
                <div class="row cart-content-wrapper">
                    <div class="amway-theme">
                        <div class="create-new-shopping-list-container new-create-new-shopping-list-container">
                            <div aria-multiselectable="true" role="tablist" class="accordion-element new-shopping-list-accordion" id="createShoppingListAccordion">
                                <div class="accordion-panel">
                                    <div id="creatShoppingList" role="tab" class="accordion-panel-heading">
                                        <div aria-controls="creatShoppingListBody" aria-expanded="true" href="#creatShoppingListBody" data-parent="#createShoppingListAccordion" data-toggle="collapse" class="accordion-toggle">
                                            <h5 class="accordion-panel-title">
                                                <span class="accordion-title-wrapper">
                                                    <span class="title-element accordion-header-icon icon-Hamburger-Plus">
                                                        <img class="cart-header__cart-icon" alt="cart" src="${themeResourcePath}/images/creat-new-list.png"></span>
                                                    <span class="title-element title-text"><spring:theme code="shopping.lists.page.create.list.label" /></span>
                                                    <span class="title-element accordion-icon-wrapper">
                                                        <span class="pull-right icon-minus"></span>
                                                    </span>
                                                </span>
                                            </h5>
                                        </div>
                                    </div>
                                    <div aria-labelledby="creatShoppingList" role="tabpanel" class="accordion-panel-collapse collapse in" id="creatShoppingListBody">
                                        <div class="accordion-collapse-wrapper">
                                            <div class="amway-theme">
	                                            <shoppingList:createShoppingListFormSection />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                                                	<c:when test="${(shoppingLists ne null) and (fn:length(shoppingLists) > 0)}">
		                                                <table class="ditto-schedule-table js-ditto-schedule-table">
		                                                    <thead>
		                                                    <tr>
		                                                        <th class="ditto-field-name js-ditto-sorting-option">
		                                                            <form class="ditto-sorting-form js-ditto-sorting-form" method="get">
		                                                            	<input type="hidden" name="sortField" value="byName" class="sortFieldInput" id="nameSortFieldInput">
		                                                            	<input type="hidden" name="sortOrder" value="desc" class="sortOrderInput" id="nameSortOrderInput">
		                                                                <button class="ditto-header-button" type="submit"><spring:theme code="shopping.lists.page.lists.table.name.label" />
		                                                                    <span class="header-icon icon-arrow-dropdown"></span></button>
		                                                            </form>
		                                                        </th>
		                                                        <th class="js-ditto-sorting-option">
		                                                            <form class="ditto-sorting-form js-ditto-sorting-form" method="get">
		                                                            	<input type="hidden" name="sortField" value="byUser" class="sortFieldInput" id="usernameSortFieldInput">
		                                                            	<input type="hidden" name="sortOrder" value="desc" class="sortOrderInput" id="usernameSortOrderInput">
		                                                                <button class="ditto-header-button" type="submit"><spring:theme code="shopping.lists.page.lists.table.createdby.label" />
		                                                                    <span class="header-icon icon-arrow-dropdown"></span></button>
		                                                            </form>
		                                                        </th>
		                                                        <th class="">
		                                                            <form class="ditto-sorting-form" method="get">
		                                                            	<input type="hidden" name="sortField" value="byAddedFor" class="sortFieldInput" id="addedforSortFieldInput">
		                                                            	<input type="hidden" name="sortOrder" value="desc" class="sortOrderInput" id="addedforSortOrderInput">
		                                                                <button class="ditto-header-button" type="submit"><spring:theme code="shopping.lists.page.lists.table.addedfor.label" />
		                                                                    <span class="header-icon icon-arrow-dropdown"></span></button></form>
		                                                        </th>
		                                                        <th class="">
		                                                            <form class="ditto-sorting-form" method="get">
		                                                            	<input type="hidden" name="sortField" value="byLastUpdated" class="sortFieldInput" id="lastupdatedSortFieldInput">
		                                                            	<input type="hidden" name="sortOrder" value="desc" class="sortOrderInput" id="lastupdatedSortOrderInput">
		                                                                <button class="ditto-header-button" type="submit"><spring:theme code="shopping.lists.page.lists.table.lastupdated.label" />
		                                                                    <span class="header-icon icon-arrow-dropdown"></span></button></form>
		                                                        </th>
		                                                    </tr>
		                                                    </thead>
		                                                    <tbody>
			                                                    <c:forEach items="${shoppingLists}" var="shoppingList" varStatus="loop">
				                                                    <tr id="row-${loop.index} }">
				                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">${shoppingList.name}</a> </span> </td>
				                                                        <td class=""> ${shoppingList.user.name}</td>
				                                                        <td class="">${shoppingList.user.name}</td> <!-- downline functinality is not done yet, so just using the user name here -->
				                                                        <td class=""><fmt:formatDate value="${shoppingList.lastUpdated}" type="both"/></td>
				                                                    </tr>
			                                                    </c:forEach>
		                                                    </tbody>
		                                                </table>
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
                </div>
            </div>