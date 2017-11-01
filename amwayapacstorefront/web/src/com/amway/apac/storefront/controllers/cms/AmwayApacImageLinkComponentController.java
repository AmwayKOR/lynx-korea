/**
 *
 */
package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacImageLinkComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;

/**
 * @author avnishalaugh
 *
 */
@Controller("AmwayApacImageLinkComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacImageLinkComponent)
public class AmwayApacImageLinkComponentController
		extends AmwayApacAbstractCMSLinkComponentController<AmwayApacImageLinkComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AmwayApacImageLinkComponentModel component)
	{
		populateLinkUrl(model, component);

	}

}
