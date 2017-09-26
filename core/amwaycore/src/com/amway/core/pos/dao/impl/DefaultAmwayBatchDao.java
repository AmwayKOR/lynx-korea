/*
 *
 */
package com.amway.core.pos.dao.impl;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public AmwayBatchModel updateBatch(final AmwayBatchModel batchModel, final Double balance, final UserModel closedBy)
	{

		batchModel.setDateOfBatchClosed(new Date());
		batchModel.setEndingBalance(balance);

		batchModel.setClosedBy(closedBy);
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

	@Override
	public List<AmwayBatchModel> getOpenBatches(final BaseStoreModel baseStore)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("BaseStoreModel.AFFILIATENUMBER", baseStore.getAffiliateNumber());

		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {ab:").append(AmwayBatchModel.PK).append("} FROM {").append(AmwayBatchModel._TYPECODE)
            .append(" AS ab")
            .append(" JOIN ").append(AmwayTerminalModel._TYPECODE).append(" AS at ON {at.").append(AmwayTerminalModel.PK).append("}={ab.").append(AmwayBatchModel.TERMINAL).append("}")
            .append(" JOIN ").append(PointOfServiceModel._TYPECODE).append(" AS pos ON {pos.").append(PointOfServiceModel.PK).append("}={at.").append(AmwayTerminalModel.POINTOFSERVICE).append("}")
            .append(" JOIN ").append(BaseStoreModel._TYPECODE).append(" AS bs ON {bs.").append(BaseStoreModel.PK).append("}={pos.").append(PointOfServiceModel.BASESTORE).append("} }")
            .append(" WHERE {bs.").append(BaseStoreModel.AFFILIATENUMBER)
				.append("}=?BaseStoreModel.AFFILIATENUMBER AND {").append(AmwayBatchModel.DATEOFBATCHCLOSED).append("} IS NULL");

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

	@Override
	public Double getAccuredBalanceByBatchAndTxnType(final String batchId, final List<String> paymentModes)
	{
		final Map queryParams = new HashMap(2);

		queryParams.put(AmwayBatchModel.BATCHNO, batchId);
		queryParams.put(PaymentModeModel.CODE + "s", paymentModes);

		final StringBuilder queryString = new StringBuilder(100);

		queryString.append("SELECT SUM({pt.").append(PaymentTransactionModel.PLANNEDAMOUNT).append("}) AS AMOUNT FROM {")//TODO: it must be Cash received - cash disbursed
				.append(OrderModel._TYPECODE).append(" AS o ").append(" JOIN ").append(AmwayBatchModel._TYPECODE)
				.append(" AS ab ON {ab.").append(AmwayBatchModel.PK).append("}={o.").append(OrderModel.BATCH).append("}")
				.append(" JOIN ").append(PaymentTransactionModel._TYPECODE).append(" AS pt ON {pt.")
				.append(PaymentTransactionModel.ORDER).append("}={o.").append(OrderModel.PK).append("}")
				.append(PaymentModeModel._TYPECODE).append(" AS pm ON {pm.").append(PaymentModeModel.PK).append("}={pt.")
				.append(PaymentTransactionModel.PAYMENTMODE).append("}").append(" } ").append(" WHERE {ab.")
				.append(AmwayBatchModel.BATCHNO).append("} = ?").append(AmwayBatchModel.BATCHNO).append(" AND {pm.")
				.append(PaymentModeModel.CODE).append("} IN (?").append(PaymentModeModel.CODE).append("s)");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(queryParams);
		query.setResultClassList(Arrays.asList(Double.class));
		final SearchResult<Double> result = this.getFlexibleSearchService().search(query);
		final Double totalAmount = result.getResult().get(0);
		return ((totalAmount == null) ? Double.valueOf(0.00D) : totalAmount);
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
