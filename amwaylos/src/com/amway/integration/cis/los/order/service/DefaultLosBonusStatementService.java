package com.amway.integration.cis.los.order.service;

import com.amway.core.los.data.BonusStatementRequestData;
import com.amway.core.los.data.BonusStatementResultData;
import com.amway.core.los.service.LosService;
import com.amway.integration.cis.los.service.impl.AbstractLosService;
import com.hybris.commons.client.RestResponse;
import org.apache.log4j.Logger;

import java.io.File;


/**
 * Default implementation Service for Los Bonus statement
 */
public class DefaultLosBonusStatementService extends AbstractLosService<BonusStatementResultData, BonusStatementRequestData, File>
		implements LosService<BonusStatementRequestData, BonusStatementResultData>
{
	private static final Logger LOG = Logger.getLogger(DefaultLosBonusStatementService.class);

	@Override
	protected BonusStatementResultData createResultObject()
	{
		return new BonusStatementResultData();
	}

	@Override
	protected BonusStatementResultData createDefaultResult()
	{
		final BonusStatementResultData bonusStatementResultData = createResultObject();
		bonusStatementResultData.setReturnCode(new Integer(-1));
		bonusStatementResultData.setReturnMessage("Error to get bonus statement");
		return bonusStatementResultData;
	}

	@Override
	protected File executeEvent(final Object input)
	{
		try
		{
			LOG.info("Calling webservice for Bonus statement.....");
			final RestResponse<File> restResponse = getLosClient().executeLosBonusStatementRequest(getXclientRefId(), getUrlPath(),
					input,
					BonusStatementRequestData.class);

			return restResponse == null ? null : restResponse.getResult();
		}
		catch (final NullPointerException npe)
		{
			return null;
		}
	}
}
