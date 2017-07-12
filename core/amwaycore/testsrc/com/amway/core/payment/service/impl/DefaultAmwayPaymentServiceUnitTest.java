/**
 *
 */
package com.amway.core.payment.service.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.model.AmwayCreditPaymentInfoModel;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;
import com.amway.core.refund.amount.strategies.AmwayRefundAmountCalculationStrategy;


@UnitTest
public class DefaultAmwayPaymentServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayPaymentService paymentService = new DefaultAmwayPaymentService();
	@Mock
	private AmwayAccountBalanceService accountBalanceService;
	@Mock
	private ModelService modelService;
	@Mock
	private AmwayRefundAmountCalculationStrategy refundAmountCalculationStrategy;
	private PaymentTransactionModel transactionModel1, transactionModel2, transactionModel3;
	private AmwayCreditPaymentInfoModel cPaymentInfo, mockCPaymentInfo;
	private AmwayMonetaryPaymentInfoModel monetaryPaymentInfo, monetaryPaymentInfo1, mockMonetaryPaymentInfo;
	//	private AmwayCreditPaymentInfoModel ccPaymentInfo;
	private PaymentTransactionEntryModel transactionEntry1, transactionEntry2, transactionEntry3, transactionEntry2Cloned;
	private OrderModel order;
	private ReturnRequestModel returnRequestModel;
	private UserModel user;
	private AddressModel paymentAddress;
	private CurrencyModel currency;

	private CartModel cart;
	final Map<String, Map<String, String>> creditCardDetails = new LinkedHashMap<>();
	final Map<String, String> paymentDetails1 = new HashMap<>();
	final Map<String, String> paymentDetails2 = new HashMap<>();
	final Set<PaymentInfoModel> paymentInfos = new HashSet<>();

	private CommonResponseFieldsData commonResponse;
	private static final String REQUEST_TOCKEN = "req-1000000";
	private static final String TRANSACTION_DETAIL_STATUS = "SUCCESFULL";
	private static final String PAYMENT_PROVIDER = "test payment provider";
	private final Double refundAmount = Double.valueOf(200);
	private final static String RMA = "10000001";
	private static final String CC_INSTALLMENTS = "installments";
	private static final String CC_CPFNUMBER = "cpfNumber";
	private static final String CC_AMOUNT = "amount";
	private static final String DEFAULT_INSTALLMENT = "1";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		order = new OrderModel();
		order.setCode("765432");
		//testCapturePaymentTransactionModelForNonAuthorized
		cPaymentInfo = new AmwayCreditPaymentInfoModel();
		transactionModel1 = new PaymentTransactionModel();
		transactionModel1.setInfo(cPaymentInfo);
		transactionEntry1 = new PaymentTransactionEntryModel();
		transactionEntry1.setType(PaymentTransactionType.CREATE_SUBSCRIPTION);
		transactionModel1.setEntries(Arrays.asList(transactionEntry1));

		//testCapturePaymentTransactionModelSufficientAccountBalance
		monetaryPaymentInfo = new AmwayMonetaryPaymentInfoModel();
		transactionModel2 = new PaymentTransactionModel();
		transactionModel2.setCode("tr-001");
		transactionModel2.setPaymentProvider(PAYMENT_PROVIDER);
		transactionModel2.setInfo(monetaryPaymentInfo);
		transactionModel2.setOrder(order);
		transactionEntry2 = new PaymentTransactionEntryModel();
		transactionEntry2.setType(PaymentTransactionType.AUTHORIZATION);
		transactionEntry2.setRequestToken(REQUEST_TOCKEN);
		transactionEntry2.setAmount(BigDecimal.valueOf(200));
		transactionEntry2.setPaymentTransaction(transactionModel2);
		transactionModel2.setEntries(Arrays.asList(transactionEntry2));

		transactionEntry2Cloned = new PaymentTransactionEntryModel();
		transactionEntry2Cloned.setType(PaymentTransactionType.AUTHORIZATION);
		transactionEntry2Cloned.setRequestToken(REQUEST_TOCKEN);
		commonResponse = new CommonResponseFieldsData();
		commonResponse.setReturnCd(1);

		when(modelService.clone(transactionEntry2)).thenReturn(transactionEntry2Cloned);

		//testRefundFollowOnReturnRequestModel
		user = new UserModel();
		paymentAddress = new AddressModel();
		currency = new CurrencyModel();
		order.setUser(user);
		order.setPaymentAddress(paymentAddress);
		order.setCurrency(currency);
		returnRequestModel = new ReturnRequestModel();
		returnRequestModel.setOrder(order);
		returnRequestModel.setRMA(RMA);
		when(refundAmountCalculationStrategy.calculateRefundAmount(returnRequestModel)).thenReturn(refundAmount);
		order.setPaymentTransactions(Arrays.asList(transactionModel2));
		monetaryPaymentInfo1 = new AmwayMonetaryPaymentInfoModel();
		when(modelService.create(AmwayMonetaryPaymentInfoModel.class)).thenReturn(monetaryPaymentInfo1);
		transactionModel3 = new PaymentTransactionModel();
		when(modelService.create(PaymentTransactionModel.class)).thenReturn(transactionModel3);
		transactionEntry3 = new PaymentTransactionEntryModel();
		when(modelService.create(PaymentTransactionEntryModel.class)).thenReturn(transactionEntry3);
		transactionEntry3.setRequestToken(AmwaycoreConstants.PaymentMode.AccountBalanceType.ACCOUNTBALANCETYPEMONETARY);

		mockCPaymentInfo = Mockito.spy(new AmwayCreditPaymentInfoModel());
		BDDMockito.given(mockCPaymentInfo.getPk()).willReturn(PK.parse("987654345"));
		mockMonetaryPaymentInfo = Mockito.spy(new AmwayMonetaryPaymentInfoModel());
		BDDMockito.given(mockMonetaryPaymentInfo.getPk()).willReturn(PK.parse("2345675676"));


		paymentInfos.add(cPaymentInfo);
		paymentInfos.add(monetaryPaymentInfo);
		cart = Mockito.spy(new CartModel());
		cart.setTotalPrice(Double.valueOf(200));

		paymentDetails1.put(CC_AMOUNT, "100");
		paymentDetails1.put(CC_CPFNUMBER, "123.123.345-98");
		paymentDetails1.put(CC_INSTALLMENTS, DEFAULT_INSTALLMENT);

		paymentDetails2.put(CC_AMOUNT, "100");
		paymentDetails2.put(CC_CPFNUMBER, "123.123.345-98");
		paymentDetails2.put(CC_INSTALLMENTS, DEFAULT_INSTALLMENT);
		creditCardDetails.put(mockCPaymentInfo.getPk().toString(), paymentDetails1);
		creditCardDetails.put(mockMonetaryPaymentInfo.getPk().toString(), paymentDetails2);
		cart.setPaymentDetails(creditCardDetails);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#capture(de.hybris.platform.payment.model.PaymentTransactionModel)}
	 * .
	 */
	@Test(expected = AdapterException.class)
	public void testCapturePaymentTransactionModelForNonAuthorized()
	{
		paymentService.capture(transactionModel1);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#capture(de.hybris.platform.payment.model.PaymentTransactionModel)}
	 * .
	 */
	@Test
	public void testCapturePaymentTransactionModelSufficientAccountBalance()
	{
		when(accountBalanceService.debitAccountBalance(transactionModel2.getOrder(), transactionEntry2.getAmount(),
				transactionEntry2.getRequestToken())).thenReturn(commonResponse);
		final PaymentTransactionEntryModel transactionEntry = paymentService.capture(transactionModel2);
		Assert.assertNotNull(transactionEntry);
		Assert.assertEquals(TRANSACTION_DETAIL_STATUS, transactionEntry.getTransactionStatusDetails());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#capture(de.hybris.platform.payment.model.PaymentTransactionModel)}
	 * .
	 */
	@Test
	public void testCapturePaymentTransactionModelInsufficientAccountBalance()
	{
		transactionEntry2.setAmount(BigDecimal.valueOf(100));
		commonResponse.setReturnCd(-1);
		when(accountBalanceService.debitAccountBalance(transactionModel2.getOrder(), transactionEntry2.getAmount(),
				transactionEntry2.getRequestToken())).thenReturn(commonResponse);
		final PaymentTransactionEntryModel transactionEntry = paymentService.capture(transactionModel2);
		Assert.assertNotNull(transactionEntry);
		Assert.assertEquals("ERROR --1", transactionEntry.getTransactionStatusDetails());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#cancel(de.hybris.platform.payment.model.PaymentTransactionEntryModel)}
	 * .
	 */
	@Test
	public void testCancelPaymentTransactionEntryModelSuccess()
	{
		commonResponse.setReturnCd(1);
		when(accountBalanceService
				.creditAccountBalance(transactionEntry2, transactionEntry2.getRequestToken(), transactionModel2.getOrder().getCode()))
				.thenReturn(commonResponse);
		final PaymentTransactionEntryModel paymentTransactionEntryModel = paymentService.cancel(transactionEntry2);
		Assert.assertNotNull(paymentTransactionEntryModel);
		Assert.assertEquals(TransactionStatus.ACCEPTED.name(), paymentTransactionEntryModel.getTransactionStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#cancel(de.hybris.platform.payment.model.PaymentTransactionEntryModel)}
	 * .
	 */
	@Test
	public void testCancelPaymentTransactionEntryModelUnSuccess()
	{
		commonResponse.setReturnCd(-1);
		when(accountBalanceService
				.creditAccountBalance(transactionEntry2, transactionEntry2.getRequestToken(), transactionModel2.getOrder().getCode()))
				.thenReturn(commonResponse);
		final PaymentTransactionEntryModel paymentTransactionEntryModel = paymentService.cancel(transactionEntry2);
		Assert.assertNotNull(paymentTransactionEntryModel);
		Assert.assertEquals(TransactionStatus.REJECTED.name(), paymentTransactionEntryModel.getTransactionStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#refundFollowOn(de.hybris.platform.payment.model.PaymentTransactionModel, java.math.BigDecimal)}
	 * .
	 */
	@Test
	public void testRefundFollowOnPaymentTransactionModelBigDecimalSuccess()
	{
		commonResponse.setReturnCd(1);
		when(accountBalanceService.creditAccountBalance(transactionEntry2Cloned, transactionEntry2.getRequestToken(),
				transactionModel2.getOrder().getCode())).thenReturn(commonResponse);
		transactionEntry2.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		final PaymentTransactionEntryModel paymentTransactionEntryModel = paymentService
				.refundFollowOn(transactionModel2, transactionEntry2.getAmount());
		Assert.assertNotNull(paymentTransactionEntryModel);
		Assert.assertEquals(TransactionStatus.ACCEPTED.name(), paymentTransactionEntryModel.getTransactionStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#refundFollowOn(de.hybris.platform.payment.model.PaymentTransactionModel, java.math.BigDecimal)}
	 * .
	 */
	@Test
	public void testRefundFollowOnPaymentTransactionModelBigDecimalUnSuccess()
	{
		commonResponse.setReturnCd(-1);
		when(accountBalanceService.creditAccountBalance(transactionEntry2Cloned, transactionEntry2.getRequestToken(),
				transactionModel2.getOrder().getCode())).thenReturn(commonResponse);
		transactionEntry2.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		final PaymentTransactionEntryModel paymentTransactionEntryModel = paymentService
				.refundFollowOn(transactionModel2, transactionEntry2.getAmount());
		Assert.assertNotNull(paymentTransactionEntryModel);
		Assert.assertEquals(TransactionStatus.REJECTED.name(), paymentTransactionEntryModel.getTransactionStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#refundFollowOn(de.hybris.platform.returns.model.ReturnRequestModel)}
	 * .
	 */
	@Test
	public void testRefundFollowOnReturnRequestModelSuccess()
	{
		commonResponse.setReturnCd(1);
		when(accountBalanceService
				.creditAccountBalance(transactionEntry3, transactionEntry3.getRequestToken(), returnRequestModel.getRMA()))
				.thenReturn(commonResponse);
		final PaymentTransactionEntryModel refundTransactionEntryModel = paymentService.refundFollowOn(returnRequestModel);
		Assert.assertNotNull(refundTransactionEntryModel);
		Assert.assertEquals(TransactionStatus.ACCEPTED.name(), refundTransactionEntryModel.getTransactionStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#refundFollowOn(de.hybris.platform.returns.model.ReturnRequestModel)}
	 * .
	 */
	@Test
	public void testRefundFollowOnReturnRequestModelUnSuccess()
	{
		commonResponse.setReturnCd(-1);
		when(accountBalanceService
				.creditAccountBalance(transactionEntry3, transactionEntry3.getRequestToken(), returnRequestModel.getRMA()))
				.thenReturn(commonResponse);
		final PaymentTransactionEntryModel refundTransactionEntryModel = paymentService.refundFollowOn(returnRequestModel);
		Assert.assertNotNull(refundTransactionEntryModel);
		Assert.assertEquals(TransactionStatus.REJECTED.name(), refundTransactionEntryModel.getTransactionStatus());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#adjustPaymentAmount(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	@Test
	public void testAdjustPaymentAmountForEmptyPaymentInfos()
	{
		cart.setPaymentInfos(new HashSet<PaymentInfoModel>());
		Assert.assertFalse(paymentService.adjustPaymentAmount(cart));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentService#adjustPaymentAmount(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	//	@Test
	//	public void testAdjustPaymentAmount()
	//	{
	//		cart.setPaymentInfos(paymentInfos);
	/*
	 * BDDMockito.when(cart.getPaymentDetails()).thenReturn(creditCardDetails);
	 * BDDMockito.when(paymentService.getPaymentInfoList(cart)).thenReturn(paymentInfos);
	 */
	//		Assert.assertTrue(paymentService.adjustPaymentAmount(cart));
	//	}

}
