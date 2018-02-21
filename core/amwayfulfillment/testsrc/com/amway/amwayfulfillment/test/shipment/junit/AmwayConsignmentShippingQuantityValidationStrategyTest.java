package com.amway.amwayfulfillment.test.shipment.junit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.amway.amwayfulfillment.exceptions.shipment.AmwayShipmentConfirmationException;
import com.amway.amwayfulfillment.services.shipment.impl.AmwayConsignmentShippingQuantityValidationStrategy;

import org.junit.Assert;



/**
 * Junit test for {@link AmwayConsignmentShippingQuantityValidationStrategy}
 */
@UnitTest
public class AmwayConsignmentShippingQuantityValidationStrategyTest
{
	private AbstractOrderEntryModel orderEntry;
	private ConsignmentEntryModel consignmentEntryModel;
	private ConsignmentModel consignmentModel;

	@InjectMocks
	private AmwayConsignmentShippingQuantityValidationStrategy validationStrategy = new AmwayConsignmentShippingQuantityValidationStrategy();

	@Before
	public void setUp() throws AmwayShipmentConfirmationException
	{
		MockitoAnnotations.initMocks(this);
		orderEntry = mock(AbstractOrderEntryModel.class);
		consignmentEntryModel = mock(ConsignmentEntryModel.class);
		consignmentModel = mock(ConsignmentModel.class);
	}

	@Test
	public void testIsValidWhenSimilarQuantity()
	{
		long q = 10L;

		when(consignmentEntryModel.getShippedQuantity()).thenReturn(Long.valueOf(q));
		when(consignmentEntryModel.getConsignment()).thenReturn(consignmentModel);

		when(consignmentModel.getStatus()).thenReturn(ConsignmentStatus.SHIPPED);

		when(orderEntry.getConsignmentEntries()).thenReturn(Collections.singleton(consignmentEntryModel));
		when(orderEntry.getQuantity()).thenReturn(Long.valueOf(q));

		boolean res = validationStrategy.validate(orderEntry);

		Assert.assertTrue(res);
	}

	@Test
	public void testIsNotValidWhenDifferentQuantity()
	{
		when(consignmentEntryModel.getShippedQuantity()).thenReturn(Long.valueOf(10L));
		when(consignmentEntryModel.getConsignment()).thenReturn(consignmentModel);

		when(consignmentModel.getStatus()).thenReturn(ConsignmentStatus.SHIPPED);

		when(orderEntry.getConsignmentEntries()).thenReturn(Collections.singleton(consignmentEntryModel));
		when(orderEntry.getQuantity()).thenReturn(Long.valueOf(15L));

		boolean res = validationStrategy.validate(orderEntry);

		Assert.assertFalse(res);
	}

}
