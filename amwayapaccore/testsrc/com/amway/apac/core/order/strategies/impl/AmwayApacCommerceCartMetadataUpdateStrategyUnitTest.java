/**
 *
 */
package com.amway.apac.core.order.strategies.impl;

import static org.mockito.Mockito.times;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.service.data.CommerceCartMetadataParameter;
import de.hybris.platform.commerceservices.util.CommerceCartMetadataParameterUtils;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.DeliveryModeService;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.apac.core.enums.OrderType;



/**
 * @author navishsharma <br>
 *         Test class for Unit test cases for {@link AmwayApacCommerceCartMetadataUpdateStrategy}
 */
@UnitTest
public class AmwayApacCommerceCartMetadataUpdateStrategyUnitTest
{
	@InjectMocks
	private AmwayApacCommerceCartMetadataUpdateStrategy amwayApacCommerceCartMetadataUpdateStrategy;

	@Mock
	private DeliveryModeService deliveryModeService;

	@Mock
	private WarehouseService warehouseService;

	@Mock
	private CartModel cart;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDoMetadataUpdateWithNoParameters()
	{
		final CommerceCartMetadataParameter parameter = CommerceCartMetadataParameterUtils.parameterBuilder().cart(cart)
				.enableHooks(true).build();
		final boolean shouldSaveCart = amwayApacCommerceCartMetadataUpdateStrategy.doMetadataUpdate(parameter);
		Assert.assertFalse(shouldSaveCart);
	}

	@Test
	public void testDoMetadataUpdateWithWarehouse()
	{
		final CommerceCartMetadataParameter parameter = CommerceCartMetadataParameterUtils.parameterBuilder().cart(cart)
				.enableHooks(true).build();
		parameter.setWarehouseCode("TEST_WAREHOUSE_CODE");
		Mockito.when(warehouseService.getWarehouseForCode("TEST_WAREHOUSE_CODE")).thenReturn(Mockito.any(WarehouseModel.class));
		final boolean shouldSaveCart = amwayApacCommerceCartMetadataUpdateStrategy.doMetadataUpdate(parameter);
		Mockito.verify(cart, times(1)).setWarehouse(Mockito.any(WarehouseModel.class));
		Assert.assertTrue(shouldSaveCart);
	}

	@Test
	public void testDoMetadataUpdateWithVolumeAmwayAccount()
	{
		final CommerceCartMetadataParameter parameter = CommerceCartMetadataParameterUtils.parameterBuilder().cart(cart)
				.enableHooks(true).build();
		parameter.setVolumeAmwayAccount("TEST_ACCOUNT");
		final boolean shouldSaveCart = amwayApacCommerceCartMetadataUpdateStrategy.doMetadataUpdate(parameter);
		Mockito.verify(cart, times(1)).setVolumeAmwayAccount((Mockito.any(String.class)));
		Assert.assertTrue(shouldSaveCart);
	}

	@Test
	public void testDoMetadataUpdateWithDeliveryAddress()
	{
		final CommerceCartMetadataParameter parameter = CommerceCartMetadataParameterUtils.parameterBuilder().cart(cart)
				.enableHooks(true).build();
		final AddressModel addressModel = Mockito.mock(AddressModel.class);
		parameter.setDeliveryAddress(addressModel);
		final boolean shouldSaveCart = amwayApacCommerceCartMetadataUpdateStrategy.doMetadataUpdate(parameter);
		Mockito.verify(cart, times(1)).setDeliveryAddress(addressModel);
		Assert.assertTrue(shouldSaveCart);
	}

	@Test
	public void testDoMetadataUpdateWithDeliveryMode()
	{
		final CommerceCartMetadataParameter parameter = CommerceCartMetadataParameterUtils.parameterBuilder().cart(cart)
				.enableHooks(true).build();
		parameter.setDeliveryMode("TEST_DELIVERY_MODE");
		Mockito.when(deliveryModeService.getDeliveryModeForCode("TEST_DELIVERY_MODE"))
				.thenReturn(Mockito.any(DeliveryModeModel.class));
		final boolean shouldSaveCart = amwayApacCommerceCartMetadataUpdateStrategy.doMetadataUpdate(parameter);
		Mockito.verify(cart, times(1)).setDeliveryMode((Mockito.any(DeliveryModeModel.class)));
		Assert.assertTrue(shouldSaveCart);
	}

	@Test
	public void testDoMetadataUpdateWithOrderType()
	{
		final CommerceCartMetadataParameter parameter = CommerceCartMetadataParameterUtils.parameterBuilder().cart(cart)
				.enableHooks(true).build();
		parameter.setOrderType(OrderType.NORMAL_ORDER);
		final boolean shouldSaveCart = amwayApacCommerceCartMetadataUpdateStrategy.doMetadataUpdate(parameter);
		Mockito.verify(cart, times(1)).setOrderType(OrderType.NORMAL_ORDER);
		Assert.assertTrue(shouldSaveCart);
	}

}
