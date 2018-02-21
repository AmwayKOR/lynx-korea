/**
 *
 */
package com.amway.core.order.provider.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


@UnitTest
public class ReturnsConsolidateDateAttributeProviderUnitTest
{
	@InjectMocks
	private final ReturnsConsolidateDateAttributeProvider attributeProvider = new ReturnsConsolidateDateAttributeProvider();
	private ReturnRequestModel returnRequest1, returnRequest2;
	private ReturnEntryModel returnEntry1, returnEntry2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		returnRequest1 = new ReturnRequestModel();
		returnRequest1.setDate(new Date());

		returnRequest2 = new ReturnRequestModel();
		returnRequest2.setDate(new Date());

		returnEntry1 = new ReturnEntryModel();
		returnEntry2 = new ReturnEntryModel();
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		returnEntry1.setReachedDate(cal.getTime());
		cal.add(Calendar.DATE, 10);
		returnEntry2.setReachedDate(cal.getTime());

		returnRequest1.setReturnEntries(Arrays.asList(returnEntry1));
		returnRequest2.setReturnEntries(Arrays.asList(returnEntry2));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.ReturnsConsolidateDateAttributeProvider#get(de.hybris.platform.returns.model.ReturnRequestModel)}
	 * .
	 */
	@Test
	public void testGet()
	{
		Assert.assertEquals(returnRequest1.getDate(), attributeProvider.get(returnRequest1));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.ReturnsConsolidateDateAttributeProvider#get(de.hybris.platform.returns.model.ReturnRequestModel)}
	 * .
	 */
	@Test
	public void testGetForAfterReturnRequestDate()
	{
		Assert.assertEquals(returnEntry2.getReachedDate(), attributeProvider.get(returnRequest2));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.ReturnsConsolidateDateAttributeProvider#set(de.hybris.platform.returns.model.ReturnRequestModel, java.util.Date)}
	 * .
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testSet()
	{
		attributeProvider.set(returnRequest1, new Date());
	}

}
