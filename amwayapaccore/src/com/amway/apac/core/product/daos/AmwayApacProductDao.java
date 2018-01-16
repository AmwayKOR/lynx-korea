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
	 * Gets the all amway payment option from alias code.
	 *
	 * @param aliasCode
	 *           the alias code
	 * @param catalogVersion
	 *           the catalog version
	 * @return list of all payment option available for Alias Code
	 * @throws IllegalArgumentException
	 *            the illegal argument exception
	 */
	List<AmwayPaymentOptionModel> getAllAmwayPaymentOptionFromAliasCode(final String aliasCode,
			final CatalogVersionModel catalogVersion);

	/**
	 * Gets active amway payment option from alias code.
	 *
	 * @param aliasCode
	 *           the alias code
	 * @param catalogVersion
	 *           the catalog version
	 * @return payment option for Alias Code
	 * @throws IllegalArgumentException
	 *            the illegal argument exception
	 */
	List<AmwayPaymentOptionModel> getAmwayPaymentOptionFromAliasCode(final String aliasCode,
			final CatalogVersionModel catalogVersion);

	/**
	 * Gets the promotion rule count by user and product.
	 *
	 * @param userId
	 *           the user id
	 * @param productCodes
	 *           the product codes
	 * @param promotionCode
	 *           the promotion code
	 * @param store
	 *           the store
	 * @return List<AmwayUserPromotionCountModel>
	 * @throws IllegalArgumentException
	 *            the illegal argument exception
	 */
	List<AmwayUserPromotionCountModel> getPromotionRuleCountByUserAndProduct(final String userId, final List<String> productCodes,
			final String promotionCode, final BaseStoreModel store);
}
