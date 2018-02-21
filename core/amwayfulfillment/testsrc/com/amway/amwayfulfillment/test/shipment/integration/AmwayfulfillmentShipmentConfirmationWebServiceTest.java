package com.amway.amwayfulfillment.test.shipment.integration;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.enums.DeliveryStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.oauth2.constants.OAuth2Constants;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.webservicescommons.jalo.OAuthClientDetails;
import de.hybris.platform.webservicescommons.model.OAuthClientDetailsModel;
import de.hybris.platform.webservicescommons.testsupport.client.WsRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.client.WsSecuredRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.springframework.http.HttpStatus;

import com.amway.amwayfulfillment.constants.AmwayfulfillmentConstants;
import com.amway.amwayfulfillment.order.PackageEntry;
import com.amway.amwayfulfillment.order.SerialNumber;
import com.amway.amwayfulfillment.order.ShippingPackage;
import com.amway.amwayfulfillment.ws.dto.ShippingResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Integration test for shipment confirmation endpoint.
 * To run it, initialize junit teanant and execute 'ant integrationtests -Dtestclasses.extensions amwayfulfillment'
 */
@NeedsEmbeddedServer(webExtensions = { AmwayfulfillmentConstants.EXTENSIONNAME, OAuth2Constants.EXTENSIONNAME})
@IntegrationTest
public class AmwayfulfillmentShipmentConfirmationWebServiceTest extends ServicelayerTest
{

	private static final String OAUTH_CLIENT_ID = "oauth2-client";
	private static final String FORBIDDEN_CLIENT_ID = "mobile_android";
	private static final String OAUTH_CLIENT_PASS = "1234";
	private static final String WEBSERVICE_PATH = "%s/orders/%s/shipping";
	private static final String DEFAULT_STORE = "lynxswe";
	private static final String WRONG_STORE = "lynxswe123";
	private static final String DEFAULT_WH = "warehouse_sweden_custom_2";
	private static final String DEFAULT_ORDER = "700000000";
	private static final String PRODUCT_1_CODE = "119802";
	private static final String PRODUCT_1_WRONG_CODE = "111";
	private static final long PRODUCT_1_QTY = 2;
	private static final String PRODUCT_2_CODE = "118766";
	private static final String PRODUCT_2_WRONG_CODE = "222";
	private static final long PRODUCT_2_QTY = 1;

	private WsSecuredRequestBuilder wsSecuredRequestBuilder1;
	private WsSecuredRequestBuilder wsSecuredRequestBuilder2;
	private WsSecuredRequestBuilder wsSecuredWrongTokenRequestBuilder;
	private WsRequestBuilder wsInsecureRequestBuilder;

	private boolean createWrongProduct = false;
	private boolean createWrongWH = false;
	private boolean createWrongOrderDeliveryStatus = false;
	private boolean createEmptyBodyParameter = false;
	private boolean createWrongShippedQty = false;

	private static final Logger LOG = Logger.getLogger(AmwayfulfillmentShipmentConfirmationWebServiceTest.class);

	@Resource(name = "modelService")
	ModelService modelService;

	@Resource(name = "userService")
	UserService userService;

	@Resource(name = "commonI18NService")
	CommonI18NService commonI18NService;

	@Resource(name = "baseStoreService")
	BaseStoreService baseStoreService;

	@Resource(name = "flexibleSearchService")
	FlexibleSearchService flexibleSearchService;

	@Before
	public void setUp() throws Exception
	{

		importCsv("/amwayfulfillment/test/democustomer-data.impex", "utf-8");
		LOG.info("imported democustomer-data.impex ");
		// checking imported clients
		final OAuthClientDetailsModel oauth2_client = flexibleSearchService.<OAuthClientDetailsModel> search("SELECT {PK} FROM {OAuthClientDetails} WHERE {clientId}='oauth2-client'").getResult().get(0);
		Assert.assertNotNull(oauth2_client);


		wsSecuredRequestBuilder1 = new WsSecuredRequestBuilder()//
				.extensionName(AmwayfulfillmentConstants.EXTENSIONNAME)//
				.client(OAUTH_CLIENT_ID, OAUTH_CLIENT_PASS)//
				.grantClientCredentials();

		wsSecuredRequestBuilder2 = new WsSecuredRequestBuilder()//
				.extensionName(AmwayfulfillmentConstants.EXTENSIONNAME)//
				.client(OAUTH_CLIENT_ID, OAUTH_CLIENT_PASS)//
				.grantClientCredentials();

		wsSecuredWrongTokenRequestBuilder = new WsSecuredRequestBuilder()//
				.extensionName(AmwayfulfillmentConstants.EXTENSIONNAME)//
				.client(FORBIDDEN_CLIENT_ID, OAUTH_CLIENT_PASS)//
				.grantClientCredentials();

		wsInsecureRequestBuilder = new WsRequestBuilder()//
				.extensionName(AmwayfulfillmentConstants.EXTENSIONNAME);
	}

	@Test
	public void testUnauthorizedAccess()
	{
		Response putResponse = wsInsecureRequestBuilder.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, DEFAULT_ORDER))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), putResponse.getStatus());
	}

	@Test
	public void testWrongTokenAccess()
	{
		Response putResponse = wsSecuredWrongTokenRequestBuilder.path(
				String.format(WEBSERVICE_PATH, DEFAULT_STORE, DEFAULT_ORDER))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));
		Assert.assertEquals(HttpStatus.UNAUTHORIZED.value(), putResponse.getStatus());
	}

	@Ignore @Test
	public void testSuccessScenario()
	{
		OrderModel testOrder = createTestOrder();
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, testOrder.getCode()))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.OK.value(), putResponse.getStatus());
	}

	@Ignore @Test
	public void testIgnoredConsignmentCreated()
	{
		createWrongOrderDeliveryStatus = true;
		OrderModel testOrder = createTestOrder();
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, testOrder.getCode()))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), putResponse.getStatus());
		ShippingResponse shippingResponse = putResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.IGNORED_CONSIGNMENT_ERROR);
		createWrongOrderDeliveryStatus = false;
	}

	@Ignore @Test
	public void testThisConsignmentAlreadyCreated()
	{
		OrderModel testOrder = createTestOrder();
		String orderCode = testOrder.getCode();
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, orderCode))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.OK.value(), putResponse.getStatus());

		Response newResponse = wsSecuredRequestBuilder2.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, orderCode))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), newResponse.getStatus());
		ShippingResponse shippingResponse = newResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.IGNORED_CONSIGNMENT_ERROR);
	}

	@Test
	public void testBaseStoreNotFound()
	{
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, WRONG_STORE, DEFAULT_ORDER))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), putResponse.getStatus());
		ShippingResponse shippingResponse = putResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.BASE_STORE_MISSING_ERROR);
	}

	@Ignore @Test
	public void testOrderNotFound()
	{
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, DEFAULT_ORDER))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), putResponse.getStatus());
		ShippingResponse shippingResponse = putResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.ORDER_MISSING_ERROR);
	}

	@Ignore @Test
	public void testProductNotFound()
	{
		createWrongProduct = true;
		OrderModel testOrder = createTestOrder();
		String orderCode = testOrder.getCode();
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, orderCode))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.MULTI_STATUS.value(), putResponse.getStatus());
		ShippingResponse shippingResponse = putResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.PRODUCT_MISSING_ERROR);
		createWrongProduct = false;
	}

	@Ignore @Test
	public void testWarehouseNotFound()
	{
		createWrongWH = true;
		OrderModel testOrder = createTestOrder();
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, testOrder.getCode()))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));

		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), putResponse.getStatus());
		ShippingResponse shippingResponse = putResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.WAREHOUSE_MISSING_ERROR);
		createWrongWH = false;
	}

	@Ignore @Test
	public void testMissingRequiredBodyParameter()
	{
		createEmptyBodyParameter = true;
		OrderModel testOrder = createTestOrder();
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, testOrder.getCode()))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), putResponse.getStatus());

		ShippingResponse shippingResponse = putResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.NULL_PARAMETER_ERROR);
		createEmptyBodyParameter = false;
	}

	@Ignore @Test
	public void testWrongQtyShipped()
	{
		createWrongShippedQty = true;
		OrderModel testOrder = createTestOrder();
		Response putResponse = wsSecuredRequestBuilder1.path(String.format(WEBSERVICE_PATH, DEFAULT_STORE, testOrder.getCode()))//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(getTestShippingEvent()));
		Assert.assertEquals(HttpStatus.MULTI_STATUS.value(), putResponse.getStatus());

		ShippingResponse shippingResponse = putResponse.readEntity(ShippingResponse.class);
		Assert.assertEquals(shippingResponse.getErrors().get(0).getType(), AmwayfulfillmentConstants.DIFFERENT_SHIPPED_ERROR);
		createWrongShippedQty = false;
	}

	private BaseStoreModel getTestStore()
	{
		return baseStoreService.getBaseStoreForUid(DEFAULT_STORE);
	}

	private OrderModel createTestOrder()
	{
		OrderModel order = modelService.create(OrderModel.class);
		order.setCode(UUID.randomUUID().toString());
		order.setCurrency(commonI18NService.getCurrency("EUR"));
		order.setDate(new Date());
		order.setUser(userService.getCurrentUser());
		order.setStatus(OrderStatus.CREATED);
		order.setStore(getTestStore());
		order.setDeliveryAddress(createTestAddress());
		order.setDeliveryMode(createTestDeliveryMode());
		order.setDeliveryStatus(createWrongOrderDeliveryStatus ? DeliveryStatus.PENDING : DeliveryStatus.IN_PROGRESS);
		ProductModel productModel1 = createTestProduct(PRODUCT_1_CODE);
		OrderEntryModel orderEntryModel1 = createTestOrderEntry(order, productModel1, PRODUCT_1_QTY);
		ProductModel productModel2 = createTestProduct(PRODUCT_2_CODE);
		OrderEntryModel orderEntryModel2 = createTestOrderEntry(order, productModel2, PRODUCT_2_QTY);
		List<AbstractOrderEntryModel> orderEntries = new ArrayList<>();
		orderEntries.add(orderEntryModel1);
		orderEntries.add(orderEntryModel2);
		order.setEntries(orderEntries);
		modelService.save(order);
		return order;
	}

	private AddressModel createTestAddress()
	{
		AddressModel addressModel = modelService.create(AddressModel.class);
		addressModel.setOwner(userService.getCurrentUser());
		modelService.save(addressModel);
		return addressModel;
	}

	private DeliveryModeModel createTestDeliveryMode()
	{
		DeliveryModeModel deliveryModeModel = modelService.create(DeliveryModeModel.class);
		deliveryModeModel.setActive(Boolean.TRUE);
		deliveryModeModel.setCode(UUID.randomUUID().toString());
		modelService.save(deliveryModeModel);
		return deliveryModeModel;
	}

	private OrderEntryModel createTestOrderEntry(OrderModel orderModel, ProductModel productModel, long qty)
	{
		OrderEntryModel orderEntryModel = modelService.create(OrderEntryModel.class);
		orderEntryModel.setProduct(productModel);
		orderEntryModel.setQuantity(Long.valueOf(qty));
		UnitModel unitModel = modelService.create(UnitModel.class);
		unitModel.setCode(UUID.randomUUID().toString());
		unitModel.setName("pieces");
		unitModel.setUnitType("Test type");
		modelService.save(unitModel);
		orderEntryModel.setUnit(unitModel);
		orderEntryModel.setOrder(orderModel);
		modelService.save(orderEntryModel);
		return orderEntryModel;
	}

	private ProductModel createTestProduct(String code)
	{
		ProductModel productModel = modelService.create(ProductModel.class);
		productModel.setCode(code);
		CatalogVersionModel catalogVersionModel = modelService.create(CatalogVersionModel.class);
		catalogVersionModel.setActive(Boolean.TRUE);
		catalogVersionModel.setVersion("1");
		catalogVersionModel.setCategorySystemName("Test category");
		CatalogModel catalogModel = modelService.create(CatalogModel.class);
		catalogModel.setId(UUID.randomUUID().toString());
		catalogModel.setName("Test catalog");
		modelService.save(catalogModel);
		catalogVersionModel.setCatalog(catalogModel);
		modelService.save(catalogVersionModel);
		productModel.setCatalogVersion(catalogVersionModel);
		productModel.setName("Test product");
		productModel.setAlias("T1");
		modelService.save(productModel);
		return productModel;
	}

	private ShippingEvent getTestShippingEvent()
	{
		ShippingEvent shippingEvent = new ShippingEvent();
		shippingEvent.setWarehouseId(createWrongWH ? "some_wh" : DEFAULT_WH);
		shippingEvent.setCarrier(createEmptyBodyParameter ? null : "DHL");
		shippingEvent.setTrackingId("00000530050104593676");
		shippingEvent.setTrackingLink("http://dhl.pl/tracking/00000530050104593676");
		shippingEvent.setShippingDate(new Date());
		List<ShippingPackage> packages = new ArrayList<>();
		packages.add(getTestShippingPackage());
		shippingEvent.setPackages(packages);
		return shippingEvent;
	}

	private ShippingPackage getTestShippingPackage()
	{
		ShippingPackage shippingPackage = new ShippingPackage();
		shippingPackage.setPackageId("123");
		shippingPackage.setPackageSeq("1");
		List<PackageEntry> packages = new ArrayList<>();
		packages.add(getTestPackageEntry("2016245XVDA", "1", createWrongProduct ? PRODUCT_1_WRONG_CODE : PRODUCT_1_CODE,
				createWrongShippedQty ? "10" : String.valueOf(PRODUCT_1_QTY)));
		packages.add(getTestPackageEntry("2016187ZWRA", "2", createWrongProduct ? PRODUCT_2_WRONG_CODE : PRODUCT_2_CODE,
				createWrongShippedQty ? "20" : String.valueOf(PRODUCT_2_QTY)));
		shippingPackage.setEntries(packages);
		return shippingPackage;
	}

	private PackageEntry getTestPackageEntry(String lot, String version, String productCode, String shippedQty)
	{
		PackageEntry packageEntry = new PackageEntry();
		packageEntry.setLot(lot);
		packageEntry.setVersion(version);
		packageEntry.setProductCode(productCode);
		packageEntry.setShippedQuantity(shippedQty);
		SerialNumber serialNumber = new SerialNumber();
		serialNumber.setSerialNumber("012345");
		packageEntry.setSerialNumbers(Collections.singletonList(serialNumber));
		return packageEntry;
	}

	static class ShippingEvent implements Serializable
	{
		String warehouseId;
		String carrier;
		String trackingId;
		String trackingLink;
		@JsonSerialize(using = DateSerializer.class)
		Date shippingDate;
		private List<ShippingPackage> packages;

		public String getWarehouseId()
		{
			return warehouseId;
		}

		public void setWarehouseId(String warehouseId)
		{
			this.warehouseId = warehouseId;
		}

		public String getCarrier()
		{
			return carrier;
		}

		public void setCarrier(String carrier)
		{
			this.carrier = carrier;
		}

		public String getTrackingId()
		{
			return trackingId;
		}

		public void setTrackingId(String trackingId)
		{
			this.trackingId = trackingId;
		}

		public String getTrackingLink()
		{
			return trackingLink;
		}

		public void setTrackingLink(String trackingLink)
		{
			this.trackingLink = trackingLink;
		}

		public Date getShippingDate()
		{
			return shippingDate;
		}

		public void setShippingDate(Date shippingDate)
		{
			this.shippingDate = shippingDate;
		}

		public List<ShippingPackage> getPackages()
		{
			return packages;
		}

		public void setPackages(List<ShippingPackage> packages)
		{
			this.packages = packages;
		}
	}

	static class DateSerializer extends JsonSerializer
	{
		@Override
		public void serialize(Object value, JsonGenerator gen, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			String formattedDate = formatter.format(value);
			gen.writeString(formattedDate);
		}
	}

}
