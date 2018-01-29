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

angular.module('amwayapaccmssmarteditModule', [
	'editorFieldMappingServiceModule',
	'amwayApacCmsItemDropdownModule',
	'amwayApacEditableItemPrinterModule',
	'amwayapaccmssmartedit/cmssmarteditContainerTemplates'
])
.run(function(editorFieldMappingService) {
	
	// adding a new mapping for the new component dropdown type
    editorFieldMappingService.addFieldMapping('AmwayApacCMSItemDropdown', null, null, {
    	template: 'amwayApacCmsItemDropdownWrapperTemplate.html'
    });
});
