package com.amway.integration.dam.processor.impl;

import static com.amway.integration.dam.enums.AmwayDamOperation.CREATE;
import static com.amway.integration.dam.enums.AmwayDamOperation.REMOVE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
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
 * Unit test for {@link AmwayDamRemoveEventProcessor}
 */
@UnitTest
public class AmwayDamRemoveEventProcessorTest
{
	@InjectMocks
	private AmwayDamRemoveEventProcessor amwayDamRemoveEventProcessor;

	@Mock
	private AmwayDamAssetProcessor amwayDamAssetProcessor;
	@Mock
	private Converter<AmwayDamQueueEntryModel, AmwayDamProcessingData> amwayDamRemoveProcessingDataConverter;


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

		amwayDamRemoveEventProcessor.setValidators(validators);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenCheckingApplicableForNullEvent()
	{
		AmwayDamQueueEntryModel nullEvent = null;

		amwayDamRemoveEventProcessor.isApplicable(nullEvent);
	}

	@Test
	public void shouldBeApplicableWhenOperationIsRemove()
	{
		when(validEvent.getOperation()).thenReturn(REMOVE);

		boolean isApplicable = amwayDamRemoveEventProcessor.isApplicable(validEvent);

		assertTrue(isApplicable);
	}

	@Test
	public void shouldNotBeApplicableWhenOperationIsNotRemove()
	{
		when(validEvent.getOperation()).thenReturn(CREATE);

		boolean isApplicable = amwayDamRemoveEventProcessor.isApplicable(validEvent);

		assertFalse(isApplicable);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenProcessNullEvent()
	{
		AmwayDamQueueEntryModel nullEvent = null;

		amwayDamRemoveEventProcessor.process(nullEvent);
	}

	@Test
	public void shouldProcessEventWhenProcessingDataIsValid()
	{
		AmwayDamProcessingData processingData = mock(AmwayDamProcessingData.class);

		when(amwayDamRemoveProcessingDataConverter.convert(validEvent)).thenReturn(processingData);
		doNothing().when(amwayDamValidator).validate(any(AmwayDamProcessingData.class));

		amwayDamRemoveEventProcessor.process(validEvent);
	}

}
