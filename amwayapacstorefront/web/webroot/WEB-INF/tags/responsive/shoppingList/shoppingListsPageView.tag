<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="shoppingLists" required="true" type="java.util.List" %>
<%@ attribute name="sortField" required="false" type="java.lang.String" %>

<div class="container-fluid main-container">
	<div class="print-hide breadcrumb-section">
		<ol class="breadcrumb">
			<li><a href="homepage.html">Home</a></li>
			<li class="active">Shopping List</li>
		</ol>
	</div>
</div>
<div class="container-fluid main-container">
	<div class="row cartTitile">
		<h1 class="product-list-page-title mb25">
			<spring:theme code="shopping.lists.page.heading.label" />
		</h1>
	</div>
	<div class="row cart-content-wrapper">
		<shoppingList:createShoppingListSection />
		<shoppingList:shoppingListsDisplaySection shoppingListsData="${shoppingLists}" sortField="${sortField}"/>
	</div>
</div>