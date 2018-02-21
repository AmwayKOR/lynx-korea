package com.amway.facades.amwayaccount.populators;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import com.amway.facades.data.AmwayAccountData;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.util.ConverterFactory;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;


@UnitTest
public class AmwayAccountPopulatorTest
{

	private AbstractPopulatingConverter<AmwayAccountModel, AmwayAccountData> amwayAccountConverter = new ConverterFactory<AmwayAccountModel, AmwayAccountData, AmwayAccountPopulator>()
			.create(AmwayAccountData.class, new AmwayAccountPopulator());
	private static final String CODE = "123456";
	private static final String NAME = "abc";
	@InjectMocks
	AmwayAccountPopulator AmwayAccountPopulator = new AmwayAccountPopulator();
	AmwayAccountModel accountModel = mock(AmwayAccountModel.class);
	AmwayAccountData accountData;


	private static final String BUSINESSNATURE1 = "AMWAY BUSINESS OWNER";
	private static final String BUSINESSNATURE4 = "CLIENT";
	private static final String BUSINESSNATURE7 = "EMPLOYEE";


	@Before
	public void setUp()
	{
		accountData = new AmwayAccountData();
		accountModel.setCode(CODE);
		accountModel.setName(NAME);
		accountModel.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_4);
		given(accountModel.getCode()).willReturn(CODE);
		given(accountModel.getName()).willReturn(NAME);
	}

	@Test
	public void testConvert()
	{

		AmwayAccountData accountData = amwayAccountConverter.convert(accountModel);
		Assert.assertEquals(CODE, accountData.getCode());
		Assert.assertEquals(NAME, accountData.getName());
	}

	@Test
	public void testConvertForAmwayBusinessNature1()
	{
		given(accountModel.getBusinessNature()).willReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		AmwayAccountData accountData = amwayAccountConverter.convert(accountModel);
		Assert.assertEquals(BUSINESSNATURE1, accountData.getAccountType());
	}

	@Test
	public void testConvertForAmwayBusinessNature4()
	{
		given(accountModel.getBusinessNature()).willReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_4);
		AmwayAccountData accountData = amwayAccountConverter.convert(accountModel);
		Assert.assertEquals(BUSINESSNATURE4, accountData.getAccountType());
	}

	@Test
	public void testConvertForAmwayBusinessNature7()
	{
		given(accountModel.getBusinessNature()).willReturn(AmwayBusinessNature.AMWAYBUSINESSNATURE_7);
		AmwayAccountData accountData = amwayAccountConverter.convert(accountModel);
		Assert.assertEquals(BUSINESSNATURE7, accountData.getAccountType());
	}

	@Test(expected = NullPointerException.class)
	public void testConvertNull()
	{
		amwayAccountConverter.convert(null);
	}
}

