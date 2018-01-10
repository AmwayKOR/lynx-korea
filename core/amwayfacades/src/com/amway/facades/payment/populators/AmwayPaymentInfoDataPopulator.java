/**
 *
 */
package com.amway.facades.payment.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.order.data.PaymentModeData;


/**
 * @author mohit2496
 *
 */
public class AmwayPaymentInfoDataPopulator<T extends AmwayPaymentInfoData> implements Populator<PaymentTransactionModel, T>
{

	private Converter<PaymentModeModel, PaymentModeData> paymentModeConverter;

	@Override
	public void populate(final PaymentTransactionModel source, final T target) throws ConversionException
	{
		target.setAmount(Double.valueOf(source.getPlannedAmount().doubleValue()));
		target.setTransactionid(source.getCode());
		target.setRequesttoken(source.getRequestToken());
		target.setPaymentMode(paymentModeConverter.convert(source.getPaymentMode()));
	}

	public void setPaymentModeConverter(final Converter<PaymentModeModel, PaymentModeData> paymentModeConverter)
	{
		this.paymentModeConverter = paymentModeConverter;
	}

}