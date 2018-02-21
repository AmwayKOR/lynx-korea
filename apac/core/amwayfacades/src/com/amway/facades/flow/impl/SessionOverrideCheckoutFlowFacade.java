/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package com.amway.facades.flow.impl;

import de.hybris.platform.acceleratorfacades.flow.impl.DefaultCheckoutFlowFacade;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.acceleratorservices.enums.CheckoutFlowEnum;
import de.hybris.platform.acceleratorservices.enums.CheckoutPciOptionEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * Specialised version of the DefaultCheckoutFlowFacade that allows the checkout flow and pci strategy to be overridden
 * in the session. This is primarily used for demonstration purposes and you may not need to use this sub-class in your
 * environment.
 */
public class SessionOverrideCheckoutFlowFacade extends de.hybris.platform.acceleratorfacades.flow.impl.SessionOverrideCheckoutFlowFacade
{
     //note how this class was doing its thing was deprecated in version 5.6.  Classs now simply extends from the OTB impl
}
