package com.amway.apac.core.resource.center.interceptors;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.resourcecentre.model.media.AbstractAmwayAssetModel;


/**
 * Interceptor to set and update classification rank in asset
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacAssetInterceptor
		implements InitDefaultsInterceptor<AbstractAmwayAssetModel>, PrepareInterceptor<AbstractAmwayAssetModel>
{
	private ModelService modelService;
	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;

	/**
	 * Sets rank during asset object initialize
	 */
	@Override
	public void onInitDefaults(final AbstractAmwayAssetModel amwayAssetModel, final InterceptorContext ctx)
			throws InterceptorException
	{
		setAssetRank(amwayAssetModel);
	}


	/**
	 * Sets rank during asset object update
	 */
	@Override
	public void onPrepare(final AbstractAmwayAssetModel amwayAssetModel, final InterceptorContext ctx) throws InterceptorException
	{
		setAssetRank(amwayAssetModel);
	}

	/**
	 * Sets classification rank from classification model to asset model
	 *
	 * @param amwayAssetModel
	 * @throws InterceptorException
	 */
	private void setAssetRank(final AbstractAmwayAssetModel amwayAssetModel) throws InterceptorException
	{
		validateParameterNotNullStandardMessage("Asset model", amwayAssetModel);

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
	@Required
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
	@Required
	public void setAmwayAccountClassificationRankMapping(
			final Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping)
	{
		this.amwayAccountClassificationRankMapping = amwayAccountClassificationRankMapping;
	}
}
