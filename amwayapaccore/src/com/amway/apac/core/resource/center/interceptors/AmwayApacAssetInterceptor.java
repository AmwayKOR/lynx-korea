package com.amway.apac.core.resource.center.interceptors;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

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
	/** The Constant ASSET_MODEL. */
	private static final String ASSET_MODEL = "Asset model";

	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;

	/**
	 * Sets rank during asset object initialize.
	 *
	 * @param amwayAssetModel
	 *           the amway asset model
	 * @param ctx
	 *           the ctx
	 * @throws InterceptorException
	 *            the interceptor exception
	 */
	@Override
	public void onInitDefaults(final AbstractAmwayAssetModel amwayAssetModel, final InterceptorContext ctx)
			throws InterceptorException
	{
		setAssetRank(amwayAssetModel);
	}


	/**
	 * Sets rank during asset object update.
	 *
	 * @param amwayAssetModel
	 *           the amway asset model
	 * @param ctx
	 *           the ctx
	 * @throws InterceptorException
	 *            the interceptor exception
	 */
	@Override
	public void onPrepare(final AbstractAmwayAssetModel amwayAssetModel, final InterceptorContext ctx) throws InterceptorException
	{
		setAssetRank(amwayAssetModel);
	}

	/**
	 * Sets classification rank from classification model to asset model.
	 *
	 * @param amwayAssetModel
	 *           the new asset rank
	 * @throws InterceptorException
	 * @throws IllegalArgumentException
	 *            if amwayAssetModel is null.
	 */
	protected void setAssetRank(final AbstractAmwayAssetModel amwayAssetModel) throws InterceptorException
	{
		validateParameterNotNullStandardMessage(ASSET_MODEL, amwayAssetModel);

		if (Objects.nonNull(amwayAssetModel.getClassification()))
		{
			final Integer rank = getAmwayAccountClassificationRankMapping().get(amwayAssetModel.getClassification());
			if (null != rank)
			{
				amwayAssetModel.setRank(rank);
			}
			else
			{
				throw new InterceptorException(new StringBuilder().append("No rank mapping exist for classification enum : ")
						.append(amwayAssetModel.getClassification().toString()).toString());
			}
		}
	}

	/**
	 * Gets the amway account classification rank mapping.
	 *
	 * @return the amwayAccountClassificationRankMapping
	 */
	public Map<AccountClassificationEnum, Integer> getAmwayAccountClassificationRankMapping()
	{
		return amwayAccountClassificationRankMapping;
	}

	/**
	 * Sets the amway account classification rank mapping.
	 *
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
