package com.amway.apac.serviceability.strategy.impl;

import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.BASESITE_STRING;
import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.REGION_STRING;
import static org.springframework.util.Assert.hasText;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.daos.AmwayApacWarehouseServiceabilityDao;
import com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayWarehouseServiceabilityModel;
import com.amway.apac.serviceability.strategy.AmwayApacWarehouseServiceabilitySelectionStrategy;


/**
 * Default Implementation for {@link AmwayApacWarehouseServiceabilitySelectionStrategy} for serviceablity enquiry on
 * Major and Minor postal codes.
 * 
 * @author Shubham Goyal
 */
public class DefaultAmwayApacWarehouseServiceabilitySelectionStrategy implements AmwayApacWarehouseServiceabilitySelectionStrategy
{

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacWarehouseServiceabilitySelectionStrategy.class);

	private AmwayApacWarehouseServiceabilityDao amwayApacWarehouseServiceabilityDao;
	private BaseSiteService baseSiteService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite)
	{
		hasText(postalCode);
		ServicesUtil.validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);

		WarehouseModel warehouse = null;
		if (postalCode.length() > 3)
		{
			final String majorPostalCode = postalCode.substring(0, 2);
			final String minorPostalCode = postalCode.substring(2);
			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(100).append("Searching serviceable warehouse for majorPostalCode [")
						.append(majorPostalCode).append("], minorPostalCode [").append(minorPostalCode).append("] and basesite [")
						.append(baseSite.getName()).append("].").toString());
			}
			final List<AmwayWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
					.getWarehouseServiceabilityList(minorPostalCode, majorPostalCode, baseSite);
			if (CollectionUtils.isNotEmpty(searchResult))
			{
				warehouse = searchResult.get(0).getWarehouse();
			}
		}

		return warehouse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPostalCodeServiceable(final String postalCode, final BaseSiteModel baseSite, final RegionModel region)
	{
		hasText(postalCode);
		ServicesUtil.validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);
		ServicesUtil.validateParameterNotNullStandardMessage(REGION_STRING, region);
		Boolean serviceable = Boolean.FALSE;
		if (postalCode.length() > 3)
		{
			final String majorPostalCode = postalCode.substring(0, 2);
			final String minorPostalCode = postalCode.substring(2);
			final List<AmwayWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
					.getServiceableWarehouseListForPostalCodeRegion(minorPostalCode, majorPostalCode, baseSite, region);
			serviceable = Boolean.valueOf(CollectionUtils.isNotEmpty(searchResult));
		}
		return serviceable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite)
	{
		hasText(postalCode);
		ServicesUtil.validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);
		final List<RegionModel> regionList = new ArrayList<>();
		if (postalCode.length() > 3)
		{
			final String majorPostalCode = postalCode.substring(0, 2);
			final String minorPostalCode = postalCode.substring(2);
			final List<? extends AbstractAmwayWarehouseServiceabilityModel> amwayWarehouseServiceabilityList = getAmwayApacWarehouseServiceabilityDao()
					.getWarehouseServiceabilityList(minorPostalCode, majorPostalCode, baseSite);
			for (final AbstractAmwayWarehouseServiceabilityModel warehouseServiceability : amwayWarehouseServiceabilityList)
			{
				regionList.add(warehouseServiceability.getRegion());
			}
		}
		return regionList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode)
	{
		hasText(postalCode);
		Boolean serviceable = Boolean.FALSE;
		if (postalCode.length() > 3)
		{
			final String majorPostalCode = postalCode.substring(0, 2);
			final String minorPostalCode = postalCode.substring(2);
			final List<AmwayWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
					.getWarehouseServiceabilityList(minorPostalCode, majorPostalCode, getBaseSiteService().getCurrentBaseSite());
			serviceable = Boolean.valueOf(CollectionUtils.isNotEmpty(searchResult));
		}
		return serviceable;
	}


	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the amwayApacWarehouseServiceabilityDao
	 */
	public AmwayApacWarehouseServiceabilityDao getAmwayApacWarehouseServiceabilityDao()
	{
		return amwayApacWarehouseServiceabilityDao;
	}

	/**
	 * @param amwayApacWarehouseServiceabilityDao
	 *           the amwayApacWarehouseServiceabilityDao to set
	 */
	@Required
	public void setAmwayApacWarehouseServiceabilityDao(
			final AmwayApacWarehouseServiceabilityDao amwayApacWarehouseServiceabilityDao)
	{
		this.amwayApacWarehouseServiceabilityDao = amwayApacWarehouseServiceabilityDao;
	}

}
