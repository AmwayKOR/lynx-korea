package com.amway.integration.dam.dao.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.*;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;
import java.util.Map;

import com.amway.integration.dam.dao.AmwayDamAssetFormatDao;
import com.amway.integration.dam.model.AmwayDamAssetFormatModel;
import com.google.common.collect.ImmutableMap;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of DAO for working with {@link AmwayDamAssetFormatModel}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamAssetFormatDaoImpl extends DefaultGenericDao<AmwayDamAssetFormatModel> implements AmwayDamAssetFormatDao
{

	public AmwayDamAssetFormatDaoImpl()
	{
		super(AmwayDamAssetFormatModel._TYPECODE);
	}

	@Override
	public List<AmwayDamAssetFormatModel> findByAssetTypeWithoutSize(String assetType)
	{
		validateParameterNotNullStandardMessage(PARAM_ASSET_TYPE, assetType);
		//@formatter:off
		Map<String, Object> searchParameters = ImmutableMap.<String, Object>builder()
			  .put(AmwayDamAssetFormatModel.ASSETTYPE, assetType)
			  .put(AmwayDamAssetFormatModel.WIDTH, WITHOUT_SIZE)
			  .put(AmwayDamAssetFormatModel.HEIGHT, WITHOUT_SIZE)
			  .build();
		//@formatter:on
		return find(searchParameters);
	}

	@Override
	public List<AmwayDamAssetFormatModel> findByAssetTypeWidthIntervalAndHeightInterval(Map<String, Object> parameters)
	{
		validateParameterNotNullStandardMessage(PARAM_ASSET_TYPE, parameters.get(PARAM_ASSET_TYPE));
		validateParameterNotNullStandardMessage(PARAM_MIN_HEIGHT, parameters.get(PARAM_MIN_HEIGHT));
		validateParameterNotNullStandardMessage(PARAM_MAX_HEIGHT, parameters.get(PARAM_MAX_HEIGHT));
		validateParameterNotNullStandardMessage(PARAM_MIN_WIDTH, parameters.get(PARAM_MIN_WIDTH));
		validateParameterNotNullStandardMessage(PARAM_MAX_WIDTH, parameters.get(PARAM_MAX_WIDTH));

		//@formatter:off
		FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(
			  "SELECT {" + AmwayDamAssetFormatModel.PK + "}"
			  + " FROM {" + AmwayDamAssetFormatModel._TYPECODE + "}"
			  + " WHERE {" + AmwayDamAssetFormatModel.ASSETTYPE + "} = ?" + PARAM_ASSET_TYPE
			  + " AND {" + AmwayDamAssetFormatModel.WIDTH + "} >= ?" + PARAM_MIN_WIDTH
			  + " AND {" + AmwayDamAssetFormatModel.WIDTH + "} <= ?" + PARAM_MAX_WIDTH
			  + " AND {" + AmwayDamAssetFormatModel.HEIGHT + "} >= ?" + PARAM_MIN_HEIGHT
			  + " AND {" + AmwayDamAssetFormatModel.HEIGHT + "} <= ?" + PARAM_MAX_HEIGHT
		);
		//@formatter:on

		searchQuery.addQueryParameters(parameters);

		return getFlexibleSearchService().<AmwayDamAssetFormatModel>search(searchQuery).getResult();
	}
}
