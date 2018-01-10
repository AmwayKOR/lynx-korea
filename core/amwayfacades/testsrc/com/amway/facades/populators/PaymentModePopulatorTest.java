package com.amway.facades.populators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.order.data.PaymentModeData;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.util.ConverterFactory;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;


@UnitTest
public class PaymentModePopulatorTest
{

	private AbstractPopulatingConverter<PaymentModeModel, PaymentModeData> paymentModeConvertor = new ConverterFactory<PaymentModeModel, PaymentModeData, PaymentModePopulator>()
			.create(PaymentModeData.class, new PaymentModePopulator<PaymentModeModel, PaymentModeData>());
	private PaymentModeModel paymentModel;
	private PaymentModeData paymentData;
	private static final String CODE = "123456";
	private static final String NAME = "Test Payment Mode";
	private static final boolean ACTIVE = true;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		paymentModel = Mockito.mock(PaymentModeModel.class);
		BDDMockito.given(paymentModel.getCode()).willReturn(CODE);
		BDDMockito.given(paymentModel.getName()).willReturn(NAME);
		BDDMockito.given(paymentModel.getActive()).willReturn(ACTIVE);
		paymentData = new PaymentModeData();
		paymentData.setCode(CODE);
		paymentData.setName(NAME);
		paymentData.setActive(ACTIVE);

	}

	@Test
	public void testPopulate()
	{
		PaymentModeData paymentModeData = paymentModeConvertor.convert(paymentModel);
		Assert.assertNotNull(paymentModeData);
		Assert.assertEquals(CODE, paymentModeData.getCode());
		Assert.assertEquals(NAME, paymentModeData.getName());
	}

}
