package com.amway.integration.dam.service.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.*;

import de.hybris.platform.enumeration.EnumerationService;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

import com.amway.integration.dam.dao.AmwayDamAssetFormatDao;
import com.amway.integration.dam.data.AmwayDamRenditionData;
import com.amway.integration.dam.enums.AmwayDamAssetTypeEnum;
import com.amway.integration.dam.model.AmwayDamAssetFormatModel;
import com.amway.integration.dam.service.AmwayDamMediaFormatService;
import com.google.common.collect.ImmutableMap;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of service for working with {@link AmwayDamRenditionData}
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamMediaFormatServiceImpl implements AmwayDamMediaFormatService
{
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

	private Double imageSizeDeviation;

	@Autowired
	private AmwayDamAssetFormatDao amwayDamAssetFormatDao;

	@Autowired
	private EnumerationService enumerationService;

	@Override
	public AmwayDamAssetFormatModel getAssetFormat(AmwayDamRenditionData renditionData)
	{
		List<AmwayDamAssetFormatModel> amwayDamAssetFormatModels = getAssetFormats(renditionData);

		if (CollectionUtils.isEmpty(amwayDamAssetFormatModels))
		{
			LOG.warn(WARNING_UNKNOWN_MEDIA_FORMAT + ": MediaFormat not found: renditionId=[" + renditionData.getRenditionId() + "]");
			return null;
		}

		return amwayDamAssetFormatModels.get(0);
	}

	private List<AmwayDamAssetFormatModel> getAssetFormats(AmwayDamRenditionData renditionData)
	{
		if (isRenditionWithoutSize(renditionData))
		{
			return getAssetFormatWithoutSizes(renditionData);
		}

		return getAssetFormatWithDeviationSizes(renditionData);
	}

	private boolean isRenditionWithoutSize(AmwayDamRenditionData renditionData)
	{
		Integer width = renditionData.getWidth();
		Integer height = renditionData.getHeight();
		return width == null || height == null || width < 0 || height < 0;
	}

	private List<AmwayDamAssetFormatModel> getAssetFormatWithoutSizes(AmwayDamRenditionData renditionData)
	{
		String typeCode = getFormatType(renditionData);
		return amwayDamAssetFormatDao.findByAssetTypeWithoutSize(typeCode);
	}

	private List<AmwayDamAssetFormatModel> getAssetFormatWithDeviationSizes(AmwayDamRenditionData renditionData)
	{
		String typeCode = getFormatType(renditionData);
		Integer renditionWidth = renditionData.getWidth();
		Integer renditionHeight = renditionData.getHeight();

		int heightDeviation = getDeviationForValue(renditionHeight);
		int widthDeviation = getDeviationForValue(renditionWidth);

		//@formatter:off
		Map<String, Object> assetFormatParameters = ImmutableMap.<String, Object>builder()
			  .put(PARAM_ASSET_TYPE, typeCode)
			  .put(PARAM_MIN_HEIGHT, calculateMinValue(renditionHeight, heightDeviation))
			  .put(PARAM_MAX_HEIGHT, calculateMaxValue(renditionHeight, heightDeviation))
			  .put(PARAM_MIN_WIDTH, calculateMinValue(renditionWidth, widthDeviation))
			  .put(PARAM_MAX_WIDTH, calculateMaxValue(renditionWidth, widthDeviation))
			  .build();
		//@formatter:on

		return amwayDamAssetFormatDao.findByAssetTypeWidthIntervalAndHeightInterval(assetFormatParameters);
	}

	protected String getFormatType(AmwayDamRenditionData renditionData)
	{
		AmwayDamAssetTypeEnum assetType = enumerationService.getEnumerationValue(AmwayDamAssetTypeEnum._TYPECODE,
				renditionData.getType().name());
		return assetType.getCode();
	}

	private int getDeviationForValue(int value)
	{
		return (int) Math.round(value * imageSizeDeviation);
	}

	private Integer calculateMinValue(int value, int deviation)
	{
		if (deviation < value)
		{
			return value - deviation;
		}

		return 0;
	}

	private Integer calculateMaxValue(int value, int deviation)
	{
		if (Integer.MAX_VALUE - deviation > value)
		{
			return value + deviation;
		}

		return Integer.MAX_VALUE;
	}

	@Required
	public void setImageSizeDeviation(Double imageSizeDeviation)
	{
		this.imageSizeDeviation = imageSizeDeviation;
	}
}
