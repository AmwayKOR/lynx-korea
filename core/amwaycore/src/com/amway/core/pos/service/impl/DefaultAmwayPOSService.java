package com.amway.core.pos.service.impl;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.model.AmwayBatchModel;
import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.pos.dao.AmwayBatchDao;
import com.amway.core.pos.dao.AmwayTerminalDao;
import com.amway.core.pos.service.AmwayPOSService;
import com.google.common.base.Preconditions;


/**
 */
public class DefaultAmwayPOSService implements AmwayPOSService
{

	private AmwayBatchDao batchDao;
	private AmwayTerminalDao terminalDao;
	private UserService userService;
	private PointOfServiceService pointOfServiceService;

   @Override
   public AmwayBatchModel updateBatch(String batchNo, String balance) {
       AmwayBatchModel batch = getBatch(batchNo);

       return batchDao.updateBatch(batch, Double.parseDouble(balance));
   }

	@Override
	public AmwayBatchModel updateBatch(final String batchNo, final String balance, final UserModel user)
	{
		final AmwayBatchModel batch = getBatch(batchNo);

		final UserModel closedBy = userService.getUserForUID(user.getUid());

		return batchDao.updateBatch(batch, Double.parseDouble(balance), closedBy);
	}

	@Override
	public List<AmwayBatchModel> getBatches(final String userId, final Date startDate, final Date endDate)
	{
		final UserModel userModel = userService.getUserForUID(userId);

		final List<AmwayBatchModel> batchList = batchDao.getBatches(userModel, startDate, endDate);

		return batchList;
	}

	@Override
	public List<AmwayBatchModel> getBatches(final String pickupStore, final String terminal, final Date startDate,
			final Date endDate)
	{


		final AmwayTerminalModel resolvedTerminal = resolveTerminal(pickupStore, terminal);
		Preconditions.checkNotNull(resolvedTerminal, "POS Terminal can't be resolved.");

		final List<AmwayBatchModel> batchList = batchDao.getBatches(resolvedTerminal, startDate, endDate);

		return batchList;
	}

	private AmwayTerminalModel resolveTerminal(final String pickupStore, final String terminal)
	{
		final List<AmwayTerminalModel> terminals = getPOSTerminals(pickupStore);

		AmwayTerminalModel resolvedTerminal = null;
		for (final AmwayTerminalModel terminalModel : terminals)
		{
			if (terminalModel.getId().equalsIgnoreCase(terminal))
			{
				resolvedTerminal = terminalModel;
				break;
			}
		}

		return resolvedTerminal;
	}

	@Override
	public AmwayBatchModel getOpenBatch(final String pickupStore, final String terminal)
	{

		final AmwayTerminalModel resolvedTerminal = resolveTerminal(pickupStore, terminal);
		Preconditions.checkNotNull(resolvedTerminal, "POS Terminal can't be resolved.");

		final List<AmwayBatchModel> batchList = batchDao.getOpenBatches(resolvedTerminal);

		ServicesUtil.validateIfAnyResult(batchList, "No open Batch found for this terminal: " + terminal);
		ServicesUtil.validateIfSingleResult(batchList, "More than one open Batch found for terminal: " + terminal, terminal);

		final AmwayBatchModel resolvedBatch = null;
		//		= CollectionUtils.isEmpty(openBatches) ? null : openBatches.get(0);

		if (Objects.nonNull(resolvedBatch))
		{
			resolvedBatch.setEndingBalance(getAccuredCashBalanceByBatchAndTxnType(resolvedBatch,
					AmwaycoreConstants.POS.BATCH_ENDING_BALANCE_CALCULATION_PAYMENT_MODES));
		}
		return resolvedBatch;

	}


	@Override
	public List<AmwayBatchModel> getOpenBatches(final BaseStoreModel baseStore)
	{
		return batchDao.getOpenBatches(baseStore);
	}

	@Override
	public List<AmwayTerminalModel> getPOSTerminals(final String pickupStore)
	{

		final PointOfServiceModel pointOfServiceModel = getPointOfServiceService().getPointOfServiceForName(pickupStore);
		Preconditions.checkNotNull(pointOfServiceModel, "POS can't be resolved.");

		final List<AmwayTerminalModel> terminals = terminalDao.findTerminals(pointOfServiceModel);
		ServicesUtil.validateIfAnyResult(terminals, "No terminals found for POS: " + pickupStore);

		return terminals;
	}

	@Override
	public AmwayBatchModel createBatch(final String baseSiteId, final String pickupStore, final String terminal,
			final String userId, final String startingBalance)
	{

		final UserModel userModel = userService.getUserForUID(userId);

		final List<AmwayTerminalModel> terminals = getPOSTerminals(pickupStore);

		AmwayTerminalModel resolvedTerminal = null;
		for (final AmwayTerminalModel terminalModel : terminals)
		{
			if (terminalModel.getId().equalsIgnoreCase(terminal))
			{
				resolvedTerminal = terminalModel;
				break;
			}
		}

		Preconditions.checkNotNull(resolvedTerminal, "POS Terminal can't be resolved.");

		final String batchNo = generateBatchNo(baseSiteId, pickupStore, terminal);
		return batchDao.createBatch(resolvedTerminal, batchNo, userModel, Double.parseDouble(startingBalance));
	}

	protected String generateBatchNo(final String baseSiteId, final String storeId, final String terminal)
	{
		final Date currentDate = new Date();
		final SimpleDateFormat formatter = new SimpleDateFormat("ddmmss");

		return baseSiteId + "-" + storeId + "-" + terminal + "-" + formatter.format(currentDate);
	}

	@Override
	public AmwayBatchModel getBatch(final String batch_id)
	{

		final List<AmwayBatchModel> batchList = batchDao.getBatch(batch_id);

		ServicesUtil.validateIfAnyResult(batchList, "No valid Batch found for this batchNo: " + batch_id);
		ServicesUtil.validateIfSingleResult(batchList, "More than one Batch found for this batchNo:" + batch_id, batch_id);

		final AmwayBatchModel resolvedBatch = batchList.get(0);
		resolvedBatch.setEndingBalance(getAccuredCashBalanceByBatchAndTxnType(resolvedBatch,
				AmwaycoreConstants.POS.BATCH_ENDING_BALANCE_CALCULATION_PAYMENT_MODES));
		return resolvedBatch;
	}

	@Override
	public Double getAccuredBalanceByBatchAndTxnType(final String batchId, final List<String> paymentModes)
	{
		return batchDao.getAccuredBalanceByBatchAndTxnType(batchId, paymentModes);
	}

	public Double getAccuredCashBalanceByBatchAndTxnType(final AmwayBatchModel batch, final List<String> paymentModes)
	{

		final Double accurredCashBalance = batchDao.getAccuredBalanceByBatchAndTxnType(batch.getBatchNo(), paymentModes);

		return (accurredCashBalance + batch.getStartingBalance());
	}

	@Override
	public SearchPageData<OrderModel> getOrders(final PageableData pageableData, final String batch_id)
	{
		final AmwayBatchModel batchModel = getBatch(batch_id);
		final SearchPageData<OrderModel> orders = batchDao.getOrders(pageableData, batchModel);
		return orders;
	}

	@Override
	public SearchPageData<OrderModel> getOrders(final PageableData pageableData, final String pickupStore, final Date startDate,
			final Date endDate)
	{

		final List<AmwayTerminalModel> terminals = getPOSTerminals(pickupStore);

		final List<AmwayBatchModel> batchList = batchDao.getBatches(terminals, startDate, endDate);
		final SearchPageData<OrderModel> orders = batchDao.getOrders(pageableData, batchList, startDate, endDate);

		return orders;
	}

	public AmwayTerminalDao getTerminalDao()
	{
		return terminalDao;
	}

	public void setTerminalDao(final AmwayTerminalDao terminalDao)
	{
		this.terminalDao = terminalDao;
	}

	public AmwayBatchDao getBatchDao()
	{
		return batchDao;
	}

	public void setBatchDao(final AmwayBatchDao batchDao)
	{
		this.batchDao = batchDao;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public PointOfServiceService getPointOfServiceService()
	{
		return pointOfServiceService;
	}

	public void setPointOfServiceService(final PointOfServiceService pointOfServiceService)
	{
		this.pointOfServiceService = pointOfServiceService;
	}


}