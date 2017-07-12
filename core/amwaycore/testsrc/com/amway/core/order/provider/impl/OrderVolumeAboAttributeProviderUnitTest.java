/**
 *
 */
package com.amway.core.order.provider.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayAccountModel;


@UnitTest
public class OrderVolumeAboAttributeProviderUnitTest
{
	@InjectMocks
	private final OrderVolumeAboAttributeProvider attributeProvider = new OrderVolumeAboAttributeProvider();
	private AbstractOrderEntryModel entry1, entry2, entry3;
	private OrderModel order;
	private AmwayAccountModel account;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		account = new AmwayAccountModel();
		account.setCode("123456");
		entry1 = new AbstractOrderEntryModel();
		entry1.setVolumeAbo("123456");
		entry2 = new AbstractOrderEntryModel();
		entry2.setVolumeAbo("123456");
		entry3 = new AbstractOrderEntryModel();
		entry3.setVolumeAbo("654321");
		order = new OrderModel();
		order.setEntries(Arrays.asList(entry1, entry2));
		order.setAccount(account);
	}


	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.OrderVolumeAboAttributeProvider#get(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	@Test
	public void testGet()
	{
		Assert.assertEquals("123456", attributeProvider.get(order));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.OrderVolumeAboAttributeProvider#get(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	@Test
	public void testGetForDiffVolumeABO()
	{
		order.setEntries(Arrays.asList(entry1, entry2, entry3));
		Assert.assertNull(attributeProvider.get(order));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.OrderVolumeAboAttributeProvider#set(de.hybris.platform.core.model.order.AbstractOrderModel, java.lang.String)}
	 * .
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void set()
	{
		attributeProvider.set(order, "test");
	}

}
