/**
 *
 */
package com.amway.apac.facades.customeraccount.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.apac.core.customer.services.AmwayApacCustomerAccountService;
import com.amway.apac.facades.constants.AmwayapacFacadesConstants;

import reactor.util.StringUtils;


/**
 * @author Aaron Yong
 *
 */
@UnitTest
public class DefaultAmwayApacOrderFacadeUnitTesting
{

	private DefaultAmwayApacOrderFacade defaultAmwayApacOrderFacade;

	@Mock
	private AmwayApacCustomerAccountService amwayApacCustomerAccountService;
	@Mock
	private CustomerAccountService customerAccountService;
	@Mock
	private AbstractPopulatingConverter<OrderModel, OrderData> orderConverter;
	@Mock
	private UserService userService;
	@Mock
	private BaseStoreService baseStoreService;
	@Mock
	private AbstractPopulatingConverter<OrderModel, OrderHistoryData> orderHistoryConverter;
	@Mock
	private CheckoutCustomerStrategy defaultCheckoutCustomerStrategy;

	private OrderModel orderModel;

	private OrderHistoryData orderHistoryData;

	private ProductModel productModel;

	private ProductData productData;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		defaultAmwayApacOrderFacade = new DefaultAmwayApacOrderFacade();
		defaultAmwayApacOrderFacade.setCustomerAccountService(customerAccountService);
		defaultAmwayApacOrderFacade.setOrderConverter(orderConverter);
		defaultAmwayApacOrderFacade.setUserService(userService);
		defaultAmwayApacOrderFacade.setBaseStoreService(baseStoreService);
		defaultAmwayApacOrderFacade.setOrderHistoryConverter(orderHistoryConverter);
		defaultAmwayApacOrderFacade.setCheckoutCustomerStrategy(defaultCheckoutCustomerStrategy);
		defaultAmwayApacOrderFacade.setAmwayApacCustomerAccountService(amwayApacCustomerAccountService);

		orderModel = new OrderModel();
		orderModel.setCode("order");
		final AbstractOrderEntryModel entryModel = new AbstractOrderEntryModel();
		final ProductModel productModel1 = new ProductModel();
		entryModel.setProduct(productModel1);
		final List<AbstractOrderEntryModel> entryModelList = new ArrayList<AbstractOrderEntryModel>();
		entryModelList.add(entryModel);
		orderModel.setEntries(entryModelList);

		final OrderData orderData = Mockito.mock(OrderData.class);
		final List<OrderEntryData> listData = new ArrayList<OrderEntryData>();
		final OrderEntryData entryData = new OrderEntryData();
		entryData.setProduct(productData);
		listData.add(entryData);
		given(orderData.getEntries()).willReturn(listData);
		given(orderConverter.convert(orderModel)).willReturn(orderData);

		productModel = Mockito.mock(ProductModel.class);
		productData = Mockito.mock(ProductData.class);

	}

	@Test
	public void testGetCustomerOrderCounts()
	{
		Assert.assertEquals(new Integer(0), defaultAmwayApacOrderFacade.getCustomerOrderCounts());
	}

	@Test
	public void testGetOrderHistoryDateOptionsForCorrectFormat()
	{
		Assert.assertEquals(
				java.time.format.DateTimeFormatter.ofPattern("yyyy-MM").format(java.time.LocalDate.now().minusMonths(1)),
				defaultAmwayApacOrderFacade.getOrderHistoryDateOptions().get(0));
	}

	@Test
	public void testGetPagedOrderHistoryByFilterAndSearch()
	{
		given(orderHistoryConverter.convert(orderModel)).willReturn(orderHistoryData);
		final CustomerModel customerModel = new CustomerModel();
		given(userService.getCurrentUser()).willReturn(customerModel);
		final BaseStoreModel baseStoreModel = new BaseStoreModel();
		baseStoreModel.setUid("baseStoreModel");
		given(baseStoreService.getCurrentBaseStore()).willReturn(baseStoreModel);
		given(customerAccountService.getOrderList(customerModel, baseStoreModel, null)).willReturn(Collections.EMPTY_LIST);

		final PageableData pageabledata = new PageableData();
		final SearchPageData<OrderModel> orderResults = new SearchPageData<OrderModel>();
		given(amwayApacCustomerAccountService.getOrderListByFilter(customerModel, baseStoreModel, getStartDate(null),
				getEndDate(null), null, pageabledata)).willReturn(orderResults);

		Assert.assertEquals(Collections.EMPTY_LIST.size(),
				defaultAmwayApacOrderFacade.getPagedOrderHistoryByFilterAndSearch(pageabledata, null, null).getResults().size());

	}

	private LocalDate getStartDate(final String date)
	{
		if (StringUtils.isEmpty(date) || AmwayapacFacadesConstants.LAST_THIRTY_DAYS.equals(date))
		{
			return LocalDate.now().minusDays(30);
		}
		else
		{
			return LocalDate.parse(date + AmwayapacFacadesConstants.FIRST_DAY_OF_MONTH,
					DateTimeFormatter.ofPattern(AmwayapacFacadesConstants.ORDER_DATE_FORMAT_PATTERN));
		}
	}

	private LocalDate getEndDate(final String date)
	{
		if (StringUtils.isEmpty(date) || AmwayapacFacadesConstants.LAST_THIRTY_DAYS.equals(date))
		{
			return LocalDate.now();
		}
		else
		{
			return LocalDate.parse(date + AmwayapacFacadesConstants.LAST_DAY_OF_MONTH,
					DateTimeFormatter.ofPattern(AmwayapacFacadesConstants.ORDER_DATE_FORMAT_PATTERN));
		}
	}
}
