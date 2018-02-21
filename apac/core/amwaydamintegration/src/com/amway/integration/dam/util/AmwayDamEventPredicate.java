package com.amway.integration.dam.util;

import static com.amway.integration.dam.constants.AmwayDamConstants.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.data.AmwayDamEventData;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamEventPredicate implements Predicate<AmwayDamEventData>
{
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

	private List<String> allowedOperations;

	@Override
	public boolean test(AmwayDamEventData eventData)
	{
		if (eventData.getPath() == null)
		{
			LOG.error(ERROR_EVENT_EMPTY_PATH + ": Empty path in registration event");
			return false;
		}
		if (eventData.getOperation() == null)
		{
			LOG.error(ERROR_EVENT_EMPTY_OPERATION + ": Empty operation in registration event");
			return false;
		}
		if (!allowedOperations.contains(eventData.getOperation()))
		{
			LOG.error(ERROR_EVENT_WRONG_OPERATION + ": Unknown operation in registration event");
			return false;
		}
		return true;
	}

	@Required
	public void setAllowedOperations(List<String> allowedOperations)
	{
		this.allowedOperations = allowedOperations;
	}
}
