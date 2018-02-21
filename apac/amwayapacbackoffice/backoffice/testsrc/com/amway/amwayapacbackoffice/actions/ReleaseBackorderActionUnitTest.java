/**
 *
 */
package com.amway.amwayapacbackoffice.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;




/**
 *
 */
@UnitTest
public class ReleaseBackorderActionUnitTest
{
	@InjectMocks
	private ReleaseBackorderAction releaseBackorderAction;
	@Mock
	private StockLevelModel stockLevel;
	@Mock
	private PaymentTransactionEntryModel paymentTransactionEntryModel;
	@Mock
	private PaymentTransactionModel paymentTransactionModel;
	@Mock
	private OrderModel order;
	@Mock
	private AmwayBackOrderModel amwayBackOrderModel;
	@Mock
	private WarehouseModel wareHouse;
	@Mock
	private ProductModel productModel;
	@Mock
	private BaseStoreModel baseStoreModel;
	@Mock
	private UserModel userModel;
	@Mock
	private ConsignmentModel consignmentModel;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test()
	{
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		final ActionContext<StockLevelModel> ctx = mock(ActionContext.class);
		stockLevel.setWarehouse(wareHouse);
		stockLevel.setProduct(productModel);
		stockLevel.setInStockStatus(InStockStatus.FORCEINSTOCK);
		stockLevel.setAvailable(10);
		paymentTransactionEntryModel.setVersionID(null);
		paymentTransactionEntryModel.setType(PaymentTransactionType.CAPTURE);
		final List<PaymentTransactionEntryModel> paymentTransactionEntryModels = new ArrayList<PaymentTransactionEntryModel>();
		paymentTransactionEntryModels.add(paymentTransactionEntryModel);
		paymentTransactionModel.setEntries(paymentTransactionEntryModels);
		final List<PaymentTransactionModel> paymentTransactionModels = new ArrayList<PaymentTransactionModel>();
		paymentTransactionModels.add(paymentTransactionModel);
		order.setPaymentTransactions(paymentTransactionModels);
		consignmentModel.setOrder(order);
		amwayBackOrderModel.setBaseStore(baseStoreModel);
		amwayBackOrderModel.setWarehouse(wareHouse);
		amwayBackOrderModel.setProduct(productModel);
		amwayBackOrderModel.setCreationtime(cal.getTime());
		cal.add(Calendar.DATE, 360);
		amwayBackOrderModel.setReleaseByDate(cal.getTime());
		amwayBackOrderModel.setStatus(AmwayBackOrderStatus.ACTIVE);
		amwayBackOrderModel.setOrderingAbo(userModel);
		amwayBackOrderModel.setOriginalOrder(order);
		amwayBackOrderModel.setConsignment(consignmentModel);
		when(ctx.getData()).thenReturn(stockLevel);
		final ActionResult<String> result = releaseBackorderAction.perform(ctx);
		Assert.assertEquals(ActionResult.SUCCESS, result);
	}

}
