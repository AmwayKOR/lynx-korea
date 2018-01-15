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
 * @author Ashish Sabal
 *
 */
public interface AmwayApacProductService extends ProductService
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

	/**
	 * @param splitOmsCode
	 * @param omscodevalidationparams
	 * @return
	 */
	boolean validateOmsCode(String[] splitOmsCode, int[] omscodevalidationparams);

	/**
	 * Returns the Available Purchasable Quantity.
	 *
	 * @param userId
	 * @param productCode
	 * @return
	 */
	int getUsedQuantityForPrelaunch(String userId, String productCode, final BaseStoreModel store);

	/**
	 * Updates the {@link AmwayUserPromotionCountModel} for given product, quantity and account.
	 *
	 * @param productCodeToCount
	 * @param amwayAccountCode
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
	 * @param product
	 * @param bundled
	 * @return
	 */
	boolean checkKitProductByType(ProductModel product, AmwayKitProductType bundled);
}
