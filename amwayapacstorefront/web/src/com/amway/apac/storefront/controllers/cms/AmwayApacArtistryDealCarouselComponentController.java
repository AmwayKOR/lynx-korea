package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.commercefacades.product.data.ImageData;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacArtistryDealCarouselComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacArtistryDealCarouselComponentModel}
 *
 * @author Parvesh Goyal
 */
@Controller("AmwayApacArtistryDealCarouselComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacArtistryDealCarouselComponent)
public class AmwayApacArtistryDealCarouselComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacArtistryDealCarouselComponentModel>
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
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacArtistryDealCarouselComponentModel component)
	{
		populateData(model, component);

		if ((null != component.getSecondaryImage()) && (null != component.getSecondaryImage().getMedia()))
		{
			final List<ImageData> mediaDataList = getResponsiveMediaFacade()
					.getImagesFromMediaContainer(component.getSecondaryImage().getMedia());

			model.addAttribute(ControllerConstants.ModelParameters.SECONDARY_MEDIAS_STRING, mediaDataList);
		}

		if (null != component.getSecondaryLink())
		{
			model.addAttribute(ControllerConstants.ModelParameters.SECONDARY_LINK_URL_STRING,
					getAmwayApacCmsLinkComponentUrlResolver().resolve(component.getSecondaryLink()));
		}
	}
}
