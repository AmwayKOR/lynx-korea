/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.amwaycoetools.controllers;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.webservicescommons.mapping.DataMapper;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import de.hybris.platform.core.Registry;
import de.hybris.platform.stock.impl.DefaultStockService;

import com.amway.amwaycoetools.data.*;
import com.amway.amwaycoetools.dto.AmwayBeanListWsDTO;
import com.amway.amwaycoetools.facades.CoeToolsFacades;

/**
 *
 */
@Controller
@RequestMapping(value = "/coetools")
@AmwayBean(ext="amwaycoetools",docs="https://jira.amway.com:8444/display/HC/amwaycoetools")
public class CoeToolsController
{
    public static final String DEFAULT_FIELD_SET = "DEFAULT";

    @Resource
    private CoeToolsFacades coeToolsFacades;


    @Resource(name = "dataMapper")
    private DataMapper dataMapper;


    /**
     *
     */
    @RequestMapping(value = "/amwaybeans", method = RequestMethod.GET)
    @ResponseBody
    public AmwayBeanListWsDTO getAmwayBeans( @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
    {
        return dataMapper.map(coeToolsFacades.getAmwayBeans(), AmwayBeanListWsDTO.class, fields);
    }

    /**
     *
     */
    @RequestMapping(value = "/amwaybeans/core", method = RequestMethod.GET)
    @ResponseBody
    public AmwayBeanListWsDTO getCoreAmwayBeans( @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
    {
        return dataMapper.map(coeToolsFacades.getCoreAmwayBeans(), AmwayBeanListWsDTO.class, fields);
    }

    /**
     *
     */
    @RequestMapping(value = "/amwaybeans/lynx", method = RequestMethod.GET)
    @ResponseBody
    public AmwayBeanListWsDTO getLynxAmwayBeans( @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
    {
        return dataMapper.map(coeToolsFacades.getLynxAmwayBeans(), AmwayBeanListWsDTO.class, fields);
    }


    /**
     *
     */
    @RequestMapping(value = "/amwaybeans/annotations", method = RequestMethod.GET)
    @ResponseBody
    public AmwayBeanListWsDTO getAmwayAnnotations( @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
    {
        return dataMapper.map(coeToolsFacades.getAmwayAnnotation(), AmwayBeanListWsDTO.class, fields);
    }

    /**
     *
     */
    @RequestMapping(value = "/amwaybeans/no/annotation", method = RequestMethod.GET)
    @ResponseBody
    public AmwayBeanListWsDTO getAmwayBeansWithoutAnnotations( @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
    {
        return dataMapper.map(coeToolsFacades.getAmwayBeansWithoutAnnotation(), AmwayBeanListWsDTO.class, fields);
    }


    /**
     *
     */
    @RequestMapping(value = "/amwaybeans/hybris/override", method = RequestMethod.GET)
    @ResponseBody
    public AmwayBeanListWsDTO getAmwayOverrideBeans( @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
    {
        return dataMapper.map(coeToolsFacades.getAmwayHybrisOverrideBeans(), AmwayBeanListWsDTO.class, fields);
    }



}

