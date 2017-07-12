/**
 *
 */
package com.amway.core.refund.amount.strategies.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@UnitTest
public class DefaultAmwayRefundAmountCalculationStrategyUnitTest
{
	@InjectMocks
	private final DefaultAmwayRefundAmountCalculationStrategy calculationStrategy = new DefaultAmwayRefundAmountCalculationStrategy();
	private ReturnRequestModel returnRequestModel;
	private ReturnEntryModel returnEntryModel1, returnEntryModel2;
	private AbstractOrderEntryModel orderEntry1, orderEntry2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		orderEntry1 = new AbstractOrderEntryModel();
		orderEntry1.setProRatedPrice(Double.valueOf(20));
		orderEntry1.setQuantity(Long.valueOf(2));

		orderEntry2 = new AbstractOrderEntryModel();
		orderEntry2.setProRatedPrice(Double.valueOf(10));
		orderEntry2.setQuantity(Long.valueOf(2));

		returnEntryModel1 = new ReturnEntryModel();
		returnEntryModel1.setOrderEntry(orderEntry1);
		returnEntryModel1.setReceivedQuantity(Long.valueOf(2));

		returnEntryModel2 = new ReturnEntryModel();
		returnEntryModel2.setOrderEntry(orderEntry2);
		returnEntryModel2.setReceivedQuantity(Long.valueOf(1));

		returnRequestModel = Mockito.spy(new ReturnRequestModel());
		returnRequestModel.setReturnEntries(Arrays.asList(returnEntryModel1, returnEntryModel2));

	}

	/**
	 * Test method for
	 * {@link com.amway.core.refund.amount.strategies.impl.DefaultAmwayRefundAmountCalculationStrategy#calculateRefundAmount(de.hybris.platform.returns.model.ReturnRequestModel)}
	 * .
	 */
	@Test
	public void testCalculateRefundAmount()
	{
		Assert.assertEquals(Double.valueOf(25), calculationStrategy.calculateRefundAmount(returnRequestModel));
	}

}
