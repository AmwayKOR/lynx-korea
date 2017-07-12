/**
 *
 */
package com.amway.core.balance.services.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.account.service.impl.MockCreateAccountBalanceService;
import com.amway.core.account.service.impl.MockGetBalanceAccountService;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.model.AmwayAccountModel;


@UnitTest
public class DefaultAmwayAccountBalanceServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayAccountBalanceService defaultAmwayAccountBalanceService = new DefaultAmwayAccountBalanceService();
	@Mock
	private BaseStoreService baseStoreService;
	private MockCreateAccountBalanceService createBalanceService;
	private MockGetBalanceAccountService getBalanceService;
	private OrderModel order;
	private PaymentTransactionEntryModel paymentTransactionEntryModel;
	private PaymentTransactionModel paymentTransactionModel;
	private CurrencyModel currency;
	private CMSSiteModel site;
	private CountryModel country;
	private BaseStoreModel store;
	private AmwayAccountModel account;

	private static final String COUNTRY_ISOCODE = "BR";
	private static final String CURRENCY_ISOCODE = "BRL";
	private static final String AFFILIATE_NUMBER = "170";
	private static final String ACCOUNT_CODE_STRING = "322112";
	private static final String ORDER_CODE = "7000000001";
	private static final String PAYMENT_TR_ENTRY_CODE = "123456";
	private static final String REQUEST_TOCKEN = "222223333333333444444444";
	private static final String CREATE_BALANCE_RETURN_MESSAGE = "Balance created successfully";
	private static final int RETURN_CODE = 1;
	private static final String AMOUNT = "200";
	private static final BigDecimal ORDER_AMOUNT = BigDecimal.valueOf(200);
	private static final String CREDIT_BALANCE_TYPE_CD = "Credit";
	private static final String MONETARY_BALANCE_TYPE_CD = "Monetary";
	private CommonResponseFieldsData commonResponseFieldsData;
	private List<AccountBalanceData> accountBalance;
	private GetBalanceResponseData getBalanceResponseData;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		createBalanceService = new MockCreateAccountBalanceService();
		defaultAmwayAccountBalanceService.setCreateBalanceService(createBalanceService);
		getBalanceService = new MockGetBalanceAccountService();
		defaultAmwayAccountBalanceService.setGetBalanceService(getBalanceService);
		// mocks related to CreditAccountBalance test method
		country = Mockito.mock(CountryModel.class);
		given(country.getIsocode()).willReturn(COUNTRY_ISOCODE);
		currency = Mockito.mock(CurrencyModel.class);
		given(currency.getIsocode()).willReturn(CURRENCY_ISOCODE);
		site = Mockito.mock(CMSSiteModel.class);
		given(site.getDefaultCountry()).willReturn(country);
		store = Mockito.mock(BaseStoreModel.class);
		given(store.getAffiliateNumber()).willReturn(AFFILIATE_NUMBER);
		account = Mockito.mock(AmwayAccountModel.class);
		given(account.getCode()).willReturn(ACCOUNT_CODE_STRING);
		order = Mockito.mock(OrderModel.class);
		given(order.getCode()).willReturn(ORDER_CODE);
		given(order.getSite()).willReturn(site);
		given(order.getStore()).willReturn(store);
		given(order.getAccount()).willReturn(account);
		given(order.getCurrency()).willReturn(currency);
		paymentTransactionModel = Mockito.mock(PaymentTransactionModel.class);
		given(paymentTransactionModel.getOrder()).willReturn(order);
		paymentTransactionEntryModel = Mockito.mock(PaymentTransactionEntryModel.class);
		given(paymentTransactionEntryModel.getCode()).willReturn(PAYMENT_TR_ENTRY_CODE);
		given(paymentTransactionEntryModel.getAmount()).willReturn(BigDecimal.valueOf(200));
		given(paymentTransactionEntryModel.getType()).willReturn(PaymentTransactionType.CAPTURE);
		given(paymentTransactionEntryModel.getPaymentTransaction()).willReturn(paymentTransactionModel);
		given(paymentTransactionEntryModel.getRequestToken()).willReturn(REQUEST_TOCKEN);
		given(paymentTransactionEntryModel.getCurrency()).willReturn(currency);
		given(paymentTransactionEntryModel.getTime()).willReturn(new Date());

		commonResponseFieldsData = Mockito.mock(CommonResponseFieldsData.class);
		given(Integer.valueOf(commonResponseFieldsData.getReturnCd())).willReturn(Integer.valueOf(RETURN_CODE));
		given(commonResponseFieldsData.getReturnMessage()).willReturn(CREATE_BALANCE_RETURN_MESSAGE);

		given(baseStoreService.getCurrentBaseStore()).willReturn(store);

		accountBalance = Mockito.mock(List.class);
		final AccountBalanceData accountBalanceData1 = Mockito.mock(AccountBalanceData.class);
		given(accountBalanceData1.getBalanceTypeCd()).willReturn(CREDIT_BALANCE_TYPE_CD);
		given(accountBalanceData1.getBalanceAmount()).willReturn(AMOUNT);
		given(accountBalanceData1.getCurrencyCd()).willReturn(CURRENCY_ISOCODE);
		given(accountBalance.get(0)).willReturn(accountBalanceData1);

		final AccountBalanceData accountBalanceData2 = Mockito.mock(AccountBalanceData.class);
		given(accountBalanceData1.getBalanceTypeCd()).willReturn(MONETARY_BALANCE_TYPE_CD);
		given(accountBalanceData1.getBalanceAmount()).willReturn(AMOUNT);
		given(accountBalanceData1.getCurrencyCd()).willReturn(CURRENCY_ISOCODE);
		given(accountBalance.get(1)).willReturn(accountBalanceData2);
		getBalanceResponseData = Mockito.mock(GetBalanceResponseData.class);
		given(getBalanceResponseData.getAccountBalance()).willReturn(accountBalance);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.balance.services.impl.DefaultAmwayAccountBalanceService#creditAccountBalance(de.hybris.platform.payment.model.PaymentTransactionEntryModel, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCreditAccountBalance()
	{
		final CommonResponseFieldsData commonResponseFieldsData = defaultAmwayAccountBalanceService
				.creditAccountBalance(paymentTransactionEntryModel, paymentTransactionEntryModel.getRequestToken(),
						paymentTransactionModel.getOrder().getCode());
		Assert.assertNotNull(commonResponseFieldsData);
		Assert.assertEquals(CREATE_BALANCE_RETURN_MESSAGE, commonResponseFieldsData.getReturnMessage());
		Assert.assertEquals(RETURN_CODE, commonResponseFieldsData.getReturnCd());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.balance.services.impl.DefaultAmwayAccountBalanceService#debitAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String)}
	 * .
	 */
	@Test
	public void testDebitAccountBalance()
	{
		final CommonResponseFieldsData commonResponseFieldsData = defaultAmwayAccountBalanceService
				.debitAccountBalance(order, ORDER_AMOUNT, paymentTransactionEntryModel.getRequestToken());
		Assert.assertNotNull(commonResponseFieldsData);
		Assert.assertEquals(CREATE_BALANCE_RETURN_MESSAGE, commonResponseFieldsData.getReturnMessage());
		Assert.assertEquals(RETURN_CODE, commonResponseFieldsData.getReturnCd());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.balance.services.impl.DefaultAmwayAccountBalanceService#getAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	@Test
	public void testGetAccountBalance()
	{
		final GetBalanceResponseData getBalanceResponseData = defaultAmwayAccountBalanceService.getAccountBalance(order);
		Assert.assertNotNull(getBalanceResponseData);
		Assert.assertEquals(2, CollectionUtils.size(getBalanceResponseData.getAccountBalance()));
	}

}
