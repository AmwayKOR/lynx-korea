package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commerceservices.url.UrlResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.core.model.components.AmwayApacShopByCategoryComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacShopByCategoryComponentModel}
 *
 * @author Parvesh Goyal
 */
@Controller("AmwayApacShopByCategoryComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacShopByCategoryComponent)
public class AmwayApacShopByCategoryComponentController
		extends AbstractAcceleratorCMSComponentController<AmwayApacShopByCategoryComponentModel>
{

	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	/**
	 * Populating the link url and other attributes in shop by category component.
	 *
	 * @param request
	 *           http request
	 * @param model
	 *           view model
	 * @param component
	 *           component
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacShopByCategoryComponentModel component)
	{
		if (null != component.getLink())
		{
			model.addAttribute(ControllerConstants.ModelParameters.LINK_URL,
					amwayApacCmsLinkComponentUrlResolver.resolve(component.getLink()));
		}

		model.addAttribute(AmwayApacShopByCategoryComponentModel.BANNER, component.getBanner());
		model.addAttribute(AmwayApacShopByCategoryComponentModel.LINK, component.getLink());
	}
}
