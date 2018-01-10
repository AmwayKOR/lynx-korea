package com.amway.integration.dam.strategy.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.INTERNAL_DELIMITER;
import static com.amway.integration.dam.constants.AmwayDamConstants.PATH_DELIMITER;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;

import java.util.StringJoiner;
import java.util.UUID;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.strategy.AmwayDamAssetItemCodeStrategy;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of generating code for {@link com.amway.integration.dam.model.AmwayDamAssetItemModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetItemCodeStrategyImpl implements AmwayDamAssetItemCodeStrategy
{
	private static final String RENDITION_PARAM = "rendition";
	private static final String TYPE_PARAM = "type";
	private static final String ASSET_DATA_PARAM = "assetData";
	private static final String PATH_PARAM = "path";

	@Override
	public String generateCode(AmwayDamRenditionData rendition)
	{
		validateParameterNotNullStandardMessage(RENDITION_PARAM, rendition);
		AmwayDamAssetData assetData = rendition.getAsset();
		validateParameterNotNullStandardMessage(TYPE_PARAM, rendition.getType());
		validateParameterNotNullStandardMessage(ASSET_DATA_PARAM, assetData);
		validateParameterNotNullStandardMessage(PATH_PARAM, assetData.getPath());

		StringJoiner codeJoiner = new StringJoiner(INTERNAL_DELIMITER);
		codeJoiner.add(rendition.getType().toString());

		if (rendition.getWidth() != null && rendition.getHeight() != null)
		{
			//@formatter:off
			codeJoiner
					.add(rendition.getWidth().toString())
					.add(rendition.getHeight().toString());
			//@formatter:on
		}

		//@formatter:off
		return codeJoiner
				.add(substringAfterLast(assetData.getPath(), PATH_DELIMITER))
				.add(UUID.randomUUID().toString())
				.toString();
		//@formatter:on
	}
}
