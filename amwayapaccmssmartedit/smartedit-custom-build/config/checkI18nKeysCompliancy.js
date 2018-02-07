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
module.exports = function() {

    return {
        prefix: {
            ignored: ['se.', 'type.'], // keys provided by smartedit-locales_en.properties
            expected: ['amwayapaccmssmartedit.']
        },
        paths: {
            files: [
                "web/features/**/*Template.html",
                "web/features/**/*.js"
            ],
            properties: [
                "resources/localization/amwayapaccmssmartedit-locales_en.properties",
                "gruntTasks/smartedit-locales_en.properties"
            ]
        }
    };
};
