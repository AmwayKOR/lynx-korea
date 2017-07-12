/*
 *
 */
package com.amway.core.pos.dao.impl;

import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.pos.dao.AmwayTerminalDao;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation
 */
public class DefaultAmwayTerminalDao extends DefaultGenericDao<AmwayTerminalModel> implements AmwayTerminalDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayTerminalDao.class);

	private ModelService modelService;

	public DefaultAmwayTerminalDao()
	{
		super(AmwayTerminalModel._TYPECODE);
	}

	public List<AmwayTerminalModel> findTerminals(final PointOfServiceModel pointOfService)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("pointOfService", pointOfService);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {at:").append(AmwayTerminalModel.PK).append("} FROM {").append(AmwayTerminalModel._TYPECODE)
				.append(" AS at}");
		queryString.append(" WHERE {");
		queryString.append(AmwayTerminalModel.POINTOFSERVICE).append("}=?pointOfService");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayTerminalModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
}
