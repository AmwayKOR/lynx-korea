/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
angular.module('amwayApacCmsItemDropdownModule', ['amwayapaccmssmartedit/cmssmarteditContainerTemplates'])
    .controller('amwayApacCmsItemDropdownController', function() {

        this.$onInit = function() {
            this.itemTemplateUrl = 'amwayApacCmsItemSearchTemplate.html';
            this.field.uri = this.field.uri || "/cmswebservices/v1/sites/CURRENT_CONTEXT_SITE_ID/cmsitems";
            this.field.params = this.field.params || {};
            this.field.params.catalogId = 'CURRENT_CONTEXT_CATALOG';
            this.field.params.catalogVersion = 'CURRENT_CONTEXT_CATALOG_VERSION';

        };

    })
    
    /**
     * @name amwayApacCmsItemDropdownModule.directive:amwayApacCmsItemDropdown
     * @scope
     * @restrict E
     * @element amway-apac-cms-item-dropdown
     * 
     * @description
     * Custom wrapper for CMS Item's on top of seDropdown component that upon selection of an option, will print the CMSItem
     * in the provided format.
     * 
     * @param {=Object} field The component field
     * @param {=String} id The component id
     * @param {=Object} model The component model
     * @param {=String} qualifier The qualifier within the structure attribute
     */
    .component('amwayApacCmsItemDropdown', {
        templateUrl: 'amwayApacCmsItemDropdownTemplate.html',
        controller: 'amwayApacCmsItemDropdownController',
        controllerAs: 'amwayApacCmsItemDropdownCtrl',
        bindings: {
            field: '=',
            qualifier: '=',
            model: '=',
            id: '='
        }
    });
