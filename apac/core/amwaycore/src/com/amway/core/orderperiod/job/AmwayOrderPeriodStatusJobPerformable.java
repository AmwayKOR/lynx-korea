package com.amway.core.orderperiod.job;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.orderperiod.services.impl.DefaultAmwayOrderPeriodService;


/**
 * Job for order period status
 */
public class AmwayOrderPeriodStatusJobPerformable extends AbstractJobPerformable<CronJobModel>
{

	private I18NService i18nService;
	private DefaultAmwayOrderPeriodService orderPeriodService;
	private EmailService emailService;
	private ModelService modelService;




	/**
	 * To perform all active order period status info.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public PerformResult perform(final CronJobModel cronJobModel)
	{


		final List<AmwayOrderPeriodModel> orderPeriods = orderPeriodService.findAllOrderPeriodsForSite();
		final Calendar today = Calendar.getInstance(getI18nService().getCurrentLocale());
		final Calendar tomorrow = Calendar.getInstance(getI18nService().getCurrentLocale());
		tomorrow.add(Calendar.DAY_OF_YEAR, 1);

		final List<EmailAddressModel> emailToList = new ArrayList<EmailAddressModel>();
		if (StringUtils.isNotEmpty(cronJobModel.getEmailAddress()))
		{
			final String[] emailToStrings = cronJobModel.getEmailAddress().split(",");
			for (final String email : emailToStrings)
			{
				final EmailAddressModel emailTo = getEmailService().getOrCreateEmailAddressForEmail(email, email);
				emailTo.setEmailAddress(email);
				emailToList.add(emailTo);
			}
		}

		final EmailAddressModel emailFrom = getEmailService().getOrCreateEmailAddressForEmail(
				Config.getString("eom.fromAddress.email", "aiu0256@amway.com"), "AmwayOrderPeriodStatusJob");

		for (final AmwayOrderPeriodModel orderPeriod : orderPeriods)
		{
			final Date startDate = orderPeriod.getStartDate();
			if ((startDate.before(tomorrow.getTime()) || startDate.compareTo(tomorrow.getTime()) == 0)
					&& AmwayPeriodTypeEnum.INACTIVE.equals(orderPeriod.getStatus()))
			{
				orderPeriod.setStatus(AmwayPeriodTypeEnum.ACTIVE);
				getModelService().save(orderPeriod);

				final EmailMessageModel msgModel = new EmailMessageModel();
				msgModel.setToAddresses(emailToList);
				msgModel.setFromAddress(emailFrom);
				msgModel.setReplyToAddress(emailFrom.getEmailAddress());

				msgModel.setBody(orderPeriod.getCode());
				msgModel.setSubject(Config.getParameter("orderPeriod.status.subject"));
				getEmailService().send(msgModel);
				continue;
			}

			if (AmwayPeriodTypeEnum.ACTIVE.equals(orderPeriod.getStatus()) && (orderPeriod.getEndDate().before(today.getTime())))
			{
				orderPeriod.setStatus(AmwayPeriodTypeEnum.CLOSED);
				getModelService().save(orderPeriod);
				final EmailMessageModel msgModel = new EmailMessageModel();
				msgModel.setToAddresses(emailToList);
				msgModel.setFromAddress(emailFrom);
				msgModel.setReplyToAddress(emailFrom.getEmailAddress());
				msgModel.setBody(orderPeriod.getCode());
				msgModel.setSubject(Config.getParameter("orderPeriod.status.subject"));
				getEmailService().send(msgModel);
				//orderPeriodService.sendClosedOrderPeriodToMagic(orderPeriod);
				continue;
			}
		}

		cronJobModel.setAlternativeDataSourceID(String.valueOf(today.getTimeInMillis()));
		modelService.save(cronJobModel);
		modelService.refresh(cronJobModel);
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}






	/**
	 * @return i18nService
	 */
	public I18NService getI18nService()
	{
		return i18nService;
	}


	/**
	 * @param i18nService
	 *           the i18nService to set
	 */
	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}


	/**
	 * @return orderPeriodService
	 */
	public DefaultAmwayOrderPeriodService getOrderPeriodService()
	{
		return orderPeriodService;
	}


	/**
	 * @param orderPeriodService
	 *           the orderPeriodService to set
	 */
	public void setOrderPeriodService(final DefaultAmwayOrderPeriodService orderPeriodService)
	{
		this.orderPeriodService = orderPeriodService;
	}


	/**
	 * @return emailService
	 */
	public EmailService getEmailService()
	{
		return emailService;
	}

	/**
	 * @param emailService
	 *           the emailService to set
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
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
