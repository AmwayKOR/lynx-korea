package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commerceservices.url.UrlResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacPathwayToSuccessComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacPathwayToSuccessComponentModel}
 */
@Controller("AmwayApacPathwayToSuccessComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacPathwayToSuccessComponent)
public class AmwayApacPathwayToSuccessComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacPathwayToSuccessComponentModel>
{

	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacPathwayToSuccessComponentModel component)
	{
		populateData(model, component);
	}
}
