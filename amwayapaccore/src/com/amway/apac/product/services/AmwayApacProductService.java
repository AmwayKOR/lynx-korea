/**
 *
 */
package com.amway.apac.product.services;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

import com.amway.apac.core.model.AmwayPaymentOptionModel;


public interface AmwayApacProductService
{

	/**
	 * @param omsCode
	 * @param catalogVersion
	 * @return all payment option models for OMS code
	 */
	AmwayPaymentOptionModel getAllPaymentOptionForOmsCode(String omsCode, CatalogVersionModel catalogVersion);

	/**
	 * @param omsCode
	 * @param catalogVersion
	 * @return payment option for OMS code
	 */
	AmwayPaymentOptionModel getPaymentOptionForOmsCode(String omsCode, CatalogVersionModel catalogVersion);

	/**
	 * @param productModel
	 * @return boolean for payment option availability
	 */
	boolean checkIfPIFIsActive(ProductModel productModel);
}
