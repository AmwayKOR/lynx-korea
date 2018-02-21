/**
 *
 */
package com.amway.core.varaintproduct.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.amway.core.varaintproduct.service.impl.AmwayVariantProductServiceImpl;


public class AmwayVariantProductServiceImplUnitTest
{
	@InjectMocks
	AmwayVariantProductServiceImpl amwayVariantProductService = new AmwayVariantProductServiceImpl();

	private ProductModel product;
	private VariantProductModel variant1;
	private VariantProductModel variant2;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		product = mock(ProductModel.class);
		variant1 = mock(VariantProductModel.class);
		variant2 = mock(VariantProductModel.class);
		variant1.setSequenceId(Long.valueOf(208291));
	}

	@Test
	public void testNullDefaultVariant()
	{
		Assert.assertNull(amwayVariantProductService.getDefaultVariantProduct(product));
	}

	@Test
	public void testGetDefaultVariant()
	{
		final Collection<VariantProductModel> variants = new ArrayList<>();
		variants.add(variant1);
		product.setVariants(variants);
		given(product.getVariants()).willReturn(variants);
		Assert.assertEquals(amwayVariantProductService.getDefaultVariantProduct(product), variant1);
	}

	@Test
	public void testGetDefaultVariantWithoutSequenceId()
	{
		final List<VariantProductModel> variants = new ArrayList<>();
		variants.add(variant2);
		product.setVariants(variants);
		Assert.assertNull(amwayVariantProductService.getDefaultVariantProduct(product));
	}

	@Test
	public void testSingleVariant()
	{
		final List<VariantProductModel> variants = new ArrayList<>();
		variants.add(variant1);
		product.setVariants(variants);
		given(product.getVariants()).willReturn(variants);
		Assert.assertTrue(product.getVariants().size() == 1);
	}

	@Test
	public void testMultipleVariant()
	{
		final List<VariantProductModel> variants = new ArrayList<>();
		variants.add(variant1);
		variants.add(variant2);
		product.setVariants(variants);
		Assert.assertFalse(product.getVariants().size() == 1);
	}

}
