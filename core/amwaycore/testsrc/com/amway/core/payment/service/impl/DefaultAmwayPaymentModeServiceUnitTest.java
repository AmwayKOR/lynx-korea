/**
 *
 */
package com.amway.core.payment.service.impl;

import com.amway.core.account.service.impl.MockCreateAccountBalanceService;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.util.AmwayCustomerHelper;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.CartModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.order.data.AmwayPaymentModeData;


@UnitTest
public class DefaultAmwayPaymentModeServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayPaymentModeService paymentService = new DefaultAmwayPaymentModeService();
	private CartModel cart;
	private AmwayProfileResponseData profileResponse;
	private AccountBalanceData accountBalanceData;
	private List<AccountBalanceData> accountBalanceDataList;
	private AmwayCustomerHelper amwayCustomerHelper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		cart = new CartModel();
		profileResponse = new AmwayProfileResponseData();
		accountBalanceData = new AccountBalanceData();
		accountBalanceDataList = new ArrayList<>();
		amwayCustomerHelper = new AmwayCustomerHelper();

	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService#getSupportedSplitPaymentModes(de.hybris.platform.core.model.order.CartModel, com.amway.core.dms.data.AmwayProfileResponseData)}
	 * .
	 */
	@Test
	public void testGetSupportedSplitPaymentModes()
	{
		final Set<AmwayPaymentModeData> paymentModes = paymentService.getSupportedSplitPaymentModes(cart, profileResponse);
		Assert.assertNotNull(paymentModes);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService#getSupportedPaymentModesCombination(de.hybris.platform.core.model.order.CartModel, com.amway.core.dms.data.AmwayProfileResponseData, boolean)}
	 * .
	 */
	//@Test
	public void testGetSupportedPaymentModesCombinationCartModelAmwayProfileResponseDataBoolean()
	{
		cart.setTotalPrice(50.00);
		accountBalanceData.setBalanceAmount("5.00");
		accountBalanceDataList.add(accountBalanceData);
		profileResponse.setAccountBalance(accountBalanceDataList);
		Mockito.when(amwayCustomerHelper.getARCreditLimit(profileResponse)).thenReturn(BigDecimal.valueOf(50.00));
		final Map<String, List<AmwayPaymentModeData>> paymentModes = paymentService
				.getSupportedPaymentModesCombination(cart, profileResponse, true);
		Assert.assertNotNull(paymentModes);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService#getSupportedPaymentModes(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	@Test
	public void testGetSupportedPaymentModesCartModel()
	{
		Assert.assertNotNull(paymentService.getSupportedPaymentModes(cart));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService#getSupportedPaymentModes(de.hybris.platform.core.model.order.CartModel, com.amway.core.dms.data.AmwayProfileResponseData)}
	 * .
	 */
	@Test
	public void testGetSupportedPaymentModesCartModelAmwayProfileResponseData()
	{
		Assert.assertTrue(paymentService.getSupportedPaymentModes(cart, profileResponse).isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService#isCardAliasExistsInCart(de.hybris.platform.core.model.order.CartModel, java.lang.String)}
	 * .
	 */
	@Test
	public void testIsCardAliasExistsInCart()
	{
		Assert.assertFalse(paymentService.isCardAliasExistsInCart(cart, "cc"));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService#getSupportedPaymentModesCombination(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	@Test
	public void testGetSupportedPaymentModesCombinationCartModel()
	{
		Assert.assertNotNull(paymentService.getSupportedPaymentModesCombination(cart));
	}

}
