package com.amway.apac.serviceability.daos.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.daos.AmwayApacWarehouseServiceabilityDao;
import com.amway.apac.serviceability.model.AmwayApacAbstractWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayApacPostcodeWarehouseServiceabilityModel;
import com.amway.apac.serviceability.model.AmwayApacWarehouseServiceabilityModel;


/**
 * Amway APAC Warehouse Serviceability dao
 *
 * @author Shubham Goyal
 */
public class AmwayApacPostcodeWarehouseServiceabilityDaoImpl implements AmwayApacWarehouseServiceabilityDao
{

	private FlexibleSearchService flexibleSearchService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WarehouseModel getServiceableWarehouse(final String postalcode, final BaseSiteModel baseSite)
	{
		ServicesUtil.validateParameterNotNull(postalcode, "postalcode");
		ServicesUtil.validateParameterNotNull(baseSite, "baseSite");

		WarehouseModel serviceableWarehouse = null;

		final String queryString = "SELECT " + AmwayApacPostcodeWarehouseServiceabilityModel.PK + " FROM {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel.POSTALCODE + "} = (?postalcode)" + "AND {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel.SITE + "} = (?baseSite)";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("postalCode", postalcode);
		query.addQueryParameter("baseSite", baseSite);
		query.setResultClassList(Arrays.asList(AmwayApacPostcodeWarehouseServiceabilityModel.class));
		final List<AmwayApacPostcodeWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
				.<AmwayApacPostcodeWarehouseServiceabilityModel> search(query).getResult();

		if (CollectionUtils.isNotEmpty(searchResult))
		{
			for (final AmwayApacPostcodeWarehouseServiceabilityModel amwayWarehouseServiceabilityModel : searchResult)
			{
				if (amwayWarehouseServiceabilityModel.getPostalCode() != null)
				{
					serviceableWarehouse = amwayWarehouseServiceabilityModel.getWarehouse();
				}
			}
			if (null == serviceableWarehouse)
			{
				serviceableWarehouse = searchResult.get(0).getWarehouse();
			}
		}
		return serviceableWarehouse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WarehouseModel getServiceableWarehouseForPostalCodeRegion(final String postalcode, final BaseSiteModel baseSite,
			final RegionModel region)
	{
		WarehouseModel serviceableWarehouse = null;

		final String queryString = "SELECT " + AmwayApacPostcodeWarehouseServiceabilityModel.PK + " FROM {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel.POSTALCODE + "} = (?postalcode)" + "AND {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel.SITE + "} = (?baseSite) " + "AND {"
				+ AmwayApacWarehouseServiceabilityModel.REGION + "} = (?region)";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("postalCode", postalcode);
		query.addQueryParameter("baseSite", baseSite);
		query.addQueryParameter("region", region);
		query.setResultClassList(Arrays.asList(AmwayApacPostcodeWarehouseServiceabilityModel.class));
		final List<AmwayApacPostcodeWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
				.<AmwayApacPostcodeWarehouseServiceabilityModel> search(query).getResult();

		if (CollectionUtils.isNotEmpty(searchResult))
		{
			for (final AmwayApacPostcodeWarehouseServiceabilityModel amwayWarehouseServiceabilityModel : searchResult)
			{
				if (amwayWarehouseServiceabilityModel.getPostalCode() != null)
				{
					serviceableWarehouse = amwayWarehouseServiceabilityModel.getWarehouse();
				}
			}
			if (null == serviceableWarehouse)
			{
				serviceableWarehouse = searchResult.get(0).getWarehouse();
			}
		}
		return serviceableWarehouse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayApacAbstractWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String postalcode,
			final BaseSiteModel baseSite)
	{
		final List<AmwayApacAbstractWarehouseServiceabilityModel> warehouseServiceabilityList = new ArrayList<>();

		final String queryString = "SELECT " + AmwayApacPostcodeWarehouseServiceabilityModel.PK + " FROM {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel.POSTALCODE + "} = (?postalcode)" + "AND {"
				+ AmwayApacPostcodeWarehouseServiceabilityModel.SITE + "} = (?baseSite)";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("postalCode", postalcode);
		query.addQueryParameter("baseSite", baseSite);
		query.setResultClassList(Arrays.asList(AmwayApacPostcodeWarehouseServiceabilityModel.class));
		final List<AmwayApacPostcodeWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
				.<AmwayApacPostcodeWarehouseServiceabilityModel> search(query).getResult();

		if (CollectionUtils.isNotEmpty(searchResult))
		{
			warehouseServiceabilityList.addAll(searchResult);
		}
		return warehouseServiceabilityList;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
