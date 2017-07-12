/**
 *
 */
package com.amway.core.processes.job;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.cronjob.model.FetchProcessStatusEmailCronJobModel;
import com.amway.core.enums.OrderFailureStatusTypeEnum;
import com.amway.core.processes.services.AmwayFetchProcessStatusService;

import junit.framework.Assert;


@UnitTest
public class AmwayFetchProcessStatusEmailJobUnitTest
{
	@InjectMocks
	private final AmwayFetchProcessStatusEmailJob emailJob = new AmwayFetchProcessStatusEmailJob();
	@Mock
	private I18NService i18Service;
	@Mock
	private AmwayFetchProcessStatusService fetchProcessStatusService;
	@Mock
	private ModelService modelService;
	private FetchProcessStatusEmailCronJobModel cronJob;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		cronJob = Mockito.spy(new FetchProcessStatusEmailCronJobModel());
		cronJob.setProcessStatusValue(OrderFailureStatusTypeEnum.FAILED);
		cronJob.setReturnStatusCode("OK");
		cronJob.setLastRemoteUpdateTime(new Date());
		BDDMockito.when(i18Service.getCurrentLocale()).thenReturn(Locale.US);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.processes.job.AmwayFetchProcessStatusEmailJob#perform(com.amway.core.cronjob.model.FetchProcessStatusEmailCronJobModel)}
	 * .
	 */
	@Test
	public void testPerformFetchProcessStatusEmailCronJobModel()
	{
		BDDMockito.when(Boolean.valueOf(fetchProcessStatusService
				.sendEmailForFailedProcesses(cronJob.getProcessStatusValue().toString(), cronJob.getReturnStatusCode(),
						cronJob.getLastRemoteUpdateTime()))).thenReturn(Boolean.TRUE);
		final PerformResult result = emailJob.perform(cronJob);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.processes.job.AmwayFetchProcessStatusEmailJob#perform(com.amway.core.cronjob.model.FetchProcessStatusEmailCronJobModel)}
	 * .
	 */
	@Test
	public void testPerformFetchProcessStatusEmailCronJobModelFailed()
	{
		BDDMockito.when(Boolean.valueOf(fetchProcessStatusService
				.sendEmailForFailedProcesses(cronJob.getProcessStatusValue().toString(), cronJob.getReturnStatusCode(),
						cronJob.getLastRemoteUpdateTime()))).thenReturn(Boolean.FALSE);
		final PerformResult result = emailJob.perform(cronJob);
		Assert.assertEquals(CronJobResult.FAILURE, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

}
