package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.dms.data.AccountBalanceData;
import com.amway.core.dms.data.GetBalanceResponseData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import com.amway.amwaydms.model.AccountBalanceResponse;
import com.amway.amwaydms.model.AccountBalance;

/**
 * Created by aiueq92 on 10/8/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class AccountBalanceOutputPopulator extends AbstractDmsPopulator
        implements Populator<AccountBalanceResponse, GetBalanceResponseData>
{
    private CommonI18NService commonI18NService;

    @Override
    public void populate(final AccountBalanceResponse source, final GetBalanceResponseData target) throws ConversionException
    {

        if (CollectionUtils.isNotEmpty(source.getAccntBalList()))
        {
            target.setAccountBalance(populateAccountBalanceData(source.getAccntBalList()));
        }

        if (source.getErrorMessage() == null) {
            target.setReturnCd(1);
        } else {
            target.setReturnCd(source.getErrorMessage().getCode());
            target.setReturnMessage(source.getErrorMessage().getMessage());
        }

    }

    /**
     * To populate account balance data.
     *
     * @param accountBalanceList
     * @return List<AccountBalanceData>
     */
    public List<AccountBalanceData> populateAccountBalanceData(final List<AccountBalance> accountBalanceList)
    {
        final List<AccountBalanceData> accountBalanceDataList = new ArrayList<AccountBalanceData>();
        final String currentIsocode = commonI18NService.getCurrentCurrency().getIsocode();

        for (final AccountBalance accountBalance : accountBalanceList)
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

