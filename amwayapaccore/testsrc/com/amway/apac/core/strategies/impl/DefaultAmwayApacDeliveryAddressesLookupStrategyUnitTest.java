/**
 *
 */
package com.amway.apac.core.strategies.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.apac.core.strategies.AmwayApacDeliveryAddressesLookupStrategy;
import com.amway.core.model.AmwayAccountModel;



/**
 * @author navishsharma <br>
 *         Unit Test class for {@link DefaultAmwayApacDeliveryAddressesLookupStrategy}
 */
@UnitTest
public class DefaultAmwayApacDeliveryAddressesLookupStrategyUnitTest
{
	@InjectMocks
	private final AmwayApacDeliveryAddressesLookupStrategy amwayApacDeliveryAddressesLookupStrategy = new DefaultAmwayApacDeliveryAddressesLookupStrategy();

	@Mock
	private AmwayApacAccountService amwayApacAccountService;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDeliveryAddressForCustomerWithNull()
	{
		amwayApacDeliveryAddressesLookupStrategy.getDeliveryAddressForCustomer(null);
	}

	@Test
	public void testGetDeliveryAddressForCustomerShouldReturnRegAddress()
	{
		final CustomerModel customer = Mockito.mock(CustomerModel.class);
		final AmwayAccountModel amwayAccount = Mockito.mock(AmwayAccountModel.class);
		final AddressModel address = Mockito.mock(AddressModel.class);
		Mockito.when(amwayApacAccountService.getAmwayAccount(customer)).thenReturn(amwayAccount);
		Mockito.when(amwayAccount.getRegisteredAddress()).thenReturn(address);
		final AddressModel lookupAddress = amwayApacDeliveryAddressesLookupStrategy.getDeliveryAddressForCustomer(customer);

		Assert.assertEquals(address, lookupAddress);
	}

	@Test
	public void testGetDeliveryAddressForCustomerShouldReturnNull()
	{
		final CustomerModel customer = Mockito.mock(CustomerModel.class);
		final AmwayAccountModel amwayAccount = Mockito.mock(AmwayAccountModel.class);
		final AddressModel address = Mockito.mock(AddressModel.class);
		Mockito.when(amwayApacAccountService.getAmwayAccount(customer)).thenReturn(null);
		Mockito.when(amwayAccount.getRegisteredAddress()).thenReturn(address);
		final AddressModel lookupAddress = amwayApacDeliveryAddressesLookupStrategy.getDeliveryAddressForCustomer(customer);

		Assert.assertNull(lookupAddress);
	}

}
