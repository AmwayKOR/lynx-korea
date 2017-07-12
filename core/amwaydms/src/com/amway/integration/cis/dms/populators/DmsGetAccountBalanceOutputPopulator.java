package com.amway.integration.cis.dms.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.integration.dms.services.AccountBalanceResponse;
import com.amway.integration.dms.services.AccountBalanceSvcObject;
import org.springframework.beans.factory.annotation.Required;


/**
 * Populator implementation for {@link AccountBalanceResponse} as source and {@link GetBalanceResponseData} as target
 * type.
 */
public class DmsGetAccountBalanceOutputPopulator extends AbstractDmsPopulator
		implements Populator<AccountBalanceResponse, GetBalanceResponseData>
{
	private CommonI18NService commonI18NService;

	@Override
	public void populate(final AccountBalanceResponse source, final GetBalanceResponseData target) throws ConversionException
	{

		if (CollectionUtils.isNotEmpty(source.getAccountBalanceObjList()))
		{
			target.setAccountBalance(populateAccountBalanceData(source.getAccountBalanceObjList()));
		}

	}

	/**
	 * To populate account balance data.
	 *
	 * @param accountBalanceList
	 * @return List<AccountBalanceData>
	 */
	public List<AccountBalanceData> populateAccountBalanceData(final List<AccountBalanceSvcObject> accountBalanceList)
	{
		final List<AccountBalanceData> accountBalanceDataList = new ArrayList<AccountBalanceData>();
		final String currentIsocode = commonI18NService.getCurrentCurrency().getIsocode();

		for (final AccountBalanceSvcObject accountBalance : accountBalanceList)
		{
			if (StringUtils.equalsIgnoreCase(currentIsocode, accountBalance.getCurrencyCd()))
			{
				final AccountBalanceData accountBalanceData = new AccountBalanceData();

				accountBalanceData.setBalanceTypeCd(accountBalance.getBalanceTypeCd());
				accountBalanceData.setBalanceAmount(accountBalance.getBalanceAmount());
				accountBalanceData.setCurrencyCd(accountBalance.getCurrencyCd());
				accountBalanceData.setInstrumentId(accountBalance.getInstrumentId());
				accountBalanceDataList.add(accountBalanceData);
			}
		}
		return accountBalanceDataList;

	}

	/**
	 * @return commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}


	/**
	 * @param commonI18NService the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}
}
