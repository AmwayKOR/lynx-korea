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
public class AccountBusinessNatureChangeRestrictionTest
{
	private CartModel cartModel;
	private AmwayAccountModel accountModel;
	@InjectMocks
	private final AccountBusinessNatureChangeRestriction accountBusinessNatureChangeRestriction = new AccountBusinessNatureChangeRestriction();


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
	 * {@link com.amway.core.account.restrictions.impl.AccountBusinessNatureChangeRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluate()
	{
		final boolean isChanged = accountBusinessNatureChangeRestriction.evaluate(cartModel, accountModel);
		Assert.assertFalse(isChanged);
	}

}
