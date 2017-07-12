/**
 *
 */
package com.amway.core.infraavail.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.task.TaskConditionModel;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;


@IntegrationTest
public class DefaultAmwayInfraAvailabilityEventPublishServiceTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayInfraAvailabilityEventPublishService defaultAmwayInfraAvailabilityEventPublishService;
	private static final String INFRA_AVAIL_CODE = "0001";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/taskConditionTestData.csv", "windows-1252");
	}

	/**
	 * Test method for
	 * {@link com.amway.core.infraavail.service.impl.DefaultAmwayInfraAvailabilityEventPublishService#getWaitingEvents(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetWaitingEvents()
	{
		final List<TaskConditionModel> waitingEventList = defaultAmwayInfraAvailabilityEventPublishService
				.getWaitingEvents(INFRA_AVAIL_CODE);
		Assert.assertNotNull(waitingEventList);
		Assert.assertEquals(4, CollectionUtils.size(waitingEventList));
	}

}
