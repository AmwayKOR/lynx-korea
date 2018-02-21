package com.amway.core.order.dao;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.daos.PaymentModeDao;

import java.util.HashMap;
import java.util.List;

import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayPaymentConfigModel;


/**
 * Interface for amway payment dao
 */
public interface AmwayPaymentModeDao extends PaymentModeDao
{

	/**
	 * returns the supported split combinations based on channel, custType and order
	 *
	 * @param channel
	 * @param custType
	 * @param order
	 * @param modeList
	 * @return List<AmwayPaymentConfigModel>
	 */
	List<AmwayPaymentConfigModel> getSupportedSplitCombinations(SalesApplication channel, AmwayBusinessNature custType,
			AbstractOrderModel order, HashMap<String, Integer> modeList);
	
	/**
	 * Method to get payment configs for specified code.
	 * 
	 * @param code
	 *           the code
	 * @return the amway payment config model list
	 */
	List<AmwayPaymentConfigModel> getPaymentConfigForCode(final String code, final SalesApplication channel);
}
