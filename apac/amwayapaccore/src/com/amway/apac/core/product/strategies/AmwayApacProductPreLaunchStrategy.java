package com.amway.apac.core.product.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import com.amway.apac.core.product.AmwayPreLaunchResponse;


/**
 * Strategy to evaluate preLaunch status.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacProductPreLaunchStrategy
{
	/**
	 * Returns the preLaunch access status of given product for current user.
	 *
	 * @param product
	 *           Pre-Launch product
	 * @return {@link AmwayPreLaunchResponse} for the passes pre-launch product for current user.
	 */
	AmwayPreLaunchResponse getProductPrelaunchStatusForCurrentUser(final ProductModel product);
}
