package com.amway.apac.serviceability.services.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;
import com.amway.apac.serviceability.strategy.AmwayApacWarehouseServiceabilitySelectionStrategy;


/**
 * Default implementation for the warehouse serviceability service
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacWarehouseServiceabilityServiceImpl implements AmwayApacWarehouseServiceabilityService
{

	private AmwayApacWarehouseServiceabilitySelectionStrategy amwayApacWarehouseServiceabilitySelectionStrategy;

	@Override
	public WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite)
	{
		return getAmwayApacWarehouseServiceabilitySelectionStrategy().getServiceableWareHouse(postalCode, baseSite);
	}

	@Override
	public Boolean isPostalCodeServiceable(final String postalCode, final BaseSiteModel baseSite, final RegionModel region)
	{
		return getAmwayApacWarehouseServiceabilitySelectionStrategy().isPostalCodeServiceable(postalCode, baseSite, region);
	}

	@Override
	public List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite)
	{

		return getAmwayApacWarehouseServiceabilitySelectionStrategy().getRegionsForPostalCode(postalCode, baseSite);
	}

	@Override
	public Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode)
	{
		return getAmwayApacWarehouseServiceabilitySelectionStrategy().isPostalCodeServiceableForCurrentBaseSite(postalCode);
	}


	/**
	 * @return the amwayApacWarehouseServiceabilitySelectionStrategy
	 */
	public AmwayApacWarehouseServiceabilitySelectionStrategy getAmwayApacWarehouseServiceabilitySelectionStrategy()
	{
		return amwayApacWarehouseServiceabilitySelectionStrategy;
	}

	/**
	 * @param amwayApacWarehouseServiceabilitySelectionStrategy
	 *           the amwayApacWarehouseServiceabilitySelectionStrategy to set
	 */
	@Required
	public void setAmwayApacWarehouseServiceabilitySelectionStrategy(
			final AmwayApacWarehouseServiceabilitySelectionStrategy amwayApacWarehouseServiceabilitySelectionStrategy)
	{
		this.amwayApacWarehouseServiceabilitySelectionStrategy = amwayApacWarehouseServiceabilitySelectionStrategy;
	}

}
