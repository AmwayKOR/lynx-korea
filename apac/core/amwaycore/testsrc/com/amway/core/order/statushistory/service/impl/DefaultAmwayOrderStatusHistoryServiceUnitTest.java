/**
 *
 */
package com.amway.core.order.statushistory.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayOrderStatusHistoryEntryModel;
import com.amway.core.order.status.dao.AmwayOrderStatusHistoryEntryDao;


@UnitTest
public class DefaultAmwayOrderStatusHistoryServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayOrderStatusHistoryService historyService = new DefaultAmwayOrderStatusHistoryService();
	@Mock
	private AmwayOrderStatusHistoryEntryDao amwayOrderStatusHistoryEntryDao;
	AmwayOrderStatusHistoryEntryModel statusHistory1, statusHistory2;
	private static final String ORDER_CODE = "98765434";


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		statusHistory1 = Mockito.mock(AmwayOrderStatusHistoryEntryModel.class);
		statusHistory2 = Mockito.mock(AmwayOrderStatusHistoryEntryModel.class);
		BDDMockito.when(amwayOrderStatusHistoryEntryDao.findOrderStatusTransitions(ORDER_CODE))
				.thenReturn(Arrays.asList(statusHistory1, statusHistory2));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.statushistory.service.impl.DefaultAmwayOrderStatusHistoryService#getStatusHistory(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetStatusHistory()
	{
		final List<AmwayOrderStatusHistoryEntryModel> statusHistories = historyService.getStatusHistory(ORDER_CODE);
		Assert.assertNotNull(statusHistories);
		Assert.assertTrue(CollectionUtils.size(statusHistories) == 2);
	}

}
