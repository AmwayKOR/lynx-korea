package com.amway.core.message.dao;

import java.util.List;

import com.amway.core.model.AmwayMessageModel;


/**
 * Data access to {@link com.amway.core.model.AmwayOrderPeriodModel}
 */
public interface AmwayMessageDao
{
	/**
	 * find message for the given code
	 *
	 * @param code
	 * @return the message
	 * @code code code for the message
	 */
	List<AmwayMessageModel> findMessage(String code);
}
