/**
 *
 */
package com.amway.core.order.cancel.denialstrategies.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.OrderCancelState;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.ordercancel.DefaultOrderCancelDenialReason;
import de.hybris.platform.ordercancel.OrderCancelDenialReason;
import de.hybris.platform.ordercancel.impl.DefaultOrderCancelStateMappingStrategy;
import de.hybris.platform.ordercancel.model.OrderCancelConfigModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



@UnitTest
public class AmwaySentToWarehouseDenialStrategyUnitTest
{
	@InjectMocks
	private final AmwaySentToWarehouseDenialStrategy amwaySentToWarehouseDenialStrategy = new AmwaySentToWarehouseDenialStrategy();
	@Mock
	private DefaultOrderCancelStateMappingStrategy cancelStrategy;
	@Mock
	private DefaultOrderCancelDenialReason denialReason;
	@Mock
	private UserService userService;
	private UserModel user;
	private EmployeeModel employee;
	@Mock
	private OrderCancelConfigModel configuration;
	@Mock
	private OrderModel order;
	private boolean partialCancel;
	private boolean partialEntryCancel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		amwaySentToWarehouseDenialStrategy.setStateMappingStrategy(cancelStrategy);
		amwaySentToWarehouseDenialStrategy.setStrategyInvolvedStates(Arrays.asList(OrderCancelState.SENTTOWAREHOUSE));
		amwaySentToWarehouseDenialStrategy.setReason(denialReason);
		when(Integer.valueOf(denialReason.getCode())).thenReturn(Integer.valueOf(4));
		when(denialReason.getDescription())
				.thenReturn("Order cannot be cancelled because current warehouse configuration does not allow it");

		user = Mockito.mock(UserModel.class);
		employee = Mockito.mock(EmployeeModel.class);
		when(userService.getAdminUser()).thenReturn(employee);
		partialCancel = true;
		partialEntryCancel = true;

		when(cancelStrategy.getOrderCancelState(order)).thenReturn(OrderCancelState.SENTTOWAREHOUSE);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.cancel.denialstrategies.impl.AmwaySentToWarehouseDenialStrategy#getCancelDenialReason(de.hybris.platform.ordercancel.model.OrderCancelConfigModel, de.hybris.platform.core.model.order.OrderModel, de.hybris.platform.core.model.security.PrincipalModel, boolean, boolean)}
	 * .
	 */
	@Test
	public void testGetCancelDenialReasonForAdminUser()
	{
		Assert.assertNull(amwaySentToWarehouseDenialStrategy
				.getCancelDenialReason(configuration, order, employee, partialCancel, partialEntryCancel));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.cancel.denialstrategies.impl.AmwaySentToWarehouseDenialStrategy#getCancelDenialReason(de.hybris.platform.ordercancel.model.OrderCancelConfigModel, de.hybris.platform.core.model.order.OrderModel, de.hybris.platform.core.model.security.PrincipalModel, boolean, boolean)}
	 * .
	 */
	@Test
	public void testGetCancelDenialReasonForOtherUserAndstateMappingStrategyIsNull()
	{
		amwaySentToWarehouseDenialStrategy.setStateMappingStrategy(null);
		Assert.assertNull(amwaySentToWarehouseDenialStrategy
				.getCancelDenialReason(configuration, order, user, partialCancel, partialEntryCancel));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.cancel.denialstrategies.impl.AmwaySentToWarehouseDenialStrategy#getCancelDenialReason(de.hybris.platform.ordercancel.model.OrderCancelConfigModel, de.hybris.platform.core.model.order.OrderModel, de.hybris.platform.core.model.security.PrincipalModel, boolean, boolean)}
	 * .
	 */
	@Test
	public void testGetCancelDenialReasonForOtherUserAndstateMappingStrategyAllowCancel()
	{
		when(Boolean.valueOf(configuration.isCancelAfterWarehouseAllowed())).thenReturn(Boolean.TRUE);
		amwaySentToWarehouseDenialStrategy.setStateMappingStrategy(cancelStrategy);
		Assert.assertNull(amwaySentToWarehouseDenialStrategy
				.getCancelDenialReason(configuration, order, user, partialCancel, partialEntryCancel));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.cancel.denialstrategies.impl.AmwaySentToWarehouseDenialStrategy#getCancelDenialReason(de.hybris.platform.ordercancel.model.OrderCancelConfigModel, de.hybris.platform.core.model.order.OrderModel, de.hybris.platform.core.model.security.PrincipalModel, boolean, boolean)}
	 * .
	 */
	@Test
	public void testGetCancelDenialReasonForOtherUserAndstateMappingStrategyNotAllowCancel()
	{
		when(Boolean.valueOf(configuration.isCancelAfterWarehouseAllowed())).thenReturn(Boolean.FALSE);
		amwaySentToWarehouseDenialStrategy.setStateMappingStrategy(cancelStrategy);
		final OrderCancelDenialReason reason = amwaySentToWarehouseDenialStrategy
				.getCancelDenialReason(configuration, order, user, partialCancel, partialEntryCancel);
		Assert.assertNotNull(reason);
	}

}
