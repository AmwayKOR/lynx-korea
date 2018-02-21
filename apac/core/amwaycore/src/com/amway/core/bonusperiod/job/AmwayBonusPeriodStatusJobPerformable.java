package com.amway.core.bonusperiod.job;

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
import org.apache.log4j.Logger;

import com.amway.core.bonusperiod.services.impl.DefaultAmwayBonusPeriodService;
import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayBonusPeriodModel;



/**
 * Implementation for Amway Bonus Period Status Job
 */
public class AmwayBonusPeriodStatusJobPerformable extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(AmwayBonusPeriodStatusJobPerformable.class);
	private I18NService i18nService;
	private DefaultAmwayBonusPeriodService bonusPeriodService;
	private EmailService emailService;
	private ModelService modelService;

	/**
	 * {@link #perform(de.hybris.platform.cronjob.model.CronJobModel)}
	 */
	@Override
	public PerformResult perform(final CronJobModel cronJobModel)
	{
		final List<AmwayBonusPeriodModel> bonusPeriods = bonusPeriodService.findAllBonusPeriodsForSite();
		final Calendar today = Calendar.getInstance(getI18nService().getCurrentLocale());
		final Calendar tomorrow = Calendar.getInstance(getI18nService().getCurrentLocale());
		tomorrow.add(Calendar.DAY_OF_YEAR, 1);

		final EmailAddressModel emailFrom = getEmailService().getOrCreateEmailAddressForEmail(
				Config.getString("eom.fromAddress.email", "aiu0256@amway.com"), "AmwayOrderPeriodStatusJob");

		final List<EmailAddressModel> emailToList = new ArrayList<EmailAddressModel>();

		if (StringUtils.isNotEmpty(cronJobModel.getEmailAddress()))
		{
			final String[] emailToStrings = cronJobModel.getEmailAddress().split(",");
			for (final String email : emailToStrings)
			{
				final EmailAddressModel emailTo = new EmailAddressModel();
				emailTo.setEmailAddress(email);
				emailToList.add(emailTo);
			}
		}

		AmwayBonusPeriodModel aboutToExpireCutoffDatePeriod = null;
		for (final AmwayBonusPeriodModel bonusPeriod : bonusPeriods)
		{
			final Date startDate = bonusPeriod.getStartDate();
			final Date cutoffDate = bonusPeriod.getCutoffDate();
			if ((startDate.before(tomorrow.getTime()) || startDate.compareTo(tomorrow.getTime()) == 0)
					&& AmwayPeriodTypeEnum.INACTIVE.equals(bonusPeriod.getStatus()))
			{
				bonusPeriod.setStatus(AmwayPeriodTypeEnum.ACTIVE);
				getModelService().save(bonusPeriod);
				final EmailMessageModel msgModel = new EmailMessageModel();
				msgModel.setToAddresses(emailToList);
				msgModel.setFromAddress(emailFrom);
				msgModel.setBody(bonusPeriod.getCode());
				msgModel.setReplyToAddress(emailFrom.getEmailAddress());
				msgModel.setSubject(Config.getParameter("bonusPeriod.status.subject"));
				getEmailService().send(msgModel);
				continue;
			}

			if (AmwayPeriodTypeEnum.ACTIVE.equals(bonusPeriod.getStatus()) && (bonusPeriod.getEndDate().before(today.getTime())))
			{
				LOG.info("Closing Bonus Period : " + bonusPeriod.getCode());
				bonusPeriod.setStatus(AmwayPeriodTypeEnum.CLOSED);
				getModelService().save(bonusPeriod);
				getModelService().refresh(bonusPeriod);
			}

			if ((cutoffDate.before(tomorrow.getTime()) || cutoffDate.compareTo(tomorrow.getTime()) == 0)
					&& AmwayPeriodTypeEnum.ACTIVE.equals(bonusPeriod.getStatus()))
			{
				aboutToExpireCutoffDatePeriod = bonusPeriod;
			}
		}

		if (aboutToExpireCutoffDatePeriod != null)
		{
			final Calendar newPeriodCalendar = Calendar.getInstance(getI18nService().getCurrentLocale());
			newPeriodCalendar.setTime(aboutToExpireCutoffDatePeriod.getEndDate());
			newPeriodCalendar.add(Calendar.DAY_OF_YEAR, 1);

			for (final AmwayBonusPeriodModel bonusPeriod : bonusPeriods)
			{
				final Date startDate = bonusPeriod.getStartDate();
				if ((startDate.before(newPeriodCalendar.getTime()) || startDate.compareTo(newPeriodCalendar.getTime()) == 0)
						&& AmwayPeriodTypeEnum.INACTIVE.equals(bonusPeriod.getStatus()))
				{
					LOG.info("Activating Bonus Period : " + bonusPeriod.getCode() + " as the current period "
							+ aboutToExpireCutoffDatePeriod.getCode() + " has reached CUTOFF date");
					bonusPeriod.setStatus(AmwayPeriodTypeEnum.ACTIVE);
					getModelService().save(bonusPeriod);
					getModelService().refresh(bonusPeriod);
					break;
				}
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
	 * @return bonusPeriodService
	 */
	public DefaultAmwayBonusPeriodService getBonusPeriodService()
	{
		return bonusPeriodService;
	}

	/**
	 * @param bonusPeriodService
	 *           the bonusPeriodService to set
	 */
	public void setBonusPeriodService(final DefaultAmwayBonusPeriodService bonusPeriodService)
	{
		this.bonusPeriodService = bonusPeriodService;
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
