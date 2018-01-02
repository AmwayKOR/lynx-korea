/**
 *
 */
package com.amway.apac.core.product.daos;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

import com.amway.apac.core.model.AmwayPaymentOptionModel;


public interface AmwayApacProductDao
{

	/**
	 * @param omsCode
	 * @param catalogVersion
	 * @return list of all payment option available for OMS code
	 */
	List<AmwayPaymentOptionModel> getAllAmwayPaymentOptionFromOmsCode(String omsCode, CatalogVersionModel catalogVersion);

	/**
	 * @param omsCode
	 * @param catalogVersion
	 * @return payment option for OMS code
	 */
	List<AmwayPaymentOptionModel> getAmwayPaymentOptionFromOmsCode(String omsCode, CatalogVersionModel catalogVersion);
}
