package com.amway.apac.serviceability.strategy.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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

	private AmwayApacWarehouseServiceabilityDao amwayApacWarehouseServiceabilityDao;
	private BaseSiteService baseSiteService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite)
	{
		WarehouseModel warehouse = null;
		final String majorPostalCode = postalCode.substring(0, 2);
		final String minorPostalCode = postalCode.substring(2);
		final List<AmwayWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
				.getWarehouseServiceabilityList(minorPostalCode, majorPostalCode, baseSite);
		if (CollectionUtils.isNotEmpty(searchResult))
		{
			warehouse = searchResult.get(0).getWarehouse();
		}

		return warehouse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPostalCodeServiceable(final String postalCode, final BaseSiteModel baseSite, final RegionModel region)
	{
		final String majorPostalCode = postalCode.substring(0, 2);
		final String minorPostalCode = postalCode.substring(2);
		final List<AmwayWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
				.getServiceableWarehouseListForPostalCodeRegion(minorPostalCode, majorPostalCode, baseSite, region);
		return Boolean.valueOf(CollectionUtils.isNotEmpty(searchResult));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite)
	{
		final String majorPostalCode = postalCode.substring(0, 2);
		final String minorPostalCode = postalCode.substring(2);
		final List<RegionModel> regionList = new ArrayList<>();
		final List<? extends AbstractAmwayWarehouseServiceabilityModel> amwayWarehouseServiceabilityList = getAmwayApacWarehouseServiceabilityDao()
				.getWarehouseServiceabilityList(minorPostalCode, majorPostalCode, baseSite);
		for (final AbstractAmwayWarehouseServiceabilityModel warehouseServiceability : amwayWarehouseServiceabilityList)
		{
			regionList.add(warehouseServiceability.getRegion());
		}
		return regionList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode)
	{
		final String majorPostalCode = postalCode.substring(0, 2);
		final String minorPostalCode = postalCode.substring(2);
		final List<AmwayWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
				.getWarehouseServiceabilityList(minorPostalCode, majorPostalCode, getBaseSiteService().getCurrentBaseSite());
		return Boolean.valueOf(CollectionUtils.isNotEmpty(searchResult));
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
	public void setAmwayApacWarehouseServiceabilityDao(AmwayApacWarehouseServiceabilityDao amwayApacWarehouseServiceabilityDao)
	{
		this.amwayApacWarehouseServiceabilityDao = amwayApacWarehouseServiceabilityDao;
	}


}
