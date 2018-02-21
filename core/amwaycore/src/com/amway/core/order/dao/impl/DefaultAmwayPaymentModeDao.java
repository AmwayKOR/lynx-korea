package com.amway.core.order.dao.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.daos.impl.DefaultPaymentModeDao;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import com.amway.core.enums.AmwayBusinessNature;
import com.amway.core.model.AmwayPaymentConfigModel;
import com.amway.core.order.dao.AmwayPaymentModeDao;


/**
 * Default implementation
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class DefaultAmwayPaymentModeDao extends DefaultPaymentModeDao implements AmwayPaymentModeDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayPaymentModeDao.class);


	/**
	 * returns the supported split combinations based on channel, custType and order
	 *
	 * @param channel
	 * @param custType
	 * @param order
	 * @param modeList
	 * @return List<AmwayPaymentConfigModel>
	 */
	@Override
	public List<AmwayPaymentConfigModel> getSupportedSplitCombinations(final SalesApplication channel,
			final AmwayBusinessNature custType, final AbstractOrderModel order, final HashMap<String, Integer> modeList)
	{
		final StringBuilder query = new StringBuilder(1000);

		query.append("SELECT DISTINCT {apc.pk} FROM {AmwayPaymentConfig as apc} WHERE {apc." + AmwayPaymentConfigModel.BASESTORE
				+ "}= '" + order.getStore().getPk() + "' ");

		if (channel != null)
		{
			query.append(" AND {apc.channel} = ({{ SELECT {sa.pk} FROM {SalesApplication as sa} where {sa.code} = '"
					+ channel.getCode() + "'}})");
		}

		if (custType != null)
		{
			query.append(" AND {apc.businessNature} in ({{ select {abn.pk} from {AmwayBusinessNature as abn} where {abn.code} ='"
					+ custType.getCode() + "'}})");
		}
		if (order.getType() != null)
		{
			query.append(" AND {apc.type} in ({{ select {act.pk} from {AmwayCartType as act} where {act.code} = '"
					+ order.getType().getCode() + "'}})");
		}
		query.append(" AND {apc.subtotal} < '" + order.getTotalPrice() + "'");

		if (MapUtils.isNotEmpty(modeList))
		{
			for (final Map.Entry<String, Integer> entry : modeList.entrySet())
			{
				query.append(
						" AND {apc.pk} IN ({{ SELECT {apc.pk} FROM {AmwayPaymentTypeConfig as aptc} WHERE {aptc.config} = {apc.pk} AND {aptc.paymentMode} IN  ({{ select {spm.pk} from {StandardPaymentMode as spm}  WHERE  {spm.code} ='"
								+ entry.getKey() + "' }}) AND {aptc.repeatableCount} >=" + entry.getValue() + "}})");
			}

		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("finale Query for AmwayPaymentConfig : " + query.toString());
		}
		final SearchResult<AmwayPaymentConfigModel> result = getFlexibleSearchService().search(query.toString());
		return result.getResult();
	}

	@Override
	public List<AmwayPaymentConfigModel> getPaymentConfigForCode(final String code, final SalesApplication channel)
	{
		final AmwayPaymentConfigModel amwayPaymentConfig = new AmwayPaymentConfigModel();
		amwayPaymentConfig.setChannel(channel);
		amwayPaymentConfig.setCode(code);
		return getFlexibleSearchService().getModelsByExample(amwayPaymentConfig);

	}
}
