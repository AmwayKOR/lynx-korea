package com.amway.core.pos.service.impl;

import com.amway.core.model.AmwayBatchModel;
import com.amway.core.pos.dao.AmwayBatchDao;
import com.amway.core.pos.dao.AmwayTerminalDao;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.storelocator.pos.PointOfServiceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultAmwayPOSServiceTest {

    private static final String BATCH = "test-batch";
    private static final Long BATCH_ORDERS = 4L;
    private static final Long BATCH_ITEMS = 20L;
    private static final Long BATCH_CUSTOMERS = 3L;


    @InjectMocks
    DefaultAmwayPOSService amwayPOSService = new DefaultAmwayPOSService();
    @Mock
    private AmwayBatchDao batchDao;
    @Mock
    private AmwayTerminalDao terminalDao;
    @Mock
    private UserService userService;
    @Mock
    private PointOfServiceService pointOfServiceService;
    @Mock
    private AmwayBatchModel amwayBatchModel;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        given(batchDao.getBatch(BATCH)).willReturn(Arrays.asList(amwayBatchModel));
    }

    @Test
    public void testGetOrdersCountWhenBatchExists()
    {
        when(batchDao.getOrdersCount(amwayBatchModel)).thenReturn(BATCH_ORDERS);

        Long batchOrderCount = amwayPOSService.getOrdersCount(BATCH);

        Assert.assertEquals(BATCH_ORDERS, batchOrderCount);
    }

    @Test(expected = AmbiguousIdentifierException.class)
    public void testGetOrdersCountWhenMoreThatBatchExistsForTheSameBatchId()
    {
        given(batchDao.getBatch(BATCH)).willReturn(Arrays.asList(amwayBatchModel, amwayBatchModel));
        when(batchDao.getOrdersCount(amwayBatchModel)).thenReturn(BATCH_ORDERS);

        Long batchOrderCount = amwayPOSService.getOrdersCount(BATCH);

        Assert.assertEquals(BATCH_ORDERS, batchOrderCount);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testGetOrdersCountWhenBatchIsNULL()
    {
        given(batchDao.getBatch(BATCH)).willReturn(null);
        when(batchDao.getOrdersCount(amwayBatchModel)).thenReturn(BATCH_ORDERS);

        Long batchOrderCount = amwayPOSService.getOrdersCount(BATCH);

        Assert.assertEquals(BATCH_ORDERS, batchOrderCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOrdersCountWhenBatchIdIsNULL()
    {
        amwayPOSService.getOrdersCount(null);
    }



    @Test
    public void testGetCustomersCountWhenBatchExists()
    {
        when(batchDao.getCustomersCount(amwayBatchModel)).thenReturn(BATCH_CUSTOMERS);

        Long batchCustomersCount = amwayPOSService.getCustomersCount(BATCH);

        Assert.assertEquals(BATCH_CUSTOMERS, batchCustomersCount);
    }

    @Test(expected = AmbiguousIdentifierException.class)
    public void testGetCustomersCountWhenMoreThatBatchExistsForTheSameBatchId()
    {
        given(batchDao.getBatch(BATCH)).willReturn(Arrays.asList(amwayBatchModel, amwayBatchModel));
        when(batchDao.getCustomersCount(amwayBatchModel)).thenReturn(BATCH_CUSTOMERS);

        Long batchCustomersCount = amwayPOSService.getCustomersCount(BATCH);

        Assert.assertEquals(BATCH_CUSTOMERS, batchCustomersCount);
    }


    @Test(expected = UnknownIdentifierException.class)
    public void testGetCustomersCountWhenBatchIsNULL()
    {
        given(batchDao.getBatch(BATCH)).willReturn(null);
        when(batchDao.getCustomersCount(amwayBatchModel)).thenReturn(BATCH_CUSTOMERS);

        Long batchCustomersCount = amwayPOSService.getCustomersCount(BATCH);

        Assert.assertEquals(BATCH_CUSTOMERS, batchCustomersCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCustomersCountWhenBatchIdIsNULL()
    {
        amwayPOSService.getCustomersCount(null);
    }

    @Test
    public void testGetProductsCountWhenBatchExists()
    {
        when(batchDao.getProductsCount(amwayBatchModel)).thenReturn(BATCH_ITEMS);

        Long batchProductCount = amwayPOSService.getProductsCount(BATCH);

        Assert.assertEquals(BATCH_ITEMS, batchProductCount);
    }

    @Test(expected = AmbiguousIdentifierException.class)
    public void testGetProductsCountWhenMoreThatBatchExistsForTheSameBatchId()
    {
        given(batchDao.getBatch(BATCH)).willReturn(Arrays.asList(amwayBatchModel, amwayBatchModel));
        when(batchDao.getProductsCount(amwayBatchModel)).thenReturn(BATCH_ITEMS);

        Long batchProductCount = amwayPOSService.getProductsCount(BATCH);

        Assert.assertEquals(BATCH_ITEMS, batchProductCount);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testGetProductsCountWhenBatchIsNULL()
    {
        given(batchDao.getBatch(BATCH)).willReturn(null);
        when(batchDao.getProductsCount(amwayBatchModel)).thenReturn(BATCH_ITEMS);

        Long batchProductCount = amwayPOSService.getProductsCount(BATCH);

        Assert.assertEquals(BATCH_ITEMS, batchProductCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetProductsCountWhenBatchIdIsNULL()
    {
        amwayPOSService.getProductsCount(null);
    }



}
