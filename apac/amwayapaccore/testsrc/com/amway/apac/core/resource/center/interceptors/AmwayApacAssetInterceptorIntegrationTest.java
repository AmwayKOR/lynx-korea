package com.amway.apac.core.resource.center.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class AmwayApacAssetInterceptorIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ModelService modelService;
	@Resource
	private Map<AccountClassificationEnum, Integer> amwayAccountClassificationRankMapping;
	@Resource
	private CatalogService catalogService;


	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
	}

	@Test
	public void testAssetInterceptor()
	{
		final AmwayAssetModel assetToSave = modelService.create(AmwayAssetModel.class);
		assetToSave.setAssetId("testAsset");
		assetToSave.setCatalogVersion(catalogService.getCatalogVersion("testCatalog", "Online"));
		assetToSave.setClassification(AccountClassificationEnum.PLATINUM_AND_ABOVE);
		modelService.save(assetToSave);
		modelService.refresh(assetToSave);
		Assert.assertEquals(amwayAccountClassificationRankMapping.get(AccountClassificationEnum.PLATINUM_AND_ABOVE),
				assetToSave.getRank());
	}

	@Test
	public void testAssetInterceptorForNullClassification()
	{
		final AmwayAssetModel assetToSave = modelService.create(AmwayAssetModel.class);
		assetToSave.setAssetId("testAsset");
		assetToSave.setCatalogVersion(catalogService.getCatalogVersion("testCatalog", "Online"));
		assetToSave.setClassification(null);
		modelService.save(assetToSave);
		modelService.refresh(assetToSave);
		Assert.assertEquals(amwayAccountClassificationRankMapping.get(AccountClassificationEnum.PLATINUM_AND_ABOVE),
				assetToSave.getRank());
	}

}
