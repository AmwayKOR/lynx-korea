/**
 *
 */
package com.amway.core.product.unit.service.impl;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.model.AmwayUnitModel;
import com.amway.core.product.unit.service.AmwayUnitService;
import com.amway.core.unit.dao.AmwayUnitDao;


/**
 * Service to retrieve the details of AmwayUnit
 */
public class DefaultAmwayUnitService implements AmwayUnitService
{

	private static final Logger LOG = Logger.getLogger(DefaultAmwayUnitService.class);

	private AmwayUnitDao amwayUnitDao;

	/**
	 * @return amwayUnitDao
	 */
	public AmwayUnitDao getAmwayUnitDao()
	{
		return amwayUnitDao;
	}

	/**
	 * @param amwayUnitDao the amwayUnitDao to set
	 */
	public void setAmwayUnitDao(final AmwayUnitDao amwayUnitDao)
	{
		this.amwayUnitDao = amwayUnitDao;
	}

	/**
	 * To retrieve the details of AmwayUnit.
	 *
	 * @param code
	 */
	@Override
	public AmwayUnitModel getUnitForCode(final String code)
	{
		Assert.notNull(code, "Code cannot be null");
		final AmwayUnitModel unitModel = getAmwayUnitDao().findAmwayUnit(code);
		LOG.debug("AmwayUnitModel for code " + code + " is " + unitModel);
		return unitModel;
	}
}
