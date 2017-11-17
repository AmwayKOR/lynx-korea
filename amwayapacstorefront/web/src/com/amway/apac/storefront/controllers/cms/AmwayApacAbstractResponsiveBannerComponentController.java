package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.acceleratorfacades.device.ResponsiveMediaFacade;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commerceservices.url.UrlResolver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;

import com.amway.apac.storefront.components.model.AmwayApacAbstractTextLinkImageComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Abstract Controller for {@link AmwayApacAbstractTextLinkImageComponentModel}
 *
 * @author Avnish Alaugh
 *
 */
public abstract class AmwayApacAbstractResponsiveBannerComponentController<T extends AmwayApacAbstractTextLinkImageComponentModel>
		extends AbstractAcceleratorCMSComponentController<T>
{
	@Resource(name = "responsiveMediaFacade")
	private ResponsiveMediaFacade responsiveMediaFacade;

	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	/**
	 * Populates the media list and link url
	 *
	 * @param model
	 *           view model
	 * @param component
	 *
	 */
	protected void populateData(final Model model, final T component)
	{

		if (null != component.getMedia())
		{
			final List<ImageData> mediaDataList = getResponsiveMediaFacade().getImagesFromMediaContainer(component.getMedia());
			model.addAttribute(ControllerConstants.ModelParameters.MEDIAS_STRING, mediaDataList);
		}
		if (null != component.getLink())
		{
			model.addAttribute(ControllerConstants.ModelParameters.LINK_URL_STRING,
					getAmwayApacCmsLinkComponentUrlResolver().resolve(component.getLink()));
		}
	}

	/**
	 * @return the responsiveMediaFacade
	 */
	public ResponsiveMediaFacade getResponsiveMediaFacade()
	{
		return responsiveMediaFacade;
	}

	/**
	 * @param responsiveMediaFacade
	 *           the responsiveMediaFacade to set
	 */
	public void setResponsiveMediaFacade(final ResponsiveMediaFacade responsiveMediaFacade)
	{
		this.responsiveMediaFacade = responsiveMediaFacade;
	}

	/**
	 * @return the amwayApacCmsLinkComponentUrlResolver
	 */
	public UrlResolver<CMSLinkComponentModel> getAmwayApacCmsLinkComponentUrlResolver()
	{
		return amwayApacCmsLinkComponentUrlResolver;
	}

	/**
	 * @param amwayApacCmsLinkComponentUrlResolver
	 *           the amwayApacCmsLinkComponentUrlResolver to set
	 */
	public void setAmwayApacCmsLinkComponentUrlResolver(
			final UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver)
	{
		this.amwayApacCmsLinkComponentUrlResolver = amwayApacCmsLinkComponentUrlResolver;
	}
}
