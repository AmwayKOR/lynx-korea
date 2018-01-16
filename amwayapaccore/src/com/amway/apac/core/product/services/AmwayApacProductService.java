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
	 * @param catalogVersion
	 * @return all payment option models for Alias Code
	 */
	AmwayPaymentOptionModel getAllPaymentOptionForAliasCode(final String aliasCode, final CatalogVersionModel catalogVersion);

	/**
	 * Returns all active payment options for approved products
	 *
	 * @param aliasCode
	 * @param catalogVersion
	 * @return payment option for Alias Code
	 */
	AmwayPaymentOptionModel getPaymentOptionForAliasCode(final String aliasCode, final CatalogVersionModel catalogVersion);

	/**
	 * Checks if PIF is currently active for product
	 *
	 * @param productModel
	 * @return boolean for payment option availability
	 */
	boolean checkIfPIFIsActive(final ProductModel productModel);

	/**
	 * Checks for Alias Code and catalog params to be not empty
	 *
	 * @param splitAliasCode
	 * @param aliasCodevalidationparams
	 * @return Checks for Alias Code and catalog params not empty
	 */
	boolean validateAliasCode(final String[] splitAliasCode, final int[] aliasCodevalidationparams);

	/**
	 * Returns the Available Purchasable Quantity.
	 *
	 * @param userId
	 * @param productCode
	 * @return the Available Purchasable Quantity.
	 */
	int getUsedQuantityForPrelaunch(final String userId, final String productCode, final BaseStoreModel store);

	/**
	 * Updates the {@link AmwayUserPromotionCountModel} for given product, quantity and account.
	 *
	 * @param productCodeToCount
	 * @param order
	 */
	void updatePreLaunchProductCount(final Map<String, Integer> productCodeToCount, final AbstractOrderModel order);

	/**
	 * Returns a map of PreLauch products and their ordered quantity in a given order.
	 *
	 * @param orderModel
	 * @return products
	 */
	Map<String, Integer> getPreLaunchConfigProducts(final AbstractOrderModel orderModel);

	/**
	 * Checks for product if it is a kit product of given type.
	 *
	 * @param product
	 * @param bundled
	 * @return checks for product if it is a kit product of given type
	 */
	boolean checkKitProductByType(final ProductModel product, final AmwayKitProductType bundled);
}
