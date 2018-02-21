<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shoppingList" tagdir="/WEB-INF/tags/responsive/shoppingList"%>

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
							<shoppingList:createShoppingListForm />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
