package com.amway.core.unit.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.model.AmwayUnitModel;
import com.amway.core.unit.dao.AmwayUnitDao;


/**
 * Default implementation
 */
public class DefaultAmwayUnitDao extends DefaultGenericDao<AmwayUnitModel> implements AmwayUnitDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayUnitDao.class);

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayUnitDao()
	{
		super(AmwayUnitModel._TYPECODE);
	}

	/**
	 * To find the amway unit using the first element.
	 *
	 * @param code
	 */
	@Override
	public AmwayUnitModel findAmwayUnit(final String code)
	{
		Assert.notNull(code, "Code cannot be null");
		final Map<String, Object> attributes = new HashMap();
		attributes.put("code", code);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {au:").append(AmwayUnitModel.PK).append("} FROM { ").append(AmwayUnitModel._TYPECODE)
				.append(" AS au }");
		queryString.append(" WHERE {");
		queryString.append(AmwayUnitModel.CODE).append("}=?code");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayUnitModel> result = getFlexibleSearchService().search(query);
		final List<AmwayUnitModel> unitModels = result.getResult();

		if (unitModels != null && unitModels.size() > 1)
		{
			LOG.warn("More than one AmwayUnit found for code " + code + " : Using first element");
		}
		return CollectionUtils.isNotEmpty(unitModels) ? unitModels.get(0) : null;
	}
}
