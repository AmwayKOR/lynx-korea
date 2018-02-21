package com.amway.integration.dam.populator;

import de.hybris.platform.converters.Populator;

import com.amway.integration.dam.data.AmwayDamExtendedRenditionData;
import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.model.AmwayDamAssetItemModel;
import com.amway.core.annotations.AmwayBean;

/**
 * Populating values from {@link AmwayDamExtendedRenditionData} to {@link AmwayDamAssetItemModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetItemReversePopulator implements Populator<AmwayDamExtendedRenditionData, AmwayDamAssetItemModel>
{

	@Override
	public void populate(AmwayDamExtendedRenditionData source, AmwayDamAssetItemModel target)
	{
		AmwayDamRenditionData renditionData = source.getRenditionData();
		target.setCode(source.getRenditionCode());
		target.setPath(renditionData.getAsset().getPath());
		target.setRealFileName(renditionData.getRenditionId());
		target.setCatalogVersion(source.getCatalogVersion());
		target.setMediaFormat(source.getMediaFormat());
		target.setMime(renditionData.getMimeType());
		target.setRemoved(Boolean.FALSE);
	}
}
