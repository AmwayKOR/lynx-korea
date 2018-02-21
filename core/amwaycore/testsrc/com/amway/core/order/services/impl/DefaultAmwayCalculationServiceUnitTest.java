/**
 *
 */
package com.amway.core.order.services.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.strategies.calculation.FindDiscountValuesStrategy;
import de.hybris.platform.order.strategies.calculation.FindPriceStrategy;
import de.hybris.platform.order.strategies.calculation.FindTaxValuesStrategy;
import de.hybris.platform.order.strategies.calculation.OrderRequiresCalculationStrategy;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.PriceValue;
import de.hybris.platform.util.TaxValue;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.price.service.AmwayNetPriceService;


@UnitTest
public class DefaultAmwayCalculationServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayCalculationService amwayCalculationService = new DefaultAmwayCalculationService();
	@Mock
	private OrderRequiresCalculationStrategy orderRequiresCalculationStrategy;
	@Mock
	private FindPriceStrategy findPriceStrategy;
	@Mock
	private ModelService modelService;
	@Mock
	private BaseSiteService baseSiteService;
	@Mock
	private SessionService sessionService;
	@Mock
	private CommercePriceService commercePriceService;
	@Mock
	private AmwayNetPriceService amwayNetPriceService;
	@Mock
	private CommonI18NService commonI18NService;

	private OrderModel order;
	private PriceRowModel row1, row2;
	private AbstractOrderEntryModel entry1, entry2;
	private ProductModel product1, product2;
	private UnitModel unit;
	private CurrencyModel currency;
	private PriceValue priceValue1, priceValue2, aboPriceValue;
	private CMSSiteModel site;
	private PriceInformation priceInfo1, priceInfo2;
	private Map<String, Object> qualifiers1, qualifiers2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		qualifiers1 = new HashMap<>();
		qualifiers1.put("businessVolume", Double.valueOf(4.0));
		qualifiers1.put("pointValue", Double.valueOf(5.0));

		qualifiers2 = new HashMap<>();
		qualifiers2.put("businessVolume", Double.valueOf(4.0));
		qualifiers2.put("pointValue", Double.valueOf(5.0));

		site = new CMSSiteModel();
		site.setGiftWrapPrice(Double.valueOf(20));
		amwayCalculationService.setModelService(modelService);
		amwayCalculationService.setFindTaxesStrategies(new ArrayList<FindTaxValuesStrategy>());
		amwayCalculationService.setFindDiscountsStrategies(new ArrayList<FindDiscountValuesStrategy>());
		amwayCalculationService.setSessionService(sessionService);
		amwayCalculationService.setCommercePriceService(commercePriceService);
		amwayCalculationService.setCommonI18NService(commonI18NService);



		currency = new CurrencyModel();
		currency.setIsocode("USD");
		currency.setConversion(Double.valueOf(1.4));
		currency.setDigits(Integer.valueOf(2));
		currency.setSymbol("$");

		unit = new UnitModel();
		unit.setCode("pieces");
		unit.setUnitType("pieces");
		//		unit.setName("pieces");

		product1 = new ProductModel();
		product2 = new ProductModel();

		row1 = new PriceRowModel();
		row1.setProduct(product1);
		row1.setBusinessVolume(4.0);
		row1.setPointValue(5.0);
		row1.setUnit(unit);
		row1.setCurrency(currency);

		row2 = new PriceRowModel();
		row2.setProduct(product2);
		row2.setBusinessVolume(4.0);
		row2.setPointValue(5.0);
		row2.setUnit(unit);
		row2.setCurrency(currency);

		final DiscountValue discount = new DiscountValue("D001", 4.5, true, "USD");
		final TaxValue tax1 = new TaxValue("T001", 2.0, true, "USD");
		final TaxValue tax2 = new TaxValue("T001", 2.0, true, "USD");
		entry1 = Mockito.spy(new AbstractOrderEntryModel());
		entry2 = Mockito.spy(new AbstractOrderEntryModel());
		entry1.setProduct(product1);
		entry1.setAboBasePrice(Double.valueOf(100));
		entry1.setBasePrice(Double.valueOf(120));
		entry1.setBusinessVolume(4.0);
		entry1.setPointValue(5.0);
		entry1.setQuantity(Long.valueOf(1));
		entry1.setTotalPrice(Double.valueOf(120));
		entry1.setTaxValues(new ArrayList<TaxValue>());
		//		entry1.setDiscountValues(Arrays.asList(discount));
		entry1.setGiveAway(Boolean.FALSE);
		when(entry1.getDiscountValues()).thenReturn(Arrays.asList(discount));
		when(entry1.getTaxValues()).thenReturn(Arrays.asList(tax1));

		entry2.setProduct(product2);
		entry2.setAboBasePrice(Double.valueOf(100));
		entry2.setBasePrice(Double.valueOf(120));
		entry2.setBusinessVolume(4.0);
		entry2.setPointValue(5.0);
		entry2.setQuantity(Long.valueOf(1));
		entry2.setTotalPrice(Double.valueOf(120));
		entry2.setTaxValues(new ArrayList<TaxValue>());
		//		entry2.setDiscountValues(Arrays.asList(discount));
		entry2.setGiveAway(Boolean.FALSE);
		when(entry2.getDiscountValues()).thenReturn(Arrays.asList(discount));
		when(entry2.getTaxValues()).thenReturn(Arrays.asList(tax2));

		priceValue1 = new PriceValue("USD", 120, true);
		priceValue2 = new PriceValue("USD", 120, true);
		aboPriceValue = new PriceValue("USD", 100, true);

		priceInfo1 = new PriceInformation(qualifiers1, priceValue1);
		priceInfo2 = new PriceInformation(qualifiers2, priceValue2);

		when(findPriceStrategy.findBasePrice(entry1)).thenReturn(priceValue1);
		when(findPriceStrategy.findBasePrice(entry2)).thenReturn(priceValue2);

		order = new OrderModel();
		order.setEntries(Arrays.asList(entry1, entry2));
		order.setCurrency(currency);
		order.setNet(Boolean.TRUE);
		order.setTotalTaxValues(new ArrayList<TaxValue>());
		order.setTotalPrice(Double.valueOf(240));

		entry1.setOrder(order);
		entry2.setOrder(order);

		when(Boolean.valueOf(orderRequiresCalculationStrategy.requiresCalculation(entry1))).thenReturn(Boolean.TRUE);
		when(Boolean.valueOf(orderRequiresCalculationStrategy.requiresCalculation(entry2))).thenReturn(Boolean.TRUE);
		when(baseSiteService.getCurrentBaseSite()).thenReturn(site);
		when(commercePriceService.getFromPriceForProduct(product1)).thenReturn(priceInfo1);
		when(commercePriceService.getFromPriceForProduct(product2)).thenReturn(priceInfo2);
		when(amwayNetPriceService.findABOBasePrice(entry1)).thenReturn(aboPriceValue);
		when(amwayNetPriceService.findRetailBasePrice(entry1)).thenReturn(priceValue1);
		when(amwayNetPriceService.findABOBasePrice(entry2)).thenReturn(aboPriceValue);
		when(amwayNetPriceService.findRetailBasePrice(entry2)).thenReturn(priceValue2);


	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.services.impl.DefaultAmwayCalculationService#calculateEntries(de.hybris.platform.core.model.order.AbstractOrderModel, boolean)}
	 * .
	 *
	 * @throws CalculationException
	 */
	@Test
	public void testCalculateEntries() throws CalculationException
	{
		amwayCalculationService.calculateEntries(order, true);
		Assert.assertEquals(Double.valueOf(0.0), Double.valueOf(order.getBusinessVolume()));
		Assert.assertEquals(Double.valueOf(0.0), Double.valueOf(order.getPointValue()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.services.impl.DefaultAmwayCalculationService#calculateGiftWrapPrice(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	@Test
	public void testCalculateGiftWrapPriceContainsGift()
	{
		order.setGiftWrap(Boolean.TRUE);
		amwayCalculationService.calculateGiftWrapPrice(order);
		Assert.assertEquals(Double.valueOf(20), Double.valueOf(order.getGiftWrapPrice()));
		Assert.assertEquals(Double.valueOf(260), order.getTotalPrice());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.services.impl.DefaultAmwayCalculationService#calculateGiftWrapPrice(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 * .
	 */
	@Test
	public void testCalculateGiftWrapPriceNotContainingGift()
	{
		order.setGiftWrap(Boolean.FALSE);
		amwayCalculationService.calculateGiftWrapPrice(order);
		Assert.assertEquals(Double.valueOf(0), Double.valueOf(order.getGiftWrapPrice()));
		Assert.assertEquals(Double.valueOf(240), order.getTotalPrice());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.services.impl.DefaultAmwayCalculationService#calculateAmwayValue(de.hybris.platform.core.model.order.AbstractOrderModel, boolean)}
	 * .
	 */
	@Test
	public void testCalculateAmwayValue()
	{
		amwayCalculationService.calculateAmwayValue(order, true);
		Assert.assertEquals(Double.valueOf(0), Double.valueOf(order.getBusinessVolume()));
		Assert.assertEquals(Double.valueOf(0), Double.valueOf(order.getPointValue()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.services.impl.DefaultAmwayCalculationService#setAboRetailBasePrices(de.hybris.platform.core.model.order.AbstractOrderEntryModel)}
	 * .
	 */
	@Test
	public void testSetAboRetailBasePrices()
	{
		amwayCalculationService.setAboRetailBasePrices(entry1);
		Assert.assertEquals(Double.valueOf(100), entry1.getAboBasePrice());
		Assert.assertEquals(Double.valueOf(120), entry1.getRetailBasePrice());
	}

}
