package com.amway.integration.dam.strategy.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.CATALOG_VERSION_STAGED;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;

import java.util.Collection;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamAssetDefinition;
import com.amway.integration.dam.data.AmwayDamCatalogFetchingData;
import com.amway.integration.dam.data.AmwayDamCatalogTypeEnum;
import com.amway.integration.dam.service.AmwayDamAssetDefinitionService;
import com.amway.integration.dam.service.AmwayDamCatalogVersionService;
import com.amway.integration.dam.strategy.AmwayDamCatalogFetchStrategy;
import com.amway.integration.dam.strategy.AmwayDamSiteCatalogFetchStrategy;
import com.amway.core.annotations.AmwayBean;

/**
 * Strategy for fetching appropriate catalog related to site
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamSiteCatalogFetchStrategyImpl implements AmwayDamCatalogFetchStrategy, AmwayDamSiteCatalogFetchStrategy
{
	@Autowired
	private CMSSiteService cmsSiteService;
	@Autowired
	private AmwayDamCatalogVersionService amwayDamCatalogVersionService;
	@Autowired
	private AmwayDamAssetDefinitionService amwayDamAssetDefinitionService;

	private Map<String, String> countriesToIsoCodeMap;

	@Override
	public boolean isApplicable(String value)
	{
		return countriesToIsoCodeMap.containsKey(value);
	}

	@Override
	public CatalogVersionModel fetchCatalogVersion(AmwayDamCatalogFetchingData catalogFetchingData)
	{
		String country = catalogFetchingData.getCountry();
		String countryIsoCode = countriesToIsoCodeMap.get(country);
		CMSSiteModel site = getSiteForCountry(countryIsoCode);

		if (site == null)
		{
			return null;
		}

		return fetchCatalogVersionBySite(catalogFetchingData, site);
	}

	@Override
	public CatalogVersionModel fetchCatalogVersionBySite(AmwayDamCatalogFetchingData catalogFetchingData,
			@NotNull CMSSiteModel site)
	{
		AmwayDamAssetData assetData = catalogFetchingData.getAssetData();
		AmwayDamAssetDefinition assetDefinition = amwayDamAssetDefinitionService.getAssetDefinition(assetData);
		if (assetDefinition == null || assetDefinition.getCatalogType() == null)
		{
			return null;
		}

		AmwayDamCatalogTypeEnum catalogType = assetDefinition.getCatalogType();

		Collection<CatalogModel> allSiteCatalogs = cmsSiteService.getAllCatalogs(site);
		//@formatter:off
		return allSiteCatalogs.stream()
			  .map(CatalogModel::getCatalogVersions)
			  .flatMap(Collection::stream)
			  .filter(catalog -> amwayDamCatalogVersionService.isCatalogVersionValidByCatalogType(catalog, catalogType))
			  .filter(catalogVersion -> CATALOG_VERSION_STAGED.equals(catalogVersion.getVersion()))
			  .findFirst()
			  .orElse(null);
		//@formatter:on
	}

	private CMSSiteModel getSiteForCountry(String isoCode)
	{
		Collection<CMSSiteModel> sites = cmsSiteService.getSites();
		//@formatter:off
		return sites.stream()
			  .filter(baseSite -> baseSite.getDefaultCountry() != null)
			  .filter(baseSite -> baseSite.getDefaultCountry().getIsocode().equalsIgnoreCase(isoCode))
			  .findFirst()
			  .orElse(null);
		//@formatter:on
	}

	@Required
	public void setCountriesToIsoCodeMap(Map<String, String> countriesToIsoCodeMap)
	{
		this.countriesToIsoCodeMap = countriesToIsoCodeMap;
	}

}
