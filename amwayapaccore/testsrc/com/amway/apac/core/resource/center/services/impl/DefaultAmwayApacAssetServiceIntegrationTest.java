package com.amway.apac.core.resource.center.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.apac.core.user.services.AmwayApacUserService;
import com.amway.apac.resourcecenter.enums.AmwayApacAssetsSort;
import com.amway.apac.resourcecenter.services.AmwayApacAssetService;
import com.amway.apac.resourcecentre.model.media.AmwayAssetModel;
import com.amway.core.model.AmwayAccountModel;


/**
 * Integration Test for Asset Service [ Knowledge Centre ]
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacAssetServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_PRODUCT_UID = "220136";
	private static final String TEST_AMWAY_ACCOUNT_ID = "8914266";
	private static final String TEST_AMWAY_ACCOUNT_AFFIALIATE_COUNTRY_CODE = "100";
	private static final String AMWAY_APAC_SITE_UID = "amwayapac";

	@Resource(name = "amwayApacAssetService")
	private AmwayApacAssetService amwayApacAssetService;

	@Resource(name = "cmsSiteService")
	private CMSSiteService cmsSiteService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "amwayAccountService")
	private AmwayApacAccountService amwayApacAccountService;

	@Resource(name = "userService")
	private AmwayApacUserService userService;

	/**
	 * Setup required data for integration test.
	 *
	 * @throws ImpExException
	 * @throws CMSItemNotFoundException
	 */
	@Before
	public void setup() throws ImpExException, CMSItemNotFoundException
	{
		importCsv("/amwayapaccore/test/common.impex", "UTF-8");
		importCsv("/amwayapaccore/test/testAssetService.impex", "UTF-8");
		cmsSiteService.setCurrentSiteAndCatalogVersions(AMWAY_APAC_SITE_UID, true);
		final AmwayAccountModel testAccount = amwayApacAccountService.getAmwayAccount(TEST_AMWAY_ACCOUNT_ID,
				TEST_AMWAY_ACCOUNT_AFFIALIATE_COUNTRY_CODE);
		if (null == testAccount)
		{
			throw new IllegalStateException("Test account is not properly setup. Please check.");
		}
		else
		{
			final CustomerModel primaryCustomer = testAccount.getPrimaryParty();
			userService.setCurrentUser(primaryCustomer);
		}
	}

	/**
	 * Test get assets for product
	 */
	@Test
	public void testGetAssetsForProduct()
	{
		ProductModel product = null;
		boolean collectiveTestResult = false;

		try
		{
			product = productService.getProductForCode(TEST_PRODUCT_UID);

			// Test product has 3 assets assigned through import script
			String currentYear = "";
			final int year = Calendar.getInstance().get(Calendar.YEAR);
			currentYear = Integer.toString(year);
			final String sortCode = AmwayApacAssetsSort.LATEST_DATE.getCode();

			// Getting all assets of component in single page
			int pageNumber = 0;
			int pageSize = 3;
			int expectedAssetsInComponent = 3;
			PageableData pageableData = createPageableData(pageNumber, pageSize, sortCode);
			SearchPageData<AmwayAssetModel> searchData = amwayApacAssetService.getAssetsForProduct(product, pageableData,
					currentYear);
			final boolean individualTestResult1 = (expectedAssetsInComponent == searchData.getResults().size());

			// Getting all assets of component in single page but page size is greater than assets assigned to component
			pageNumber = 0;
			pageSize = 5;
			expectedAssetsInComponent = 3;
			pageableData = createPageableData(pageNumber, pageSize, sortCode);
			searchData = amwayApacAssetService.getAssetsForProduct(product, pageableData, currentYear);
			final boolean individualTestResult2 = (expectedAssetsInComponent == searchData.getResults().size());

			// Getting all assets of component when page number is greater than possible max page number for results
			pageNumber = 1;
			pageSize = 4;
			expectedAssetsInComponent = 0;
			pageableData = createPageableData(pageNumber, pageSize, sortCode);
			searchData = amwayApacAssetService.getAssetsForProduct(product, pageableData, currentYear);
			final boolean individualTestResult3 = (expectedAssetsInComponent == searchData.getResults().size());

			// Getting all assets of component when page size is 1, single result should get returned
			pageNumber = 0;
			pageSize = 1;
			expectedAssetsInComponent = 1;
			pageableData = createPageableData(pageNumber, pageSize, sortCode);
			searchData = amwayApacAssetService.getAssetsForProduct(product, pageableData, currentYear);
			final boolean individualTestResult4 = (expectedAssetsInComponent == searchData.getResults().size());

			// Getting all assets of component when empty product provided, this test should return exception
			pageNumber = 0;
			pageSize = 4;
			expectedAssetsInComponent = 0;
			pageableData = createPageableData(pageNumber, pageSize, sortCode);

			boolean individualTestResult5 = false;
			try
			{
				searchData = amwayApacAssetService.getAssetsForProduct(null, pageableData, currentYear);
			}
			catch (final IllegalArgumentException e)
			{
				individualTestResult5 = true;
			}

			// Getting all assets of component when no asset exist for year which is 2018 in imported data
			pageNumber = 0;
			pageSize = 4;
			currentYear = "2019";
			expectedAssetsInComponent = 0;
			pageableData = createPageableData(pageNumber, pageSize, sortCode);
			searchData = amwayApacAssetService.getAssetsForProduct(product, pageableData, currentYear);
			final boolean individualTestResult6 = (expectedAssetsInComponent == searchData.getResults().size());

			collectiveTestResult = individualTestResult1 && individualTestResult2 && individualTestResult3 && individualTestResult4
					&& individualTestResult5 && individualTestResult6;
			assertTrue(collectiveTestResult);
		}
		catch (final UnknownIdentifierException | AmbiguousIdentifierException | IllegalArgumentException e)
		{
			assertFalse(e.getMessage(), collectiveTestResult);
		}
	}

	protected PageableData createPageableData(final int pageNumber, final int pageSize, final String sortCode)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(pageNumber);
		pageableData.setSort(sortCode);
		pageableData.setPageSize(pageSize);
		return pageableData;
	}
}
