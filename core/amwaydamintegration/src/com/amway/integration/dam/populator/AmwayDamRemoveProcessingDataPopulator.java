package com.amway.integration.dam.populator;

import de.hybris.platform.converters.Populator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.dao.AmwayDamAssetDao;
import com.amway.integration.dam.data.AmwayDamProcessingData;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamRemoveProcessingDataPopulator implements Populator<AmwayDamQueueEntryModel, AmwayDamProcessingData>
{
	@Autowired
	private AmwayDamAssetDao amwayDamAssetDao;

	@Override
	public void populate(AmwayDamQueueEntryModel source, AmwayDamProcessingData target)
	{
		String path = source.getPath();
		List<AmwayDamAssetModel> amwayDamAssets = amwayDamAssetDao.findAssetsForInactiveCatalogsByPath(path);
		target.setAssetsForRemove(amwayDamAssets);
		target.setAssetPath(path);
		target.setOperationType(source.getOperation());
	}
}
