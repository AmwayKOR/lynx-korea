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
import com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel;
import com.amway.apac.serviceability.strategy.AmwayApacWarehouseServiceabilitySelectionStrategy;


/**
 * Implementation for {@link AmwayApacWarehouseServiceabilitySelectionStrategy} for serviceablity enquiry on complete
 * postal code
 * 
 * @author Shubham Goyal
 */
public class AmwayApacPostcodeWarehouseServiceabilitySelectionStrategy
		implements AmwayApacWarehouseServiceabilitySelectionStrategy
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
		final List<AmwayPostcodeWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
				.getWarehouseServiceabilityList(postalCode, baseSite);
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
		final List<AmwayPostcodeWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
				.getServiceableWarehouseListForPostalCodeRegion(postalCode, baseSite, region);
		return Boolean.valueOf(CollectionUtils.isNotEmpty(searchResult));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite)
	{
		final List<RegionModel> regionList = new ArrayList<>();
		final List<AmwayPostcodeWarehouseServiceabilityModel> amwayWarehouseServiceabilityList = getAmwayApacWarehouseServiceabilityDao()
				.getWarehouseServiceabilityList(postalCode, baseSite);
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
		final List<AmwayPostcodeWarehouseServiceabilityModel> searchResult = getAmwayApacWarehouseServiceabilityDao()
				.getWarehouseServiceabilityList(postalCode, getBaseSiteService().getCurrentBaseSite());
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
