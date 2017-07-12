/**
 *
 */
package com.amway.core.customjalo.price;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.JaloItemNotFoundException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.enumeration.EnumerationManager;
import de.hybris.platform.jalo.enumeration.EnumerationType;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.product.ProductManager;
import de.hybris.platform.jalo.product.Unit;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class AmwayEurope1PriceFactoryIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private SessionService sessionService;
	@Resource
	private ModelService modelService;
	private SessionContext ctx;
	private static final String TEST_PRODUCT = "HW1210-3422";
	private static final String TEST_USER = "amway_party_test_1";
	private static final String TEST_USER_GROUP = "AmwayDefaultABOPriceGroup";
	private static final String TEST_PRODUCT_GROUP = "TEST_PRODUCT_GROUP";

	private AmwayEurope1PriceFactory amwayEurope1PriceFactory;
	private Unit unit;
	private Currency currency;

	private Product givenProduct;
	private EnumerationValue givenProductGroup;
	private User givenUser;
	private EnumerationValue givenUserGroup;
	private JaloSession session;
	private CatalogVersionModel onlineVersion;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void prepare() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/productTestData.csv", "windows-1252");

		ctx = JaloSession.getCurrentSession().getSessionContext();

		amwayEurope1PriceFactory = (AmwayEurope1PriceFactory) AmwayEurope1PriceFactory.getInstance();
		final ProductManager productManager = ProductManager.getInstance();
		final UserManager userManager = UserManager.getInstance();
		final EnumerationManager enumerationManager = EnumerationManager.getInstance();
		final EnumerationType productGroupType = enumerationManager.getEnumerationType(Europe1Constants.TYPES.PRICE_PRODUCT_GROUP);

		unit = productManager.createUnit("pieces", "pieces");
		try
		{
			currency = C2LManager.getInstance().getCurrencyByIsoCode("USD");
		}
		catch (final JaloItemNotFoundException e)
		{
			currency = C2LManager.getInstance().createCurrency("USD");
		}

		//		productManager.createProduct(TEST_PRODUCT);
		//		userManager.createCustomer(TEST_USER);
		//		amwayEurope1PriceFactory.createUserPriceGroup(TEST_USER_GROUP);
		//		enumerationManager.createEnumerationValue(productGroupType, TEST_PRODUCT_GROUP);

		givenProduct = product(TEST_PRODUCT);
		givenProductGroup = null;
		givenUser = user(TEST_USER);
		givenUserGroup = userGroup(TEST_USER_GROUP);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.customjalo.price.AmwayEurope1PriceFactory#matchPriceRowsForInfo(de.hybris.platform.jalo.SessionContext, de.hybris.platform.jalo.product.Product, de.hybris.platform.jalo.enumeration.EnumerationValue, de.hybris.platform.jalo.user.User, de.hybris.platform.jalo.enumeration.EnumerationValue, de.hybris.platform.jalo.c2l.Currency, java.util.Date, boolean)}
	 * .
	 *
	 * @throws JaloPriceFactoryException
	 */
	@Test
	public void testMatchPriceRowsForInfoSessionContextProductEnumerationValueUserEnumerationValueCurrencyDateBoolean()
			throws JaloPriceFactoryException
	{
		final List<PriceRow> priceRows = amwayEurope1PriceFactory
				.matchPriceRowsForInfo(ctx, givenProduct, givenProductGroup, givenUser, givenUserGroup, currency, new Date(), true);
		Assert.assertNotNull(priceRows);
		Assert.assertTrue(CollectionUtils.size(priceRows) > 0);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.customjalo.price.AmwayEurope1PriceFactory#matchPriceRowForPrice(de.hybris.platform.jalo.SessionContext, de.hybris.platform.jalo.product.Product, de.hybris.platform.jalo.enumeration.EnumerationValue, de.hybris.platform.jalo.user.User, de.hybris.platform.jalo.enumeration.EnumerationValue, long, de.hybris.platform.jalo.product.Unit, de.hybris.platform.jalo.c2l.Currency, java.util.Date, boolean, boolean)}
	 * .
	 *
	 * @throws JaloPriceFactoryException
	 */
	@Test
	public void testMatchPriceRowForPriceSessionContextProductEnumerationValueUserEnumerationValueLongUnitCurrencyDateBooleanBoolean()
			throws JaloPriceFactoryException
	{
		final PriceRow priceRow = amwayEurope1PriceFactory
				.matchPriceRowForPrice(ctx, givenProduct, givenProductGroup, givenUser, givenUserGroup, 2, unit, currency, new Date(),
						true, false);
		Assert.assertNotNull(priceRow);
	}

	@Test
	public void testQueryPriceRows4Price()
	{
		final Collection<PriceRow> priceRows = amwayEurope1PriceFactory
				.queryPriceRows4Price(ctx, givenProduct, givenProductGroup, givenUser, givenUserGroup);
		Assert.assertNotNull(priceRows);
		Assert.assertTrue(CollectionUtils.size(priceRows) > 0);
	}

	private Collection<PriceRow> queryForPrices(final Object product, final Object user)
	{
		final Product prod = (product instanceof Product) ? (Product) product : null;
		final EnumerationValue prodGroup = (product instanceof EnumerationValue) ? (EnumerationValue) product : null;
		final User usr = (user instanceof User) ? (User) user : null;
		final EnumerationValue usrGroup = (user instanceof EnumerationValue) ? (EnumerationValue) user : null;

		return amwayEurope1PriceFactory.queryPriceRows4Price(null, prod, prodGroup, usr, usrGroup);
	}

	private PriceRow createPrice(final Object product, final Object user) throws Exception
	{
		final Product prod = (product instanceof Product) ? (Product) product : null;
		final EnumerationValue prodGroup = (product instanceof EnumerationValue) ? (EnumerationValue) product : null;
		final User usr = (user instanceof User) ? (User) user : null;
		final EnumerationValue usrGroup = (user instanceof EnumerationValue) ? (EnumerationValue) user : null;
		final String productCode = (product instanceof String) ? (String) product : null;

		final SessionContext ctx = JaloSession.getCurrentSession().getSessionContext();

		return (PriceRow) ComposedType
				.newInstance(ctx, PriceRow.class, PriceRow.PRODUCT, prod, PriceRow.PG, prodGroup, PriceRow.USER, usr, PriceRow.UG,
						usrGroup, PriceRow.MINQTD, Long.valueOf(2), PriceRow.CURRENCY, currency, PriceRow.UNIT, unit,
						PriceRow.UNITFACTOR, Integer.valueOf(1), PriceRow.NET, Boolean.TRUE, PriceRow.DATERANGE, null, PriceRow.PRICE,
						Double.valueOf(123.45), PriceRow.PRODUCTID, productCode);
	}

	private User user(final String login)
	{
		if (login == null)
		{
			return null;
		}
		return UserManager.getInstance().getUserByLogin(login);
	}

	private EnumerationValue userGroup(final String code)
	{
		if (code == null)
		{
			return null;
		}

		return amwayEurope1PriceFactory.getUserPriceGroup(code);
	}

	private Product product(final String code)
	{
		if (code == null)
		{
			return null;
		}

		final Collection candidates = ProductManager.getInstance().getProductsByCode(code);
		if (candidates == null || candidates.isEmpty())
		{
			return null;
		}
		if (candidates.size() > 1)
		{
			throw new IllegalStateException("More than one product for code " + code + " have been found.");
		}
		return (Product) candidates.iterator().next();
	}

	private EnumerationValue productGroup(final String code)
	{
		if (code == null)
		{
			return null;
		}

		final EnumerationManager manager = EnumerationManager.getInstance();
		final EnumerationType type = manager.getEnumerationType(Europe1Constants.TYPES.PRICE_PRODUCT_GROUP);

		return manager.getEnumerationValue(type, code);
	}
}
