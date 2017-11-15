package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacAccountProfileBarLinkComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link CMSLinkComponentModel}
 */
@Controller("AmwayApacAccountProfileBarLinkComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacAccountProfileBarLinkComponent)
public class AmwayApacAccountProfileBarLinkComponentController
		extends AmwayApacAbstractCMSLinkComponentController<AmwayApacAccountProfileBarLinkComponentModel>
{

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacAccountProfileBarLinkComponentModel component)
	{
		populateLinkUrl(model, component);
	}
}

