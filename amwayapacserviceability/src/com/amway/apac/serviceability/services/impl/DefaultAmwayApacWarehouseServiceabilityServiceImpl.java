package com.amway.apac.serviceability.services.impl;

import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.BASESITE_STRING;
import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.REGION_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import static org.springframework.util.Assert.hasText;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;
import com.amway.apac.serviceability.strategy.AmwayApacWarehouseServiceabilitySelectionStrategy;


/**
 * Default implementation for the {@link AmwayApacWarehouseServiceabilityService}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacWarehouseServiceabilityServiceImpl implements AmwayApacWarehouseServiceabilityService
{

	private AmwayApacWarehouseServiceabilitySelectionStrategy amwayApacWarehouseServiceabilitySelectionStrategy;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite)
	{
		hasText(postalCode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);

		return getAmwayApacWarehouseServiceabilitySelectionStrategy().getServiceableWareHouse(postalCode, baseSite);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPostalCodeServiceable(final String postalCode, final BaseSiteModel baseSite, final RegionModel region)
	{
		hasText(postalCode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);
		validateParameterNotNullStandardMessage(REGION_STRING, region);

		return getAmwayApacWarehouseServiceabilitySelectionStrategy().isPostalCodeServiceable(postalCode, baseSite, region);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionModel> getRegionsForPostalCode(final String postalCode, final BaseSiteModel baseSite)
	{
		hasText(postalCode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);

		return getAmwayApacWarehouseServiceabilitySelectionStrategy().getRegionsForPostalCode(postalCode, baseSite);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPostalCodeServiceableForCurrentBaseSite(final String postalCode)
	{
		hasText(postalCode);

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
