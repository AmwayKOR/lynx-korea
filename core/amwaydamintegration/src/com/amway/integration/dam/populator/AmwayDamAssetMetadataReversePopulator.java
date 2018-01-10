package com.amway.integration.dam.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.model.AmwayDamAssetMetadataModel;
import com.amway.core.annotations.AmwayBean;

/**
 * Populates data from {@link AmwayDamAssetData} to {@link List<AmwayDamAssetMetadataModel>}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetMetadataReversePopulator implements Populator<AmwayDamAssetData, List<AmwayDamAssetMetadataModel>>
{
	@Autowired
	private ModelService modelService;

	@Override
	public void populate(AmwayDamAssetData source, List<AmwayDamAssetMetadataModel> target)
	{
		for (Map.Entry<String, Object> entry : source.getRowAssetMetadata().entrySet())
		{
			AmwayDamAssetMetadataModel damAssetMetadataModel = modelService.create(AmwayDamAssetMetadataModel.class);
			damAssetMetadataModel.setDamAssetId(source.getAssetId());
			damAssetMetadataModel.setKey(entry.getKey());
			damAssetMetadataModel.setValue(String.valueOf(entry.getValue()));
			target.add(damAssetMetadataModel);
		}
	}
}
