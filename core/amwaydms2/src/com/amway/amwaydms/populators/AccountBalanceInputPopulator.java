package com.amway.amwaydms.populators;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonRequestFieldsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.amway.amwaydms.model.AccountBalanceRequest;


/**
 * Created by aiueq92 on 10/8/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/DMS+Populators+and+Converters")
public class AccountBalanceInputPopulator extends AbstractDmsPopulator
        implements Populator<CommonRequestFieldsData, AccountBalanceRequest>
{
    @Override
    public void populate(final CommonRequestFieldsData source, final AccountBalanceRequest target) throws ConversionException
    {
        target.setTxIsoCntryCd(source.getClientCntryCd());
    }
}