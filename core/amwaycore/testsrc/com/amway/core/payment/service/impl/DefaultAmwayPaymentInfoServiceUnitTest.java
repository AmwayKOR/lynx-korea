package com.amway.core.payment.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayAccountModel;


@UnitTest
public class DefaultAmwayPaymentInfoServiceUnitTest
{
	private static final int END_REF_NUMBER = 4;
	private static final int START_REF_NUMBER = 3;
	private static final String TEST_ABO_NUMBER = "44586";
	private static final int MAX_REF_NUMBER = 9999;
	private static final int DEFAULT_REF_NUMBER = 0;

	@InjectMocks
	private final DefaultAmwayPaymentInfoService paymentInfoService = new DefaultAmwayPaymentInfoService();
	@Mock
	private ModelService modelService;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGenerateVerificationNumberForPaymentInfo()
	{
		final PaymentInfoModel paymentInfo = new PaymentInfoModel();
		final AmwayAccountModel account = new AmwayAccountModel();
		final CustomerModel customer = new CustomerModel();
		customer.setAmwayPaymentReferenceNumber(START_REF_NUMBER);
		account.setPrimaryParty(customer);
		account.setCode(TEST_ABO_NUMBER);
		paymentInfoService.generateVerificationNumberForPaymentInfo(paymentInfo, account);

		Assertions.assertThat(paymentInfo.getVerificationReferenceNumber()).isEqualTo(TEST_ABO_NUMBER + "000" + START_REF_NUMBER);
		Assertions.assertThat(customer.getAmwayPaymentReferenceNumber()).isEqualTo(END_REF_NUMBER);
	}

	@Test
	public void testResetPaymentReferenceNumber()
	{
		final PaymentInfoModel paymentInfo = new PaymentInfoModel();
		final AmwayAccountModel account = new AmwayAccountModel();
		final CustomerModel customer = new CustomerModel();
		customer.setAmwayPaymentReferenceNumber(MAX_REF_NUMBER);
		account.setPrimaryParty(customer);
		account.setCode(TEST_ABO_NUMBER);
		paymentInfoService.generateVerificationNumberForPaymentInfo(paymentInfo, account);

		Assertions.assertThat(paymentInfo.getVerificationReferenceNumber()).isEqualTo(TEST_ABO_NUMBER + MAX_REF_NUMBER);
		Assertions.assertThat(customer.getAmwayPaymentReferenceNumber()).isEqualTo(DEFAULT_REF_NUMBER);
	}
}
