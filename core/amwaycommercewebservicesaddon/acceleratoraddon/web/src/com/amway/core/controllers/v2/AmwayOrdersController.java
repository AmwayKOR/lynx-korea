/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.core.v2.controllers;


import com.amway.core.exceptions.*;

import de.hybris.platform.commercefacades.order.data.*;
import de.hybris.platform.commerceservices.enums.SalesApplication;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.core.enumeration.dto.EnumerationListWsDTO;
import com.amway.core.enumeration.data.EnumerationListData;

import de.hybris.platform.cmsfacades.data.EnumData;

import com.amway.core.v2.controller.BaseCommerceController;
import com.amway.facades.enumData.AmwayEnumDataFacade;

/**
 *  Order Search and View API's controller for Amway Global
 *  
 */
@Controller
@RequestMapping(value = "/{baseSiteId}")
public class AmwayOrdersController extends BaseCommerceController
{

	private static final Logger LOG = Logger.getLogger(AmwayOrdersController.class);

	@Resource(name = "amwayEnumDataFacade")
	private AmwayEnumDataFacade amwayEnumDataFacade;


	/**
	 * Returns order Sales Channel/Application enum for Order search view filters
	 * 
	 * @return SalesChannelTypeWsDTO
	 */
	@Secured({ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/order/salesChannels", method = RequestMethod.GET)
	@ResponseBody
	public EnumerationListWsDTO getSalesChannelTypes(
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		List<EnumData> enumData = amwayEnumDataFacade.getEnumValuesByClass(SalesApplication.class);
		EnumerationListData enumDataList = new EnumerationListData();
		enumDataList.setEnumerationValues(enumData);
		return getDataMapper().map(enumDataList, EnumerationListWsDTO.class, fields);
	}

}

