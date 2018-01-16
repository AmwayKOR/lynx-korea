/**
 *
 */
package com.amway.apac.core.order.consignment.service;

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
public class DefaultAmwayApacConsignmentServiceIT extends ServicelayerTransactionalTest
{

	@Resource(name = "defaultAmwayApacConsignmentService")
	private AmwayApacConsignmentService amwayApacConsignmentService;

	@Resource(name = "commerceOrderDao")
	private CommerceOrderDao orderDao;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testConsignmentService.impex", "UTF-8");
	}

	@Test
	public void testCreateConsignments() throws ConsignmentCreationException
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(OrderModel.CODE, "testOrder1");
		final List<OrderModel> orderList = orderDao.find(parameters);
		final OrderModel order = orderList.get(0);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		Assert.assertEquals(1, consignments.size());
	}

	@Test
	public void testCreateConsignmentsWithBackOrder() throws ConsignmentCreationException
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(OrderModel.CODE, "testOrder2");
		final List<OrderModel> orderList = orderDao.find(parameters);
		final OrderModel order = orderList.get(0);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		Assert.assertEquals(2, consignments.size());
	}

	@Test(expected = ConsignmentCreationException.class)
	public void testCreateConsignmentsWithNoWarehouse() throws ConsignmentCreationException
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(OrderModel.CODE, "testOrder3");
		final List<OrderModel> orderList = orderDao.find(parameters);
		final OrderModel order = orderList.get(0);
		amwayApacConsignmentService.createConsignments(order);
	}

	@Test
	public void testCreateConsignmentsForShippingDate() throws ConsignmentCreationException
	{
		final Map<String,Object> parameters=new HashMap<>();
		parameters.put(OrderModel.CODE, "testOrder1");
		final List<OrderModel> orderList=orderDao.find(parameters);
		final OrderModel order=orderList.get(0);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment=consignments.get(0);
		Assert.assertNotNull(consignment.getShippingDate());
	}

	@Test
	public void testCreateConsignmentsForDeliveryMode() throws ConsignmentCreationException
	{
		final Map<String,Object> parameters=new HashMap<>();
		parameters.put(OrderModel.CODE, "testOrder1");
		final List<OrderModel> orderList=orderDao.find(parameters);
		final OrderModel order=orderList.get(0);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment=consignments.get(0);
		Assert.assertNotNull(consignment.getDeliveryMode());
		Assert.assertEquals(order.getDeliveryMode(),consignment.getDeliveryMode());
	}

	@Test
	public void testCreateConsignmentsForDeliveryPointOfService() throws ConsignmentCreationException
	{
		final Map<String,Object> parameters=new HashMap<>();
		parameters.put(OrderModel.CODE, "testOrder4");
		final List<OrderModel> orderList=orderDao.find(parameters);
		final OrderModel order=orderList.get(0);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment=consignments.get(0);
		Assert.assertNotNull(consignment.getDeliveryMode());
		Assert.assertEquals(order.getDeliveryMode(),consignment.getDeliveryMode());
	}

	@Test
	public void testCreateConsignmentsForWarehouse() throws ConsignmentCreationException
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(OrderModel.CODE, "testOrder4");
		final List<OrderModel> orderList = orderDao.find(parameters);
		final OrderModel order = orderList.get(0);
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		final ConsignmentModel consignment = consignments.get(0);
		Assert.assertNotNull(consignment.getWarehouse());
	}

}
