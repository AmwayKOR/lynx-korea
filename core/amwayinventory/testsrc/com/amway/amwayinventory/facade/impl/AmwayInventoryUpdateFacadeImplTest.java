package com.amway.amwayinventory.facade.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryUpdateRequest;
import com.amway.amwayinventory.data.AmwayInventoryUpdateResponse;
import com.amway.amwayinventory.data.AmwayInventoryValidationResult;
import com.amway.amwayinventory.service.AmwayInventoryValidationService;
import com.amway.amwayinventory.service.stock.AmwayConsolidatedBeanImportService;
import com.amway.amwayinventory.service.stock.AmwayStockConsolidationService;


@UnitTest
public class AmwayInventoryUpdateFacadeImplTest
{
	@InjectMocks
	private AmwayInventoryUpdateFacadeImpl amwayInventoryUpdateFacade;

	@Mock
	private Converter<AmwayInventoryUpdateRequest, List<AmwayInventoryBean>> amwayInventoryUpdateBeansConverter;
	@Mock
	private AmwayStockConsolidationService amwayStockConsolidationService;
	@Mock
	private AmwayConsolidatedBeanImportService amwayInventoryAdjustImportService;
	@Mock
	private AmwayInventoryValidationService amwayInventoryAdjustValidationService;

	@Mock
	private AmwayInventoryUpdateRequest incomingRequest;
	@Mock
	private AmwayInventoryValidationResult validationResult;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(amwayInventoryAdjustValidationService.validate(any())).thenReturn(validationResult);
	}

	@Test
	public void shouldConvertRequestToInventoryBeansDuringProcessing()
	{
		amwayInventoryUpdateFacade.process(incomingRequest);

		verify(amwayInventoryUpdateBeansConverter).convert(eq(incomingRequest), any());
	}

	@Test
	public void shouldValidateInventoryBeansWhenRequestConvertedSuccessfully()
	{
		amwayInventoryUpdateFacade.process(incomingRequest);

		verify(amwayInventoryAdjustValidationService).validate(any());
	}

	@Test
	public void shouldReturnErrorCodeAndAbortProcessingWhenOneOfInventoryBeansHasError()
	{
		when(validationResult.isAnyInvalid()).thenReturn(Boolean.TRUE);

		AmwayInventoryUpdateResponse response = amwayInventoryUpdateFacade.process(incomingRequest);

		assertTrue(response.getStatusCode() == 400);
		verifyNoMoreInteractions(amwayStockConsolidationService, amwayInventoryAdjustImportService);
	}

	@Test
	public void shouldConsolidateInventoryBeansWhenAllInventoryBeansHasNoErrors()
	{
		amwayInventoryUpdateFacade.process(incomingRequest);

		verify(amwayStockConsolidationService).consolidateInventory(any());
	}

	@Test
	public void shouldUpdateStockLevelsWhenInventoryBeansSuccessfullyConsolidated()
	{
		amwayInventoryUpdateFacade.process(incomingRequest);

		verify(amwayInventoryAdjustImportService).importStocks(any());
	}

	@Test
	public void shouldReturnSuccessCodeWhenRequestHasNoErrorsAndProcessedSuccessful()
	{
		AmwayInventoryUpdateResponse response = amwayInventoryUpdateFacade.process(incomingRequest);

		assertTrue(response.getStatusCode() == 200);
	}
}