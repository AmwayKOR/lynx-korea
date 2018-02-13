package com.amway.integration.dam.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;

import com.amway.integration.dam.dao.AmwayDamAssetDao;
import com.amway.integration.dam.model.AmwayDamAssetModel;
import com.amway.core.annotations.AmwayBean;

/**
 * DAO for working with {@link AmwayDamAssetModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetDaoImpl extends DefaultGenericDao<AmwayDamAssetModel> implements AmwayDamAssetDao
{
	private static final String PARAM_ASSET_ID = "assetId";
	private static final String PARAM_CATALOG_ACTIVE = "isCatalogActive";
	private static final String PARAM_PATH = "path";

	public AmwayDamAssetDaoImpl()
	{
		super(AmwayDamAssetModel._TYPECODE);
	}

	@Override
	public List<AmwayDamAssetModel> findAssetsForInactiveCatalogsByPath(String path)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(PARAM_PATH, path);

		//@formatter:off
		FlexibleSearchQuery query = new FlexibleSearchQuery(
			  "SELECT {a." + AmwayDamAssetModel.PK + "}"
			  + " FROM {" + AmwayDamAssetModel._TYPECODE + " AS a"
					+ " JOIN " + CatalogVersionModel._TYPECODE + " AS cv ON"
					+ " {a." + AmwayDamAssetModel.CATALOGVERSION + "} = {cv." + CatalogVersionModel.PK +  "}"
			  + "}"
			  + " WHERE {a." + AmwayDamAssetModel.ORIGINAL + "} =?" + PARAM_PATH
			  + " AND {cv." + CatalogVersionModel.ACTIVE + "} =?" + PARAM_CATALOG_ACTIVE
		);
		//@formatter:on

		query.addQueryParameter(PARAM_PATH, path);
		query.addQueryParameter(PARAM_CATALOG_ACTIVE, Boolean.FALSE);

		return getFlexibleSearchService().<AmwayDamAssetModel>search(query).getResult();
	}

	@Override
	public List<AmwayDamAssetModel> findAssetsForInactiveCatalogsByAssetId(String assetId)
	{
		ServicesUtil.validateParameterNotNullStandardMessage(PARAM_ASSET_ID, assetId);

		//@formatter:off
		FlexibleSearchQuery query = new FlexibleSearchQuery(
			  "SELECT {a." + AmwayDamAssetModel.PK + "}"
			  + " FROM {" + AmwayDamAssetModel._TYPECODE + " AS a"
					+ " JOIN " + CatalogVersionModel._TYPECODE + " AS cv ON"
					+ " {a." + AmwayDamAssetModel.CATALOGVERSION + "} = {cv." + CatalogVersionModel.PK +  "}"
			  + "}"
			  + " WHERE {a." + AmwayDamAssetModel.ASSETID + "} =?" + PARAM_ASSET_ID
			  + " AND {cv." + CatalogVersionModel.ACTIVE + "} =?" + PARAM_CATALOG_ACTIVE
		);
		//@formatter:on

		query.addQueryParameter(PARAM_ASSET_ID, assetId);
		query.addQueryParameter(PARAM_CATALOG_ACTIVE, Boolean.FALSE);

		return getFlexibleSearchService().<AmwayDamAssetModel>search(query).getResult();
	}
}
