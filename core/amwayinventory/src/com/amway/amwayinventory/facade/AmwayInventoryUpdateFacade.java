package com.amway.amwayinventory.facade;

import com.amway.amwayinventory.data.AmwayInventoryUpdateRequest;
import com.amway.amwayinventory.data.AmwayInventoryUpdateResponse;


/**
 * Facade for working with stock levels on inventory updates.
 */
public interface AmwayInventoryUpdateFacade
{
	/**
	 * Performing inventory delta adjustments.
	 *
	 * @param request
	 * 		with adjustment data
	 * @return response with status code
	 */
	AmwayInventoryUpdateResponse process(AmwayInventoryUpdateRequest request);
}
