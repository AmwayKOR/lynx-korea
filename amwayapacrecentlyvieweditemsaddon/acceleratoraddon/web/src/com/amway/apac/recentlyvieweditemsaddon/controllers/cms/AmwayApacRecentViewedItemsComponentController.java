package com.amway.apac.recentlyvieweditemsaddon.controllers.cms;

import com.amway.apac.amwayapacrecentlyvieweditemsaddon.constants.AmwayapacrecentlyvieweditemsaddonConstants;
import com.amway.apac.recentlyvieweditemsaddon.model.AmwayApacRecentViewedItemsComponentModel;
import com.amway.apac.recentlyvieweditemsaddon.model.AmwayApacRecentViewedItemsComponentModel;
import com.amway.apac.recentlyvieweditemsaddon.services.AmwayApacProductSearchService;
import com.hybris.ymkt.recentvieweditemsservices.RecentViewedItemsService;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Govind on 12/8/2017.
 */
@Controller("AmwayApacRecentViewedItemsComponentController")
@RequestMapping(value = "/view/AmwayApacRecentViewedItemsComponentController")
public class AmwayApacRecentViewedItemsComponentController extends AbstractCMSAddOnComponentController<AmwayApacRecentViewedItemsComponentModel> {

    @Resource(name = "recentViewedItemsService")
    private RecentViewedItemsService recentViewedItemsService;

    @Resource(name = "amwayApacCommerceProductSearchService")
    private AmwayApacProductSearchService amwayApacProductSearchService;

    @Override
    protected String getAddonUiExtensionName(final AmwayApacRecentViewedItemsComponentModel component)
    {
        return AmwayapacrecentlyvieweditemsaddonConstants.EXTENSIONNAME;
    }

    @Override
    protected void fillModel(HttpServletRequest request, Model model, AmwayApacRecentViewedItemsComponentModel component) {
        model.addAttribute("recentlyViewedProducts", amwayApacProductSearchService.productCodesSearch(String.format("code_string:(%s)", String.join(" ", recentViewedItemsService.getRecentViewedProducts())), null));
    }
}
