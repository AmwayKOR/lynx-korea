package com.amway.apac.facades.product.impl;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by MY020221 on 2/2/2018.
 */
@IntegrationTest
public class DefaultAmwayApacPriceDataFactoryIntTest extends ServicelayerTest{

    private static final String TEST_ABO_ID = "testuser";

    private static final String TEST_PRODUCT_CODE = "100099A";

    private static final double TEST_RETAIL_PRICE = 46.08;

    private static final double TEST_POINT_VALUE = 36;

    private static final double TEST_BUSINESS_VOLUME = 36;

    private static final String TEST_CURRENCY_ISO = "USD";

    private static final String TEST_GROUP = "AmwayDefaultRetailPriceGroup";

    @Resource
    private UserService userService;

    @Resource
    private SessionService sessionService;

    @Resource
    private ProductService productService;

    private ProductModel product;

    @Resource(name = "defaultAmwayApacPriceDataFactory")
    private AmwayApacPriceDataFactory amwayApacPriceDataFactory;

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
    }

    @Test
    public void testCreate() throws Exception {
        final Collection<PriceRowModel> prices = product.getEurope1Prices();
        final PriceRowModel priceRow = prices.stream().filter(p -> TEST_GROUP.equalsIgnoreCase(p.getUg().getCode())).findFirst().get();
        PriceData priceData = amwayApacPriceDataFactory.create(priceRow);

        Assert.assertNotNull(priceData);

        Assert.assertNotNull(priceData.getValue());
        Assert.assertEquals(priceData.getValue().doubleValue(), TEST_RETAIL_PRICE, 0.0);

        Assert.assertNotNull(priceData.getCurrencyIso());
        Assert.assertEquals(priceData.getCurrencyIso(), TEST_CURRENCY_ISO);


        Assert.assertNotNull(priceData.getAmwayValue());

        Assert.assertNotNull(priceData.getAmwayValue().getPointValue());
        Assert.assertEquals(priceData.getAmwayValue().getPointValue(), TEST_POINT_VALUE, 0.0);

        Assert.assertNotNull(priceData.getAmwayValue().getBusinessVolume());
        Assert.assertEquals(priceData.getAmwayValue().getBusinessVolume(), TEST_BUSINESS_VOLUME, 0.0);
    }
}