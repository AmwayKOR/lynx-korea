/**
 * 
 */
package com.amway.core.pos.dao;

import com.amway.core.model.AmwayBatchModel;
import com.amway.core.model.AmwayTerminalModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Date;
import java.util.List;

/**
 * Data access to {@link AmwayBatchModel}
 */
public interface AmwayBatchDao
{
	public AmwayBatchModel createBatch(AmwayTerminalModel terminalModel, String batchNo, UserModel employeeModel, Double balance);
	public AmwayBatchModel updateBatch(AmwayBatchModel batchModel, Double balance);
	public AmwayBatchModel updateBatch(AmwayBatchModel batchModel, Double balance, UserModel closedBy);
	public List<AmwayBatchModel> getBatches(UserModel employeeModel, Date startDate, Date endDate);
	public List<AmwayBatchModel> getBatches(AmwayTerminalModel terminal, Date startDate, Date endDate);
	public List<AmwayBatchModel> getBatches(List<AmwayTerminalModel> terminals, Date startDate, Date endDate);
	public List<AmwayBatchModel> getBatch(String batch_id);
	public List<AmwayBatchModel> getOpenBatches(AmwayTerminalModel terminal);
	public List<AmwayBatchModel> getOpenBatches(BaseStoreModel baseStore);
	public SearchPageData<OrderModel> getOrders(PageableData pageableData, AmwayBatchModel batchModel);
	public SearchPageData<OrderModel> getOrders(PageableData pageableData, List<AmwayBatchModel> batchList, Date startDate, Date endDate);
	public Double getAccuredBalanceByBatchAndTxnType(String batchId, List<String> paymentModes);
}
