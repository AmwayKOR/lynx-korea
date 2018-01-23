/**
 *
 */
package com.amway.apac.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.user.UserService;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apacfacades.data.EnumData;
import com.amway.apacfacades.notification.data.AmwayApacNotificationData;


/**
 * @author Aaron Yong
 *
 */
public class AmwayApacNotificationPopulator implements Populator<AmwayNotificationModel, AmwayApacNotificationData>
{


	private static final String DATE_FORMAT = "dd/MM/yyyy";

	private Converter<HybrisEnumValue, EnumData> enumConverter;
	private DefaultGenericDao<AmwayNotificationUserActionModel> amwayNotificationUserActionDao;


	private UserService userService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final AmwayNotificationModel source, final AmwayApacNotificationData target) throws ConversionException
	{

		target.setLongDescription(source.getLongDescription());
		target.setShortDescription(source.getShortDescription());
		target.setCode(source.getCode());

		//		if (null != source.getDetailMessage())
		//		{
		//			target.setCmsComponentId(source.getDetailMessage().getUid());
		//		}

		if (null != source.getPublishDate())
		{
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			final String stringDate = simpleDateFormat.format(source.getPublishDate());
			target.setPublishDate(stringDate);
		}

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(AmwayNotificationUserActionModel.NOTIFICATION, source);
		params.put(AmwayNotificationUserActionModel.USER, userService.getCurrentUser());
		final List<AmwayNotificationUserActionModel> notificationMappings = amwayNotificationUserActionDao.find(params);

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
	 * @param amwayNotificationUserActionDao
	 *           the amwayNotificationUserActionDao to set
	 */
	public void setAmwayNotificationUserActionDao(
			final DefaultGenericDao<AmwayNotificationUserActionModel> amwayNotificationUserActionDao)
	{
		this.amwayNotificationUserActionDao = amwayNotificationUserActionDao;
	}

	/**
	 * @return the amwayNotificationUserActionDao
	 */
	public DefaultGenericDao<AmwayNotificationUserActionModel> getAmwayNotificationUserActionDao()
	{
		return amwayNotificationUserActionDao;
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
