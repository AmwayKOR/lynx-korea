package com.amway.core.user.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Set;
import org.apache.log4j.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import com.amway.core.user.dao.AmwayUserDao;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultAmwayUserServiceUnitTest
{
	private final static Logger LOG = Logger.getLogger(DefaultAmwayUserServiceUnitTest.class);
	private static final String PARTY_ID = "testPartyID_99999";

	@Mock
	private UserModel userModel;
	@Mock
	private CustomerModel customer;
	@Mock
	private UserGroupModel userGroup;
	@Mock
	private UserGroupModel employeeGroup;
	@Mock
	private Set<PrincipalGroupModel> principalGroups;
	@Mock
	private AmwayUserDao amwayUserDao;

	@Spy
	@InjectMocks
	private final DefaultAmwayUserService unit = new DefaultAmwayUserService();

	private CustomerModel customer1;

	@Before
	public void setUp() throws Exception
	{
		unit.setAmwayUserDao(amwayUserDao);

		customer1 = Mockito.mock(CustomerModel.class);
		customer1.setUid(PARTY_ID);
	}

	@Test
	public void shouldGetCurrentCustomer()
	{
		//given
		doReturn(customer).when(unit).getCurrentUser();

		//when
		final CustomerModel currentCustomer = unit.getCurrentCustomer();

		//then
		assertEquals(customer, currentCustomer);
	}

	@Test
	public void testGetCustomerByPartyId()
	{
		given(amwayUserDao.getCustomerByPartyId(PARTY_ID)).willReturn(customer1);
		final CustomerModel testCustomer = unit.getCustomerByPartyId(PARTY_ID);
		Assert.assertNotNull(testCustomer);
		Assert.assertEquals(customer1.getUid(), testCustomer.getCustomerID());

	}

}