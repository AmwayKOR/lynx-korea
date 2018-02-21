/**
 *
 */
package com.amway.core.unit.dao;

import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import com.amway.core.model.AmwayUnitModel;


/**
 * Data access to {@link com.amway.core.model.AmwayUnitModel}
 */
public interface AmwayUnitDao extends GenericDao<AmwayUnitModel>
{
	/**
	 * To find the amway unit using the first element.
	 *
	 * @param code
	 * @return AmwayUnitModel
	 */
	AmwayUnitModel findAmwayUnit(final String code);
}
