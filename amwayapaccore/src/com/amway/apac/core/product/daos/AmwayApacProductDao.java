package com.amway.apac.core.product.daos;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.product.daos.ProductDao;

import java.util.List;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.model.AmwayUserPromotionCountModel;


/**
 *
 * @author Ashish Sabal
 */
public interface AmwayApacProductDao extends ProductDao
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

	/**
	 * @param userId
	 * @param productCodes
	 * @param promotionCode
	 * @return List<AmwayUserPromotionCountModel>
	 */
	List<AmwayUserPromotionCountModel> getPromotionRuleCountByUserAndProduct(final String userId, final List<String> productCodes,
			final String promotionCode);
}
