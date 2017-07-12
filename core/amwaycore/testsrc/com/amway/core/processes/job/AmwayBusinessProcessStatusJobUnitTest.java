/**
 *
 */
package com.amway.core.processes.job;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.cronjob.model.AmwayPublishEventCronJobModel;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityEventPublishService;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityService;
import com.amway.core.infraavail.service.impl.DefaultAmwayInfraAvailabilityService;
import com.amway.core.model.AmwayInfraAvailabilityModel;

import junit.framework.Assert;


@UnitTest
public class AmwayBusinessProcessStatusJobUnitTest
{
	@InjectMocks
	private final AmwayBusinessProcessStatusJob statusJob = new AmwayBusinessProcessStatusJob();
	@Mock
	private AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService;
	@Mock
	private AmwayInfraAvailabilityEventPublishService amwayInfraAvailabilityEventPublishService;
	private AmwayPublishEventCronJobModel cronJob;
	private AmwayInfraAvailabilityModel infraAvailabilityModel;
	private static final String INFRAAVAILABILITY_CODE = "infraAvailability_test";
	private static final String SERVICE_ID = "service_id_test";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		final DefaultAmwayInfraAvailabilityService amwayInfraAvailabilityService = Mockito
				.mock(DefaultAmwayInfraAvailabilityService.class);
		statusJob.setAmwayInfraAvailabilityService(amwayInfraAvailabilityService);
		cronJob = Mockito.spy(new AmwayPublishEventCronJobModel());
		BDDMockito.when(cronJob.getServiceId()).thenReturn(SERVICE_ID);
		infraAvailabilityModel = Mockito.spy(new AmwayInfraAvailabilityModel());
		BDDMockito.when(infraAvailabilityModel.getCode()).thenReturn(INFRAAVAILABILITY_CODE);
		BDDMockito.when(amwayInfraAvailabilityService.getInfraAvailForCode(cronJob.getServiceId()))
				.thenReturn(infraAvailabilityModel);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.processes.job.AmwayBusinessProcessStatusJob#perform(com.amway.core.cronjob.model.AmwayPublishEventCronJobModel)}
	 * .
	 */
	@Test
	public void testPerformAmwayPublishEventCronJobModel()
	{
		final PerformResult result = statusJob.perform(cronJob);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

}
