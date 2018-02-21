/**
 *
 */
package com.amway.core.account.restrictions.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;


/**
 * @author C5240679
 */
public class AccountOrderRestrictionTest
{
	@InjectMocks
	private final AccountOrderRestriction accountOrderRestriction = new AccountOrderRestriction();

	private CartModel cartModel;
	private AmwayAccountModel accountModel;
	private CartModel cartModel1;
	private AmwayAccountModel accountModel1;
	private AmwayAccountModel accountModel2;
	private AmwayBusinessRestrictionModel amwayBusinessRestrictionModel1;
	private AmwayBusinessRestrictionModel amwayBusinessRestrictionModel2;
	private DeliveryModeModel deliveryMode;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		cartModel = new CartModel();
		accountModel = new AmwayAccountModel();

		cartModel1 = new CartModel();
		accountModel1 = new AmwayAccountModel();

		accountModel2 = new AmwayAccountModel();

		amwayBusinessRestrictionModel1 = new AmwayBusinessRestrictionModel();
		amwayBusinessRestrictionModel1.setRestrictionId("BlockOrdering");

		amwayBusinessRestrictionModel2 = new AmwayBusinessRestrictionModel();
		amwayBusinessRestrictionModel2.setRestrictionId("BlockInStorePickUp");

		final Set<AmwayBusinessRestrictionModel> restrictions1 = new HashSet<>();
		restrictions1.add(amwayBusinessRestrictionModel1);
		accountModel.setRestrictions(restrictions1);
		accountModel.setCode("123456");

		final Set<AmwayBusinessRestrictionModel> restrictions2 = new HashSet<>();
		restrictions2.add(amwayBusinessRestrictionModel2);

		deliveryMode = new DeliveryModeModel();
		deliveryMode.setCode("pickup-bopis");
		cartModel.setDeliveryMode(deliveryMode);


		deliveryMode = new DeliveryModeModel();
		deliveryMode.setCode("freeshipping");
		cartModel1.setDeliveryMode(deliveryMode);


		accountModel1.setRestrictions(restrictions2);
		accountModel1.setCode("654321");

		accountModel2.setRestrictions(new HashSet<AmwayBusinessRestrictionModel>());
		accountModel2.setCode("121212");

	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountOrderRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateForBlockOrderingRestriction()
	{
		Assert.assertTrue("Should be true for BlockOrdering Restriction",
				accountOrderRestriction.evaluate(cartModel, accountModel));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountOrderRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateForBlockInStorePickUpRestrictionAndDeliveryModePickupBopis()
	{
		Assert.assertTrue("Should be true for BlockInStorePickUp Restriction And pickup-bopis",
				accountOrderRestriction.evaluate(cartModel, accountModel1));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountOrderRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateForBlockInStorePickUpRestrictionAndDeliveryModeFreeShiping()
	{
		Assert.assertFalse("Should be false for BlockInStorePickUp Restriction And FreeShiping",
				accountOrderRestriction.evaluate(cartModel1, accountModel1));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.account.restrictions.impl.AccountOrderRestriction#evaluate(de.hybris.platform.core.model.order.CartModel, com.amway.core.model.AmwayAccountModel)}
	 * .
	 */
	@Test
	public void testEvaluateWithoutRestriction()
	{
		Assert.assertFalse("Should be false for without Restriction", accountOrderRestriction.evaluate(cartModel, accountModel2));
	}

}
