package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import com.amway.core.dms.data.AboPartyAccountHistoryData;
import com.amway.core.dms.data.AccountHistoryDataResponse;
import com.amway.integration.dms.services.AccountHistoryData;
import com.amway.integration.dms.services.AccountHistoryResponse;


/**
 * Populator implementation for {@link AccountHistoryResponse} as source and {@link AccountHistoryDataResponse} as
 * target type.
 */
public class DmsGetAccountHistoryOutputPopulator extends AbstractDmsPopulator
		implements Populator<AccountHistoryResponse, AccountHistoryDataResponse>
{
	@Override
	public void populate(final AccountHistoryResponse source, final AccountHistoryDataResponse target) throws ConversionException
	{
		final List<AccountHistoryData> aboHistoryList = source.getAccountHistoryData();
		final List<AboPartyAccountHistoryData> aboHistoryDataList = new ArrayList<>();
		for (final AccountHistoryData aboHistoryData : aboHistoryList)
		{
			final AboPartyAccountHistoryData accountHistoryData = new AboPartyAccountHistoryData();
			accountHistoryData.setActionCd(aboHistoryData.getActionCd());
			accountHistoryData.setCountryCd(aboHistoryData.getCountryCd());
			accountHistoryData.setDetail(aboHistoryData.getDetail());
			accountHistoryData.setModuleName(aboHistoryData.getModuleName());
			accountHistoryData.setProcessCd(aboHistoryData.getProcessCd());
			accountHistoryData.setProcessTypeCd(aboHistoryData.getProcessTypeCd());
			accountHistoryData.setReasonCd(aboHistoryData.getReasonCd());
			accountHistoryData.setProcessDate(formatDate(aboHistoryData.getProcessDate(), DMSDATEPATTERN, "dd/MM/yyyy"));
			accountHistoryData.setTransactionDate(formatDate(aboHistoryData.getTransactionDate(), DMSDATEPATTERN, "dd/MM/yyyy"));
			accountHistoryData.setTransactionSourceCd(aboHistoryData.getTransactionSourceCd());
			accountHistoryData.setUserId(aboHistoryData.getUserId());
			accountHistoryData.setAboNum(aboHistoryData.getAboNum());
			accountHistoryData.setSalesPlanaff(aboHistoryData.getSalesPlanaff());
			aboHistoryDataList.add(accountHistoryData);
		}
		target.setAccountHistoryDataList(aboHistoryDataList);
	}

}
