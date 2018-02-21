package com.amway.amwayinventory.service.impl;

import static java.util.Collections.singletonList;
import static org.fest.util.Collections.list;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryValidationResult;


@UnitTest
public class AmwayInventoryValidationServiceImplTest
{
	@InjectMocks
	private AmwayInventoryValidationServiceImpl amwayInventoryValidationService;

	@Mock
	private Converter<Map<AmwayInventoryBean, Errors>, AmwayInventoryValidationResult> amwayInventoryValidationResultConverter;

	@Mock
	private AmwayInventoryBean inventoryBean;
	@Mock
	private Validator firstValidator;
	@Mock
	private Validator secondValidator;
	@Mock
	private Collection<AmwayInventoryBean> sourceData;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		when(sourceData.stream()).thenReturn(Stream.of(inventoryBean));
		when(sourceData.size()).thenReturn(1);
	}

	@Test
	public void shouldCallAllValidatorsForEachInventorBeanWhenInventoryBeansNotEmpty()
	{
		amwayInventoryValidationService.setValidators(list(firstValidator, secondValidator));

		amwayInventoryValidationService.validate(sourceData);

		verify(firstValidator, times(sourceData.size())).validate(any(), any());
		verify(secondValidator, times(sourceData.size())).validate(any(), any());
	}

	@Test
	public void shouldCallConverterWhenValidationIsDone()
	{
		amwayInventoryValidationService.setValidators(singletonList(firstValidator));

		amwayInventoryValidationService.validate(sourceData);

		verify(amwayInventoryValidationResultConverter).convert(any());
	}
}