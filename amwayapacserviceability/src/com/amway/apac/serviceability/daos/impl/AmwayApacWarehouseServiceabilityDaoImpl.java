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
import com.amway.apac.serviceability.model.AmwayApacWarehouseServiceabilityModel;


/**
 * Amway APAC Warehouse Serviceability dao
 *
 * @author Shubham Goyal
 */
public class AmwayApacWarehouseServiceabilityDaoImpl implements AmwayApacWarehouseServiceabilityDao
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

		WarehouseModel warehouse = null;
		if (postalcode.length() > 1)
		{
			final String majorPostalCode = postalcode.substring(0, 2);
			final String minorPostalCode = postalcode.substring(2);

			final String queryString = "SELECT " + AmwayApacWarehouseServiceabilityModel.PK + " FROM {"
					+ AmwayApacWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE (({"
					+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} = (?majorPostalCode)" + " AND {"
					+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} = (?minorPostalCode)) OR ({"
					+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} = (?majorPostalCode)" + " AND {"
					+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} is null)) AND {"
					+ AmwayApacWarehouseServiceabilityModel.SITE + "} = (?baseSite)";
			final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
			query.addQueryParameter("majorPostalCode", majorPostalCode);
			query.addQueryParameter("minorPostalCode", minorPostalCode);
			query.addQueryParameter("baseSite", baseSite);
			query.setResultClassList(Arrays.asList(AmwayApacWarehouseServiceabilityModel.class));
			final List<AmwayApacWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
					.<AmwayApacWarehouseServiceabilityModel> search(query).getResult();

			if (CollectionUtils.isNotEmpty(searchResult))
			{
				for (final AmwayApacWarehouseServiceabilityModel AmwayApacWarehouseServiceabilityModel : searchResult)
				{
					if (AmwayApacWarehouseServiceabilityModel.getMajorPostalCode() != null
							&& AmwayApacWarehouseServiceabilityModel.getMinorPostalCode() != null)
					{
						warehouse = AmwayApacWarehouseServiceabilityModel.getWarehouse();
					}
				}
				if (null == warehouse)
				{
					warehouse = searchResult.get(0).getWarehouse();
				}
			}

		}
		if (null == warehouse)
		{
			warehouse = getDefaultServiceableWarehouse(baseSite);
		}
		return warehouse;
	}

	/**
	 * Returns serviceable warehouse null postalcodes and given Basesite.
	 */
	private WarehouseModel getDefaultServiceableWarehouse(final BaseSiteModel baseSite)
	{
		WarehouseModel defaultServiceableWarehouse = null;
		final String queryString = "SELECT " + AmwayApacWarehouseServiceabilityModel.PK + " FROM {"
				+ AmwayApacWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE {"
				+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} is NULL" + " AND {"
				+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} is NULL  AND {"
				+ AmwayApacWarehouseServiceabilityModel.SITE + "} = (?baseSite)";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("baseSite", baseSite);
		query.setResultClassList(Arrays.asList(AmwayApacWarehouseServiceabilityModel.class));
		final List<AmwayApacWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
				.<AmwayApacWarehouseServiceabilityModel> search(query).getResult();

		if (CollectionUtils.isNotEmpty(searchResult))
		{
			defaultServiceableWarehouse = searchResult.get(0).getWarehouse();
		}
		return defaultServiceableWarehouse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WarehouseModel getServiceableWarehouseForPostalCodeRegion(final String postalcode, final BaseSiteModel baseSite,
			final RegionModel region)
	{
		WarehouseModel warehouse = null;
		if (postalcode.length() > 1)
		{
			final String majorPostalCode = postalcode.substring(0, 2);
			final String minorPostalCode = postalcode.substring(2);

			final String queryString = "SELECT " + AmwayApacWarehouseServiceabilityModel.PK + " FROM {"
					+ AmwayApacWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE (({"
					+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} = (?majorPostalCode)" + " AND {"
					+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} = (?minorPostalCode)) OR ({"
					+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} = (?majorPostalCode)" + " AND {"
					+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} is null)) " + "AND {"
					+ AmwayApacWarehouseServiceabilityModel.SITE + "} = (?baseSite) " + "AND {"
					+ AmwayApacWarehouseServiceabilityModel.REGION + "} = (?region)";
			final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
			query.addQueryParameter("majorPostalCode", majorPostalCode);
			query.addQueryParameter("minorPostalCode", minorPostalCode);
			query.addQueryParameter("baseSite", baseSite);
			query.addQueryParameter("region", region);
			query.setResultClassList(Arrays.asList(AmwayApacWarehouseServiceabilityModel.class));
			final List<AmwayApacWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
					.<AmwayApacWarehouseServiceabilityModel> search(query).getResult();

			if (CollectionUtils.isNotEmpty(searchResult))
			{
				for (final AmwayApacWarehouseServiceabilityModel AmwayApacWarehouseServiceabilityModel : searchResult)
				{
					if (AmwayApacWarehouseServiceabilityModel.getMajorPostalCode() != null
							&& AmwayApacWarehouseServiceabilityModel.getMinorPostalCode() != null)
					{
						warehouse = AmwayApacWarehouseServiceabilityModel.getWarehouse();
					}
				}
				if (null == warehouse)
				{
					warehouse = searchResult.get(0).getWarehouse();
				}
			}
		}

		if (null == warehouse)
		{
			warehouse = getDefaultServiceableWarehouse(baseSite);
		}
		return warehouse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayApacAbstractWarehouseServiceabilityModel> getWarehouseServiceabilityList(final String postalcode,
			final BaseSiteModel baseSite)
	{

		final List<AmwayApacAbstractWarehouseServiceabilityModel> warehouseServiceabilityList = new ArrayList<>();
		final String majorPostalCode = postalcode.substring(0, 2);
		final String minorPostalCode = postalcode.substring(2);

		final String queryString = "SELECT " + AmwayApacWarehouseServiceabilityModel.PK + " FROM {"
				+ AmwayApacWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE (({"
				+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} = (?majorPostalCode)" + " AND {"
				+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} = (?minorPostalCode)) OR ({"
				+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} = (?majorPostalCode)" + " AND {"
				+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} is null)) AND {"
				+ AmwayApacWarehouseServiceabilityModel.SITE + "} = (?baseSite)";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("majorPostalCode", majorPostalCode);
		query.addQueryParameter("minorPostalCode", minorPostalCode);
		query.addQueryParameter("baseSite", baseSite);
		query.setResultClassList(Arrays.asList(AmwayApacWarehouseServiceabilityModel.class));
		final List<AmwayApacWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
				.<AmwayApacWarehouseServiceabilityModel> search(query).getResult();

		if (CollectionUtils.isNotEmpty(searchResult))
		{
			warehouseServiceabilityList.addAll(searchResult);
		}
		else
		{
			warehouseServiceabilityList.addAll(getDefaultWarehouseServiceabilityList(baseSite));
		}
		return warehouseServiceabilityList;
	}

	/**
	 * Returns list of warehouse serviceablities for null postalcodes and given Basesite.
	 */
	private List<AmwayApacWarehouseServiceabilityModel> getDefaultWarehouseServiceabilityList(final BaseSiteModel baseSite)
	{
		final List<AmwayApacWarehouseServiceabilityModel> warehouseServiceabilityList = new ArrayList<>();

		final String queryString = "SELECT " + AmwayApacWarehouseServiceabilityModel.PK + " FROM {"
				+ AmwayApacWarehouseServiceabilityModel._TYPECODE + "} " + "WHERE {"
				+ AmwayApacWarehouseServiceabilityModel.MAJORPOSTALCODE + "} is NULL" + " AND {"
				+ AmwayApacWarehouseServiceabilityModel.MINORPOSTALCODE + "} is NULL  AND {"
				+ AmwayApacWarehouseServiceabilityModel.SITE + "} = (?baseSite)";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("baseSite", baseSite);
		query.setResultClassList(Arrays.asList(AmwayApacWarehouseServiceabilityModel.class));
		final List<AmwayApacWarehouseServiceabilityModel> searchResult = getFlexibleSearchService()
				.<AmwayApacWarehouseServiceabilityModel> search(query).getResult();

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
