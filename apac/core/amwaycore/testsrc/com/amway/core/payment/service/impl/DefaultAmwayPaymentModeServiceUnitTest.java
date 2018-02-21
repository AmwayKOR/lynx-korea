/**
 *
 */
package com.amway.core.payment.service.impl;

import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.platform.commerceservices.enums.SalesApplication;
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

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.daos.PaymentModeDao;
import de.hybris.platform.order.impl.DefaultPaymentModeService;
import de.hybris.platform.servicelayer.session.MockSessionService;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.model.AmwayAccountModel;


@UnitTest
public class DefaultAmwayPaymentModeServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayPaymentModeService paymentService = new DefaultAmwayPaymentModeService();
	private SessionService sessionService;
	private AmwayAccountModel account;
	private CartModel cart;
	private AmwayProfileResponseData profileResponse;
	private AccountBalanceData accountBalanceData;
	private List<AccountBalanceData> accountBalanceDataList;
	private AmwayCustomerHelper amwayCustomerHelper;

	@Mock
	private PaymentModeDao paymentModeDao;

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

		sessionService = Mockito.spy(new MockSessionService());
		paymentService.setSessionService(sessionService);
		account = new AmwayAccountModel();
		account.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);
		cart.setAccount(account);
		paymentService.setPaymentModeDao(paymentModeDao);


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
		// use Jmeter to test this... getPaymentModes in POS JMX
	}

	/**
	 * Test method for
	 * {@link com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService#getSupportedPaymentModes(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	//@Test
	public void testGetSupportedPaymentModesCartModel()
	{
		// use Jmeter to test this... getPaymentModes in POS JMX
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
	//@Test
	// use Jmeter to test this... getPaymentModes in POS JMX
	public void testGetSupportedPaymentModesCombinationCartModel()

	{
		// use Jmeter to test this... getPaymentModes in POS JMX
	}

}
