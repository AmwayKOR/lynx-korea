package com.amway.apac.core.product.services;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Map;

import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.model.AmwayUserPromotionCountModel;
import com.amway.core.enums.AmwayKitProductType;


/**
 * Service layer for all product related APIs
 *
 * @author Ashish Sabal
 *
 */
public interface AmwayApacProductService extends ProductService
{

	/**
	 * Returns all payment options available for Alias Code.
	 *
	 * @param aliasCode
	 *           Alias Code
	 * @param catalogVersion
	 *           Catalog Version
	 * 
	 * @return all payment option models for Alias Code
	 * @throws IllegalArgumentException
	 *            if aliasCode or catalogVersion is null.
	 */
	AmwayPaymentOptionModel getAllPaymentOptionForAliasCode(final String aliasCode, final CatalogVersionModel catalogVersion);

	/**
	 * Returns all active payment options for approved products
	 *
	 * @param aliasCode
	 *           Alias Code
	 * @param catalogVersion
	 *           Catalog Version
	 *
	 * @return payment option for Alias Code
	 * @throws IllegalArgumentException
	 *            if aliasCode or catalogVersion is null.
	 */
	AmwayPaymentOptionModel getPaymentOptionForAliasCode(final String aliasCode, final CatalogVersionModel catalogVersion);

	/**
	 * Checks if PIF is currently active for product
	 *
	 * @param productModel
	 *           Product
	 *
	 * @return boolean for payment option availability
	 * @throws IllegalArgumentException
	 *            if productModel is null.
	 */
	boolean checkIfPIFIsActive(final ProductModel productModel);

	/**
	 * Checks for Alias Code and catalog params to be not empty
	 *
	 * @param splitAliasCode
	 *           Split Alias Code
	 * @param aliasParams
	 *           Alias Params
	 * @return Checks for Alias Code and catalog params not empty
	 *
	 * @throws IllegalArgumentException
	 *            if aliasParams is null.
	 */
	boolean validateAliasCode(final String[] splitAliasCode, final int[] aliasParams);

	/**
	 * Returns the Available Purchasable Quantity.
	 *
	 * @param userId
	 *           User ID for which pre-launch used quantity needed
	 * @param productCode
	 *           Product code for which pre-launch used quantity needed
	 *
	 * @return the Available Purchasable Quantity.
	 * @throws IllegalArgumentException
	 *            if userId, productCode or store is null.
	 */
	int getUsedQuantityForPrelaunch(final String userId, final String productCode, final BaseStoreModel store);

	/**
	 * Updates the {@link AmwayUserPromotionCountModel} for given product, quantity and account.
	 *
	 * @param productCodeToCount
	 *           Map of Pre-launch product codes and their corresponding ordered quantity.
	 * @param order
	 *           Order for which {@link AmwayUserPromotionCountModel} need to be updated.
	 *
	 * @throws IllegalArgumentException
	 *            if productCodeToCount or order is null.
	 */
	void updatePreLaunchProductCount(final Map<String, Integer> productCodeToCount, final AbstractOrderModel order);

	/**
	 * Returns a map of PreLauch products and their ordered quantity in a given order.
	 *
	 * @param orderModel
	 *           Order for which map fetched
	 *
	 * @return Map of Pre-launch product codes and their corresponding ordered quantity.
	 * @throws IllegalArgumentException
	 *            if orderModel is null.
	 */
	Map<String, Integer> getPreLaunchConfigProducts(final AbstractOrderModel orderModel);

	/**
	 * Checks for product if it is a kit product of given type.
	 *
	 * @param product
	 *           Product need to be checked
	 * @param kitProductType
	 *           Kit Product Type
	 *
	 * @return checks for product if it is a kit product of given type
	 * @throws IllegalArgumentException
	 *            if product or kitProductType is null.
	 */
	boolean checkKitProductByType(final ProductModel product, final AmwayKitProductType kitProductType);
}
