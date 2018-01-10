/**
 *
 */
package com.amway.facades.populators;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.util.AmwayCartHelper;
import com.amway.facades.cart.data.PaymentDetailsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


/**
 * @author mohit2496
 *
 */
public class AmwayPaymentDetailsPopulator implements Populator<AbstractOrderModel, PaymentDetailsData>
{

	private Map<String, Converter<PaymentTransactionModel, AmwayPaymentInfoData>> paymentInfoConvertersMap;

	@Override
	public void populate(final AbstractOrderModel source, final PaymentDetailsData target) throws ConversionException
	{
		validateParameterNotNull(source, "Parameter source cannot be null");
		validateParameterNotNull(target, "Parameter target cannot be null");

		final List<AmwayPaymentInfoData> posPaymentInfoDatas = AmwayCartHelper.getPaymentTransactionList(source).stream()
				.filter(paymentTransaction -> null != paymentTransaction.getInfo()).map(paymentTransaction -> paymentInfoConvertersMap
						.get(paymentTransaction.getInfo().getItemtype()).convert(paymentTransaction))
				.collect(Collectors.toList());
		target.setPaymentInfos(posPaymentInfoDatas);
		target.setBalanceAmount(AmwayCartHelper.getBalanceAmount(source));
		target.setTotalPaidAmount(AmwayCartHelper.getTotalPaidAmount(source));

	}

	public void setPaymentInfoConvertersMap(
			final Map<String, Converter<PaymentTransactionModel, AmwayPaymentInfoData>> paymentInfoConvertersMap)
	{
		this.paymentInfoConvertersMap = paymentInfoConvertersMap;
	}

}
