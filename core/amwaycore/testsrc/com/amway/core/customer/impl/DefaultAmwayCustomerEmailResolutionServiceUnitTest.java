/**
 *
 */
package com.amway.core.customer.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.user.CustomerModel;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;



@SuppressWarnings("deprecation")
@Ignore("HE-210 - removing powermock and disabling test")
@UnitTest
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(
//{ CustomerModel.class })
public class DefaultAmwayCustomerEmailResolutionServiceUnitTest
{
	private static final String guest_customer_uid1 = "guest | guest@test.com";
	private static final String guest_customer_uid2 = "guest";
	private static final String CUSTOMER_EMAIL = "test@test.com";
	@InjectMocks
	private final DefaultAmwayCustomerEmailResolutionService defaultAmwayCustomerEmailResolutionService = new DefaultAmwayCustomerEmailResolutionService();
	private CustomerModel guestCustomer1, guestCustomer2, customer1, customer2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		guestCustomer1 = Mockito.mock(CustomerModel.class);
		given(guestCustomer1.getType()).willReturn(CustomerType.GUEST);
		given(guestCustomer1.getUid()).willReturn(guest_customer_uid1);

		guestCustomer2 = Mockito.mock(CustomerModel.class);
		given(guestCustomer2.getType()).willReturn(CustomerType.GUEST);
		given(guestCustomer2.getUid()).willReturn(guest_customer_uid2);

		customer1 = Mockito.mock(CustomerModel.class);
		given(customer1.getType()).willReturn(CustomerType.CUSTOMERTYPE_1);
		given(customer1.getCustomerEmail()).willReturn(CUSTOMER_EMAIL);

		customer2 = Mockito.mock(CustomerModel.class);
		given(customer2.getType()).willReturn(CustomerType.CUSTOMERTYPE_1);
		given(customer2.getCustomerEmail()).willReturn(null);
		//		PowerMockito.mock(String.class);
		/*
		 * final String[] stringArr = new String[] { "customer email" };
		 */
		//		Mockito.any(String[].class).thenReturn(stringArr);

		//		PowerMockito.mockStatic(Localization.class);
		//		PowerMockito.when(Localization.getLocalizedString(null, Matchers.<String[]> any())).thenReturn("");
	}

	/**
	 * Test method for
	 * {@link com.amway.core.customer.impl.DefaultAmwayCustomerEmailResolutionService#validateAndProcessEmailForCustomer(de.hybris.platform.core.model.user.CustomerModel)}
	 * .
	 */
	@Test
	public void testValidateAndProcessEmailForCustomerCustomerModel()
	{
		Assert.assertEquals(CUSTOMER_EMAIL,
				defaultAmwayCustomerEmailResolutionService.validateAndProcessEmailForCustomer(customer1));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.customer.impl.DefaultAmwayCustomerEmailResolutionService#validateAndProcessEmailForCustomer(de.hybris.platform.core.model.user.CustomerModel)}
	 * .
	 */
	//	@Test
	//	public void testValidateAndProcessEmailForCustomerCustomerModelEmailIsNull()
	//	{
	//		Assert.assertEquals(StringUtils.EMPTY,
	//				defaultAmwayCustomerEmailResolutionService.validateAndProcessEmailForCustomer(customer2));
	//	}

	/**
	 * Test method for
	 * {@link com.amway.core.customer.impl.DefaultAmwayCustomerEmailResolutionService#validateAndProcessEmailForCustomer(de.hybris.platform.core.model.user.CustomerModel)}
	 * .
	 */
	@Test
	public void testValidateAndProcessEmailForCustomerCustomerModelEGuestHavingEmailWithUid()
	{
		Assert.assertEquals(StringUtils.substringAfter(guest_customer_uid1, "|"),
				defaultAmwayCustomerEmailResolutionService.validateAndProcessEmailForCustomer(guestCustomer1));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.customer.impl.DefaultAmwayCustomerEmailResolutionService#validateAndProcessEmailForCustomer(de.hybris.platform.core.model.user.CustomerModel)}
	 * .
	 */
	@Test(expected = IllegalStateException.class)
	public void testValidateAndProcessEmailForCustomerCustomerModelEGuestHavingUid()
	{
		Assert.assertEquals(StringUtils.EMPTY,
				defaultAmwayCustomerEmailResolutionService.validateAndProcessEmailForCustomer(guestCustomer2));
	}

}
