package com.amway.core.pos.service.impl;

import com.amway.core.model.AmwayBatchModel;
import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.pos.dao.AmwayBatchDao;
import com.amway.core.pos.dao.AmwayTerminalDao;
import com.amway.core.pos.service.AmwayPOSService;
import com.google.common.base.Preconditions;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 */
public class DefaultAmwayPOSService implements AmwayPOSService {

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
    public List<AmwayBatchModel> getBatches(String userId, Date startDate, Date endDate) {
        final UserModel userModel = userService.getUserForUID(userId);

        List<AmwayBatchModel> batchList = batchDao.getBatches(userModel, startDate, endDate);

        return batchList;
    }

    @Override
    public List<AmwayBatchModel> getBatches(String pickupStore, String terminal, Date startDate, Date endDate) {


        AmwayTerminalModel resolvedTerminal = resolveTerminal(pickupStore, terminal);
        Preconditions.checkNotNull(resolvedTerminal, "POS Terminal can't be resolved.");

        List<AmwayBatchModel> batchList = batchDao.getBatches(resolvedTerminal, startDate, endDate);

        return batchList;
    }

    private AmwayTerminalModel resolveTerminal(String pickupStore, String terminal) {
        final List <AmwayTerminalModel> terminals = getPOSTerminals(pickupStore);

        AmwayTerminalModel resolvedTerminal = null;
        for(AmwayTerminalModel terminalModel:terminals) {
            if (terminalModel.getId().equalsIgnoreCase(terminal)) {
                resolvedTerminal = terminalModel;
                break;
            }
        }

        return resolvedTerminal;
    }

    @Override
    public AmwayBatchModel getOpenBatch(String pickupStore, String terminal) {

        AmwayTerminalModel resolvedTerminal = resolveTerminal(pickupStore, terminal);
        Preconditions.checkNotNull(resolvedTerminal, "POS Terminal can't be resolved.");

        List<AmwayBatchModel> batchList = batchDao.getOpenBatches(resolvedTerminal);

        ServicesUtil.validateIfAnyResult(batchList, "No open Batch found for this terminal: " + terminal);
        ServicesUtil.validateIfSingleResult(batchList, "More than one open Batch found for terminal: " + terminal, terminal) ;

        return batchList.get(0);
    }

    @Override
    public List<AmwayTerminalModel> getPOSTerminals(String pickupStore) {

        final PointOfServiceModel pointOfServiceModel = getPointOfServiceService().getPointOfServiceForName(pickupStore);
        Preconditions.checkNotNull(pointOfServiceModel, "POS can't be resolved.");

        final List <AmwayTerminalModel> terminals = terminalDao.findTerminals(pointOfServiceModel);
        ServicesUtil.validateIfAnyResult(terminals, "No terminals found for POS: " + pickupStore);

        return terminals;
    }

    @Override
    public AmwayBatchModel createBatch(String baseSiteId, String pickupStore, String terminal, String userId, String startingBalance) {

        final UserModel userModel = userService.getUserForUID(userId);

        final List <AmwayTerminalModel> terminals = getPOSTerminals(pickupStore);

        AmwayTerminalModel resolvedTerminal = null;
        for(AmwayTerminalModel terminalModel:terminals) {
            if (terminalModel.getId().equalsIgnoreCase(terminal)) {
                resolvedTerminal = terminalModel;
                break;
            }
        }

        Preconditions.checkNotNull(resolvedTerminal, "POS Terminal can't be resolved.");

        String batchNo = generateBatchNo(baseSiteId, pickupStore, terminal);
        return batchDao.createBatch(resolvedTerminal, batchNo, userModel, Double.parseDouble(startingBalance));
    }

    protected String generateBatchNo(String baseSiteId, String storeId, String terminal) {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddmmss");

        return baseSiteId +"-"+storeId+"-"+terminal+"-"+formatter.format(currentDate);
    }

    @Override
    public AmwayBatchModel getBatch(String batch_id) {

        List<AmwayBatchModel> batchList = batchDao.getBatch(batch_id);

        ServicesUtil.validateIfAnyResult(batchList, "No valid Batch found for this batchNo: " + batch_id);
        ServicesUtil.validateIfSingleResult(batchList, "More than one Batch found for this batchNo:" + batch_id, batch_id) ;

        return batchList.get(0);
    }

    @Override
    public SearchPageData<OrderModel> getOrders(PageableData pageableData, String batch_id) {
        AmwayBatchModel batchModel = getBatch(batch_id);
        SearchPageData<OrderModel> orders = batchDao.getOrders(pageableData, batchModel);
        return orders;
    }

    @Override
    public SearchPageData<OrderModel> getOrders(PageableData pageableData, String pickupStore, Date startDate, Date endDate) {

        final List <AmwayTerminalModel> terminals = getPOSTerminals(pickupStore);

        List<AmwayBatchModel> batchList = batchDao.getBatches(terminals, startDate, endDate);
        SearchPageData<OrderModel> orders = batchDao.getOrders(pageableData, batchList, startDate, endDate);

        return orders;
    }

    public AmwayTerminalDao getTerminalDao() {
        return terminalDao;
    }

    public void setTerminalDao(AmwayTerminalDao terminalDao) {
        this.terminalDao = terminalDao;
    }

    public AmwayBatchDao getBatchDao() {
        return batchDao;
    }

    public void setBatchDao(AmwayBatchDao batchDao) {
        this.batchDao = batchDao;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PointOfServiceService getPointOfServiceService() {
        return pointOfServiceService;
    }

    public void setPointOfServiceService(PointOfServiceService pointOfServiceService) {
        this.pointOfServiceService = pointOfServiceService;
    }
}