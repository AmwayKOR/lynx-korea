package com.amway.amwayinventory.facade.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.amway.amwayinventory.data.AmwayConsolidatedInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryUpdateRequest;
import com.amway.amwayinventory.data.AmwayInventoryUpdateResponse;
import com.amway.amwayinventory.data.AmwayInventoryValidationResult;
import com.amway.amwayinventory.facade.AmwayInventoryUpdateFacade;
import com.amway.amwayinventory.service.AmwayInventoryValidationService;
import com.amway.amwayinventory.service.stock.AmwayConsolidatedBeanImportService;
import com.amway.amwayinventory.service.stock.AmwayStockConsolidationService;
import com.hybris.commons.rest.resources.HttpStatus;


public class AmwayInventoryUpdateFacadeImpl implements AmwayInventoryUpdateFacade
{
	private static final Logger LOG = LoggerFactory.getLogger(AmwayInventoryUpdateFacadeImpl.class);

	@Autowired
	private Converter<AmwayInventoryUpdateRequest, List<AmwayInventoryBean>> amwayInventoryUpdateBeansConverter;
	@Autowired
	private AmwayStockConsolidationService amwayStockConsolidationService;
	@Autowired
	private AmwayConsolidatedBeanImportService amwayInventoryAdjustImportService;
	@Autowired
	private AmwayInventoryValidationService amwayInventoryAdjustValidationService;

	@Override
	public AmwayInventoryUpdateResponse process(AmwayInventoryUpdateRequest request)
	{
		List<AmwayInventoryBean> inventoryBeans = new ArrayList<>();
		amwayInventoryUpdateBeansConverter.convert(request, inventoryBeans);

		AmwayInventoryValidationResult validationResult = amwayInventoryAdjustValidationService.validate(inventoryBeans);

		if (validationResult.isAnyInvalid())
		{
			LOG.error("Invalid request body : {}", validationResult.getErrorMessage());
			return buildResponse(HttpStatus.BadRequest.CODE);
		}

		Collection<AmwayConsolidatedInventoryBean> consolidatedBeans = amwayStockConsolidationService.consolidateInventory(
				validationResult.getBeansWithoutErrors());
		amwayInventoryAdjustImportService.importStocks(consolidatedBeans);
		return buildResponse(HttpStatus.OK.CODE);
	}

	private AmwayInventoryUpdateResponse buildResponse(int statusCode)
	{
		AmwayInventoryUpdateResponse response = new AmwayInventoryUpdateResponse();
		response.setStatusCode(statusCode);
		return response;
	}
}
