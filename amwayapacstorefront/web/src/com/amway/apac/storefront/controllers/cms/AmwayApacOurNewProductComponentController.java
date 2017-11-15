package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commerceservices.url.UrlResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.core.model.components.AmwayApacOurNewProductComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacOurNewProductComponentModel}
 */
@Controller("AmwayApacOurNewProductComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacOurNewProductComponent)
public class AmwayApacOurNewProductComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacOurNewProductComponentModel>
{

	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacOurNewProductComponentModel component)
	{
		populateData(model, component);
	}
}
