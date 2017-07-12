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
import java.util.List;

import javax.annotation.Resource;

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
	private static final String ORDER_CODE = "testOrder1";
	private static final String STORE_UID = "defaultstore";

	private OrderModel order;
	private BaseStoreModel store;

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
		store = baseStoreService.getBaseStoreForUid(STORE_UID);
		order = customerAccountService.getOrderForCode(ORDER_CODE, store);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.dao.impl.DefaultAmwayPaymentModeDao#getSupportedSplitCombinations(de.hybris.platform.commerceservices.enums.SalesApplication, com.amway.core.enums.AmwayBusinessNature, de.hybris.platform.core.model.order.AbstractOrderModel, java.util.HashMap)}
	 * .
	 */
	@Test
	public void testGetSupportedSplitCombinations()
	{
		final HashMap<String, Integer> selectedPayModes = new HashMap<String, Integer>();
		selectedPayModes.put(AmwaycoreConstants.PaymentMode.CREDITCARD,
				selectedPayModes.containsKey(AmwaycoreConstants.PaymentMode.CREDITCARD) ?
						new Integer(selectedPayModes.get(AmwaycoreConstants.PaymentMode.CREDITCARD).intValue() + 1) :
						new Integer(1));
		final List<AmwayPaymentConfigModel> paymentConfigs = defaultAmwayPaymentModeDao
				.getSupportedSplitCombinations(SalesApplication.WEB, AmwayBusinessNature.AMWAYBUSINESSNATURE_1, order,
						selectedPayModes);
		Assert.assertNotNull(paymentConfigs);
	}

}
