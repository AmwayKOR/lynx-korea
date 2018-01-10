package com.amway.amwayfinance.test.webservices;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.oauth2.constants.OAuth2Constants;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.webservicescommons.testsupport.client.WsSecuredRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.springframework.http.HttpStatus;

import com.amway.amwayfinance.constants.AmwayfinanceConstants;
import com.amway.amwayfinance.services.AmwayTransactionInfoService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@NeedsEmbeddedServer(webExtensions = { AmwayfinanceConstants.EXTENSIONNAME, OAuth2Constants.EXTENSIONNAME })
@IntegrationTest
public class AmwayfinanceWebServicesTest extends ServicelayerTest
{
	private  static final String OAUTH_CLIENT_ID = "finance-test";
	private static final String OAUTH_CLIENT_PASS = "secret";

	private static final String BANK_TRANSFER = "bank-transfer";
	private static final String CREDIT_CARD = "credit-card";
	private static final String DEFAULT_STORE = "lynxswe";
	private static final String USD = "USD";
	private static final String EUR = "EUR";

	@Resource
	private ModelService modelService;

	@Resource
	private BaseStoreService baseStoreService;

	@Resource
	private CommonI18NService commonI18NService;

	@Resource
	private UserService userService;

	@Resource
	private PaymentModeService paymentModeService;

	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwayfinance/test/democustomer-data.impex", "utf-8");
		modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");
	}

	private WsSecuredRequestBuilder getRequestBuilder()
	{
		return new WsSecuredRequestBuilder()//
				.extensionName(AmwayfinanceConstants.EXTENSIONNAME)//
				.client(OAUTH_CLIENT_ID, OAUTH_CLIENT_PASS)//
				.grantClientCredentials();
	}

	@Ignore @Test
	public void testAddPaymentInfoForAnOrder()
	{
		String currency = AmwayfinanceWebServicesTest.USD;
		String paymentMode = BANK_TRANSFER;

		OrderModel order = createOrder(currency, paymentMode, OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.CREATED.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("order has one and only one payment transaction", 1, transactions.size());
		PaymentTransactionModel transaction = transactions.get(0);
		Assert.assertEquals("order's payment transaction has one and only one entry", 1, transaction.getEntries().size());
		PaymentTransactionEntryModel entry = transaction.getEntries().get(0);
		Assert.assertEquals("currency set correctly", currency, entry.getCurrency().getIsocode());
		Assert.assertEquals("transactionId mapped to PaymentTransactionEntry#code", dto.getTransactionId(), entry.getCode());
		Assert.assertEquals("transaction type is 'external capture'", PaymentTransactionType.EXTERNAL_CAPTURE, entry.getType());
		Assert.assertTrue("amount mapped to PaymentTransactionEntry#amount", entry.getAmount().compareTo(dto.getAmount()) == 0);
		Assert.assertEquals("paymentType mapped to PaymentTransactionEntry#paymentProvider", dto.getPaymentType(),
				transaction.getPaymentProvider());
		Assert.assertEquals("Transaction marked as valid", dto.getStatus(), transactions.get(0).getEntries()
				.get(0).getTransactionStatus());

		modelService.remove(order);
	}


	@Ignore @Test
	public void testZeroAmount()
	{
		String currency = AmwayfinanceWebServicesTest.USD;
		String paymentMode = BANK_TRANSFER;

		OrderModel order = createOrder(currency, paymentMode, OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(0));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.CREATED.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("order has one and only one payment transaction", 1, transactions.size());
		PaymentTransactionModel transaction = transactions.get(0);
		Assert.assertEquals("order's payment transaction has one and only one entry", 1, transaction.getEntries().size());
		PaymentTransactionEntryModel entry = transaction.getEntries().get(0);
		Assert.assertEquals("currency set correctly", currency, entry.getCurrency().getIsocode());
		Assert.assertEquals("transactionId mapped to PaymentTransactionEntry#code", dto.getTransactionId(), entry.getCode());
		Assert.assertEquals("transaction type is 'external capture'", PaymentTransactionType.EXTERNAL_CAPTURE, entry.getType());
		Assert.assertTrue("amount mapped to PaymentTransactionEntry#amount", entry.getAmount().compareTo(dto.getAmount()) == 0);
		Assert.assertEquals("paymentType mapped to PaymentTransactionEntry#paymentProvider", dto.getPaymentType(),
				transaction.getPaymentProvider());
		Assert.assertEquals("Transaction marked as valid", dto.getStatus(), transactions.get(0).getEntries()
				.get(0).getTransactionStatus());
	}

	@Ignore @Test
	public void testNegativeAmount()
	{
		String currency = AmwayfinanceWebServicesTest.USD;
		String paymentMode = BANK_TRANSFER;

		OrderModel order = createOrder(currency, paymentMode, OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(-100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("order has no payment transaction", 0, transactions.size());

		modelService.remove(order);
	}

	@Ignore @Test
	public void testForWrongCurrency()
	{
		OrderModel order = createOrder(AmwayfinanceWebServicesTest.USD, AmwayfinanceWebServicesTest.BANK_TRANSFER,
				OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(EUR);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("no transactions created", 0, transactions.size());
		modelService.remove(order);
	}

	@Ignore @Test
	public void testForWrongPaymentMode()
	{
		OrderModel order = createOrder(AmwayfinanceWebServicesTest.USD, AmwayfinanceWebServicesTest.CREDIT_CARD,
				OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(AmwayfinanceWebServicesTest.USD);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("Verify, that payment transaction has been created anyway", 1, transactions.size());
		Assert.assertEquals("Transaction marked as invalid", AmwayTransactionInfoService.IGNORED_PAYMENT_TRANSACTION_STATUS,
				transactions.get(0).getEntries().get(0).getTransactionStatus());

		modelService.remove(order);
	}

	@Ignore @Test
	public void testDuplicateTransaction()
	{
		String currency = AmwayfinanceWebServicesTest.USD;
		String paymentMode = BANK_TRANSFER;

		OrderModel order = createOrder(currency, paymentMode, OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setTransactionId("dup");
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));
		Assert.assertEquals(HttpStatus.CREATED.value(), result.getStatus());

		PaymentEvent dto2 = createPaymentEvent();
		dto2.setTransactionId("dup");
		dto2.setCurrency(currency);
		dto2.setAmount(new BigDecimal(100));

		final Response result2 = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result2.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();

		Assert.assertEquals("Verify, that payment transaction has been created anyway", 2, transactions.size());
		Assert.assertEquals("First transaction marked as valid", dto.getStatus(), transactions.get(0).getEntries()
				.get(0).getTransactionStatus());
		Assert.assertEquals("The second transaction marked as valid",
				AmwayTransactionInfoService.IGNORED_PAYMENT_TRANSACTION_STATUS, transactions.get(1).getEntries().get(0)
						.getTransactionStatus());


		modelService.remove(order);
	}

	@Ignore @Test
	public void testMissingOrder()
	{
		String currency = AmwayfinanceWebServicesTest.USD;

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + "700000000" + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
	}

	@Ignore @Test
	public void testErrorOrderStatusCanceled()
	{
		String currency = AmwayfinanceWebServicesTest.USD;
		String paymentMode = BANK_TRANSFER;

		OrderModel order = createOrder(currency, paymentMode, OrderStatus.CANCELLED);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		Assert.assertEquals("transaction status is ignored", AmwayTransactionInfoService.IGNORED_PAYMENT_TRANSACTION_STATUS,
				changedOrder.getPaymentTransactions().get(0).getEntries().get(0).getTransactionStatus());

		modelService.remove(order);
	}

	@Ignore @Test
	public void testErrorOrderStatusPaid()
	{
		String currency = AmwayfinanceWebServicesTest.USD;
		String paymentMode = BANK_TRANSFER;

		OrderModel order = createOrder(currency, paymentMode, OrderStatus.ACTIVE);
		order.setPaymentStatus(PaymentStatus.PAID);
		modelService.save(order);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		Assert.assertEquals("transaction status is ignored", AmwayTransactionInfoService.IGNORED_PAYMENT_TRANSACTION_STATUS,
				changedOrder.getPaymentTransactions().get(0).getEntries().get(0).getTransactionStatus());

		modelService.remove(order);
	}

	@Ignore @Test
	public void testErrorOrderOnlinePayment()
	{
		String currency = AmwayfinanceWebServicesTest.USD;
		String paymentMode = CREDIT_CARD;

		OrderModel order = createOrder(currency, paymentMode, OrderStatus.ACTIVE);
		modelService.save(order);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(currency);
		dto.setAmount(new BigDecimal(100));

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		Assert.assertEquals("transaction status is ignored", AmwayTransactionInfoService.IGNORED_PAYMENT_TRANSACTION_STATUS,
				changedOrder.getPaymentTransactions().get(0).getEntries().get(0).getTransactionStatus());

		modelService.remove(order);
	}

	@Ignore @Test
	public void testForWrongPaymentEventStatus()
	{
		OrderModel order = createOrder(AmwayfinanceWebServicesTest.USD, AmwayfinanceWebServicesTest.BANK_TRANSFER,
				OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(EUR);
		dto.setAmount(new BigDecimal(100));
		dto.setStatus(null);

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("no transactions created", 0, transactions.size());
		modelService.remove(order);
	}

	@Ignore @Test
	public void testForPaymentEventStatusDetailsBlank()
	{
		OrderModel order = createOrder(AmwayfinanceWebServicesTest.EUR, AmwayfinanceWebServicesTest.BANK_TRANSFER,
				OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(EUR);
		dto.setAmount(new BigDecimal(100));
		dto.setStatusDetails(null);

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.CREATED.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("order has one and only one payment transaction", 1, transactions.size());
		PaymentTransactionModel transaction = transactions.get(0);
		Assert.assertEquals("order's payment transaction has one and only one entry", 1, transaction.getEntries().size());
		Assert.assertEquals("Transaction marked as valid", dto.getStatus(), transactions.get(0).getEntries()
				.get(0).getTransactionStatus());

		modelService.remove(order);
	}

	@Ignore @Test
	public void testNoAmount()
	{
		OrderModel order = createOrder(AmwayfinanceWebServicesTest.USD, AmwayfinanceWebServicesTest.BANK_TRANSFER,
				OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(EUR);
		dto.setAmount(null);

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("no transactions created", 0, transactions.size());
		modelService.remove(order);
	}

	@Ignore @Test
	public void testNoPaymentType()
	{
		OrderModel order = createOrder(AmwayfinanceWebServicesTest.USD, AmwayfinanceWebServicesTest.BANK_TRANSFER,
				OrderStatus.ACTIVE);

		PaymentEvent dto = createPaymentEvent();
		dto.setCurrency(EUR);
		dto.setAmount(new BigDecimal(100));
		dto.setPaymentType(null);

		final Response result = getRequestBuilder()//
				.path(DEFAULT_STORE + "/orders/" + order.getCode() + "/payment")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.put(Entity.json(dto));

		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());

		OrderModel changedOrder = modelService.get(order.getPk());
		List<PaymentTransactionModel> transactions = changedOrder.getPaymentTransactions();
		Assert.assertEquals("no transactions created", 0, transactions.size());
		modelService.remove(order);
	}

	private OrderModel createOrder(String currency, String paymentMode, OrderStatus status)
	{
		BaseStoreModel store = baseStoreService.getBaseStoreForUid(DEFAULT_STORE);
		OrderModel order = modelService.create(OrderModel.class);
		order.setPaymentStatus(PaymentStatus.NOTPAID);
		order.setCode(UUID.randomUUID().toString());
		order.setCurrency(commonI18NService.getCurrency(currency));
		order.setDate(new Date());
		order.setUser(userService.getCurrentUser());
		order.setPaymentMode(paymentModeService.getPaymentModeForCode(paymentMode));
		order.setStatus(status);
		order.setStore(store);
		modelService.save(order);
		return order;
	}

	private PaymentEvent createPaymentEvent()
	{
		PaymentEvent dto = new PaymentEvent();
		dto.date = new Date();
		dto.transactionId = "tr1";
		dto.status = "status1";
		dto.statusDetails = "status details1";
		dto.paymentType = "BANK transfer";
		return dto;
	}

	static class PaymentEvent implements java.io.Serializable
	{
		String transactionId;
		String status;
		String statusDetails;
		String currency;
		BigDecimal amount;
		@JsonSerialize(using = DateSerializer.class)
		Date date;
		String paymentType;

		public String getTransactionId()
		{
			return transactionId;
		}

		public void setTransactionId(String transactionId)
		{
			this.transactionId = transactionId;
		}

		public String getStatus()
		{
			return status;
		}

		public void setStatus(String status)
		{
			this.status = status;
		}

		public String getStatusDetails()
		{
			return statusDetails;
		}

		public void setStatusDetails(String statusDetails)
		{
			this.statusDetails = statusDetails;
		}

		public String getCurrency()
		{
			return currency;
		}

		public void setCurrency(String currency)
		{
			this.currency = currency;
		}

		public BigDecimal getAmount()
		{
			return amount;
		}

		public void setAmount(BigDecimal amount)
		{
			this.amount = amount;
		}

		public Date getDate()
		{
			return date;
		}

		public void setDate(Date date)
		{
			this.date = date;
		}

		public String getPaymentType()
		{
			return paymentType;
		}

		public void setPaymentType(String paymentType)
		{
			this.paymentType = paymentType;
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
