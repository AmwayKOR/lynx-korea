/**
 *
 */
package com.amway.core.stock.service;

import de.hybris.platform.commerceservices.stock.CommerceStockService;

import de.hybris.platform.core.model.order.AbstractOrderModel;



/**
 * Interface for Amway commerce stock service.
 */
public interface AmwayCommerceStockService extends CommerceStockService
{

	public void reserve(final AbstractOrderModel abstractOrderModel);

}