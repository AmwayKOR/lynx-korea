package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.AboPartyAccountHistoryData;
import com.amway.core.dms.data.PartyHistoryDataResponse;
import com.amway.integration.dms.services.PartyHistoryData;
import com.amway.integration.dms.services.PartyHistoryResponse;


/**
 * Populator implementation for {@link PartyHistoryResponse} as source and {@link PartyHistoryDataResponse} as target
 * type.
 */
public class DmsGetPartyHistoryOutputPopulator extends AbstractDmsPopulator
		implements Populator<PartyHistoryResponse, PartyHistoryDataResponse>
{

	@Override
	public void populate(final PartyHistoryResponse source, final PartyHistoryDataResponse target) throws ConversionException
	{
		final List<PartyHistoryData> partyHistoryList = source.getPartyHistoryData();
		final List<AboPartyAccountHistoryData> partyHistoryDataList = new ArrayList<>();
		for (final PartyHistoryData partyHistoryData : partyHistoryList)
		{
			final AboPartyAccountHistoryData accountHistoryData = new AboPartyAccountHistoryData();
			accountHistoryData.setActionCd(partyHistoryData.getActionCd());
			accountHistoryData.setCountryCd(partyHistoryData.getCountryCd());
			accountHistoryData.setDetail(partyHistoryData.getDetail());
			accountHistoryData.setModuleName(partyHistoryData.getModuleName());
			accountHistoryData.setProcessCd(partyHistoryData.getProcessCd());
			accountHistoryData.setProcessTypeCd(partyHistoryData.getProcessTypeCd());
			accountHistoryData.setReasonCd(partyHistoryData.getReasonCd());
			accountHistoryData.setProcessDate(formatDate(partyHistoryData.getProcessDate(), DMSDATEPATTERN, "dd/MM/yyyy"));
			accountHistoryData.setTransactionDate(formatDate(partyHistoryData.getProcessDate(), DMSDATEPATTERN, "dd/MM/yyyy"));
			accountHistoryData.setTransactionSourceCd(partyHistoryData.getTransactionSourceCd());
			accountHistoryData.setUserId(partyHistoryData.getUserId());
			partyHistoryDataList.add(accountHistoryData);
		}
		target.setPartyHistoryDataList(partyHistoryDataList);
	}
}
