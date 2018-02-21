package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacImageLinkComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for {@link AmwayApacImageLinkComponentModel}
 * 
 * @author Avnish Alaugh
 *
 */
@Controller("AmwayApacImageLinkComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacImageLinkComponent)
public class AmwayApacImageLinkComponentController
		extends AmwayApacAbstractCMSLinkComponentController<AmwayApacImageLinkComponentModel>
{

	/**
	 * Populates the url for the link in the view model.
	 *
	 * @param request
	 *           http request
	 * @param model
	 *           view model
	 * @param component
	 *           component
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AmwayApacImageLinkComponentModel component)
	{
		populateLinkUrl(model, component);
	}

}
