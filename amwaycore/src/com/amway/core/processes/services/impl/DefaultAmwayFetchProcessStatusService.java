/**
 *
 */
package com.amway.core.processes.services.impl;

import de.hybris.platform.acceleratorservices.email.CMSEmailPageService;
import de.hybris.platform.acceleratorservices.email.EmailGenerationService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.acceleratorservices.process.strategies.ProcessContextResolutionStrategy;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.amway.core.processes.dao.AmwayFetchProcessStatusDao;
import com.amway.core.processes.services.AmwayFetchProcessStatusService;


/**
 * Default implementation for to fetch the process status
 */
public class DefaultAmwayFetchProcessStatusService implements AmwayFetchProcessStatusService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayFetchProcessStatusService.class);
	private AmwayFetchProcessStatusDao fetchProcessStatusDao;
	private EmailService emailService;
	private ModelService modelService;
	private CMSEmailPageService cmsEmailPageService;
	private String frontendTemplateName;
	private ProcessContextResolutionStrategy contextResolutionStrategy;
	private EmailGenerationService emailGenerationService;

	/**
	 * To send the email for failed processes.
	 *
	 * @param processesStatusCode
	 * @param returnStatusCode
	 * @param cronjobEndDate
	 */
	@Override
	public boolean sendEmailForFailedProcesses(final String processesStatusCode, final String returnStatusCode,
			final Date cronjobEndDate)
	{
		final List<BusinessProcessModel> businessProcessModels = fetchProcessStatusDao
				.getListOfAllFailedProcessStatus(processesStatusCode, cronjobEndDate);
		if (!businessProcessModels.isEmpty())
		{
			for (final BusinessProcessModel businessProcessModel : businessProcessModels)
			{
				final OrderProcessModel orderProcessModel = (OrderProcessModel) businessProcessModel;
				orderProcessModel.setReturnRequestCode(returnStatusCode);

				final CatalogVersionModel contentCatalogVersion = getContextResolutionStrategy()
						.getContentCatalogVersion(businessProcessModel);
				if (contentCatalogVersion != null)
				{
					final EmailPageModel emailPageModel = getCmsEmailPageService()
							.getEmailPageForFrontendTemplate(getFrontendTemplateName(), contentCatalogVersion);

					if (emailPageModel != null)
					{
						final EmailMessageModel emailMessageModel = getEmailGenerationService()
								.generate(businessProcessModel, emailPageModel);

						if (emailMessageModel != null)
						{
							return getEmailService().send(emailMessageModel);
						}
						else
						{
							LOG.error("Failed to Generate email for process [" + businessProcessModel.getProcessDefinitionName() + "]");
						}
					}
				}
			}
		}
		return false;
	}


	/**
	 * @return fetchProcessStatusDao
	 */
	public AmwayFetchProcessStatusDao getFetchProcessStatusDao()
	{
		return fetchProcessStatusDao;
	}


	/**
	 * @param fetchProcessStatusDao the fetchProcessStatusDao to set
	 */
	public void setFetchProcessStatusDao(final AmwayFetchProcessStatusDao fetchProcessStatusDao)
	{
		this.fetchProcessStatusDao = fetchProcessStatusDao;
	}

	/**
	 * @return emailService
	 */
	public EmailService getEmailService()
	{
		return emailService;
	}

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(final EmailService emailService)
	{
		this.emailService = emailService;
	}

	/**
	 * @return modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return frontendTemplateName
	 */
	public String getFrontendTemplateName()
	{
		return frontendTemplateName;
	}

	/**
	 * @param frontendTemplateName the frontendTemplateName to set
	 */
	public void setFrontendTemplateName(final String frontendTemplateName)
	{
		this.frontendTemplateName = frontendTemplateName;
	}

	/**
	 * @return contextResolutionStrategy
	 */
	public ProcessContextResolutionStrategy getContextResolutionStrategy()
	{
		return contextResolutionStrategy;
	}

	/**
	 * @param contextResolutionStrategy the contextResolutionStrategy to set
	 */
	public void setContextResolutionStrategy(final ProcessContextResolutionStrategy contextResolutionStrategy)
	{
		this.contextResolutionStrategy = contextResolutionStrategy;
	}

	/**
	 * @return emailGenerationService
	 */
	public EmailGenerationService getEmailGenerationService()
	{
		return emailGenerationService;
	}

	/**
	 * @param emailGenerationService the emailGenerationService to set
	 */
	public void setEmailGenerationService(final EmailGenerationService emailGenerationService)
	{
		this.emailGenerationService = emailGenerationService;
	}

	/**
	 * @return cmsEmailPageService
	 */
	public CMSEmailPageService getCmsEmailPageService()
	{
		return cmsEmailPageService;
	}

	/**
	 * @param cmsEmailPageService the cmsEmailPageService to set
	 */
	public void setCmsEmailPageService(final CMSEmailPageService cmsEmailPageService)
	{
		this.cmsEmailPageService = cmsEmailPageService;
	}
}
