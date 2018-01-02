/**
 *
 */
package com.amway.apac.core.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PersistenceOperation;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Calendar;

import com.amway.apac.core.model.AmwayPaymentOptionModel;


public class AmwayApacPaymentOptionInterceptor implements PrepareInterceptor, RemoveInterceptor
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.RemoveInterceptor#onRemove(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onRemove(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof AmwayPaymentOptionModel)
		{
			final ProductModel productModel = ((AmwayPaymentOptionModel) model).getProduct();
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
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof AmwayPaymentOptionModel)
		{
			final AmwayPaymentOptionModel amwayPaymentOptionModel = (AmwayPaymentOptionModel) model;
			final ProductModel productModel = amwayPaymentOptionModel.getProduct();

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
}
