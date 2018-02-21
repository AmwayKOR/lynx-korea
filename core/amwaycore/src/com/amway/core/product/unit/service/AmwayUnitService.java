/**
 *
 */
package com.amway.core.product.unit.service;

import com.amway.core.model.AmwayUnitModel;


/**
 * Service to retrieve the details of AmwayUnit
 */
public interface AmwayUnitService
{
	/**
	 * To retrieve the Unit for given code.
	 *
	 * @param code
	 * @return AmwayUnitModel
	 */
	AmwayUnitModel getUnitForCode(String code);
}
