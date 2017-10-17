package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacButtonLinkComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link CMSLinkComponentModel}
 */
@Controller("AmwayApacButtonLinkComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacButtonLinkComponent)
public class AmwayApacButtonLinkComponentController
		extends AmwayApacAbstractCMSLinkComponentController<AmwayApacButtonLinkComponentModel>
{

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacButtonLinkComponentModel component)
	{
		populateLinkUrl(model, component);
	}
}
