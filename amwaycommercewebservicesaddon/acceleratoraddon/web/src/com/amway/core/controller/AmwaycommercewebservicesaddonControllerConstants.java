/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 *
 */
package com.amway.core.controllers;

/**
 */
public interface AmwaycommercewebservicesaddonControllerConstants
{
	// implement here controller constants used by this extension
    public interface Role{
        String GUEST = "ROLE_GUEST";
        String CLIENT = "ROLE_CLIENT";
        String TRUSTED_CLIENT = "ROLE_TRUSTED_CLIENT";
        String CUSTOMERGROUP = "ROLE_CUSTOMERGROUP";
        String CUSTOMERMANAGERGROUP = "ROLE_CUSTOMERMANAGERGROUP";
        String POSEMPLOYEEGROUP = "ROLE_POSEMPLOYEEGROUP";
    }

    public interface Scope{
        String REPORTS = "reports";
        String SALES = "extended";
    }

}
