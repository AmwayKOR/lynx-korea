package com.amway.apac.message.center.notification.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.services.AmwayApacNotificationService;
import com.amway.apacfacades.data.EnumData;


/**
 * Implementation of Notification Populator
 *
 * @author Aaron Yong
 *
 */
public class AmwayApacNotificationPopulator implements Populator<AmwayNotificationModel, AmwayApacNotificationData>
{
	private static final String DATE_FORMAT = "dd/MM/yyyy";

	private Converter<HybrisEnumValue, EnumData> enumConverter;
	private AmwayApacNotificationService amwayApacNotificationService;
	private UserService userService;

	@Override
	public void populate(final AmwayNotificationModel source, final AmwayApacNotificationData target) throws ConversionException
	{

		target.setLongDescription(source.getLongDescription());
		target.setShortDescription(source.getShortDescription());
		target.setCode(source.getCode());

		if (null != source.getPublishDate())
		{
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			final String stringDate = simpleDateFormat.format(source.getPublishDate());
			target.setPublishDate(stringDate);
		}

		final List<AmwayNotificationUserActionModel> notificationMappings = amwayApacNotificationService
				.getNotificationActionByUserAndNotification((CustomerModel) userService.getCurrentUser(), source);

		if (CollectionUtils.isNotEmpty(notificationMappings))
		{
			target.setStatus(enumConverter.convert(notificationMappings.iterator().next().getStatus()));
		}
		else
		{
			target.setStatus(enumConverter.convert(AmwayNotificationUserActionStatus.UNREAD));
		}

	}

	/**
	 * @param enumConverter
	 *           the enumConverter to set
	 */
	@Required
	public void setEnumConverter(final Converter<HybrisEnumValue, EnumData> enumConverter)
	{
		this.enumConverter = enumConverter;
	}

	/**
	 * @param lynxUserService
	 *           the lynxUserService to set
	 */
	@Required
	public void setUserService(final UserService lynxUserService)
	{
		this.userService = lynxUserService;
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

	/**
	 * @return the enumConverter
	 */
	public Converter<HybrisEnumValue, EnumData> getEnumConverter()
	{
		return enumConverter;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}
}
