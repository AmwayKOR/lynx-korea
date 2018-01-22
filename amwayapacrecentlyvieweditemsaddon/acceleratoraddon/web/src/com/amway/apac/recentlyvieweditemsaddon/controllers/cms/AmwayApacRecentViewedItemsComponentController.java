package com.amway.apac.recentlyvieweditemsaddon.controllers.cms;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.amwayapacrecentlyvieweditemsaddon.constants.AmwayapacrecentlyvieweditemsaddonConstants;
import com.amway.apac.facades.product.AmwayApacProductFacade;
import com.amway.apac.recentlyvieweditemsaddon.model.AmwayApacRecentViewedItemsComponentModel;
import com.hybris.ymkt.recentvieweditemsservices.RecentViewedItemsService;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.data.ProductData;

/**
 * Recently viewed component controller
 * 
 * Created by Govind on 12/8/2017.
 */
@Controller("AmwayApacRecentViewedItemsComponentController")
@RequestMapping(value = "/view/AmwayApacRecentViewedItemsComponentController")
public class AmwayApacRecentViewedItemsComponentController extends AbstractCMSAddOnComponentController<AmwayApacRecentViewedItemsComponentModel> {

	/** The Constant LOGGER to log events at class level. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacRecentViewedItemsComponentController.class);
	
    /** The recent viewed items service. */
    @Resource(name = "recentViewedItemsService")
    private RecentViewedItemsService recentViewedItemsService;
    
    /** The amway apac product facade. */
    @Resource(name = "amwayApacProductFacade")
    private AmwayApacProductFacade amwayApacProductFacade;

    @Override
    protected String getAddonUiExtensionName(final AmwayApacRecentViewedItemsComponentModel component)
    {
        return AmwayapacrecentlyvieweditemsaddonConstants.EXTENSIONNAME;
    }

    /**
     * Gets recently viewed product data to use in component.
     *
     * @param request the request
     * @param model the model
     * @param component the component
     */
    @Override
    protected void fillModel(HttpServletRequest request, Model model, AmwayApacRecentViewedItemsComponentModel component) {
    	
    	final List<ProductData> recentlyViewedProducts = amwayApacProductFacade.getRecentlyViewedProductData(recentViewedItemsService.getRecentViewedProducts());
    	
		if (CollectionUtils.isNotEmpty(recentlyViewedProducts))
		{
			LOGGER.info(new StringBuilder(100).append("Found ").append(recentlyViewedProducts.size())
					.append(" recently viewed products.").toString());
			
			model.addAttribute("recentlyViewedProducts", recentlyViewedProducts);
		}
		else
		{
			LOGGER.info(new StringBuilder(50).append("No recently viewed product found.").toString());
		}
    }
}
