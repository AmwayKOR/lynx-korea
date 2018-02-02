package com.amway.apac.facades.product.populators;

import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPricePopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by MY020221 on 1/30/2018.
 */
@IntegrationTest
public class AmwayApacProductPricePopulatorIntTest extends ServicelayerTest{

    private static final String TEST_ABO_ID = "testuser";

    private static final String TEST_PRODUCT_CODE = "100099A";

    private static final double TEST_RETAIL_PRICE = 46.08;

    private static final double TEST_ABO_PRICE = 35.99;

    private static final double TEST_POINT_VALUE = 36;

    private static final double TEST_BUSINESS_VOLUME = 36;

    @Resource
    private UserService userService;

    @Resource
    private SessionService sessionService;

    @Resource
    private ProductService productService;

    @Resource(name = "amwayProductPricePopulator")
    private AmwayApacProductPricePopulator amwayApacProductPricePopulator;

    @Resource(name = "productPricePopulator")
    private ProductPricePopulator productPricePopulator;

    @Resource
    private AmwayAccountService amwayAccountService;

    private ProductModel product;

    private ProductData productData;

    @Before
    public void setUp() throws Exception {
        userService.setCurrentUser(userService.getAdminUser());
        importCsv("/amwayapacfacades/test/testCommercePrice.csv", "utf-8");
        sessionService.executeInLocalView(new SessionExecutionBody()
        {
            @Override
            public void executeWithoutResult()
            {
                product = productService.getProductForCode(TEST_PRODUCT_CODE);
            }
        });
        productData = new ProductData();
    }

    @Test
    public void testPopulateABOPrice() throws Exception {
        final AmwayAccountModel accountForUid = amwayAccountService.findAccount(TEST_ABO_ID);
        sessionService.getCurrentSession().setAttribute(AmwaycoreConstants.SessionVariables.ACCOUNT, accountForUid);
        userService.setCurrentUser(userService.getUserForUID(TEST_ABO_ID));
        sessionService.setAttribute(Europe1Constants.PARAMS.UPG, UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.ABO_USER_PRICE_GROUP));

        productPricePopulator.populate(product, productData);
        amwayApacProductPricePopulator.populate(product, productData);

        Assert.assertNotNull(productData.getPrice());

        Assert.assertNotNull(productData.getPrice().getAmwayValue());

        Assert.assertNotNull(productData.getPrice().getAmwayValue().getPointValue());
        Assert.assertEquals(productData.getPrice().getAmwayValue().getPointValue(), TEST_POINT_VALUE, 0.0);

        Assert.assertNotNull(productData.getPrice().getAmwayValue().getBusinessVolume());
        Assert.assertEquals(productData.getPrice().getAmwayValue().getBusinessVolume(), TEST_BUSINESS_VOLUME, 0.0);

        Assert.assertNotNull(productData.getPrice().getValue());
        Assert.assertEquals(productData.getPrice().getValue().doubleValue(), TEST_ABO_PRICE, 0.0);

        Assert.assertNotNull(productData.getRetailPrice().getValue().doubleValue());
        Assert.assertEquals(productData.getRetailPrice().getValue().doubleValue(), TEST_RETAIL_PRICE, 0.0);
    }

    @Test
    public void testPopulateRetailPrice() throws Exception {
        userService.setCurrentUser(userService.getAnonymousUser());
        sessionService.setAttribute(Europe1Constants.PARAMS.UPG, UserPriceGroup.valueOf(AmwaycoreConstants.PriceGroups.RETAIL_PRICE_GROUP));

        productPricePopulator.populate(product, productData);
        amwayApacProductPricePopulator.populate(product, productData);

        Assert.assertNotNull(productData.getPrice());
        Assert.assertEquals(productData.getPrice().getValue().doubleValue(), TEST_RETAIL_PRICE, 0.0);

        Assert.assertNull(productData.getPrice().getAmwayValue());
        Assert.assertNull(productData.getRetailPrice());
    }
}