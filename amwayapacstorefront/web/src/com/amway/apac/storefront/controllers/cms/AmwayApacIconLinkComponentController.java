/**
 *
 */
package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacIconLinkComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link CMSLinkComponentModel}
 */
@Controller("AmwayApacIconLinkComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacIconLinkComponent)
public class AmwayApacIconLinkComponentController
		extends AmwayApacAbstractCMSLinkComponentController<AmwayApacIconLinkComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AmwayApacIconLinkComponentModel component)
	{
		populateLinkUrl(model, component);
	}

}