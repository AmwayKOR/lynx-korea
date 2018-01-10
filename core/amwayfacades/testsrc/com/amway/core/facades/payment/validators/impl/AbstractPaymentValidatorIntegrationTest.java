/**
 *
 */
package com.amway.core.facades.payment.validators.impl;

import com.amway.core.cart.data.AmwayCCPaymentInfoData;
import com.amway.core.cart.data.AmwayCashPaymentInfoData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.model.AmwayCashPaymentInfoModel;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.order.data.PaymentModeData;
import com.amway.core.payment.service.AmwaySplitPaymentsService;
import com.amway.core.payment.service.impl.DefaultAmwayPaymentModeService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.jalo.CoreBasicDataCreator;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import javax.annotation.Resource;
import java.util.*;


/**
 *
 */
@SuppressWarnings("deprecation")
public abstract class AbstractPaymentValidatorIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(AbstractPaymentValidatorIntegrationTest.class);
	protected static final String CUSTOMR_UID1 = "ahertz";

	protected static final String TEST_BASESITE_UID = "amway";
	protected static final String CASH_PAYMENT_MODE = "cash";
	protected static final String CREDIT_CARD_PAYMENT_MODE = "creditCard";
	protected static final double CURRENT_PAYMENT_AMOUNT = 500d;

	protected static final String TEST_DATA_PATH = "/amwayfacades/test/paymentsTestData";

	@Resource(name = "defaultAmwayPaymentModeService")
	protected DefaultAmwayPaymentModeService amwayPaymentModeService;
	@Resource
	protected SessionService sessionService;
	@Resource
	protected UserService userService;
	@Resource
	protected OrderService orderService;
	@Resource
	protected ProductService productService;
	@Resource
	protected ModelService modelService;
	@Resource
	protected BaseSiteService baseSiteService;
	@Resource
	protected UnitService unitService;
	@Resource
	protected CatalogVersionService catalogVersionService;
	@Resource
	protected CommerceCartService commerceCartService;
	@Resource
	protected AmwaySplitPaymentsService amwaySplitPaymentsService;

	protected CartModel cart;

	protected AmwayProfileResponseData amwayProfileData;

	protected AmwayCashPaymentInfoData amwayCashPaymentInfoData;

	protected Errors errors;

	protected PaymentModeData cashPaymentModeData;

	protected AmwayCCPaymentInfoData amwayCCPaymentInfoData;

	protected PaymentModeData ccPaymentModeData;

	protected void prepare() throws Exception
	{
		setSalesApplicationInSession(SalesApplication.POS);
		setUpBasicData();
		setUpUserData();
		setUpCartData();
		setUpPaymentsData();
		MockitoAnnotations.initMocks(this);
	}


	protected void setSalesApplicationInSession(final SalesApplication salesApplication)
	{
		sessionService.setAttribute("currentChannel", salesApplication);
	}

	protected void setUpBasicData() throws Exception
	{
		LOG.info("Creating basic test data ..");
		userService.setCurrentUser(userService.getAdminUser());
		new CoreBasicDataCreator().createEssentialData(Collections.EMPTY_MAP, null); // importing test csv
		importCsv(TEST_DATA_PATH + "/basicTestData.csv", "utf-8");
	}

	protected void setUpUserData() throws Exception
	{
		LOG.info("Creating test data for user ..");
		importCsv(TEST_DATA_PATH + "/userTestData.csv", "utf-8");
	}

	protected void setUpCartData() throws Exception
	{
		LOG.info("Creating cart test data ..");
		importCsv(TEST_DATA_PATH + "/cartTestData.csv", "utf-8");
		setCurrentBaseSite();
		cart = getCartForUser(CUSTOMR_UID1);
		addNewEntryToCart(cart);
	}

	protected void setUpPaymentsData() throws ImpExException
	{
		importCsv(TEST_DATA_PATH + "/paymentModeTestData.csv", "utf-8");

		cashPaymentModeData = new PaymentModeData();
		cashPaymentModeData.setCode(CASH_PAYMENT_MODE);
		cashPaymentModeData.setActive(true);
		amwayCashPaymentInfoData = new AmwayCashPaymentInfoData();
		final String randomId = "123456789";
		amwayCashPaymentInfoData.setAmount(CURRENT_PAYMENT_AMOUNT);
		amwayCashPaymentInfoData.setPaymentMode(cashPaymentModeData);
		amwayCashPaymentInfoData.setTransactionid(randomId);
		ccPaymentModeData = new PaymentModeData();
		ccPaymentModeData.setCode(CREDIT_CARD_PAYMENT_MODE);
		ccPaymentModeData.setActive(true);
		amwayCCPaymentInfoData = new AmwayCCPaymentInfoData();
		amwayCCPaymentInfoData.setAmount(CURRENT_PAYMENT_AMOUNT);
		amwayCCPaymentInfoData.setPaymentMode(ccPaymentModeData);
		amwayCCPaymentInfoData.setMaskedcardnumber(randomId);
		amwayCCPaymentInfoData.setTransactionid(randomId);
		amwayCCPaymentInfoData.setCardtype("VISA");

		errors = new BeanPropertyBindingResult(amwayCashPaymentInfoData, "amwayPaymentInfoData");
	}

	protected CartModel getCartForUser(final String uid)
	{
		final CustomerModel userModel = getUserForUid(uid);
		final Collection<CartModel> cartModels = userModel.getCarts();
		Assert.assertNotNull(cartModels);
		Assert.assertTrue(cartModels.size() > 0);
		final CartModel cart = cartModels.iterator().next();
		setCartType(cart, AmwayCartType.POS);
		SetAccountInCart(userModel, cart);
		modelService.save(cart);
		return cart;
	}

	protected void addNewEntryToCart(final CartModel cart) throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cart);
		parameter.setProduct(productModel);
		parameter.setQuantity(3);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);
		// Add new entry
		commerceCartService.addToCart(parameter);
	}

	protected void setCurrentBaseSite()
	{
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);
	}

	protected void setCartType(final CartModel cart, final AmwayCartType cartType)
	{
		cart.setType(cartType);
	}

	protected void SetAccountInCart(final CustomerModel userModel, final CartModel cart)
	{
		Assert.assertNotNull(userModel.getAccounts());
		Assert.assertTrue(userModel.getAccounts().size() > 0);
		cart.setAccount(userModel.getAccounts().iterator().next());
	}

	protected CustomerModel getUserForUid(final String uid)
	{
		final CustomerModel userModel = (CustomerModel) userService.getUserForUID(uid);
		return userModel;
	}

	protected PaymentInfoModel buildPaymentInfo(final AbstractOrderModel cart, final UserModel userModel)
	{
		final AmwayCashPaymentInfoModel cashPaymentInfoModel = modelService.create(AmwayCashPaymentInfoModel.class);
		cashPaymentInfoModel.setUser(userModel);
		cashPaymentInfoModel.setCode(cart.getCode() + UUID.randomUUID().toString());
		cashPaymentInfoModel.setBillingAddress(cart.getPaymentAddress());
		cart.setPaymentInfo(cashPaymentInfoModel);
		modelService.saveAll();
		return cashPaymentInfoModel;
	}


	protected Map<String, List<AmwayPaymentModeData>> getSupportedPaymentModesCombinations(final AbstractOrderModel cart,
			final AmwayProfileResponseData amwayProfileData, final boolean skipSelectedMode, final String currentPaymentMode)
	{
		return amwayPaymentModeService.getSupportedPaymentModesCombination(cart, amwayProfileData, skipSelectedMode,
				currentPaymentMode);
	}



}
