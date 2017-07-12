/**
 *
 */
package com.amway.core.message.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.model.AmwayMessageModel;

import junit.framework.Assert;


@IntegrationTest
public class DefaultAmwayMessageDaoTest extends ServicelayerTransactionalTest
{

	@Resource
	private DefaultAmwayMessageDao defaultAmwayMessageDao;
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
	 * Test method for {@link com.amway.core.message.dao.impl.DefaultAmwayMessageDao#findMessage(java.lang.String)}.
	 */
	@Test
	public void testFindMessage()
	{
		final List<AmwayMessageModel> messages = defaultAmwayMessageDao.findMessage(MESSAGE_KEY);
		Assert.assertNotNull(messages);
		Assert.assertEquals(1, CollectionUtils.size(messages));
	}

}
