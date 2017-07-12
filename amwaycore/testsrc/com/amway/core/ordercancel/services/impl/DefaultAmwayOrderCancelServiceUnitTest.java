/**
 *
 */
package com.amway.core.ordercancel.services.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordercancel.OrderCancelRecordsHandler;
import de.hybris.platform.ordercancel.model.OrderCancelRecordEntryModel;
import de.hybris.platform.ordercancel.model.OrderCancelRecordModel;
import de.hybris.platform.orderhistory.model.OrderHistoryEntryModel;
import de.hybris.platform.ordermodify.model.OrderModificationRecordEntryModel;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@UnitTest
public class DefaultAmwayOrderCancelServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayOrderCancelService cancelService = new DefaultAmwayOrderCancelService();
	@Mock
	private OrderCancelRecordsHandler orderCancelRecordsHandler;
	private OrderModel order;
	private OrderCancelRecordModel cancelRecordModel;
	private OrderModificationRecordEntryModel cancelRecordEntryModel;
	private OrderHistoryEntryModel historyEntryModel;
	private OrderCancelRecordEntryModel recordEntryModel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		order = Mockito.mock(OrderModel.class);
		cancelRecordModel = Mockito.mock(OrderCancelRecordModel.class);
		cancelRecordEntryModel = Mockito.mock(OrderModificationRecordEntryModel.class);
		historyEntryModel = Mockito.mock(OrderHistoryEntryModel.class);
		recordEntryModel = Mockito.mock(OrderCancelRecordEntryModel.class);

		when(cancelRecordModel.getModificationRecordEntries()).thenReturn(Arrays.asList(cancelRecordEntryModel, recordEntryModel));
		when(recordEntryModel.getOriginalVersion()).thenReturn(historyEntryModel);
		when(historyEntryModel.getPreviousOrderVersion()).thenReturn(order);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.ordercancel.services.impl.DefaultAmwayOrderCancelService#getOriginalSnapshopt(de.hybris.platform.core.model.order.OrderModel)}
	 * .
	 */
	@Test
	public void testGetOriginalSnapshopt()
	{
		when(orderCancelRecordsHandler.getCancelRecord(order)).thenReturn(cancelRecordModel);
		Assert.assertEquals(order, cancelService.getOriginalSnapshopt(order));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.ordercancel.services.impl.DefaultAmwayOrderCancelService#getOriginalSnapshopt(de.hybris.platform.core.model.order.OrderModel)}
	 * .
	 */
	@Test
	public void testGetOriginalSnapshoptNullCancelRecord()
	{
		when(orderCancelRecordsHandler.getCancelRecord(order)).thenReturn(null);
		Assert.assertNull(cancelService.getOriginalSnapshopt(order));
	}


}
