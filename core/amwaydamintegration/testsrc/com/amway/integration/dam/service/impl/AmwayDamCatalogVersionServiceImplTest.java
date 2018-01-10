package com.amway.integration.dam.service.impl;

import static com.amway.integration.dam.data.AmwayDamCatalogTypeEnum.CONTENT;
import static com.amway.integration.dam.data.AmwayDamCatalogTypeEnum.PRODUCT;
import static java.util.Collections.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.services.ContentCatalogService;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.integration.dam.data.AmwayDamAssetData;
import com.amway.integration.dam.data.AmwayDamCatalogFetchingData;
import com.amway.integration.dam.strategy.AmwayDamCatalogFetchStrategy;


/**
 * Unit test for {@link AmwayDamCatalogVersionServiceImpl}
 */
@UnitTest
public class AmwayDamCatalogVersionServiceImplTest
{
	@InjectMocks
	private AmwayDamCatalogVersionServiceImpl amwayDamCatalogVersionService;

	@Mock
	private AmwayDamCatalogFetchStrategy amwayDamCatalogFetchStrategy;
	@Mock
	private ContentCatalogService contentCatalogService;
	@Mock
	private CatalogVersionModel validCatalogVersion;
	@Mock
	private AmwayDamAssetData validAssetData;

	private Set<AmwayDamCatalogFetchStrategy> amwayCatalogFetchStrategies;

	private static final String ANY_ASSET_COUNTRY = "anyAssetCountry";

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		amwayCatalogFetchStrategies = singleton(amwayDamCatalogFetchStrategy);
		amwayDamCatalogVersionService.setAmwayCatalogFetchStrategies(amwayCatalogFetchStrategies);
	}

	@Test
	public void shouldGetEmptyListOfCatalogVersionsForAssetDataWithoutCountries()
	{
		when(validAssetData.getCountries()).thenReturn(emptyList());

		List<CatalogVersionModel> catalogVersionsForAssetData = amwayDamCatalogVersionService.getCatalogVersionsForAssetData(
			  validAssetData);

		assertThat(catalogVersionsForAssetData).isEmpty();
	}

	@Test
	public void shouldNotGetCatalogVersionsForAssetDataDeliveredByNotApplicableCatalogFetchStrategy()
	{
		when(amwayDamCatalogFetchStrategy.isApplicable(any(String.class))).thenReturn(false);
		when(amwayDamCatalogFetchStrategy.fetchCatalogVersion(any(AmwayDamCatalogFetchingData.class))).thenReturn(
			  validCatalogVersion);

		when(validAssetData.getCountries()).thenReturn(singletonList(ANY_ASSET_COUNTRY));

		List<CatalogVersionModel> catalogVersionsForAssetData = amwayDamCatalogVersionService.getCatalogVersionsForAssetData(
			  validAssetData);

		assertThat(catalogVersionsForAssetData).excludes(validCatalogVersion);
	}

	@Test
	public void shouldGetCatalogVersionsForAssetDataWithCountriesDeliveredByApplicableCatalogFetchStrategy()
	{
		when(amwayDamCatalogFetchStrategy.isApplicable(any(String.class))).thenReturn(true);
		when(amwayDamCatalogFetchStrategy.fetchCatalogVersion(any(AmwayDamCatalogFetchingData.class))).thenReturn(
			  validCatalogVersion);

		when(validAssetData.getCountries()).thenReturn(singletonList(ANY_ASSET_COUNTRY));

		List<CatalogVersionModel> catalogVersionsForAssetData = amwayDamCatalogVersionService.getCatalogVersionsForAssetData(
			  validAssetData);

		assertThat(catalogVersionsForAssetData).containsExactly(validCatalogVersion);
	}

	@Test
	public void shouldDecideCatalogVersionValidByCatalogTypeWhenCatalogVersionIsContentAndCatalogTypeIsContent()
	{
		when(contentCatalogService.isContentCatalog(validCatalogVersion)).thenReturn(true);
		when(contentCatalogService.isProductCatalog(validCatalogVersion)).thenReturn(false);

		boolean isCatalogVersionValidByCatalogType = amwayDamCatalogVersionService.isCatalogVersionValidByCatalogType(
			  validCatalogVersion, CONTENT);

		assertThat(isCatalogVersionValidByCatalogType).isTrue();
	}

	@Test
	public void shouldDecideCatalogVersionValidByCatalogTypeWhenCatalogVersionIsProductAndCatalogTypeIsProduct()
	{
		when(contentCatalogService.isContentCatalog(validCatalogVersion)).thenReturn(false);
		when(contentCatalogService.isProductCatalog(validCatalogVersion)).thenReturn(true);

		boolean isCatalogVersionValidByCatalogType = amwayDamCatalogVersionService.isCatalogVersionValidByCatalogType(
			  validCatalogVersion, PRODUCT);

		assertThat(isCatalogVersionValidByCatalogType).isTrue();
	}

	@Test
	public void shouldDecideCatalogVersionIsInvalidByCatalogTypeWhenCatalogVersionIsProductButCatalogTypeIsContent()
	{
		when(contentCatalogService.isContentCatalog(validCatalogVersion)).thenReturn(true);
		when(contentCatalogService.isProductCatalog(validCatalogVersion)).thenReturn(false);

		boolean isCatalogVersionValidByCatalogType = amwayDamCatalogVersionService.isCatalogVersionValidByCatalogType(
			  validCatalogVersion, PRODUCT);

		assertThat(isCatalogVersionValidByCatalogType).isFalse();
	}

	@Test
	public void shouldDecideCatalogVersionIsInvalidByCatalogTypeWhenCatalogVersionIsContentButCatalogTypeIsProduct()
	{
		when(contentCatalogService.isContentCatalog(validCatalogVersion)).thenReturn(true);
		when(contentCatalogService.isProductCatalog(validCatalogVersion)).thenReturn(false);

		boolean isCatalogVersionValidByCatalogType = amwayDamCatalogVersionService.isCatalogVersionValidByCatalogType(
			  validCatalogVersion, PRODUCT);

		assertThat(isCatalogVersionValidByCatalogType).isFalse();
	}

}
