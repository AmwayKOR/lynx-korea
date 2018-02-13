package com.amway.integration.dam.validate.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.WARNING_ASSET_NOT_EXIST;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.lang.invoke.MethodHandles;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.validate.AmwayDamValidator;
import com.amway.core.annotations.AmwayBean;

/**
 * Validating {@link AmwayDamProcessingData} for events of type UPDATE and METADATE_UPDATE
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamUpdateEventValidatorImpl implements AmwayDamValidator
{
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

	@Override
	public void validate(AmwayDamProcessingData processingData)
	{
		AmwayDamAssetData assetData = processingData.getAssetData();
		Map<CatalogVersionModel, AmwayDamAssetModel> mapToUpdate = processingData.getCatalogToAssetMapForUpdate();

		if (mapToUpdate.isEmpty() && isNotEmpty(processingData.getCatalogsForCreateAssets()))
		{
			LOG.warn(
					WARNING_ASSET_NOT_EXIST + ": Update for not exists asset: id=[" + assetData.getAssetId() + "], path=[" + assetData
							.getPath() + "]");
		}
	}
}
