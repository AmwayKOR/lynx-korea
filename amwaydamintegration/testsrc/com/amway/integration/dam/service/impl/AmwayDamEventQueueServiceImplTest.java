package com.amway.integration.dam.service.impl;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.dao.AmwayDamQueueDao;
import com.amway.integration.dam.data.AmwayDamEventData;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;


/**
 * Unit Test for {@link AmwayDamEventQueueServiceImplTest}
 */
@UnitTest
public class AmwayDamEventQueueServiceImplTest
{
	@InjectMocks
	private AmwayDamEventQueueServiceImpl amwayDamEventQueueService;

	@Mock
	private ModelService modelService;
	@Mock
	private AmwayDamQueueDao amwayDamQueueDao;
	@Mock
	private Predicate<AmwayDamEventData> amwayDamEventDataPredicate;
	@Mock
	private Converter<AmwayDamEventData, AmwayDamQueueEntryModel> amwayDamQueueEntryReverseConverter;

	@Mock
	private AmwayDamQueueEntryModel queueEntryModel;

	private final AmwayDamEventData eventData = new AmwayDamEventData();
	private final List<AmwayDamEventData> events = singletonList(eventData);
	private final List<AmwayDamQueueEntryModel> queueEntries = singletonList(queueEntryModel);

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		when(amwayDamQueueDao.findEvents(anyInt())).thenReturn(queueEntries);
	}

	@Test
	public void shouldNotCreateAndPutToQueueWhenEventListIsEmpty()
	{
		amwayDamEventQueueService.registerEvents(emptyList());

		verifyZeroInteractions(modelService);
		verifyZeroInteractions(amwayDamQueueEntryReverseConverter);
	}

	@Test
	public void shouldNotCreateAndPutToQueueWhenAllEventsInvalid()
	{
		when(amwayDamEventDataPredicate.test(eventData)).thenReturn(false);

		amwayDamEventQueueService.registerEvents(events);

		verifyZeroInteractions(modelService);
		verifyZeroInteractions(amwayDamQueueEntryReverseConverter);
	}

	@Test
	public void shouldCreateAndPutToQueueWhenSomeEventAreValid()
	{
		when(amwayDamEventDataPredicate.test(eventData)).thenReturn(true);

		amwayDamEventQueueService.registerEvents(events);

		verify(modelService).create(AmwayDamQueueEntryModel.class);
		verify(amwayDamQueueEntryReverseConverter).convert(any(), any());
		verify(modelService).save(any());
	}

	@Test
	public void shouldReturnEventsWhenLimitIsNull()
	{
		List<AmwayDamQueueEntryModel> result = amwayDamEventQueueService.getEvents(null);

		assertEquals(queueEntries, result);
	}

	@Test
	public void shouldReturnEventsWhenLimitSpecified()
	{
		Integer limit = 1;

		List<AmwayDamQueueEntryModel> result = amwayDamEventQueueService.getEvents(limit);

		assertEquals(queueEntries, result);
	}
}