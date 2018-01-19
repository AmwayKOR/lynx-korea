/**
 *
 */
package com.amway.apac.core.job;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 */
@IntegrationTest
public class ExpiredBackorderJobPerformableTest extends ServicelayerTransactionalTest
{

	@Resource
	private ExpiredBackorderJobPerformable expiredBackorderJobPerformable;
	@Resource
	private CronJobService cronJobService;
	private CronJobModel expiredBackorderJob;


	private static final String CRONJOB_CODE = "expiredBackorderJob";



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/AmwayBackOrderTestData.csv", "windows-1252");

		expiredBackorderJob = cronJobService.getCronJob(CRONJOB_CODE);

	}

	@Test
	public void testPerformCronJobModelMalasia()
	{
		final PerformResult result = expiredBackorderJobPerformable.perform(expiredBackorderJob);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}



}
