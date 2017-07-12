/**
 *
 */
package com.amway.core.order.provider.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayDimensionDescriptorModel;
import com.amway.core.model.AmwayDimensionModel;
import com.amway.core.product.service.AmwayProductDimensionService;


@UnitTest
public class OrderTotalWeightAttributeProviderUnitTest
{
	@InjectMocks
	private final OrderTotalWeightAttributeProvider attributeProvider = new OrderTotalWeightAttributeProvider();
	@Mock
	private AmwayProductDimensionService productDimensionService;
	private AbstractOrderEntryModel entry1, entry2;
	private OrderModel order;
	private ProductModel product1, product2;
	private AmwayDimensionDescriptorModel weightDimension1, weightDimension2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		product1 = Mockito.mock(ProductModel.class);
		product2 = Mockito.mock(ProductModel.class);
		entry1 = new AbstractOrderEntryModel();
		entry1.setQuantity(Long.valueOf(1));
		entry1.setProduct(product1);
		entry2 = new AbstractOrderEntryModel();
		entry2.setQuantity(Long.valueOf(1));
		entry2.setProduct(product2);
		order = new OrderModel();
		order.setEntries(Arrays.asList(entry1, entry2));
		weightDimension1 = new AmwayDimensionDescriptorModel();
		weightDimension1.setValue(1);
		weightDimension2 = new AmwayDimensionDescriptorModel();
		weightDimension2.setValue(1);
		when(productDimensionService.getDimension(product1, AmwayDimensionModel.WEIGHT)).thenReturn(weightDimension1);
		when(productDimensionService.getDimension(product2, AmwayDimensionModel.WEIGHT)).thenReturn(weightDimension2);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.OrderTotalWeightAttributeProvider#get(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	@Test
	public void testGet()
	{
		final Double wieght = attributeProvider.get(order);
		Assert.assertEquals(Double.valueOf(2), wieght);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.provider.impl.OrderTotalWeightAttributeProvider#set(de.hybris.platform.core.model.order.AbstractOrderModel, java.lang.Double)}
	 * .
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testSet()
	{
		attributeProvider.set(order, Double.valueOf(1));
	}

}
