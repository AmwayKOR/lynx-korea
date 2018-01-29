package com.amway.apac.core.product.interceptors;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PersistenceOperation;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import java.util.Calendar;

import com.amway.apac.core.model.AmwayCategoryProductReferenceModel;


/**
 * On creating or updating {@link AmwayCategoryProductReferenceModel} does not change the modified time of
 * {@link CategoryModel}} (to which the product reference is attached). So, the changes do not get synced. This
 * implementation changes the modified time of category so that the changes made to AmwayCategoryProductReferenceModel
 * can be synced.
 *
 * @author Rajesh Dhiman
 */
public class AmwayApacCategoryReferenceInterceptor
		implements PrepareInterceptor<AmwayCategoryProductReferenceModel>, RemoveInterceptor<AmwayCategoryProductReferenceModel>
{

	/**
	 * Prepare method implementation for {@link AmwayCategoryProductReferenceModel}
	 */
	@Override
	public void onPrepare(final AmwayCategoryProductReferenceModel amwayCategoryProductReferenceModel,
			final InterceptorContext ctx) throws InterceptorException
	{
		registerCategoryForUpdate(amwayCategoryProductReferenceModel, ctx);
	}

	/**
	 * This method updates the modified time of the category.
	 *
	 * @param amwayCategoryProductReferenceModel
	 *           category product reference model
	 * @param ctx
	 *           model save context
	 */
	protected void registerCategoryForUpdate(final AmwayCategoryProductReferenceModel amwayCategoryProductReferenceModel,
			final InterceptorContext ctx)
	{
		final CategoryModel categoryModel = amwayCategoryProductReferenceModel.getSourceCategory();
		if (null != categoryModel)
		{
			categoryModel.setModifiedtime(Calendar.getInstance().getTime());
			if (!(ctx.contains(categoryModel, PersistenceOperation.SAVE)))
			{
				ctx.registerElementFor(categoryModel, PersistenceOperation.SAVE);
			}
		}
	}

	/**
	 * Remove method implementation for {@link AmwayCategoryProductReferenceModel}
	 */
	@Override
	public void onRemove(final AmwayCategoryProductReferenceModel amwayCategoryProductReferenceModel, final InterceptorContext ctx)
			throws InterceptorException
	{
		registerCategoryForUpdate(amwayCategoryProductReferenceModel, ctx);
	}

}
