/**
 *
 */
package com.amway.core.message.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;


@IntegrationTest
public class DefaultAmwayMessageServiceTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayMessageService defaultAmwayMessageService;
	private static final String MESSAGE_KEY = "account.confirmation.address.added";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/messageTestData.csv", "windows-1252");
	}

	/**
	 * Test method for
	 * {@link com.amway.core.message.service.impl.DefaultAmwayMessageService#findMessage(java.lang.String)}.
	 */
	@Test
	public void testFindMessage()
	{
		final String message = defaultAmwayMessageService.findMessage(MESSAGE_KEY);
		Assert.assertNotNull(message);
		Assert.assertEquals("Your address was created.", message);
	}

}
