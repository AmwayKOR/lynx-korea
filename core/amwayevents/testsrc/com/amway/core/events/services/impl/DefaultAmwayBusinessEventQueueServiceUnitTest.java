package com.amway.core.events.services.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.events.listener.AmwayBusinessEventsProcessing;


@UnitTest
public class DefaultAmwayBusinessEventQueueServiceUnitTest
{
	public static final String TEST_JOB_NAME = "testJobName";

	@InjectMocks
	DefaultAmwayBusinessEventQueueService defaultAmwayBusinessEventQueueService = new DefaultAmwayBusinessEventQueueService();

	@Mock
	CronJobService cronJobService;

	CronJobModel cronJobModel;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		AmwayBusinessEventsProcessing processing = new AmwayBusinessEventsProcessing();
		processing.setInternalProcessingJobNames(Collections.singletonList(TEST_JOB_NAME));
		defaultAmwayBusinessEventQueueService.setProcessing(processing);
		cronJobModel = Mockito.mock(CronJobModel.class);
		given(cronJobService.getCronJob(TEST_JOB_NAME)).willReturn(cronJobModel);
		given(cronJobService.isRunning(cronJobModel)).willReturn(false);
	}

	@Test
	public void testSendPostponedEvents() {
		defaultAmwayBusinessEventQueueService.sendPostponedEvents();
		verify(cronJobService, times(1)).performCronJob(cronJobModel);
	}
}
