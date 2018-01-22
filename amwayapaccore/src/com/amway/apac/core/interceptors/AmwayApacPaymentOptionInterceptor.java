package com.amway.apac.core.interceptors;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PersistenceOperation;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Calendar;

import com.amway.apac.core.model.AmwayPaymentOptionModel;


/**
 * Interceptor to update product modified time while removing and updating payment option model
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacPaymentOptionInterceptor
		implements PrepareInterceptor<AmwayPaymentOptionModel>, RemoveInterceptor<AmwayPaymentOptionModel>
{

	/** The Constant PAYMENT_OPTION. */
	private static final String PAYMENT_OPTION = "Payment Option Model";

	/**
	 * Method to update product model modified time while removing payment option
	 *
	 * @throws IllegalArgumentException
	 *            if model is null
	 */
	@Override
	public void onRemove(final AmwayPaymentOptionModel model, final InterceptorContext ctx) throws InterceptorException
	{
		validateParameterNotNullStandardMessage(PAYMENT_OPTION, model);

		final ProductModel productModel = model.getProduct();
		if (null != productModel)
		{
			final Calendar calendar = Calendar.getInstance();
			productModel.setModifiedtime(calendar.getTime());
			if (!(ctx.contains(productModel, PersistenceOperation.SAVE)))
			{
				ctx.registerElementFor(productModel, PersistenceOperation.SAVE);
			}
		}
	}

	/**
	 * Method to update product model modified time while updating payment option
	 *
	 * @throws IllegalArgumentException
	 *            if model is null
	 */
	@Override
	public void onPrepare(final AmwayPaymentOptionModel model, final InterceptorContext ctx) throws InterceptorException
	{
		validateParameterNotNullStandardMessage(PAYMENT_OPTION, model);

		final ProductModel productModel = model.getProduct();

		if (null != productModel)
		{
			final Calendar cal = Calendar.getInstance();
			productModel.setModifiedtime(cal.getTime());
			if (!ctx.contains(productModel, PersistenceOperation.SAVE))
			{
				ctx.registerElementFor(productModel, PersistenceOperation.SAVE);
			}

			if (productModel instanceof VariantProductModel)
			{
				final VariantProductModel variant = (VariantProductModel) productModel;
				final ProductModel baseProduct = variant.getBaseProduct();
				baseProduct.setModifiedtime(cal.getTime());
				if (!ctx.contains(variant.getBaseProduct(), PersistenceOperation.SAVE))
				{
					ctx.registerElementFor(variant.getBaseProduct(), PersistenceOperation.SAVE);
				}
			}
		}
	}
}
