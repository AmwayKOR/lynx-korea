package com.amway.amwayfinance.converters;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import org.springframework.util.Assert;

import com.amway.amwayfinance.order.dto.PaymentEvent;


/**
 * Converts payment event to entry model for saving in DB.
 */
public class AmwayPayEventToPayTransactionEntryPopulator implements Populator<PaymentEvent, PaymentTransactionEntryModel>
{
	private CommonI18NService commonI18NService;

	@Override
	public void populate(final PaymentEvent source, final PaymentTransactionEntryModel target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getTransactionId());
		target.setType(PaymentTransactionType.EXTERNAL_CAPTURE);
		target.setTransactionStatus(source.getStatus());
		target.setTransactionStatusDetails(source.getStatusDetails());
		target.setCurrency(getCommonI18NService().getCurrency(source.getCurrency()));
		target.setAmount(source.getAmount());
		target.setTime(source.getDate());
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}
}
