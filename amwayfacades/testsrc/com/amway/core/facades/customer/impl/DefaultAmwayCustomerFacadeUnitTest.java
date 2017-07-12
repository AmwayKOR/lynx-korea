package com.amway.core.facades.customer.impl;

import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.facades.data.AmwayAccountData;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import junit.framework.Assert;


@UnitTest
public class DefaultAmwayCustomerFacadeUnitTest
{

	@Mock
	private AmwayAccountCommerceService amwayAccountCommerceService;
	@Mock
	private Converter<AmwayAccountModel, AmwayAccountData> amwayAccountConverter;
	@InjectMocks
	private DefaultAmwayCustomerFacade defaultAmwayCustomerFacade = new DefaultAmwayCustomerFacade();
	private AmwayAccountModel currentAccountModel;
	private AmwayAccountData currentAccountData;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		currentAccountModel = new AmwayAccountModel();
		currentAccountData = new AmwayAccountData();
		currentAccountModel.setCode("123456");
		currentAccountModel.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		currentAccountModel.setName("Test Account");
	}

	@Test
	public void testCurrentAccount()
	{
		given(amwayAccountCommerceService.getCurrentAccount()).willReturn(currentAccountModel);
		given(amwayAccountConverter.convert(currentAccountModel)).willReturn(currentAccountData);
		AmwayAccountData accountData = defaultAmwayCustomerFacade.getCurrentAccount();
		Assert.assertNotNull(accountData);
		//	assertEquals("Account Type should be same","AMWAY BUSINESS OWNER", accountData.getAccountType());
		Assert.assertEquals(accountData.getCode(), currentAccountData.getCode());
		Assert.assertEquals(accountData.getName(), currentAccountData.getName());
	}

}
