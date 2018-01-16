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
	 * Gets all amway payment options for oms code.
	 *
	 * @param omsCode
	 *           the oms code
	 * @param catalogVersion
	 *           the catalog version
	 * @return list of all payment option available for OMS code
	 * @throws IllegalArgumentException
	 *            the illegal argument exception
	 */
	List<AmwayPaymentOptionModel> getAllAmwayPaymentOptionFromOmsCode(final String omsCode,
			final CatalogVersionModel catalogVersion);

	/**
	 * Gets active amway payment options for approved products.
	 *
	 * @param omsCode
	 *           the oms code
	 * @param catalogVersion
	 *           the catalog version
	 * @return payment option for OMS code
	 * @throws IllegalArgumentException
	 *            the illegal argument exception
	 */
	List<AmwayPaymentOptionModel> getAmwayPaymentOptionFromOmsCode(final String omsCode, final CatalogVersionModel catalogVersion);

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
