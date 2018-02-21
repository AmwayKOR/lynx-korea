package com.amway.facades.populators;

//import com.hybris.oms.domain.order.Order;
//import com.hybris.oms.domain.order.PaymentInfo;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
//import de.hybris.platform.integration.oms.order.populators.OmsOrderPopulator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayOmsOrderPopulator //extends OmsOrderPopulator
{
	private static final String AUTH_URL = "Test";

	public AmwayOmsOrderPopulator()
	{
	}

	//@Override
	public void populate(OrderModel source, Object target) throws ConversionException
	{
		//super.populate(source, target);

		//if(source.getPaymentInfo() instanceof CreditCardPaymentInfoModel && source.getPaymentTransactions() != null && !source.getPaymentTransactions().isEmpty()) {
		//	((PaymentInfo)target.getPaymentInfos().get(0)).setAuthUrl(AUTH_URL);
		//}
	}

}
