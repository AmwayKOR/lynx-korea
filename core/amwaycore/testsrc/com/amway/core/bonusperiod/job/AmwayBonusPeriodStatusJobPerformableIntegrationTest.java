/**
 *
 */
package com.amway.core.bonusperiod.job;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;


@IntegrationTest
public class AmwayBonusPeriodStatusJobPerformableIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private AmwayBonusPeriodStatusJobPerformable amwayBonusPeriodStatusJobPerformable;
	@Resource
	private CronJobService cronJobService;
	private CronJobModel cronJob;

	private static final String CRONJOB_CODE = "bonusPeriodCronJob";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/bonusPeriodJob.csv", "windows-1252");
		cronJob = cronJobService.getCronJob(CRONJOB_CODE);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.bonusperiod.job.AmwayBonusPeriodStatusJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)}
	 * .
	 */
	@Test
	public void testPerformCronJobModel()
	{
		final PerformResult result = amwayBonusPeriodStatusJobPerformable.perform(cronJob);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

}
