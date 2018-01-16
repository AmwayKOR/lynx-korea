package com.amway.apac.core.product.daos;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.product.daos.ProductDao;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.model.AmwayUserPromotionCountModel;


/**
 * DAO containing all product related APIs
 * 
 * @author Ashish Sabal
 */
public interface AmwayApacProductDao extends ProductDao
{

	/**
	 * @param aliasCode
	 * @param catalogVersion
	 * @return list of all payment option available for Alias Code
	 */
	List<AmwayPaymentOptionModel> getAllAmwayPaymentOptionFromAliasCode(final String aliasCode,
			final CatalogVersionModel catalogVersion);

	/**
	 * @param aliasCode
	 * @param catalogVersion
	 * @return payment option for Alias Code
	 */
	List<AmwayPaymentOptionModel> getAmwayPaymentOptionFromAliasCode(final String aliasCode, final CatalogVersionModel catalogVersion);

	/**
	 * @param userId
	 * @param productCodes
	 * @param promotionCode
	 * @return List<AmwayUserPromotionCountModel>
	 */
	List<AmwayUserPromotionCountModel> getPromotionRuleCountByUserAndProduct(final String userId, final List<String> productCodes,
			final String promotionCode, final BaseStoreModel store);
}
