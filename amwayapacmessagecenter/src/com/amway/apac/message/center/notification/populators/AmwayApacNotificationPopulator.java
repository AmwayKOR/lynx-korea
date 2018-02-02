package com.amway.apac.message.center.notification.populators;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.SOURCE_STRING;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.TARGET_STRING;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.text.SimpleDateFormat;
import java.util.Objects;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.services.AmwayApacNotificationService;


/**
 * Implementation of Notification Populator
 *
 * @author Aaron Yong
 *
 */
public class AmwayApacNotificationPopulator implements Populator<AmwayNotificationModel, AmwayApacNotificationData>
{
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

	private AmwayApacNotificationService amwayApacNotificationService;

	@Override
	public void populate(final AmwayNotificationModel source, final AmwayApacNotificationData target) throws ConversionException
	{
		validateParameterNotNullStandardMessage(SOURCE_STRING, source);
		validateParameterNotNullStandardMessage(TARGET_STRING, target);

		target.setLongDescription(source.getLongDescription());
		target.setShortDescription(source.getShortDescription());
		target.setCode(source.getCode());

		if (null != source.getPublishDate())
		{
			target.setPublishDate(simpleDateFormat.format(source.getPublishDate()));
		}

		populateNotificationStatus(source, target);
	}

	protected void populateNotificationStatus(final AmwayNotificationModel source, final AmwayApacNotificationData target)
	{
		final AmwayNotificationUserActionModel userAction = getAmwayApacNotificationService()
				.getNotificationActionForCurrentUser(source);

		if (Objects.nonNull(userAction))
		{
			target.setStatus(userAction.getStatus().getCode());
		}
		else
		{
			target.setStatus(AmwayNotificationUserActionStatus.UNREAD.getCode());
		}
	}

	/**
	 * @return the amwayApacNotificationService
	 */
	public AmwayApacNotificationService getAmwayApacNotificationService()
	{
		return amwayApacNotificationService;
	}

	/**
	 * @param amwayApacNotificationService
	 *           the amwayApacNotificationService to set
	 */
	public void setAmwayApacNotificationService(final AmwayApacNotificationService amwayApacNotificationService)
	{
		this.amwayApacNotificationService = amwayApacNotificationService;
	}
}
