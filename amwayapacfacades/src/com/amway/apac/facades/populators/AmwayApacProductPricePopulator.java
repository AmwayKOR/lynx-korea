package com.amway.apac.facades.populators;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import com.amway.facades.populators.AmwayProductPricePopulator;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;
import org.springframework.beans.factory.annotation.Required;


import java.util.Collection;

/**
 * Created by MY020221 on 9/28/2017.
 */
public class AmwayApacProductPricePopulator extends AmwayProductPricePopulator {
    protected static final String RETAIL_PRICE_GROUP = "amwayapaccore.defaultRetailPriceGroup";



    private CommercePriceService commercePriceService;
    private ModelService modelService;
    private SessionService sessionService;
    private UserService userService;
    private AmwayApacPriceDataFactory amwayApacPriceDataFactory;

    protected CommercePriceService getCommercePriceService() {
        return commercePriceService;
    }

    @Required
    public void setCommercePriceService(final CommercePriceService commercePriceService) {
        this.commercePriceService = commercePriceService;
    }

    /**
     * @return the modelService
     */
    public ModelService getModelService() {
        return modelService;
    }

    /**
     * @param modelService the modelService to set
     */
    @Required
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    public AmwayApacPriceDataFactory getAmwayApacPriceDataFactory() {
        return amwayApacPriceDataFactory;
    }

    public void setAmwayApacPriceDataFactory(AmwayApacPriceDataFactory amwayApacPriceDataFactory) {
        this.amwayApacPriceDataFactory = amwayApacPriceDataFactory;
    }

    protected PriceRowModel getPriceRow(final ProductModel product, String group) {
        final Collection<PriceRowModel> prices = product.getEurope1Prices();
        PriceRowModel priceRow = prices.stream().filter(p -> group.equalsIgnoreCase(p.getUg().getCode())).findFirst().get();
        return priceRow;
    }

    @Override
    public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException {
        final PriceRowModel priceRow = getPriceRow(productModel, Config.getParameter(DISTRIBUTOR_PRICE_GROUP));
        if (priceRow != null) {
            final PriceData priceData = amwayApacPriceDataFactory.create(priceRow);
            productData.setPrice(priceData);
        }

        final PriceRowModel retailPriceRow = getPriceRow(productModel, Config.getParameter(RETAIL_PRICE_GROUP));
        if (retailPriceRow != null) {
            final PriceData retailPriceData = amwayApacPriceDataFactory.create(retailPriceRow);
            productData.setRetailPrice(retailPriceData);
        }
    }
}
