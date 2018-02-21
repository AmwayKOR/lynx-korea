package com.amway.integration.dam.dao.impl;

import static de.hybris.platform.servicelayer.internal.dao.SortParameters.singletonAscending;
import static java.util.Collections.emptyMap;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;

import com.amway.integration.dam.dao.AmwayDamQueueDao;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamEventQueueDaoImpl extends DefaultGenericDao<AmwayDamQueueEntryModel> implements AmwayDamQueueDao
{
	public AmwayDamEventQueueDaoImpl()
	{
		super(AmwayDamQueueEntryModel._TYPECODE);
	}

	@Override
	public List<AmwayDamQueueEntryModel> findEvents(Integer limit)
	{
		return find(emptyMap(), singletonAscending(AmwayDamQueueEntryModel.CREATIONTIME), limit);
	}
}
