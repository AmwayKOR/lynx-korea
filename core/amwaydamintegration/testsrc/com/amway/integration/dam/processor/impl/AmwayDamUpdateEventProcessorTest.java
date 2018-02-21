package com.amway.integration.dam.processor.impl;

import static com.amway.integration.dam.enums.AmwayDamOperation.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.integration.dam.processor.AmwayDamAssetProcessor;
import com.amway.integration.dam.validate.AmwayDamValidator;


/**
 * Unit test for {@link AmwayDamUpdateEventProcessor}
 */
@UnitTest
public class AmwayDamUpdateEventProcessorTest
{
	@InjectMocks
	private AmwayDamUpdateEventProcessor amwayDamUpdateEventProcessor;

	@Mock
	private AmwayDamAssetProcessor amwayDamAssetProcessor;
	@Mock
	private Converter<AmwayDamQueueEntryModel, AmwayDamProcessingData> amwayDamUpdateProcessingDataConverter;

	private List<AmwayDamValidator> validators;
	@Mock
	private AmwayDamValidator amwayDamValidator;
	@Mock
	private AmwayDamQueueEntryModel validEvent;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		validators = new ArrayList<>();
		validators.add(amwayDamValidator);

		amwayDamUpdateEventProcessor.setValidators(validators);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenCheckingApplicableForNullEvent()
	{
		AmwayDamQueueEntryModel nullEvent = null;

		amwayDamUpdateEventProcessor.isApplicable(nullEvent);
	}

	@Test
	public void shouldNotBeApplicableWhenOperationIsNotUpdateOrMetaDataUpdate()
	{
		when(validEvent.getOperation()).thenReturn(CREATE);

		boolean isApplicable = amwayDamUpdateEventProcessor.isApplicable(validEvent);

		assertFalse(isApplicable);
	}

	@Test
	public void shouldBeApplicableWhenOperationIsUpdate()
	{
		when(validEvent.getOperation()).thenReturn(UPDATE);

		boolean isApplicable = amwayDamUpdateEventProcessor.isApplicable(validEvent);

		assertTrue(isApplicable);
	}

	@Test
	public void shouldBeApplicableWhenOperationIsMetaDataUpdate()
	{
		when(validEvent.getOperation()).thenReturn(METADATA_UPDATE);

		boolean isApplicable = amwayDamUpdateEventProcessor.isApplicable(validEvent);

		assertTrue(isApplicable);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenProcessNullEvent()
	{
		AmwayDamQueueEntryModel nullEvent = null;

		amwayDamUpdateEventProcessor.process(nullEvent);
	}

	@Test
	public void shouldProcessEventWhenProcessingDataIsValid()
	{
		AmwayDamProcessingData processingData = mock(AmwayDamProcessingData.class);

		when(amwayDamUpdateProcessingDataConverter.convert(validEvent)).thenReturn(processingData);
		doNothing().when(amwayDamValidator).validate(any(AmwayDamProcessingData.class));

		amwayDamUpdateEventProcessor.process(validEvent);
	}

}
