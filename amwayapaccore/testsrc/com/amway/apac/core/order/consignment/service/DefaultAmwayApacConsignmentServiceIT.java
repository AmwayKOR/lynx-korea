/**
 *
 */
package com.amway.apac.core.order.consignment.service;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.order.dao.CommerceOrderDao;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




/**
 * Integration test class to test {@DefaultAmwayApacConsignmentService}
 */
@IntegrationTest
public class DefaultAmwayApacConsignmentServiceIT extends ServicelayerTransactionalTest
{

	@Resource(name = "defaultAmwayApacConsignmentService")
	private AmwayApacConsignmentService amwayApacConsignmentService;

	@Resource(name = "commerceOrderDao")
	private CommerceOrderDao commerceOrderDao;

	private static final String TEST_ORDER_1 = "testOrder1";
	private static final String TEST_ORDER_2 = "testOrder2";
	private static final String TEST_ORDER_3 = "testOrder3";
	private static final String TEST_ORDER_4 = "testOrder4";

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testConsignmentService.impex", "UTF-8");
	}

	@Test
	public void testCreateConsignments() throws ConsignmentCreationException
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(OrderModel.CODE, TEST_ORDER_1);
		final List<OrderModel> orderList = commerceOrderDao.find(parameters);
		final OrderModel order = orderList.get(0);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		Assert.assertEquals(1, consignments.size());
	}

	@Test
	public void testCreateConsignmentsWithBackOrder() throws ConsignmentCreationException
	{
		final OrderModel order = getOrderByCode(TEST_ORDER_2);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		Assert.assertEquals(2, consignments.size());
	}

	@Test(expected = ConsignmentCreationException.class)
	public void testCreateConsignmentsWithNoWarehouse() throws ConsignmentCreationException
	{
		final OrderModel order = getOrderByCode(TEST_ORDER_3);
		amwayApacConsignmentService.createConsignments(order);
	}

	@Test
	public void testCreateConsignmentsForShippingDate() throws ConsignmentCreationException
	{
		final OrderModel order = getOrderByCode(TEST_ORDER_1);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment = consignments.get(0);
		Assert.assertNotNull(consignment.getShippingDate());
	}

	@Test
	public void testCreateConsignmentsForDeliveryMode() throws ConsignmentCreationException
	{
		final OrderModel order = getOrderByCode(TEST_ORDER_1);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment = consignments.get(0);
		Assert.assertNotNull(consignment.getDeliveryMode());
		Assert.assertEquals(order.getDeliveryMode(), consignment.getDeliveryMode());
	}

	@Test
	public void testCreateConsignmentsForDeliveryPointOfService() throws ConsignmentCreationException
	{
		final OrderModel order = getOrderByCode(TEST_ORDER_4);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment = consignments.get(0);
		Assert.assertNotNull(consignment.getDeliveryMode());
		Assert.assertEquals(order.getDeliveryMode(), consignment.getDeliveryMode());
	}


	@Test
	public void testCreateConsignmentsForWarehouse() throws ConsignmentCreationException
	{
		final OrderModel order = getOrderByCode(TEST_ORDER_4);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment = consignments.get(0);
		Assert.assertNotNull(consignment.getWarehouse());
	}

	/**
	 * @return order
	 */
	private OrderModel getOrderByCode(final String orderCode)
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(OrderModel.CODE, orderCode);
		final List<OrderModel> orderList = commerceOrderDao.find(parameters);
		return orderList.get(0);
	}
}
