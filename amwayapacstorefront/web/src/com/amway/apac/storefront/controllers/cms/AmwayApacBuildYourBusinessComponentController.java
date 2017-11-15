package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commerceservices.url.UrlResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacBuildYourBusinessComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacBuildYourBusinessComponentModel}
 */
@Controller("AmwayApacBuildYourBusinessComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacBuildYourBusinessComponent)
public class AmwayApacBuildYourBusinessComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacBuildYourBusinessComponentModel>
{

	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacBuildYourBusinessComponentModel component)
	{
		populateData(model, component);
	}
}
