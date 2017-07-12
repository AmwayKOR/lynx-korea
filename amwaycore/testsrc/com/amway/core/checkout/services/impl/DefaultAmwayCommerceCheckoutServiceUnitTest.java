/**
 *
 */
package com.amway.core.checkout.services.impl;

import com.amway.core.account.restrictions.impl.AccountOrderRestriction;
import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayCreditPaymentInfoModel;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.service.AmwayAccountCommerceService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.powermock.api.mockito.PowerMockito.when;


@SuppressWarnings("deprecation")
@Ignore("HE-210 - removing powermock and disabling test")
@UnitTest
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(
//{ AmwayAccountBalanceService.class, AmwayAccountCommerceService.class, AccountOrderRestriction.class, SessionService.class,
//		ModelService.class, PaymentService.class, ApplicationContext.class, BaseSiteService.class, Registry.class,
//		RedeployUtilities.class, AmwayCartHelper.class, PK.class })
public class DefaultAmwayCommerceCheckoutServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayCommerceCheckoutService defaultAmwayCommerceCheckoutService = new DefaultAmwayCommerceCheckoutService();
	PaymentTransactionModel transactionModel;
	PaymentTransactionEntryModel paymentTransactionEntryModel;
	PaymentTransactionModel rTransactionModel;
	PaymentTransactionEntryModel rPaymentTransactionEntryModel;
	GetBalanceResponseData response;
	GetBalanceResponseData responseWithError;
	AccountBalanceData accountBalanceData, accountBalanceData1;
	AmwayCreditPaymentInfoModel amwayCreditPaymentInfoModel;
	AmwayAccountModel accountModel;
	OrderModel order, rOrder;
	AmwayMonetaryPaymentInfoModel monetaryPaymentInfoModel;
	@Mock
	private AmwayAccountBalanceService amwayAccountBalanceService;
	@Mock
	private AmwayAccountCommerceService amwayAccountCommerceService;
	@Mock
	private AccountOrderRestriction accountOrderRestriction;
	@Mock
	private SessionService sessionService;
	@Mock
	private ModelService modelService;
	@Mock
	private PaymentService paymentService;
	@Mock
	private ApplicationContext context;
	@Mock
	private BaseSiteService baseSiteService;
	private CustomerModel customer;
	//private ArCreditPaymentInfoData arCreditPaymentInfoData;
	private CartModel cart;
	private CartModel cart1;
	private CartModel cart2;
	private PaymentInfoModel paymentInfoModel;
	private PaymentInfoModel paymentInfoModel1;
	private AddressModel paymentAddress;
	private OrderModel replenishmentOrder;
	private OrderModel replenishmentOrder1;
	private CurrencyModel currency;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		// mocking static class methods
		PowerMockito.mockStatic(Registry.class);
		PowerMockito.when(Registry.getApplicationContext()).thenReturn(context);
		when(context.getBean("sessionService")).thenReturn(sessionService);
		when(context.getBean("baseSiteService")).thenReturn(baseSiteService);
		when(context.getBean("modelService")).thenReturn(modelService);


		defaultAmwayCommerceCheckoutService.setModelService(modelService);
		defaultAmwayCommerceCheckoutService.setAmwayAccountBalanceService(amwayAccountBalanceService);
		defaultAmwayCommerceCheckoutService.setAmwayAccountCommerceService(amwayAccountCommerceService);
		defaultAmwayCommerceCheckoutService.setAccountOrderRestriction(accountOrderRestriction);
		defaultAmwayCommerceCheckoutService.setSessionService(sessionService);
		defaultAmwayCommerceCheckoutService.setPaymentService(paymentService);

		transactionModel = new PaymentTransactionModel();
		paymentTransactionEntryModel = new PaymentTransactionEntryModel();

		rTransactionModel = new PaymentTransactionModel();
		rPaymentTransactionEntryModel = new PaymentTransactionEntryModel();

		amwayCreditPaymentInfoModel = new AmwayCreditPaymentInfoModel();

		currency = new CurrencyModel();
		currency.setIsocode("BRL");
		paymentInfoModel = new AmwayMonetaryPaymentInfoModel();
		paymentAddress = new AddressModel();

		customer = new CustomerModel();
		customer.setUid("customer1");

		//arCreditPaymentInfoData = new ArCreditPaymentInfoData();
		//	arCreditPaymentInfoData.setAmount(200);
		cart = new CartModel();
		cart.setCurrency(currency);
		cart.setPaymentAddress(paymentAddress);
		cart.setPaymentInfo(paymentInfoModel);
		cart.setPaymentTransactions(Collections.singletonList(transactionModel));
		cart.setUser(customer);
		cart.setType(AmwayCartType.WEB);

		paymentInfoModel1 = new CreditCardPaymentInfoModel();
		cart1 = new CartModel();
		cart1.setPaymentAddress(paymentAddress);
		cart1.setPaymentInfo(paymentInfoModel1);
		cart1.setCurrency(currency);
		cart1.setPaymentTransactions(Collections.singletonList(transactionModel));
		cart1.setUser(customer);
		cart1.setType(AmwayCartType.WEB);

		cart2 = new CartModel();
		cart2.setCurrency(currency);
		cart2.setPaymentAddress(paymentAddress);
		cart2.setUser(customer);
		cart2.setType(AmwayCartType.WEB);
		//		cart2.setPaymentInfo(paymentInfoModel);
		//		cart2.setPaymentTransactions(Collections.singletonList(transactionModel));

		//when(AmwayCartHelper.createCancelTransction(cart)).th
		//nuttriCart = new CartModel();
		//nuttriCart.setType(AmwayCartType.WEB);

		replenishmentOrder = new OrderModel();
		replenishmentOrder.setPaymentAddress(paymentAddress);
		replenishmentOrder.setPaymentInfo(paymentInfoModel);
		replenishmentOrder.setCurrency(currency);
		replenishmentOrder.setPaymentTransactions(Collections.singletonList(transactionModel));
		replenishmentOrder.setType(AmwayCartType.REPLACEMENT);

		replenishmentOrder1 = new OrderModel();
		replenishmentOrder1.setPaymentAddress(paymentAddress);
		replenishmentOrder1.setPaymentInfo(paymentInfoModel1);
		replenishmentOrder1.setCurrency(currency);
		replenishmentOrder1.setPaymentTransactions(Collections.singletonList(transactionModel));
		replenishmentOrder1.setType(AmwayCartType.REPLACEMENT);

		accountBalanceData = new AccountBalanceData();
		accountBalanceData.setBalanceAmount("200");
		accountBalanceData.setBalanceTypeCd(AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPECREDIT);

		accountBalanceData1 = new AccountBalanceData();
		accountBalanceData1.setBalanceAmount("200");
		accountBalanceData1.setBalanceTypeCd(AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY);

		response = new GetBalanceResponseData();
		response.setReturnCd(1);
		response.setAccountBalance(Arrays.asList(accountBalanceData, accountBalanceData1));

		responseWithError = new GetBalanceResponseData();
		responseWithError.setReturnCd(-1);


		accountModel = new AmwayAccountModel();

		//caputre and accepted
		paymentTransactionEntryModel.setType(PaymentTransactionType.CAPTURE);
		paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());

		transactionModel.setEntries(Collections.singletonList(paymentTransactionEntryModel));
		transactionModel.setInfo(amwayCreditPaymentInfoModel);

		paymentTransactionEntryModel.setPaymentTransaction(transactionModel);
		order = new OrderModel();
		order.setPaymentTransactions(Collections.singletonList(transactionModel));
		final Set<PaymentInfoModel> paymentInfos = new HashSet<>();
		paymentInfos.add(paymentInfoModel1);
		order.setPaymentInfos(paymentInfos);

		// rejected transaction
		rPaymentTransactionEntryModel.setType(PaymentTransactionType.CAPTURE);
		rPaymentTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());

		rTransactionModel.setEntries(Collections.singletonList(paymentTransactionEntryModel));
		rTransactionModel.setInfo(amwayCreditPaymentInfoModel);

		rPaymentTransactionEntryModel.setPaymentTransaction(rTransactionModel);
		rOrder = new OrderModel();
		rOrder.setPaymentTransactions(Collections.singletonList(transactionModel));
		final Set<PaymentInfoModel> paymentInfos1 = new HashSet<>();
		paymentInfos1.add(paymentInfoModel1);
		rOrder.setPaymentInfos(paymentInfos1);

		monetaryPaymentInfoModel = Mockito.spy(new AmwayMonetaryPaymentInfoModel());
		PowerMockito.when(monetaryPaymentInfoModel.getPk()).thenReturn(PK.parse("876545"));
		given(modelService.create(AmwayMonetaryPaymentInfoModel.class)).willReturn(monetaryPaymentInfoModel);
		given(modelService.create(PaymentTransactionModel.class)).willReturn(transactionModel);
		given(modelService.create(PaymentTransactionEntryModel.class)).willReturn(paymentTransactionEntryModel);
		given(modelService.create(AmwayCreditPaymentInfoModel.class)).willReturn(amwayCreditPaymentInfoModel);
		given(paymentService.refundFollowOn(transactionModel, BigDecimal.valueOf(200))).willReturn(paymentTransactionEntryModel);
		given(paymentService.refundFollowOn(rTransactionModel, BigDecimal.valueOf(200))).willReturn(null);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#createARCreditPaymentInfo(de.hybris.platform.core.model.user.CustomerModel, com.amway.facades.order.data.ArCreditPaymentInfoData, de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	@Test
	public void testCreateARCreditPaymentInfoCustomerModelCreditCardPaymentInfoCartModel()
	{
		final PaymentInfoModel arPaymentInfoModel = defaultAmwayCommerceCheckoutService
				.createARCreditPaymentInfo(customer, Double.valueOf(150).doubleValue(), cart1);
		Assert.assertEquals(monetaryPaymentInfoModel, arPaymentInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#createARCreditPaymentInfo(de.hybris.platform.core.model.user.CustomerModel, com.amway.facades.order.data.ArCreditPaymentInfoData, de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	//success
	@Test
	public void testCreateARCreditPaymentInfoCustomerModelArCreditPaymentInfoDataCartModel()
	{
		final PaymentInfoModel arPaymentInfoModel = defaultAmwayCommerceCheckoutService
				.createARCreditPaymentInfo(customer, Double.valueOf(150).doubleValue(), cart1);
		Assert.assertEquals(monetaryPaymentInfoModel, arPaymentInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#createARCreditPaymentInfo(de.hybris.platform.core.model.user.CustomerModel, com.amway.facades.order.data.ArCreditPaymentInfoData, de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	//success
	@Test
	public void testCreateARCreditPaymentInfoCustomerModelArCreditPaymentInfoDataAbstractOrderModel()
	{
		final PaymentInfoModel arPaymentInfoModel = defaultAmwayCommerceCheckoutService
				.createARCreditPaymentInfo(customer, replenishmentOrder);
		Assert.assertEquals(paymentInfoModel, arPaymentInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#authorizeAccountBalance(de.hybris.platform.core.model.order.CartModel, java.math.BigDecimal, java.lang.String, de.hybris.platform.core.model.order.payment.PaymentInfoModel)}
	 * .
	 *
	 * @throws BusinessException
	 */
	//success
	@Test
	public void testAuthorizeAccountBalanceCartModelBigDecimalStringPaymentInfoModel() throws BusinessException
	{
		given(amwayAccountBalanceService.getAccountBalance(cart1)).willReturn(response);
		final PaymentTransactionEntryModel arPaymentTransactionEntryModel = defaultAmwayCommerceCheckoutService
				.authorizeAccountBalance(cart1, BigDecimal.valueOf(150),
						AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY, paymentInfoModel);
		Assert.assertEquals(paymentTransactionEntryModel, arPaymentTransactionEntryModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#authorizeAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String, de.hybris.platform.core.model.order.payment.PaymentInfoModel)}
	 * .
	 *
	 * @throws BusinessException
	 */
	//success
	@Test
	public void testAuthorizeAccountBalanceAbstractOrderModelBigDecimalStringPaymentInfoModel() throws BusinessException
	{
		given(amwayAccountBalanceService.getAccountBalance(cart)).willReturn(response);
		final PaymentTransactionEntryModel pTransactionEntryModel = defaultAmwayCommerceCheckoutService
				.authorizeAccountBalance(cart, BigDecimal.valueOf(150),
						AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY, paymentInfoModel);
		Assert.assertEquals(paymentTransactionEntryModel, pTransactionEntryModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#authorizeAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String, de.hybris.platform.core.model.order.payment.PaymentInfoModel)}
	 * .
	 *
	 * @throws BusinessException
	 */
	//success
	@Test(expected = BusinessException.class)
	public void testAuthorizeAccountBalanceAbstractOrderModelBigDecimalStringPaymentInfoModelForInsufficientBalance()
			throws BusinessException
	{
		given(amwayAccountBalanceService.getAccountBalance(cart)).willReturn(responseWithError);
		final PaymentTransactionEntryModel pTransactionEntryModel = defaultAmwayCommerceCheckoutService
				.authorizeAccountBalance(cart, BigDecimal.valueOf(200),
						AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY, paymentInfoModel);
		Assert.assertEquals(paymentTransactionEntryModel, pTransactionEntryModel);
	}



	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#authAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String)}
	 * .
	 *
	 * @throws BusinessException
	 */
	//success
	@Test
	public void testAuthAccountBalance() throws BusinessException
	{
		given(amwayAccountBalanceService.getAccountBalance(cart)).willReturn(response);
		final PaymentTransactionEntryModel transactionEntryModel = defaultAmwayCommerceCheckoutService
				.authAccountBalance(cart, BigDecimal.valueOf(200),
						AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY);
		Assert.assertEquals(paymentTransactionEntryModel, transactionEntryModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#authAccountBalance(de.hybris.platform.core.model.order.AbstractOrderModel, java.math.BigDecimal, java.lang.String)}
	 * .
	 *
	 * @throws BusinessException
	 */
	//success
	@Test(expected = BusinessException.class)
	public void testAuthAccountBalanceForInsufficientBalance() throws BusinessException
	{
		given(amwayAccountBalanceService.getAccountBalance(cart)).willReturn(responseWithError);
		final PaymentTransactionEntryModel transactionEntryModel = defaultAmwayCommerceCheckoutService
				.authAccountBalance(cart, BigDecimal.valueOf(200),
						AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPECREDIT);
		Assert.assertEquals(paymentTransactionEntryModel, transactionEntryModel);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService#isPlaceOrderAllowed(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	//success
	@Test
	public void testIsPlaceOrderAllowed()
	{
		given(amwayAccountCommerceService.getCurrentAccount()).willReturn(accountModel);
		given(Boolean.valueOf(accountOrderRestriction.evaluate(cart, accountModel))).willReturn(Boolean.FALSE);
		final boolean isAllowed = defaultAmwayCommerceCheckoutService.isPlaceOrderAllowed(cart);
		Assert.assertTrue(isAllowed);
	}



}
