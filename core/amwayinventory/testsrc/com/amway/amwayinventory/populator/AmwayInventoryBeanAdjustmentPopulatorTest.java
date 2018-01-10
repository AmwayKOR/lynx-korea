package com.amway.amwayinventory.populator;

import static com.amway.amwayinventory.AmwayInventoryTestConstants.BASE_ITEM_NUMBER;
import static com.amway.amwayinventory.AmwayInventoryTestConstants.PRODUCT_1;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Test;

import com.amway.amwayinventory.data.AmwayInventoryBean;
import com.amway.amwayinventory.data.AmwayInventoryDeltaAdjustmentData;


@UnitTest
public class AmwayInventoryBeanAdjustmentPopulatorTest
{
	private AmwayInventoryBeanAdjustmentPopulator amwayInventoryBeanAdjustmentPopulator = new AmwayInventoryBeanAdjustmentPopulator();

	private AmwayInventoryDeltaAdjustmentData deltaAdjustmentData = mock(AmwayInventoryDeltaAdjustmentData.class);
	private AmwayInventoryBean dataToPopulate = new AmwayInventoryBean();

	@Test
	public void shouldPopulateAvailableFromSourceAdjustmentProperty()
	{
		Integer expected = 5;
		when(deltaAdjustmentData.getAdjustment()).thenReturn(expected);

		amwayInventoryBeanAdjustmentPopulator.populate(deltaAdjustmentData, dataToPopulate);

		assertEquals(expected, dataToPopulate.getAvailable());
	}

	@Test
	public void shouldPopulateBaseItemNumberFromSourceBaseItemNumberProperty()
	{
		when(deltaAdjustmentData.getBaseItemNumber()).thenReturn(BASE_ITEM_NUMBER);

		amwayInventoryBeanAdjustmentPopulator.populate(deltaAdjustmentData, dataToPopulate);

		assertEquals(BASE_ITEM_NUMBER, dataToPopulate.getBaseItemNumber());
	}

	@Test
	public void shouldPopulateItemNumberFromSourceItemNumberProperty()
	{
		when(deltaAdjustmentData.getItemNumber()).thenReturn(PRODUCT_1);

		amwayInventoryBeanAdjustmentPopulator.populate(deltaAdjustmentData, dataToPopulate);

		assertEquals(PRODUCT_1, dataToPopulate.getItemNumber());
	}
}