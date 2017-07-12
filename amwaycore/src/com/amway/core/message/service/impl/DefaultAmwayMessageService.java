/**
 *
 */
package com.amway.core.message.service.impl;

import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.message.dao.AmwayMessageDao;
import com.amway.core.message.service.AmwayMessageService;
import com.amway.core.model.AmwayMessageModel;


/**
 * Default implementation
 */
public class DefaultAmwayMessageService implements AmwayMessageService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayMessageService.class);//NOPMD

	private AmwayMessageDao amwayMessageDao;

	/**
	 * To find the message for the given code.
	 *
	 * @param code
	 */
	@Override
	public String findMessage(final String code)
	{

		final List<AmwayMessageModel> messages = amwayMessageDao.findMessage(code);

		//this is to make sure it does not throw an error if no message is configured for the key.
		if (CollectionUtils.isEmpty(messages))
		{
			return StringUtils.EMPTY;
		}
		ServicesUtil.validateIfAnyResult(messages, "no valid  messages found for this code :" + code);
		ServicesUtil.validateIfSingleResult(messages, "more than one message found for this code :" + code, code);
		return messages.get(0).getMessage();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.core.message.service.AmwayMessageService#findMessage(java.lang.String, java.util.Locale)
	 */
	@Override
	public String findMessage(final String code, final Locale locale)
	{
		final List<AmwayMessageModel> messages = amwayMessageDao.findMessage(code);

		//this is to make sure it does not throw an error if no message is configured for the key.
		if (CollectionUtils.isEmpty(messages))
		{
			return StringUtils.EMPTY;
		}
		ServicesUtil.validateIfSingleResult(messages, "more than one message found for this code :" + code, code);
		return messages.get(0).getMessage(locale);

	}

	/**
	 * @return amwayMessageDao
	 */
	public AmwayMessageDao getAmwayMessageDao()
	{
		return amwayMessageDao;
	}

	/**
	 * @param amwayMessageDao the amwayMessageDao to set
	 */
	public void setAmwayMessageDao(final AmwayMessageDao amwayMessageDao)
	{
		this.amwayMessageDao = amwayMessageDao;
	}

}
