/**
 *
 */
package com.amway.core.infraavail.cronjob;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.store.BaseStoreModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.cronjob.model.AmwayInfraAvailCronJobModel;
import com.amway.core.enums.AmwayInfraAvlStatus;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityEventPublishService;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityService;
import com.amway.core.model.AmwayInfraAvailabilityModel;

import junit.framework.Assert;


@UnitTest
public class AmwayInfraAvailJobUnitTest
{
	@InjectMocks
	private final AmwayInfraAvailJob amwayInfraAvailJob = new AmwayInfraAvailJob();
	@Mock
	private AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService;
	@Mock
	private AmwayInfraAvailabilityEventPublishService amwayInfraAvailabilityEventPublishService;
	private AmwayInfraAvailCronJobModel cronjob;
	private static final String STRING_URL = "https://www.google.com/";
	private BaseStoreModel store;
	private static final String INFRAAVAILCODE = "test_code";
	private AmwayInfraAvailabilityModel infraAvailForCode;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		cronjob = new AmwayInfraAvailCronJobModel();
		cronjob.setInfraAvailCheckUrl(STRING_URL);
		cronjob.setInfraAvailCode(INFRAAVAILCODE);
		cronjob.setBaseStore(store);

		infraAvailForCode = Mockito.mock(AmwayInfraAvailabilityModel.class);
		when(infraAvailForCode.getStatus()).thenReturn(AmwayInfraAvlStatus.ACTIVE);



	}

	/**
	 * Test method for
	 * {@link com.amway.core.infraavail.cronjob.AmwayInfraAvailJob#perform(com.amway.core.cronjob.model.AmwayInfraAvailCronJobModel)}
	 * .
	 */
	@Test
	public void testPerformAmwayInfraAvailCronJobModel()
	{
		when(amwayInfraAvailabilityService.getInfraAvailForStoreAndCode(INFRAAVAILCODE, store)).thenReturn(infraAvailForCode);
		final PerformResult performResult = amwayInfraAvailJob.perform(cronjob);
		Assert.assertEquals(CronJobResult.SUCCESS, performResult.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, performResult.getStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.infraavail.cronjob.AmwayInfraAvailJob#perform(com.amway.core.cronjob.model.AmwayInfraAvailCronJobModel)}
	 * .
	 */
	@Test
	public void testPerformAmwayInfraAvailCronJobModelForInfraAvailabilityNull()
	{
		when(amwayInfraAvailabilityService.getInfraAvailForStoreAndCode(INFRAAVAILCODE, store)).thenReturn(null);
		final PerformResult performResult = amwayInfraAvailJob.perform(cronjob);
		Assert.assertEquals(CronJobResult.ERROR, performResult.getResult());
		Assert.assertEquals(CronJobStatus.FINISHED, performResult.getStatus());
	}

}
