package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.dms.data.CreateBalanceRequestData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.amway.amwaydms.model.AccountBalanceRequest;

/**
 * Created by aiueq92 on 10/8/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class CreateAccountBalanceInputPopulator extends AbstractDmsPopulator
        implements Populator<CreateBalanceRequestData, AccountBalanceRequest>
{
    @Override
    public void populate(final CreateBalanceRequestData source, final AccountBalanceRequest target)
            throws ConversionException
    {
        target.setBalanceAmount(source.getBalanceAmount());
        target.setBalanceTypeCd(source.getBalanceTypeCd());
        target.setCurrencyCd(source.getCurrencyCd());
        target.setBalanceAmount(source.getBalanceAmount());
        target.setTxSourceCd(source.getTxSourceCd());
        target.setSourcRefNum(source.getSourcRefNum());
        target.setTxTypeCd(source.getTxTypeCd());
        target.setBalanceTypeCd(source.getBalanceTypeCd());
        target.setTxIsoCntryCd(source.getClientCntryCd());

    }
}
