/**
 *
 */
package com.amway.core.order.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayPaymentConfigModel;


@IntegrationTest
public class DefaultAmwayPaymentModeDaoTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayPaymentModeDao defaultAmwayPaymentModeDao;
	@Resource
	private CustomerAccountService customerAccountService;
	@Resource
	private BaseStoreService baseStoreService;
	private static final String DEFAULT_STORE_ORDER_CODE = "testOrder1";
	private static final String DEFAULT_STORE2_ORDER_CODE = "testOrder14";
	private static final String STORE_UID = "defaultstore";
	private static final String STORE2_UID = "defaultstore2";

	private OrderModel defaultStoreOrder;
	private OrderModel defaultStore2Order;
	private BaseStoreModel defaultStore;
	private BaseStoreModel defaultStore2;

	private HashMap<String, Integer> selectedPayModes;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/delivery-modes.csv", "windows-1252");
		importCsv("/amwaycore/test/productTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/splitConfigTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/orderTestDataForSplit.csv", "windows-1252");
		defaultStore = baseStoreService.getBaseStoreForUid(STORE_UID);
		defaultStore2 = baseStoreService.getBaseStoreForUid(STORE2_UID);
		defaultStoreOrder = customerAccountService.getOrderForCode(DEFAULT_STORE_ORDER_CODE, defaultStore);
		defaultStore2Order = customerAccountService.getOrderForCode(DEFAULT_STORE2_ORDER_CODE, defaultStore2);
		selectedPayModes = new HashMap<String, Integer>();
		selectedPayModes.put(AmwaycoreConstants.PaymentMode.CREDITCARD,
				selectedPayModes.containsKey(AmwaycoreConstants.PaymentMode.CREDITCARD)
						? new Integer(selectedPayModes.get(AmwaycoreConstants.PaymentMode.CREDITCARD).intValue() + 1) : new Integer(1));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.dao.impl.DefaultAmwayPaymentModeDao#getSupportedSplitCombinations(de.hybris.platform.commerceservices.enums.SalesApplication, com.amway.core.enums.AmwayBusinessNature, de.hybris.platform.core.model.order.AbstractOrderModel, java.util.HashMap)}
	 * .
	 */
	@Test
	public void testGetSupportedSplitCombinations()
	{

		final List<AmwayPaymentConfigModel> paymentConfigs = defaultAmwayPaymentModeDao.getSupportedSplitCombinations(
				SalesApplication.WEB, AmwayBusinessNature.AMWAYBUSINESSNATURE_1, defaultStoreOrder, selectedPayModes);
		Assert.assertTrue(CollectionUtils.isNotEmpty(paymentConfigs));

	}

	@Test
	public void testGetSupportedSplitCombinationsBySalesApplication()
	{
		final List<AmwayPaymentConfigModel> paymentConfigsForStore2Order = defaultAmwayPaymentModeDao.getSupportedSplitCombinations(
				SalesApplication.POS, AmwayBusinessNature.AMWAYBUSINESSNATURE_1, defaultStore2Order, selectedPayModes);

		Assert.assertNotNull(paymentConfigsForStore2Order);

		// Validate Sales Application based Payment Modes
		final Set<SalesApplication> salesApplications = new HashSet(paymentConfigsForStore2Order.size(), 1);
		paymentConfigsForStore2Order.forEach(amwayPaymentConfig -> salesApplications.add(amwayPaymentConfig.getChannel()));
		Assert.assertTrue(1 == salesApplications.size());
		Assert.assertEquals(SalesApplication.POS, salesApplications.iterator().next());
	}

	@Test
	public void testGetSupportedSplitCombinationsByBusinessNature()
	{
		final List<AmwayPaymentConfigModel> paymentConfigsForStore2Order = defaultAmwayPaymentModeDao.getSupportedSplitCombinations(
				SalesApplication.POS, AmwayBusinessNature.AMWAYBUSINESSNATURE_1, defaultStore2Order, selectedPayModes);

		Assert.assertNotNull(paymentConfigsForStore2Order);

		// Validate Sales Application based Payment Modes
		final Set<AmwayBusinessNature> businessNatureList = new HashSet(paymentConfigsForStore2Order.size(), 1);
		paymentConfigsForStore2Order.forEach(amwayPaymentConfig -> businessNatureList.add(amwayPaymentConfig.getBusinessNature()));
		Assert.assertTrue(1 == businessNatureList.size());
		Assert.assertEquals(AmwayBusinessNature.AMWAYBUSINESSNATURE_1, businessNatureList.iterator().next());
	}

	@Test
	public void testGetSupportedSplitCombinationsByBaseStore()
	{
		final List<AmwayPaymentConfigModel> paymentConfigsForStore2Order = defaultAmwayPaymentModeDao.getSupportedSplitCombinations(
				SalesApplication.POS, AmwayBusinessNature.AMWAYBUSINESSNATURE_1, defaultStore2Order, selectedPayModes);

		Assert.assertNotNull(paymentConfigsForStore2Order);

		// Validate Sales Application based Payment Modes
		final Set<BaseStoreModel> baseStoreSet = new HashSet(paymentConfigsForStore2Order.size(), 1);
		paymentConfigsForStore2Order.forEach(amwayPaymentConfig -> baseStoreSet.add(amwayPaymentConfig.getBaseStore()));
		Assert.assertTrue(1 == baseStoreSet.size());
		Assert.assertEquals(STORE2_UID, baseStoreSet.iterator().next().getUid());
	}

}
