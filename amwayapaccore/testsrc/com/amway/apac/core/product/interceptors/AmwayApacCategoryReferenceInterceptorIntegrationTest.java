package com.amway.apac.core.product.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.model.AmwayCategoryProductReferenceModel;


/**
 * @author Shubham Goyal
 */

@IntegrationTest
public class AmwayApacCategoryReferenceInterceptorIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private ModelService modelService;
	@Resource
	private CategoryService categoryService;


	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
	}

	@Test
	public void testGetAmwayAccount()
	{
		final CategoryModel category = categoryService.getCategoryForCode("testCategory0");
		final Date beforeUpdate = category.getModifiedtime();
		final List<AmwayCategoryProductReferenceModel> categoryReferences = (List<AmwayCategoryProductReferenceModel>) category
				.getProductReferences();

		final AmwayCategoryProductReferenceModel referenceModelToBeSaved = categoryReferences.iterator().next();
		referenceModelToBeSaved.setDescription("new description");
		modelService.save(referenceModelToBeSaved);
		modelService.refresh(category);
		final Date afterUpdate = category.getModifiedtime();
		Assert.assertTrue(beforeUpdate.before(afterUpdate));
	}

	@Test
	public void testGetAmwayAccountForReferenceRemoval()
	{
		final CategoryModel category = categoryService.getCategoryForCode("testCategory0");
		final Date beforeUpdate = category.getModifiedtime();
		final List<AmwayCategoryProductReferenceModel> categoryReferences = (List<AmwayCategoryProductReferenceModel>) category
				.getProductReferences();
		final AmwayCategoryProductReferenceModel referenceModelToBeSaved = categoryReferences.iterator().next();
		referenceModelToBeSaved.setDescription("new description");
		modelService.remove(referenceModelToBeSaved);
		modelService.refresh(category);
		final Date afterUpdate = category.getModifiedtime();
		Assert.assertTrue(beforeUpdate.before(afterUpdate));
	}
}
