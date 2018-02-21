package com.amway.core.payment.service.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.impl.DefaultPaymentInfoService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.amway.core.model.AmwayAccountModel;
import com.amway.core.payment.service.AmwayPaymentInfoService;


/**
 * Default implementation for {@link AmwayPaymentInfoService}
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class DefaultAmwayPaymentInfoService extends DefaultPaymentInfoService implements AmwayPaymentInfoService
{
	private static final int REFERENCE_NUMBER_SIZE = 4;
	private static final int MAX_PAY_REF_NUMBER = 9999;
	private static final char PAD_CHAR = '0';

	@Override
	public void generateVerificationNumberForPaymentInfo(final PaymentInfoModel paymentInfo, final AmwayAccountModel account)
	{
		ServicesUtil.validateParameterNotNull(paymentInfo, "Payment info model cannot be null");
		ServicesUtil.validateParameterNotNull(account, "Account model cannot be null");

		final CustomerModel customer = account.getPrimaryParty();
		final int paymentReferenceNumber = customer.getAmwayPaymentReferenceNumber();
		customer.setAmwayPaymentReferenceNumber(getNextPaymentReferenceNumber(paymentReferenceNumber));

		final String generatedNumber = account.getCode()
				+ StringUtils.leftPad(String.valueOf(paymentReferenceNumber), REFERENCE_NUMBER_SIZE, PAD_CHAR);
		paymentInfo.setVerificationReferenceNumber(generatedNumber);
		getModelService().saveAll(paymentInfo, customer);
	}

	private int getNextPaymentReferenceNumber(final int paymentReferenceNumber)
	{
		return paymentReferenceNumber != MAX_PAY_REF_NUMBER ? paymentReferenceNumber + 1 : NumberUtils.INTEGER_ZERO.intValue();
	}
}
