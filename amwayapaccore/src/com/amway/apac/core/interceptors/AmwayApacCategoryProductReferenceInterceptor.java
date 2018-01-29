package com.amway.apac.core.interceptors;

import com.amway.apac.core.model.AmwayCategoryProductReferenceModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PersistenceOperation;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import java.util.Calendar;

/**
 * On creating or updating {@link AmwayCategoryProductReferenceModel} does not change the modified time of
 * {@link CategoryModel}} (to which the product reference is attached). So, the changes do not get synced. This
 * implementation changes the modified time of category so that the changes made to AmwayCategoryProductReferenceModel
 * can be synced.
 *
 */
public class AmwayApacCategoryProductReferenceInterceptor  implements PrepareInterceptor
{
    @Override
    public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
    {
        if (model instanceof AmwayCategoryProductReferenceModel)
        {
            final AmwayCategoryProductReferenceModel amwayCategoryProductReferenceModel = (AmwayCategoryProductReferenceModel) model;
            final CategoryModel categoryModel = amwayCategoryProductReferenceModel.getSourceCategory();

            if (null != categoryModel)
            {
                final Calendar calendar = Calendar.getInstance();
                categoryModel.setModifiedtime(calendar.getTime());

                if (!(ctx.contains(categoryModel, PersistenceOperation.SAVE)))
                {
                    ctx.registerElementFor(categoryModel, PersistenceOperation.SAVE);
                }
            }
        }
    }
}
