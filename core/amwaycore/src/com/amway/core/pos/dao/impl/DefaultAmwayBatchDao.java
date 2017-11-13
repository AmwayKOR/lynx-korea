/*
 *
 */
package com.amway.core.pos.dao.impl;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.*;
import org.apache.log4j.Logger;

import com.amway.core.model.AmwayBatchModel;
import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.pos.dao.AmwayBatchDao;


/**
 * Default implementation
 */
public class DefaultAmwayBatchDao extends DefaultGenericDao<AmwayBatchModel> implements AmwayBatchDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayBatchDao.class);

	private ModelService modelService;

	private PagedFlexibleSearchService pagedFlexibleSearchService;

	public DefaultAmwayBatchDao()
	{
		super(AmwayBatchModel._TYPECODE);
	}

	@Override
	public AmwayBatchModel createBatch(final AmwayTerminalModel terminalModel, final String batchNo, final UserModel employeeModel,
			final Double balance)
	{

		final AmwayBatchModel batchModel = getModelService().create(AmwayBatchModel.class);
		batchModel.setBatchNo(batchNo);
		batchModel.setStartingBalance(balance);
		batchModel.setDateOfBatchOpen(new Date());
		batchModel.setCashier((EmployeeModel) employeeModel);
		batchModel.setEndingBalance(null);
		batchModel.setTerminal(terminalModel);
		getModelService().save(batchModel);
		return batchModel;
	}

	@Override
	public AmwayBatchModel updateBatch(final AmwayBatchModel batchModel, final Double balance)
	{
		batchModel.setDateOfBatchClosed(new Date());
		batchModel.setEndingBalance(balance);

		getModelService().save(batchModel);
		return batchModel;
	}

	@Override
	public List<AmwayBatchModel> getBatches(final UserModel employeeModel, final Date startDate, final Date endDate)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("startDate", startDate);
		attributes.put("endDate", endDate);
		attributes.put("user", employeeModel);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} >= ?startDate AND {");
		queryString.append(AmwayBatchModel.DATEOFBATCHCLOSED).append("} < ?endDate AND {");
		queryString.append(AmwayBatchModel.CASHIER).append("}=?user ");
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayBatchModel> getBatches(final AmwayTerminalModel terminal, final Date startDate, final Date endDate)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("startDate", startDate);
		attributes.put("endDate", endDate);
		attributes.put("terminal", terminal);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} >= ?startDate AND {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} < ?endDate AND {");
		queryString.append(AmwayBatchModel.TERMINAL).append("}=?terminal ");
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayBatchModel> getBatches(final List<AmwayTerminalModel> terminals, final Date startDate, final Date endDate)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("startDate", startDate);
		attributes.put("endDate", endDate);
		attributes.put("terminals", terminals);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} >= ?startDate AND {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} < ?endDate AND {");
		queryString.append(AmwayBatchModel.TERMINAL).append("} IN (?terminals) ");
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayBatchModel> getBatch(final String batch_id)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("batchNo", batch_id);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.BATCHNO).append("}=?batchNo");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();

	}

	@Override
	public List<AmwayBatchModel> getOpenBatches(final AmwayTerminalModel terminal)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("terminal", terminal);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.TERMINAL).append("}=?terminal AND {");
		queryString.append(AmwayBatchModel.ENDINGBALANCE).append("} IS NULL");
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	public SearchPageData<OrderModel> getOrders(final PageableData pageableData, final AmwayBatchModel batchModel)
	{
		final Map queryParams = new HashMap();
		queryParams.put("batchModel", batchModel);

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[]
		{ createSortQueryData("byDate",
				"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} = ?batchModel ORDER BY {creationtime} DESC, {pk}"),
				createSortQueryData("byOrderNumber",
						"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} = ?batchModel ORDER BY {code},{creationtime} DESC, {pk}") });

		return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
	}

	public SearchPageData<OrderModel> getOrders(final PageableData pageableData, final List<AmwayBatchModel> batchList,
			final Date startDate, final Date endDate)
	{
		final Map queryParams = new HashMap();
		queryParams.put("batchList", batchList);

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[]
		{ createSortQueryData("byDate",
				"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} IN (?batchList) ORDER BY {creationtime} DESC, {pk}"),
				createSortQueryData("byOrderNumber",
						"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} IN (?batchList) ORDER BY {code},{creationtime} DESC, {pk}") });

		return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
	}

	protected SortQueryData createSortQueryData(final String sortCode, final String query)
	{
		final SortQueryData result = new SortQueryData();
		result.setSortCode(sortCode);
		result.setQuery(query);
		return result;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public PagedFlexibleSearchService getPagedFlexibleSearchService()
	{
		return pagedFlexibleSearchService;
	}

	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}
}
