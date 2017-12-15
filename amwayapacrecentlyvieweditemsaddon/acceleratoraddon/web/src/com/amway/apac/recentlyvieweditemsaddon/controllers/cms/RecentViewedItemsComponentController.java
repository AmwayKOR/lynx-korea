package com.amway.apac.recentlyvieweditemsaddon.controllers.cms;

import com.amway.apac.amwayapacrecentlyvieweditemsaddon.constants.AmwayapacrecentlyvieweditemsaddonConstants;
import com.amway.apac.recentlyvieweditemsaddon.model.RecentViewedItemsComponentModel;
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
@Controller("RecentViewedItemsComponentController")
@RequestMapping(value = "/view/RecentViewedItemsComponentController")
public class RecentViewedItemsComponentController extends AbstractCMSAddOnComponentController<RecentViewedItemsComponentModel> {

    @Resource(name = "recentViewedItemsService")
    private RecentViewedItemsService recentViewedItemsService;

    @Resource(name = "amwayApacCommerceProductSearchService")
    private AmwayApacProductSearchService amwayApacProductSearchService;

    @Override
    protected String getAddonUiExtensionName(final RecentViewedItemsComponentModel component)
    {
        return AmwayapacrecentlyvieweditemsaddonConstants.EXTENSIONNAME;
    }

    @Override
    protected void fillModel(HttpServletRequest request, Model model, RecentViewedItemsComponentModel component) {
        model.addAttribute("recentlyViewedProducts", amwayApacProductSearchService.productCodesSearch(String.format("code_string:(%s)", String.join(" ", recentViewedItemsService.getRecentViewedProducts())), null));
    }
}
