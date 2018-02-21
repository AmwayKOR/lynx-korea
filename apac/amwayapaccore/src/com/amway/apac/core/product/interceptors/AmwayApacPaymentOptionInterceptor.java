package com.amway.apac.core.product.interceptors;

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

	/**
	 * Method to update product model modified time while removing payment option
	 */
	@Override
	public void onRemove(final AmwayPaymentOptionModel model, final InterceptorContext ctx) throws InterceptorException
	{
		registerProductHierarchyForSave(model, ctx);
	}

	/**
	 * Method to update product model modified time while updating payment option
	 */
	@Override
	public void onPrepare(final AmwayPaymentOptionModel model, final InterceptorContext ctx) throws InterceptorException
	{
		registerProductHierarchyForSave(model, ctx);
	}

	/**
	 * Registers the product hierarchy of the payment option for save.
	 *
	 * @param model
	 *           payment option model
	 * @param ctx
	 *           Intercepter context
	 */
	protected void registerProductHierarchyForSave(final AmwayPaymentOptionModel model, final InterceptorContext ctx)
	{
		final ProductModel productModel = model.getProduct();
		if (null != productModel)
		{
			final Calendar calender = Calendar.getInstance();
			productModel.setModifiedtime(calender.getTime());
			if (!ctx.contains(productModel, PersistenceOperation.SAVE))
			{
				ctx.registerElementFor(productModel, PersistenceOperation.SAVE);
			}

			// if there is a base product, also register that for save
			if (productModel instanceof VariantProductModel)
			{
				final VariantProductModel variant = (VariantProductModel) productModel;
				final ProductModel baseProduct = variant.getBaseProduct();
				baseProduct.setModifiedtime(calender.getTime());
				if (!ctx.contains(baseProduct, PersistenceOperation.SAVE))
				{
					ctx.registerElementFor(baseProduct, PersistenceOperation.SAVE);
				}
			}
		}
	}
}
