/**
 *
 */
package com.amway.core.facades.salesreceipt;

import com.amway.facades.data.AmwayPointOfSaleReceiptData;


/**
 * @author jatinarora
 *
 */
public interface AmwayPointOfSaleReceiptFacade
{
	/**
	 * method to provide sales receipt information for the order placed in the system.
	 *
	 * @param orderCode
	 * @return sales receipt data
	 */
	AmwayPointOfSaleReceiptData getSalesReceipt(String orderCode);
}
