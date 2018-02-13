package com.amway.integration.dam.dao.impl;

import static java.util.Collections.singletonMap;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;

import com.amway.integration.dam.dao.AmwayDamAssetMetadataDao;
import com.amway.integration.dam.model.AmwayDamAssetMetadataModel;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of DAO for working with {@link AmwayDamAssetMetadataModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetMetadataDaoImpl extends DefaultGenericDao<AmwayDamAssetMetadataModel>
		implements AmwayDamAssetMetadataDao
{
	private static final String PARAM_ASSET_ID = "assetId";

	public AmwayDamAssetMetadataDaoImpl()
	{
		super(AmwayDamAssetMetadataModel._TYPECODE);
	}

	@Override
	public List<AmwayDamAssetMetadataModel> findByAssetId(String assetId)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(PARAM_ASSET_ID, assetId);
		return find(singletonMap(AmwayDamAssetMetadataModel.DAMASSETID, assetId));
	}
}
