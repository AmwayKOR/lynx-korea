/**
 *
 */
package com.amway.apac.core.job;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.model.AmwayApacStoreSpecificCronJobModel;


/**
 *
 */
@IntegrationTest
public class ReleaseBackOrderJobPerformableTest extends ServicelayerTransactionalTest
{

	@Resource
	private ReleaseBackOrderJobPerformable releaseBackOrderJobPerformable;
	@Resource
	private CronJobService cronJobService;
	private AmwayApacStoreSpecificCronJobModel cronJobmalasia;
	private AmwayApacStoreSpecificCronJobModel cronJobbrunei;
	private AmwayApacStoreSpecificCronJobModel cronJobphilippines;
	private AmwayApacStoreSpecificCronJobModel cronJobsingapore;

	private static final String CRONJOB_CODE_MALASIA = "releaseBackorderJobMalaysia";
	private static final String CRONJOB_CODE_BRUNEI = "releaseBackorderJobBrunei";
	private static final String CRONJOB_CODE_PHILIPPINES = "releaseBackorderJobPhilippines";
	private static final String CRONJOB_CODE_SINGAPORE = "releaseBackorderJobSingapore";


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/AmwayBackOrderTestData.csv", "windows-1252");

		cronJobmalasia = (AmwayApacStoreSpecificCronJobModel) cronJobService.getCronJob(CRONJOB_CODE_MALASIA);
		cronJobbrunei = (AmwayApacStoreSpecificCronJobModel) cronJobService.getCronJob(CRONJOB_CODE_BRUNEI);
		cronJobphilippines = (AmwayApacStoreSpecificCronJobModel) cronJobService.getCronJob(CRONJOB_CODE_PHILIPPINES);
		cronJobsingapore = (AmwayApacStoreSpecificCronJobModel) cronJobService.getCronJob(CRONJOB_CODE_SINGAPORE);
	}

	@Test
	public void testPerformCronJobModelMalasia()
	{
		final PerformResult result = releaseBackOrderJobPerformable.perform(cronJobmalasia);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

	@Test
	public void testPerformCronJobModelSingapore()
	{
		final PerformResult result = releaseBackOrderJobPerformable.perform(cronJobsingapore);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

	@Test
	public void testPerformCronJobModelPhilippines()
	{
		final PerformResult result = releaseBackOrderJobPerformable.perform(cronJobphilippines);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

	@Test
	public void testPerformCronJobModelBrunei()
	{
		final PerformResult result = releaseBackOrderJobPerformable.perform(cronJobbrunei);
		Assert.assertEquals(CronJobResult.SUCCESS, result.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, result.getStatus());
	}

}
