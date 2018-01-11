/**
 *
 */
package com.amway.apac.core.resource.center.interceptors;

import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Map;
import java.util.Objects;

import org.assertj.core.util.Preconditions;

import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.resourcecentre.model.media.AbstractAmwayAssetModel;


/**
 * @author Ashish Sabal
 *
 */
public class AmwayApacAssetInterceptor
		implements InitDefaultsInterceptor<AbstractAmwayAssetModel>, PrepareInterceptor<AbstractAmwayAssetModel>
{
	private ModelService modelService;
	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor#onInitDefaults(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onInitDefaults(final AbstractAmwayAssetModel amwayAssetModel, final InterceptorContext ctx)
			throws InterceptorException
	{
		setAssetRank(amwayAssetModel);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final AbstractAmwayAssetModel amwayAssetModel, final InterceptorContext ctx) throws InterceptorException
	{
		setAssetRank(amwayAssetModel);
	}

	/**
	 * @param amwayAssetModel
	 * @throws InterceptorException
	 */
	private void setAssetRank(final AbstractAmwayAssetModel amwayAssetModel) throws InterceptorException
	{
		Preconditions.checkArgument(Objects.nonNull(amwayAssetModel), "Asset model cannot be NULL.");

		if (Objects.nonNull(amwayAssetModel.getClassification()))
		{
			final Integer rank = getAmwayAccountClassificationRankMapping().get(amwayAssetModel.getClassification());
			if (null != rank)
			{
				amwayAssetModel.setRank(rank);
				getModelService().save(amwayAssetModel);
			}
			else
			{
				throw new InterceptorException(new StringBuilder().append("No rank mapping exist for classification enum : ")
						.append(amwayAssetModel.getClassification().toString()).toString());
			}
		}
		else
		{
			throw new InterceptorException("Asset classification enum is NULL.");
		}
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the amwayAccountClassificationRankMapping
	 */
	public Map<AccountClassificationEnum, Integer> getAmwayAccountClassificationRankMapping()
	{
		return amwayAccountClassificationRankMapping;
	}

	/**
	 * @param amwayAccountClassificationRankMapping
	 *           the amwayAccountClassificationRankMapping to set
	 */
	public void setAmwayAccountClassificationRankMapping(
			final Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping)
	{
		this.amwayAccountClassificationRankMapping = amwayAccountClassificationRankMapping;
	}
}
