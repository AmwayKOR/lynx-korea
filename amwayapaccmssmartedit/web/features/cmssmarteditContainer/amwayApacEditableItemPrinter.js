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
angular.module('amwayApacEditableItemPrinterModule',
		[ 'amwayapaccmssmartedit/cmssmarteditContainerTemplates' ]).controller(
		'amwayApacEditableItemPrinterController', function(editorModalService) {
			this.clickAction = function(item) {
				editorModalService.open({
					smarteditComponentId : item.uid,
					smarteditComponentUuid : item.uuid,
					smarteditComponentType : item.itemtype,
					smarteditCatalogVersionUuid : item.catalogVersion
				});
			};
		})
    /**
     * @name amwayApacEditableItemPrinterModule.directive:amwayApacEditableItemPrinter
     * @scope
     * @restrict E
     * @element amway-apac-editable-item-printer
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
    .component('amwayApacEditableItemPrinter', {
        templateUrl: 'amwayApacEditableItemPrinterTemplate.html',
        controller: 'amwayApacEditableItemPrinterController',
        controllerAs: 'amwayApacEditItem',
        bindings: {
        	item: '='        
        }
    });
