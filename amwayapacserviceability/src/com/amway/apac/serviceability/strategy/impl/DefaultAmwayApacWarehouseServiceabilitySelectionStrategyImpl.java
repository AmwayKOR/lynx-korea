package com.amway.apac.serviceability.strategy.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.daos.AmwayApacWarehouseServiceabilityDao;
import com.amway.apac.serviceability.model.AmwayApacAbstractWarehouseServiceabilityModel;
import com.amway.apac.serviceability.strategy.AmwayApacWarehouseServiceabilitySelectionStrategy;


public class DefaultAmwayApacWarehouseServiceabilitySelectionStrategyImpl
		implements AmwayApacWarehouseServiceabilitySelectionStrategy
{

	private AmwayApacWarehouseServiceabilityDao amwayWarehouseServiceabilityDao;
	private BaseSiteService baseSiteService;

	@Override
	public WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite)
	{
		return getAmwayWarehouseServiceabilityDao().getServiceableWarehouse(postalCode, baseSite);
	}

	@Override
	public Boolean isPostalCodeServiceable(final String postalCode, final BaseSiteModel baseSite, final RegionModel region)
	{
		final WarehouseModel warehouseModel = getAmwayWarehouseServiceabilityDao()
				.getServiceableWarehouseForPostalCodeRegion(postalCode, baseSite, region);
		return Boolean.valueOf(null != warehouseModel);
	}

	@Override
	public List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite)
	{
		final List<RegionModel> regionList = new ArrayList<>();

		final List<? extends AmwayApacAbstractWarehouseServiceabilityModel> amwayWarehouseServiceabilityList = getAmwayWarehouseServiceabilityDao()
				.getWarehouseServiceabilityList(postalCode, baseSite);
		for (final AmwayApacAbstractWarehouseServiceabilityModel warehouseServiceability : amwayWarehouseServiceabilityList)
		{
			regionList.add(warehouseServiceability.getRegion());
		}
		return regionList;
	}

	@Override
	public Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode)
	{
		final List<RegionModel> regionList = getRegionsForPostalCode(postalCode, getBaseSiteService().getCurrentBaseSite());
		final WarehouseModel warehouseModel = getAmwayWarehouseServiceabilityDao()
				.getServiceableWarehouseForPostalCodeRegion(postalCode, getBaseSiteService().getCurrentBaseSite(), regionList.get(0));

		return Boolean.valueOf(null != warehouseModel);
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
	 * @return the amwayWarehouseServiceabilityDao
	 */
	public AmwayApacWarehouseServiceabilityDao getAmwayWarehouseServiceabilityDao()
	{
		return amwayWarehouseServiceabilityDao;
	}

	/**
	 * @param amwayWarehouseServiceabilityDao
	 *           the amwayWarehouseServiceabilityDao to set
	 */
	public void setAmwayWarehouseServiceabilityDao(final AmwayApacWarehouseServiceabilityDao amwayWarehouseServiceabilityDao)
	{
		this.amwayWarehouseServiceabilityDao = amwayWarehouseServiceabilityDao;
	}
}