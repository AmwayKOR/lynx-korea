<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">
	<div class="container-fluid main-container">
                <div class="row cartTitile">
                    <h1 class="product-list-page-title mb25">SHOPPING LiST</h1></div>
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
                                                        <img class="cart-header__cart-icon" alt="cart" src="images/creat-new-list.png"></span>
                                                    <span class="title-element title-text">Create New Shopping List</span>
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
                                                <form method="post" action="#" class="create-shopping-list-form" id="saveShoppingListForm">
                                                    <div class="row new-shopping-list-row">
                                                        <div class="description">You can create shopping lists for yourself, your uplines, or your downlines. It's a simple way to keep track of your customers' favorite products, manage your orders, and share your lists.</div></div>
                                                    <div class="row new-shopping-list-row">
                                                        <div class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
                                                            <label for="newShoppingListName">
                                                                <h6>New List Name</h6></label>
                                                        </div>
                                                        <div class="col-sm-12 col-md-8 col-lg-8">
                                                            <input type="text" value="" required="required" class="shopping-list-input js-create-shopping-list-input" name="name" id="newShoppingListName"></div>
                                                    </div>
                                                    <div class="row new-shopping-list-row">
                                                        <div class="col-sm-12 col-md-4 col-lg-4 shopping-list-form-element-label">
                                                            <h6>List Recipient</h6></div>
                                                        <div class="col-sm-12 col-md-8 col-lg-8">
                                                            <div class="radio-wrapper">
                                                                <label class="amw-radio-wrap radio-label">
                                                                    <input type="radio" required="required" class="js-me-button amw-global-radio" checked="checked" name="ownerType" value="Me" id="me">
                                                                    <span class="amw-radio-overlay"></span>
                                                                    <span class="amw-label-radio-text">Me</span></label>
                                                            </div>
                                                            <div class="radio-wrapper">
                                                                <label class="amw-radio-wrap radio-label">
                                                                    <input type="radio" required="required" class="js-my-sponsor-button amw-global-radio" name="ownerType" value="Sponsor" id="mySponsor">
                                                                    <span class="amw-radio-overlay"></span>
                                                                    <span class="amw-label-radio-text">My Sponsor [Sponsor Name Goes Here]</span></label>
                                                            </div>
                                                            <div class="radio-wrapper los-search-wrap">
                                                                <label class="amw-radio-wrap radio-label">
                                                                    <input type="radio" required="required" class="js-downlines-button amw-global-radio" name="ownerType" value="Downline" id="personalySponsored">
                                                                    <span class="amw-radio-overlay"></span>
                                                                    <span class="amw-label-radio-text">Customs List from My Contacts</span></label>
                                                                <div class="new-ditto-search-contacts amway-theme">
                                                                    <span class="new-ditto-link-wrap">
                                                                        <a data-single-select="" title="Search: My contacts" href="#">
                                                                            <span class="new-ditto-icon icon-add-user"></span>
                                                                            <span class="link-text">Search Contacts</span></a>
                                                                    </span>
                                                                    <input type="hidden" name="ownerIDs" id="los-contacts">
                                                                    <input type="hidden" name="ownerType" id="los-contacts-type">
                                                                    <input type="hidden" name="ownerName" id="los-contacts-name">
                                                                    <span class="new-ditto-link-wrap">
                                                                        <a data-single-select="" title="Search: My contacts" class="losSearchLink new-ditto-link disabled" href="#">
                                                                            <span class="link-text js-los-search-total-value">(
                                                                                <t id="js-los-search-total">0</t>)Total</span></a>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <input type="radio" hidden="hidden" required="required" checked="checked" name="ownerType" value="Me" id="me">
                                                    <div class="row new-shopping-list-row">
                                                        <div class="col-xs-12 col-md-12">
                                                            <div class="shopping-list-row-button-wrapper">
                                                                <button id="createShoppingList" class="btn btn-primary small js-create-shopping-list-btn" type="submit">Create List</button></div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <input type="hidden" value="b23aa7c5-e296-4451-ba3b-43aec69ba792" name="CSRFToken"></div>
                                                </form>
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
                                            <h6 class="toggle-text">My Shopping Lists (3)</h6></a>
                                    </li>
                                    <li class="tab-toggle-wrap js-tab-toggle-wrap">
                                        <a data-toggle="tab" href="#downlineShoppingLists" class="tab-toggle">
                                            <h6 class="toggle-text">Downline Shopping Lists (0)</h6></a>
                                    </li>
                                </ul>
                                <div class="tab-content tabs-content-blocks">
                                    <div id="myShoppingLists" class="tab-pane content-block active">
                                        <div class="ditto-schedule-table-content">
                                            <!--<div class="col-xs-12 ditto-action-panel">-->
                                            <!--<span class="ditto-panel-element">-->
                                            <!--<label class="amw-checkbox-wrap">-->
                                            <!--<input type="checkbox" id="totalCheck" checked="" class="amw-global-checkbox js-list-selectall-checkbox" data-parent-id="myShoppingLists" data-target-class="js-landing-select-list" />-->
                                            <!--<span class="amw-checkbox-overlay"></span>-->
                                            <!--<span class="amw-label-checkbox-text">-->
                                            <!--<span class="checkbox-text"> Select all </span>-->
                                            <!--</span>-->
                                            <!--</label>-->
                                            <!--</span>-->
                                            <!--<span class="ditto-panel-element pull-right"> <button disabled="disabled" class="btn small link-btn ditto-remove-button js-multi-delete-lists" data-delete-popup-title="Delete Selected Ditto"> Delete </button>-->
                                            <!--<div class="hidden">-->
                                            <!--<div id="popup_confirm_shoppinglist_multi_delete" class="js-shoppinglist_multi_delete_confirm_modal shoppinglist_multi_delete_confirm_modal">-->
                                            <!--<div class="modal-actions">-->
                                            <!--<div class="row">-->
                                            <!--<div class="col-xs-12 col-sm-6 col-sm-push-6">-->
                                            <!--<button type="button" class="btn btn-primary btn-block js-remove-multi-list-confirm" id="removeShoppingListBtn" data-parent-id="myShoppingLists"> Delete </button>-->
                                            <!--</div>-->
                                            <!--<div class="col-xs-12 col-sm-6 col-sm-pull-6">-->
                                            <!--<button type="button" class="js-shoppinglist_delete_confirm_cancel btn btn-default btn-block"> Cancel </button>-->
                                            <!--</div>-->
                                            <!--</div>-->
                                            <!--</div>-->
                                            <!--</div>-->
                                            <!--</div>-->
                                            <!--</span>-->
                                            <!--</div>-->
                                            <div id="table-block-wrapper" class="table-block-wrapper new-table-block-wrapper">
                                                <div>
                                                    <input type="hidden" value="b23aa7c5-e296-4451-ba3b-43aec69ba792" name="CSRFToken"></div>
                                                <table class="ditto-schedule-table js-ditto-schedule-table">
                                                    <thead>
                                                    <tr>
                                                        <th class="ditto-field-name js-ditto-sorting-option">
                                                            <form class="ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">List Name
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button>
                                                            </form>
                                                            <form class="display-none ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">List Name
                                                                    <span class="header-icon icon-arrow-dropdown-expanded"></span></button>
                                                            </form>
                                                        </th>
                                                        <th class="js-ditto-sorting-option">
                                                            <form class="ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Created by
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button>
                                                            </form>
                                                            <form class="display-none ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Created by
                                                                    <span class="header-icon icon-arrow-dropdown-expanded"></span></button>
                                                            </form>
                                                        </th>
                                                        <th class="">
                                                            <form class="ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Added For
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button></form>
                                                        </th>
                                                        <th class="">
                                                            <form class="ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Last Updated
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button></form>
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <form method="post" class="js-ditto-shopping-list-items-form" id="checkShoppingList"></form>
                                                    <tr id="row-0">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html"> List Name Goes Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Holly Smith</td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-1">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Really Long List Name Would Go Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-2">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Firstname Lastname List Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Tony &amp; Linda Martin </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-3">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">List Name Goes Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Holly Smith</td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-4">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Really Long List Name Would Go Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-5">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Firstname Lastname List Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Tony &amp; Linda Martin </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-6">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">List Name Goes Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Holly Smith</td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-7">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Really Long List Name Would Go Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-8">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Firstname Lastname List Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Tony &amp; Linda Martin </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-9">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">List Name Goes Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="downlineShoppingLists" class="tab-pane content-block">
                                        <div class="ditto-schedule-table-content">
                                            <div id="table-block-wrapper" class="table-block-wrapper new-table-block-wrapper">
                                                <div>
                                                    <input type="hidden" value="b23aa7c5-e296-4451-ba3b-43aec69ba792" name="CSRFToken"></div>
                                                <table class="ditto-schedule-table js-ditto-schedule-table">
                                                    <thead>
                                                    <tr>
                                                        <th class="ditto-field-name js-ditto-sorting-option">
                                                            <form class="ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">List Name
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button>
                                                            </form>
                                                            <form class="display-none ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">List Name
                                                                    <span class="header-icon icon-arrow-dropdown-expanded"></span></button>
                                                            </form>
                                                        </th>
                                                        <th class="js-ditto-sorting-option">
                                                            <form class="ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Created by
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button>
                                                            </form>
                                                            <form class="display-none ditto-sorting-form js-ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Created by
                                                                    <span class="header-icon icon-arrow-dropdown-expanded"></span></button>
                                                            </form>
                                                        </th>
                                                        <th class="">
                                                            <form class="ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Added For
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button></form>
                                                        </th>
                                                        <th class="">
                                                            <form class="ditto-sorting-form">
                                                                <button class="ditto-header-button" type="submit">Last Updated
                                                                    <span class="header-icon icon-arrow-dropdown"></span></button></form>
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <form method="post" class="js-ditto-shopping-list-items-form" id="checkShoppingList"></form>
                                                    <tr id="row-0">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html"> List Name Goes Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Holly Smith</td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-1">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Really Long List Name Would Go Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-2">
                                                        <td class="ditto-field-name"> <span class="ditto-schedule-checkbox-wrapper">  </span> <span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Firstname Lastname List Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Tony &amp; Linda Martin </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-3">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">List Name Goes Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Holly Smith</td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-4">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Really Long List Name Would Go Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-5">
                                                        <td class="ditto-field-name"> <span class="ditto-schedule-checkbox-wrapper">  </span> <span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Firstname Lastname List Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Tony &amp; Linda Martin </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-6">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">List Name Goes Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Holly Smith</td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-7">
                                                        <td class="ditto-field-name"><span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Really Long List Name Would Go Here</a> </span> </td>
                                                        <td class=""> Jennifer Jones  </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-8">
                                                        <td class="ditto-field-name"> <span class="ditto-schedule-checkbox-wrapper">  </span> <span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">Firstname Lastname List Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Tony &amp; Linda Martin </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    <tr id="row-9">
                                                        <td class="ditto-field-name"> <span class="ditto-schedule-checkbox-wrapper">  </span> <span class="link-wrapper"> <a class="responsive-table-link js-saved-cart-name" href="shopping-list-detail.html">List Name Goes Here </a> </span> </td>
                                                        <td class=""> Jennifer Jones </td>
                                                        <td class="">Jennifer Jones </td>
                                                        <td class=""> </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
</template:page>