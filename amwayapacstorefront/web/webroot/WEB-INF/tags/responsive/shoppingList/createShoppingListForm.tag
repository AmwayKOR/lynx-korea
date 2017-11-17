<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="createShoppingListurl" value="/shopping-lists/create-shopping-list" />
<form method="POST" action="${createShoppingListurl}" class="create-shopping-list-form" id="createShoppingListForm">
	<div class="row new-shopping-list-row">
		<div class="description">
			<spring:theme code="shopping.lists.page.create.list.description" />
		</div>
	</div>
	<div class="row new-shopping-list-row">
		<div class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
			<label for="newShoppingListName">
				<h6><spring:theme code="shopping.lists.page.create.form.name.label" /></h6>
			</label>
		</div>
		<div class="col-sm-12 col-md-8 col-lg-8">
			<input type="text" value="" required="required"
				class="shopping-list-input js-create-shopping-list-input"
				name="shoppingListName" id="newShoppingListName">
		</div>
	</div>
	<div class="row new-shopping-list-row create-list-button-wrapper">
		<div class="col-xs-12 col-md-12">
			<div class="shopping-list-row-button-wrapper">
				<button id="createShoppingList"	class="btn btn-primary small js-create-shopping-list-btn" type="submit"><spring:theme code="shopping.lists.page.create.form.submit.label" /></button>
			</div>
		</div>
	</div>
</form>