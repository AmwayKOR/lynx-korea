/*
 *
 */
package com.amway.core.pos.dao.impl;

import com.amway.core.model.AmwayBatchModel;
import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.pos.dao.AmwayBatchDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.util.*;


/**
 * Default implementation
 */
public class DefaultAmwayBatchDao extends DefaultGenericDao<AmwayBatchModel> implements AmwayBatchDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayBatchDao.class);
	
	private static final String BATCH = "batch";

	protected static final String ORDER_COUNT_BY_BATCH = new StringBuilder(100).append("SELECT COUNT( {").append(OrderModel.PK)
			.append("} ) FROM {").append(OrderModel._TYPECODE).append("} WHERE {").append(OrderModel.VERSIONID)
			.append("} IS NULL AND {").append(OrderModel.BATCH).append("} = ?").append(BATCH).toString();
	
	protected static final String CUSTOMER_COUNT_BY_BATCH = new StringBuilder(100).append("SELECT COUNT(DISTINCT( {")
			.append(OrderModel.USER).append("} )) FROM {").append(OrderModel._TYPECODE).append("} WHERE {")
			.append(OrderModel.VERSIONID).append("} IS NULL AND {").append(OrderModel.BATCH).append("} = ?").append(BATCH)
			.toString();

	protected static final String PRODUCT_COUNT_BY_BATCH = new StringBuilder(100).append("SELECT SUM( {oe.")
			.append(OrderEntryModel.QUANTITY).append("} ) FROM {").append(OrderModel._TYPECODE).append(" as o JOIN ")
			.append(OrderEntryModel._TYPECODE).append(" as oe ON { oe.").append(OrderEntryModel.ORDER).append("} = { o.")
			.append(OrderModel.PK).append("}} WHERE { o.").append(OrderModel.VERSIONID).append("} IS NULL AND { o.")
			.append(OrderModel.BATCH).append("} = ?").append(BATCH).toString();

	private ModelService modelService;

	private PagedFlexibleSearchService pagedFlexibleSearchService;

	public DefaultAmwayBatchDao()
	{
		super(AmwayBatchModel._TYPECODE);
	}

	@Override
	public AmwayBatchModel createBatch(AmwayTerminalModel terminalModel, String batchNo, UserModel employeeModel, Double balance) {

		AmwayBatchModel batchModel = getModelService().create(AmwayBatchModel.class);
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
	public AmwayBatchModel updateBatch(AmwayBatchModel batchModel, Double balance) {

		batchModel.setDateOfBatchClosed(new Date());
		batchModel.setEndingBalance(balance);

		getModelService().save(batchModel);
		return batchModel;
	}

	@Override
	public List<AmwayBatchModel> getBatches(UserModel employeeModel, Date startDate, Date endDate) {
		final Map<String, Object> attributes = new HashMap();
		attributes.put("startDate", startDate);
		attributes.put("endDate", endDate);
        attributes.put("user", employeeModel);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} >= ?startDate AND {" );
		queryString.append(AmwayBatchModel.DATEOFBATCHCLOSED).append("} < ?endDate AND {" );
        queryString.append(AmwayBatchModel.CASHIER).append("}=?user " );
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayBatchModel> getBatches(AmwayTerminalModel terminal, Date startDate, Date endDate) {
		final Map<String, Object> attributes = new HashMap();
		attributes.put("startDate", startDate);
		attributes.put("endDate", endDate);
		attributes.put("terminal", terminal);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} >= ?startDate AND {" );
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} < ?endDate AND {" );
		queryString.append(AmwayBatchModel.TERMINAL).append("}=?terminal " );
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayBatchModel> getBatches(List<AmwayTerminalModel> terminals, Date startDate, Date endDate) {
		final Map<String, Object> attributes = new HashMap();
		attributes.put("startDate", startDate);
		attributes.put("endDate", endDate);
		attributes.put("terminals", terminals);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} >= ?startDate AND {" );
		queryString.append(AmwayBatchModel.DATEOFBATCHOPEN).append("} < ?endDate AND {" );
		queryString.append(AmwayBatchModel.TERMINAL).append("} IN (?terminals) " );
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public List<AmwayBatchModel> getBatch(String batch_id) {
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
	public List<AmwayBatchModel> getOpenBatches(AmwayTerminalModel terminal) {
		final Map<String, Object> attributes = new HashMap();
		attributes.put("terminal", terminal);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab}");
		queryString.append(" WHERE {");
		queryString.append(AmwayBatchModel.TERMINAL).append("}=?terminal AND {" );
		queryString.append(AmwayBatchModel.ENDINGBALANCE).append("} IS NULL" );
		queryString.append(" ORDER BY {ab:").append(AmwayBatchModel.DATEOFBATCHOPEN).append("} DESC");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBatchModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	@Override
	public SearchPageData<OrderModel> getOrders(PageableData pageableData, AmwayBatchModel batchModel) {
		final Map queryParams = new HashMap();
		queryParams.put("batchModel", batchModel);

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[] {
				createSortQueryData("byDate", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} = ?batchModel ORDER BY {creationtime} DESC, {pk}"),
				createSortQueryData("byOrderNumber", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} = ?batchModel ORDER BY {code},{creationtime} DESC, {pk}") });

		return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
	}

	@Override
	public SearchPageData<OrderModel> getOrders(PageableData pageableData, List<AmwayBatchModel> batchList, Date startDate, Date endDate) {
		final Map queryParams = new HashMap();
		queryParams.put("batchList", batchList);

		final List<SortQueryData> sortQueries = Arrays.asList(new SortQueryData[] {
				createSortQueryData("byDate", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} IN (?batchList) ORDER BY {creationtime} DESC, {pk}"),
				createSortQueryData("byOrderNumber", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {versionID} IS NULL AND {batch} IN (?batchList) ORDER BY {code},{creationtime} DESC, {pk}") });

		return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
	}
	
	@Override
	public Long getOrdersCount(final AmwayBatchModel batchModel)
	{
		Assert.notNull(batchModel, "Batch model must not be null");
		final FlexibleSearchQuery query = new FlexibleSearchQuery(ORDER_COUNT_BY_BATCH);
		query.getQueryParameters().putAll(Collections.singletonMap(BATCH, batchModel));
		query.setResultClassList(Arrays.asList(Long.class));

		LOG.debug("Query to get order count by batchID : "+query+"[Parameters] : "+query.getQueryParameters());

		final SearchResult<Long> result = this.getFlexibleSearchService().search(query);
		return result.getResult().get(0);
	}

	@Override
	public Long getCustomersCount(final AmwayBatchModel batchModel)
	{
		Assert.notNull(batchModel, "Batch model must not be null");
		final FlexibleSearchQuery query = new FlexibleSearchQuery(CUSTOMER_COUNT_BY_BATCH);
		query.getQueryParameters().putAll(Collections.singletonMap(BATCH, batchModel));
		query.setResultClassList(Arrays.asList(Long.class));

		LOG.debug("Query to get customers count by batchID : "+query+"[Parameters] : "+query.getQueryParameters());

		final SearchResult<Long> result = this.getFlexibleSearchService().search(query);
		return result.getResult().get(0);
	}

	@Override
	public Long getProductsCount(final AmwayBatchModel batchModel)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(PRODUCT_COUNT_BY_BATCH);
		query.getQueryParameters().putAll(Collections.singletonMap(BATCH, batchModel));
		query.setResultClassList(Arrays.asList(Long.class));

		LOG.debug("Query to get item count by batchID : "+query+"[Parameters] : "+query.getQueryParameters());

		final SearchResult<Long> result = this.getFlexibleSearchService().search(query);
		final Long productCount = result.getResult().get(0);
		return ((productCount == null) ? Long.valueOf(0) : productCount);

	}

	protected SortQueryData createSortQueryData(final String sortCode, final String query)
	{
		final SortQueryData result = new SortQueryData();
		result.setSortCode(sortCode);
		result.setQuery(query);
		return result;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public PagedFlexibleSearchService getPagedFlexibleSearchService() {
		return pagedFlexibleSearchService;
	}

	public void setPagedFlexibleSearchService(PagedFlexibleSearchService pagedFlexibleSearchService) {
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}
}
