/**
 *
 */
package com.amway.core.account.restrictions.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.CartModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayAccountModel;


@UnitTest
public class AccountSponsorRestrictionTest
{
	@InjectMocks
	private final AccountSponsorRestriction accountSponsorRestriction = new AccountSponsorRestriction();
	private CartModel cartModel;
	private AmwayAccountModel accountModel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		cartModel = Mockito.mock(CartModel.class);
		accountModel = Mockito.mock(AmwayAccountModel.class);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountSponsorRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluate()
	{
		Assert.assertFalse(accountSponsorRestriction.evaluate(cartModel, accountModel));
	}

}
