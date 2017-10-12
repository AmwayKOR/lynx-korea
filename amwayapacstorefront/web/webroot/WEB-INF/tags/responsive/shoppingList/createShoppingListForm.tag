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
		<div
			class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
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
	<div class="row new-shopping-list-row">
		<div
			class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
			<h6><spring:theme code="shopping.lists.page.create.form.recipient.label" /></h6>
		</div>
		<div class="col-sm-12 col-md-8 col-lg-8">
			<div class="radio-wrapper">
				<label class="amw-radio-wrap radio-label"> 
					<input type="radio" required="required" class="js-me-button amw-global-radio" checked="checked"	name="ownerType" value="Me" id="me"> 
					<span class="amw-radio-overlay"></span> 
					<span class="amw-label-radio-text"><spring:theme code="shopping.lists.page.create.form.recipient.radio.me.label" /></span>
				</label>
			</div>
			<div class="radio-wrapper">
				<label class="amw-radio-wrap radio-label"> 
					<input type="radio" required="required"	class="js-my-sponsor-button amw-global-radio" name="ownerType" value="Sponsor" id="mySponsor"> 
					<span class="amw-radio-overlay"></span> 
					<span class="amw-label-radio-text"><spring:theme code="shopping.lists.page.create.form.recipient.radio.sponsor.label" /></span>
				</label>
			</div>
			<div class="radio-wrapper los-search-wrap">
				<label class="amw-radio-wrap radio-label"> 
					<input type="radio" required="required" class="js-downlines-button amw-global-radio" name="ownerType" value="Downline" id="personalySponsored"> 
						<span class="amw-radio-overlay"></span> 
						<span class="amw-label-radio-text"><spring:theme code="shopping.lists.page.create.form.recipient.radio.contacts.label" /></span>
				</label>
				<div class="new-ditto-search-contacts amway-theme">
					<span class="new-ditto-link-wrap"> 
						<a data-single-select="" title="Search: My contacts" href="#"> 	
							<span class="new-ditto-icon icon-add-user"></span> 
							<span class="link-text"><spring:theme code="shopping.lists.page.create.form.recipient.radio.contacts.search.label" /></span>
						</a>
					</span> 
					<input type="hidden" name="ownerIDs" id="los-contacts"> 
					<input type="hidden" name="ownerType" id="los-contacts-type"> 
					<input type="hidden" name="ownerName" id="los-contacts-name"> 
					<span class="new-ditto-link-wrap"> 
						<a data-single-select="" title="Search: My contacts" class="losSearchLink new-ditto-link disabled" href="#"> 
							<span class="link-text js-los-search-total-value">( <t id="js-los-search-total">0</t>)<spring:theme code="shopping.lists.page.create.form.recipient.radio.contacts.search.total.label" /></span>
						</a>
					</span>
				</div>
			</div>
		</div>
	</div>
	<input type="radio" hidden="hidden" required="required"
		checked="checked" name="ownerType" value="Me" id="me">
	<div class="row new-shopping-list-row">
		<div class="col-xs-12 col-md-12">
			<div class="shopping-list-row-button-wrapper">
				<button id="createShoppingList"	class="btn btn-primary small js-create-shopping-list-btn" type="submit"><spring:theme code="shopping.lists.page.create.form.submit.label" /></button>
			</div>
		</div>
	</div>
</form>