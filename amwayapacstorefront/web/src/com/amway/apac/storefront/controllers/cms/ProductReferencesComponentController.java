/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.acceleratorcms.model.components.ProductReferencesComponentModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.facades.product.AmwayApacProductFacade;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS ProductReferencesComponent
 */
@Controller("ProductReferencesComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.ProductReferencesComponent)
public class ProductReferencesComponentController
		extends AbstractAcceleratorCMSComponentController<ProductReferencesComponentModel>
{
	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ProductReferencesComponentController.class);

	/**
	 * AmwayAPAC product facade instance.
	 */
	@Resource(name = "productFacade")
	private AmwayApacProductFacade productFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ProductReferencesComponentModel component)
	{
		final ProductModel currentProduct = getRequestContextData(request).getProduct();
		if (currentProduct != null)
		{
			populateProductReferences(model, component, currentProduct);
		}
		else
		{
			/**
			 * Flow to get Product references from Category.
			 */
			populateCategoryReferences(model, component, getRequestContextData(request).getCategory());
		}

	}

	/**
	 * Populates the reference products for the product given in the model view. If there are no references present in
	 * the given product then reference products are checked in the category of the product, if any.
	 *
	 * @param model
	 *           view model
	 * @param component
	 *           current references component
	 * @param product
	 *           product whose reference products are to be fetched
	 */
	private void populateProductReferences(final Model model, final ProductReferencesComponentModel component,
			final ProductModel product)
	{
		if (null != product)
		{
			LOG.info("Fetching product references for product [{}]", product.getCode());

			final List<ProductData> productReferences = productFacade.getProductReferencesProductDataForCode(product.getCode(),
					component.getProductReferenceTypes(), component.getMaximumNumberProducts());

			if (CollectionUtils.isNotEmpty(productReferences))
			{
				LOG.info("Found {} product references for product [{}]", productReferences.size(), product.getCode());
				model.addAttribute("title", component.getTitle());
				model.addAttribute("productReferences", productReferences);
			}
			else
			{
				final CategoryModel categoryModel = getSuperCategory(product);
				LOG.info(
						"Found no reference products for the product [{}]. Fetching reference products for the category [{}] of the product.",
						product.getCode(), categoryModel.getCode());
				populateCategoryReferences(model, component, categoryModel);
			}
		}
	}

	/**
	 * Populates the best references for the given category in the view model.
	 *
	 * @param model
	 *           view model
	 * @param component
	 *           current references component
	 * @param category
	 *           category whose reference products are to be populated
	 */
	private void populateCategoryReferences(final Model model, final ProductReferencesComponentModel component,
			final CategoryModel category)
	{
		if (category != null)
		{
			LOG.info("Fetching product references for category {}", category.getCode());

			final List<ProductData> productReferences = productFacade.getCategoryProductReferencesForCode(category,
					component.getProductReferenceTypes(), component.getMaximumNumberProducts());
			if (CollectionUtils.isNotEmpty(productReferences))
			{
				LOG.info("Found {} product references for category [{}].", productReferences.size(), category.getCode());

				model.addAttribute("title", component.getTitle());
				model.addAttribute("productReferences", productReferences);
			}
		}
	}

	/**
	 * Iterates over the product hierarchy to get product super category.
	 *
	 * @param product
	 *           product whose super category is needed.
	 * @return first category found in product hierarchy, null if no category is found
	 */
	private CategoryModel getSuperCategory(final ProductModel product)
	{
		CategoryModel category = null;
		if (CollectionUtils.isNotEmpty(product.getSupercategories()))
		{
			category = product.getSupercategories().iterator().next();
		}
		else if ((product instanceof VariantProductModel) && (null != ((VariantProductModel) product).getBaseProduct()))
		{
			category = getSuperCategory(((VariantProductModel) product).getBaseProduct());
		}
		return category;
	}

}