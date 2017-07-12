/**
 *
 */
package com.amway.core.cms.services.evaluator.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@UnitTest
public class CheckServiceKitEvaluatorUnitTest
{
	@InjectMocks
	private final CheckServiceKitEvaluator checkServiceKitEvaluator = new CheckServiceKitEvaluator();
	private AbstractOrderEntryModel entryModel1, entryModel2, entryModel3;
	private ProductModel product1, product2, product3;
	private CartModel cart1, cart2;

	private static final String PRODUCT1_CODE = "100566";
	private static final String PRODUCT2_CODE = "100368";
	private static final String PRODUCT3_CODE = "SVR0005";
	private static final String SERVICE_KIT_CODE = "SVR0005";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		checkServiceKitEvaluator.setServiceKitCode(SERVICE_KIT_CODE);
		product1 = Mockito.mock(ProductModel.class);
		given(product1.getCode()).willReturn(PRODUCT1_CODE);
		product2 = Mockito.mock(ProductModel.class);
		given(product2.getCode()).willReturn(PRODUCT2_CODE);
		product3 = Mockito.mock(ProductModel.class);
		given(product3.getCode()).willReturn(PRODUCT3_CODE);

		entryModel1 = Mockito.mock(AbstractOrderEntryModel.class);
		given(entryModel1.getProduct()).willReturn(product1);
		entryModel2 = Mockito.mock(AbstractOrderEntryModel.class);
		given(entryModel2.getProduct()).willReturn(product2);
		entryModel3 = Mockito.mock(AbstractOrderEntryModel.class);
		given(entryModel3.getProduct()).willReturn(product3);

		//		entries = Mockito.mock(ArrayList.class);

		cart1 = Mockito.mock(CartModel.class);
		given(cart1.getEntries()).willReturn(Arrays.asList(entryModel1, entryModel2, entryModel3));

		cart2 = Mockito.mock(CartModel.class);
		given(cart2.getEntries()).willReturn(Arrays.asList(entryModel1, entryModel2));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.cms.services.evaluator.impl.CheckServiceKitEvaluator#evaluate(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	@Test
	public void testEvaluateHavingServiceKit()
	{
		Assert.assertTrue(checkServiceKitEvaluator.evaluate(cart1));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.cms.services.evaluator.impl.CheckServiceKitEvaluator#evaluate(de.hybris.platform.core.model.order.CartModel)}
	 * .
	 */
	@Test
	public void testEvaluateCartHaveNotServiceKit()
	{
		Assert.assertFalse(checkServiceKitEvaluator.evaluate(cart2));
	}
}
