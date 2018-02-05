package com.amway.apac.core.product.strategies.impl;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.account.services.impl.DefaultAmwayApacAccountService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.AccountClassificationEnum;
import com.amway.apac.core.model.AmwayPreLaunchConfigModel;
import com.amway.apac.core.product.AmwayPreLaunchResponse;
import com.amway.apac.core.product.AmwayProductPreLaunchStatus;
import com.amway.core.constants.AmwaycoreConstants.SessionVariables;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacProductPreLaunchStrategyIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private DefaultAmwayApacProductPreLaunchStrategy defaultAmwayApacProductPreLaunchStrategy;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private DefaultAmwayApacAccountService defaultAmwayApacAccountService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private SessionService sessionService;
	@Resource
	ProductService productService;
	@Resource
	ModelService modelService;

	private static final String PRODUCT_WITHOUT_PRELAUNCH = "HW1210-3425";
	private static final String PRODUCT_BEFORE_PRELAUNCH = "HW1210-3422";
	private static final String PRODUCT_IN_PRELAUNCH = "HW1210-3423";
	private static final String PRODUCT_AFTER_PRELAUNCH = "HW1210-3424";


	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));
		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				AccountClassificationEnum.PLATINUM_AND_ABOVE.toString());
		sessionService.setAttribute(SessionVariables.ACCOUNT,
				defaultAmwayApacAccountService.getAmwayAccount("ahertz", "100").iterator().next());
		setProductsPrelaunchProperties();
	}

	private void setProductsPrelaunchProperties()
	{
		final Date now = Calendar.getInstance().getTime();
		final Date one_month_from_now = Calendar.getInstance().getTime();
		one_month_from_now.setMonth(now.getMonth() + 1);
		final Date one_month_ago = Calendar.getInstance().getTime();
		one_month_ago.setMonth(now.getMonth() - 1);
		final List<AmwayPreLaunchConfigModel> preLaunchConfigs = new ArrayList<>();
		final AmwayPreLaunchConfigModel config_before = productService.getProductForCode(PRODUCT_BEFORE_PRELAUNCH)
				.getPreLaunchConfig();
		config_before.setStartDate(one_month_from_now);
		preLaunchConfigs.add(config_before);
		final AmwayPreLaunchConfigModel config_in = productService.getProductForCode(PRODUCT_IN_PRELAUNCH).getPreLaunchConfig();
		config_in.setStartDate(one_month_ago);
		config_in.setEndDate(one_month_from_now);
		preLaunchConfigs.add(config_in);
		final AmwayPreLaunchConfigModel config_after = productService.getProductForCode(PRODUCT_AFTER_PRELAUNCH)
				.getPreLaunchConfig();
		config_after.setEndDate(one_month_ago);
		preLaunchConfigs.add(config_after);
		modelService.saveAll(preLaunchConfigs);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetProductPrelaunchStatusForCurrentUserForNullProduct()
	{
		defaultAmwayApacProductPreLaunchStrategy.getProductPrelaunchStatusForCurrentUser(null);
	}

	@Test
	public void testGetProductPrelaunchStatusForCurrentUserForNonPrelaunchProduct()
	{
		final AmwayPreLaunchResponse prelaunchResponse = defaultAmwayApacProductPreLaunchStrategy
				.getProductPrelaunchStatusForCurrentUser(productService.getProductForCode(PRODUCT_WITHOUT_PRELAUNCH));
		Assert.assertEquals(AmwayProductPreLaunchStatus.NOT_IN_PRELAUNCH, prelaunchResponse.getPreLaunchStatus());
	}

	@Test
	public void testGetProductPrelaunchStatusForCurrentUserForNonClassified()
	{
		sessionService.setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				AccountClassificationEnum.NORMAL_ABO.toString());
		final AmwayPreLaunchResponse prelaunchResponse = defaultAmwayApacProductPreLaunchStrategy
				.getProductPrelaunchStatusForCurrentUser(productService.getProductForCode(PRODUCT_IN_PRELAUNCH));
		Assert.assertEquals(AmwayProductPreLaunchStatus.IN_PRE_LAUNCH_PERIOD, prelaunchResponse.getPreLaunchStatus());
		Assert.assertEquals(Integer.valueOf(0), prelaunchResponse.getAllowedQuantity());
	}

	@Test
	public void testGetProductPrelaunchStatusForCurrentUserForClassifiedBeforeLaunch()
	{
		final AmwayPreLaunchResponse prelaunchResponse = defaultAmwayApacProductPreLaunchStrategy
				.getProductPrelaunchStatusForCurrentUser(productService.getProductForCode(PRODUCT_BEFORE_PRELAUNCH));
		Assert.assertEquals(AmwayProductPreLaunchStatus.NOT_YET_LAUNCHED, prelaunchResponse.getPreLaunchStatus());
		Assert.assertEquals(Integer.valueOf(0), prelaunchResponse.getAllowedQuantity());
	}

	@Test
	public void testGetProductPrelaunchStatusForCurrentUserForClassifiedAfterPrelaunchPeriod()
	{
		final AmwayPreLaunchResponse prelaunchResponse = defaultAmwayApacProductPreLaunchStrategy
				.getProductPrelaunchStatusForCurrentUser(productService.getProductForCode(PRODUCT_AFTER_PRELAUNCH));
		Assert.assertEquals(AmwayProductPreLaunchStatus.NOT_IN_PRELAUNCH, prelaunchResponse.getPreLaunchStatus());
	}

	@Test
	public void testGetProductPrelaunchStatusForCurrentUserForClassifiedInPrelaunchPeriodNoConsumption()
	{
		final AmwayPreLaunchResponse prelaunchResponse = defaultAmwayApacProductPreLaunchStrategy
				.getProductPrelaunchStatusForCurrentUser(productService.getProductForCode(PRODUCT_IN_PRELAUNCH));
		Assert.assertEquals(AmwayProductPreLaunchStatus.IN_PRE_LAUNCH_PERIOD, prelaunchResponse.getPreLaunchStatus());
		Assert.assertEquals(Integer.valueOf(5), prelaunchResponse.getAllowedQuantity());
	}

	@Test
	public void testGetProductPrelaunchStatusForCurrentUserForClassifiedInPrelaunchPeriodCompleteConsumption()
	{
		sessionService.setAttribute(SessionVariables.ACCOUNT,
				defaultAmwayApacAccountService.getAmwayAccount("abrode", "100").iterator().next());
		final AmwayPreLaunchResponse prelaunchResponse = defaultAmwayApacProductPreLaunchStrategy
				.getProductPrelaunchStatusForCurrentUser(productService.getProductForCode(PRODUCT_IN_PRELAUNCH));
		Assert.assertEquals(AmwayProductPreLaunchStatus.IN_PRE_LAUNCH_PERIOD, prelaunchResponse.getPreLaunchStatus());
		Assert.assertEquals(Integer.valueOf(0), prelaunchResponse.getAllowedQuantity());
	}

	@Test
	public void testGetProductPrelaunchStatusForCurrentUserForClassifiedForNegativeCount()
	{
		final ProductModel product = productService.getProductForCode(PRODUCT_IN_PRELAUNCH);
		product.getPreLaunchConfig().setMaxShoppingCount(Integer.valueOf(AmwayapacCoreConstants.NEGATIVE_ONE));
		modelService.save(product);
		modelService.refresh(product);
		final AmwayPreLaunchResponse prelaunchResponse = defaultAmwayApacProductPreLaunchStrategy
				.getProductPrelaunchStatusForCurrentUser(product);
		Assert.assertEquals(AmwayProductPreLaunchStatus.IN_PRE_LAUNCH_PERIOD, prelaunchResponse.getPreLaunchStatus());
		Assert.assertEquals(product.getMaxOrderQuantity(), prelaunchResponse.getAllowedQuantity());
	}

}
