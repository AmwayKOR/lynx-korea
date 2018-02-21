package com.amway.core.returns.dao;

import de.hybris.platform.returns.dao.ReturnRequestDao;
import de.hybris.platform.returns.model.ReplacementOrderModel;
import de.hybris.platform.returns.model.ReturnRequestModel;


/**
 * Interface for Amway return request.
 */
public interface AmwayReturnRequestDao extends ReturnRequestDao
{
	/**
	 * Method to return corresponding return request for a replacement order
	 *
	 * @param replacementOrderModel
	 * @return ReturnRequestModel
	 */
	ReturnRequestModel getReturnRequest(ReplacementOrderModel replacementOrderModel);
}
