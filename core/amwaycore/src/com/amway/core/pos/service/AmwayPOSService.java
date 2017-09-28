/**
 *
 */
package com.amway.core.pos.service;

import com.amway.core.model.AmwayBatchModel;
import com.amway.core.model.AmwayTerminalModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Date;
import java.util.List;


/**
 */
public interface AmwayPOSService
{
	public AmwayBatchModel updateBatch(String pk, String balance);
	public AmwayBatchModel updateBatch(String pk, String balance, UserModel user);
	public List<AmwayBatchModel> getBatches(String userId, Date startDate, Date endDate);
	public List<AmwayBatchModel> getBatches(String pickupStore, String terminal, Date startDate, Date endDate);
	public AmwayBatchModel getOpenBatch(String pickupStore, String terminal);
	public List<AmwayBatchModel> getOpenBatches(BaseStoreModel baseStore);
	public List<AmwayTerminalModel> getPOSTerminals(String pickupStore);
	public AmwayBatchModel createBatch(String baseSiteId, String pickupStore, String terminal, String user_id, String startingBalance);
	public AmwayBatchModel getBatch(String batch_id);
	public SearchPageData<OrderModel> getOrders(PageableData pageableData, String batch_id);
	public SearchPageData<OrderModel> getOrders(PageableData pageableData, String pickupStore, Date startDate, Date endDate);
	public Double getAccuredBalanceByBatchAndTxnType(String batchId, List<String> paymentModes);
}
