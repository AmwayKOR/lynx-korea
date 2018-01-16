package com.amway.apac.serviceability.services.impl;

import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.BASESITE_STRING;
import static com.amway.apac.serviceability.constants.AmwayapacserviceabilityConstants.REGION_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import static org.springframework.util.Assert.hasText;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.daos.AmwayApacWarehouseServiceabilityDao;
import com.amway.apac.serviceability.model.AbstractAmwayWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayPostcodeWarehouseServiceabilityModel;
import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;


/**
 * Default implementation for the {@link AmwayApacWarehouseServiceabilityService}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacWarehouseServiceabilityServiceImpl implements AmwayApacWarehouseServiceabilityService
{

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacWarehouseServiceabilityServiceImpl.class);

	private AmwayApacWarehouseServiceabilityDao amwayApacWarehouseServiceabilityDao;
	private BaseSiteService baseSiteService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WarehouseModel getServiceableWareHouse(final String postalCode, final BaseSiteModel baseSite)
	{
		hasText(postalCode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);

		WarehouseModel warehouse = null;
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching serviceable warehouse for postalcode [").append(postalCode)
					.append("] and basesite [").append(baseSite.getName()).append("].").toString());
		}
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
		hasText(postalCode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);
		validateParameterNotNullStandardMessage(REGION_STRING, region);

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
		hasText(postalCode);
		validateParameterNotNullStandardMessage(BASESITE_STRING, baseSite);

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
		hasText(postalCode);

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
	public void setAmwayApacWarehouseServiceabilityDao(
			final AmwayApacWarehouseServiceabilityDao amwayApacWarehouseServiceabilityDao)
	{
		this.amwayApacWarehouseServiceabilityDao = amwayApacWarehouseServiceabilityDao;
	}

}
