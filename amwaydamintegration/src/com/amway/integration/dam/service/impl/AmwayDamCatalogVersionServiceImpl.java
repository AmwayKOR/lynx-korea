package com.amway.integration.dam.service.impl;

import static com.amway.integration.dam.data.AmwayDamCatalogTypeEnum.CONTENT;
import static com.amway.integration.dam.data.AmwayDamCatalogTypeEnum.PRODUCT;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.services.ContentCatalogService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamCatalogFetchingData;
import com.amway.integration.dam.data.AmwayDamCatalogTypeEnum;
import com.amway.integration.dam.service.AmwayDamCatalogVersionService;
import com.amway.integration.dam.strategy.AmwayDamCatalogFetchStrategy;
import com.amway.core.annotations.AmwayBean;

/**
 * Implementation of service for working with {@link CatalogVersionModel} versions for DAM
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamCatalogVersionServiceImpl implements AmwayDamCatalogVersionService
{
	@Autowired
	private ContentCatalogService contentCatalogService;

	private Set<AmwayDamCatalogFetchStrategy> amwayCatalogFetchStrategies;

	@Override
	public List<CatalogVersionModel> getCatalogVersionsForAssetData(AmwayDamAssetData assetData)
	{
		List<String> countries = assetData.getCountries();
		if (CollectionUtils.isNotEmpty(countries))
		{
			//@formatter:off
			return countries.stream()
				  .map(country -> getCatalogVersion(country, assetData))
				  .filter(Objects::nonNull)
				  .collect(Collectors.toList());
			//@formatter:on
		}

		return Collections.emptyList();
	}

	private CatalogVersionModel getCatalogVersion(String country, AmwayDamAssetData assetData)
	{
		AmwayDamCatalogFetchingData catalogFetchingData = new AmwayDamCatalogFetchingData();
		catalogFetchingData.setCountry(country);
		catalogFetchingData.setAssetData(assetData);

		//@formatter:off
		return amwayCatalogFetchStrategies.stream()
			  .filter(strategy -> strategy.isApplicable(country))
			  .map(strategy -> strategy.fetchCatalogVersion(catalogFetchingData))
			  .filter(Objects::nonNull)
			  .findFirst()
			  .orElse(null);
		//@formatter:on
	}

	@Override
	public boolean isCatalogVersionValidByCatalogType(CatalogVersionModel catalogVersion, AmwayDamCatalogTypeEnum catalogType)
	{
		if (catalogType.equals(CONTENT))
		{
			return contentCatalogService.isContentCatalog(catalogVersion);
		}
		else if (catalogType.equals(PRODUCT))
		{
			return contentCatalogService.isProductCatalog(catalogVersion);
		}

		return false;
	}

	@Required
	public void setAmwayCatalogFetchStrategies(Set<AmwayDamCatalogFetchStrategy> amwayCatalogFetchStrategies)
	{
		this.amwayCatalogFetchStrategies = amwayCatalogFetchStrategies;
	}
}
