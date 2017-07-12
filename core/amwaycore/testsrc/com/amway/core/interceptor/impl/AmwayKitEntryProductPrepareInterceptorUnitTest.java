/**
 *
 */
package com.amway.core.interceptor.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.model.AmwayKitEntryProductModel;
import com.amway.core.model.AmwayKitProductModel;


@UnitTest
public class AmwayKitEntryProductPrepareInterceptorUnitTest
{
	@InjectMocks
	private final AmwayKitEntryProductPrepareInterceptor interceptor = new AmwayKitEntryProductPrepareInterceptor();
	private AmwayKitEntryProductModel kitEntryProduct;
	private AmwayKitProductModel kitProduct;
	private ProductModel product;
	@Mock
	private InterceptorContext ctx;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		product = Mockito.mock(ProductModel.class);
		given(product.getCode()).willReturn("P0001");

		kitProduct = Mockito.mock(AmwayKitProductModel.class);
		given(kitProduct.getCode()).willReturn("K0001");

		kitEntryProduct = new AmwayKitEntryProductModel();
		kitEntryProduct.setKitProduct(kitProduct);
		kitEntryProduct.setEntry(product);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.interceptor.impl.AmwayKitEntryProductPrepareInterceptor#onPrepare(com.amway.core.model.AmwayKitEntryProductModel, de.hybris.platform.servicelayer.interceptor.InterceptorContext)}
	 * .
	 *
	 * @throws InterceptorException
	 */
	@Test
	public void testOnPrepare() throws InterceptorException
	{
		interceptor.onPrepare(kitEntryProduct, ctx);
		Assert.assertEquals(kitProduct.getCode() + "-" + product.getCode(), kitEntryProduct.getCode());
	}

}
