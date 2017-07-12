/**
 *
 */
package com.amway.core.account.restrictions.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.site.BaseSiteService;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;

import com.amway.core.cms.services.evaluator.impl.CheckServiceKitEvaluator;
import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.util.AmwayDateHelper;


@SuppressWarnings("deprecation")
@Ignore("HE-210 - removing powermock and disabling test")
@UnitTest
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(
//{ Registry.class, AmwayDateHelper.class })
public class AccountExpirationRestrictionTest
{
	@InjectMocks
	private final AccountExpirationRestriction accountExpirationRestriction = new AccountExpirationRestriction();
	private CartModel cartModel;
	private AmwayAccountModel expiredAccountModel;
	private AmwayAccountModel notExpiredAccountModel;
	private AmwayAccountModel nonABOAccountModel;
	private AmwayAccountModel nonABOAccountModel1;
	@Mock
	private BaseSiteService baseSiteService;
	@Mock
	private CheckServiceKitEvaluator lateRenewalKitEvaluator;
	@Mock
	private ApplicationContext context;
	private static final String TIME_ZONE = "Brazil/East";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		// mocking static class methods
		PowerMockito.mockStatic(Registry.class);
		PowerMockito.when(Registry.getApplicationContext()).thenReturn(context);
		PowerMockito.when(context.getBean("baseSiteService")).thenReturn(baseSiteService);
		PowerMockito.mockStatic(AmwayDateHelper.class);
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE));
		PowerMockito.when(AmwayDateHelper.getTimeForSiteTimeZone()).thenReturn(cal.getTime());
		cartModel = new CartModel();
		expiredAccountModel = new AmwayAccountModel();
		cal.add(Calendar.MONDAY, -1);
		expiredAccountModel.setExpiryDate(cal.getTime());
		expiredAccountModel.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);

		notExpiredAccountModel = new AmwayAccountModel();
		cal = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE));
		cal.add(Calendar.MONDAY, 1);
		notExpiredAccountModel.setExpiryDate(cal.getTime());
		notExpiredAccountModel.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_1);

		nonABOAccountModel = new AmwayAccountModel();
		nonABOAccountModel.setExpiryDate(cal.getTime());
		nonABOAccountModel.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_4);

		nonABOAccountModel1 = new AmwayAccountModel();
		nonABOAccountModel1.setExpiryDate(cal.getTime());
		nonABOAccountModel1.setBusinessNature(AmwayBusinessNature.AMWAYBUSINESSNATURE_7);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountExpirationRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateForExpiredAccountWithLateRenewalServiceKit()
	{
		given(Boolean.valueOf(lateRenewalKitEvaluator.evaluate(cartModel))).willReturn(Boolean.TRUE);
		final boolean aBoolean = accountExpirationRestriction.evaluate(cartModel, expiredAccountModel);
		Assert.assertTrue(aBoolean);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountExpirationRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateForExpiredAccountWithoutLateRenewalServiceKit()
	{
		given(Boolean.valueOf(lateRenewalKitEvaluator.evaluate(cartModel))).willReturn(Boolean.FALSE);
		final boolean aBoolean = accountExpirationRestriction.evaluate(cartModel, expiredAccountModel);
		Assert.assertFalse(aBoolean);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountExpirationRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateForNotExpiredAccount()
	{
		final boolean aBoolean = accountExpirationRestriction.evaluate(cartModel, notExpiredAccountModel);
		Assert.assertTrue(aBoolean);
	}


	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountExpirationRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateForNonABOAccount()
	{
		final boolean aBoolean = accountExpirationRestriction.evaluate(cartModel, nonABOAccountModel);
		Assert.assertTrue(aBoolean);

		final boolean bBoolean = accountExpirationRestriction.evaluate(cartModel, nonABOAccountModel1);
		Assert.assertTrue(bBoolean);
	}

}
